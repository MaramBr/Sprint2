/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Categorie;
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
public class ServiceCategorie implements ICService<Categorie> {
     Statement stm;
    Connection cnx ;
    public ServiceCategorie(){
        cnx = MyDB.getInstance().getCnx();
    }
    
     @Override
    public void ajouterCategorie(Categorie t) {
   String qry ="INSERT INTO `category`(libelle) VALUES ('"+t.getLibelle()+"')";
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
    public List<Categorie> afficherCategorie() {
    List<Categorie> Categories = new ArrayList();
        String qry ="SELECT `id`, `libelle` FROM `category`";
        
     try {
         Statement stm = cnx.createStatement();
         ResultSet rs = stm.executeQuery(qry);
         
         while (rs.next()){
             Categorie p = new Categorie();
             p.setId(rs.getInt("id"));
             p.setLibelle(rs.getString("libelle"));
          
            
             Categories.add(p);
         }
                 
         return Categories;
     } catch (SQLException ex) {
         System.out.println(ex.getMessage());
     }
    
    return Categories;
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
    public boolean modifierCategorie(Categorie t) {
         try {
            String qry = "UPDATE category SET `libelle` = '" + t.getLibelle() + "' WHERE `id` = '" + t.getId() + "'";
             stm = cnx.createStatement();

            stm.executeUpdate(qry);
            return true;
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }  
    }

    @Override
    public void supprimerCategorie(Categorie t) {
        String qry = "DELETE FROM `category` WHERE id=?";
    try {
        PreparedStatement pstmt = cnx.prepareStatement(qry);
        pstmt.setInt(1, t.getId());
        pstmt.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }


   
        
    }
    /*
    public boolean categorieExiste(String libelle) {
         Iterable<Categorie> Categories = null;
    for (Categorie c : Categories) {
        if (c.getLibelle().equals(libelle)) {
            return true;
        }
    }
    return false;
}*/
}


   
   
    
    
    

