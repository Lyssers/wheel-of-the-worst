package com.lyssers.wheelofmisfortune;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClass extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        //Show GUI
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setResizable(false);
        stage.setTitle("Wheel Of Misfortune");
        stage.setScene(scene);
        stage.show();
    }
    
    //Ignored in IDEs with proper JavaFX support.
    public static void main(String[] args) {
        launch(args);
    }

}
