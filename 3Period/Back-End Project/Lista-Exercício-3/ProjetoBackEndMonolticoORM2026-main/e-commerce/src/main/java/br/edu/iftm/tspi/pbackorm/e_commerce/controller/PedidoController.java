package br.edu.iftm.tspi.pbackorm.e_commerce.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Cliente;
import br.edu.iftm.tspi.pbackorm.e_commerce.domain.DetalhePedido;
import br.edu.iftm.tspi.pbackorm.e_commerce.domain.DetalhePedidoID;
import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Pedido;
import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Produto;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.ItemPedidoDTO;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.PedidoDTO;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.mapper.ItemPedidoMapper;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.mapper.PedidoMapper;
import br.edu.iftm.tspi.pbackorm.e_commerce.repository.ClienteRepository;
import br.edu.iftm.tspi.pbackorm.e_commerce.repository.PedidoRepository;
import br.edu.iftm.tspi.pbackorm.e_commerce.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoController {

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoMapper mapper;
    private final ItemPedidoMapper itemMapper;

    @GetMapping
    public List<PedidoDTO> listar() {
        return mapper.toDtoList(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Integer id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Pedido de ID " + id + " não encontrado"));
        return ResponseEntity.ok(mapper.toDto(pedido));
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> criar(@Valid @RequestBody PedidoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cliente de ID " + dto.getClienteId() + " não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setDataPedido(dto.getDataPedido() != null
                ? dto.getDataPedido() : LocalDateTime.now());
        pedido.setCliente(cliente);

        List<DetalhePedido> itens = new ArrayList<>();
        if (dto.getItens() != null) {
            for (ItemPedidoDTO itemDto : dto.getItens()) {
                itens.add(criarDetalhePedido(pedido, null, itemDto));
            }
        }
        pedido.setDetalhesPedido(itens);

        Pedido pedidoSalvo = repository.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(pedidoSalvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> atualizar(@PathVariable Integer id,
            @Valid @RequestBody PedidoDTO dto) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Pedido de ID " + id + " não encontrado"));

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cliente de ID " + dto.getClienteId() + " não encontrado"));

        pedido.setDataPedido(dto.getDataPedido() != null
                ? dto.getDataPedido() : pedido.getDataPedido());
        pedido.setCliente(cliente);

        Pedido pedidoAtualizado = repository.save(pedido);
        return ResponseEntity.ok(mapper.toDto(pedidoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Pedido de ID " + id + " não encontrado"));
        repository.delete(pedido);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{pedidoId}/itens")
    public ResponseEntity<List<ItemPedidoDTO>> listarItens(@PathVariable Integer pedidoId) {
        Pedido pedido = repository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Pedido de ID " + pedidoId + " não encontrado"));
        return ResponseEntity.ok(itemMapper.toDtoList(pedido.getDetalhesPedido()));
    }

    @PostMapping("/{pedidoId}/itens")
    public ResponseEntity<ItemPedidoDTO> adicionarItem(@PathVariable Integer pedidoId,
            @Valid @RequestBody ItemPedidoDTO dto) {
        Pedido pedido = repository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Pedido de ID " + pedidoId + " não encontrado"));

        DetalhePedido item = criarDetalhePedido(pedido, pedidoId, dto);
        if (pedido.getDetalhesPedido() == null) {
            pedido.setDetalhesPedido(new ArrayList<>());
        }
        pedido.getDetalhesPedido().add(item);

        repository.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemMapper.toDto(item));
    }

    @PutMapping("/{pedidoId}/itens/{index}")
    public ResponseEntity<ItemPedidoDTO> atualizarItem(@PathVariable Integer pedidoId,
            @PathVariable int index,
            @Valid @RequestBody ItemPedidoDTO dto) {
        Pedido pedido = repository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Pedido de ID " + pedidoId + " não encontrado"));

        List<DetalhePedido> itens = pedido.getDetalhesPedido();
        if (itens == null || index < 0 || index >= itens.size()) {
            throw new EntityNotFoundException(
                    "Item de índice " + index + " não encontrado");
        }

        DetalhePedido item = itens.get(index);
        item.setPrecoVenda(dto.getPrecoVenda());
        item.setQuantidade(dto.getQuantidade());
        item.setDesconto(dto.getDesconto());

        repository.save(pedido);
        return ResponseEntity.ok(itemMapper.toDto(item));
    }

    @DeleteMapping("/{pedidoId}/itens/{index}")
    public ResponseEntity<Void> removerItem(@PathVariable Integer pedidoId,
            @PathVariable int index) {
        Pedido pedido = repository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Pedido de ID " + pedidoId + " não encontrado"));

        List<DetalhePedido> itens = pedido.getDetalhesPedido();
        if (itens == null || index < 0 || index >= itens.size()) {
            throw new EntityNotFoundException(
                    "Item de índice " + index + " não encontrado");
        }

        itens.remove(index);
        repository.save(pedido);
        return ResponseEntity.noContent().build();
    }

    private DetalhePedido criarDetalhePedido(Pedido pedido, Integer pedidoId,
            ItemPedidoDTO dto) {
        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produto de ID " + dto.getProdutoId() + " não encontrado"));

        DetalhePedido item = new DetalhePedido();
        DetalhePedidoID id = new DetalhePedidoID();
        if (pedidoId != null) {
            id.setPedidoId(pedidoId);
        }
        id.setProdutoId(produto.getId());
        item.setId(id);
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setPrecoVenda(dto.getPrecoVenda());
        item.setQuantidade(dto.getQuantidade());
        item.setDesconto(dto.getDesconto());
        return item;
    }
}
