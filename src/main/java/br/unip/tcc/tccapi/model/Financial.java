package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Financial extends GenericJsonConverter<Financial> {
    private String bankCode;
    private String bankNumber;
    private String digit;
    private String bankKind;

    @Override
    public String getTypeName(){
        return Financial.class.getTypeName();
    }
}