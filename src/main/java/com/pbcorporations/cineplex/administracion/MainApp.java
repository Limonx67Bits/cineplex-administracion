package main.java.com.pbcorporations.cineplex.administracion;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.com.pbcorporations.cineplex.administracion.util.SceneManager;

public class MainApp extends Application{
    @Override
    public void start(Stage primary){
        try{
            SceneManager manager = new SceneManager(primary);
            manager.showLoginView();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
