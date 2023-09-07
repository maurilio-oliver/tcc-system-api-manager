package br.unip.tcc.tccapi.controller;

import br.unip.tcc.tccapi.model.Product;
import br.unip.tcc.tccapi.repository.ProductRepository;
import br.unip.tcc.tccapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity findByProduct() {
        Product product = this.productService.findProductById(null);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity createNewProduct(Product product) {
        return ResponseEntity.ok(this.productService.createNewProduct(product));
    }

    @GetMapping
    public ResponseEntity deleteProductById(final Long productId) {
        this.productService.deleteProductById(productId);
        return ResponseEntity.ok("ok");
    }
}
