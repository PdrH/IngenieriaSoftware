package uia.com.inventarios;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RetiroMaterial {
   private List<SolicitudSalidaMaterial> items = new ArrayList<SolicitudSalidaMaterial>();



    public RetiroMaterial() {
    }

    public void creaSolicitudRetiroMaterial(SolicitudSalidaMaterial newSSM) throws IOException {


        this.getItems().add(newSSM);
        //AQUI es donde se crea la solicitud apartadoMaterial

        this.serializa("RetiroMaterial-1.json");
    }
    protected void serializa(String nombre) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(nombre), this);
    }

    public List<SolicitudSalidaMaterial> getItems() {
        return items;
    }

    public void setItems(List<SolicitudSalidaMaterial> items) {
        this.items = items;
    }
}
