package com.iuri.apivendas.criterian;

import com.iuri.apivendas.dto.VendedorFilter;
import com.iuri.apivendas.model.Venda;
import com.iuri.apivendas.model.Vendedor;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class VendedorSpecification implements Specification<Vendedor>{

    private final VendedorFilter filter;

    public static VendedorSpecification of(VendedorFilter filter){
        if (filter == null){
            return null;
        }
        return new VendedorSpecification(filter);
    }

    @Override
    public Predicate toPredicate (Root<Vendedor> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
        List<Predicate> predicates = new ArrayList<>();

        if(filter.getNome() != null) {
            predicates.add(criteriaBuilder.like(root.get("nome"), "%" + filter.getNome() + "%"));
        }
        if (filter.getDataInicio() != null && filter.getDataFim() != null) {
            Join<Vendedor, Venda> vendaJoin = root.join("vendas");
            predicates.add(criteriaBuilder.between(vendaJoin.get("data"), filter.getDataInicio().atStartOfDay(),
                filter.getDataFim().atStartOfDay().plusHours(24)));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
