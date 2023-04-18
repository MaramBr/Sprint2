/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author rayan
 */
public class User {
       private int id;
    private String nom;
    private String prenom;
    private String password;
    private String email;
    private String roles;
     private String image;
     
      public User() {
    }

    public User(int id, String nom, String prenom, String password, String email, String roles, String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.image = image;
    }

    public User(String nom, String prenom, String password, String email, String roles, String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.image = image;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", password=" + password + ", email=" + email + ", roles=" + roles + ", image=" + image + '}';
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRoles() {
        return roles;
    }

    public String getImage() {
        return image;
    }
     

}
