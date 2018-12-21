package SceneAB;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GridScene extends Application {
    @Override
    public void start(Stage primaryStage) {
        GridAnim gridAnim = new GridAnim();
        
        Scene scene = new Scene(gridAnim, 595, 595);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Gridman");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
