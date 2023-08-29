package br.unip.tcc.tccapi.model;


import jakarta.persistence.Convert;

import java.time.LocalDateTime;


public class OrderHIstory {


    private Long id;
    private Long memberId;
    private Long sellerId;


    private LocalDateTime createdAt;
    @Convert(converter = Order.class)
    private Order state;

}
