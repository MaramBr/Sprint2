/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;
import Entities.Transporteur;
import Entities.Companie;
import Services.ServiceTransporteur;
//import Services.ServiceCompanie;
import util.MyDB;

/**
 *
 * @author 21692
 */
public class PI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       Transporteur p4 = new  Transporteur("tapis","jjjjjjjjjjjjjj",20 , 12, "tapis.png");
      
       ServiceTransporteur sp = new ServiceTransporteur();
       //sp.ajouterTransporteur(p4);
      System.out.println(sp.afficherTransporteur());
     // sp.supprimerTransporteur(81);
    //   sp.modifierTransporteur(74, "ballon","jjjjjjjjjjjjjj",100 , 12, "tapis.png");
    }
    
}
