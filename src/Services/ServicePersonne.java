/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Personne;
import Utils.MyDB;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.ObservableList;
/**
 *
 * @author Mohamed
 */
public class ServicePersonne implements IService<Personne>{
 Connection cnx ;
    public ServicePersonne(){
        cnx = MyDB.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Personne t) {
   String qry ="INSERT INTO `personne`(`nom`, `prenom`, `age`) VALUES ('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAge()+"')";
        try {
            Statement stm = cnx.createStatement();
       
        stm.executeUpdate(qry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    }

    @Override
    public List<Personne> afficher() {
    List<Personne> personnes = new ArrayList();
        String qry ="SELECT * FROM `personne`";
     try {
         Statement stm = cnx.createStatement();
         ResultSet rs = stm.executeQuery(qry);
         
         while (rs.next()){
             Personne p = new Personne();
             p.setId(rs.getInt(1));
             p.setNom(rs.getString("nom"));
             p.setPrenom(rs.getString(3));
             p.setAge(rs.getInt(4));
             personnes.add(p);
         }
                 
         return personnes;
     } catch (SQLException ex) {
         System.out.println(ex.getMessage());
     }
    
    return personnes;
    }

    @Override
    public boolean modifier(Personne t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Personne t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Personne> afficher2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
