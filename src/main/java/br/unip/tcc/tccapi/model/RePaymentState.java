package br.unip.tcc.tccapi.model;

public enum RePaymentState {
    PENDING(),
    CONFIRMATION_PENDING(),
    CONFIRMED(),
    RE_PAID();


    private int id;
    private BillingMethodState billingMethod;


}
