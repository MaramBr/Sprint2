/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import Entities.Sponsor;
import Utils.MyDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import gestionEvenement.intefaces.InterfaceEvenement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author emna
 */
public class EvenementCRUD implements InterfaceEvenement {

    @Override
  public void ajouterEvenement(Evenement r) {
    try {
        String requete1 = "INSERT INTO evenement(sponsors_id,nom,lieu,type,description,date_debut,date_fin,image,nb_participant,prix) VALUES(?,?,?,?,?,STR_TO_DATE(?, '%d/%m/%Y'),STR_TO_DATE(?, '%d/%m/%Y'),?,?,?)";
        PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete1);
        pst.setInt(1, r.getSponsors_id());
        pst.setString(2, r.getNom());
        pst.setString(3, r.getLieu());
        pst.setString(4, r.getType());
        pst.setString(5, r.getDescription());
        pst.setString(6, r.getDate_debut());
        pst.setString(7, r.getDate_fin());
        pst.setString(8, r.getImage());
        pst.setInt(9, r.getNb_participant());
        pst.setInt(10, r.getPrix());
        pst.executeUpdate();
        System.out.println("Evenement ajouté avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


@Override
public void modifierEvenement(Evenement r) {
    try {
        String requete = "UPDATE evenement SET sponsors_id = ?, nom = ?, lieu = ?, type = ?, description = ?, date_debut = STR_TO_DATE(?, '%d/%m/%Y'), date_fin = STR_TO_DATE(?, '%d/%m/%Y'), image = ?, nb_participant = ?, prix = ? WHERE id = ?";
        PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete);
        pst.setInt(1, r.getSponsors_id());
        pst.setString(2, r.getNom());
        pst.setString(3, r.getLieu());
        pst.setString(4, r.getType());
        pst.setString(5, r.getDescription());
        pst.setString(6, r.getDate_debut());
        pst.setString(7, r.getDate_fin());
        pst.setString(8, r.getImage());
        pst.setInt(9, r.getNb_participant());
        pst.setInt(10, r.getPrix());
        pst.setInt(11, r.getId());
        pst.executeUpdate();
        System.out.println("Evenement modifié avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}



@Override
public void supprimerEvenement(int id) {
    try {
        String requete = "DELETE FROM evenement WHERE id=?";
        PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete);
        pst.setInt(1, id);
        pst.executeUpdate();
        System.out.println("Evenement supprimé avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


@Override
public List<Evenement> afficherEvenement() {
    List<Evenement> liste = new ArrayList<>();
    try {
        String requete = "SELECT * FROM evenement";
        Statement st = MyDB.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            Evenement evenement;
            evenement = new Evenement(
                    rs.getInt("id"),
                    rs.getInt("sponsors_id"),
                    rs.getString("nom"), 
                    rs.getString("lieu"),
                    rs.getString("type"),
                    rs.getString("description"),
                    rs.getString("date_debut"),
                    rs.getString("date_fin"),
                    rs.getString("image"),
                    rs.getInt("nb_participant"),
                    rs.getInt("prix")
                   
            );
            liste.add(evenement);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return liste;
}


public int getIdSponsor(String nom) {
        int id = 0;
        try (PreparedStatement pstmt = MyDB.getInstance().getCnx().prepareStatement("SELECT id FROM sponsor WHERE nom_sponsor= ?")) {
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
  public Sponsor getSponsorById(int id) {
    Sponsor sponsor = null;
    try {
        Connection conn = MyDB.getInstance().getCnx();
        String query = "SELECT * FROM sponsor WHERE id = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            sponsor = new Sponsor();
            sponsor.setId(rs.getInt("id"));
            sponsor.setNom_Sponsor(rs.getString("nom"));
            sponsor.setInvest(rs.getString("invest"));
            // Compléter avec les autres champs
        }
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return sponsor;
}
public String getNomSponsor(Sponsor sponsor) {
    String nomSponsor = null;
    try {
        Connection connection = MyDB.getInstance().getCnx();
        String query = "SELECT nom_sponsor FROM sponsor WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, sponsor.getId());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            nomSponsor = resultSet.getString("nom_sponsor");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return nomSponsor;
}
public Integer getIdSponsorFromNom(Sponsor sponsor) {
    Integer id = null;
    try (PreparedStatement pstmt = MyDB.getInstance().getCnx().prepareStatement("SELECT id  FROM Sponsor WHERE nom_sponsor= ?")) {
        pstmt.setString(1, sponsor.getNom_Sponsor());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id");
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    System.out.println("ID du sponsor : " + id);
    return id;
}

public String getNomSponsor(int id) {
String nom = "";
try (PreparedStatement pstmt = MyDB.getInstance().getCnx().prepareStatement("SELECT nom_sponsor FROM sponsor WHERE id= ?")) {
pstmt.setInt(1, id);
ResultSet rs = pstmt.executeQuery();
if (rs.next()) {
nom = rs.getString("nom_sponsor");
}
} catch (SQLException e) {
System.out.println(e.getMessage());
}
return nom;
}


public String getSponsorNameById(int sponsorsId) {
    String sponsorName = "";
    try {
        String requete = "SELECT nom_sponsor FROM sponsor WHERE id = ?";
        PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete);
        pst.setInt(1, sponsorsId);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            sponsorName = rs.getString("nom_sponsor");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return sponsorName;
}




public List<Evenement> recupererEvenement() throws SQLException {
    List<Evenement> evenements = new ArrayList<>();
    String s = "SELECT * FROM evenement";
    Statement st = MyDB.getInstance().getCnx().createStatement();
    ResultSet rs = st.executeQuery(s);
    while (rs.next()) {
        Evenement e = new Evenement(
                rs.getInt("id"),
                rs.getInt("sponsors_id"),
                rs.getString("nom"), 
                rs.getString("lieu"),
                rs.getString("type"),
                rs.getString("description"),
                rs.getString("date_debut"),
                rs.getString("date_fin"),
                rs.getString("image"),
                rs.getInt("nb_participant"),
               rs.getInt("prix")
        );
        evenements.add(e);
    }
    return evenements;
}




}