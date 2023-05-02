/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.net.URL;
import java.util.HashMap;

import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;


/**
 * FXML Controller class
 *
 * @author Majdi
 */
public class ChatgptController implements Initializable {

    @FXML
    private AnchorPane an;
    @FXML
    private TextField ASK;
   
    @FXML
    private TextArea textchat;
    private Map<String, String> responses;
   
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     responses = new HashMap<>();
        // TODO
         responses.put("bonjour", "comment puis je vous aidez");
        responses.put("donner moi le meilleurs cours dans votre salle de sport? ", "zumba");
        
        responses.put("combien dure un cours kick", "40 min");
        responses.put("les bienfaits de la salle de sport? ", "bien pour la santé,bien a la cardiovascularité");
        responses.put("donnez moi le nom de l admin", "aziz");
         responses.put("donne moi le lien de l'esprit", " https://esprit-tn.com");
        responses.put("salut bot", "comment puis je vous aidez? ");
         responses.put("qui etes vous?", "je suis un bot creer par weGym ");
          responses.put("weGym?", "weGym! weGym!  ");
             TextFields.bindAutoCompletion(ASK, responses.keySet());
           TextField textField =  TextFields.createClearablePasswordField();
  
    }

    @FXML
    private void UserA(ActionEvent event) {
     String input = ASK.getText();
        String response = responses.getOrDefault(input, "désolé j'ai pas la réponse ");
        textchat.appendText("User: " + input + "\n");
        textchat.appendText("Chatbot: " + response + "\n\n");
        ASK.clear();}
}