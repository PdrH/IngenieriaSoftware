package uia.com.inventarios;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class NivelInventario extends PartidaInventario{

    //private HashMap<String, HashMap<String, PartidaInventario>> reporteNIvelInventario = new HashMap<String, HashMap<String, PartidaInventario>>();


    public NivelInventario() {
        super();
    }

//todo a la firma de agrega solo agrego lote
    public InfoItem agrega(String idPartida, String descPartida, String idSubpartida, String descSubpartida, String idCat, String descCat, String cantidad, Lote lote)
    {
        //todo desde aqui declaro el info item que antes declaraba en el for
        InfoItem item = null;

        if(this.getItems().get(idPartida) != null) {
            if (this.getItems().get(idPartida).getItems().get(idSubpartida) != null)
            {
                if (this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().get(idCat) == null)
                {
                    // TODO: 23/11/2022 esto es el valor de las computadoras 3A30,3A32,3A31 
                    //idpartida 300 entre 100 =3
                    //todo partida de la mesa secretarial clase 1 es 200 enter 100 = 2
                    String ubicP = String.valueOf(Integer.parseInt(idPartida)/100);
                    //idsubpartida=150/1000 = 0.15+64= 64.15 A
                    // idsubpartida =120, 120/100= 1.2/10=0.12 +64 =64.12 que sigue siendo A en ascii
                    char ubicS = (char) (65 + (Integer.parseInt(idSubpartida)/100)/10);
                    //idcat =153- 150 es igual a 3
                    // todo idcat= 123 entonces 123-120=3
                    String ubicC = String.valueOf((Integer.parseInt(idCat)-Integer.parseInt(idSubpartida)));
                    CategoriaInventario cat = new CategoriaInventario("Categoria", idCat, descCat, "SinEstatus", cantidad, "SinPosicion");
                    this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().put(idCat, cat);

                    // todo crea las 5 en base a la cantidad
                    for(int i=0; i<Integer.parseInt(cantidad); i++)
                    {
                        // todo aqui pegan partida+subpartida+categoria que esto es  2A3+(i), i ira incrementado segun el for en mesa son 5 obetos asi que llegara hasta 4, siendo 2A34
                        String ubic = ubicP+ubicS+ubicC+String.valueOf(i);
                        //todo el id de la categoria se ocupa para darle el idItem +(i)
                        String idItem = idCat+String.valueOf(i);
                        /*
                        el primer valor del constructor es clase el cual se le asigna el nombre de item,se coloca el id del item, seguido de la descripcion, estatus esta sin estatus
                        porque aun en este paso no se aparta ni nada, cantidad se asignamo como  y al final se agrega la ubicacion. ya que va contando de uno en uno
                        hasta que sale de ciclo for segun la cantidad de items que agregaste en la firma del metodo
                        al final en item.setLote se agrega el lote el cual en principal se ve que se mete como nulo.
                         */
                        item = new InfoItem("Item", idItem, descCat, "SinEstatus", "1", ubic);
                        item.setLote(lote);
                        //recorre el mapa y bajamos hasta categoria obteniendo los items y agregando el material al Nivelinventario
                        this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().get(idCat).getItems().put(idItem, item);
                    }
                }
            } else {
                //si subpartida esta bacia se crea, aqui la cantidad y el estatus no importan porque eso es relevante hasta categoria asi que aqui se dejan vacios
                SubpartidaInventario subpartida = new SubpartidaInventario("Subpartida", idSubpartida, descSubpartida,  "SinEstatus", "", "");
                //se agrega la subpartida en el mapa
                this.getItems().get(idPartida).getItems().put(idPartida, subpartida);
            }
        }
        else
        {
            PartidaInventario partida = new PartidaInventario("Partida", idPartida, descPartida, "", "SinEstatus", "");
            SubpartidaInventario subpartida = new SubpartidaInventario("Subpartida", idSubpartida, descSubpartida, "SinEstatus", "", "");

            String ubicP = String.valueOf(Integer.parseInt(idPartida)/100);
            char ubicS = (char) (65 + (Integer.parseInt(idSubpartida)/100)/10);
            String ubicC = String.valueOf((Integer.parseInt(idCat)-Integer.parseInt(idSubpartida)));
            this.getItems().put(idPartida, partida);
            partida.getItems().put(idSubpartida, subpartida);

            CategoriaInventario cat = new CategoriaInventario("Categoria", idCat, descCat, "SinEstatus", cantidad, "");
            this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().put(idCat, cat);

            for(int i=0; i<Integer.parseInt(cantidad); i++)
            {
                String ubic = ubicP+ubicS+ubicC+String.valueOf(i);
                String idItem = idCat+String.valueOf(i);
                item = new InfoItem("Item", idItem, descCat, "SinEstatus", "1", ubic);
                item.setLote(lote);
                this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().get(idCat).getItems().put(idItem, item);
            }
        }

        return item;
    }


    public void serializa() throws IOException {
        //aqui a mano cambio el pathname antes era "ReporteNivelInventario-6.json"
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("ReporteNivelInventario-9.json"), this);
    }

    public List<InfoItem> busca(int id, String name, String descCat, String cantidad, String idPartida, String idSubpartida, String idCat)
    {
        if(this.getItems().get(idPartida) != null)
        {
            if (this.getItems().get(idPartida).getItems().get(idSubpartida) != null)
            {
                if (this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().get(idCat) != null)
                {
                    CategoriaInventario cat = (CategoriaInventario) this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().get(idCat);

                    if(Integer.parseInt(cat.getCantidad()) >= Integer.parseInt(cantidad))
                    {
                        return cat.getItems(cantidad);
                    }
                }
            }
        }
        return null;
    }

    public void print()
    {
        if (this.getItems() != null)
        {
            System.out.println("----- Items List -----");
            for (Map.Entry<String, InfoItem> item : getItems().entrySet())
            {
                PartidaInventario nodo = (PartidaInventario) item.getValue();
                nodo.print();
            }
        }
    }
    public InfoItem actualizaNivelInventario(String idPartida, String idSubpartida, String idCat, CategoriaInventario cat , String descCat, String cantidadSEM, String cantidadNivelInventario, Lote lote) throws IOException {
        //todo desde aqui declaro el info item que antes declaraba en el for
        InfoItem item = null;

        if (this.getItems().get(idPartida) != null) {
            if (this.getItems().get(idPartida).getItems().get(idSubpartida) != null) {
                if (this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().get(idCat) != null) {

                    String ubicP = String.valueOf(Integer.parseInt(idPartida)/100);
                    char ubicS = (char) (65 + (Integer.parseInt(idSubpartida)/100)/10);
                    String ubicC = String.valueOf((Integer.parseInt(idCat)-Integer.parseInt(idSubpartida)));
                    this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().put(idCat, cat);

                    int total = Integer.parseInt(cantidadSEM) + Integer.parseInt(cantidadNivelInventario);
                    // todo crea las 5 en base a la cantidad
                    for (int i = 0; i < total; i++) {
                        // todo aqui pegan partida+subpartida+categoria que esto es  2A3+(i), i ira incrementado segun el for en mesa son 5 objetos asi que llegara hasta 4, siendo 2A34
                        String ubic = ubicP+ubicS+ubicC+String.valueOf(i);
                        //todo el id de la categoria se ocupa para darle el idItem +(i)
                       String idItem = idCat + String.valueOf(i);
                        /*
                        el primer valor del constructor es clase el cual se le asigna el nombre de item,se coloca el id del item, seguido de la descripcion, estatus esta sin estatus
                        porque aun en este paso no se aparta ni nada, cantidad se asignamo como  y al final se agrega la ubicacion. ya que va contando de uno en uno
                        hasta que sale de ciclo for segun la cantidad de items que agregaste en la firma del metodo
                        al final en item.setLote se agrega el lote el cual en principal se ve que se mete como nulo.
                         */
                        item = new InfoItem("Item", idItem, descCat, "SinEstatus", "1", ubic);
                        item.setLote(lote);
                        //recorre el mapa y bajamos hasta categoria obteniendo los items y agregando el material al Nivelinventario
                        this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().get(idCat).getItems().put(idItem, item);
                    }
                    this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().get(idCat).setCantidad(String.valueOf(total));

                }

            }


        }
        return item;
    }


}
