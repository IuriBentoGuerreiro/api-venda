package com.iuri.apivendas.service;

import com.iuri.apivendas.criterian.VendedorSpecification;
import com.iuri.apivendas.dto.VendedorFilter;
import com.iuri.apivendas.dto.VendedorRequest;
import com.iuri.apivendas.dto.VendedorResponse;
import com.iuri.apivendas.model.Venda;
import com.iuri.apivendas.model.Vendedor;
import com.iuri.apivendas.repository.VendedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class VendedorServiceTest {

    @Mock
    private VendedorRepository vendedorRepository;

    @InjectMocks
    private VendedorService vendedorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void salvarVendedor() {
        VendedorRequest vendedorRequest = new VendedorRequest();
        vendedorRequest.setNome("João");

        Vendedor vendedorSimulado = new Vendedor();
        vendedorSimulado.setId(1);
        vendedorSimulado.setNome("João");

        when(vendedorRepository.save(any(Vendedor.class))).thenReturn(vendedorSimulado);

        VendedorResponse vendedorResponse = vendedorService.salvarVendedor(vendedorRequest);

        assertNotNull(vendedorResponse);
        assertEquals(vendedorSimulado.getId(), vendedorResponse.getId());
        assertEquals(vendedorSimulado.getNome(), vendedorResponse.getNome());
    }

    @Test
    void testListarVendedorEstatisticas() {
        VendedorFilter vendedorFilter = new VendedorFilter();
        vendedorFilter.setDataInicio(LocalDate.of(2024, 1, 1));
        vendedorFilter.setDataFim(LocalDate.of(2024, 1, 31));

        List<Vendedor> vendedoresSimulados = new ArrayList<>();
        vendedoresSimulados.add(criarVendedorComVendas("Vendedor 1", 5));
        vendedoresSimulados.add(criarVendedorComVendas("Vendedor 2", 10));
        vendedoresSimulados.add(criarVendedorComVendas("Vendedor 3", 15));

        when(vendedorRepository.findAll(any(VendedorSpecification.class))).thenReturn(vendedoresSimulados);

        List<VendedorResponse> vendedoresResponse = vendedorService.listarVendedorEstatisticas(vendedorFilter);

        assertEquals(vendedoresSimulados.size(), vendedoresResponse.size());

        assertEquals(5, vendedoresResponse.get(0).getTotalVendas());
        assertEquals(10, vendedoresResponse.get(1).getTotalVendas());
        assertEquals(15, vendedoresResponse.get(2).getTotalVendas());
    }

    private Vendedor criarVendedorComVendas(String nome, int qtdVendas) {
        Vendedor vendedor = new Vendedor();
        vendedor.setNome(nome);
        List<Venda> vendas = new ArrayList<>();
        for (int i = 0; i < qtdVendas; i++) {
            Venda venda = new Venda();
            vendas.add(venda);
        }
        vendedor.setVendas(vendas);
        return vendedor;
    }
}