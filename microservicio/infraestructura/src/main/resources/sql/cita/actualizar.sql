update cita
set cedula_usuario = :cedulaUsuario,
    fecha = :fecha,
	tipo_vehiculo = :tipoVehiculo,
	placa = :placa,
	valor = :valor
where id = :id and placa = :placa