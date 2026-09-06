package main.java.com.pbcorporations.cineplex.administracion.model.pojo;
import java.time.LocalDateTime;

public class Usuario {
    private String idUsuario;
    private String username;
    private String passwordHash;
    private LocalDateTime fechaRegistro;
    private int idRol;

    public Usuario() {
    }

    public Usuario(String idUsuario, String username, String passwordHash, LocalDateTime fechaRegistro, int idRol) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.passwordHash = passwordHash;
        this.fechaRegistro = fechaRegistro;
        this.idRol = idRol;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password_hash) {
        this.passwordHash = password_hash;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
}
