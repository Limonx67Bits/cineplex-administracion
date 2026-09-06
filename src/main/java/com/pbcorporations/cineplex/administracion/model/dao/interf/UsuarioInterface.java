package main.java.com.pbcorporations.cineplex.administracion.model.dao.interf;

import main.java.com.pbcorporations.cineplex.administracion.model.pojo.Usuario;

public interface UsuarioInterface {
    boolean register(Usuario usuario);
    Usuario findByUser(String username);
    boolean userExist(String username);
}
