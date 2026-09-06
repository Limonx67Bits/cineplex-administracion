package main.java.com.pbcorporations.cineplex.administracion.model.dao.impl;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import main.java.com.pbcorporations.cineplex.administracion.config.DBConnection;
import main.java.com.pbcorporations.cineplex.administracion.model.dao.interf.UsuarioInterface;
import main.java.com.pbcorporations.cineplex.administracion.model.pojo.Usuario;

public class UsuarioDAO implements UsuarioInterface{
    @Override
    public boolean register(Usuario usuario){
        String sql = "insert into usuarios values(?, ?, ?, ?, ?)";
        try(Connection conn = DBConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, usuario.getIdUsuario());
            pstm.setString(2, usuario.getUsername());
            pstm.setString(3, usuario.getPasswordHash());
            pstm.setObject(4, usuario.getFechaRegistro());
            pstm.setInt(5, usuario.getIdRol());
            
            return pstm.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Usuario findByUser(String username){
        String sql = "select id_usuario, username, password_hash, fecha_registro, id_rol from usuarios where username = ?";
        try(Connection conn = DBConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, username);
            try(ResultSet rs = pstm.executeQuery()){
                if(rs.next()){
                    return new Usuario(
                            rs.getString("id_usuario"),
                            rs.getString("username"),
                            rs.getString("password_hash"),
                            rs.getObject("fecha_registro", LocalDateTime.class),
                            rs.getInt("id_rol")
                    );
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public boolean userExist(String username){
        String sql = "select count(*) from usuarios where username = ?";
        try(Connection conn = DBConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, username);
            try(ResultSet rs = pstm.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1) > 0;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
