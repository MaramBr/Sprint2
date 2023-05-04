/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */package Services;
import java.io.FileOutputStream;
import Entities.Evenement;
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
import Entities.Participant;
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

        // Add a title in orange at the top of the page
        Paragraph title = new Paragraph("Invitation à l'événement", new Font(Font.FontFamily.TIMES_ROMAN, 25, Font.BOLDITALIC, new BaseColor(255, 170, 0)));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);

        // Loop through the list of participants and add their information as an invitation
        for (Participant participant : participants) {
            Paragraph invitation = new Paragraph();
            invitation.add(new Chunk("Cher(e) " + participant.getNom() + ",\n\n", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLDITALIC)));
            invitation.add(new Chunk("Vous êtes cordialement invité(e) à notre événement " + participant.getNomev() + ".\n", new Font(Font.FontFamily.HELVETICA, 17)));
            invitation.add(new Chunk("Veuillez confirmer votre présence en nous répondant à cet email E_Fit@gmail.com  ou en nous appelant au " + participant.getTel() + ".\n\n", new Font(Font.FontFamily.HELVETICA, 17)));
            document.add(invitation);
        }

        // Add a thank you message at the end with the event name
        Paragraph thanks = new Paragraph("Nous vous remercions d'avoir participé à notre événement " + participants.get(0).getNomev() + " !", new Font(Font.FontFamily.TIMES_ROMAN, 19, Font.BOLDITALIC, new BaseColor(255, 170, 0)));
        thanks.setAlignment(Element.ALIGN_CENTER);
        thanks.setSpacingBefore(50f);
        document.add(thanks);

        document.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
