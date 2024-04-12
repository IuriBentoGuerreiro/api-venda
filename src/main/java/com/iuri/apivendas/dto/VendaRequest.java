package com.iuri.apivendas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaRequest {

    private LocalDateTime data;
    private BigDecimal valor;
    private Integer idVendedor;
    private String vendedorNome;
}
