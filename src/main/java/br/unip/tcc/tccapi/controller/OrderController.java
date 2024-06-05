package br.unip.tcc.tccapi.controller;

import br.unip.tcc.tccapi.model.Orders;
import br.unip.tcc.tccapi.model.Product;
import br.unip.tcc.tccapi.model.bussines.BussinesException;
import br.unip.tcc.tccapi.repository.OrderRepository;
import br.unip.tcc.tccapi.service.OrderService;
import br.unip.tcc.tccapi.service.ProductService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//
//@Controller
@RequestMapping("/orders")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    /**
     * <h1>Find orders by member id</h1>
     * @param memberId
     * @return {@code List<Order>} all user orders
     */
    @GetMapping("/find-by/{memberId}/member")
    private List<Orders> findByMemberId(@PathVariable final Long memberId) {
       return this.orderService.findByMemberId(memberId);
    }

    /**
     * <h1>Find order by <span style="color:red;">seller</span> id</h1>
     * @param sellerId
     * @return {@code @return}
     */
    @GetMapping("/find-by/{sellerId}/seller")
    private List<Orders> findBySellerId(@PathVariable final Long sellerId) {
        return this.orderService.findBySellerId(sellerId);
    }

    /**
     * <h1>Find order by id</h1>
     * @param orderId
     * @return the product corresponding to the id
     */
    @GetMapping("/find-by-id/{orderId}")
    public Orders findById(@PathVariable final Long orderId) {
        return this.orderService.findById(orderId);
    }

    /**
     * <h1>Request a new order</h1>
     * @param order
     */
    @PostMapping("/create-new-order")
    public void requestNewOrder(@RequestParam Long memberId, @RequestParam Long sellerId ,@RequestBody List<Long> productList) {
        Orders newOrder = new Orders();
        List<Product> products = new ArrayList<>();
        productList.forEach(id -> {
            Product product = this.productService.findProductById(id);
            if (Objects.nonNull(product)) {
                products.add(product);
            }
        });
        newOrder.getItems().setProducts(products);
        newOrder.setMemberId(memberId);
        newOrder.setSellerId(sellerId);
        newOrder.setRequestedAt(LocalDateTime.now());
        this.orderService.save(newOrder);
    }

    /**
     * <h1>Cancel an order by id</h1>
     * @param orderId
     */
    @DeleteMapping("/delete/{orderId}")
    public void cancelOrder(@PathVariable final Long orderId){
        this.orderService.cancelById(orderId);
    }

    /**
     * Update an order
     * @param order
     */
    @PutMapping("update/{orderId}")
    public void updateOrder(final Orders order) {
        this.orderService.save(order);
    }





}
