package com.sinensia.polloloko.backend.business.services;

import java.util.Date;
import java.util.List;

import com.sinensia.polloloko.backend.business.model.Estado;
import com.sinensia.polloloko.backend.business.model.Pedido;

public interface PedidoServices {

	Pedido read(Long codigo);
	List<Pedido> getAll();
	List<Pedido> getByCodigoEmpleado(Long codigoEmpleado);
	List<Pedido> getBetweenFechas(Date desde, Date hasta);
	
	Long create(Pedido pedido);
	
	List<Estado> getEstados();
	
	/**
	 * Métodos que cambian el estado de un pedido siempre y cuando la transición entre estados sea posible.
	 * 
	 * Si la transición no es posible, se lanza la excepción IllegalStateException con un mensaje del tipo:
	 * 
	 * "No se puede pasar de un estado [ESTADO_ACTUAL] a [NUEVO_ESTADO"
	 * 
	 */
	
	void cancelar(Long codigo);			   // Solo posible desde NUEVO, EN_PROCESO
	void iniciarProceso(Long codigo);      // Solo posible desde NUEVO
	void ofrecerParaEntrega(Long codigo);  // Solo posible desde EN_PROCESO
	void entregar(Long codigo);			   // Solo posible desde PENDIENTE_ENTREGA
	
	List<Pedido> getUltimosNPedidosByEstado(Estado estado, int limit); 
}
