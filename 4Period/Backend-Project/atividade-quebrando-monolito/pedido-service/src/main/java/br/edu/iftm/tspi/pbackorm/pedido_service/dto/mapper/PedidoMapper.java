package br.edu.iftm.tspi.pbackorm.pedido_service.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.edu.iftm.tspi.pbackorm.pedido_service.domain.DetalhePedido;
import br.edu.iftm.tspi.pbackorm.pedido_service.domain.Pedido;
import br.edu.iftm.tspi.pbackorm.pedido_service.dto.DetalhePedidoDTO;
import br.edu.iftm.tspi.pbackorm.pedido_service.dto.PedidoDTO;

@Mapper(componentModel= "spring")
public interface PedidoMapper {
    
    Pedido toEntity(PedidoDTO pedidoDto);

    PedidoDTO toDto(Pedido pedido);

    @Mapping(source = "pedido.id", target = "idPedido")
    @Mapping(source = "id.produtoId", target = "idProduto")
    DetalhePedidoDTO toDetalheDto(DetalhePedido detalhe);
    
    @Mapping(source = "idPedido", target = "pedido.id")
    @Mapping(source = "idProduto", target = "id.produtoId")
    DetalhePedido toDetalheEntity(DetalhePedidoDTO detalheDto);


    List<DetalhePedidoDTO> toDetalhesDtoList(List<DetalhePedido> detalhes);
    List<DetalhePedido> toDetalhesEntityList(List<DetalhePedidoDTO> detalhesDto);

    List<Pedido> toEntityList(List<PedidoDTO> PedidoDto);
    List<PedidoDTO> toDtoList(List<Pedido> Pedido);
}