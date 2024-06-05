package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import br.unip.tcc.tccapi.view.ListConverter;
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
    private LocalDateTime paymentRequested;
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
    private Items items = new Items();

    @Convert (converter = OrderState.Convert.class)
    private OrderState state;

    public Orders processState() {

        if (Objects.nonNull(cancelledAt)){
            this.setState(OrderState.CANCELED);
        }
        else if (Objects.nonNull(deliveredAt)) {
            this.setState(OrderState.DELIVERED);
        }
        else if (Objects.nonNull(deliveredAtConfirmed)) {
            this.setState(OrderState.DELIVERD_CONFIRMED);
        }
        else if (Objects.nonNull(sendAt)) {
            this.setState(OrderState.SENDED);
        }
        else if (Objects.nonNull(acceptedAt)) {
            this.setState(OrderState.ACCEPTED);
        }
        else if (Objects.nonNull(paidOffAt)) {
            this.setState(OrderState.PAID);
        }
        else if (Objects.nonNull(paymentRequested)) {
            this.setState(OrderState.PENDING_PAYMENT);
        }

        else if (Objects.nonNull(requestedAt)) {
            this.setState(OrderState.REQUESTED);
        }
        else {
            this.setState(OrderState.ERROR);
        }
        return this;
    }



}
