package br.unip.tcc.tccapi.repository;

import br.unip.tcc.tccapi.model.RequestHIstory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestHistoryRepository extends JpaRepository<RequestHIstory, Long> { }
