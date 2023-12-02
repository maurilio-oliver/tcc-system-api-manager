package br.unip.tcc.tccapi.model.test;

import br.unip.tcc.tccapi.model.Personal;
import br.unip.tcc.tccapi.view.Typable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class MaurilioModel implements Typable, Serializable {


    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

}
