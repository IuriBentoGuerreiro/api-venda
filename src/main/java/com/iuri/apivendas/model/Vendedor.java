package com.iuri.apivendas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vendedor")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @OneToMany
    @JoinColumn(name = "venda")
    private Venda venda;
}
