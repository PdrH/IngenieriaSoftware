package uia.com.inventarios;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class NivelInventario extends PartidaInventario{

    //private HashMap<String, HashMap<String, PartidaInventario>> reporteNIvelInventario = new HashMap<String, HashMap<String, PartidaInventario>>();


    public NivelInventario() {
        super();
    }

    public void agrega(String idPartida, String descPartida, String idSubpartida, String descSubpartida, String idCat, String descCat, String cantidad)
    {
        if(this.getItems().get(idPartida) != null) {
            if (this.getItems().get(idPartida).getItems().get(idSubpartida) != null) {
                if (this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().get(idCat) == null) {
                    CategoriaInventario cat = new CategoriaInventario(idCat, descCat, "SinEstatus", cantidad);
                    this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().put(idCat, cat);
                }
            } else {
                SubpartidaInventario subpartida = new SubpartidaInventario(idSubpartida, descSubpartida, "SinEstatus");
                this.getItems().get(idPartida).getItems().put(idPartida, subpartida);
            }
        }
        else
        {
            PartidaInventario partida = new PartidaInventario(idPartida, descPartida, "SinEstatus");
            SubpartidaInventario subpartida = new SubpartidaInventario(idSubpartida, descSubpartida, "SinEstatus");
            CategoriaInventario cat = new CategoriaInventario(idCat, descCat, "SinEstatus", cantidad);
            subpartida.getItems().put(idCat, cat);
            partida.getItems().put(idSubpartida, subpartida);
            this.getItems().put(idPartida, partida);
        }
    }

    public void serializa() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("ReporteNivelInventario-3.json"), this);
    }

    public String busca(int id, String name, String descCat, String cantidad, String idPartida, String idSubpartida, String idCat)
    {
        if(this.getItems().get(idPartida) != null)
        {
            if (this.getItems().get(idPartida).getItems().get(idSubpartida) != null)
            {
                if (this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().get(idCat) != null)
                {
                    CategoriaInventario cat = (CategoriaInventario) this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().get(idCat);
                    return cat.getCantidad();
                }
            }
        }
        return "";
    }
}
