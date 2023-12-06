package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
public class CategoryAllocation extends GenericJsonConverter<CategoryAllocation> {

    private String category;

    private String subCategory;
    private List<String> keywords;

    @Override
    public String getTypeName() {
        return this.getClass().getTypeName();
    }
}
