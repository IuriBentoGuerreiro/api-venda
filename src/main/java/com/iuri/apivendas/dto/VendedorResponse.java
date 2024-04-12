package com.iuri.apivendas.dto;

import com.iuri.apivendas.model.Venda;
import com.iuri.apivendas.model.Vendedor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendedorResponse {

    private Integer id;
    private String nome;
    private List<Venda> venda;

    public static VendedorResponse converterParaResponse(Vendedor vendedor){
        return VendedorResponse.builder()
            .id(vendedor.getId())
            .nome(vendedor.getNome())
            .venda(vendedor.getVendas())
            .build();
    }
}
