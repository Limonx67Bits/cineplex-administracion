package main.java.com.pbcorporations.cineplex.administracion.model.dto.request;

public class PeliculaDTORequest {
    private String tituloPelicula;
    private int duracionMinutos;
    private String urlPoster;
    private int idClasificacion;
    private int idGenero;
    private int idDirector;

    public PeliculaDTORequest(String tituloPelicula, int duracionMinutos, String urlPoster, int idClasificacion, int idGenero, int idDirector) {
        this.tituloPelicula = tituloPelicula;
        this.duracionMinutos = duracionMinutos;
        this.urlPoster = urlPoster;
        this.idClasificacion = idClasificacion;
        this.idGenero = idGenero;
        this.idDirector = idDirector;
    }

    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public int getIdClasificacion() {
        return idClasificacion;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public int getIdDirector() {
        return idDirector;
    }
}
