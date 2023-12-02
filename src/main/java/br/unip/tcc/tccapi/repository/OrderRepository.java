package br.unip.tcc.tccapi.repository;

import br.unip.tcc.tccapi.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query(value = "select orders from orders o where o.memberId = :memberId order by o.createdAt", nativeQuery = true)
    public List<Orders> findAllByMemberId(final Long memberId);

    @Query(value = "select orders from orders o where o.sellerId = :sellerId order by o.createdAt", nativeQuery = true)
    List<Orders> findAllBySellerId(Long sellerId);


}
