/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Coaching;
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
public class ServiceCoaching implements IService<Coaching>{
 Connection cnx ;
  Statement stm;
    public ServiceCoaching(){
        cnx = MyDB.getInstance().getCnx();
  
     
      
    }
    
    @Override
    public void ajouter(Coaching c) {
    String qry ="INSERT INTO `coaching`(`cours`, `dispo_coach`, `img_coach`, `desc_coach`) VALUES ('"+c.getCours()+"','"+c.getDispoCoach()+"','"+c.getImgCoach()+"','"+c.getDescCoach()+"')";
   try {
            Statement stm = cnx.createStatement();
       
        stm.executeUpdate(qry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    }

  
    

    @Override
    public boolean modifier(Coaching c) {
       
     try {
            String qry = "UPDATE `coaching` SET `cours` = '" + c.getCours() + "', `dispo_coach` = '" + c.getDispoCoach() + "',`img_coach` = '" + c.getImgCoach() + "', `desc_coach` = '" + c.getDescCoach() + "' WHERE `id` = '" + c.getId() + "'";
            stm = cnx.createStatement();

            stm.executeUpdate(qry);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }  }

    @Override
    public void supprimer(Coaching c) {
String qry = "DELETE FROM coaching WHERE id=?";
    try {
        PreparedStatement pstmt = cnx.prepareStatement(qry);
        pstmt.setInt(1, c.getId());
        pstmt.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }    }

    @Override
    public ObservableList<Coaching> afficher() {

        ObservableList<Coaching> CoachingList = FXCollections.observableArrayList();
        try {
            String qry = "SELECT `id`, `cours`, `dispo_coach`, `img_coach`, `desc_coach` FROM `coaching` ";
            //stm = cnx.createStatement();
            PreparedStatement ps= cnx.prepareCall(qry);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Coaching c = new Coaching();
                c.setId(rs.getInt("id"));
                c.setCours(rs.getString("cours"));
                c.setDescCoach(rs.getString("desc_coach"));
                
                c.setDispoCoach(rs.getString("dispo_coach"));
                c.setImgCoach(rs.getString("img_coach"));

                CoachingList.add(c);
            }
            return CoachingList;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return CoachingList;
    }

    @Override
    public List<Coaching> afficherrdv() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
