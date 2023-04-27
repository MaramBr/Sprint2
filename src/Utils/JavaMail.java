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


public class JavaMail {
  private String mdp ;
    public static void sendMail(String Text,String recepient) throws Exception {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "rayan.lahmar@esprit.tn";
        
        String password = "201JMT3276";

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

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient,String Text) {
/*
   private static Message prepareMessage(Session session, String myAccountEmail, String recepient,String Text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Code de vérification");
            message.setText(Text);
             String 
            htmlContent = "<html><head><style>" +
                    "* { box-sizing: border-box; }" +
                    "body {" +
                    "  background-color: #fafafa;" +
                    "  display: flex;" +
                    "  justify-content: center;" +
                    "  align-items: center;" +
                    "}" +
                    ".c-email {" +
                    "  width: 40vw;" +
                    "  border-radius: 40px;" +
                    "  overflow: hidden;" +
                    "  box-shadow: 0px 7px 22px 0px rgba(0, 0, 0, .1);" +
                    "}" +
                    ".c-email__header {" +
                    "  background-color: #0fd59f;" +
                    "  width: 100%;" +
                    "  height: 60px;" +
                    "}" +
                    ".c-email__header__title {" +
                    "  font-size: 23px;" +
                    "  font-family: 'Open Sans', sans-serif;" +
                    "  height: 60px;" +
                    "  line-height: 60px;" +
                    "  margin: 0;" +
                    "  text-align: center;" +
                    "  color: white;" +
                    "}" +
                    ".c-email__content {" +
                    "  width: 100%;" +
                    "  height: 300px;" +
                    "  display: flex;" +
                    "  flex-direction: column;" +
                    "  justify-content: space-around;" +
                    "  align-items: center;" +
                    "  flex-wrap: wrap;" +
                    "  background-color: #fff;" +
                    "  padding: 15px;" +
                    "}" +
                    ".c-email__content__text {" +
                    "  font-size: 20px;" +
                    "  text-align: center;" +
                    "  color: #343434;" +
                    "  margin-top: 0;" +
                    "}" +
                    ".c-email__code {" +
                    "  display: block;" +
                    "  width: 60%;" +
                    "  margin: 30px auto;" +
                    "  background-color: #ddd;" +
                    "  border-radius: 40px;" +
                    "  padding: 20px;" +
                    "  text-align: center;" +
                    "  font-size: 36px;" +
                    "  font-family: 'Open Sans', sans-serif;" +
                    "  letter-spacing: 10px;" +
                    "  box-shadow: 0px 7px 22px 0px rgba(0, 0, 0, .1);" +
                    "}" +
                    ".c-email__footer {" +
                    "  width: 100%;" +
                    "  height: 60px;" +
                    "  background-color: #fff;" +
                    "}" +
                    ".text-title {" +
                    "  font-family: 'Open Sans', sans-serif;" +
                    "}" +
                    ".text-center {" +
                    "  text-align: center;" +
                    "}" +
                    ".text-italic {" +
                    " ";
            message.setContent(Text,"text/html; charset=utf-8");
            
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
        
   */
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String text) throws MessagingException {
    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(myAccountEmail));
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
    message.setSubject("Code de vérification");

    // Create the HTML content of the email
    String htmlContent = "<html><head><style>" +
        "body {background-color: #fafafa;display: flex;justify-content: center;align-items: center;}"+
        ".c-email {width: 40vw;border-radius: 40px;overflow: hidden;box-shadow: 0px 7px 22px 0px rgba(0, 0, 0, .1);}"+
        ".c-email__header {background-color: #0fd59f;width: 100%;height: 60px;}"+
        ".c-email__header__title {font-size: 23px;font-family: 'Open Sans';height: 60px;line-height: 60px;margin: 0;text-align: center;color: white;}"+
        ".c-email__content {width: 100%;height: 300px;display: flex;flex-direction: column;justify-content: space-around;align-items: center;flex-wrap: wrap;background-color: #fff;padding: 15px;}"+
        ".c-email__content__text {font-size: 20px;text-align: center;color: #343434;margin-top: 0;}"+
        ".c-email__code {display: block;width: 60%;margin: 30px auto;background-color: #ddd;border-radius: 40px;padding: 20px;text-align: center;font-size: 36px;font-family: 'Open Sans';letter-spacing: 10px;box-shadow: 0px 7px 22px 0px rgba(0, 0, 0, .1);}"+
        ".c-email__footer {width: 100%;height: 60px;background-color: #fff;}"+
        ".text-title {font-family: 'Open Sans';}"+
        ".text-center {text-align: center;}"+
        ".text-italic {font-style: italic;}"+
        ".opacity-30 {opacity: 0.3;}"+
        ".mb-0 {margin-bottom: 0;}"+
        "</style></head><body>"+
        "<div class='c-email'>"+
        "<div class='c-email__header'>"+
        "<h1 class='c-email__header__title'>Your Verification Code</h1>"+
        "</div>"+
        "<div class='c-email__content'>"+
        "<p class='c-email__content__text text-title'>Enter this verification code in field:</p>"+
        "<div class='c-email__code'>" +
        "<span class='c-email__code__text'>" + text + "</span>" +
        "</div>"+
        "<p class='c-email__content__text text-italic opacity-30 text-title mb-0'>Cordialement, L'équipe de support technique de E-FIT</p>"+
        "</div>"+
        "<div class='c-email__footer'></div>"+
        "</div>"+
        "</body></html>";
    message.setContent(htmlContent, "text/html; charset=utf-8");

    return message;
}

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
        String password = "201JFT1884";
