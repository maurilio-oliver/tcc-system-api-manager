package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;

public class Financial extends GenericJsonConverter<Financial> {
    private String bankCode;
    private String bankNumber;
    private String digit;

    private String banckKind;

    private String cardNumber;


}