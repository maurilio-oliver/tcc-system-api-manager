package br.unip.tcc.tccapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Order {

    private Long id;
    private Long memberId;
    private Long sellerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BigDecimal total;

}
