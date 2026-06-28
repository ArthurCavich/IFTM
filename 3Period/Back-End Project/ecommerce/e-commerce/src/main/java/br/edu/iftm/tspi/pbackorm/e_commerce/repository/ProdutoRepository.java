package br.edu.iftm.tspi.pbackorm.e_commerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Produto;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.ConsumoProdutoDTO;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.ProdutoUnidadesDTO;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.VendaProdutoDTO;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

    public List<Produto> 
        findByNomeContainingIgnoreCaseAndEstoqueGreaterThanAndPrecoLessThan(
            String nome,Short estoqueMin, Double precoMax);

    @Query("""
            SELECT p.nome AS nome, SUM(d.quantidade) AS total
            FROM DetalhePedido d
            JOIN d.produto p
            WHERE (:codigo IS NULL OR p.id = :codigo)
            GROUP BY p.nome
            """)
    List<ProdutoUnidadesDTO> buscarUnidadesCompradas(@Param("codigo") Integer codigo);

    @Query("""
            SELECT new br.edu.iftm.tspi.pbackorm.e_commerce.dto.VendaProdutoDTO(
                d.pedido.id, d.quantidade,
                d.precoVenda * d.quantidade,
                d.precoVenda * d.quantidade * d.desconto)
            FROM DetalhePedido d
            WHERE d.produto.id = :idProduto
            """)
    List<VendaProdutoDTO> buscarVendasPorProduto(@Param("idProduto") Integer idProduto);

    @Query("""
            SELECT new br.edu.iftm.tspi.pbackorm.e_commerce.dto.ConsumoProdutoDTO(
                p.nome, SUM(d.precoVenda * d.quantidade))
            FROM DetalhePedido d
            JOIN d.produto p
            WHERE d.pedido.cliente.id = :idCliente
            GROUP BY p.nome
            """)
    List<ConsumoProdutoDTO> consumoPorProdutoDoCliente(@Param("idCliente") String idCliente);

    @Query("""
            SELECT SUM(d.precoVenda * d.quantidade)
            FROM DetalhePedido d
            WHERE d.produto.categoria.id = :idCategoria
            """)
    Double totalConsumidoPorCategoria(@Param("idCategoria") Integer idCategoria);

}
