package com.iuri.apivendas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    private List<Venda> vendas;

   public Vendedor(Integer id){
       this.id = id;
   }

   public static Vendedor converterParaVendedor(VendedorRequest vendedorRequest){
       return Vendedor.builder()
           .nome(vendedorRequest.getNome())
           .build();
   }
}
