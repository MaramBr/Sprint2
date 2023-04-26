/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author Majdi
 */
import Entities.Genre;
import Utils.MyDB;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServiceGenre implements IService<Genre>{
      Connection cnx ;
      Statement stm;
    public ServiceGenre() {
        cnx = MyDB.getInstance().getCnx();
    }

    
    @Override
    public void ajouter(Genre t) {
        
        String qry="INSERT INTO `genre`(`libelle`) VALUES ('"+t.getLibelle()+"')";
   try {
            Statement stm = cnx.createStatement();
       
        stm.executeUpdate(qry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }  }

   @Override
    public List<Genre> afficher() {
   List<Genre> genresList = new ArrayList();
        String qry ="SELECT * FROM `genre`";
     try {
         Statement stm = cnx.createStatement();
         ResultSet rs = stm.executeQuery(qry);
         
         while (rs.next()){
             Genre c = new Genre();
                c.setId(rs.getInt(1));
                c.setLibelle(rs.getString("libelle"));
                 genresList.add(c);
         }
                 
         return genresList;
     } catch (SQLException ex) {
         System.out.println(ex.getMessage());
     }
     return genresList;
    }

    @Override
    public boolean modifier(Genre t) {
    
 try {
            String qry = "UPDATE `genre` SET `id`='"+t.getId()+"',`libelle`='"+t.getLibelle()+"' WHERE `id` = '" + t.getId() + "'";
            stm = cnx.createStatement();

            stm.executeUpdate(qry);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } 
    }

    @Override
    public void supprimer(Genre t) {
     
String qry = "DELETE FROM `genre` WHERE id=?";
    try {
        PreparedStatement pstmt = cnx.prepareStatement(qry);
        pstmt.setInt(1, t.getId());
        pstmt.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }     
    
    }

    @Override
    public ObservableList<Genre> afficher2() {
        
           ObservableList<Genre> genresList = FXCollections.observableArrayList();

        try {
            String qry = "SELECT * FROM `genre`";
            stm = cnx.createStatement();
           // PreparedStatement ps= cnx.prepareCall(qry);
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Genre c = new Genre();
                c.setId(rs.getInt("id"));
                c.setLibelle(rs.getString("libelle"));
                 genresList.add(c);
            }
            return genresList;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return genresList;
        
    }
    public List<Genre> afficherGenres() {
    List<Genre> libelles = new ArrayList<>();
    try {
        String req = "SELECT * FROM `genre`";
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Genre c = new Genre();
            c.setId(rs.getInt("id"));
                c.setLibelle(rs.getString("libelle"));
            libelles.add(c);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return libelles;
}
    
    
    
     public List<Genre> rechercher(String libelle){
    List<Genre> genresList = new ArrayList<>();
    try {
        String req = "SELECT * FROM `genre` where libelle= ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, libelle);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Genre c = new Genre();
            c.setId(rs.getInt("id"));
                c.setLibelle(rs.getString("libelle"));
            genresList.add(c);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
     return genresList;               
    }
}
