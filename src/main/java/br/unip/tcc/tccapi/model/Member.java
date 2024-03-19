package br.unip.tcc.tccapi.model;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.ColumnTransformer;


import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Member /*implements UserDetails*/ {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private String password;

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

   /* @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (Objects.nonNull(this.seller) && Objects.nonNull(this.seller.getInitializedAt())){
          return List.of(new SimpleGrantedAuthority("ROLE_USER_SELLER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER_COMMON"));
    }

    @Override
    public String getUsername() {
        return this.getPersonal().getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }*/

    public boolean hasPersistir(){
        return Objects.nonNull(this.getPersonal()) &&
                (Objects.nonNull(this.getPersonal().getEmail())
                        && Objects.nonNull(this.getPersonal().getTaxId())
                        && Objects.nonNull(this.getPersonal().getMobilePhone()));
    }


}
