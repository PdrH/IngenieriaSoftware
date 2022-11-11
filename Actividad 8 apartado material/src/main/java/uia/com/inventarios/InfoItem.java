package uia.com.inventarios;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PartidaInventario.class, name = "Partida"),
        @JsonSubTypes.Type(value = SubpartidaInventario.class, name = "Subpartida"),
        @JsonSubTypes.Type(value = CategoriaInventario.class, name = "Categoria"),
        })



public class InfoItem {
    private String id;
    private String name;
    //private List<InfoItem> items = new ArrayList<InfoItem>();
    private HashMap<String, InfoItem> items = new HashMap<String, InfoItem>();
    private String estatus = "sinEstatus";

    public InfoItem(String id, String name, String estatus) {
        this.id = id;
        this.name = name;
        this.estatus = estatus;
    }

    public InfoItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, InfoItem> getItems() {
        return items;
    }

    public void setItems(HashMap<String, InfoItem> items) {
        this.items = items;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }



    public void print()
    {
        if (this.getItems().size() > 0)
        {
            System.out.println("----- Items List -----");

            for (Map.Entry<String, InfoItem> item : this.getItems().entrySet())
            {
                InfoItem nodo =  item.getValue();
                nodo.print();
            }
        }
        else {
            System.out.println(this);
            System.out.println("Id:\t" + this.getId());
            System.out.println("Name:\t" + this.getName());
            System.out.println("Estatus:\t" + this.getEstatus());
        }

    }
}
