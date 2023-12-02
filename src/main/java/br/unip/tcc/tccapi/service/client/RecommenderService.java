package br.unip.tcc.tccapi.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class RecommenderService {


    public void GetAnonymosRecommender(@RequestBody Map<String, Object> productsData) {
        try {
            // URL da API ou serviço que você deseja acessar
            String apiUrl = "http://localhost:4652/product-engine/";

            // Cria uma URL a partir da String da URL
            URL url = new URL(apiUrl);

            // Abre uma conexão HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configura o método da requisição (GET)
            connection.setRequestMethod("GET");

            // Define um tempo limite de conexão (opcional)
            connection.setConnectTimeout(5000);

            // Lê a resposta da requisição
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                reader.close();

                // Imprime a resposta
                System.out.println("Resposta da requisição GET:");
                System.out.println(response.toString());
                ObjectMapper objectMapper = new ObjectMapper();
                List<Map> test = objectMapper.readValue(response.toString(), List.class);
                System.out.println(test);
            } else {
                System.out.println("A requisição GET falhou. Código de resposta: " + responseCode);
            }

            // Fecha a conexão
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new RecommenderService().GetAnonymosRecommender(null);
    }
}
