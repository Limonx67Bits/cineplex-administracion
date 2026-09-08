package main.java.com.pbcorporations.cineplex.administracion.model.dao.interf;

import java.util.List;

public interface CrudInterface<T, R> {
    boolean register(T t);
    boolean update(T t);
    boolean delete(T t);
    List<R> getAll();
    boolean exist(T t);
}
