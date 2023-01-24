package com.sinensia.polloloko.backend.services;

import java.util.Date;
import java.util.List;

import com.sinensia.polloloko.backend.model.Pedido;

public interface PedidoServices {

	Pedido read(Long codigo);
	List<Pedido> getAll();
	List<Pedido> getByCodigoEmpleado(Long codigoEmpleado);
	List<Pedido> getBetweenFechas(Date desde, Date hasta);
	
	Long create(Pedido pedido);
	
}
