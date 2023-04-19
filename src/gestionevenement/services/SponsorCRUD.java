/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvenement.services;

import gestionEvenement.entities.Sponsor;
import gestionEvenement.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import gestionEvenement.intefaces.InterfaceSponsor;
import java.sql.Connection;
import javax.activation.DataSource;

/**
 *
 * @author emna
 */
public class SponsorCRUD implements InterfaceSponsor{



    @Override
    public void ajouterSponsor(Sponsor s) {
        try {
            String insertQuery = "INSERT INTO sponsor(nom_Sponsor, email, invest) VALUES(?,?,?)";
            PreparedStatement insertStmt = MyConnection.getInstance().getCnx().prepareStatement(insertQuery);
            insertStmt.setString(1, s.getNom_Sponsor());
            insertStmt.setString(2, s.getEmail());
            insertStmt.setString(3, s.getInvest());
            insertStmt.executeUpdate();
            System.out.println("Sponsor ajouté avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierSponsor(Sponsor s) {
        try {
            String requete = "UPDATE sponsor SET nom_Sponsor=?, email=?, invest=? WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, s.getNom_Sponsor());
            pst.setString(2, s.getEmail());
            pst.setString(3, s.getInvest());
            pst.setInt(4, s.getId());
            pst.executeUpdate();
            System.out.println("Sponsor modifié");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerSponsor(int id) {
        try {
            String requete = "DELETE FROM sponsor WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Sponsor supprimé");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Sponsor> afficherSponsor() {
        List<Sponsor> sponsorList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM sponsor";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Sponsor sponsor = new Sponsor(rs.getInt("id"), rs.getString("nom_Sponsor"), rs.getString("email"), rs.getString("invest"));
                sponsorList.add(sponsor);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return sponsorList;
    }
    
    public int getIdSponsor(String nom) {
        int id = 0;
        try (PreparedStatement pstmt = MyConnection.getInstance().getCnx().prepareStatement("SELECT id FROM sponsor WHERE nom_sponsor= ?")) {
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

   





public String getSponsorNameById(int sponsorsId) {
    String sponsorName = "";
    try {
        String requete = "SELECT nom_sponsor FROM sponsor WHERE id = ?";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
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


public int getIdByNom(String nom) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int id = -1;
    try {
       
        String query = "SELECT id FROM sponsor WHERE nom_sponsor = ?";
        ps =MyConnection.getInstance().getCnx().prepareStatement(query);
        ps.setString(1, nom);
        rs = ps.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return id;
}





 
}
