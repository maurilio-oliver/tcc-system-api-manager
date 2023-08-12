package br.unip.tcc.tccapi.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Request {
    @Id
    @GeneratedValue
    private Long id;
    private Long memberId;
    private Long sellerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BigDecimal total;
    private
    @Convert
    private List<Product> products;

    @Column(columnDefinition = "jsonb")
    @Convert(converter = RequestState.Convert.class)
    private RequestState state;


}
