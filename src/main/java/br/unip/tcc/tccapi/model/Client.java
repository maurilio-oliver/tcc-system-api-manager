package br.unip.tcc.tccapi.model;

import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {

    @Id
    private Integer id;
    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
}
