CREATE SEQUENCE "EMPLEADOS_SEQ" 
	MINVALUE 1000
	MAXVALUE 999999999 
	INCREMENT BY 50 
	START WITH 1000
	NOCACHE 
	NOCYCLE;
	
CREATE SEQUENCE "PRODUCTOS_SEQ" 
	MINVALUE 1000
	MAXVALUE 999999999 
	INCREMENT BY 50 
	START WITH 1000
	NOCACHE 
	NOCYCLE;
	
CREATE SEQUENCE "PEDIDOS_SEQ" 
	MINVALUE 1000
	MAXVALUE 999999999 
	INCREMENT BY 50 
	START WITH 1000
	NOCACHE 
	NOCYCLE;
	
CREATE TABLE EMPLEADOS(

	CODIGO					BIGINT			NOT NULL,
	NOMBRE					VARCHAR(100)	,
	APELLIDO1				VARCHAR(100)	,
	APELLIDO2				VARCHAR(100)	,
	
	PRIMARY KEY (CODIGO)

);

CREATE TABLE PRODUCTOS(

	CODIGO					BIGINT			NOT NULL,
	NOMBRE					VARCHAR(200)	,
	CATEGORIA				VARCHAR(100)	,
	DESCRIPCION				VARCHAR(250)	,
	PRECIO					DOUBLE			,
	FECHA_ALTA				DATE			,
	DESCATALOGADO			BOOLEAN			,
	
	PRIMARY KEY (CODIGO)
	
);

CREATE TABLE PEDIDOS(

	CODIGO					BIGINT			NOT NULL,
	CODIGO_EMPLEADO			BIGINT			,
	FECHA_HORA				TIMESTAMP		,
	ESTADO					VARCHAR(50)		,
	OBSERVACIONES			VARCHAR(250)	,

	PRIMARY KEY (CODIGO),
	FOREIGN KEY (CODIGO_EMPLEADO) REFERENCES EMPLEADOS (CODIGO)
);

CREATE TABLE LINEAS_PEDIDO(

	CODIGO_PEDIDO			BIGINT			,
	CODIGO_PRODUCTO			BIGINT			,
	CANTIDAD				INTEGER			,
	ORDEN					INTEGER			,
	
	FOREIGN KEY (CODIGO_PEDIDO) REFERENCES PEDIDOS (CODIGO),
	FOREIGN KEY (CODIGO_PRODUCTO) REFERENCES PRODUCTOS (CODIGO)

);


	



