package uia.com.inventarios;

import java.io.IOException;
import java.util.Scanner;

public class EspacioAlmacenamiento extends InfoInventarioUIA {

    private String ubicacion;


    public String getUbicacion() {
        return ubicacion;
    }

    public EspacioAlmacenamiento() {
    }

    public EspacioAlmacenamiento(int id, String name, String descripcion, String cantidad, String partida, String subpartida, String categoria, String ubicacion) {
        super(id, name, descripcion, cantidad, partida, subpartida, categoria);
        this.ubicacion = ubicacion;
    }



    public void validarEspacio(SolicitudSalidaMaterial sol,String ubicacion) {
        EspacioAlmacenamiento es = new EspacioAlmacenamiento();
        for (int i = 0; i < sol.getItems().size(); i++) {
            System.out.println(sol.getItems().get(i));
            System.out.println("Donde se ubica el material?");
            sol.getItems().get(i).setUbicacion(ubicacion);

        }
        System.out.println(es);


    }
}
