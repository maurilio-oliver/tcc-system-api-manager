package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = false)
public class Items extends GenericJsonConverter<Items> {

    private List<Product> products;

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
       for (Product product : this.getProducts()) {
           total = total.add(product.getPrice());
       }
       return total;
    }

    @Override
    public String getTypeName() {
        return this.getClass().getTypeName();
    }
}
