package main.java.com.pbcorporations.cineplex.administracion.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.java.com.pbcorporations.cineplex.administracion.model.dto.request.RegisterDTORequest;
import main.java.com.pbcorporations.cineplex.administracion.model.service.AuthService;
import main.java.com.pbcorporations.cineplex.administracion.util.SceneManager;

public class RegisterController implements Initializable {

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
    @FXML
    private ComboBox<Integer> cbRol;

    public RegisterController(AuthService service, SceneManager manager) {
        this.service = service;
        this.manager = manager;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (cbRol != null) {
            cbRol.getItems().addAll(1, 2);
        }

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
    public void handleRegister(ActionEvent event) {
        if (txtUsername.getText() == null || txtUsername.getText().isEmpty()
                || pwField.getText() == null || pwField.getText().isEmpty()
                || cbRol.getValue() == null) {
            manager.showAlertInfo("Registro invalido", "Registrando...", "Revise los campos ingresados antes de registrarse", Alert.AlertType.WARNING);
            return;
        }

        try {
            RegisterDTORequest request = new RegisterDTORequest(txtUsername.getText(), pwField.getText(), cbRol.getValue());
            boolean registrado = service.registerUser(request);

            if (registrado) {
                manager.showAlertInfo("Registro completado!", "Registrando...", "Su usuario fue registrado en la base de datos con exito", Alert.AlertType.INFORMATION);
                handleReturnLogin(event);
            }
        } catch (Exception e) {
            manager.showAlertInfo("Registro denegado", "Registrando...", "El usuario ingresado ya existe dentro de la base de datos", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleReturnLogin(ActionEvent event) {
        try {
            manager.showLoginView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
