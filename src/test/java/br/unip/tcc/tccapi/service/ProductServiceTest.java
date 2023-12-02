package br.unip.tcc.tccapi.service;

import br.unip.tcc.tccapi.model.*;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testng.annotations.BeforeTest;

import java.math.BigDecimal;

@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {

    @Autowired
    private ProductService productService;


    @Autowired
    private SellerService sellerService;


    public void before() {
        Member seller = new Member();
        Personal personal = new Personal();
        personal.setMobilePhone("11958110699");
        personal.setTaxId("42505023022");
        personal.setEmail("product.test@integration.com.br");
        personal.setName("test product faster");
        seller.setPersonal(personal);
        this.sellerService.save(seller);
    }

    @Test
    public void simpleProductSaveTest() {
        this.before();
        Member seller = this.sellerService.findByTerm(null, "42505023022", null);
        Product product = new Product();
        product.setName("test product");
        CategoryAllocation allocation = new CategoryAllocation();
        product.setCategory(allocation);
        product.setDescription("simples produto de teste");
        product.setPrice(new BigDecimal("200.90"));
        product.setImagePath("C://home/path/category/subCategory");
        product.setSellerId(seller.getId());

        product = this.productService.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(product.getSellerId(), seller.getId());

    }

    @Test
    public void simpleProductUpdateTest(){
        Member seller = sellerService.findByTerm("42505023022", null, null);
        Product product = new Product();
        product.setName("test product");
        CategoryAllocation allocation = new CategoryAllocation();
        product.setCategory(allocation);
        product.setDescription("simples produto de teste");
        product.setPrice(new BigDecimal("200.90"));
        product.setImagePath("C://home/path/category/subCategory");
        product.setSellerId(seller.getId());

        product = this.productService.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(product.getSellerId(), seller.getId());

        product.setName("test product 2");
        this.productService.save(product);
        Product updatedProduct = this.productService.findProductById(product.getId());

        Assertions.assertEquals(product.getId(), updatedProduct.getId());
        Assertions.assertNotEquals(product.getName(), updatedProduct.getName());
    }

    @Test
    public void simpleProductDeleteTest(){}

    public void simpleProductFindBySellerId(){}


}
