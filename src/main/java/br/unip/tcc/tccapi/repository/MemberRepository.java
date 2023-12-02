package br.unip.tcc.tccapi.repository;

import br.unip.tcc.tccapi.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


    @Query(value = "select * from Member u where cast(u.personal as JSONB) ->> 'email'  = ?1 or cast(u.personal as JSONB) ->> 'taxId' = ?2 or cast(u.personal as JSONB) ->> 'phoneNumber' =  ?3 limit 1", nativeQuery = true)
    Member findByTerms(String email, String taxId, String phoneNumber);

    @Query(value = "select * from Member u where u.email = ?1", nativeQuery = true)
    Member findByEmail(String email);


}