package main.java.com.pbcorporations.cineplex.administracion.model.dto.request;

public class RegisterDTORequest {
    private String username;
    private String password;
    private int idRol;
    
    public RegisterDTORequest(){
    }

    public RegisterDTORequest(String username, String password, int idRol) {
        this.username = username;
        this.password = password;
        this.idRol = idRol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
}
