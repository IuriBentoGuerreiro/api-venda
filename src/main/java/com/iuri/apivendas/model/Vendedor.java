package com.iuri.apivendas.model;

import com.iuri.apivendas.dto.VendedorRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "vendedor")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @OneToMany
    @JoinColumn(name = "venda")
    private List<Venda> venda;

   public Vendedor(Integer id){
       this.id = id;
   }

   public static Vendedor converterParaVendedor(VendedorRequest vendedorRequest){
       return Vendedor.builder()
           .nome(vendedorRequest.getNome())
           .venda(vendedorRequest.getVenda())
           .build();
   }
}
