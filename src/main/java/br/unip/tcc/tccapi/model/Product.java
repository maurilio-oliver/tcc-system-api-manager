package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * this entity's made with java apart by spring boot, test generic convert in enum placeholder
 */

@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sellerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime pausedAt;
    private LocalDateTime deletedAt;
    private LocalDateTime disabledAt;
    private BigDecimal price;
    private Integer clicks;
    private String description;
    private String name;
    private String imagePath;
    private BigDecimal quality;
    private LocalDate releaseDate;
    private String color;
    private Integer size;
    private String title;

    @Enumerated
    private ProductState state;

    @Convert(converter = CategoryAllocation.class)
    @Column(columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    private CategoryAllocation category;

    public boolean inConfiguration(){
        return Objects.isNull(price) && Objects.isNull(name) && Objects.isNull(description) && Objects.isNull(imagePath);
    }
    public void processState(){
        if (inConfiguration()){
            this.state = ProductState.SET_UP;
        } else if (Objects.nonNull(pausedAt)) {
            this.state = ProductState.PAUSED;
        } else if(Objects.nonNull(deletedAt)){
            this.state = ProductState.DELETED;
        }
        else if(Objects.nonNull(disabledAt)){
            this.state = ProductState.DISABLED;
        }
        else {
            this.state = ProductState.ACTIVE;
        }
    }
    public void process(){
        processState();
    }

}