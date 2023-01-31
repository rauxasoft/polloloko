<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="common-in-heads.jsp"/>
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<h2>Listado de Productos</h2>
		<table id="idTablaProductos" class="full-table tabla-datos">
			<thead>
				<tr>
					<th class="to-right">Código</th>
					<th class="center">Nombre</th>
					<th>Precio</th>
					<th class="center">Fecha de Alta</th>
					<th class="center">Categoria</th>
					<th class="center">Descatalogado</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="producto" items="${productos}">
					<tr>
						<td class="to-right">${producto.codigo}</td>
						<td>${producto.nombre}</td>
						<td class="to-right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${producto.precio}"/> €</td>
						<td class="center"><fmt:formatDate pattern="dd/MM/yyyy" value="${producto.fechaAlta}" /></td>
						<td class="center">${producto.categoria}</td>
						<td class="center">
							<c:if test="${producto.descatalogado}">
								<span style="color:green;">DESCATALOGADO</span>
							</c:if>
						</td>
						<td><a href="ficha-producto?codigo=${producto.codigo}">ver ficha</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</body>
</html>