<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="common-in-heads.jsp"/>
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<h2>Alta de Producto</h2> 
		<form:form id="idFormularioAltaProducto" method="POST" action="create-producto" modelAttribute="producto">
			<table>
				<tr>
					<td><form:label path="nombre">Nombre</form:label></td>
                    <td><form:input type="text" path="nombre" required="required"/></td>
                    <td style="color:red;"></td>
				</tr>
				<tr>
					<td><form:label path="precio">Precio</form:label></td>
                    <td><form:input type="number"  path="precio" required="required"/></td>
                    <td style="color:red;"></td>
				</tr>
				<tr>
					<td><form:label path="categoria">Categoría</form:label></td>
					<td>
						<form:select path="categoria">
							<form:option value="BEBIDA">Bebida</form:option>
							<form:option value="CAFE">Café</form:option>
							<form:option value="CERVEZA">Cerveza</form:option>
							<form:option value="COMIDA">Comida</form:option>
							<form:option value="LICOR">Licor</form:option>
							<form:option value="POSTRE">Postre</form:option>
							<form:option value="TAPA">Tapa</form:option>
						</form:select>
					</td>
					<td></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit"/></td>
				</tr>	
			</table>
		</form:form>
	</body>
</html>