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
		<h2>Ficha de Producto 
			<c:if test="${producto.descatalogado}">
				<span style="color:red;">(descatalogado)</span>
			</c:if>
		</h2>
		<table class="ficha">	
			<tr>
				<th>Código</th>
				<td>${producto.codigo}</td>
			</tr>
			<tr>
				<th>Nombre</th>
				<td>${producto.nombre}</td>
			</tr>
			<tr>
				<th>Precio</th>
				<td>${producto.precio}</td>
			</tr>
			<tr>
				<th>Fecha de Alta</th>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${producto.fechaAlta}" /></td>
			</tr>
			<tr>
				<th>Categoría</th>
				<td>${producto.categoria}</td>
			</tr>
			<tr>
				<th>Descripción</th>
				<td>${producto.descripcion}</td>
			</tr>
		</table>
	</body>
</html>