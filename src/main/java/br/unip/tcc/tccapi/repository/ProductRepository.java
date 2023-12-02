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
//    public Optional<Product> findProductBySellerId(final Long sellerId);

//    public Optional<Product> findAllByCategory(CategoryAllocation category);

    @Query(value = "",nativeQuery = true)
    public Optional<List<Product>> findAllBySellerId(Long sellerId);

//    @Query(value = "SELECT p FROM Product p ")
//    public Optional<Product> findAllLatestBestSellers();
//
//    @Query(value = "SELECT p FROM Product p order by p.sellers limit 10")
//    public Optional<List<Product>> findAllPopularProductsbByellers();

}
