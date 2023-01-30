package com.sinensia.polloloko.backend.business.services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	@Transactional
	public Long create(Pedido pedido) {
			
		if(pedido.getCodigo() != null) {
			throw new IllegalStateException("El código de pedido deber ser null.");
		}
	
		pedido.setEstado(Estado.NUEVO);
		
		return pedidoRepository.save(pedido).getCodigo();
	}

	@Override
	public List<Estado> getEstados() {
		return Arrays.asList(Estado.values());
	}

	@Override
	@Transactional
	public void cancelar(Long codigo) {
		
		Pedido pedido = recuperarPedido(codigo);
		
		if(!(pedido.getEstado() == Estado.NUEVO || pedido.getEstado() == Estado.EN_PROCESO)) {
			throw new IllegalStateException("No se puede pasar de estado [" + pedido.getEstado() + "] a  [CANCELADO]");
		}
		
		pedido.setEstado(Estado.CANCELADO);
		
	}

	@Override
	@Transactional
	public void iniciarProceso(Long codigo) {
		
		Pedido pedido = recuperarPedido(codigo);
		
		if(pedido.getEstado() != Estado.NUEVO) {
			throw new IllegalStateException("No se puede pasar de estado [" + pedido.getEstado() + "] a  [EN_PROCESO]");
		}
		
		pedido.setEstado(Estado.EN_PROCESO);
		
	}

	@Override
	@Transactional
	public void ofrecerParaEntrega(Long codigo) {
		
		Pedido pedido = recuperarPedido(codigo);
		
		if(pedido.getEstado() != Estado.EN_PROCESO) {
			throw new IllegalStateException("No se puede pasar de estado [" + pedido.getEstado() + "] a  [PENDIENTE_ENTREGA]");
		}
		
		pedido.setEstado(Estado.PENDIENTE_ENTREGA);
		
	}

	@Override
	@Transactional
	public void entregar(Long codigo) {
		
		Pedido pedido = recuperarPedido(codigo);
		
		if(pedido.getEstado() != Estado.PENDIENTE_ENTREGA) {
			throw new IllegalStateException("No se puede pasar de estado [" + pedido.getEstado() + "] a  [ENTREGADO]");
		}
		
		pedido.setEstado(Estado.ENTREGADO);
		
	}

	@Override
	public List<Pedido> getUltimosNPedidosByEstado(Estado estado, int limit) {
		Page<Pedido> page = pedidoRepository.findPedidoByEstadoTopN(estado, PageRequest.of(0, limit));
		return page.getContent();
	}
	
	// *****************************************************************************
	//
	// PRIVATE METHODS
	//
	// *****************************************************************************

	private Pedido recuperarPedido(Long codigo) {
		
		boolean exsite = pedidoRepository.existsById(codigo);
		
		if(!exsite) {
			throw new IllegalArgumentException("No existe el pedido [" + codigo + "]");
		}
		
		return pedidoRepository.findById(codigo).orElse(null);
	}
	
}
