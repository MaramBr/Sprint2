/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Reclamation;
import java.util.List;
import Entities.Genre;
import Utils.MyDB;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Majdi
 */
public class ServiceReclamation implements IService<Reclamation> {

    Connection cnx;
    LocalDate currentDate = LocalDate.now();

    public ServiceReclamation() {
        cnx = MyDB.getInstance().getCnx();
    }

   
    public void ajouter(Reclamation t) {
        t.setStatus("en attente");
        t.setTraitement("pas de traitement");
        String dateSysteme = currentDate.toString(); // Convertir la date en String
        t.setDate(dateSysteme);
        String qry = "INSERT INTO `reclamation`(`genre_id`, `date_r`, `titre_r`, `description_r`, `status_r`, `traitement`) VALUES ('" + t.getIdG() + "','" + t.getDate() + "','" + t.getTitre() + "','" + t.getDescription() + "','" + t.getStatus() + "','" + t.getTraitement() + "')";
        try {
            Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

  
    public List<Reclamation> afficher() {
        List<Reclamation> reclamations = new ArrayList();
        String qry = "SELECT r.titre, r.description, r.date, r.status, g.libelle FROM reclamation r JOIN genre g ON r.id_genre = g.id;";

        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setIdRec(rs.getInt(1));
                r.setTitre(rs.getString("r.titre_r"));
                r.setDescription(rs.getString("r.description_r"));
                r.setDate(rs.getString("r.date_r"));
                // r.setIdG(rs.getInt("genre_id"));
                r.setGenre(rs.getString("g.libelle"));
                r.setStatus(rs.getString("r.status_r"));
                r.setTraitement(rs.getString("r.traitement"));
                reclamations.add(r);
            }

            return reclamations;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return reclamations;
    }

    
    public boolean modifier(Reclamation t) {
        try {
            String qry = "UPDATE `reclamation` SET `id`='" + t.getIdRec() + "',`date_r`='" + t.getDate() + "',`titre_r`='" + t.getTitre() + "',`description_r`='" + t.getDescription() + "',`status_r`='" + t.getStatus() + "',`traitement`='" + t.getTraitement() + "'WHERE `id` = '" + t.getIdRec() + "'";
            Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    
    public void supprimer(Reclamation t) {
        String qry = "DELETE FROM `reclamation` WHERE id=?";
        try {
            PreparedStatement pstmt = cnx.prepareStatement(qry);
            pstmt.setInt(1, t.getIdRec());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public ObservableList<Reclamation> afficher2() {
        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();
        String qry = "SELECT r.id , g.libelle, r.date_r, r.titre_r, r.description_r, r.status_r, r.traitement FROM reclamation r JOIN genre g ON r.genre_id = g.id;";

        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setIdRec(rs.getInt(1));
                r.setTitre(rs.getString("r.titre_r"));
                r.setDescription(rs.getString("r.description_r"));
                r.setDate(rs.getString("r.date_r"));
                // r.setIdG(rs.getInt("genre_id"));
                r.setGenre(rs.getString("g.libelle"));
                r.setStatus(rs.getString("r.status_r"));
                r.setTraitement(rs.getString("r.traitement"));
                reclamations.add(r);
            }

            return reclamations;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return reclamations;
    }

    public ObservableList<Reclamation> afficher3() {
        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();
        String qry = "SELECT * FROM `reclamation`";

        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setIdRec(rs.getInt("id"));
                r.setTitre(rs.getString("titre_r"));
                r.setDescription(rs.getString("description_r"));
                r.setDate(rs.getString("date_r"));
                r.setIdG(rs.getInt("genre_id"));
                r.setStatus(rs.getString("status_r"));
                r.setTraitement(rs.getString("traitement"));
                reclamations.add(r);
            }

            return reclamations;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return reclamations;
    }

    public List<Reclamation> rechercher(String titre_r) {
        List<Reclamation> reclamationsList = new ArrayList<>();
        try {
            String req = "SELECT * FROM `reclamation` where titre_r = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, titre_r);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setIdRec(rs.getInt("id"));
                r.setTitre(rs.getString("titre_r"));
                r.setDescription(rs.getString("description_r"));
                r.setDate(rs.getString("date_r"));
                r.setIdG(rs.getInt("genre_id"));
                r.setStatus(rs.getString("status_r"));
                r.setTraitement(rs.getString("traitement"));
                reclamationsList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamationsList;
    }

    public void notification(String message) {
        if (SystemTray.isSupported()) {
            try {
                // Initialiser SystemTray
                SystemTray tray = SystemTray.getSystemTray();

                // Créer une icône pour la notification
                Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
                TrayIcon trayIcon = new TrayIcon(image, "Notification");

                // Ajouter l'icône au SystemTray
                tray.add(trayIcon);

                // Afficher la notification
                trayIcon.displayMessage("Notification", message, TrayIcon.MessageType.INFO);
            } catch (AWTException e) {
                System.err.println("Erreur lors de l'initialisation du SystemTray: " + e);
            }
        } else {
            System.out.println("SystemTray n'est pas pris en charge");
        }
    }

}
