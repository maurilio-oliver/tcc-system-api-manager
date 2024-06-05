package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Converter;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public String getTypeName() {
        return Personal.class.getTypeName();
    }

    public static void main(String[] args) {
        var personal = new Personal();

    }
}
