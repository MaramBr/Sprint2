/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.UserService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import net.glxn.qrgen.QRCode;

/**
 * FXML Controller class
 *
 * @author rayan
 */
public class AdminController implements Initializable {
  @FXML
    private ChoiceBox<String>roles;
 int index=-1;
    private String []type={"ADMIN","Coach","USER"};
     UserService userService = new UserService();
    ObservableList<User> UserList;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField nom;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> nomU;
    @FXML
    private TableColumn<User, String> prenomU;
    @FXML
    private TableColumn<User, String> emailU;
    @FXML
    private TableColumn<User, String> image;
    @FXML
    private TableColumn<User, String> rolesU;
    @FXML
    private PasswordField mdp;
    @FXML
    private ImageView image2;
    String path="C:\\Users\\Maram\\Desktop\\integration\\Sprint2\\src\\images\\userv1.png";
    @FXML
    private TableColumn<User,Integer> idc;
    @FXML
    private TextField idfield;
    @FXML
    private TextField Recherche;
    @FXML
    private Button stat;
    @FXML
    private TableColumn<?, ?> etatU;
    @FXML
    private ImageView Qr;
    private Button bloquer;
    private Button debloquer;
    @FXML
    private Button block;
    @FXML
    private FontAwesomeIconView bloquer1;
    @FXML
    private FontAwesomeIconView bloquer2;
 
