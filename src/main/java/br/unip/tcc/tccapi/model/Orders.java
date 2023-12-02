package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CollectionType;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Type;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private Long sellerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime paidOffAt;
    private LocalDateTime requestedAt;
    private LocalDateTime confirmationPending;
    private LocalDateTime acceptedAt;
    private LocalDateTime sendAt;
    private LocalDateTime deliveredAt;
    private LocalDateTime deliveredAtConfirmed;
    private LocalDateTime cancelledAt;
    private BigDecimal total;
    @Column(columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    @Convert(converter = Items.class)
    private Items items;

    @Convert (converter = OrderState.Convert.class)
    private OrderState state;



}
