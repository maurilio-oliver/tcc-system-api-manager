package br.unip.tcc.tccapi.repository;

import br.unip.tcc.tccapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product, Long> {
//    public Optional<Product> findProductBySellerId(final Long sellerId);

}
