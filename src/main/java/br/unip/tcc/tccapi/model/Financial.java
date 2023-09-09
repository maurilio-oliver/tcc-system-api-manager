package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Embeddable
public class Financial extends GenericJsonConverter<Financial> {
    private String bankCode;
    private String bankNumber;
    private String digit;
    private String banckKind;
    private String cardNumber;
}