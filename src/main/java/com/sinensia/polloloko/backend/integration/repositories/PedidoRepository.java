package com.sinensia.polloloko.backend.integration.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sinensia.polloloko.backend.business.model.Estado;
import com.sinensia.polloloko.backend.business.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	List<Pedido> findByEmpleadoCodigo(Long codigo);
	
	List<Pedido> findByFechaHoraBetween(Date desde, Date hasta);
	
	@Query("SELECT p FROM Pedido p WHERE p.estado = :estado ORDER BY p.codigo")
    public Page<Pedido> findPedidoByEstadoTopN(Estado estado, Pageable pageable);
}
