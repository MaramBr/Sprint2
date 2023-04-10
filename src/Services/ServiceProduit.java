/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Produit;
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
public class ServiceProduit implements IService<Produit> {
     Statement stm;
    Connection cnx ;
    public ServiceProduit(){
        cnx = MyDB.getInstance().getCnx();
    }
    
     @Override
    public void ajouterProduit(Produit t) {
   //     String qry="INSERT INTO `rendez_vous`( `coachings_id`, `daterdv`) VALUES ('"+c.getIdCoaching()+"','"+c.getDaterdv()+"')";

   String qry ="INSERT INTO `produit`(nom,categorys_id,description,quantite, prix, image ) VALUES ('"+t.getNom()+"','"+t.getIdCategory()+"','"+t.getDescription()+"','"+t.getQuantite()+"','"+t.getPrix()+"','"+t.getImage()+"')";
        try {
            Statement stm = cnx.createStatement();
       
        stm.executeUpdate(qry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    }
    /*
     public  void  supprimerProduit ( int  id ) {
       try {
            Statement stm=cnx.createStatement();
            String query="delete from produit where id = '"+id+"'";
           
            stm.executeUpdate(query);
            
       } catch (SQLException ex) {
           Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
       }
     }
*/
    @Override
    public List<Produit> afficherProduit() {
    List<Produit> produits = new ArrayList();
        String qry = "SELECT p.id ,c.libelle, p.nom,p.description,p.quantite,p.prix,p.image  FROM category c JOIN produit p ON c.id = p.categorys_id";
     try {
         Statement stm = cnx.createStatement();
         ResultSet rs = stm.executeQuery(qry);
         
         while (rs.next()){
             Produit p = new Produit();
             p.setId(rs.getInt(1));
              p.setNomCategory(rs.getString(2));

             p.setNom(rs.getString(3));
             p.setDescription(rs.getString(4));
              p.setQuantite(rs.getInt(5));
               p.setPrix(rs.getFloat(6));
                p.setImage(rs.getString(7));
            
             produits.add(p);
         }
                 
         return produits;
     } catch (SQLException ex) {
         System.out.println(ex.getMessage());
     }
    
    return produits;
    }
/*
    public void modifierProduit (int id,String nom, String description, int quantite, float prix, String image){
         String requete="UPDATE produit SET nom='"+nom+"', quantite='"+quantite+"', description='"+description+"',prix='"+prix+"',image='"+image+"'  WHERE id='"+id+"'";
         
         
         try{
            
             Ste = cnx.createStatement();
            Ste.executeUpdate(requete);
            System.out.println("Produit modifié");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }*/
     

    @Override
    public boolean modifierProduit(Produit t) {
         try {
            String qry = "UPDATE produit SET `nom` = '" + t.getNom()+ "',`categorys_id` = '" + t.getIdCategory()+ "', `description` = '" + t.getDescription() + "',`quantite` = '" + t.getQuantite() + "',`prix` = '" + t.getPrix() + "',`image` = '" + t.getImage() + "' WHERE `id` = '" + t.getId() + "'";
           //String qry = "UPDATE `rendez_vous` SET `daterdv`  = '" + c.getDaterdv()+ "', `coachings_id` = '" + c.getIdCoaching() + "',`etatrdv` = '" + c.isEtatrdv() + "' WHERE `id` = '" + c.getId() + "'";
 // UPDATE `produit` SET `id`='[value-1]',`categorys_id`='[value-2]',`nom`='[value-3]',`description`='[value-4]',`quantite`='[value-5]',`prix`='[value-6]',`image`='[value-7]',`likes`='[value-8]',`dislikes`='[value-9]' WHERE 1
            stm = cnx.createStatement();

            stm.executeUpdate(qry);
            return true;
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }  
    }

    @Override
    public void supprimerProduit(Produit t) {
        String qry = "DELETE FROM `produit` WHERE id=?";
    try {
        PreparedStatement pstmt = cnx.prepareStatement(qry);
        pstmt.setInt(1, t.getId());
        pstmt.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }


   
        
    }
}


   
   
    
    
    

