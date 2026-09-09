package main.java.com.pbcorporations.cineplex.administracion.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import main.java.com.pbcorporations.cineplex.administracion.util.SceneManager;

public class DashboardController implements Initializable {
    private final SceneManager manager;
    
    @FXML
    private BorderPane panelCentral;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        openPeliculasView(null);
    }
    
    public DashboardController(SceneManager manager){
        this.manager = manager;
    }
    
    @FXML
    private void openPeliculasView(ActionEvent event){
        try{
            Parent vistaPeliculas = manager.loadPeliculaView();
            panelCentral.setCenter(vistaPeliculas);
        } catch (Exception e) {
            e.printStackTrace();
            manager.showAlertInfo("Error", "Error en el sistema", "No se pudo cargar la vista", Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void openDirectoresView(ActionEvent event){
        try {
            Parent vistaDirectores = manager.loadDirectorView();
            panelCentral.setCenter(vistaDirectores);
        } catch (Exception e) {
            e.printStackTrace();
            manager.showAlertInfo("Error", "Error en el sistema", "No se pudo cargar la vista", Alert.AlertType.ERROR);
        }
    }
}
