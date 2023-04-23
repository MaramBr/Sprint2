/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Coaching;
import Services.ServiceCoaching;
import Utils.MyDB;
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
import javafx.geometry.Pos;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Wahch
 */
public class FrontController implements Initializable {

    @FXML
    private HBox cardlayoout;
    private List<Coaching> recentlyadd;
    Connection cnx;
    Statement stmt;
    @FXML
    private Pagination pagination;
    private int rowsPerPage = 2;
private int pageCount;

    public FrontController() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
   public void initialize(URL url, ResourceBundle rb) {
    ServiceCoaching sm = new ServiceCoaching();
    recentlyadd = new ArrayList<>(sm.afficher());
    pageCount = (int) Math.ceil((double) recentlyadd.size() / rowsPerPage);
    pagination.setPageCount(pageCount);
    pagination.setPageFactory(this::createPage);
}

private Region createPage(int pageIndex) {
    int startIndex = pageIndex * rowsPerPage;
    int endIndex = Math.min(startIndex + rowsPerPage, recentlyadd.size());
    List<Coaching> pageProducts = recentlyadd.subList(startIndex, endIndex);

    VBox pageContainer = new VBox();
    pageContainer.setSpacing(20.0);
    pageContainer.setAlignment(Pos.CENTER);

    try {
        for (Coaching value : pageProducts) {
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