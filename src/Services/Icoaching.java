/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author Majdi
 */
import java.util.List;
import javafx.collections.ObservableList;

public interface Icoaching<C> {
     public void ajouter(C c);
    public boolean modifier (C c);
    public void supprimer(C c);
    public ObservableList<C> afficher();
    public List<C>afficherrdv();
    
}
