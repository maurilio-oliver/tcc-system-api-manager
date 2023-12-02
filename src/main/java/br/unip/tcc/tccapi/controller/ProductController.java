package br.unip.tcc.tccapi.controller;

import br.unip.tcc.tccapi.model.CategoryAllocation;
import br.unip.tcc.tccapi.model.Orders;
import br.unip.tcc.tccapi.model.Product;
import br.unip.tcc.tccapi.repository.ProductRepository;
import br.unip.tcc.tccapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // get product by product id
    @GetMapping("/find-by-id/{productId}")
    public ResponseEntity findByProduct(@PathVariable final Long productId) {
        Product product = this.productService.findProductById(productId);
        return ResponseEntity.ok(product);
    }

    // create a new product
    @PostMapping("/create-new")
    public ResponseEntity createNewProduct(@RequestBody Product product) {
            Product createdProduct = this.productService.save(product);

        return (ResponseEntity) ResponseEntity.ok(createdProduct);
    }

    // delete product by id
    @DeleteMapping("/delete-by-id/{productId}")
    public ResponseEntity deleteProductById(@PathVariable final Long productId) {
        this.productService.deleteProductById(productId);
        return ResponseEntity.ok("ok");
    }
    // update product by id
    @PutMapping("/update/{productId}")
    public ResponseEntity updateByProductId(@PathVariable final Long productId, @RequestBody Orders orders) {
        return null;
    }

    @GetMapping
    public List<Product> getProductsRecommender(
            @RequestBody List<Long> productsNavigation
    ) {
        if (productsNavigation.isEmpty()) {
            this.productService.findPopularProducts();
        } else {

        }
        return null;
    }

    @GetMapping("tes")
    public ResponseEntity test() {
        Product product = new Product();
        product.setCategory(new CategoryAllocation());
        product.process();
        return ResponseEntity.ok(product);
    }
}
