package main.java.com.pbcorporations.cineplex.administracion.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.java.com.pbcorporations.cineplex.administracion.controller.DashboardController;
import main.java.com.pbcorporations.cineplex.administracion.controller.DirectorController;
import main.java.com.pbcorporations.cineplex.administracion.controller.LoginController;
import main.java.com.pbcorporations.cineplex.administracion.controller.PeliculaController;
import main.java.com.pbcorporations.cineplex.administracion.controller.RegisterController;
import main.java.com.pbcorporations.cineplex.administracion.model.dao.impl.CatalogoDAO;
import main.java.com.pbcorporations.cineplex.administracion.model.dao.impl.DirectorDAO;
import main.java.com.pbcorporations.cineplex.administracion.model.dao.impl.PeliculaDAO;
import main.java.com.pbcorporations.cineplex.administracion.model.dao.impl.UsuarioDAO;
import main.java.com.pbcorporations.cineplex.administracion.model.dao.interf.UsuarioInterface;
import main.java.com.pbcorporations.cineplex.administracion.model.service.AuthService;
import main.java.com.pbcorporations.cineplex.administracion.model.service.CatalogoService;
import main.java.com.pbcorporations.cineplex.administracion.model.service.DirectorService;
import main.java.com.pbcorporations.cineplex.administracion.model.service.PeliculaService;

public class SceneManager {

    private final Stage stage;
    private final String FXML_PATH = "/main/resources/view/";

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void showLoginView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "login-view.fxml"));

        loader.setControllerFactory(clazz -> {
            if (clazz == LoginController.class) {
                UsuarioInterface usuarioDAO = new UsuarioDAO();
                AuthService service = new AuthService(usuarioDAO);
                return new LoginController(service, this);
            }
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
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

    public void showRegisterView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "register-view.fxml"));

        loader.setControllerFactory(clazz -> {
            if (clazz == RegisterController.class) {
                UsuarioInterface usuarioDAO = new UsuarioDAO();
                AuthService service = new AuthService(usuarioDAO);
                return new RegisterController(service, this);
            }
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Error al cargar el controlador: " + e.getMessage());
            }
        });

        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 500);
        stage.setMinHeight(400);
        stage.setMinWidth(450);
        stage.setTitle("CinePlex - Registrarse");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    public void showDashboardView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "dashboard-view.fxml"));

        loader.setControllerFactory(clazz -> {
            if (clazz == DashboardController.class) {
                return new DashboardController(this);
            }
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Error al cargar el controlador: " + e.getMessage());
            }
        });

        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 500);
        stage.setMinHeight(400);
        stage.setMinWidth(450);
        stage.setTitle("CinePlex - Registrarse");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void showAlertInfo(String head, String title, String content, AlertType type) {
        Alert alert = new Alert(type);
        alert.initOwner(this.stage);
        alert.setHeaderText(head);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public Parent loadPeliculaView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "pelicula-view.fxml"));

        loader.setControllerFactory(clazz -> {
            if (clazz == PeliculaController.class) {
                PeliculaService peliculaService = new PeliculaService(new PeliculaDAO());
                DirectorService directorService = new DirectorService(new DirectorDAO());
                CatalogoService catalogoService = new CatalogoService(new CatalogoDAO());

                return new PeliculaController(peliculaService, directorService, catalogoService, this);
            }
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Error al cargar controlador de Películas: " + e.getMessage());
            }
        });

        return loader.load();
    }

    public Parent loadDirectorView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "director-view.fxml"));

        loader.setControllerFactory(clazz -> {
            if (clazz == DirectorController.class) {
                DirectorService directorService = new DirectorService(new DirectorDAO());

                return new DirectorController(directorService, this);
            }
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Error al cargar controlador de Directores: " + e.getMessage());
            }
        });

        return loader.load();
    }
}
