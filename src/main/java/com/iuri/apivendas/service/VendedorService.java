package com.iuri.apivendas.service;

import com.iuri.apivendas.criterian.VendedorSpecification;
import com.iuri.apivendas.dto.VendedorFilter;
import com.iuri.apivendas.dto.VendedorRequest;
import com.iuri.apivendas.dto.VendedorResponse;
import com.iuri.apivendas.model.Vendedor;
import com.iuri.apivendas.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    public VendedorResponse salvarVendedor(VendedorRequest vendedorRequest){
        return VendedorResponse.converterParaResponse(vendedorRepository
            .save(Vendedor.converterParaVendedor(vendedorRequest)));
    }

    public List<VendedorResponse> listarVendedores(VendedorFilter vendedorFilter){
        return vendedorRepository.findAll(VendedorSpecification.of(vendedorFilter))
            .stream().map(VendedorResponse::converterParaResponse).toList();
    }
}
