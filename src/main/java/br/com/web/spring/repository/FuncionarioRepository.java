package br.com.web.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.web.spring.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	@Query(value=" select * from Funcionario f where nome_funcionario like ?1%", nativeQuery=true)
	List<Funcionario> findByNomeFuncionario(String nomeFuncionario);
}
