package com.iuri.apivendas.repository;

import com.iuri.apivendas.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer>, JpaSpecificationExecutor<Vendedor> { }
