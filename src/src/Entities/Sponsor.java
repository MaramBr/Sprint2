/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

public class Sponsor {
    private int id;
    private String nom_Sponsor, email,invest;

    public Sponsor(String nom_Sponsor, String email, String invest) {
        this.nom_Sponsor = nom_Sponsor;
        this.email = email;
        this.invest = invest;
    }

    public Sponsor(int id, String nom_Sponsor, String email, String invest) {
        this.id = id;
        this.nom_Sponsor = nom_Sponsor;
        this.email = email;
        this.invest = invest;
    }

    public Sponsor(int sponsors_id, String sponsor_nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Sponsor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_Sponsor() {
        return nom_Sponsor;
    }

    public void setNom_Sponsor(String nom_Sponsor) {
        this.nom_Sponsor = nom_Sponsor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInvest() {
        return invest;
    }

    public void setInvest(String invest) {
        this.invest = invest;
    }

    @Override
    public String toString() {
        return "Sponsor{" + "id=" + id + ", nom_Sponsor=" + nom_Sponsor + ", email=" + email + ", invest=" + invest + '}';
    }
   

  
    
    
}
