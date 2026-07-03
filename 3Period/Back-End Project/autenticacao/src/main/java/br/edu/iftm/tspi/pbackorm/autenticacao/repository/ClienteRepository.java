package br.edu.iftm.tspi.pbackorm.autenticacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pbackorm.autenticacao.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer>{

}
