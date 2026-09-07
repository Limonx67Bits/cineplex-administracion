package main.java.com.pbcorporations.cineplex.administracion.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.com.pbcorporations.cineplex.administracion.model.pojo.Usuario;
import main.java.com.pbcorporations.cineplex.administracion.model.service.AuthService;
import main.java.com.pbcorporations.cineplex.administracion.util.SceneManager;

public class LoginController implements Initializable {
    SceneManager manager;
    AuthService service;
    
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField pwField;
    
    public LoginController(AuthService service, SceneManager manager){
        this.service = service;
        this.manager = manager;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void handleLogin(){
        if(txtUsername.getText().trim() == null || txtUsername.getText().isEmpty()
                || pwField.getText().trim() == null || pwField.getText().isEmpty()){
            manager.showAlertInfo("Inicio de Sesión incorrecto", "Iniciando Sesión...", "Revise los campos ingresados antes de iniciar sesión", Alert.AlertType.WARNING);
        }
        
        Usuario usuario = service.auth(txtUsername.getText(), pwField.getText());
        if(usuario != null){
            try{
                manager.showDashboardView();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            manager.showAlertInfo("Inicio de Sesión denegado", "Iniciando Sesión...", "Credenciales incorrectas", Alert.AlertType.ERROR);
        }
    }
    
    public void handleRegisterButton(){
        try{
            manager.showRegisterView();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
