package br.edu.iftm.lista2.controller;

import br.edu.iftm.lista2.model.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    // "Banco de dados" em memória
    private List<Produto> produtos = new ArrayList<>();
    private Long proximoId = 1L;

    private String validar(Produto p) {
        if (p.getNome() == null || p.getNome().isBlank()) {
            return "O nome não pode ser vazio";
        }
        if (p.getPreco() == null || p.getPreco() <= 0) {
            return "O preço deve ser maior que 0";
        }
        if (p.getQuantidadeEstoque() == null || p.getQuantidadeEstoque() < 0) {
            return "O estoque não pode ser negativo";
        }
        return null; // null = sem erro
    }

    @GetMapping
    public List<Produto> listarTodos() {
        return produtos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Produto encontrado = produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (encontrado == null) {
            return ResponseEntity.status(404).body(Map.of("erro", "Produto não encontrado"));
        }

        return ResponseEntity.ok(encontrado);
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Produto produto) {

        String erro = validar(produto);
        if (erro != null) {
            return ResponseEntity.badRequest().body(Map.of("erro", erro));
        }

        // Verifica código de barras duplicado
        boolean codigoDuplicado = produtos.stream()
                .anyMatch(p -> p.getCodigoBarras() != null &&
                        p.getCodigoBarras().equals(produto.getCodigoBarras()));

        if (codigoDuplicado) {
            return ResponseEntity.status(409).body(Map.of("erro", "Código de barras já cadastrado"));
        }

        produto.setId(proximoId++);
        produtos.add(produto);
        return ResponseEntity.status(201).body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Produto atualizado) {

        Produto existente = produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (existente == null) {
            return ResponseEntity.status(404).body(Map.of("erro", "Produto não encontrado"));
        }

        String erro = validar(atualizado);
        if (erro != null) {
            return ResponseEntity.badRequest().body(Map.of("erro", erro));
        }

        // Verifica código de barras duplicado (ignorando o próprio produto)
        boolean codigoDuplicado = produtos.stream()
                .anyMatch(p -> !p.getId().equals(id) &&
                        p.getCodigoBarras() != null &&
                        p.getCodigoBarras().equals(atualizado.getCodigoBarras()));

        if (codigoDuplicado) {
            return ResponseEntity.status(409).body(Map.of("erro", "Código de barras já cadastrado"));
        }

        existente.setNome(atualizado.getNome());
        existente.setDescricao(atualizado.getDescricao());
        existente.setPreco(atualizado.getPreco());
        existente.setQuantidadeEstoque(atualizado.getQuantidadeEstoque());
        existente.setCodigoBarras(atualizado.getCodigoBarras());

        return ResponseEntity.ok(existente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        Produto existente = produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (existente == null) {
            return ResponseEntity.status(404).body(Map.of("erro", "Produto não encontrado"));
        }

        produtos.remove(existente);
        return ResponseEntity.noContent().build(); // 204
    }
}
