package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * this entity's made with java apart by spring boot, test generic convert in enum placeholder
 */
@Getter
@Setter
@Entity
public class Product extends GenericJsonConverter<Product> {

    @Id
    private Long id;
    private Long sellerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BigDecimal price;
    private String name;
    private String description;
    private String imagePath;
    private BigDecimal quality;
    @Embedded
    private CategoryAllocation category;
    @Embedded
    private ProductState productState;


}