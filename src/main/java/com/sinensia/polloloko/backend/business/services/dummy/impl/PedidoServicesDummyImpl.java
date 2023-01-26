package com.sinensia.polloloko.backend.business.services.dummy.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sinensia.polloloko.backend.business.model.Empleado;
import com.sinensia.polloloko.backend.business.model.Estado;
import com.sinensia.polloloko.backend.business.model.Pedido;
import com.sinensia.polloloko.backend.business.model.Producto;
import com.sinensia.polloloko.backend.business.services.PedidoServices;
import com.sinensia.polloloko.backend.integration.repositories.dummy.DummyDB;

@Service
@Primary
public class PedidoServicesDummyImpl implements PedidoServices{

	private final DummyDB dummyDB;
	
	public PedidoServicesDummyImpl() {
		this.dummyDB = DummyDB.getInstance();
	}
	
	@Override
	public Pedido read(Long codigo) {
		return dummyDB.getPedidosDB().get(codigo);
	}

	@Override
	public List<Pedido> getAll() {
		return new ArrayList<>(dummyDB.getPedidosDB().values());
	}

	@Override
	public List<Pedido> getByCodigoEmpleado(Long codigoEmpleado) {
		
		return dummyDB.getPedidosDB().values().stream()
				.filter(x -> (x.getEmpleado() != null) && (x.getEmpleado().getCodigo()) == codigoEmpleado)
				.collect(Collectors.toList());
	}

	@Override
	public List<Pedido> getBetweenFechas(Date desde, Date hasta) {
		
		return dummyDB.getPedidosDB().values().stream()
				.filter(x -> x.getFechaHora().after(desde) && x.getFechaHora().before(hasta))
				.collect(Collectors.toList());
	}

	@Override
	public Long create(Pedido pedido) {
		
		if(pedido.getCodigo() != null) {
			throw new IllegalStateException("No se puede crear un pedido que ya tiene código [" +  pedido.getCodigo() + "]");
		}
		
		Long ultimoCodigo = ((TreeMap<Long, Pedido>) dummyDB.getPedidosDB()).lastKey();
		
		Long nuevoCodigo = ultimoCodigo + 1;
		
		pedido.setCodigo(nuevoCodigo);
		
		Empleado empleado = dummyDB.getEmpleadosDB().get(pedido.getEmpleado().getCodigo());
		
		pedido.setEmpleado(empleado);
		
		for(int i = 0; i <  pedido.getLineas().size(); i++ ) {
			Producto producto = dummyDB.getProductosDB().get(pedido.getLineas().get(i).getProducto().getCodigo());
			pedido.getLineas().get(i).setProducto(producto);
		}
		
		dummyDB.getPedidosDB().put(pedido.getCodigo(), pedido);
		
		return nuevoCodigo;
	}

	@Override
	public List<Estado> getEstados() {
		return Arrays.asList(Estado.values());
	}	
}
