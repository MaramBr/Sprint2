/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */package gestionevenement.services;
import java.io.FileOutputStream;
import gestionEvenement.entities.Evenement;
import java.util.List;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import gestionEvenement.entities.Participant;
import java.io.FileNotFoundException;


/**
 *
 * @author emna
 */
public class PDFGenerator {
public void generatePDF(List<Participant> participants, String fileName) {
    Document document = new Document();
    try {
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();
        
        // Ajouter une table avec une cellule orange qui contient le contenu
        PdfPTable table = new PdfPTable(1);
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(new BaseColor(255, 165, 0));
        cell.setPadding(20f);
        
        // Ajouter un titre en bleu au-dessus de la table
        Paragraph title = new Paragraph("Invitation à l'événement", new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, new BaseColor(33, 150, 243)));
        title.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(title);
        cell.addElement(Chunk.NEWLINE);
        
        // Parcourir la liste des participants et ajouter leurs informations sous forme d'invitation
        for (Participant participant : participants) {
            Paragraph invitation = new Paragraph();
            invitation.add(new Chunk("Evenement: ", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD)));
            invitation.add(new Chunk(participant.getNomev() + "\n", new Font(Font.FontFamily.HELVETICA, 14)));
            invitation.add(new Chunk("Nom: ", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD)));
            invitation.add(new Chunk(participant.getNom() + "\n", new Font(Font.FontFamily.HELVETICA, 14)));
            invitation.add(new Chunk("Prénom: ", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD)));
            invitation.add(new Chunk(participant.getPrenom() + "\n", new Font(Font.FontFamily.HELVETICA, 14)));
            invitation.add(new Chunk("E-mail: ", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD)));
            invitation.add(new Chunk(participant.getEmail() + "\n", new Font(Font.FontFamily.HELVETICA, 14)));
            invitation.add(new Chunk("Âge: ", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD)));
            invitation.add(new Chunk(String.valueOf(participant.getAge()) + "\n", new Font(Font.FontFamily.HELVETICA, 14)));
            invitation.add(new Chunk("Téléphone: ", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD)));
            invitation.add(new Chunk(String.valueOf(participant.getTel()) + "\n", new Font(Font.FontFamily.HELVETICA, 14)));
            cell.addElement(invitation);
            cell.addElement(Chunk.NEWLINE);
        }
            // Ajouter un paragraphe de remerciement à la fin
Paragraph thanks = new Paragraph("Nous vous remercions d'avoir participé à notre événement !", new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, new BaseColor(255, 255, 255)));
thanks.setAlignment(Element.ALIGN_CENTER);
thanks.setSpacingBefore(50f);
document.add(thanks);
        // Ajouter la cellule à la table et ajouter la table au document
        table.addCell(cell);
        document.add(table);
        
    

  


        document.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}



}
