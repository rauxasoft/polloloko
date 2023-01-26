package com.sinensia.polloloko.backend.business.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinensia.polloloko.backend.business.model.Estado;
import com.sinensia.polloloko.backend.business.model.Pedido;
import com.sinensia.polloloko.backend.business.services.PedidoServices;

@Service
public class PedidoServicesImpl implements PedidoServices{

	@Override
	public Pedido read(Long codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> getByCodigoEmpleado(Long codigoEmpleado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> getBetweenFechas(Date desde, Date hasta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long create(Pedido pedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estado> getEstados() {
		// TODO Auto-generated method stub
		return null;
	}

}
