package com.iuri.apivendas.dto;

import com.iuri.apivendas.model.Venda;
import com.iuri.apivendas.model.Vendedor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VendedorResponseTest {

        @Test
        void testConverterParaResponse() {
            Vendedor vendedor = mock(Vendedor.class);

            when(vendedor.getId()).thenReturn(1);
            when(vendedor.getNome()).thenReturn("Vendedor Teste");

            List<Venda> vendas = new ArrayList<>();
            Venda venda1 = new Venda();
            Venda venda2 = new Venda();
            vendas.add(venda1);
            vendas.add(venda2);
            when(vendedor.getVendas()).thenReturn(vendas);

            VendedorResponse vendedorResponse = VendedorResponse.converterParaResponse(vendedor);

            assertEquals(1, vendedorResponse.getId());
            assertEquals("Vendedor Teste", vendedorResponse.getNome());
            assertEquals(vendas, vendedorResponse.getVenda());
        }
}