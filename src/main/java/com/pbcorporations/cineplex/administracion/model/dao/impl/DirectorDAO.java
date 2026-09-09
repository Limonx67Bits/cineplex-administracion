package main.java.com.pbcorporations.cineplex.administracion.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import main.java.com.pbcorporations.cineplex.administracion.config.DBConnection;
import main.java.com.pbcorporations.cineplex.administracion.model.dao.interf.CrudInterface;
import main.java.com.pbcorporations.cineplex.administracion.model.pojo.Director;

public class DirectorDAO implements CrudInterface<Director, Director> {

    @Override
    public boolean register(Director director) {
        String sql = "insert into directores (nombre, apellido) values(?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, director.getNombre());
            pstm.setString(2, director.getApellido());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Director director) {
        String sql = "update directores set nombre = ?, apellido = ? where id_director = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, director.getNombre());
            pstm.setString(2, director.getApellido());
            pstm.setInt(3, director.getIdDirector());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Director director) {
        String sql = "delete from directores where id_director = ?";
        String sqlMaxId = "select max(id_director) from directores";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, director.getIdDirector());
            int filasAfectadas = pstm.executeUpdate();

            if (filasAfectadas > 0) {
                int maxId = 0;
                try (PreparedStatement pstmMax = conn.prepareStatement(sqlMaxId); ResultSet rs = pstmMax.executeQuery()) {
                    if (rs.next()) {
                        maxId = rs.getInt(1);
                    }
                }
                String sqlReset = "alter table directores auto_increment = " + (maxId + 1);
                try (PreparedStatement pstmReset = conn.prepareStatement(sqlReset)) {
                    pstmReset.executeUpdate();
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return false;
    }

    @Override
    public List<Director> getAll() {
        List<Director> lista = new ArrayList<>();
        String sql = "select * from directores";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql); ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                lista.add(new Director(
                        rs.getInt("id_director"),
                        rs.getString("nombre"),
                        rs.getString("apellido")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean exist(Director director) {
        String sql = "select count(*) from directores where lower(nombre) = lower(?) and lower(apellido) = lower(?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, director.getNombre());
            pstm.setString(2, director.getApellido());

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
