/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<<< HEAD:src/userjava/mainmaram.java
package userjava;
========
package pi;
>>>>>>>> origin/IhebTransport:src/pi/NewFXMain.java

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 *
 * @author 21692
 */
public class mainmaram extends Application {
    
    @Override
    public void start(Stage primaryStage) {
<<<<<<<< HEAD:src/userjava/mainmaram.java
         try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/Menu.fxml"));
========
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/MenuTransporteur.fxml"));
>>>>>>>> origin/IhebTransport:src/pi/NewFXMain.java
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex);        }
     
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
