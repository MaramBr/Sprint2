/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.ProductRating;
import Entities.Produit;
import util.MyDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author msi
 */
public class ServiceRating {
      Statement Ste;
    Connection cnx ;
    public ServiceRating(){
        cnx = MyDB.getInstance().getCnx();
    }
    
    
     public void ajouterRating(int productId, String userName, int rating) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");
            String query = "INSERT INTO ratings (produit_id, user_name, rating) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, productId);
            stmt.setString(2, userName);
            stmt.setInt(3, rating);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}