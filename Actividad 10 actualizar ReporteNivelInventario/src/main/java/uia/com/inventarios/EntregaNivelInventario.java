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
import java.util.Map;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SolicitudEntregaMaterial.class, name = "SEM")
})

public class EntregaNivelInventario implements IEntregaNivelInventario {
    protected NivelInventario inventario; //esto tiene un mapa de InfoItem es por eso que es posible navegar en el mapa reportenivelinventario-8.jason
    protected SolicitudEntregaMaterial sem;// aqui ser carga la solicitud del json  solicitudentregaMaterial_1.jason

    public EntregaNivelInventario(IEntregaNivelInventario inventario) {
        super();
        this.inventario = (NivelInventario) new NivelInventario();
    }

    public EntregaNivelInventario() {
    }

    @Override
    public void cargaSolicitudEntrega(String nombre) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            sem = mapper.readValue(new FileInputStream(nombre), SolicitudEntregaMaterial.class);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // this.sem.getInventario().print();// esto imprime el inventario que fue cargado dentro de sem
    }

    @Override
    public List<InfoItem> busca(int id, String descripcion, String categoria, String cantidad, String idPartida, String idSubpartida, String idCategoria) {
        return inventario.busca(id, descripcion, categoria, cantidad, idPartida, idSubpartida, idCategoria);
    }

    @Override
    public void serializa() {
    }

    @Override
    public void print() {

    }

    //interface pollution
    @Override
    public SolicitudEntregaMaterial nuevaSolicitudEntrega(String s) {
        return null;
    }


    public void agrega(String idPartida, String descPartida, int cantidad, String idSubpartida, String descSubpartida, String idCat,
                       String descCat, String idLote, String lote, String fechaIngreso,
                       String idProveedor, String proveedor) {
        InfoItem item = new InfoItem("Item", idPartida, descPartida, descCat, String.valueOf(cantidad), idPartida, idSubpartida, idCat,
                idLote, lote, fechaIngreso, idProveedor, proveedor);
    }

    // TODO: 23/11/2022 esto me parece que es la tarea
    @Override
    public void cargaSolicitudEntregaToInventario() throws IOException {
        String idPartida = "";
        String idSubpartida = "";
        String idCategoria = "";
        CategoriaInventario categoria;//esto es porque ya existe una categoria previa que tenemos que capturar
        String descripcionCategoria = "";
        String cantidadSEM = "";
        String cantidadNivelInventario;
        Lote lote;//el lote ya existe solo hay que capturarlo
        String ubicacion; //la posicion igual ya existe podemos guardarla o construirla
        int cantidadsuma;
        for (String partida : sem.getInventario().getItems().keySet()) {//saco el keyset de la partida
            for (String subpartida : sem.getInventario().getItems().get(partida).getItems().keySet()) {//saco el keyset de la subpartida
                for (String cat : sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().keySet()) {//saco el keyset de la categoria

                    idPartida=this.sem.getInventario().getItems().get(partida).getId();//atrapamos el id del producto en el mapa correspondiente a la partida
                    idSubpartida=this.sem.getInventario().getItems().get(partida).getItems().get(subpartida).getId();
                    idCategoria=this.sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getId();
                    categoria = (CategoriaInventario) this.sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().get(cat);//esto funciona porque esta clase hereda de infoItem
                    cantidadSEM = sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getCantidad();//saco la cantidad
                    cantidadNivelInventario = inventario.getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getCantidad();
                    descripcionCategoria=sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getName();
                    lote=this.sem.getInventario().getItems().get(partida).getItems().get(subpartida).getItems().get(cat).getLote();
                    this.inventario.actualizaNivelInventario(idPartida,idSubpartida,idCategoria,categoria,descripcionCategoria,cantidadSEM,cantidadNivelInventario,lote);


                }

            }


        }

    }
    public void serializaActualizacionInventario() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("ReporteNivelInventario-10.json"), this.inventario);
    }

    public void cargaInventario(String nombre) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            inventario = mapper.readValue(new FileInputStream(nombre), NivelInventario.class);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.inventario.print();

    }
}
