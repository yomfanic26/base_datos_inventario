-----TABLA CATEGORIA-----
drop table if exists categorias CASCADE;
Create table categorias (

	codigo_cat serial not null,
	nombre varchar (100) not null,
	catagoria_padre int ,
	constraint categorias_pk primary key (codigo_cat),
	constraint categorias_fk foreign key (codigo_cat)
	references categorias (codigo_cat)
);
--insertar
INSERT INTO categorias (nombre, catagoria_padre)
VALUES 
  ('Materia_Prima', null),
  ('Proteina', 1),
  ('Salsas', 1),
  ('Punto de venta', null),
  ('Bebidas', 4),
  ('Con alcohol', 5),
  ('Sin alcohol', 5);
  select * from categorias;
  
  
-----TABLA CATEGORIA UNIDADES DE MEDIDAS-----
drop table if exists categoria_udm CASCADE;
Create table categoria_udm (

	codigo_cat_udm char (1) not null,
	nombre varchar (100) not null,
	constraint categoria_udm_pk primary key (codigo_cat_udm)
);
--insertar
INSERT INTO categoria_udm (codigo_cat_udm, nombre)
VALUES 
  ('U', 'Unidades'),
  ('V', 'Volumen'),
  ('P','Peso');
  select * from categoria_udm;
  
  
-----TABLA UNIDADES DE MEDIDAS-----
drop table if exists unidades_medidas CASCADE;
Create table unidades_medidas (

	codigo_udm serial not null,
	nombre varchar (2) not null,
	descripcion varchar (100) not null,
	codigo_ctr_udm char (1) not null,
	constraint unidades_medidas_pk primary key (codigo_udm),
	constraint unidades_medidas_categoria_udm_fk foreign key (codigo_ctr_udm)
	references categoria_udm (codigo_cat_udm)

);
--insertar
INSERT INTO unidades_medidas (nombre, descripcion,codigo_ctr_udm)
VALUES 
  ('ml', 'mili litros','V'),
  ('l', 'litros','V'),
  ('u','unidad','U'),
  ('d','docena','U'),
  ('g','gramos','P'),
  ('kg','kilogramos','P'),
  ('lb','libras','P');
  select * from unidades_medidas;
  
  
  -----TABLA PRODUCTOS-----
drop table  if exists productos CASCADE;
create table productos (
	codigo_pro int not null,
	nombre varchar (100) not null,
	unidad_medida int not null,
	precio money not null,
	tiene_iva boolean not null,
	coste money not null,
	categoria int not null,
	stock int not null,
	constraint producto_pk primary key (codigo_pro),
	constraint productos_unidad_medida_fk foreign key (unidad_medida)
	references unidades_medidas(codigo_udm),
	constraint productos_categoria_fk foreign key (categoria)
	references categorias(codigo_cat)
	);
--insertar--
INSERT INTO productos (codigo_pro,nombre,unidad_medida,precio,tiene_iva,coste,categoria,stock)
VALUES 
  ('1', 'Coca cola pequeña',3,0.5804,true,0.3729,7,105),
  ('2', 'Salsa de tomate',6,0.95,true,0.8736,3,0),
  ('3','Mostaza',6,0.95,true,0.89,3,0),
  ('4','Fuze tea','3',0.8,true,0.7,7,49);
  select * from productos;

-----TABLA HISTORIAL STOCK-----
drop table if exists histotial_stock cascade;
create table histotial_stock (
	codigo serial not null,
	fecha timestamp without time zone not null,
	referencia varchar (15) not null,
	producto int not null,
	cantidad int not null,
	constraint historial_stock_pk primary key (codigo),
	constraint historial_stock_pro_fk foreign key (producto)
	references productos (codigo_pro)
);
--insertar--
INSERT INTO histotial_stock (fecha,referencia,producto,cantidad)
VALUES 
  ('2023-11-20 19:59', 'Pedido 1', 1, 100),
  ('2023-11-20 19:59', 'Pedido 1', 4, 50),
  ('2023-11-20 20:00', 'Pedido 2', 1, 10),
  ('2023-11-20 20:00', 'Venta 1', 1, -5),
  ('2023-11-20 20:00', 'Venta 1', 4, 1);
  select * from histotial_stock;
 
 
------TABLA CABECERA VENTA-----
drop table if exists cabecera_venta cascade;
create table cabecera_venta (
	codigo serial not null,
	fecha timestamp without time zone not null,
	total_iva money ,
	iva money,
	total money not null,
	constraint cabecera_venta_pk primary key (codigo)
);
--insertar--
INSERT INTO cabecera_venta (fecha,total_iva,iva,total)
VALUES 
  ('2023-11-20 20:00',3.26, 0.39, 3.65);
  select * from cabecera_venta;
  
  
  
  ------TABLA DETALL VENTA-----
