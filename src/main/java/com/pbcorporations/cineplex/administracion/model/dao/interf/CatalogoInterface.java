package main.java.com.pbcorporations.cineplex.administracion.model.dao.interf;

import java.util.List;
import main.java.com.pbcorporations.cineplex.administracion.model.pojo.Clasificacion;
import main.java.com.pbcorporations.cineplex.administracion.model.pojo.Genero;

public interface CatalogoInterface {
    List<Genero> getGenres();
    List<Clasificacion> getRatings();
}
