package main.java.com.pbcorporations.cineplex.administracion.model.service;

import java.util.List;
import main.java.com.pbcorporations.cineplex.administracion.model.dao.interf.CatalogoInterface;
import main.java.com.pbcorporations.cineplex.administracion.model.pojo.Clasificacion;
import main.java.com.pbcorporations.cineplex.administracion.model.pojo.Genero;

public class CatalogoService {
    private final CatalogoInterface catalogoDAO;
    
    public CatalogoService(CatalogoInterface catalogoDAO){
        this.catalogoDAO = catalogoDAO;
    }
    
    public List<Genero> getGenresList(){
        return catalogoDAO.getGenres();
    }
    
    public List<Clasificacion> getRatingsList(){
        return catalogoDAO.getRatings();
    }
}
