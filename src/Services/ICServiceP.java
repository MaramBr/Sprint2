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
public interface ICServiceP<T> {
  public void ajouterCategorie(T t);
    public List<T> afficherCategorie();
    public boolean modifierCategorie (T t);
    public void supprimerCategorie(T t);
}