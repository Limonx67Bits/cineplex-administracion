package main.java.com.pbcorporations.cineplex.administracion.util;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BaseController {

    private double xOffset = 0;
    private double yOffset = 0;

    protected void setupTitleBar(HBox titleBar, Button btnMinimizar, Button btnCerrar) {
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

        if (btnMinimizar != null) {
            btnMinimizar.setOnAction(event -> {
                Stage stage = (Stage) btnMinimizar.getScene().getWindow();
                stage.setIconified(true);
            });
        }

        if (btnCerrar != null) {
            btnCerrar.setOnAction(event -> {
                Stage stage = (Stage) btnCerrar.getScene().getWindow();
                stage.close();
            });
        }
    }
}