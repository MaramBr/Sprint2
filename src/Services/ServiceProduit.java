/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Produit;
import Entities.ProduitLike;
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
public class ServiceProduit implements IServiceP<Produit> {
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
        String qry = "SELECT p.id ,c.libelle, p.nom,p.description,p.quantite,p.prix,p.image, (SELECT AVG(rating) from ratings where ratings.id_produit = p.id) as moyRating    FROM category c JOIN produit p ON c.id = p.categorys_id";
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
                 p.setMoyRating(rs.getFloat(8));
            
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
    
    
    
    
    public List<ProduitLike> likes (int id){
        List<ProduitLike> produits = new ArrayList<>();
        try {
            String req = "select * from produit_like where produit_id =" +id+";";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                ProduitLike p = new ProduitLike();
                p.setId(rs.getInt("id"));
                p.setIdproduit(rs.getInt("produit_id"));
                //p.setIduser(rs.getInt("user_id"));
                produits.add(p);
            }
            System.out.print(produits);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return produits ; 
    }
    public List<ProduitLike> islikedbyuser(int idp) {
        
        List<ProduitLike> produits = new ArrayList<>();
        try {
            String req = "Select * from produit_like where produit_id= '"+idp+"';";
          
             Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                ProduitLike p = new ProduitLike();
                p.setId(rs.getInt("id"));
                p.setIdproduit(rs.getInt("produit_id"));
                produits.add(p);
            }
            System.out.println(produits.size());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return produits;
    }
     public void Supprimerlike(int id) {
    try
    { 
      Statement st = cnx.createStatement();
      String req = "DELETE FROM produit_like WHERE produit_id = '"+id+"';";
                st.executeUpdate(req);      
      System.out.println("produit supprimer avec succès...");
    } catch (SQLException ex) {
                System.out.println(ex.getMessage());        
              }
    }
     public boolean ajouterlike(int idp) {
        boolean a=false;
        try {
            String req = "insert into produit_like(produit_id) values("+idp+")";
          
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            //System.out.println("like ajoutée");
            a=true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
    }
}





   
   
    
    
    

