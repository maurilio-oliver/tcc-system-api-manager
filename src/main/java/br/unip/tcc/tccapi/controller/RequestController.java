package br.unip.tcc.tccapi.controller;

import br.unip.tcc.tccapi.config.ActiveMQConfig;
import br.unip.tcc.tccapi.model.Product;
import br.unip.tcc.tccapi.model.Request;
import br.unip.tcc.tccapi.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RequestController {

    @Autowired
    ActiveMQConfig jms;

    @Autowired
    private RequestRepository requestRepository;

    public Product findById() {return null;}

    public void findBySellerId() {}

    public void findBySellerAndMEmberId() {}

    public void findByMemberId() {}



    void saveAsync() {


   }




}
