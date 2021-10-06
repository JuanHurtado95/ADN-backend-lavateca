select cita.id, cita.fecha,
vehiculo.id as id_vehiculo, vehiculo.placa,
usuario.id as id_usuario, usuario.cedula, usuario.nombre, usuario.telefono,
tipo_vehiculo.id as id_tipo_vehiculo, tipo_vehiculo.nombre as nombre_tipo_vehiculo, tipo_vehiculo.precio_lavada
from cita
inner join vehiculo on cita.id_vehiculo = vehiculo.id
inner join usuario on vehiculo.id_usuario = usuario.id
inner join tipo_vehiculo on vehiculo.id_tipo_vehiculo = tipo_vehiculo.id;