/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Companie;
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

/**
 *
 * @author msi
 */
public class ServiceCompanie implements TCService<Companie> {
     Statement stm;
    Connection cnx ;
    public ServiceCompanie(){
        cnx = MyDB.getInstance().getCnx();
    }
    
     @Override
    public void ajouterCompanie(Companie t) {
   String qry ="INSERT INTO `companie`(libelle) VALUES ('"+t.getLibelle()+"')";
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
    public List<Companie> afficherCompanie() {
    List<Companie> Companies = new ArrayList();
        String qry ="SELECT `id`, `libelle` FROM `companie`";
        
     try {
         Statement stm = cnx.createStatement();
         ResultSet rs = stm.executeQuery(qry);
         
         while (rs.next()){
             Companie p = new Companie();
             p.setId(rs.getInt("id"));
             p.setLibelle(rs.getString("libelle"));
          
            
             Companies.add(p);
         }
                 
         return Companies;
     } catch (SQLException ex) {
         System.out.println(ex.getMessage());
     }
    
    return Companies;
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
    public boolean modifierCompanie(Companie t) {
         try {
            String qry = "UPDATE companie SET `libelle` = '" + t.getLibelle() + "' WHERE `id` = '" + t.getId() + "'";
             stm = cnx.createStatement();

            stm.executeUpdate(qry);
            return true;
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }  
    }

    @Override
    public void supprimerCompanie(Companie t) {
        String qry = "DELETE FROM `companie` WHERE id=?";
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
         Iterable<Companie> Companies = null;
    for (Companie c : Companies) {
        if (c.getLibelle().equals(libelle)) {
            return true;
        }
    }
    return false;
}*/
}


   
   
    
    
    

