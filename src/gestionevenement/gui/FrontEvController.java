/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvenement.gui;

import gestionEvenement.entities.Evenement;
import gestionEvenement.services.EvenementCRUD;
import gestionEvenement.utils.MyConnection;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class FrontEvController implements Initializable {

    private List<Evenement> recentlyadd;
    Connection cnx;
    Statement stmt;
    @FXML
    private Pagination pagination;
    private int rowsPerPage = 2;
private int pageCount;

    public FrontEvController() {
        cnx = MyConnection.getInstance().getCnx();
    }

    @Override
   public void initialize(URL url, ResourceBundle rb) {
    EvenementCRUD sm = new EvenementCRUD();
    recentlyadd = new ArrayList<>(sm.afficherEvenement());
    pageCount = (int) Math.ceil((double) recentlyadd.size() / rowsPerPage);
    pagination.setPageCount(pageCount);
    pagination.setPageFactory(this::createPage);
}
private Region createPage(int pageIndex) {
    int startIndex = pageIndex * rowsPerPage;
    int endIndex = Math.min(startIndex + rowsPerPage, recentlyadd.size());
    List<Evenement> pageProducts = recentlyadd.subList(startIndex, endIndex);

    HBox pageContainer = new HBox();
    pageContainer.setSpacing(20.0);
    pageContainer.setAlignment(Pos.CENTER);

    try {
        for (int i = 0; i < Math.ceil((double) pageProducts.size() / 2); i++) {
            HBox rowContainer = new HBox();
            rowContainer.setSpacing(20.0);
            rowContainer.setAlignment(Pos.CENTER);
            for (int j = i * 2; j < Math.min(i * 2 + 2, pageProducts.size()); j++) {
                Evenement value = pageProducts.get(j);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("CardEv.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardEvController cardController = fxmlLoader.getController();
                cardController.setData(value);
                rowContainer.getChildren().add(cardBox);
            }
            // Set alignment to center for each row
            HBox.setHgrow(rowContainer, Priority.ALWAYS);
            HBox.setMargin(rowContainer, new Insets(0, 0, 0, (pageContainer.getWidth() - rowContainer.getWidth()) / 2));
            pageContainer.getChildren().add(rowContainer);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return pageContainer;
}

    
}