/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
import Services.ServiceProduit;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import util.MyDB;

/**
 * FXML Controller class
 *
 * @author 21692
 */
public class FrontController implements Initializable {

    @FXML
    private HBox cardlayoout;
    private List<Produit> recentlyadd;
    Connection cnx;
    Statement stmt;

    public FrontController() {
        cnx = MyDB.getInstance().getCnx();}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ServiceProduit sm = new ServiceProduit();

        recentlyadd = new ArrayList<>(sm.afficherProduit());
        try {
            for (Produit value : recentlyadd) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(value);
                cardlayoout.getChildren().add(cardBox);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO
    }    
    
}
