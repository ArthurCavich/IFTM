package br.edu.iftm.pbackorm.contatos.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="TB_CONTATO")
@NoArgsConstructor
@Data
public class Contato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COD_CONTATO")
    private Integer codigo;

    @Column(name="NOM_CONTATO",nullable=false)
    @NotBlank(message = "Nome e obrigatorio")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;
    

    @Column(name="DES_EMAIL",nullable=false)
    @NotBlank(message = "Email e obrigatorio")
    @Email(message = "Email invalido")
    private String email;

    @Column(name="DES_TELEFONE",nullable=false)
    @NotBlank(message = "Telefone e obrigatorio")
    @Pattern(regexp = "\\d+", message = "Telefone deve conter apenas numeros")
    private String telefone;

    @CreationTimestamp
    @Column(name="DAT_CADASTRO")
    private LocalDateTime dataCadastro;
}
