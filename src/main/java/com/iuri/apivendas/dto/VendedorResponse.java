package com.iuri.apivendas.dto;

import com.iuri.apivendas.model.Venda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendedorResponse {

    private Integer id;
    private String nome;
    private List<Venda> venda;
}
