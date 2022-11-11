package uia.com.inventarios;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;


    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = SolicitudSalidaMaterial.class, name = "SSM")
    })
/*
en info inventario se hacen las busquedas del estatus del materia ya sea por partida o subpartida
 */
    public class InfoInventarioUIA {

        private int id;
        private String name;
        private String descripcion;
        private String cantidad;
        private String partida;
        private String subpartida;
        private String categoria;
        private List<InfoInventarioUIA> items = new ArrayList<InfoInventarioUIA>();
        private String estatus = "sinEstatus";
        private String ubicacion = "";


        public InfoInventarioUIA(int id, String name, String descripcion, String cantidad, String partida, String subpartida, String categoria) {
            this.id = id;
            this.name = name;
            this.descripcion = descripcion;
            this.cantidad = cantidad;
            this.partida = partida;
            this.subpartida = subpartida;
            this.categoria = categoria;

        }

        public InfoInventarioUIA(int id, String name, String descripcion, String cantidad, String partida, String subpartida, String categoria, List<InfoInventarioUIA> items, String estatus, String ubicacion) {
            this.id = id;
            this.name = name;
            this.descripcion = descripcion;
            this.cantidad = cantidad;
            this.partida = partida;
            this.subpartida = subpartida;
            this.categoria = categoria;
            this.items = items;
            this.estatus = estatus;
            this.ubicacion = ubicacion;
        }

        public InfoInventarioUIA() {

        }



        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getCantidad() {
            return cantidad;
        }

        public void setCantidad(String cantidad) {
            this.cantidad = cantidad;
        }

        public String getPartida() {
            return partida;
        }

        public void setPartida(String partida) {
            this.partida = partida;
        }

        public String getSubpartida() {
            return subpartida;
        }

        public void setSubpartida(String subpartida) {
            this.subpartida = subpartida;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public List<InfoInventarioUIA> getItems() {
            return items;
        }

        public void setItems(List<InfoInventarioUIA> items) {
            this.items = items;
        }

        public String getEstatus() {
            return estatus;
        }

        public void setEstatus(String estatus) {
            this.estatus = estatus;
        }

        public String getUbicacion() {
            return ubicacion;
        }

        public void setUbicacion(String ubicacion) {
            this.ubicacion = ubicacion;
        }

        @Override
        public String toString() {
            return "InfoInventarioUIA{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", descripcion='" + descripcion + '\'' +
                    ", cantidad='" + cantidad + '\'' +
                    ", partida='" + partida + '\'' +
                    ", subpartida='" + subpartida + '\'' +
                    ", categoria='" + categoria + '\'' +
                    ", items=" + items +
                    ", estatus='" + estatus + '\'' +
                    ", ubicacion='" + ubicacion + '\'' +
                    '}';
        }
    }
