package main.java.com.pbcorporations.cineplex.administracion.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.com.pbcorporations.cineplex.administracion.model.dto.request.PeliculaDTORequest;
import main.java.com.pbcorporations.cineplex.administracion.model.dto.response.PeliculaDTOResponse;
import main.java.com.pbcorporations.cineplex.administracion.model.pojo.Clasificacion;
import main.java.com.pbcorporations.cineplex.administracion.model.pojo.Director;
import main.java.com.pbcorporations.cineplex.administracion.model.pojo.Genero;
import main.java.com.pbcorporations.cineplex.administracion.model.service.CatalogoService;
import main.java.com.pbcorporations.cineplex.administracion.model.service.DirectorService;
import main.java.com.pbcorporations.cineplex.administracion.model.service.PeliculaService;
import main.java.com.pbcorporations.cineplex.administracion.util.SceneManager;

public class PeliculaController implements Initializable {

    private final PeliculaService service;
    private final DirectorService dService;
    private final CatalogoService cService;
    private final SceneManager manager;

    @FXML
    private TextField txtTituloPelicula;
    @FXML
    private TextField txtDuracionMinutos;
    @FXML
    private TextField txtUrlPoster;
    @FXML
    private ComboBox<Clasificacion> cbClasificacion;
    @FXML
    private ComboBox<Genero> cbGenero;
    @FXML
    private ComboBox<Director> cbDirector;

    @FXML
    private TableView<PeliculaDTOResponse> tablePeliculas;
    @FXML
    private TableColumn<PeliculaDTOResponse, String> columnTituloPelicula;
    @FXML
    private TableColumn<PeliculaDTOResponse, Integer> columnDuracionMinutos;
    @FXML
    private TableColumn<PeliculaDTOResponse, String> columnGenero;
    @FXML
    private TableColumn<PeliculaDTOResponse, String> columnClasificacion;
    @FXML
    private TableColumn<PeliculaDTOResponse, String> columnDirector;

    private PeliculaDTOResponse peliculaSeleccionada;

