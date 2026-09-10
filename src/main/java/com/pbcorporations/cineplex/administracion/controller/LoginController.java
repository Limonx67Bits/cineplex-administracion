package main.java.com.pbcorporations.cineplex.administracion.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.java.com.pbcorporations.cineplex.administracion.model.pojo.Usuario;
import main.java.com.pbcorporations.cineplex.administracion.model.service.AuthService;
import main.java.com.pbcorporations.cineplex.administracion.util.SceneManager;

public class LoginController implements Initializable {

    private final SceneManager manager;
    private final AuthService service;

    @FXML
    private HBox titleBar;
    @FXML
    private Button btnMinimizar;
    @FXML
    private Button btnCerrar;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField pwField;

    public LoginController(AuthService service, SceneManager manager) {
        this.service = service;
        this.manager = manager;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

    @FXML
    public void handleLogin(ActionEvent event) {
        if (txtUsername.getText().trim() == null || txtUsername.getText().isEmpty()
                || pwField.getText().trim() == null || pwField.getText().isEmpty()) {
            manager.showAlertInfo("Inicio de Sesión incorrecto", "Iniciando Sesión...", "Revise los campos ingresados antes de iniciar sesión", Alert.AlertType.WARNING);
            return;
        }

        Usuario usuario = service.auth(txtUsername.getText(), pwField.getText());
        if (usuario != null) {
            try {
                manager.showDashboardView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            manager.showAlertInfo("Inicio de Sesión denegado", "Iniciando Sesión...", "Credenciales incorrectas", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleRegisterButton(ActionEvent event) {
        try {
            manager.showRegisterView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
