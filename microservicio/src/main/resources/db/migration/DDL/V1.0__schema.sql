create table usuario (
 id int(11) not null auto_increment,
 cedula varchar(30) not null,
 nombre varchar(45) not null,
 telefono varchar(30) not null,
 primary key (id)
);

create table tipo_vehiculo (
 id int(11) not null auto_increment,
 nombre varchar(45) not null,
 precio_lavada double not null,
 primary key (id)
);

create table vehiculo (
 id int(11) not null auto_increment,
 id_usuario int(11) not null,
 id_tipo_vehiculo int(11) not null,
 placa varchar(6) not null,
 primary key (id),
 foreign key (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE,
 foreign key (id_tipo_vehiculo) REFERENCES tipo_vehiculo(id) ON DELETE CASCADE
);

create table cita (
 id int(11) not null auto_increment,
 id_vehiculo int(11) not null,
 fecha date not null,
 primary key (id),
 foreign key (id_vehiculo) REFERENCES vehiculo(id) ON DELETE CASCADE
);
