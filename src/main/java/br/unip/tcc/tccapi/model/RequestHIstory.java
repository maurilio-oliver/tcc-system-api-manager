package br.unip.tcc.tccapi.model;


import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class RequestHIstory {
    @Id
    private Long id;
    private Long memberId;
    private Long sellerId;


    private LocalDateTime createdAt;
    @Convert(converter = Request.class)
    private Request state;

}
