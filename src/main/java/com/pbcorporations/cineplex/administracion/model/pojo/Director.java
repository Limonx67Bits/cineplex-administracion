package main.java.com.pbcorporations.cineplex.administracion.model.pojo;

public class Director {
    private int idDirector;
    private String nombre;
    private String apellido;

    public Director(int idDirector, String nombre, String apellido) {
        this.idDirector = idDirector;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    public Director(){
    }

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
