package main.java.com.pbcorporations.cineplex.administracion.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import main.java.com.pbcorporations.cineplex.administracion.util.SceneManager;

public class DashboardController implements Initializable {
    private final SceneManager manager;
    
    @FXML
    private BorderPane panelCentral;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }
    
    public DashboardController(SceneManager manager){
        this.manager = manager;
    }
    
    @FXML
    private void openPeliculasView(ActionEvent event){
        changeView("pelicula-view.fxml");
    }
    
    @FXML
    private void openDirectoresView(ActionEvent event){
        changeView("director-view.fxml");
    }
    
    private void changeView(String pathFXML){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pathFXML));
            Parent nuevaVista = loader.load();
            
            panelCentral.setCenter(nuevaVista);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Error al cargar la vista: " + pathFXML);
        }
    }
}
