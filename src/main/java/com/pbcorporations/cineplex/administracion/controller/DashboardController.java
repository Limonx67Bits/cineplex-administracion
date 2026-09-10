package main.java.com.pbcorporations.cineplex.administracion.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.java.com.pbcorporations.cineplex.administracion.util.SceneManager;

public class DashboardController implements Initializable {

    private final SceneManager manager;

    @FXML
    private HBox titleBar;
    @FXML
    private Button btnMinimizar;
    @FXML
    private Button btnCerrar;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private BorderPane panelCentral;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        openPeliculasView(null);

        titleBar.setOnMousePressed(event -> {
            Stage stage = (Stage) titleBar.getScene().getWindow();
            xOffset = stage.getX() - event.getScreenX();
            yOffset = stage.getY() - event.getScreenY();
        });

        titleBar.setOnMouseDragged(event -> {
            Stage stage = (Stage) titleBar.getScene().getWindow();
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
        });

        btnMinimizar.setOnAction(event -> {
            Stage stage = (Stage) btnMinimizar.getScene().getWindow();
            stage.setIconified(true);
        });

        btnCerrar.setOnAction(event -> {
            Stage stage = (Stage) btnCerrar.getScene().getWindow();
            stage.close();
        });

    }

    public DashboardController(SceneManager manager) {
        this.manager = manager;
    }

    @FXML
    private void openPeliculasView(ActionEvent event) {
        try {
            Parent vistaPeliculas = manager.loadPeliculaView();
            panelCentral.setCenter(vistaPeliculas);
        } catch (Exception e) {
            e.printStackTrace();
            manager.showAlertInfo("Error", "Error en el sistema", "No se pudo cargar la vista", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void openDirectoresView(ActionEvent event) {
        try {
            Parent vistaDirectores = manager.loadDirectorView();
            panelCentral.setCenter(vistaDirectores);
        } catch (Exception e) {
            e.printStackTrace();
            manager.showAlertInfo("Error", "Error en el sistema", "No se pudo cargar la vista", Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            manager.showLoginView();
        } catch (Exception e){
            manager.showAlertInfo("Error", "Error en el sistema", "No se ha podido cerrar sesión", Alert.AlertType.ERROR);
        }
    }
}
