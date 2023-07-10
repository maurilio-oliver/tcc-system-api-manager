package br.unip.tcc.tccapi.model;

import jakarta.persistence.*;

@Entity
public class Request {
    @Id
    @GeneratedValue
    private Long id;


    @Column(columnDefinition = "jsonb")
    @Convert(converter = RequestState.Convert.class)
    private RequestState state;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public void process(){}
}
