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
public class Produit {
    
    private int id,quantite,idCategory ;
    private String nom ,description,image,nomCategory ;

    public Produit(int quantite, int idCategory, String nom, String description, String image, String nomCategory, float prix) {
        this.quantite = quantite;
        this.idCategory = idCategory;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.nomCategory = nomCategory;
        this.prix = prix;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNomCategory() {
        return nomCategory;
    }

    public void setNomCategory(String nomCategory) {
        this.nomCategory = nomCategory;
    }
    private float prix;
    

    public Produit() {
    }

    public Produit(int id,String nom, String description, int quantite, float prix, String image) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.description = description;
        this.image = image;
        this.prix = prix;
    }

    public Produit(String nom, String description, int quantite, float prix, String image) {
        this.nom = nom;
        this.quantite = quantite;
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
    
    public int getQuantite() {
        return quantite;
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

    public void setQuantite(int quantite) {
        this.quantite = quantite;
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
        return "Produit{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", quantite=" + quantite +  ", prix=" + prix + ", image=" + image + '}';
    }
}