    public PeliculaController(PeliculaService service, DirectorService dService, CatalogoService cService, SceneManager manager) {
        this.service = service;
        this.dService = dService;
        this.cService = cService;
        this.manager = manager;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnTituloPelicula.setCellValueFactory(new PropertyValueFactory<>("tituloPelicula"));
        columnDuracionMinutos.setCellValueFactory(new PropertyValueFactory<>("duracionMinutos"));
        columnGenero.setCellValueFactory(new PropertyValueFactory<>("nombreGenero"));
        columnClasificacion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        columnDirector.setCellValueFactory(new PropertyValueFactory<>("nombreDirector"));

        tablePeliculas.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                peliculaSeleccionada = newSel;
                txtTituloPelicula.setText(newSel.getTituloPelicula());
                txtDuracionMinutos.setText(String.valueOf(newSel.getDuracionMinutos()));
                txtUrlPoster.setText(newSel.getUrlPoster());
            }
        });

        loadCombo();
        loadTablePelicula();
    }

    @FXML
    public void handleSaveMovie(ActionEvent event) {
        try {
            if (txtTituloPelicula.getText() == null || txtTituloPelicula.getText().isEmpty()
                    || txtDuracionMinutos.getText() == null || txtDuracionMinutos.getText().isEmpty()
                    || txtUrlPoster.getText() == null || txtUrlPoster.getText().isEmpty()
                    || cbClasificacion.getValue() == null || cbGenero.getValue() == null
                    || cbDirector.getValue() == null) {
                manager.showAlertInfo("Error en el guardado", "Agregando pelicula...", "Revise los campos insertados", Alert.AlertType.WARNING);
                return;
            }
            int duracion;
            try {
                duracion = Integer.parseInt(txtDuracionMinutos.getText());
                if (duracion <= 0) {
                    manager.showAlertInfo("Error en la duración de la pelicula", "Agregando pelicula...", "Una pelicula no puede durar 0 minutos", Alert.AlertType.WARNING);
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("La duración debe ser un numero entero valido");
            }

            PeliculaDTORequest request = new PeliculaDTORequest(
                    txtTituloPelicula.getText(),
                    duracion,
                    txtUrlPoster.getText(),
                    cbClasificacion.getValue().getIdClasificacion(),
                    cbGenero.getValue().getIdGenero(),
                    cbDirector.getValue().getIdDirector()
            );

            if (service.saveMovie(request)) {
                manager.showAlertInfo("Éxito", "Película guardada", "Película registrada en la cartelera", javafx.scene.control.Alert.AlertType.INFORMATION);
                cleanForm();
                loadTablePelicula();
            } else {
                manager.showAlertInfo("Error", "Error al guardar", "No se pudo registrar la película", javafx.scene.control.Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            manager.showAlertInfo("Error", "Error inesperado", "Ocurrió un problema en el sistema " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleRemoveMovie(ActionEvent event) {
        if (peliculaSeleccionada == null) {
            manager.showAlertInfo("Datos nulos", "Eliminando", "No seleccionó ninguna pelicula para borrar", Alert.AlertType.WARNING);
        }

        try {
            if (service.deleteMovie(peliculaSeleccionada.getIdPelicula())) {
                manager.showAlertInfo("Proceso ejecutado con exito", "Eliminando", "La eliminación fue ejecutada sin errores", Alert.AlertType.INFORMATION);
                cleanForm();
                loadTablePelicula();
            }
        } catch (Exception e) {
            manager.showAlertInfo("Error inesperado", "Atención", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleUpdateMovie(ActionEvent event) {
        try {
            if (peliculaSeleccionada == null) {
                manager.showAlertInfo("Aviso", "Selección requerida", "Por favor, seleccione una película de la tabla para modificar.", Alert.AlertType.WARNING);
                return;
            }

            if (txtTituloPelicula.getText() == null || txtTituloPelicula.getText().isEmpty()
                    || txtDuracionMinutos.getText() == null || txtDuracionMinutos.getText().isEmpty()
                    || txtUrlPoster.getText() == null || txtUrlPoster.getText().isEmpty()
                    || cbClasificacion.getValue() == null || cbGenero.getValue() == null
                    || cbDirector.getValue() == null) {
                manager.showAlertInfo("Error al actualizar", "Modificando película...", "Revise los campos insertados", Alert.AlertType.WARNING);
                return;
            }

            int duracion;
            try {
                duracion = Integer.parseInt(txtDuracionMinutos.getText());
                if (duracion <= 0) {
                    manager.showAlertInfo("Error en la duración", "Modificando película...", "Una película no puede durar 0 o menos minutos", Alert.AlertType.WARNING);
                    return;
                }
            } catch (NumberFormatException e) {
                manager.showAlertInfo("Error en la duración", "Modificando película...", "La duración debe ser un número entero válido", Alert.AlertType.WARNING);
                return;
            }

            PeliculaDTORequest request = new PeliculaDTORequest(
                    txtTituloPelicula.getText(),
                    duracion,
                    txtUrlPoster.getText(),
                    cbClasificacion.getValue().getIdClasificacion(),
                    cbGenero.getValue().getIdGenero(),
                    cbDirector.getValue().getIdDirector()
            );

            if (service.updateMovie(peliculaSeleccionada.getIdPelicula(), request)) {
                manager.showAlertInfo("Éxito", "Película actualizada", "Los cambios se guardaron correctamente en la cartelera", Alert.AlertType.INFORMATION);
                cleanForm();
                loadTablePelicula();
            } else {
                manager.showAlertInfo("Error", "Error al actualizar", "No se pudo actualizar la película en la base de datos", Alert.AlertType.ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
            manager.showAlertInfo("Error", "Error inesperado", "Ocurrió un problema: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    public void loadCombo() {
        cbGenero.setItems(FXCollections.observableArrayList(cService.getGenresList()));
        cbClasificacion.setItems(FXCollections.observableArrayList(cService.getRatingsList()));
        cbDirector.setItems(FXCollections.observableArrayList(dService.getDirectorList()));
    }
    
    private void loadTablePelicula() {
        tablePeliculas.setItems(FXCollections.observableArrayList(service.getBillboard()));
    }
    
    private void cleanForm() {
        txtTituloPelicula.clear();
        txtDuracionMinutos.clear();
        txtUrlPoster.clear();
        cbGenero.getSelectionModel().clearSelection();
        cbClasificacion.getSelectionModel().clearSelection();
        cbDirector.getSelectionModel().clearSelection();
        peliculaSeleccionada = null;
        tablePeliculas.getSelectionModel().clearSelection();
    }
}
