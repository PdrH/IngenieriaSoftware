package uia.com.inventarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Principal {

    public Principal() {
    }

    public static void main(String[] args) throws IOException {
        //  System.out.println("Hola UIA");
        //  NivelInventario inventario = new NivelInventario();
        // inventario.agrega("300","Videojuegos","150","PcGaming","153","Computadora de escritorio de alta gama", "3", null);
        //  inventario.serializa();

/*
        //funcionalidad transitoria para construcción del reporte de nivel de inventario
        NivelInventario inventario = new NivelInventario();
        inventario.agrega("100","Material Oficina","110","Sillas","111","Silla ejecutiva clase 1", "3", null);
        inventario.agrega("100","Material Oficina","110","Sillas","112","Silla ejecutiva clase 2", "3", null);
        inventario.agrega("100","Material Oficina","110","Sillas","113","Silla secretarial clase 1", "3", null);
        inventario.agrega("100","Material Oficina","110","Sillas","114","Silla secretarial clase 2", "3", null);
        inventario.agrega("100","Material Oficina","110","Sillas","115","Silla de espera ", "3", null);
        inventario.agrega("100","Material Oficina","120","Mesas","121","Mesa ejecutivo clase 1", "4", null);
        inventario.agrega("100","Material Oficina","120","Mesas","122","Mesa ejecutivo clase 2", "4", null);
        inventario.agrega("200","Material Oficina","120","Mesas","123","Mesa secretarial clase 1", "5", null);
        inventario.serializa();

/*

        //funcionalidad transitoria para construcción de la solicitud de Entrega
        todo: Explicacion, en este gestorEntrega se crea en su firma una nueva EntregaNivelInventario que este se encarga de cargar
        GestorEntrega gestorEntrega = new GestorEntrega(new EntregaNivelInventario());
        todo esta parte lo crea la solicitud de salida de material que se me hace raro que no la cree gestor salida
        SolicitudEntregaMaterial solicitudEntrega = gestorEntrega.agregaSolicitudSalidaMaterial(
                "SolicitudEntrega",
                "Alfonso ",
                "Profesor TSU");

        solicitudEntrega.agrega("100","Material Oficina",10, "110","Sillas","111","Silla ejecutiva clase 1",
                                                    "1000","Sillas Material Oficina","10-10-2020","700","Acme 1");
        solicitudEntrega.agrega("100","Material Oficina",11,"110","Sillas","112","Silla ejecutiva clase 2",
                                                    "1000","Sillas Material Oficina","11-10-2020","700","Acme 1");
        solicitudEntrega.agrega("100","Material Oficina",12,"110","Sillas","113","Silla secretarial clase 1",
                                                    "1000","Sillas Material Oficina","12-10-2020","700","Acme 1");
        solicitudEntrega.agrega("100","Material Oficina",13,"110","Sillas","114","Silla secretarial clase 2",
                                                    "1000","Sillas Material Oficina","13-10-2020","700","Acme 1");
        solicitudEntrega.agrega("100","Material Oficina",14,"110","Sillas","115","Silla de espera ",
                                                    "1000","Sillas Material Oficina","14-10-2020","700","Acme 1");
        solicitudEntrega.agrega("100","Material Oficina",15,"120","Mesas","121","Mesa ejecutivo clase 1",
                                                    "1000","Mesas Material Oficina","15-10-2020","700","Acme 1");
        solicitudEntrega.agrega("100","Material Oficina",10, "130","Escritorios","111","Escritorio ejecutiva clase 1",
                                                    "2000","Escritorios Material Oficina","10-11-2021","800","Acme 2");
        solicitudEntrega.agrega("100","Material Oficina",11,"130","Escritorios","112","Escritorio ejecutiva clase 2",
                                                    "2000","Escritorios Material Oficina","10-11-2021","800","Acme 2");
        solicitudEntrega.agrega("100","Material Oficina",12,"130","Escritorios","113","Escritorio secretarial clase 1",
                                                    "2000","Escritorios Material Oficina","10-11-2021","800","Acme 2");
        solicitudEntrega.agrega("100","Material Oficina",13,"130","Escritorios","114","Escritorio secretarial clase 2",
                                                    "2000","Escritorios Material Oficina","10-11-2021","800","Acme 2");
        solicitudEntrega.agrega("100","Material Oficina",14,"130","Escritorios","115","Escritorio de espera ",
                                                    "2000","Escritorios Material Oficina","10-11-2021","800","Acme 2");
        solicitudEntrega.serializa();

        */


        IEntregaNivelInventario inventario = null;
        GestorEntrega gestorEntrega = new GestorEntrega(inventario);
        gestorEntrega.cargaSolicitudEntrega("SolicitudEntrega_1.json");   // Esta es la solicitud o archivo fuente o documento que se quiere agregar o que se usa para actualizar
        gestorEntrega.cargaInventario("ReporteNivelInventario-8.json");   // Este es documento objetivo.. el que quieren actualizar
        gestorEntrega.cargaSolicitudEntregaToInventario();
        gestorEntrega.serializaActualizacionInventario();
/*
        String cantidad = "";
        String categoriaIdSem = "";
        String categoriaIdInventario = "";
        String cantidadTotal;
        String cantidadNivelInventario;
        int cantidadsuma;
        for (String partida : gestorEntrega.sem.getInventario().getItems().keySet()) {//saco el keyset de la partida
            for (String subpartida : gestorEntrega.sem.getInventario().getItems().get(partida).getItems().keySet()) {//saco el keyset de la subpartida
                for (String cat : gestorEntrega.sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().keySet()) {//saco el keyset de la categoria



                    cantidad = gestorEntrega.sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getCantidad();//saco la cantidad
                    categoriaIdSem = gestorEntrega.sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getId();// aqui sacas el id
                    categoriaIdInventario = gestorEntrega.inventario.getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getId();
                    cantidadNivelInventario= gestorEntrega.inventario.getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getCantidad();




                    System.out.println("*****************************************************");
                   // System.out.println("Lote desde categoria "+gestorEntrega.sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getLote());
                   // System.out.println("categoria sem " + categoriaIdSem + " categoria idinventario " + categoriaIdInventario);
                   // System.out.println("Aqui esta la cantidad: " + cantidad + " de la categoria: " + categoriaIdSem + " " + "el id de la categoria de los items en el inventario son :" + categoriaIdInventario);//obtengo items de la lista en basae a su cantidad +gestorEntrega.sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getItems(cantidad)
                   // System.out.println("Cantidad de producto en SEM a llegar: " + cantidad+ " de la categoria "+ categoriaIdSem);
                    //System.out.println("Cantidad de producto en NivelInventario a llegar: " +  cantidadNivelInventario+" de la categoria "+ categoriaIdInventario);



                }

            }
*/

            SpringApplication.run(Principal.class, args);
        }
    }
