package com.iuri.apivendas.controller;

import com.iuri.apivendas.dto.VendedorFilter;
import com.iuri.apivendas.dto.VendedorRequest;
import com.iuri.apivendas.dto.VendedorResponse;
import com.iuri.apivendas.service.VendedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Vendedor")
@RestController
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @Operation(summary = "Salvar vendedor")
    @PostMapping
    public VendedorResponse salvarVendedor(@RequestBody VendedorRequest vendedorRequest){
        return vendedorService.salvarVendedor(vendedorRequest);
    }

    @Operation(summary = "Listar vendedores")
    @GetMapping
    public List<VendedorResponse>listarVendedores(VendedorFilter vendedorFilter){
        return vendedorService.listarVendedores(vendedorFilter);
    }
}
