package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;



public class CategoryAllocation extends GenericJsonConverter<CategoryAllocation> {

    private String categoory;

    private String subCategory;
    private String keywords;

    @Override
    public String getTypeName() {
        return this.getClass().getTypeName();
    }
}
