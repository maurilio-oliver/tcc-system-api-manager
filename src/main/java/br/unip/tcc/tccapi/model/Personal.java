package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;

import java.time.LocalDate;

public class Personal extends GenericJsonConverter<Personal> {
    private String name;
    private String taxId;
    private String mobilePhone;
    private String email;
    private LocalDate birthDate;

}
