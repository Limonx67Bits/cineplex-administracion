package main.java.com.pbcorporations.cineplex.administracion.model.service;

import java.util.List;
import java.util.UUID;
import main.java.com.pbcorporations.cineplex.administracion.model.dao.interf.CrudInterface;
import main.java.com.pbcorporations.cineplex.administracion.model.dto.request.PeliculaDTORequest;
import main.java.com.pbcorporations.cineplex.administracion.model.dto.response.PeliculaDTOResponse;
import main.java.com.pbcorporations.cineplex.administracion.model.pojo.Pelicula;

public class PeliculaService {

    private final CrudInterface peliculaDAO;

    public PeliculaService(CrudInterface peliculaDAO) {
        this.peliculaDAO = peliculaDAO;
    }

    public boolean saveMovie(PeliculaDTORequest request) throws Exception {
        if (peliculaDAO.exist(request.getTituloPelicula())) {
            throw new Exception("El nombre de la pelicula ya se encuentra registrada");
        }

        String idGenerado = UUID.randomUUID().toString();

        Pelicula nuevaPelicula = new Pelicula(
                idGenerado,
                request.getTituloPelicula(),
                request.getDuracionMinutos(),
                request.getUrlPoster(),
                request.getIdClasificacion(),
                request.getIdGenero(),
                request.getIdDirector()
        );

        return peliculaDAO.register(nuevaPelicula);
    }

    public boolean updateMovie(String idPelicula, PeliculaDTORequest request) throws Exception {
        if (idPelicula == null || idPelicula.isEmpty()) {
            throw new Exception("ID de pelicula no valido para actualizar");
        }
        Pelicula peliculaModificada = new Pelicula(
                idPelicula,
                request.getTituloPelicula(),
                request.getDuracionMinutos(),
                request.getUrlPoster(),
                request.getIdGenero(),
                request.getIdClasificacion(),
                request.getIdDirector()
        );
        
        return peliculaDAO.update(peliculaModificada);
    }
    
    public List<PeliculaDTOResponse> getBillboard(){
        return peliculaDAO.getAll();
    }
    
    public boolean deleteMovie(String idPelicula) throws Exception {
        if(idPelicula == null || idPelicula.isEmpty()){
            throw new Exception("Seleccione una pelicula valida para eliminar");
        }
        return peliculaDAO.delete(idPelicula);
    }
}
