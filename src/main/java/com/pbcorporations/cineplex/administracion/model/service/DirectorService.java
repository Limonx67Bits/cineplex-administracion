package main.java.com.pbcorporations.cineplex.administracion.model.service;

import java.util.List;
import main.java.com.pbcorporations.cineplex.administracion.model.dao.interf.CrudInterface;
import main.java.com.pbcorporations.cineplex.administracion.model.dto.request.DirectorDTORequest;
import main.java.com.pbcorporations.cineplex.administracion.model.pojo.Director;

public class DirectorService {

    private final CrudInterface directorDAO;

    public DirectorService(CrudInterface directorDAO) {
        this.directorDAO = directorDAO;
    }

    public boolean saveDirector(DirectorDTORequest request) throws Exception {
        if (request.getNombreDirector() == null || request.getNombreDirector().isEmpty()
                || request.getApellidoDirector() == null || request.getApellidoDirector().isEmpty()) {
            throw new Exception("El nombre y apellido del director no pueden estar vacios");
        }
        String nombreLimpio = request.getNombreDirector().strip();
        String apellidoLimpio = request.getApellidoDirector().strip();

        Director directorTemporal = new Director(0, nombreLimpio, apellidoLimpio);

        if (directorDAO.exist(directorTemporal)) {
            throw new IllegalArgumentException("Ya existe un director registrado con ese nombre.");
        }

        return directorDAO.register(directorTemporal);
    }
    
    public List<Director> getDirectorList(){
        return directorDAO.getAll();
    }
    
        public boolean updateDirector(int id, DirectorDTORequest request) throws Exception {
        if (id <= 0) {
            throw new Exception("ID de director inválido.");
        }
        if (request.getNombreDirector()== null || request.getNombreDirector().isBlank()) {
            throw new Exception("El nombre no puede estar vacío.");
        }
        if (request.getApellidoDirector()== null || request.getApellidoDirector().isBlank()) {
            throw new Exception("El apellido no puede estar vacío.");
        }

        Director directorModificado = new Director(
            id, 
            request.getNombreDirector().strip(), 
            request.getApellidoDirector().strip()
        );

        return directorDAO.update(directorModificado);
    }

    public boolean deleteDirector(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para eliminar.");
        }
        
        Director directorAEliminar = new Director();
        directorAEliminar.setIdDirector(id);

        return directorDAO.delete(directorAEliminar);
    }
}
