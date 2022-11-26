package uia.com.inventarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Principal {

    public Principal() {
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Hola UIA, Realizando Examen 3c, Retiro de Mat");


        //funcionalidad transitoria para construcción del reporte de nivel de inventario
        NivelInventario inventario = new NivelInventario();

        Lote lote1 = new Lote("1000","Lote 1", "6","10-10-2020","700","Acme 1");
        Lote lote2 = new Lote("2000","Lote 2", "7","10-10-2020","701","Acme 2");
        Lote lote3 = new Lote("3000","Lote 3", "8","10-10-2020","703","Acme 3");
        Lote lote4 = new Lote("4000","Lote 4", "9","10-10-2020","704","Acme 4");
        Lote lote5 = new Lote("5000","Lote 5", "5","10-10-2020","705","Acme 5");
        Lote lote6 = new Lote("6000","Lote 6", "6","10-10-2020","706","Acme 6");
        Lote lote7 = new Lote("2000","Lote 7","7", "10-11-2021","800","Patito 1");
        Lote lote8 = new Lote("8000","Lote 8","8", "10-11-2021","801","Patito 2");
        Lote lote9 = new Lote("9000","Lote 9","9", "10-11-2021","803","Patito 3");
        Lote lote10 = new Lote("10000","Lote 10","5", "10-11-2021","804","Patito 4");
        Lote lote11 = new Lote("11000","Lote 11","6", "10-11-2021","805","Patito 5");

        inventario.agrega("100","Mat Oficina", "110","Sillas","111","Silla ejecutiva clase 1", lote1, 5);
        inventario.agrega("100","Mat Oficina","110","Sillas","112","Silla ejecutiva clase 2", lote2,5);
        inventario.agrega("100","Mat Oficina","110","Sillas","113","Silla secretarial clase 1", lote3,5);
        inventario.agrega("100","Mat Oficina","110","Sillas","114","Silla secretarial clase 2", lote4,5);
        inventario.agrega("100","Mat Oficina","110","Sillas","115","Silla de espera ", lote5,5);
        inventario.agrega("100","Mat Oficina","120","Mesas","121","Mesa ejecutivo clase 1", lote6,5);
        inventario.agrega("100","Mat Oficina", "130","Escritorios","131","Escritorio ejecutiva clase 1", lote7,5);
        inventario.agrega("100","Mat Oficina","130","Escritorios","132","Escritorio ejecutiva clase 2", lote8,5);
        inventario.agrega("100","Mat Oficina","130","Escritorios","133","Escritorio secretarial clase 1", lote9,5);
        inventario.agrega("100","Mat Oficina","130","Escritorios","134","Escritorio secretarial clase 2", lote10,5);
        inventario.agrega("100","Mat Oficina","130","Escritorios","135","Escritorio de espera ", lote11,5);
        inventario.serializa();


        //funcionalidad transitoria para construcción de la solicitud de Retiro
        GestorRetiroInventario gestorRetiro = new GestorRetiroInventario(new ReporteRetiroInventario());

        SolicitudRetiroMat solicitudRetiro = gestorRetiro.agregaSolicitudSalidaMat(
                "SolicitudRetiroMat",
                "Alfonso ",
                "Profesor TSU");

        solicitudRetiro.agrega("100","Mat Oficina", "110","Sillas","111","Silla ejecutiva clase 1", lote1, 2);
        solicitudRetiro.agrega("100","Mat Oficina","110","Sillas","113","Silla secretarial clase 1", lote3, 3);
        solicitudRetiro.agrega("100","Mat Oficina","110","Sillas","115","Silla de espera ", lote5, 4);
        solicitudRetiro.agrega("100","Mat Oficina", "130","Escritorios","131","Escritorio ejecutiva clase 1", lote7, 2);
        solicitudRetiro.agrega("100","Mat Oficina","130","Escritorios","133","Escritorio secretarial clase 1", lote9, 3);
        solicitudRetiro.agrega("100","Mat Oficina","130","Escritorios","135","Escritorio de espera ", lote11, 4);
        solicitudRetiro.serializa();





        IRetiroInventario inventario2 = null;
        gestorRetiro = new GestorRetiroInventario(inventario2);
        gestorRetiro.cargaSolicitudRetiro("SolicitudRetiroMat_1.json");   // Esta es la solicitud o archivo fuente o documento que se quiere agregar o que se usa para actualizar
        gestorRetiro.cargaInventario("ReporteNivelInventario-15.json");   // Este es documento objetivo.. el que quieren actualizar
        gestorRetiro.cargaSolicitudRetiroToInventario();
        gestorRetiro.serializaNivelInventario("ReporteNivelInventario-15.json");

        String clase = "";
        String idPartida = "";
        String descripcionPartida = "";
        String idSubpartida = "";
        String descripcionSubPartida = "";
        String idCategoria = "";
        String descripcionCategoria = "";
        CategoriaInventario categoria;//esto es porque ya existe una categoria previa que tenemos que capturar
        int retiroSem;
        int retiroInve;
        String retiroStringSEM;
        String cantidadNivelInventario;
        String idItem;
        Lote lote;//el lote ya existe solo hay que capturarlo
        String ubicacion; //la posicion igual ya existe podemos guardarla o construirla

        for (String partida : gestorRetiro.sem.getInventario().getItems().keySet()) {//saco el keyset de la partida
            for (String subpartida : gestorRetiro.sem.getInventario().getItems().get(partida).getItems().keySet()) {//saco el keyset de la subpartida
                for (String cat : gestorRetiro.sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().keySet()) {//saco el keyset de la categoria
                    clase = gestorRetiro.sem.getInventario().getItems().get(partida).getClase();

                    idPartida = gestorRetiro.sem.getInventario().getItems().get(partida).getId();//atrapamos el id del producto en el mapa correspondiente a la partida
                    descripcionPartida = gestorRetiro.sem.getInventario().getItems().get(partida).getName();
                    idSubpartida = gestorRetiro.sem.getInventario().getItems().get(partida).getItems().get(subpartida).getId();
                    descripcionSubPartida = gestorRetiro.sem.getInventario().getItems().get(partida).getItems().get(subpartida).getName();
                    idCategoria = gestorRetiro.sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getId();
                    descripcionCategoria = gestorRetiro.sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getName();
                    retiroSem = gestorRetiro.sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getCantidadRetiro();//saco la cantidad
                    retiroInve = inventario.getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getCantidadRetiro();
                    categoria = (CategoriaInventario) gestorRetiro.sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().get(cat);//esto funciona porque esta clase hereda de infoItem
                    cantidadNivelInventario = inventario.getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getCantidad();
                    retiroStringSEM = gestorRetiro.sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getCantidad();
                    idItem =gestorRetiro.sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getId();
                    lote = gestorRetiro.sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getLote();
                    //    this.inventario.retiraInventario(idPartida, idSubpartida, idCategoria, categoria, descripcionCategoria, cantidadSEM, cantidadNivelInventario, lote);
                    System.out.println("************************************");
                    System.out.println("La  clase de la partida es " + clase);
                    System.out.println("El id de la partida es: " + idPartida);
                    System.out.println("La descripcion de la clase de la partida es " + descripcionPartida);
                    System.out.println("El id de la subpartida es: " + idSubpartida);
                    System.out.println("La descripcion de la clase de la Subpartida es " + descripcionSubPartida);
                    System.out.println("El id de la categoria es: " + idCategoria);
                    System.out.println("El id del item de la categori es: " + idItem);
                    System.out.println("La descripcion de la clase de la categoria es " + descripcionCategoria);
                    System.out.println("La cantidad de material a retirar de SEM es: " + retiroSem);
                    System.out.println("La cantidad de material de inventario es :" + cantidadNivelInventario);
                   // System.out.println("La cantidad de material a retirar en SEM es :" + retiroStringSEM);
                    System.out.println("La cantidad de retiro que aparece en getCantidadRetiro para nivel inventario es: " + retiroInve);
                    System.out.println("La categoria atrapada es "+categoria);


                }

            }


        }

        SpringApplication.run(Principal.class, args);
    }

}
