/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userjava;
import Entities.Produit;
import Entities.Categorie;
import Services.ServiceProduit;
//import Services.ServiceCategorie;
import util.MyDB;

/**
 *
 * @author 21692
 */
public class Pimaisa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       Produit p4 = new  Produit("tapis","jjjjjjjjjjjjjj",20 , 12, "tapis.png");
      
       ServiceProduit sp = new ServiceProduit();
       //sp.ajouterProduit(p4);
      System.out.println(sp.afficherProduit());
      // sp.supprimerProduit(81);
    //   sp.modifierProduit(74, "ballon","jjjjjjjjjjjjjj",100 , 12, "tapis.png");
    }
    
}
