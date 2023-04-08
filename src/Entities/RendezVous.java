/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Maram
 */
public class RendezVous {
    
    private int id ,idCoaching;
   private String nomCours;
    private Date daterdv;

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }
private boolean etatrdv;
    public RendezVous() {
    }

    public RendezVous(int id, int idCoaching, Date daterdv, String nomCours, boolean etatrdv) {
        this.id = id;
        this.idCoaching = idCoaching;
        this.daterdv = daterdv;
        this.nomCours = nomCours;
        this.etatrdv = etatrdv;
    }

    public RendezVous(int idCoaching, Date daterdv, String nomCours, boolean etatrdv) {
        this.idCoaching = idCoaching;
        this.daterdv = daterdv;
        this.nomCours = nomCours;
        this.etatrdv = etatrdv;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDaterdv() {
        return daterdv;
    }

    public void setDaterdv(Date daterdv) {
        this.daterdv = daterdv;
    }



    public int getIdCoaching() {
        return idCoaching;
    }

    public void setIdCoaching(int idCoaching) {
        this.idCoaching = idCoaching;
    }

    public boolean isEtatrdv() {
        return etatrdv;
    }

    public void setEtatrdv(boolean etatrdv) {
        this.etatrdv = etatrdv;
    }
    

    public RendezVous(int idCoaching, Date daterdv, boolean etatrdv) {
        this.idCoaching = idCoaching;
        this.daterdv = daterdv;
        this.etatrdv = etatrdv;
    }

   
    
    
    
    
    
}
