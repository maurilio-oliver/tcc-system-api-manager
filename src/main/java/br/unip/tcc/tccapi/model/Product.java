package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;

import java.math.BigDecimal;

/**
 * this entity's made with java apart by spring boot, test generic convert in enum placeholder
 */
@Entity
public class Product extends GenericJsonConverter<Product> {
    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal price;
    private String name;
    private String content;
    private BigDecimal quality;

    @Column(columnDefinition = "jsonb")
    @Convert(converter=ProductState.Convert.class)
    private ProductState productState;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void process(){}
}