package br.unip.tcc.tccapi.model;

import java.time.LocalDateTime;

public abstract class User {
    private Long id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Personal personal;
    private Financial financial;

}
