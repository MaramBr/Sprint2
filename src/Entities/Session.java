/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


public class Session {
    private static int id = 0;
    private static String nom;
    private static String prenom;
    private static String email;
    private static String roles;
    private static String password;
     private static String image;
     private static int  is_active;

    public static int getIs_active() {
        return is_active;
    }

    public static void setIs_active(int is_active) {
        Session.is_active = is_active;
    }

 
    public static String getImage() {
        return image;
    }

    public static void setImage(String image) {
        Session.image = image;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Session.id = id;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        Session.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        Session.prenom = prenom;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Session.email = email;
    }

    public static String getRoles() {
        return roles;
    }

    public static void setRoles(String roles) {
        Session.roles = roles;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Session.password = password;
    }

    
    


       

  
  
}
