package com.iuri.apivendas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendaLogicaResponse {

    private Integer totalVendas;
    private Float mediaVendas;
}
