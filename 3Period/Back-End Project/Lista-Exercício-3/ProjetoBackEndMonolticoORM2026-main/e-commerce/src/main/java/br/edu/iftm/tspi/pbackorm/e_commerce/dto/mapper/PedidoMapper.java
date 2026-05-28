package br.edu.iftm.tspi.pbackorm.e_commerce.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Pedido;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.PedidoDTO;

@Mapper(componentModel = "spring", uses = ItemPedidoMapper.class)
public interface PedidoMapper {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "detalhesPedido", target = "itens")
    PedidoDTO toDto(Pedido pedido);

    List<PedidoDTO> toDtoList(List<Pedido> pedidos);
}
