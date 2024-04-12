package com.iuri.apivendas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iuri.apivendas.dto.VendaRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "venda")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "data")
    private LocalDateTime data;
    @Column(name = "valor")
    private BigDecimal valor;
    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    @JsonBackReference
    private Vendedor vendedor;
    @Column(name = "vendedor_nome")
    private String vendedorNome;

    public static Venda converterParaVenda(VendaRequest vendaRequest){
        return Venda.builder()
            .data(LocalDateTime.now())
            .valor(vendaRequest.getValor())
            .vendedor(new Vendedor(vendaRequest.getIdVendedor()))
            .vendedorNome(vendaRequest.getVendedorNome())
            .build();
    }
}
