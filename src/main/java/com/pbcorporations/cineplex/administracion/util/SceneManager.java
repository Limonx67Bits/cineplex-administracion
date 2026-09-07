package main.java.com.pbcorporations.cineplex.administracion.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.java.com.pbcorporations.cineplex.administracion.controller.LoginController;
import main.java.com.pbcorporations.cineplex.administracion.model.dao.impl.UsuarioDAO;
import main.java.com.pbcorporations.cineplex.administracion.model.dao.interf.UsuarioInterface;
import main.java.com.pbcorporations.cineplex.administracion.model.service.AuthService;

public class SceneManager {
    private final Stage stage;
    private final String FXML_PATH = "/main/resources/view/";
    
    public SceneManager(Stage stage){
        this.stage = stage;
    }
    
    public void showLoginView() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "login-view.fxml"));
        
        loader.setControllerFactory(clazz -> {
            if (clazz == LoginController.class){
                    UsuarioInterface usuarioDAO = new UsuarioDAO();
                    AuthService service = new AuthService(usuarioDAO);
                    return new LoginController(service, this);
            }
            try{
                return clazz.getDeclaredConstructor().newInstance();
            }catch (Exception e){
                throw new RuntimeException("Error al cargar el controlador: " + e.getMessage());
            }
        });
        
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 500);
        stage.setMinHeight(400);
        stage.setMinWidth(450);
        stage.setTitle("CinePlex - Iniciar Sesión");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    
    public void showRegisterView(){
        
    }
    
    public void showDashboardView(){
        
    }
    
    public void showAlertInfo(String head, String title, String content, AlertType type) {
        Alert alert = new Alert(type);
        alert.initOwner(this.stage);
        alert.setHeaderText(head);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
