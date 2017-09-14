package br.com.ajsantos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.ajsantos.model.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, QueryDslPredicateExecutor<Usuario> {

	/**
	 * @param username
	 * @return {@link Usuario}
	 */
	 Usuario findByUsername(String username);
}
