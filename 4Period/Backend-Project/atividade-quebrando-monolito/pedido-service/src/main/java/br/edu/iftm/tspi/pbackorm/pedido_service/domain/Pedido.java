package br.edu.iftm.tspi.pbackorm.pedido_service.domain;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="pedidos")
@Data
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="PedidoID")
    private Integer id;

    @Column(name="datapedido")
    private LocalDateTime dataPedido;

    @Column(name = "ClienteID", nullable = false)
    private String idCliente;
    
    @OneToMany(mappedBy="pedido",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    private List<DetalhePedido> detalhesPedido;
    
}
