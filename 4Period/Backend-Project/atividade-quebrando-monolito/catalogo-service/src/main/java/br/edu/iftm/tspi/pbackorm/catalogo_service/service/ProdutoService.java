package br.edu.iftm.tspi.pbackorm.catalogo_service.service;

import org.springframework.stereotype.Service;

import br.edu.iftm.tspi.pbackorm.catalogo_service.domain.Produto;
import br.edu.iftm.tspi.pbackorm.catalogo_service.exception.EstoqueInsuficienteException;
import br.edu.iftm.tspi.pbackorm.catalogo_service.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public void atualizarEstoque(Integer produtoID, Short quantidade) {
        Produto produto = produtoRepository.findById(produtoID)
                    .orElseThrow(() -> new EntityNotFoundException(
                    "Produto de ID " + produtoID + " não encontrado"));
        Short estoqueNovo = (short) (produto.getEstoque() - quantidade);                    
        if (estoqueNovo < 0) {
            throw new EstoqueInsuficienteException(
                     "Estoque insuficiente para o produto " + produto.getNome());
        }                    
        produto.setEstoque(estoqueNovo);
        produtoRepository.save(produto);
    }

}
