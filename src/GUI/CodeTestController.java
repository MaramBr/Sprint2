/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.UserService;
import Utils.JavaMail;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static javax.swing.text.html.parser.DTDConstants.MS;

/**
 * FXML Controller class
 *
 * @author rayan
 */
public class CodeTestController implements Initializable {

    @FXML
    private TextField code1;
    @FXML
    private Button send;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void codereset(String email) throws Exception{
        // String c=code1.getText()+code2.getText()+code3.getText();
      
        // int n = Integer.parseInt(c);
        File fileLogo = new File("images/logo2.png");
        Image logoI = new Image(fileLogo.toURI().toString());
        //log.setImage(logoI);image view logo
          int min = 1000;  
       int max = 9999;  
//Generate random int value from 200 to 400     
     int b = (int)(Math.random()*(max-min+1)+min); 
     String s=Integer.toString(b);
     System.out.println(b);  
      //MailService MS = new MailService();
      UserService U = new UserService();
      User us = U.getUser(email);
      System.out.println(us.getPrenom()+us.getEmail());
        //MS.send(us.getPrenom()+ us.getNom()+"Mr ou Madame"+" voila ton code est "+b, email);
         JavaMail.sendMail("Bonjour,\n\n"+"Mr/Mme"+"\t"+ us.getNom()+"\t"+us.getPrenom()+"\t"+"Veuillez saisir le code suivant pour vérifier votre compte: "+"\t"+b+"\n\n" +
                 "Cordialement,\n" +
                 "L'équipe de support technique de E-FIT",email);
        send.setOnAction(e->{
            String c=code1.getText();
        if(c.equals(s)){
             FXMLLoader blog_parent = new FXMLLoader(getClass().getResource("/GUI/Reset.fxml"));
        try {
           
            Parent root1 = blog_parent.load();
                   ResetController RC = blog_parent.getController();
                   RC.update(email);
                   
                  send.getScene().setRoot(root1);
       // Scene blog_scene = new Scene(blog_parent);
       /// Stage app_stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        
       // app_stage.setScene(blog_scene);
       // app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MdpoublieeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Code incorrect");
        alert.showAndWait();
        System.out.println("noo");
       // System.out.println(s);
        System.out.println(c);
        }
        });
       
    
    
    
        
        // TODO
    
}
       @FXML
  void retour(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("mdpoubliee.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  


     
            } catch (IOException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
}