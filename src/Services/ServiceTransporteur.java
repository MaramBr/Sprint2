/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Transporteur;
import util.MyDB;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author msi
 */
public class ServiceTransporteur implements TService<Transporteur> {
     Statement stm;
    Connection cnx ;
    public ServiceTransporteur(){
        cnx = MyDB.getInstance().getCnx();
    }
    
     @Override
    public void ajouterTransporteur(Transporteur t) {
   //     String qry="INSERT INTO `rendez_vous`( `coachings_id`, `daterdv`) VALUES ('"+c.getIdCoaching()+"','"+c.getDaterdv()+"')";

   String qry ="INSERT INTO `transporteur`(nom,companies_id,description,numero, prix, image ) VALUES ('"+t.getNom()+"','"+t.getCompanies_id()+"','"+t.getDescription()+"','"+t.getNumero()+"','"+t.getPrix()+"','"+t.getImage()+"')";
        try {
            Statement stm = cnx.createStatement();
       
        stm.executeUpdate(qry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    }
    /*
     public  void  supprimerTransporteur ( int  id ) {
       try {
            Statement stm=cnx.createStatement();
            String query="delete from transporteur where id = '"+id+"'";
           
            stm.executeUpdate(query);
            
       } catch (SQLException ex) {
           Logger.getLogger(ServiceTransporteur.class.getName()).log(Level.SEVERE, null, ex);
       }
     }
*/
    @Override
    public List<Transporteur> afficherTransporteur() {
    List<Transporteur> transporteurs = new ArrayList();
        String qry = "SELECT p.id ,c.libelle,p.nom,p.description,p.numero,p.prix,p.image  FROM companie c JOIN transporteur p ON c.id = p.companies_id";
     try {
         Statement stm = cnx.createStatement();
         ResultSet rs = stm.executeQuery(qry);
         
         while (rs.next()){
             Transporteur p = new Transporteur();
             p.setId(rs.getInt(1));
              p.setNomCompanie(rs.getString(2));

             p.setNom(rs.getString(3));
             p.setDescription(rs.getString(4));
              p.setNumero(rs.getInt(5));
               p.setPrix(rs.getFloat(6));
                p.setImage(rs.getString(7));
            
             transporteurs.add(p);
         }
                 
         return transporteurs;
     } catch (SQLException ex) {
         System.out.println(ex.getMessage());
     }
    
    return transporteurs;
    }
/*
    public void modifierTransporteur (int id,String nom, String description, int numero, float prix, String image){
         String requete="UPDATE transporteur SET nom='"+nom+"', numero='"+numero+"', description='"+description+"',prix='"+prix+"',image='"+image+"'  WHERE id='"+id+"'";
         
         
         try{
            
             Ste = cnx.createStatement();
            Ste.executeUpdate(requete);
            System.out.println("Transporteur modifié");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTransporteur.class.getName()).log(Level.SEVERE, null, ex);
        }*/
     

    @Override
    public boolean modifierTransporteur(Transporteur t) {
         try {
            String qry = "UPDATE transporteur SET `nom` = '" + t.getNom()+ "',`companies_id` = '" + t.getCompanies_id()+ "', `description` = '" + t.getDescription() + "',`numero` = '" + t.getNumero() + "',`prix` = '" + t.getPrix() + "',`image` = '" + t.getImage() + "' WHERE `id` = '" + t.getId() + "'";
           //String qry = "UPDATE `rendez_vous` SET `daterdv`  = '" + c.getDaterdv()+ "', `coachings_id` = '" + c.getIdCoaching() + "',`etatrdv` = '" + c.isEtatrdv() + "' WHERE `id` = '" + c.getId() + "'";
 // UPDATE `transporteur` SET `id`='[value-1]',`companies_id`='[value-2]',`nom`='[value-3]',`description`='[value-4]',`numero`='[value-5]',`prix`='[value-6]',`image`='[value-7]',`likes`='[value-8]',`dislikes`='[value-9]' WHERE 1
            stm = cnx.createStatement();

            stm.executeUpdate(qry);
            return true;
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }  
    }

    @Override
    public void supprimerTransporteur(Transporteur t) {
        String qry = "DELETE FROM `transporteur` WHERE id=?";
    try {
        PreparedStatement pstmt = cnx.prepareStatement(qry);
        pstmt.setInt(1, t.getId());
        pstmt.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }


   
        
    }
    
    


}





   
   
    
    
    

