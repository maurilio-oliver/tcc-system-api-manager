package br.unip.tcc.tccapi.repository;

import br.unip.tcc.tccapi.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT Member from Member where Member.id = :sellerId and Member.seller.initializedAt is not null ")
    public Member findSellerById(@Param("sellerId") Long sellerId);
}