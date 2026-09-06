package main.java.com.pbcorporations.cineplex.administracion.model.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegisterDTOResponse {
    private String idUsuario;
    private String username;
    private String fechaRegistroFormateada;
    private String idRol;

    public RegisterDTOResponse(String idUsuario, String username, String fechaRegistroFormateada, String idRol) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.fechaRegistroFormateada = fechaRegistroFormateada;
        this.idRol = idRol;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public String getFechaRegistroFormateada() {
        return fechaRegistroFormateada;
    }

    public String getIdRol() {
        return idRol;
    }
}
