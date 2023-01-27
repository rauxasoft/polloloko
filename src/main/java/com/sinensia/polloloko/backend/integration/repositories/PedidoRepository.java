package com.sinensia.polloloko.backend.integration.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinensia.polloloko.backend.business.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	List<Pedido> findByEmpleadoCodigo(Long codigo);
	
	List<Pedido> findByFechaHoraBetween(Date desde, Date hasta);
}
