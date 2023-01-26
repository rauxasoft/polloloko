package com.sinensia.polloloko.backend.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinensia.polloloko.backend.business.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
