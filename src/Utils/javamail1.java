/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class javamail1 {
  private String mdp ;
    public static void sendMail(String Text,String recepient) throws Exception {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "maram.brinsi@esprit.tn";
        
        String password = "98568813";

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password); //To change body of generated methods, choose Tools | Templates.
            }

        });

        Message message = prepareMessage(session, myAccountEmail, recepient,Text);

        Transport.send(message);
        System.out.println("Message sent successfully");

    }

   /* private static Message prepareMessage(Session session, String myAccountEmail, String recepient,String Text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("EFIT CLUB :");
            message.setText(Text);
            message.setContent(Text,"text/html; charset=utf-8");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }*/
    
   private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String text) throws MessagingException {
    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(myAccountEmail));
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
    message.setSubject("Email Confirmation");

    String htmlContent = "<!DOCTYPE html>" +
        "<html>" +
        "<head>" +
        "<meta charset='utf-8'>" +
        "<meta http-equiv='x-ua-compatible' content='ie=edge'>" +
        "<title>Email Confirmation</title>" +
        "<meta name='viewport' content='width=device-width, initial-scale=1'>" +
        "<style type='text/css'>" +
        "body {background-color: #fafafa;font-family: 'Source Sans Pro', sans-serif;margin: 0;padding: 0;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%;}" +
        "table {border-collapse: collapse !important;}" +
        "td {padding: 0;}" +
        "img {display: block;border: 0;height: auto;line-height: 100%;outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;}" +
        "@media only screen and (max-width: 480px) {" +
        "  .container {width: 100% !important;}" +
        "}" +
        "</style>" +
        "</head>" +
        "<body style='background-color: #fafafa;font-family: \"Source Sans Pro\", sans-serif;margin: 0;padding: 0;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%;'>" +
        "<table border='0' cellpadding='0' cellspacing='0' width='100%'>" +
        "<tr>" +
        "<td align='center'>" +
        "<table border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse !important;'>" +
        "<tr>" +
        "<td style='padding-top: 40px;padding-bottom: 20px;text-align: center;'>" +
        "<h1 style='font-size: 24px;font-weight: 700;margin: 0;text-transform: uppercase;'>Email Confirmation</h1>" +
        "</td>" +
        "</tr>" +
        "<tr>" +
        "<td style='background-color: #ffffff;border-radius: 8px;box-shadow: 0px 3px 6px 0px rgba(0,0,0,0.05);padding: 40px;'>" +
        "<p style='font-size: 16px;line-height: 24px;margin: 0 0 24px 0;text-align: center;'>Please use the following code to confirm your email:</p>" +
        "<p style='font-size: 32px;font-weight: 700;margin: 0 0 40px 0;text-align: center;'>" + text + "</p>" +
        "</td>" +
        "</tr>" +
        "</table>" +
        "</td>" +
        "</tr>" +
        "</table>" +
        "</body>" +
        "</html>";

    message.setContent(htmlContent, "text/html; charset=utf-8");

    return message;
}
/*
    public static void sendMail(String recepient, String code) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "ibtihel.chebbah@esprit.tn";
        
        String password = "201JFT1884";
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password); //To change body of generated methods, choose Tools | Templates.
            }
        });
        Message message = prepareMessage(session, myAccountEmail, recepient, code);
        Transport.send(message);
        System.out.println("Message sent successfully");
    }
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String code) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("achat ticket");
            message.setText("bonjour!\n"
                    + "\n"
                    + "votre ref ticket est :\n"
                    + "\n"
                    + code +"\n"
                    
                    + "merci de ne pas repondre");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public static void sendMai(String recepient) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "ibtihel.chebbah@esprit.tn";
        
        String password = "201JFT1884";
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password); //To change body of generated methods, choose Tools | Templates.
            }
        });
        Message message = prepareMessagee(session, myAccountEmail, recepient);
        Transport.send(message);
        System.out.println("votre demande de remboursement a ete refusé");
    }
      private static Message prepareMessagee(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("demande de remboursement");
            message.setText("votre demande de remboursement a ete refusé");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public static void sendMaill(String recepient) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "ibtihel.chebbah@esprit.tn";
        
        String password = "";
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password); //To change body of generated methods, choose Tools | Templates.
            }
        });
        Message message = prepareMessageell(session, myAccountEmail, recepient);
        Transport.send(message);
        System.out.println("votre demande de remboursement est valider");
    }
      private static Message prepareMessageell(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("demande de remboursement");
            message.setText("votre demande de remboursement est valider");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    */
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void setmdp(String m ){
        this.mdp=m;
        
    }
}
