package main.java.com.pbcorporations.cineplex.administracion.model.dto.request;

public class DirectorDTORequest {
    private String nombreDirector;
    private String apellidoDirector;
    
    public DirectorDTORequest(){
        
    }
    
    public DirectorDTORequest(String nombreDirector, String apellidoDirector){
        this.nombreDirector = nombreDirector;
        this.apellidoDirector = apellidoDirector;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }
    
    public String getApellidoDirector(){
        return apellidoDirector;
    }
    
    public void setApellidoDirector(String apellidoDirector){
        this.apellidoDirector = apellidoDirector;
    }
}
