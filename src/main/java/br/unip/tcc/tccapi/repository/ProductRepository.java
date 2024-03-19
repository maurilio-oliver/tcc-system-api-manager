package br.unip.tcc.tccapi.repository;

import br.unip.tcc.tccapi.model.CategoryAllocation;
import br.unip.tcc.tccapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select p from Product p where p.sellerId = ?1",nativeQuery = true)
    Optional<List<Product>> findAllBySellerId(Long sellerId);

    @Query(value = "select p from Product p where p.category  = ?1 limit 20", nativeQuery = true)
    List<Product> findALlByCategory(Long category, Integer limit);

    @Query(value = "select * from product p limit 10", nativeQuery = true)
    List<Product> findAllPopularProducts();

}
