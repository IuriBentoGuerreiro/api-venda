package com.iuri.apivendas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iuri.apivendas.dto.VendedorFilter;
import com.iuri.apivendas.dto.VendedorRequest;
import com.iuri.apivendas.dto.VendedorResponse;
import com.iuri.apivendas.service.VendedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(VendedorController.class)
class VendedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendedorService vendedorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSalvarVendedor() throws Exception {
        VendedorRequest vendedorRequest = new VendedorRequest("Nome do Vendedor");

        VendedorResponse vendedorResponse = new VendedorResponse();
        vendedorResponse.setId(1);
        vendedorResponse.setNome("Nome do Vendedor");

        when(vendedorService.salvarVendedor(vendedorRequest)).thenReturn(vendedorResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/vendedor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vendedorRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Nome do Vendedor"));
    }

    @Test
    void testListarVendedorEstatisticas() throws Exception {
        VendedorFilter vendedorFilter = new VendedorFilter();
        vendedorFilter.setNome("Nome do Vendedor");
        vendedorFilter.setDataInicio(LocalDate.of(2024, 1, 1));
        vendedorFilter.setDataFim(LocalDate.of(2024, 1, 31));

        List<VendedorResponse> vendedorResponses = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            VendedorResponse vendedorResponse = new VendedorResponse();
            vendedorResponse.setId(i);
            vendedorResponse.setNome("Vendedor " + i);
            vendedorResponses.add(vendedorResponse);
        }

        when(vendedorService.listarVendedorEstatisticas(vendedorFilter)).thenReturn(vendedorResponses);

        mockMvc.perform(MockMvcRequestBuilders.get("/vendedor/estatistica")
                        .param("nome", "Nome do Vendedor")
                        .param("dataInicio", "2024-01-01")
                        .param("dataFim", "2024-01-31")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                // Aqui você pode adicionar mais asserções conforme necessário
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Vendedor 1"));
    }
}