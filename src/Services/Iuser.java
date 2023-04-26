/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

/**
 *
 * @author rayan
 */
public interface Iuser<T> {
   void insert(T t);
    void delete(T t);
    void update(T t);
    public List<T> readAll();
    T readByID(int idUser);  
}


