/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
//import java.util.Date;


  public class Evenement {
  private int id,sponsors_id;
  private String nom_sponsor;
  private String nom,lieu,type,description;
  private String date_debut, date_fin;
  private String image;
  private int nb_participant,prix;
  private Sponsor sponsor; 

    public Evenement(int id, int sponsors_id, String nom_sponsor, String nom, String lieu, String type, String description, String date_debut, String date_fin, String image, int nb_participant, int prix, Sponsor sponsor) {
        this.id = id;
        this.sponsors_id = sponsors_id;
        this.nom_sponsor = nom_sponsor;
        this.nom = nom;
        this.lieu = lieu;
        this.type = type;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.image = image;
        this.nb_participant = nb_participant;
        this.prix = prix;
        this.sponsor = sponsor;
    }

    public Evenement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Evenement(int aInt, String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Evenement(int aInt, String string, String string0, String string1, String string2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }
    public Evenement(int sponsors_id, String nom, String lieu, String type, String description, String date_debut, String date_fin, String image, int nb_participant, int prix) {
        this.sponsors_id = sponsors_id;
        this.nom = nom;
        this.lieu = lieu;
        this.type = type;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.image = image;
        this.nb_participant = nb_participant;
        this.prix = prix;
      
    }

    public Evenement(int id, int sponsors_id, String nom, String lieu, String type, String description, String date_debut, String date_fin, String image, int nb_participant, int prix) {
        this.id = id;
        this.sponsors_id = sponsors_id;
        this.nom = nom;
        this.lieu = lieu;
        this.type = type;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.image = image;
        this.nb_participant = nb_participant;
        this.prix = prix;
    }

 
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSponsors_id() {
        return sponsors_id;
    }

    public void setSponsors_id(int sponsors_id) {
        this.sponsors_id = sponsors_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public  String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNb_participant() {
        return nb_participant;
    }

    public void setNb_participant(int nb_participant) {
        this.nb_participant = nb_participant;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", sponsors_id=" + sponsors_id + ", nom=" + nom + ", lieu=" + lieu + ", type=" + type + ", description=" + description + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", image=" + image + ", nb_participant=" + nb_participant + ", prix=" + prix + '}';
    }

    public Evenement(int id,  String nom_sponsor, String nom, String lieu, String type, String description, String date_debut, String date_fin, String image, int nb_participant, int prix) {
        this.id = id;
     
        this.nom_sponsor =nom_sponsor;
        this.nom = nom;
        this.lieu = lieu;
        this.type = type;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.image = image;
        this.nb_participant = nb_participant;
        this.prix = prix;
    }

   
    
public String getNom_sponsor() {
        return nom_sponsor;
    }

    public void setNom_sponsor(String image) {
        this.nom_sponsor = image;
    }

    public int getNbParticipant() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
     
    
}
