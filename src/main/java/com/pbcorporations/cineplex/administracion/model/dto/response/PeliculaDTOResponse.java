package main.java.com.pbcorporations.cineplex.administracion.model.dto.response;

public class PeliculaDTOResponse {
    private String idPelicula;
    private String tituloPelicula;
    private int duracionMinutos;
    private String urlPoster;
    private String descripcion;
    private String nombreGenero;
    private String nombreDirector;

    public PeliculaDTOResponse(String idPelicula, String tituloPelicula, int duracionMinutos, String urlPoster, String descripcion, String nombreGenero, String nombreDirector) {
        this.idPelicula = idPelicula;
        this.tituloPelicula = tituloPelicula;
        this.duracionMinutos = duracionMinutos;
        this.urlPoster = urlPoster;
        this.descripcion = descripcion;
        this.nombreGenero = nombreGenero;
        this.nombreDirector = nombreDirector;
    }

    public String getIdPelicula() {
        return idPelicula;
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

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }
}
