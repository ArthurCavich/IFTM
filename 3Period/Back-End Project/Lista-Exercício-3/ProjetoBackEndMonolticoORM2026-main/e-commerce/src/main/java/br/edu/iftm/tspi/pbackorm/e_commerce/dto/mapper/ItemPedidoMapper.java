package br.edu.iftm.tspi.pbackorm.e_commerce.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.DetalhePedido;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.ItemPedidoDTO;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {

    @Mapping(source = "produto.id", target = "produtoId")
    ItemPedidoDTO toDto(DetalhePedido entity);

    List<ItemPedidoDTO> toDtoList(List<DetalhePedido> itens);
}
