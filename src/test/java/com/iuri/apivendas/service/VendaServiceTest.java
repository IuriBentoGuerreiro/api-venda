package com.iuri.apivendas.service;

import com.iuri.apivendas.dto.VendaRequest;
import com.iuri.apivendas.dto.VendaResponse;
import com.iuri.apivendas.model.Venda;
import com.iuri.apivendas.repository.VendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class VendaServiceTest {

    @Mock
    private VendaRepository vendaRepository;

    @InjectMocks
    private VendaService vendaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void salvarVenda() {
        VendaRequest vendaRequest = new VendaRequest();
        vendaRequest.setIdVendedor(1);
        vendaRequest.setData(LocalDateTime.now());
        vendaRequest.setValor(BigDecimal.valueOf(100.0));

        Venda vendaSimulada = new Venda();
        vendaSimulada.setId(1);
        vendaSimulada.setData(LocalDateTime.now());
        vendaSimulada.setValor(BigDecimal.valueOf(100.0));


        when(vendaRepository.save(Mockito.any(Venda.class))).thenReturn(vendaSimulada);

        VendaResponse vendaResponse = vendaService.salvarVenda(vendaRequest);

        assertNotNull(vendaResponse);
        assertEquals(vendaSimulada.getId(), vendaResponse.getId());
        assertEquals(vendaSimulada.getData(), vendaResponse.getData());
        assertEquals(vendaSimulada.getValor(), vendaResponse.getValor());
    }

    @Test
    void listarVendas() {
        List<Venda> vendasSimuladas = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Venda venda = new Venda();
            venda.setId(i);
            venda.setData(LocalDateTime.now());
            venda.setValor(BigDecimal.valueOf(100.0 * i));
            vendasSimuladas.add(venda);
        }

        when(vendaRepository.findAll()).thenReturn(vendasSimuladas);

        List<VendaResponse> vendasResponse = vendaService.lisrarVendas();

        assertEquals(vendasSimuladas.size(), vendasResponse.size());
        for (int i = 0; i < vendasSimuladas.size(); i++) {
            Venda vendaSimulada = vendasSimuladas.get(i);
            VendaResponse vendaResponse = vendasResponse.get(i);
            assertEquals(vendaSimulada.getId(), vendaResponse.getId());
            assertEquals(vendaSimulada.getData(), vendaResponse.getData());
            assertEquals(vendaSimulada.getValor(), vendaResponse.getValor());
        }
    }
}