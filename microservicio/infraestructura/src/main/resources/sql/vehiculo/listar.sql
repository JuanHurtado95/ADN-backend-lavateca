select vehiculo.id, vehiculo.placa,
usuario.id as id_usuario, usuario.cedula, usuario.nombre, usuario.telefono,
tipo_vehiculo.id as id_tipo_vehiculo, tipo_vehiculo.nombre as nombre_vehiculo, tipo_vehiculo.precio_lavada
from vehiculo
inner join usuario on vehiculo.id_usuario = usuario.id
inner join tipo_vehiculo on vehiculo.id_tipo_vehiculo = tipo_vehiculo.id;