    /**
     * Initializes the controller class.
     */
   
    
    public void updateTable() {
        ObservableList<User> rdvList = FXCollections.observableArrayList(userService.readAll());

        nomU.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomU.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailU.setCellValueFactory(new PropertyValueFactory<>("email"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
          rolesU.setCellValueFactory(new PropertyValueFactory<>("roles"));
        System.out.println("affichage" + userService.readAll());
        table.setItems(rdvList);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        roles.getItems().addAll(type);
        Afficher();
    }

    @FXML
    private void ajouterimage(ActionEvent event) {
 
     FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Image");
    fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
    // load the selected image into the image view
    path=selectedFile.getAbsolutePath();
    Image image = new Image(selectedFile.toURI().toString());
    image2.setImage(image);
    }
    }
void VoirUser(ActionEvent event) {
         User selectedUser = table.getSelectionModel().getSelectedItem();
    
    if (selectedUser != null) {
      
        nom.setText(selectedUser.getNom());
        prenom.setText(selectedUser.getPrenom());
        mdp.setText(selectedUser.getPassword());
        email.setText(selectedUser.getEmail());
     
        roles.setValue(selectedUser.getRoles());
       
         Image image = new Image(new File(selectedUser.getImage()).toURI().toString());
            image2.setImage(image);
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un utilisateur à modifier.");
        alert.showAndWait();
    }

    }
      
    

      
        
  
    
    
     @FXML
    private void ajoute(ActionEvent event) {
        Image photo=image2.getImage();  ///image Done
        
                String nomValue = nom.getText();
                String prenomValue = prenom.getText();
                String pwdValue = mdp.getText();
                String emailValue = email.getText();
                String typeUserValue = "[\"ROLE_"+roles.getValue()+"\"]";
                String imagePath = path;
        
                 // Validate email input
    if (emailValue.isEmpty() || !emailValue.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir une adresse email valide.");
        alert.showAndWait();
        return;
    }
    
    if (nomValue.isEmpty() || !nomValue.matches("[a-zA-Z]+")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Un Nom ne doit contenir que des lettres.");
        alert.showAndWait();
        return;
    }
     
      if (prenomValue.isEmpty() || !prenomValue.matches("[a-zA-Z]+")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Un prenom ne doit contenir que des lettres.");
        alert.showAndWait();
        return;
    }
       if (pwdValue.isEmpty() || !(pwdValue.length() > 5)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("mot de passe doit Supérieur a 5 caracteres");
        alert.showAndWait();
        return;
    }
     // Check if email is already used by another user
    UserService userService = new UserService();
    if (userService.isEmailUsed(emailValue)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("L'adresse email est déjà utilisée par un autre utilisateur.");
        alert.showAndWait();
        return;
    }
    
    // Create new user object
    User u = new User(nomValue, prenomValue, pwdValue, emailValue, typeUserValue, imagePath);
    
    // Insert new user into the database
    userService.insert(u);
    
    // Show success message
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Utilisateur ajouté avec succès !");
    alert.showAndWait();
    
    // Clear input fields
    nom.setText("");
    prenom.setText("");
   mdp.setText("");
    email.setText("");
    roles.getSelectionModel().clearSelection();
    image2.setImage(null);
         Afficher();
    }
    
     
    
    @FXML
    private void modifier(ActionEvent event) {
        String typeString ;
     User selectedUser = table.getSelectionModel().getSelectedItem();
    if (selectedUser != null) {
        String nomUser = nom.getText();
        String prenomUser = prenom.getText();
        String password = mdp.getText();
        String emailUser = email.getText();
         //System.out.println("The value: " + roles.getValue());
        if (emailUser.isEmpty() || !emailUser.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir une adresse email valide.");
        alert.showAndWait();
        return;
    }
    
    if (nomUser.isEmpty() || !nomUser.matches("[a-zA-Z]+")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Un Nom ne doit contenir que des lettres.");
        alert.showAndWait();
        return;
    }
     
      if (prenomUser.isEmpty() || !prenomUser.matches("[a-zA-Z]+")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Un prenom ne doit contenir que des lettres.");
        alert.showAndWait();
        return;
    }
       if (password.isEmpty() || !(password.length() > 5)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("mot de passe doit Sup a 5 caracteres");
        alert.showAndWait();
        return;
    }
        
        typeString = "[\"ROLE_"+roles.getValue()+"\"]";
          String imagePath=selectedUser.getImage();
Image photo=image2.getImage();
        if (nomUser.isEmpty() || prenomUser.isEmpty() || emailUser.isEmpty() || typeString == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs obligatoires.");
            alert.showAndWait();
            return;
        }
        
         
        selectedUser.setNom(nomUser);
        selectedUser.setPrenom(prenomUser);
        selectedUser.setPassword(password);
        selectedUser.setEmail(emailUser);
     selectedUser.setPassword(password);
        selectedUser.setRoles(typeString);
        
       selectedUser.setImage(path);

        UserService userService = new UserService();
        userService.update(selectedUser);
        

        // Afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Utilisateur modifié avec succès !");
        alert.showAndWait();

        // Réinitialiser les champs
        nom.setText("");
        prenom.setText("");
        mdp.setText("");
        email.setText("");
       roles.getSelectionModel().clearSelection();
        image2.setImage(null);
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un utilisateur à modifier.");
        alert.showAndWait();
    }
     Afficher();
   
    }
    
    @FXML
    private void Supprimer(ActionEvent event) {
         if (table.getSelectionModel().getSelectedItem() != null) {
        // Récupérer les données de l'événement sélectionné
        User selectedUser = table.getSelectionModel().getSelectedItem();
        UserService es = new UserService();
        es.delete(selectedUser);
        
          }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succés");
        alert.setHeaderText(null);
        alert.setContentText("Utilisateur supprimé avec succès !");
        alert.showAndWait();
        
        Afficher();
        
    }
    
    public void Afficher() {
        UserService US =new UserService();
        
        List<User> users=US.readAll();
        
        
         
         
           idc.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomU.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomU.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailU.setCellValueFactory(new PropertyValueFactory<>("email"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
          rolesU.setCellValueFactory(new PropertyValueFactory<>("roles"));
             etatU.setCellValueFactory(new PropertyValueFactory<>("is_active"));
  ObservableList<User> userData = FXCollections.observableArrayList(users);
         table.setItems(userData);

         
        
    }
     @FXML
    private void refresh(ActionEvent event) {
 
      nom.setText("");
        prenom.setText("");
        mdp.setText("");
        email.setText("");
       roles.getSelectionModel().clearSelection();
        image2.setImage(null);
        Qr.setImage(null);
        
             block.setText("Bloquer/Débloquer");
            bloquer1.setVisible(true);
            bloquer2.setVisible(true);
        Afficher();
    }
    @FXML
  private void getSelected(MouseEvent event) {
 
   User c=table.getSelectionModel().getSelectedItem();
         index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
         if(c.getIs_active()==0){
             //bloquer.setVisible(true);
            //debloquer.setVisible(false);
            block.setText("Bloquer");
            bloquer1.setVisible(true);
            bloquer2.setVisible(false);
         }
         else if(c.getIs_active()==1){
             //bloquer.setVisible(false);
             //debloquer.setVisible(true);
             
             block.setText("Déloquer");
            bloquer2.setVisible(true);
             bloquer1.setVisible(false);
         }
       
        
        idfield.setText((idc.getCellData(index).toString()));
        nom.setText(nomU.getCellData(index));
        prenom.setText(prenomU.getCellData(index));
        email.setText(emailU.getCellData(index));
         roles.setValue(c.getRoles());
         
        
         System.out.println("The value of roles is: " + roles.getValue());
         //System.out.println(rolesU);
           Image image = new Image(new File(c.getImage()).toURI().toString());
            image2.setImage(image);
          
          
    
     
     
     
}
  
  @FXML
  void retour(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  


     
            } catch (IOException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
@FXML
public void recherche() {
    // Ajouter un listener sur le champ de recherche pour effectuer la recherche à chaque modification du texte
    Recherche.textProperty().addListener((observable, oldValue, newValue) -> {
        // Filtrer les réclamations en utilisant le nouveau texte de recherche
       List<User> userrecherche = userService.readAll().stream()
        .filter(user -> 
            user.getNom().toLowerCase().contains(newValue.toLowerCase()) ||
            user.getPrenom().toLowerCase().contains(newValue.toLowerCase()) ||
            user.getEmail().toLowerCase().contains(newValue.toLowerCase()) ||
            user.getRoles().toLowerCase().contains(newValue.toLowerCase())
        )
        .collect(Collectors.toList());

        // Mettre à jour la TableView avec les réclamations filtrées
        table.setItems(FXCollections.observableArrayList(userrecherche));
    });
}

 @FXML
    void triNom(ActionEvent event) {
          UserService us = new UserService();
 nomU.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomU.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailU.setCellValueFactory(new PropertyValueFactory<>("email"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
          rolesU.setCellValueFactory(new PropertyValueFactory<>("roles"));
       UserList=us.getAllTriNom();
             table.setItems(UserList);
    }//Ban a user
@FXML
void ban(ActionEvent event){
    
    String typeString ;
     User selectedUser = table.getSelectionModel().getSelectedItem();
    if (selectedUser != null) {
        
         
        
        UserService userService = new UserService();
        userService.banUser(selectedUser.getId());
        

        // Afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        if(selectedUser.getIs_active()==0){
            alert.setContentText("Utilisateur est bloqué !");
        alert.showAndWait();
        }
        else{
                   alert.setContentText("Utilisateur est débloqué !");
        alert.showAndWait(); 
                }
            
        
       

        // Réinitialiser les champs
        nom.setText("");
        prenom.setText("");
        mdp.setText("");
        email.setText("");
       roles.getSelectionModel().clearSelection();
        image2.setImage(null);
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un utilisateur ");
        alert.showAndWait();
    }
     Afficher();
     
}
void bloquer(ActionEvent event){
    
    String typeString ;
     User selectedUser = table.getSelectionModel().getSelectedItem();
    if (selectedUser != null) {
        
         if(selectedUser.getIs_active()==0){
             bloquer.setVisible(true);
         }
        
        UserService userService = new UserService();
        userService.banUser(selectedUser.getId());
        

        // Afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        
            alert.setContentText("Utilisateur est bloqué !");
        alert.showAndWait();
        
       
            
        
       

        // Réinitialiser les champs
        nom.setText("");
        prenom.setText("");
        mdp.setText("");
        email.setText("");
       roles.getSelectionModel().clearSelection();
        image2.setImage(null);
         } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un utilisateur ");
        alert.showAndWait();
    }
     Afficher();
     
}

void debloquer(ActionEvent event){
    
    String typeString ;
     User selectedUser = table.getSelectionModel().getSelectedItem();
    if (selectedUser != null) {
        
         
        
        UserService userService = new UserService();
        userService.debloquer(selectedUser.getId());
        

        // Afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        
            alert.setContentText("Utilisateur est débloqué !");
        alert.showAndWait();
        
       
            
        
       

        // Réinitialiser les champs
        nom.setText("");
        prenom.setText("");
        mdp.setText("");
        email.setText("");
       roles.getSelectionModel().clearSelection();
        image2.setImage(null);
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un utilisateur ");
        alert.showAndWait();
    }
     Afficher();
     
}
    
    @FXML
    private void statistique(ActionEvent event) {
         ObservableList<User> Users = table.getItems();
    Map<String, Integer> statistiques = new HashMap<>();

    // Calcul des statistiques
    for (User r : Users) {
        String type = r.getRoles();
        if (statistiques.containsKey(type)) {
            statistiques.put(type, statistiques.get(type) + 1);
        } else {
            statistiques.put(type, 1);
        }
    }

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    int totalUsers = 0;
    for (Map.Entry<String, Integer> entry : statistiques.entrySet()) {
        String type = entry.getKey();
  type = type.replace("[\"ROLE_", "").replace("\"]", "");
        int nbUsers = entry.getValue();
        totalUsers += nbUsers;
        pieChartData.add(new PieChart.Data(type + " (" + nbUsers + ")", nbUsers));
    }

    // Calcul des pourcentages
    for (PieChart.Data data : pieChartData) {
        double pourcentage = (data.getPieValue() / totalUsers) * 100;
        String label = data.getName() + " - " + String.format("%.2f", pourcentage) + "%";
        data.setName(label);
    }

    PieChart chart = new PieChart(pieChartData);
    chart.setTitle("Statistiques des utilisateurs par Role");

    Stage stage = new Stage();
    Scene scene = new Scene(new Group(chart), 600, 400);
    stage.setScene(scene);
    stage.show();
        
    }
    
            
                public static String projectPath = System.getProperty("user.dir").replace("\\", "/");
    private void QRcode(User u) throws FileNotFoundException, IOException {
        String contenue = "Nom : " + u.getNom()+"Prenom :"+u.getPrenom()+ "\n" + "Email: " + u.getEmail()+ "\n" + "Role: " + u.getRoles()
                ; 
        ByteArrayOutputStream out = QRCode.from(contenue).to(net.glxn.qrgen.image.ImageType.JPG).stream();
        File f = new File(projectPath + "\\src\\images\\" + u.getId()+ ".jpg");
        System.out.println(f.getPath());
        FileOutputStream fos = new FileOutputStream(f); //creation du fichier de sortie
        fos.write(out.toByteArray()); //ecrire le fichier du sortie converter
        fos.flush(); // creation final
        Image image = new Image(f.toURI().toString());
        Qr.setImage(image);


     }
@FXML
    private void Generate(ActionEvent event) throws IOException {
                     ObservableList<User> allUsers,SingleUser ;
             allUsers=table.getItems();
             SingleUser=table.getSelectionModel().getSelectedItems();
             User selectedUser = table.getSelectionModel().getSelectedItem();
             if (selectedUser != null) {
             //User A = SingleUser.get(0);

            QRcode(selectedUser);
             }
             else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un utilisateur ");
        alert.showAndWait();
    }
    }

    @FXML
void triNomDESC(ActionEvent event) {
          UserService us = new UserService();
 nomU.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomU.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailU.setCellValueFactory(new PropertyValueFactory<>("email"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
          rolesU.setCellValueFactory(new PropertyValueFactory<>("roles"));
       UserList=us.TriNomDESC();
             table.setItems(UserList);
    }//Ban a user

    
}
