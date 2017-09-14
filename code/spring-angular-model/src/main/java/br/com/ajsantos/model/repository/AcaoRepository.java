package br.com.ajsantos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.ajsantos.model.entity.Acao;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Long>, QueryDslPredicateExecutor<Acao>{
	
}
