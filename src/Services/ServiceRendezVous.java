/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Coaching;
import Entities.RendezVous;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Maram
 */
public class ServiceRendezVous implements IService<RendezVous> {

    Connection cnx ;
      Statement stm;
    public ServiceRendezVous(){
        cnx = MyDB.getInstance().getCnx();
  
     
      
    }
    
    
    @Override
    public void ajouter(RendezVous c) {
String qry="INSERT INTO `rendez_vous`( `coachings_id`, `daterdv`) VALUES ('"+c.getIdCoaching()+"','"+c.getDaterdv()+"','"+c.isEtatrdv()+"')";
   try {
            Statement stm = cnx.createStatement();
       
        stm.executeUpdate(qry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }    }

   

    @Override
    public boolean modifier(RendezVous c) {
 try {
            String qry = "UPDATE `rendez_vous` SET `daterdv` = '" + c.getDaterdv()+ "', `coachings_id` = '" + c.getIdCoaching() + "',`etatrdv` = '" + c.isEtatrdv() + "' WHERE `id` = '" + c.getId() + "'";
            stm = cnx.createStatement();

            stm.executeUpdate(qry);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }    }

    @Override
    public void supprimer(RendezVous c) {
String qry = "DELETE FROM rendez_vous WHERE id=?";
    try {
        PreparedStatement pstmt = cnx.prepareStatement(qry);
        pstmt.setInt(1, c.getId());
        pstmt.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }        }

    @Override
    public List<RendezVous> afficherrdv() {
        List<RendezVous> rdvList = new ArrayList();

        try {
            String qry = "SELECT rv.id, rv.coachings_id, rv.daterdv, rv.etatrdv \" +\"FROM rendez_vous rv JOIN coaching c ON c.id = rv.coachings_id;";
            stm = cnx.createStatement();
           // PreparedStatement ps= cnx.prepareCall(qry);
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                RendezVous c = new RendezVous();
                c.setId(rs.getInt(1));
                c.setIdCoaching(rs.getInt(2));
                c.setNomCours(rs.getString(3));

               // c.setDaterdv(rs.getString(3));
                
                c.setEtatrdv(rs.getBoolean(4));

                rdvList.add(c);
            }
            return rdvList;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rdvList;    }

    @Override
    public ObservableList<RendezVous> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
