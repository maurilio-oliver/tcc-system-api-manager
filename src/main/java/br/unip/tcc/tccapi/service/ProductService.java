package br.unip.tcc.tccapi.service;

import br.unip.tcc.tccapi.model.Product;
import br.unip.tcc.tccapi.model.ProductState;
import br.unip.tcc.tccapi.repository.ProductRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public List<Product> findProductBySellerId(final Long sellerId){
        return this.productRepository.findAllBySellerId(sellerId).orElse(List.of());
    }

    /**
     * <h1>Find product by id</h1>
     * <p>find the product by id</p>
     * @param productId long product id
     * @return a product instance if it exists or null
     */
    public Product findProductById(final Long productId){
        return this.productRepository.findById(productId).orElse(new Product());
    }

    /**
     * <h1>Create a new product</h1>
     * <p>creates a new product</p>
     * @param product a valid product instance
     * @return the product that was registered
     */
    @NonNull
    public Product save(Product product){
        // if prince and name and image path not null
        if (Objects.nonNull(product.getPrice())
                && Objects.nonNull(product.getName())
                && Objects.nonNull(product.getImagePath())) {
            // set created at as now
            product.setCreatedAt(LocalDateTime.now());
            // set updated at as now
            product.setUpdatedAt(LocalDateTime.now());
            // save product
            return this.productRepository.save(product);
        }
        return null;
    }

    public void deleteProductById(final Long productId){
        this.productRepository.deleteById(productId);
    }

    @Value("${br.unip.tcc.tccapi.model.member.id.notfound}")
    String v;
    public String testValue(){
        return "${br.unip.tcc.tccapi.model.member.id.notfound}";
    }

    public void findPopularProducts() {
       // this.productRepository.findPopularProducts();
    }
}
