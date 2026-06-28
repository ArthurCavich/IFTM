package br.edu.iftm.tspi.pbackorm.e_commerce.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

    @Query("""
            SELECT DISTINCT ped FROM Pedido ped
            JOIN FETCH ped.detalhesPedido d
            JOIN FETCH d.produto p
            WHERE ped.cliente.id = :idCliente
            AND ped.dataPedido BETWEEN :inicio AND :fim
            """)
    List<Pedido> buscarPorClienteEPeriodo(
            @Param("idCliente") String idCliente,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);

}
