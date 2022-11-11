package uia.com.inventarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Principal {

    public static void main(String[] args) throws IOException {
        System.out.println("Hola UIA");

        //funcionalidad transitoria para construcción del reporte de nivel de inventario
        /*
        NivelInventario inventario = new NivelInventario();
        inventario.agrega("100","Material Oficina","110","Sillas","111","Silla ejecutiva clase 1", "10");
        inventario.agrega("100","Material Oficina","110","Sillas","112","Silla ejecutiva clase 2", "20");
        inventario.agrega("100","Material Oficina","110","Sillas","113","Silla secretarial clase 1", "30");
        inventario.agrega("100","Material Oficina","110","Sillas","114","Silla secretarial clase 2", "40");
        inventario.agrega("100","Material Oficina","110","Sillas","115","Silla de espera ", "50");
        inventario.agrega("100","Material Oficina","120","Mesas","121","Mesa ejecutivo clase 1", "60");
        ItemInventarioUIA info7 = new InfoInventario("7","100","Material Oficina","120","Mesas","122","Mesa ejecutivo clase 2");
        ItemInventarioUIA info8 = new InfoInventario("8","100","Material Oficina","120","Mesas","123","Mesa secretarial clase 1");
        ItemInventarioUIA info9 = new InfoInventario("9","100","Material Oficina","120","Mesas","124","Mesa secretarial clase 2");
        ItemInventarioUIA info10 = new InfoInventario("10","100","Material Oficina","120","Mesas","125","Mesa de espera ");
        ItemInventarioUIA info11 = new InfoInventario("11","100","Material Oficina","130","Escritorios","131","Escritorio ejecutivo clase 1");
        ItemInventarioUIA info12 = new InfoInventario("12","100","Material Oficina","130","Escritorios","132","Escritorio ejecutivo clase 2");
        ItemInventarioUIA info13 = new InfoInventario("13","100","Material Oficina","130","Escritorios","133","Escritorio secretarial clase 1");
        ItemInventarioUIA info14 = new InfoInventario("14","100","Material Oficina","130","Escritorios","134","Escritorio secretarial clase 2");
        ItemInventarioUIA info15 = new InfoInventario("15","100","Material Oficina","130","Escritorios","135","Escritorio de espera ");
        ItemInventarioUIA info16 = new InfoInventario("16","100","Material Oficina","140","Libreros","141","Librero ejecutivo clase 1");
        ItemInventarioUIA info17 = new InfoInventario("17","100","Material Oficina","140","Libreros","142","Librero ejecutivo clase 2");
        ItemInventarioUIA info18 = new InfoInventario("18","100","Material Oficina","140","Libreros","143","Librero secretarial clase 1");
        ItemInventarioUIA info19 = new InfoInventario("19","100","Material Oficina","140","Libreros","144","Librero secretarial clase 2");
        ItemInventarioUIA info20 = new InfoInventario("20","100","Material Oficina","140","Libreros","145","Librero de espera ");
        ItemInventarioUIA info21 = new InfoInventario("21","100","Material Oficina","150","Archiveros ","151","Archivero ejecutivo clase 1");
        ItemInventarioUIA info22 = new InfoInventario("22","100","Material Oficina","150","Archiveros ","152","Archivero ejecutiva clase 2");
        ItemInventarioUIA info23 = new InfoInventario("22","100","Material Oficina","150","Archiveros ","153","Archivero secretarial clase 1");
        ItemInventarioUIA info24 = new InfoInventario("23","100","Material Oficina","150","Archiveros ","154","Archivero secretarial clase 2");
        ItemInventarioUIA info25 = new InfoInventario("24","100","Material Oficina","150","Archiveros ","155","Archivero de espera ");
        inventario.serializa();
         */


        GestorSalida gestor = new GestorSalida();
        //  System.out.println(gestor);

        //esta es la solicitud material

        SolicitudSalidaMaterial prueba = new SolicitudSalidaMaterial();


        prueba.getItems().add(gestor.agregaSolicitudSalidaMaterial(1, "Solicitud Material",
                "Silla secretarial clase 1\n",
                "2",
                "100",
                "110",
                "113"));





        prueba.getItems().add(gestor.agregaSolicitudSalidaMaterial(1, "Solicitud Material",
                "Silla de espera\n",
                "2",
                "100",
                "110",
                "115"));




        EspacioAlmacenamiento espacioAlmacenamiento = new EspacioAlmacenamiento();
        espacioAlmacenamiento.validarEspacio(prueba,"Planta Baja");

        SolicitudEntregaMaterial solicitudEntregaMaterial = new SolicitudEntregaMaterial(prueba);
        solicitudEntregaMaterial.serializa();

        SolicitudRetiroMaterial retiroMaterial = new SolicitudRetiroMaterial(prueba);
        System.out.println("Esto es una solicitud de retiro de material "+ retiroMaterial);
        retiroMaterial.serializa();


        SpringApplication.run(Principal.class, args);
    }

}
