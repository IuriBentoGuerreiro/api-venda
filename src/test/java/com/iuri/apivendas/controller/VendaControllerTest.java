package com.iuri.apivendas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iuri.apivendas.dto.VendaRequest;
import com.iuri.apivendas.dto.VendaResponse;
import com.iuri.apivendas.service.VendaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(VendaController.class)
class VendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendaService vendaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSalvarVenda() throws Exception {
        VendaRequest vendaRequest = new VendaRequest();
        vendaRequest.setData(LocalDateTime.now());
        vendaRequest.setValor(BigDecimal.valueOf(100.0));

        VendaResponse vendaResponse = new VendaResponse();
        vendaResponse.setId(1);
        vendaResponse.setData(LocalDateTime.now());
        vendaResponse.setValor(BigDecimal.valueOf(100.0));

        when(vendaService.salvarVenda(vendaRequest))
                .thenReturn(vendaResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/venda")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vendaRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(vendaResponse.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(vendaResponse.getData().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.valor").value(vendaResponse.getValor()));
    }

    @Test
    void testListarVendas() throws Exception {
        List<VendaResponse> vendas = new ArrayList<>();
        VendaResponse venda1 = new VendaResponse();
        venda1.setData(LocalDateTime.of(2024, 4, 14, 12, 16, 26, 903041190));
        VendaResponse venda2 = new VendaResponse();
        vendas.add(venda1);
        vendas.add(venda2);

        when(vendaService.lisrarVendas()).thenReturn(vendas);

        mockMvc.perform(MockMvcRequestBuilders.get("/venda")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(vendas), true)); // Ignora a precisão dos milissegundos
    }
}