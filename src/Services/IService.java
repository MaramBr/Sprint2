/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Mohamed
 * @param <T>
 */
public interface IService<T> {
    public void ajouter(T t);
   public List<T> afficher();
    public boolean modifier (T t);
    public void supprimer(T t);
    public ObservableList<T> afficher2();
}
