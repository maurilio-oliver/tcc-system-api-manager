package br.unip.tcc.tccapi.repository;

import br.unip.tcc.tccapi.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> { }
