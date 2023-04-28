/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author 21692
 */
public class Transporteur {
    
    private int id,numero,Companies_id ;
    private String nom ,description,image,nomCompanie ;



    public Transporteur(int numero, int Companies_id, String nom, String description, String image, float prix) {
        this.numero = numero;
        this.Companies_id = Companies_id;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
    }

    public int getCompanies_id() {
        return Companies_id;
    }

    public void setCompanies_id(int Companies_id) {
        this.Companies_id = Companies_id;
    }

    public String getNomCompanie() {
        return nomCompanie;
    }

    public void setNomCompanie(String nomCompanie) {
        this.nomCompanie = nomCompanie;
    }
    private float prix;
    

    public Transporteur() {
    }

    public Transporteur(int id,String nom, String description, int numero, float prix, String image) {
        this.id = id;
        this.nom = nom;
        this.numero = numero;
        this.description = description;
        this.image = image;
        this.prix = prix;
    }

    public Transporteur(String nom, String description, int numero, float prix, String image) {
        this.nom = nom;
        this.numero = numero;
        this.description = description;
        this.image = image;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    
    public String getDescription() {
        return description;
    }
    
    public int getNumero() {
        return numero;
    }


    public String getImage() {
        return image;
    }

    public float getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Transporteur{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", numero=" + numero +  ", prix=" + prix + ", image=" + image + '}';
    }
    
    
}
