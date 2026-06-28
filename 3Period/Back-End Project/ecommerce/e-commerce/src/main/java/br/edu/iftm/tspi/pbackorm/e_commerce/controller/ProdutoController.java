package br.edu.iftm.tspi.pbackorm.e_commerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Produto;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.ConsumoProdutoDTO;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.ProdutoDTO;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.ProdutoUnidadesDTO;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.VendaProdutoDTO;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.mapper.ProdutoMapper;
import br.edu.iftm.tspi.pbackorm.e_commerce.repository.ProdutoRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/produtos")
@AllArgsConstructor
public class ProdutoController {

    private final ProdutoRepository repository;

    private final ProdutoMapper mapper;

    @PostMapping
    public ResponseEntity<ProdutoDTO> novo(@Valid @RequestBody ProdutoDTO produtoNovoDto) {
        Produto produtoEntidade = mapper.toEntity(produtoNovoDto);
        Produto produtoSalvo = repository.save(produtoEntidade);
        ProdutoDTO produtoSalvoDto = mapper.toDto(produtoSalvo);
        return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(produtoSalvoDto);
    }

    @GetMapping("/unidades-compradas")
    public ResponseEntity<List<ProdutoUnidadesDTO>> unidadesCompradas(
            @RequestParam(required = false) Integer codigo) {
        List<ProdutoUnidadesDTO> lista = repository.buscarUnidadesCompradas(codigo);
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}/vendas")
    public ResponseEntity<List<VendaProdutoDTO>> vendasPorProduto(@PathVariable Integer id) {
        List<VendaProdutoDTO> lista = repository.buscarVendasPorProduto(id);
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/consumo-cliente/{idCliente}")
    public ResponseEntity<List<ConsumoProdutoDTO>> consumoPorCliente(@PathVariable String idCliente) {
        List<ConsumoProdutoDTO> lista = repository.consumoPorProdutoDoCliente(idCliente);
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

}
