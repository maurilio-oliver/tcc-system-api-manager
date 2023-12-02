package br.unip.tcc.tccapi.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter

public enum ProductState  {
    SET_UP(0L, LocalDateTime.now()),
    ACTIVE(1L,LocalDateTime.now()),
    PAUSED(2L,LocalDateTime.now()),
    DISABLED(3L,LocalDateTime.now()),
    DELETED(4L,LocalDateTime.now());

    ProductState(Long id, LocalDateTime now) {
        this.id = id;
        this.now = now;
    }

    private Long id;
    private LocalDateTime now;


}
