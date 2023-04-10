/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvenement.intefaces;

import gestionEvenement.entities.Sponsor;
import java.util.List;

/**
 *
 * @author emna
 */
public interface InterfaceSponsor {
    public void ajouterSponsor(Sponsor s);

    public void modifierSponsor(Sponsor s);
   

    public void supprimerSponsor(int id);
      


    public List<Sponsor> afficherSponsor();
    
}
