/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvenement.intefaces;

import gestionEvenement.entities.Sponsor;
import gestionEvenement.entities.Evenement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author emna
 */
public interface InterfaceEvenement {

    public void ajouterEvenement(Evenement r);

    public void modifierEvenement(Evenement r);
    //public void modifierEvenement(Evenement r, int id);

    public void supprimerEvenement(int id);
        //public void supprimerEvenement(int id) ;


    public List<Evenement> afficherEvenement();
}
