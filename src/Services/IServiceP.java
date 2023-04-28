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
public interface IServiceP<T> {
  public void ajouterProduit(T t);
    public List<T> afficherProduit();
    public boolean modifierProduit (T t);
    public void supprimerProduit(T t);
}
