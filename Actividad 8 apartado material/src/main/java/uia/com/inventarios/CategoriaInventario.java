package uia.com.inventarios;

public class CategoriaInventario extends InfoItem{
    String cantidad="";

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public CategoriaInventario(String idCat, String descCat, String sinEstatus, String cantidad) {
        super(idCat, descCat, sinEstatus);
        this.cantidad=cantidad;
    }

    public CategoriaInventario()
    {}
}
