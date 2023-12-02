package br.unip.tcc.tccapi.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class PaymentConfiguration {
    private BillingMethodState method;


}
