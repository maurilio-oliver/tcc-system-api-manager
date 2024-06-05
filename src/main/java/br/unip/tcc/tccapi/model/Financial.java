package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Financial extends GenericJsonConverter<Financial> {
    private String cardNumber;
    private String name;
    private String dueDate;
    private String cvv;

    @Override
    public String getTypeName(){
        return Financial.class.getTypeName();
    }
}