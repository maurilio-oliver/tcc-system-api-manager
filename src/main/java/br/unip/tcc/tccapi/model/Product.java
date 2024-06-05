package br.unip.tcc.tccapi.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * this entity's made with java apart by spring boot, test generic convert in enum placeholder
 */

@EqualsAndHashCode
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue

    private Long id;
    private Long sellerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime pausedAt;
    private LocalDateTime deletedAt;
    private LocalDateTime disabledAt;
    private BigDecimal price;
    private String imagePath;
    private BigDecimal quality;
    private LocalDate releaseDate;
    private String color;
    private Long size;
    private String title;
    private String description;

    @Enumerated
    private ProductState state;

    private Long category;

    public boolean inConfiguration(){

        return Objects.isNull(price) && Objects.isNull(title) && Objects.isNull(description) && Objects.isNull(imagePath);
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