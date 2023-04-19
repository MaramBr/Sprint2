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
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import gestionEvenement.entities.Participant;


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
        
        // Ajouter un titre en bleu au-dessus du tableau
        Paragraph title = new Paragraph("Invitation à l'événement", new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, new BaseColor(33, 150, 243)));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);
        
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
            document.add(invitation);
            document.add(Chunk.NEWLINE);
        }

        document.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


  
}
