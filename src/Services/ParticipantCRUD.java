/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Evenement;
import Entities.Participant;
import gestionEvenement.intefaces.InterfaceParticipant;
import Utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author emna
 */
public class ParticipantCRUD  implements InterfaceParticipant {
    
    @Override
public void ajouterParticipant(Participant p) {
    try {
        String insertQuery = "INSERT INTO participant(nom,prenom,email,age,tel) VALUES(?,?,?,?,?)";
        PreparedStatement insertStmt = MyConnection.getInstance().getCnx().prepareStatement(insertQuery);
       
        insertStmt.setString(1, p.getNom());
        insertStmt.setString(2, p.getPrenom());
        insertStmt.setString(3, p.getEmail());
         insertStmt.setInt(4, p.getAge());
        insertStmt.setInt(5, p.getTel());
        insertStmt.executeUpdate();
        System.out.println("Participant ajouté avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

/*public void ajouterParticipant(Participant p, Evenement e) {
    try {
        String insertQuery = "INSERT INTO participant(nom,prenom,email,age,tel) VALUES(?,?,?,?,?)";
        PreparedStatement insertStmt = MyConnection.getInstance().getCnx().prepareStatement(insertQuery);
       
        insertStmt.setString(1, p.getNom());
        insertStmt.setString(2, p.getPrenom());
        insertStmt.setString(3, p.getEmail());
        insertStmt.setInt(4, p.getAge());
        insertStmt.setInt(5, p.getTel());
        insertStmt.executeUpdate();
        System.out.println("Participant ajouté avec succès !");
        
        // Mettre à jour le nombre de participants de l'événement
        int nbParticipant = e.getNb_participant();
        if (nbParticipant > 0) {
            e.setNb_participant(nbParticipant - 1);
            String updateQuery = "UPDATE evenement SET nb_participant = ? WHERE id = ?";
            PreparedStatement updateStmt = MyConnection.getInstance().getCnx().prepareStatement(updateQuery);
            updateStmt.setInt(1, e.getNb_participant());
            updateStmt.setInt(2, e.getId());
            updateStmt.executeUpdate();
            System.out.println("Nombre de participants de l'événement mis à jour !");
        } else {
            System.out.println("Impossible de mettre à jour le nombre de participants de l'événement : il n'y a plus de place disponible !");
        }
        
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
*/

@Override
public void modifierParticipant(Participant p) {
    try {
        String requete = "UPDATE participant SET nom=?, prenom=?, email=?, age=?, tel=? WHERE id=?";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
       
        pst.setString(1, p.getNom());
        pst.setString(2, p.getPrenom());
        pst.setString(3, p.getEmail());
        pst.setInt(4, p.getAge());
        pst.setInt(5, p.getTel());
        pst.setInt(6, p.getId());
        pst.executeUpdate();
        System.out.println("Participant modifié");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

@Override
public void supprimerParticipant(int id) {
    try {
        String requete = "DELETE FROM participant WHERE id=?";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setInt(1, id);
        pst.executeUpdate();
        System.out.println("Participant supprimé");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

@Override
public List<Participant> afficherParticipant() {
    List<Participant> participantList = new ArrayList<>();
    try {
        String requete = "SELECT * FROM participant";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Participant participant = new Participant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getInt("age"), rs.getInt("tel"));
            participantList.add(participant);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return participantList;
}


public ObservableList<Participant> searchParticipants(String searchTerm) {
    ObservableList<Participant> participants = FXCollections.observableArrayList();
    try {
        String query = "SELECT * FROM participant WHERE nom LIKE ? OR prenom LIKE ? OR email LIKE ? OR age LIKE ? OR tel LIKE ?";
        PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query);
        preparedStatement.setString(1, "%" + searchTerm + "%");
        preparedStatement.setString(2, "%" + searchTerm + "%");
        preparedStatement.setString(3, "%" + searchTerm + "%");
        preparedStatement.setString(4, "%" + searchTerm + "%");
        preparedStatement.setString(5, "%" + searchTerm + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Participant participant = new Participant(
                resultSet.getInt("id"),
                resultSet.getString("nom"),
                resultSet.getString("prenom"),
                resultSet.getString("email"),
                resultSet.getInt("age"),
                resultSet.getInt("tel")
            );
            participants.add(participant);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return participants;
}

    
}
