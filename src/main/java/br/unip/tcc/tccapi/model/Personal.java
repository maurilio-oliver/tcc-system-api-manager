package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Personal extends GenericJsonConverter<Personal> {
    private String name;
    private String taxId;
    private String mobilePhone;
    private String email;
    private LocalDate birthDate;


}
