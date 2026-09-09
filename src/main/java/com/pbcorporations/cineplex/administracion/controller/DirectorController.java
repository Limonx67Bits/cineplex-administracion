package main.java.com.pbcorporations.cineplex.administracion.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.com.pbcorporations.cineplex.administracion.model.dto.request.DirectorDTORequest;
import main.java.com.pbcorporations.cineplex.administracion.model.pojo.Director;
import main.java.com.pbcorporations.cineplex.administracion.model.service.DirectorService;
import main.java.com.pbcorporations.cineplex.administracion.util.SceneManager;

public class DirectorController implements Initializable {

    private final DirectorService directorService;
    private final SceneManager manager;

    @FXML
    private TextField txtNombreDirector;
    @FXML
    private TextField txtApellidoDirector;

    @FXML
    private TableView<Director> tableDirectores;
    @FXML
    private TableColumn<Director, Integer> columnId;
    @FXML
    private TableColumn<Director, String> columnNombre;
    @FXML
    private TableColumn<Director, String> columnApellido;

    private Director directorSeleccionado;

    public DirectorController(DirectorService directorService, SceneManager manager) {
        this.directorService = directorService;
        this.manager = manager;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnId.setCellValueFactory(new PropertyValueFactory<>("idDirector"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableDirectores.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                directorSeleccionado = newSelection;
                txtNombreDirector.setText(newSelection.getNombre());
                txtApellidoDirector.setText(newSelection.getApellido());
            }
        });

        loadDirectores();
    }

    @FXML
    public void handleUpdateDirector(ActionEvent event) {
        if (directorSeleccionado == null) {
            manager.showAlertInfo("Aviso", "Selección requerida", "Seleccione un director de la tabla para editar", Alert.AlertType.WARNING);
            return;
        }
        try {
            DirectorDTORequest dto = new DirectorDTORequest(txtNombreDirector.getText(), txtApellidoDirector.getText());

            if (directorService.updateDirector(directorSeleccionado.getIdDirector(), dto)) {
                manager.showAlertInfo("Éxito", "Director actualizado", "Los cambios se guardaron con éxito", Alert.AlertType.INFORMATION);
                cleanForm();
                loadDirectores();
            }
        } catch (Exception e) {
            manager.showAlertInfo("Validación / Error", "Atención", e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void handleSaveDirector(ActionEvent event) {
        try {
            DirectorDTORequest dto = new DirectorDTORequest(txtNombreDirector.getText(), txtApellidoDirector.getText());

            if (directorService.saveDirector(dto)) { 
                manager.showAlertInfo("Éxito", "Director guardado", "El director ha sido registrado correctamente", Alert.AlertType.INFORMATION);
                cleanForm();
                loadDirectores();
            }
        } catch (Exception e) {
            manager.showAlertInfo("Validación / Error", "Atención", e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void handleRemoveDirector(ActionEvent event) {
        if (directorSeleccionado == null) {
            manager.showAlertInfo("Aviso", "Selección requerida", "Seleccione un director de la tabla para eliminar", Alert.AlertType.WARNING);
            return;
        }
        try {
            if (directorService.deleteDirector(directorSeleccionado.getIdDirector())) {
                manager.showAlertInfo("Éxito", "Eliminado", "El director fue eliminado correctamente", Alert.AlertType.INFORMATION);
                cleanForm();
                loadDirectores();
            }
        } catch (Exception e) {
            manager.showAlertInfo("Error", "Error al eliminar", "No se puede eliminar el director: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void loadDirectores() {
        tableDirectores.setItems(FXCollections.observableArrayList(directorService.getDirectorList()));
    }

    private void cleanForm() {
        txtNombreDirector.clear();
        txtApellidoDirector.clear();
        directorSeleccionado = null;
        tableDirectores.getSelectionModel().clearSelection();
    }
}
