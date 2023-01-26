INSERT INTO EMPLEADOS(CODIGO, NOMBRE, APELLIDO1, APELLIDO2) VALUES
(100, 'Pepín', 'Gálvez', 'Ridruejo'),
(101, 'Honorio', 'Martín', 'Salvador'),
(102, 'Carlota', 'Cifuentes', 'Merino');

INSERT INTO PRODUCTOS(CODIGO, NOMBRE, CATEGORIA, DESCRIPCION, PRECIO, FECHA_ALTA, DESCATALOGADO) VALUES
(10, 'Café solo', 'CAFE', 'Delecioso café solo', 1.7, '2014-10-25', FALSE),
(11, 'Café cortado', 'CAFE', 'Delecioso café cortado', 2.0, '2014-10-25', FALSE),
(12, 'Pollo Coreano', 'COMIDA', 'Delecioso pollo', 14.5, '2014-10-25', FALSE);

INSERT INTO PEDIDOS(CODIGO, CODIGO_EMPLEADO, FECHA_HORA, ESTADO, OBSERVACIONES) VALUES
(1, 100, '2023-01-24', 'ENTREGADO', null),
(2, 102, '2023-01-25', 'EN_PROCESO', 'El cliente tiene prisa...'),
(3, 102, '2023-01-26', 'NUEVO', null);

INSERT INTO LINEAS_PEDIDO(CODIGO_PEDIDO, CODIGO_PRODUCTO, CANTIDAD, ORDEN) VALUES
(1, 10, 1, 0),
(2, 10, 4, 0),
(2, 11, 1, 1),
(2, 12, 2, 2),
(3, 10, 1, 0),
(3, 12, 25, 1);