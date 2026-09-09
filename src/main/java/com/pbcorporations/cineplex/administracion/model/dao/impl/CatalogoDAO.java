package main.java.com.pbcorporations.cineplex.administracion.model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.com.pbcorporations.cineplex.administracion.config.DBConnection;
import main.java.com.pbcorporations.cineplex.administracion.model.dao.interf.CatalogoInterface;
import main.java.com.pbcorporations.cineplex.administracion.model.pojo.Clasificacion;
import main.java.com.pbcorporations.cineplex.administracion.model.pojo.Genero;

public class CatalogoDAO implements CatalogoInterface {
    @Override
    public List<Genero> getGenres(){
        List<Genero> lista = new ArrayList<>();
        String sql = "select * from  generos";
        
        try(Connection conn = DBConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql);
                ResultSet rs = pstm.executeQuery()){
            
            while(rs.next()){
                lista.add(
                new Genero(
                rs.getInt("id_genero"),
                rs.getString("nombre_genero")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    @Override
    public List<Clasificacion> getRatings(){
        List<Clasificacion> lista = new ArrayList<>();
        String sql = "select * from clasificaciones";
        
        try(Connection conn = DBConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql);
                ResultSet rs = pstm.executeQuery()){
            
            while(rs.next()){
                lista.add(
                new Clasificacion(
                rs.getInt("id_clasificacion"),
                rs.getString("codigo"),
                rs.getString("descripcion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
