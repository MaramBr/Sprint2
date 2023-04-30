/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;

/**
 *
 * @author Majdi
 */
public class Reclamation {
    private int idRec,IdG;
    private String Titre,Description,Status,Traitement;
    private String date , genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Reclamation(int IdG, String Titre, String Description, String Status, String Traitement, String date, String genre) {
        this.IdG = IdG;
        this.Titre = Titre;
        this.Description = Description;
        this.Status = Status;
        this.Traitement = Traitement;
        this.date = date;
        this.genre = genre;
    }

    public Reclamation(int idRec, int IdG, String Titre, String Description, String Status, String Traitement, String date, String genre) {
        this.idRec = idRec;
        this.IdG = IdG;
        this.Titre = Titre;
        this.Description = Description;
        this.Status = Status;
        this.Traitement = Traitement;
        this.date = date;
        this.genre = genre;
    }

    public Reclamation() {
    }

    public Reclamation(int idRec, int IdG, String Titre, String Description, String Status, String Traitement, String date) {
        this.idRec = idRec;
        this.IdG = IdG;
        this.Titre = Titre;
        this.Description = Description;
        this.Status = Status;
        this.Traitement = Traitement;
        this.date = date;
    }

    public Reclamation(int IdG, String Titre, String Description, String Status, String Traitement, String date) {
        this.IdG = IdG;
        this.Titre = Titre;
        this.Description = Description;
        this.Status = Status;
        this.Traitement = Traitement;
        this.date = date;
    }

    public Reclamation(int IdG, String Titre, String Description) {
        this.IdG = IdG;
        this.Titre = Titre;
        this.Description = Description;
    }

    public Reclamation(String Status, String Traitement) {
        this.Status = Status;
        this.Traitement = Traitement;
    }

    public Reclamation(String titre, String description, LocalDate date, String status, String libelle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    public int getIdRec() {
        return idRec;
    }

    public int getIdG() {
        return IdG;
    }

    public String getTitre() {
        return Titre;
    }

    public String getDescription() {
        return Description;
    }

    public String getStatus() {
        return Status;
    }

    public String getTraitement() {
        return Traitement;
    }

    public String getDate() {
        return date;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public void setIdG(int IdG) {
        this.IdG = IdG;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setTraitement(String Traitement) {
        this.Traitement = Traitement;
    }

    public void setDate(String date) {
        this.date = date;
    }

   
    
    
    
}
