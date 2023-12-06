package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Residential extends GenericJsonConverter<Residential> {

    private String street;
    private String neighborhood;
    private String zipCode;
    private String number;
    private String complement;
    private String reference;
    private int kind;
    private String typeName;

    public String getTypeName(){
        return this.getClass().getTypeName();
    }
}
