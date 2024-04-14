package com.iuri.apivendas.dto;

import com.iuri.apivendas.model.Venda;
import com.iuri.apivendas.model.Vendedor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendaResponse {

    private Integer id;
    private LocalDateTime data;
    private BigDecimal valor;
    private Vendedor vendedor;

    public static VendaResponse converterParaResponse(Venda venda){
        return VendaResponse.builder()
            .id(venda.getId())
            .data(venda.getData())
            .valor(venda.getValor())
            .vendedor(venda.getVendedor())
            .build();
    }
}
