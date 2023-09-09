package br.unip.tcc.tccapi.service;

import br.unip.tcc.tccapi.model.Product;
import br.unip.tcc.tccapi.model.ProductState;
import br.unip.tcc.tccapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public Product findProductBySellerId(final Long sellerId){
        return null; //this.productRepository.findProductBySellerId(sellerId).orElse(new Product());
    }
    public Product findProductById(final Long productId){
        return this.productRepository.findById(productId).orElse(new Product());
    }
    public Product createNewProduct(Product product){
        if (Objects.nonNull(product.getPrice()) && Objects.nonNull(product.getName()) && Objects.nonNull(product.getImagePath())) {
            product.setCreatedAt(LocalDateTime.now());
            product.setUpdatedAt(LocalDateTime.now());
            return this.productRepository.save(product);
        } else {
            product.setDescription("Erro ao cadastrar produto");
            return product;
        }
    }
    public Product save(Product product){
        this.productRepository.save(product);
        return product;
    }
    public void deleteProductById(final Long productId){
        this.productRepository.deleteById(productId);
    }

}
