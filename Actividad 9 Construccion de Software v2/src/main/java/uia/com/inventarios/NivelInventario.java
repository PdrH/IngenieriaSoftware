package uia.com.inventarios;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class NivelInventario extends PartidaInventario {

    //private HashMap<String, HashMap<String, PartidaInventario>> reporteNIvelInventario = new HashMap<String, HashMap<String, PartidaInventario>>();


    public NivelInventario() {
        super();
    }

    public void agrega(String idPartida, String descPartida, String idSubpartida, String descSubpartida, String idCat, String descCat, String cantidad) {/*
    en esta parte va bajando en el arreglo de partida subpartida y categoria
    */
        if (this.getItems().get(idPartida) != null) {
            if (this.getItems().get(idPartida).getItems().get(idSubpartida) != null) {
                if (this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().get(idCat) == null) {/*

                lo que hace el valor char ubicS : un valor de subpartida 110 dividelo entre 100 despues entre 10 y sumale 65,
                esto es porque en la tabla ACSII 65 es la letra A mayuscula entonces cuando haces toda esa operacion terminas sumando 0.11+ 65
                que es la letra A. Cuando la diferencia el valor de la operacion de la subpartida sea igual a 1 entonces 65+1 dara la letra B y
                asi sucesivamente. Esto lo puedes comprobar en ReporteNivelInventario-6.

                ubicC = un valor como 112(categoria)-110(subpartida) sera igual a dos

                */
                    String ubicP = String.valueOf(Integer.parseInt(idPartida) / 100);
                    char ubicS = (char) (65 + (Integer.parseInt(idSubpartida) / 100) / 10);
                    String ubicC = String.valueOf((Integer.parseInt(idCat) - Integer.parseInt(idSubpartida)));
                    CategoriaInventario cat = new CategoriaInventario(idCat, descCat, "SinEstatus", cantidad, "SinPosicion");
                    this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().put(idCat, cat);
/*
para esta parte ubic= 1A20 el ultimo digito ira aumentando segun la  i del for
idItem tomara el valor del idcategoria y le sumara un digito que es el que corresponde a la i del for
entonces genera el valor idtem dentro de nuestro for para crear un objeto infoItem que contiene los elementos
de la firma de este metodo, aqui se asigna automaticamente el SinEstatus, "1" ese no sirve de nada porque no se
asigna en nada en el constructor y al final la ubicacion de nuestro objeto
 */
                    for (int i = 0; i < Integer.parseInt(cantidad); i++) {
                        String ubic = ubicP + ubicS + ubicC + String.valueOf(i);
                        String idItem = idCat + String.valueOf(i);
                        InfoItem item = new InfoItem(idItem, descCat, "SinEstatus", "1", ubic);
                        //en este this se recorre el arreglo de items hasta llegar a categoria y se agrega el idItem de nuestro for y el item de InfoItem
                        this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().get(idCat).getItems().put(idItem, item);
                    }
                }/*
                este else es por si la subpartida estaba vacia la crea
                */
            } else {
                SubpartidaInventario subpartida = new SubpartidaInventario(idSubpartida, descSubpartida, "SinEstatus");
                this.getItems().get(idPartida).getItems().put(idPartida, subpartida);
            }//este else es por si el arreglo es nulo completamente lo crea
        } else {
            PartidaInventario partida = new PartidaInventario(idPartida, descPartida, "SinEstatus");
            SubpartidaInventario subpartida = new SubpartidaInventario(idSubpartida, descSubpartida, "SinEstatus");

            String ubicP = String.valueOf(Integer.parseInt(idPartida) / 100);
            char ubicS = (char) (65 + (Integer.parseInt(idSubpartida) / 100) / 10);
            String ubicC = String.valueOf((Integer.parseInt(idCat) - Integer.parseInt(idSubpartida)));
            this.getItems().put(idPartida, partida);
            partida.getItems().put(idSubpartida, subpartida);

            CategoriaInventario cat = new CategoriaInventario(idCat, descCat, "SinEstatus", cantidad, "SinPosicion");
            this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().put(idCat, cat);

            for (int i = 0; i < Integer.parseInt(cantidad); i++) {
                String ubic = ubicP + ubicS + ubicC + String.valueOf(i);
                String idItem = idCat + String.valueOf(i);
                InfoItem item = new InfoItem(idItem, descCat, "SinEstatus", "1", ubic);
                this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().get(idCat).getItems().put(idItem, item);
            }
        }
    }

    public void serializa() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("ReporteNivelInventario-6.json"), this);
    }

    public List<InfoItem> busca(int id, String name, String descCat, String cantidad, String idPartida, String idSubpartida, String idCat) {
        if (this.getItems().get(idPartida) != null) {
            if (this.getItems().get(idPartida).getItems().get(idSubpartida) != null) {
                if (this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().get(idCat) != null) {
                    //Categoriainventario hereda de InfoItem por lo que tambien tiene un arreglo de item y se puede castear
                    //aqui se guarda la referencia de lo que sea que estamos buscando en cat
                    CategoriaInventario cat = (CategoriaInventario) this.getItems().get(idPartida).getItems().get(idSubpartida).getItems().get(idCat);
                    //preguntamos  si lo que esta guardado en cat casteado en entero y compara con la cantidad que estamos buscando
                    //si no tenemosla cantidad suficiente retornara nulo
                    if (Integer.parseInt(cat.getCantidad()) >= Integer.parseInt(cantidad)) {
                        return cat.getItems(cantidad);
                    }
                }
            }
        }
        return null;
    }
}
