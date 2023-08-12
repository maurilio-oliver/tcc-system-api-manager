package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /** @see GenericJsonConverter **/
    @Column(columnDefinition = "jsonb")
    @Convert(converter = Personal.class)
    private Personal personal;

    /** @see GenericJsonConverter **/
    @Column(columnDefinition = "jsonb")
    @Convert(converter = Personal.class)
    private Financial financial;





}
