package com.iuri.apivendas.controller;

import com.iuri.apivendas.dto.VendaRequest;
import com.iuri.apivendas.dto.VendaResponse;
import com.iuri.apivendas.service.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Venda")
@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Operation(summary = "Salvar venda")
    @PostMapping
    public VendaResponse salvarVenda(@RequestBody VendaRequest vendaRequest){
        return vendaService.salvarVenda(vendaRequest);
    }

    @Operation(summary = "Listar vendas")
    @GetMapping
    public List<VendaResponse>listarVendas(){
        return vendaService.lisrarVendas();
    }
}
