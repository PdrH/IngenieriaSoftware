package uia.com.inventarios;

public class PartidaInventario extends InfoItem{
//por alguna razon se lleva la informacion del lote y ubicacion asi como las particularidades de cada item
    public PartidaInventario(String clase, String idPartida, String descPartida, String sinEstatus, String cantidad, String ubic) {
        super(clase, idPartida, descPartida, sinEstatus, cantidad, ubic);
    }

    public PartidaInventario() {
    }
}
