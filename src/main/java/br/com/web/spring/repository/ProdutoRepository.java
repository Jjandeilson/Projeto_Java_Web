package br.com.web.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.web.spring.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	@Query(value=" select * from Produto p where nome_produto like ?1%", nativeQuery=true)
	List<Produto>findByNomeProduto(String nome);
}
