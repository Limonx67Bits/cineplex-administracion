package main.java.com.pbcorporations.cineplex.administracion.model.dao.impl;

import main.java.com.pbcorporations.cineplex.administracion.config.DBConnection;
import main.java.com.pbcorporations.cineplex.administracion.model.pojo.Pelicula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import main.java.com.pbcorporations.cineplex.administracion.model.dto.response.PeliculaDTOResponse;
import main.java.com.pbcorporations.cineplex.administracion.model.dao.interf.CrudInterface;

public class PeliculaDAO implements CrudInterface<Pelicula, PeliculaDTOResponse> {
    @Override
    public boolean register(Pelicula pelicula){
        String sql = "insert into peliculas values (?, ?, ?, ?, ?, ? ,?)";
        
        try(Connection conn = DBConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, pelicula.getIdPelicula());
            pstm.setString(2, pelicula.getTituloPelicula());
            pstm.setInt(3, pelicula.getDuracionMinutos());
            pstm.setString(4, pelicula.getUrlPoster());
            pstm.setInt(5, pelicula.getIdClasificacion());
            pstm.setInt(6, pelicula.getIdGenero());
            pstm.setInt(7, pelicula.getIdDirector());
            
            return pstm.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean update(Pelicula pelicula){
        String sql = "update peliculas set titulo_pelicula = ?,"
                + " duracion_minutos = ?,"
                + " url_poster = ?, "
                + " id_clasificacion = ?, "
                + " id_genero = ?, "
                + " id_director = ?"
                + " where id_pelicula = ?";
        
        try(Connection conn = DBConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, pelicula.getTituloPelicula());
            pstm.setInt(2, pelicula.getDuracionMinutos());
            pstm.setString(3, pelicula.getUrlPoster());
            pstm.setInt(4, pelicula.getIdClasificacion());
            pstm.setInt(5, pelicula.getIdGenero());
            pstm.setInt(6, pelicula.getIdDirector());
            pstm.setString(7, pelicula.getIdPelicula());
            
            return pstm.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean delete(Pelicula pelicula){
        String sql = "delete from peliculas where id_pelicula = ?";
        
        try(Connection conn = DBConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, pelicula.getIdPelicula());
            
            return pstm.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public List<PeliculaDTOResponse> getAll(){ //obtener la cartelera
        List<PeliculaDTOResponse> lista = new ArrayList<>();
        String sql = "select p.id_pelicula, p.titulo_pelicula, p.duracion_minutos, p.url_poster, " +
                "g.nombre_genero as genero, " +
                "CONCAT(c.codigo, ' - ', c.descripcion) as clasificacion, " +
                "CONCAT(d.nombre, ' ', d.apellido) as director " +
                "from pelicula as p " +
                "inner join generos as g on p.id_genero = g.id_genero " +
                "inner join clasificaciones as c on p.id_clasificacion = c.id_clasificacion " +
                "inner join directores as d on p.id_director = d.id_director ";
        
        try(Connection conn = DBConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql);
                ResultSet rs = pstm.executeQuery()){
            while(rs.next()){
                lista.add(new PeliculaDTOResponse(
                rs.getString("id_pelicula"),
                rs.getString("titulo_pelicula"),
                rs.getInt("duracion_minutos"),
                rs.getString("url_poster"),
                rs.getString("genero"),
                rs.getString("clasificacion"),
                rs.getString("director")
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    
    @Override
    public boolean exist(Pelicula pelicula){
        String sql = "select count(*) from peliculas where lower(titulo_pelicula) = lower(?)";
        
        try(Connection conn = DBConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, pelicula.getTituloPelicula());
            
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
