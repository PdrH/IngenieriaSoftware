package uia.com.inventarios;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;


public class RevisionEntregaMaterial {

    private  SolicitudSalidaMaterial items;

    // private List<InfoInventarioUIA> items = new ArrayList<InfoInventarioUIA>();
    public SolicitudSalidaMaterial lee() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        items = mapper.readValue(new File("solicitudSalidaMaterial-4.json"), SolicitudSalidaMaterial.class);
      //  System.out.println(items);
        return items;
    }

    public void verificaItems(SolicitudSalidaMaterial solicitudSalidaMaterial) {


        for (InfoItem sol : solicitudSalidaMaterial.getItems()){

                System.out.println(sol);



        }
        System.out.println("Entrega Correcta se procede a generar Retiro de material");

    }



    public static void main(String[] args) throws IOException {
        RevisionEntregaMaterial r = new RevisionEntregaMaterial();
        SolicitudSalidaMaterial sl = r.lee();
        r.verificaItems(sl);

    }
}
