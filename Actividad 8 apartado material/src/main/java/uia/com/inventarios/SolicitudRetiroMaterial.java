package uia.com.inventarios;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.spi.CalendarDataProvider;

public class SolicitudRetiroMaterial extends SolicitudEntregaMaterial{

    public SolicitudRetiroMaterial(SolicitudSalidaMaterial prueba) {
        super(prueba);
    }

    @Override
    public void serializa() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("SolicitudRetiroMaterial "+ LocalDate.now() +".json"), this);
    }
}
