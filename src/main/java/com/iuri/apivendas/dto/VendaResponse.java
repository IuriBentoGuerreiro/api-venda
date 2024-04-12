package com.iuri.apivendas.dto;

import com.iuri.apivendas.model.Vendedor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaResponse {

    private Integer id;
    private LocalDateTime data;
    private BigDecimal valor;
    private Vendedor vendedor;
    private String vendedorNome;
}
