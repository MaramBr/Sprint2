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
public interface TCService<T> {
  public void ajouterCompanie(T t);
    public List<T> afficherCompanie();
    public boolean modifierCompanie (T t);
    public void supprimerCompanie(T t);
}