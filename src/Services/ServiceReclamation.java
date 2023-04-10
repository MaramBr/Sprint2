/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Reclamation;
import java.util.List;
import Entities.Genre;
import Utils.MyDB;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Majdi
 */
public class ServiceReclamation implements IService<Reclamation> {
 Connection cnx ;
 LocalDate currentDate = LocalDate.now();
    public ServiceReclamation() {
        cnx = MyDB.getInstance().getCnx();
    }
    
    

    @Override
    public void ajouter(Reclamation t) {
        t.setStatus("en attente");
    t.setTraitement("pas de traitement");
    String dateSysteme = currentDate.toString(); // Convertir la date en String
t.setDate(dateSysteme);
    String qry ="INSERT INTO `reclamation`(`genre_id`, `date_r`, `titre_r`, `description_r`, `status_r`, `traitement`) VALUES ('"+t.getIdG()+"','"+t.getDate()+"','"+t.getTitre()+"','"+t.getDescription()+"','"+t.getStatus()+"','"+t.getTraitement()+"')";
        try {
            Statement stm = cnx.createStatement();
       
        stm.executeUpdate(qry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }    
    
    
    
    }

    @Override
    public List<Reclamation> afficher() {
        List<Reclamation> reclamations = new ArrayList();
        String qry ="SELECT r.titre, r.description, r.date, r.status, g.libelle FROM reclamation r JOIN genre g ON r.id_genre = g.id;";
        
     try {
         Statement stm = cnx.createStatement();
         ResultSet rs = stm.executeQuery(qry);
         
         while (rs.next()){
             Reclamation p = new Reclamation();
           p.setIdRec(rs.getInt(1));
            p.setTitre(rs.getString(5));
             p.setGenre(rs.getString(2));
             Date date = rs.getDate(4);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = formatter.format(date);
            p.setDate(dateStr);
            
            p.setDescription(rs.getString(6));
           // p.setIdG(rs.getInt(2));
            p.setStatus(rs.getString(7));
            p.setTraitement(rs.getString(8));
            reclamations.add(p);
         }
                 
         return reclamations;
     } catch (SQLException ex) {
         System.out.println(ex.getMessage());
     }
    
    return reclamations;
     }


    @Override
    public boolean modifier(Reclamation t) {
        try {
            String qry = "UPDATE `reclamation` SET `id`='"+t.getIdRec()+"',`date_r`='"+t.getDate()+"',`titre_r`='"+t.getTitre()+"',`description_r`='"+t.getDescription()+"',`status_r`='"+t.getStatus()+"',`traitement`='"+t.getTraitement()+"'WHERE `id` = '" + t.getIdRec()+ "'";
             Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }  
    }

    @Override
    public void supprimer(Reclamation t) {
        String qry = "DELETE FROM `reclamation` WHERE id=?";
    try {
        PreparedStatement pstmt = cnx.prepareStatement(qry);
        pstmt.setInt(1, t.getIdRec());
        pstmt.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }   }

    @Override
    public ObservableList<Reclamation> afficher2() {
      ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();
    String qry ="SELECT r.id , g.libelle, r.date_r, r.titre_r, r.description_r, r.status_r, r.traitement FROM reclamation r JOIN genre g ON r.genre_id = g.id;";
        
    try {
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(qry);
         
        while (rs.next()){
            Reclamation r = new Reclamation();
            r.setIdRec(rs.getInt(1));
            r.setTitre(rs.getString("r.titre_r"));
            r.setDescription(rs.getString("r.description_r"));
            r.setDate(rs.getString("r.date_r"));
           // r.setIdG(rs.getInt("genre_id"));
           r.setGenre(rs.getString("g.libelle"));
            r.setStatus(rs.getString("r.status_r"));
            r.setTraitement(rs.getString("r.traitement"));
            reclamations.add(r);
        }
                 
        return reclamations;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    return reclamations;
    }
    public ObservableList<Reclamation> afficher3() {
      ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();
    String qry ="SELECT * FROM `reclamation`";
        
    try {
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(qry);
         
        while (rs.next()){
            Reclamation r = new Reclamation();
            r.setIdRec(rs.getInt("id"));
            r.setTitre(rs.getString("titre_r"));
            r.setDescription(rs.getString("description_r"));
            r.setDate(rs.getString("date_r"));
            r.setIdG(rs.getInt("genre_id"));
            r.setStatus(rs.getString("status_r"));
            r.setTraitement(rs.getString("traitement"));
            reclamations.add(r);
        }
                 
        return reclamations;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    return reclamations;
    }
    
    
    
      public List<Reclamation> rechercher(String titre_r){
    List<Reclamation> reclamationsList = new ArrayList<>();
    try {
        String req = "SELECT * FROM `reclamation` where titre_r = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, titre_r);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Reclamation r = new Reclamation();
            r.setIdRec(rs.getInt("id"));
            r.setTitre(rs.getString("titre_r"));
            r.setDescription(rs.getString("description_r"));
            r.setDate(rs.getString("date_r"));
            r.setIdG(rs.getInt("genre_id"));
            r.setStatus(rs.getString("status_r"));
            r.setTraitement(rs.getString("traitement"));
            reclamationsList.add(r);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
     return reclamationsList;               
    }
      
      
    
   
}
