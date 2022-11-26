package uia.com.inventarios;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SolicitudRetiroMat.class, name = "SRM")
})

public class ReporteRetiroInventario implements IRetiroInventario
{
    protected NivelInventario inventario;
    protected SolicitudRetiroMat sem;

    public ReporteRetiroInventario(IRetiroInventario inventario)
    {
        super();
        this.inventario = (NivelInventario) new NivelInventario();
    }

    public ReporteRetiroInventario() {
        super();
    }


    public void cargaSolicitudRetiro(String nombre)
    {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            sem =  mapper.readValue(new FileInputStream(nombre), SolicitudRetiroMat.class );
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

        this.sem.getInventario().print();
    }

    @Override
    public List<InfoItem> busca(int id, String descripcion, String categoria, String cantidad, String idPartida, String idSubpartida, String idCategoria)
    {
        return inventario.busca(id, descripcion, categoria, cantidad, idPartida, idSubpartida, idCategoria);
    }

    @Override
    public void serializa()
    {
    }

    @Override
    public void print() {

    }



    @Override
    public void agrega(String idPartida, String descPartida, String idSubpartida, String descSubpartida, String idCat, String descCat,
                       Lote lote, int minimoNivel)
    {
        InfoItem item = new InfoItem("Item", idPartida, descPartida, descCat,  idPartida, idSubpartida, idCat,
                lote, minimoNivel);
    }

    @Override
    public void cargaSolicitudRetiroToInventario() {
        String idPartida = "";
        String idSubpartida = "";
        String idCategoria = "";
        CategoriaInventario categoria;//esto es porque ya existe una categoria previa que tenemos que capturar
        int retiroSem;


        String ubicacion; //la posicion igual ya existe podemos guardarla o construirla
        for (String partida : sem.getInventario().getItems().keySet()) {//saco el keyset de la partida
            for (String subpartida : sem.getInventario().getItems().get(partida).getItems().keySet()) {//saco el keyset de la subpartida
                for (String cat : sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().keySet()) {//saco el keyset de la categoria


                    idPartida = sem.getInventario().getItems().get(partida).getId();//atrapamos el id del producto en el mapa correspondiente a la partida
                    idSubpartida = sem.getInventario().getItems().get(partida).getItems().get(subpartida).getId();
                    idCategoria = sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getId();
                    retiroSem  = sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getCantidadRetiro();//saco la cantidad

                    this.inventario.gestionaRetiro(idPartida,idSubpartida,idCategoria, retiroSem);


                }

            }


        }


    }
//todo este metodo lo agregre a la interfaz de Iretiro
    @Override
    public void serializaNivelInventario(String nombreArchivo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(nombreArchivo), this.inventario);
    }


    public void cargaInventario(String nombre)
    {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            inventario =  mapper.readValue(new FileInputStream(nombre), NivelInventario.class );
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

        this.inventario.print();

    }
}
