/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvenement.entities;


public class Participant {
 private int id;
    private String nom, prenom,email;
      private int age ,tel;
      
       private String nomev;

  
    public String getNomev() {
        return nomev;
    }

    public void setNomev(String nomev) {
        this.nomev = nomev;
    }

    public Participant(String nom, String prenom, String email, int age, int tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.age = age;
        this.tel = tel;
    }

    public Participant(int id, String nom, String prenom, String email, int age, int tel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.age = age;
        this.tel = tel;
    }

    public Participant() {
       
    }

   

    public Participant(String nom, String prenom, String email, int age, int tel, String nomev) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.age = age;
        this.tel = tel;
        this.nomev = nomev;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Participant{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", age=" + age + ", tel=" + tel + '}';
    }

  
  
    

  
    
    
}
