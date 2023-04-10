/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Coaching;
import Services.ServiceCoaching;
import Utils.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javax.management.Query;

/**
 * FXML Controller class
 *
 * @author Maram
 */
public class PiechartController implements Initializable {

    @FXML
    private BorderPane me;
        ServiceCoaching sc=new ServiceCoaching();
         Connection cnx ;
  Statement stm;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
                   
  
    
}

    
    @FXML
    private BarChart handleShowBarChart(ActionEvent event) {
         // TODO
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("...");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(" ");
        BarChart barChart = new BarChart(xAxis, yAxis);
     String query = "SELECT COUNT(c.id) FROM Coaching c";
     
     String nb = " COUNT(c.id) FROM Coaching c GROUP BY c.cours";
       
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Nombre des cours");

        dataSeries1.getData().add(new XYChart.Data(query,nb ));
     
        barChart.getData().add(dataSeries1);
        
        me.setCenter((barChart));

        return barChart;

    }

    @FXML
    private BarChart<String, Number> handleShowPieChart(ActionEvent event)   {
      
    CategoryAxis xAxis = new CategoryAxis();
    xAxis.setLabel("Course Type");

    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Number of Cours");

    BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

    try (Connection cnx = MyDB.getInstance().getCnx()) {
        // Count total number of Coachings
        String totalCoachingsQuery = "SELECT COUNT(id) FROM Coaching";
        PreparedStatement ps = cnx.prepareStatement(totalCoachingsQuery);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Long totalCoachings = rs.getLong(1);

        // Query for counts of coachings for each course type
        String countQuery = "SELECT cours, COUNT(id) FROM Coaching GROUP BY cours";
        ps = cnx.prepareStatement(countQuery);
        rs = ps.executeQuery();

        // Calculate the percentages
        Map<String, Integer> counts = new HashMap<>();
        while (rs.next()) {
            String cours = rs.getString(1);
            Long count = rs.getLong(2);
            counts.put(cours, count.intValue());
        }

        // Create the data series
        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("Number of Coachings per Course Type");
        for (String cours : counts.keySet()) {
            dataSeries1.getData().add(new XYChart.Data<>(cours, counts.get(cours)));
        }

        barChart.getData().add(dataSeries1);
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return barChart;
}

  
    }



    
