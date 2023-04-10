/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Maram
 */
public class MenuFrontController implements Initializable {

    @FXML
    private HBox mainPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pagerdv(MouseEvent event) throws IOException {
         mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("RendezVous.fxml"));
        mainPane.getChildren().setAll(Content);
    }

    @FXML
    private void CoachingPage(MouseEvent event) throws IOException {
         mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("CoachingFront.fxml"));
        mainPane.getChildren().setAll(Content);
    }

    @FXML
    private void BackC(MouseEvent event) {
          try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/Menu.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();}
        catch (IOException ex) {
            System.out.println(ex);        }
        
      
    }
    
}
