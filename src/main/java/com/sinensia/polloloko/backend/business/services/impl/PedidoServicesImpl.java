package com.sinensia.polloloko.backend.business.services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.polloloko.backend.business.model.Estado;
import com.sinensia.polloloko.backend.business.model.Pedido;
import com.sinensia.polloloko.backend.business.services.PedidoServices;
import com.sinensia.polloloko.backend.integration.repositories.PedidoRepository;

@Service
public class PedidoServicesImpl implements PedidoServices{

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override
	public Pedido read(Long codigo) {
		return pedidoRepository.findById(codigo).orElse(null);
	}

	@Override
	public List<Pedido> getAll() {
		return pedidoRepository.findAll();
	}

	@Override
	public List<Pedido> getByCodigoEmpleado(Long codigoEmpleado) {
		return pedidoRepository.findByEmpleadoCodigo(codigoEmpleado);
	}

	@Override
	public List<Pedido> getBetweenFechas(Date desde, Date hasta) {
		return pedidoRepository.findByFechaHoraBetween(desde, hasta);
	}

	@Override
	public Long create(Pedido pedido) {
		
		if(pedido.getCodigo() != null) {
			throw new IllegalStateException("El código de pedido deber ser null.");
		}
		
		return pedidoRepository.save(pedido).getCodigo();
	}

	@Override
	public List<Estado> getEstados() {
		return Arrays.asList(Estado.values());
	}

	@Override
	public void cancelar(Long codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void iniciarProceso(Long codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ofrecerParaEntrega(Long codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void entregar(Long codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Pedido> getUltimosNPedidosByEstado(Estado estado, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
