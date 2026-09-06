package main.java.com.pbcorporations.cineplex.administracion.model.dao.interf;

import java.util.List;

public interface CrudInterface<T, ID> {
    boolean create(T entity);
    boolean update(T entity);
    boolean eliminar(ID id);
    T findById(ID id);
    List<T> findAll();
}
