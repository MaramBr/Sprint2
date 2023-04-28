/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Transporteur;
import Services.ServiceTransporteur;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import util.MyDB;

/**
 * FXML Controller class
 *
 * @author 21692
 */
public class FrontTController implements Initializable {

private List<Transporteur> recentlyadd;
    Connection cnx;
    Statement stmt;
    @FXML
    private Pagination pagination;
    private int rowsPerPage = 2;
private int pageCount;

    public FrontTController() {
        cnx = MyDB.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    @Override
public void initialize(URL url, ResourceBundle rb) {
          
     ServiceTransporteur sm = new ServiceTransporteur();
    recentlyadd = new ArrayList<>(sm.afficherTransporteur());
    pageCount = (int) Math.ceil((double) recentlyadd.size() / rowsPerPage);
    pagination.setPageCount(pageCount);
    pagination.setPageFactory(this::createPage);
   
            
     
      }
     
 private Region createPage(int pageIndex) {
    int startIndex = pageIndex * rowsPerPage;
    int endIndex = Math.min(startIndex + rowsPerPage, recentlyadd.size());
    List<Transporteur> pageProducts = recentlyadd.subList(startIndex, endIndex);

    HBox pageContainer = new HBox(); // wrap VBox in HBox
    pageContainer.setSpacing(20.0);
    pageContainer.setAlignment(Pos.CENTER); // set alignment to CENTER

    try {
        for (Transporteur value : pageProducts) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Cardt.fxml"));
            HBox cardtBox = fxmlLoader.load();
            CardTController cardtController = fxmlLoader.getController();
            cardtController.setData(value);
            pageContainer.getChildren().add(cardtBox);
            
            
     
            
            
            
            
            
            
            
            
            
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return pageContainer;
}
 
 
 
 

      
    }

