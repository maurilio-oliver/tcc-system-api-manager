package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import jakarta.persistence.Embeddable;

@Embeddable
public class CategoryAllocation extends GenericJsonConverter<CategoryAllocation> {
    private String categoory;
    private String subCategory;
}
