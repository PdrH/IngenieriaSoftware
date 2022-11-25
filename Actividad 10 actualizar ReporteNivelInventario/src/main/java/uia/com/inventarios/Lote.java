package uia.com.inventarios;
/*
esta clase nos da la particularidad de que nos dice en que momento llego el material
Sin embargo veo mucha redundancia al todas estas clases extender de infoUIa, agregan
informacion de items en clases que no lo ocupan. Entiendo que Lote puede tener un id
clase, nombre, cantidad, un estatus tambien porque podemos decir que esta dañado,
retenido por alguna alerta sanitaria o algo similar o una simple devolucion pero
por ejemplo proveedor tendra cosas similares pero tambien tiene atributos que estan
demas como cantidad, si cada proveedor tiene su id, no es necesario tener una cantidad.

Sin embargo para efectos practicos se entiende que de forma rapida se trabaje con herencia.
Composicion sobre herencia
 */
public class Lote extends InfoUIA{

    private String fechaIngreso = "";
    private Proveedor proveedor = new Proveedor();
    private Embalaje embalajePrimario = new Embalaje();
    private Embalaje embalajeSecundrio = new Embalaje();
    private Embalaje embalajeTerciario = new Embalaje();

    public Lote() {
        super();
    }

    public Lote(String idLote, String descLote, String cantidad, String fechaIngreso, String idProv, String descProv)
    {
        super("Lote", idLote, descLote, cantidad, "Ingresado");
        this.fechaIngreso = fechaIngreso;
        proveedor.setName(descLote);
        proveedor.setId(idProv);
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Embalaje getEmbalajePrimario() {
        return embalajePrimario;
    }

    public void setEmbalajePrimario(Embalaje embalajePrimario) {
        this.embalajePrimario = embalajePrimario;
    }

    public Embalaje getEmbalajeSecundrio() {
        return embalajeSecundrio;
    }

    public void setEmbalajeSecundrio(Embalaje embalajeSecundrio) {
        this.embalajeSecundrio = embalajeSecundrio;
    }

    public Embalaje getEmbalajeTerciario() {
        return embalajeTerciario;
    }

    public void setEmbalajeTerciario(Embalaje embalajeTerciario) {
        this.embalajeTerciario = embalajeTerciario;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
