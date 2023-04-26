/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reclamation;
import Services.ServiceReclamation;
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
import javafx.geometry.Pos;
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Majdi
 */
public class ReclamationCardController implements Initializable {

    @FXML
    private Pagination pagination;
    private int rowsPerPage = 1;
    private int pageCount;
    @FXML
    private HBox cardlayoout;
    private List<Reclamation> reclamation;
    Connection cnx;
    Statement stmt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ServiceReclamation  sr = new ServiceReclamation();
    reclamation = new ArrayList<>(sr.afficher2());
    pageCount = (int) Math.ceil((double) reclamation.size() / rowsPerPage);
    pagination.setPageCount(pageCount);
    pagination.setPageFactory(this::createPage);
    }
private Region createPage(int pageIndex) {
    int startIndex = pageIndex * rowsPerPage;
    int endIndex = Math.min(startIndex + rowsPerPage, reclamation.size());
    List<Reclamation> pageRecalamtion = reclamation.subList(startIndex, endIndex);

    VBox pageContainer = new VBox();
    pageContainer.setSpacing(20.0);
    pageContainer.setAlignment(Pos.CENTER);

    try {
        for (Reclamation value : pageRecalamtion) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
            HBox box = fxmlLoader.load();
            CardController cardController = fxmlLoader.getController();
            cardController.setReclamation(value);
            pageContainer.getChildren().add(box);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return pageContainer;
}
    
    
}
