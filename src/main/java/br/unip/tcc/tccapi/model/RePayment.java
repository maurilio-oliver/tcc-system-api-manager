package br.unip.tcc.tccapi.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
public class RePayment {
    private Long id;
    private Long sellerId;
    private Long memberId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime canceledAt;
    private LocalDateTime paidOffAt;
    private BillingMethodState billingMethod;
    private RePaymentState state;

    public void processState(){
        this.state = RePaymentState.PENDING;
        if (Objects.nonNull(paidOffAt)) {
            this.state = RePaymentState.RE_PAID;
        } else if (Objects.isNull(updatedAt) && LocalDateTime.now().compareTo(this.createdAt) <= 3) {
            this.state = RePaymentState.CONFIRMATION_PENDING;
        } else if (Objects.nonNull(canceledAt)) {
            this.state = RePaymentState.CANCELED;
        }
    }
    public void automaticCancel(){
        boolean isCanceled = LocalDateTime.now().compareTo(this.createdAt) > 3
                && Objects.isNull(this.paidOffAt);

        if (isCanceled) {
            this.canceledAt = LocalDateTime.now();
        }
    }
    public void process(){
        automaticCancel();
        processState();
    }

}
