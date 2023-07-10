package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    /** @see GenericJsonConverter **/
    @Column(columnDefinition = "jsonb")
    @Convert(converter = Personal.class)
    private Personal personal;

    /** @see GenericJsonConverter **/
    @Column(columnDefinition = "jsonb")
    @Convert(converter = Personal.class)
    private Financial financial;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
