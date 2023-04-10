/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop3a53;

import Entities.Personne;
import Entities.Reclamation;
import Services.ServicePersonne;
import Services.ServiceReclamation;
import Utils.MyDB;

/**
 *
 * @author Mohamed
 */
public class Workshop3A53 {

    public static void main(String[] args) {
       // MyDB a = MyDB.getInstance();
        Reclamation p4 = new Reclamation(7, "Dakhlia", "abdou");
 
       ServiceReclamation sp = new ServiceReclamation();
       sp.supprimer(p4);
       
        System.out.println(sp.afficher2());
    }

}
