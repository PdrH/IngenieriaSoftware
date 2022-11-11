package uia.com.inventarios;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolicitudSalidaMaterial extends InfoInventarioUIA {


    public SolicitudSalidaMaterial(InfoInventarioUIA sm) throws IOException {
        super();

    }
    public SolicitudSalidaMaterial() {


    }



    public SolicitudSalidaMaterial(int id, String name, String categoria, String cantidad, String idPartida, String idSubpartida, String idCategoria) {
        super(id, name, categoria, cantidad, idPartida, idSubpartida, idCategoria);
    }

    public SolicitudSalidaMaterial(int id, String name, String descripcion, String cantidad, String partida, String subpartida, String categoria, List<InfoInventarioUIA> items, String estatus, String ubicacion) {
        super(id, name, descripcion, cantidad, partida, subpartida, categoria, items, estatus, ubicacion);
    }



    public void serializa() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("solicitudSalidaMaterial-1.json"), this);
    }

    public boolean apartado(SolicitudSalidaMaterial solicitud) {
        return solicitud.getEstatus().toLowerCase().trim() == "apartado";//true si no false
    }





}