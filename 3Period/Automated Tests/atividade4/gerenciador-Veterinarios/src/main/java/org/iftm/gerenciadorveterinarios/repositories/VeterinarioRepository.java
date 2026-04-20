package org.iftm.gerenciadorveterinarios.repositories;

import java.util.List;
import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Integer> {

    List<Veterinario> findByNome(String nome);

    List<Veterinario> findByNomeIgnoreCase(String nome);

    List<Veterinario> findByNomeContains(String nome);

    @Query("SELECT v FROM Veterinario v WHERE LOWER(v.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Veterinario> findByNomeContainsIgnoreCase(@Param("nome") String nome);

    List<Veterinario> findBySalarioGreaterThan(java.math.BigDecimal salario);

    List<Veterinario> findBySalarioLessThan(java.math.BigDecimal salario);

    List<Veterinario> findBySalarioBetween(java.math.BigDecimal minimo, java.math.BigDecimal maximo);
}