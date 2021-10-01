create table usuario (
 id int(11) not null auto_increment,
 cedula varchar(30) not null,
 nombre varchar(45) not null,
 telefono varchar(30) not null,
 primary key (id)
);

create table cita (
 id int(11) not null auto_increment,
 cedula_usuario varchar(30) not null,
 fecha date (45) not null,
 tipo_vehiculo varchar(6) not null,
 placa varchar(6) not null,
 valor double not null,
 primary key (id)
);