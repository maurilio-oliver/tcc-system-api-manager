package br.unip.tcc.tccapi.model;


import br.unip.tcc.tccapi.view.ListConverter;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import net.minidev.json.annotate.JsonIgnore;
import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Member{
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private String password;

    private String username;

    @Column(columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    @Convert(converter = Personal.class)
    private Personal personal;

    @Column(columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    @Convert(converter = Financial.class)
    private Financial financial;

    @Column(columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    @Convert(converter = Residential.class)
    private Residential residential;

    @Column(columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    @Convert(converter = Seller.class)
    private Seller seller;

    public Personal getPersonal() {
        return personal == null ? new Personal() : personal;
    }

    public boolean hasPersist(){
        return Objects.nonNull(this.getPersonal()) &&
                (Objects.nonNull(this.getPersonal().getEmail())
                        && Objects.nonNull(this.getPersonal().getTaxId())
                        && Objects.nonNull(this.getPersonal().getMobilePhone())) && this.isValid();
    }

    @JsonIgnore
    public boolean isValid() {
        EmailValidator validator = EmailValidator.getInstance();
        boolean isValid = false;
        if (Objects.nonNull(this.getPersonal()) && Objects.nonNull(this.getPersonal().getEmail())) {
            isValid = validator.isValid(this.getPersonal().getEmail());
        }
        return isValid;
    }




}
