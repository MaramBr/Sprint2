/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvenement.gui;

import gestionEvenement.entities.Evenement;
import gestionEvenement.entities.Sponsor;
import gestionEvenement.services.EvenementCRUD;
import gestionEvenement.services.SponsorCRUD;
import gestionEvenement.utils.MyConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Wahch
 */
public class FrontController implements Initializable {

    @FXML
    private HBox cardlayoout;
    private List<Evenement> recentlyadd;
    Connection cnx;
    Statement stmt;

    public FrontController() {
        cnx = MyConnection.getInstance().getCnx();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EvenementCRUD sm = new EvenementCRUD();

        recentlyadd = new ArrayList<>(sm.afficherEvenement());
        try {
            for (Evenement value : recentlyadd) {
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
    }

}