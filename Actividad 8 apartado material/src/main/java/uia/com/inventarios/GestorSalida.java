package uia.com.inventarios;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GestorSalida extends InfoInventarioUIA
{
    private NivelInventario reporteInventario;//esta clase tiene el metodo busca


    public GestorSalida(int id, String name, String descripcion, String cantidad, String partida, String subpartida, String categoria) {
        super(id, name, descripcion, cantidad, partida, subpartida, categoria);
    }

    public GestorSalida()throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            reporteInventario = mapper.readValue(new FileInputStream("ReporteNivelInventario-3.json"), NivelInventario.class );
        }catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (reporteInventario != null)
        {
            System.out.println("----- Items List -----");
            for (Map.Entry<String, InfoItem> item : reporteInventario.getItems().entrySet())
            {
                PartidaInventario nodo = (PartidaInventario) item.getValue();
                nodo.print();
            }
        }
    }


    public void serializa() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("solicitudSalidaMaterial-2.json"), this);
    }

    //pide material a gestor de salida esta es una solicitud para sacar el material
    // TODO: 10/11/2022 aqui voy a cambiar void por SolicitudSalidaMaterial porque me interesa tenerla a la mano
    public SolicitudSalidaMaterial agregaSolicitudSalidaMaterial(int id, String name, String categoria, String cantidad, String idPartida, String idSubpartida, String idCategoria) throws IOException
    {
        String existencia = "";
        existencia = reporteInventario.busca(id, name, categoria, cantidad, idPartida, idSubpartida, idCategoria);//esto retorna la cantidad
        if(existencia.length() > 0)
        {
            System.out.println("Existen "+existencia+"  "+ categoria +"en inventario");


            SolicitudSalidaMaterial newSSM = new SolicitudSalidaMaterial(id, name, categoria, cantidad, idPartida, idSubpartida, idCategoria);
            newSSM.setEstatus("apartado");
            this.getItems().add(newSSM);
            this.serializa();
            return newSSM;
        }
        return  null;
    }
}
