package br.com.web.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.web.spring.model.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{

	@Query(value=" select * from Fornecedor f where nome_fornecedor like ?1%", nativeQuery=true)
	List<Fornecedor> findByNomeFornecedorIgnoreCase(String nomeFornecedor);
	
}
