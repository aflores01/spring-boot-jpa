INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (1,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (2,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (3,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (4,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (5,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (6,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (7,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (8,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (9,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (10,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (11,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (12,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (13,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (14,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (15,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (16,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (17,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (18,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (19,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (20,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (21,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (22,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (23,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (24,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (25,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (26,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (27,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (28,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (29,'Andres','Guzman','mail@correo.com','2017-08-28','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (30,'Andres','Guzman','mail@correo.com','2017-08-28','');

INSERT INTO productos (nombre,precio,create_at) VALUES('Blackmagic PCC4k',31999,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('Blackmagic ATEM Mini',5999,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('Blackmagic URSA Mini Pro 4.6k',110999,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('Blackmagic URSA Broadcast',31999,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('Blackmagic Video Asist',9599,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('Blackmagic Studio Fiber Converter',31999,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('Zhiyun Weebill-s',11999,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('Zhiyun Smoth 4',2999,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('Sony A7 III',30999,NOW());

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura de equipo de grabacion', null, 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3,1,6);

INSERT INTO users (username,password, enabled) VALUES('admin','12345','1')
INSERT INTO users (username,password, enabled) VALUES('user','12345','1')