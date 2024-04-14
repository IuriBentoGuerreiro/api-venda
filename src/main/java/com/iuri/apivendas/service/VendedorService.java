package com.iuri.apivendas.service;

import com.iuri.apivendas.criterian.VendedorSpecification;
import com.iuri.apivendas.dto.EstatisticaRecord;
import com.iuri.apivendas.dto.VendedorFilter;
import com.iuri.apivendas.dto.VendedorRequest;
import com.iuri.apivendas.dto.VendedorResponse;
import com.iuri.apivendas.model.Vendedor;
import com.iuri.apivendas.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    public VendedorResponse salvarVendedor(VendedorRequest vendedorRequest){
        return VendedorResponse.converterParaResponse(vendedorRepository
                .save(Vendedor.converterParaVendedor(vendedorRequest)));
    }

    public List<VendedorResponse> listarVendedorEstatisticas(VendedorFilter vendedorFilter){
        List<Vendedor> vendedores = vendedorRepository.findAll(VendedorSpecification.of(vendedorFilter));

        Integer totalDias = Math.toIntExact(ChronoUnit.DAYS.between(vendedorFilter.getDataInicio(),
                vendedorFilter.getDataFim()) + 1);

        List<VendedorResponse> vendedorResponse = new ArrayList<>();

        vendedores.forEach(
                vendedor -> {
                    var calcularEstatisticas = calcularEstatisticas(vendedor.getVendas().size(), totalDias);

                    vendedorResponse.add(
                            VendedorResponse.builder()
                                    .id(vendedor.getId())
                                    .nome(vendedor.getNome())
                                    .venda(vendedor.getVendas())
                                    .mediaVendas(calcularEstatisticas.media())
                                    .totalVendas(calcularEstatisticas.qtdVendas())
                                    .build());
                }
        );

        return vendedorResponse;
    }

    private EstatisticaRecord calcularEstatisticas(Integer qtdVendas, Integer qtdDias) {
        Integer qtd = qtdVendas;
        Double media = (double) (qtd/qtdDias);
        return new EstatisticaRecord(qtd, media);
    }
}
