package br.unip.tcc.tccapi.service.client;

import br.unip.tcc.tccapi.model.Product;
import br.unip.tcc.tccapi.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/product/recommender")
public class RecommenderService {

    @Value("${br.unip.tcc.intelligence.service.host}")
    private String inteligenceHost;

    @Autowired
    private ProductService productService;

    private ObjectMapper objectMapper = configureMapper();

    private ObjectMapper configureMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(SerializationFeature.FLUSH_AFTER_WRITE_VALUE, true);
    }


    @PostMapping("/*")
    public ResponseEntity GetAnonymosRecommender(@RequestBody List<Long> productsData) throws URISyntaxException, IOException, InterruptedException {
        List<Product> products = new ArrayList<>();
        productsData.forEach(id -> {
            Product candidate = productService.findProductById(id);
            if (Objects.nonNull(candidate))
                products.add(candidate);
        });
        productService.findProductByCategory(products.get(products.size()-1).getCategory(), 10).forEach(
                product -> {
                    products.add(product);
                }
        );


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(inteligenceHost + "test/1")).header("Content-Type", "application/json").PUT(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(products))).build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<Product> recommender = (List<Product>) objectMapper.readValue(response.body(),List.class);
        return ResponseEntity.ok(recommender);
    }
}
