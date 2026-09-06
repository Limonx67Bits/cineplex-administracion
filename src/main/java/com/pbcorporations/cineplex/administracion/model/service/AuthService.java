package main.java.com.pbcorporations.cineplex.administracion.model.service;

import java.time.LocalDateTime;
import java.util.UUID;
import main.java.com.pbcorporations.abarroteria.kinal.security.jbcrypt.BCrypt;
import main.java.com.pbcorporations.cineplex.administracion.model.dao.impl.UsuarioDAO;
import main.java.com.pbcorporations.cineplex.administracion.model.dao.interf.UsuarioInterface;
import main.java.com.pbcorporations.cineplex.administracion.model.dto.request.RegisterDTORequest;
import main.java.com.pbcorporations.cineplex.administracion.model.pojo.Usuario;

public class AuthService {
    private final UsuarioInterface usuarioDAO = new UsuarioDAO();
    
    public Usuario auth(String username, String passwordIngresada){
        Usuario usuario = usuarioDAO.findByUser(username);
        if(usuario == null){
            return null;
        }
        if (BCrypt.checkpw(passwordIngresada, usuario.getPasswordHash())){
            return usuario;
        }
        return null;
    }
    
    public boolean registerUser(RegisterDTORequest request) throws Exception{
        if (usuarioDAO.userExist(request.getUsername())){
            throw new Exception("El nombre de usuario ya se encuentra registrado");
        }
        
        String idGenerado = UUID.randomUUID().toString();
        String pwHasheada = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());
        
        Usuario nuevoUsuario = new Usuario(
                idGenerado,
                request.getUsername(),
                pwHasheada,
                LocalDateTime.now(),
                request.getIdRol()
        );
        return usuarioDAO.register(nuevoUsuario);
    }
}
