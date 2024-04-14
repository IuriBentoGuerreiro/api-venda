package com.iuri.apivendas.dto;

import com.iuri.apivendas.model.Venda;
import com.iuri.apivendas.model.Vendedor;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VendaResponseTest {

        @Test
        void testConverterParaResponse() {
            Venda venda = mock(Venda.class);

            when(venda.getId()).thenReturn(1);
            when(venda.getData()).thenReturn(LocalDateTime.of(2024, 4, 15, 12, 30));
            when(venda.getValor()).thenReturn(BigDecimal.valueOf(100.0));
            Vendedor vendedor = mock(Vendedor.class); // Mock de um Vendedor
            when(venda.getVendedor()).thenReturn(vendedor);

            VendaResponse vendaResponse = VendaResponse.converterParaResponse(venda);

            assertEquals(1, vendaResponse.getId());
            assertEquals(LocalDateTime.of(2024, 4, 15, 12, 30), vendaResponse.getData());
            assertEquals(BigDecimal.valueOf(100.0), vendaResponse.getValor());
            assertEquals(vendedor, vendaResponse.getVendedor());
        }
}