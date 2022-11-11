package uia.com.inventarios;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SolicitudEntregaMaterial extends SolicitudSalidaMaterial  {


    public SolicitudEntregaMaterial(SolicitudSalidaMaterial prueba) {
        super(prueba.getId(), prueba.getName(), prueba.getDescripcion(), prueba.getCantidad(), prueba.getPartida(), prueba.getSubpartida(), prueba.getCategoria(), prueba.getItems(), prueba.getEstatus(), prueba.getUbicacion());
    }

    @Override
    public void serializa() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("EntregaSolicitudesMaterial "+ LocalDate.now() +".json"), this);
    }
}
