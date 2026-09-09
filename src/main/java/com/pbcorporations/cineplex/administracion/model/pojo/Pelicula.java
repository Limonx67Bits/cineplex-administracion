package main.java.com.pbcorporations.cineplex.administracion.model.pojo;

public class Pelicula {
    private String idPelicula;
    private String tituloPelicula;
    private int duracionMinutos;
    private String urlPoster;
    private int idClasificacion;
    private int idGenero;
    private int idDirector;

    public Pelicula(String idPelicula, String tituloPelicula, int duracionMinutos, String urlPoster, int idClasificacion, int idGenero, int idDirector) {
        this.idPelicula = idPelicula;
        this.tituloPelicula = tituloPelicula;
        this.duracionMinutos = duracionMinutos;
        this.urlPoster = urlPoster;
        this.idClasificacion = idClasificacion;
        this.idGenero = idGenero;
        this.idDirector = idDirector;
    }
    
    public Pelicula(){
    }

    public String getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(String idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public int getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(int idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }
}
