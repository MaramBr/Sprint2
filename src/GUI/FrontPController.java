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
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import util.MyDB;

/**
 * FXML Controller class
 *
 * @author 21692
 */
public class FrontPController implements Initializable {

private List<Produit> recentlyadd;
    Connection cnx;
    Statement stmt;
    @FXML
    private Pagination pagination;
    private int rowsPerPage = 2;
private int pageCount;

    public FrontPController() {
        cnx = MyDB.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    @Override
public void initialize(URL url, ResourceBundle rb) {
          
     ServiceProduit sm = new ServiceProduit();
    recentlyadd = new ArrayList<>(sm.afficherProduit());
    pageCount = (int) Math.ceil((double) recentlyadd.size() / rowsPerPage);
    pagination.setPageCount(pageCount);
    pagination.setPageFactory(this::createPage);
     
      }
     
      
      private Region createPage(int pageIndex) {
    int startIndex = pageIndex * rowsPerPage;
    int endIndex = Math.min(startIndex + rowsPerPage, recentlyadd.size());
    List<Produit> pageProducts = recentlyadd.subList(startIndex, endIndex);

    VBox pageContainer = new VBox();
    pageContainer.setSpacing(20.0);

    try {
        for (Produit value : pageProducts) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
            HBox cardBox = fxmlLoader.load();
            CardController cardController = fxmlLoader.getController();
            cardController.setData(value);
            pageContainer.getChildren().add(cardBox);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return pageContainer;
}
      
    }

