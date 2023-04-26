/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Majdi
 */
public class Genre {
    private int Id;
    private String libelle;

    public Genre() {
    }
    
    

    public Genre(int Id, String libelle) {
        this.Id = Id;
        this.libelle = libelle;
    }

    public Genre(String libelle) {
        this.libelle = libelle;
    }

    public int getId() {
        return Id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    
    
}
