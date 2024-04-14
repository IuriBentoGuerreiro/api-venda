package com.iuri.apivendas.service;

import com.iuri.apivendas.dto.VendaRequest;
import com.iuri.apivendas.dto.VendaResponse;
import com.iuri.apivendas.model.Venda;
import com.iuri.apivendas.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public VendaResponse salvarVenda(VendaRequest vendaRequest){
        return VendaResponse.converterParaResponse(vendaRepository.save(Venda.converterParaVenda(vendaRequest)));
    }

    public List<VendaResponse> lisrarVendas(){
        return vendaRepository.findAll().stream().map(VendaResponse::converterParaResponse).toList();
    }
}
