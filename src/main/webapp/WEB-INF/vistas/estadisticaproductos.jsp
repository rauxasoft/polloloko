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
		<h2>Estadística de Productos</h2>
		<h3>Número total de productos</h3>
		<p>En el sistema están dados de alta ${numeroProductos} productos.</p>
		<h3>Número total de productos por familia</h3>
		<table>
			<thead>
				<tr>
					<th class="center">Familia</th>
					<th>Número de Productos</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entry" items="${estadistica1}">
					<tr>
						<td>${entry.key}</td>
						<td style="text-align: right;">${entry.value}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<h3>Precio medio de productos por familia</h3>
				<table>
			<thead>
				<tr>
					<th class="center">Familia</th>
					<th>Precio Medio</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entry" items="${estadistica2}">
					<tr>
						<td>${entry.key}</td>
						<td style="text-align: right;"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${entry.value}"/> €</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>