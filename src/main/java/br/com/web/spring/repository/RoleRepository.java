package br.com.web.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.web.spring.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
