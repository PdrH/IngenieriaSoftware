# Explicaciones

* Se desglozo el problema encontrando que apartir del metodo de la clase GestorSalida agregaSolicitudMaterial(), cambiando la firma del metodo podemos retornar la solicitud y convertirla en nuestra solicitud temporal, en la cual seguiremos trabajando.
*  En la clase InfoinventarioUIA agregue dos variables nuevas que son estatus y ubicacion.
*  En la clase principal manejamos la SolicitudSalidaMaterial como nuestra solicitud de material temporal y seguido agregamos en ella dos solicitudes de material.
*  Como siguiente paso creamos la clase espacio almacenamiento y agregamos la ubicacion de ambas solicitudes en almacenamiento.
*  Se crea SolicitudEntregaMaterial en donde se crea un archivo EntregaSolicitudesMaterial con la fecha de creacion.
*  Se creo la nueva clase SolicitudRetiroMaterial y en ella se colocan las nuevas solicitudes para que  se guarde en un archivo llamado solicitudRetiroMaterial con la fecha de creacion.
*  Para asegurar que el material estaba con calidad de apartado, desde que se creo la solicitud de material en el gestorSalida, en ese punto se cambia el estado a "apartado"