package main.java.com.pbcorporations.cineplex.administracion.model.pojo;

public class Clasificacion {

    private int idClasificacion;
    private String codigo;
    private String descripcion;

    public Clasificacion(int idClasificacion, String codigo, String descripcion) {
        this.idClasificacion = idClasificacion;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(int idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return this.codigo + " - " + this.descripcion; 
    }
}
