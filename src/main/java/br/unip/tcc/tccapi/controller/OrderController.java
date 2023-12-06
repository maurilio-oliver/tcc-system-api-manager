package br.unip.tcc.tccapi.controller;

import br.unip.tcc.tccapi.model.Orders;
import br.unip.tcc.tccapi.model.bussines.BussinesException;
import br.unip.tcc.tccapi.repository.OrderRepository;
import br.unip.tcc.tccapi.service.OrderService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

//
//@Controller
@RequestMapping("/orders")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/find-by/{memberId}/member")
    private List<Orders> findByMemberId(@PathVariable final Long memberId) {
       return this.orderService.findByMemberId(memberId);
    }

    @GetMapping("/find-by/{sellerId}/seller")
    private List<Orders> findBySellerId(@PathVariable final Long sellerId) {
        return this.orderService.findBySellerId(sellerId);
    }

    @GetMapping("/find-by-id/{orderId}")
    public void findById(@PathVariable final Long orderId) {
        this.orderService.findById(orderId);
    }

    @PostMapping("/create-new-order")
    public void requestNewOrder(@RequestBody Orders order) {
        this.orderService.save(order);
    }

    @DeleteMapping("/delete/{orderId}")
    public void cancelOrder(@PathVariable final Long orderId){
        this.orderService.cancelById(orderId);
    }

    @PutMapping("update/{orderId}")
    public void updateOrder(final Orders order) {
        this.orderService.save(order);
    }




}
