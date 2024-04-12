package com.iuri.apivendas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendedorFilter {

    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
}
