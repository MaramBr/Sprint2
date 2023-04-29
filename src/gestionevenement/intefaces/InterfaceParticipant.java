/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvenement.intefaces;

import Entities.Evenement;
import Entities.Participant;
import java.util.List;

/**
 *
 * @author emna
 */
public interface InterfaceParticipant{
    public void ajouterParticipant(Participant p);

    public void modifierParticipant(Participant p);
   

    public void supprimerParticipant(int id);
      


    public List<Participant> afficherParticipant();
    
}
