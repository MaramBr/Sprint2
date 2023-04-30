/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

/**
 *
 * @author 21692
 */
public interface TService<T> {
  public void ajouterTransporteur(T t);
    public List<T> afficherTransporteur();
    public boolean modifierTransporteur (T t);
    public void supprimerTransporteur(T t);
}
