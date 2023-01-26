package com.sinensia.polloloko.backend.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinensia.polloloko.backend.business.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