drop table if exists detalle_venta cascade;
create table detalle_venta (
	codigo serial not null,
	cabecera_venta int not null,
	producto int not null,
	cantidad int not null,
	precio_venta money not null,
	subtotal money not null,
	subtotal_iva money not null,
	constraint detalle_venta_pk primary key (codigo),
	constraint detalle_cabecera_venta_fk foreign key (cabecera_venta)
	references cabecera_venta (codigo),
	constraint detalle_venta_producto_fk foreign key (producto)
	references productos (codigo_pro)
);
--insertar--
INSERT INTO detalle_venta (cabecera_venta,producto,cantidad,precio_venta,subtotal,subtotal_iva)
VALUES 
  (1,1,5,0.85,2.9,3.25),
  (1,4,1,0.36,0.36,0.4);
  
  select * from detalle_venta;
  
  
  -----TIPOS DOCUMENTOS-----
drop table if exists tipos_documento CASCADE;
Create table tipos_documento (

	codigo char (1) not null,
	descripcion varchar (15) not null,
	constraint tipos_documento_pk primary key (codigo)
);
 --insertar--
INSERT INTO tipos_documento (codigo,descripcion)
VALUES 
  ('C','Ceduela'),
  ('R','Ruc');
  
  select * from tipos_documento;
  
------TABLA PROVEEDORES-----
drop table if exists proveedores CASCADE;
Create table proveedores (

	codigo_prov varchar (13) not null,
	tipo_documento char (1) not null,
	nombre varchar(100) not null,
	telefono CHAR (10) not null,
	correo varchar (100),
	direccion varchar (100) not null,
	
	constraint proveedores_pk primary key (codigo_prov),
	constraint proveedores_documento_fk foreign key (tipo_documento)
	references tipos_documento(codigo)
);
 --insertar--
INSERT INTO proveedores (codigo_prov,tipo_documento,nombre,telefono,correo,direccion)
VALUES 
  ('1792285747','C','SANTIAGO MOSQUERA','0967565511','yofanic@gmail.com','Cumbayork'),
  ('1792285747001','R','SNACK SA','0969293599','snack@gmail.com','La tola');
  
  select * from proveedores;
  
  ------TABLA ESTADO PEDIDO-----
drop table if exists estados_pedido CASCADE;
Create table estados_pedido (
	codigo char (1) not null,
	descripcion varchar (100) not null,
	constraint estados_pedido_pk primary key (codigo)
);

 --insertar--
INSERT INTO estados_pedido (codigo,descripcion)
VALUES 
  ('S','Solicitado'),
  ('R','Recibido');
  
  select * from estados_pedido;
  
------TABLA CABECERA PEDIDO-----
drop table if exists cabeceras_pedido CASCADE;
Create table cabeceras_pedido (
	numero serial not null,
	proveedor varchar (13) not null,
	fecha date not null,
	estado char (1) not null,
	constraint cabeceras_pedido_pk primary key (numero),
	constraint cabeceras_pedido_prov_fk foreign key (proveedor)
	references proveedores (codigo_prov),
	constraint cabeceras_pedido_estado_fk foreign key (estado)
	references estados_pedido (codigo)
);
 --insertar--
INSERT INTO cabeceras_pedido (numero,proveedor,fecha,estado)
VALUES 
  (1,'1792285747','2023-11-20','R'),
  (2,'1792285747','2023-11-20','R');
  
  select * from cabeceras_pedido;

------TABLA DETALLE PEDIDO-----
drop table if exists detalles_pedido CASCADE;
Create table detalles_pedido (
	codigo serial not null,
	cabecera_pedido int not null,
	producto int not null,
	cantidad int not null,
	cantidad_recibida int not null,
	subtotal money not null,
	constraint detalles_pedido_pk primary key (codigo),
	constraint detalle_producto_fk foreign key (producto)
	references productos (codigo_pro),
	constraint cabeceras_pedido_estado_fk foreign key (cabecera_pedido)
	references cabeceras_pedido (numero)
);

 --insertar--
INSERT INTO detalles_pedido (cabecera_pedido,producto,cantidad,subtotal,cantidad_recibida)
VALUES 
  (1,1,100,37.29,100),
  (1,4,50,11.8,50),
  (2,1,10,3.73,10);

  select * from detalles_pedido;