
package userlogin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class InterfaceController implements Initializable {
   
   @FXML
     private Button closeButton; // creating id for close button
   @FXML
   private Button loginButton; // creating id for login button
   @FXML 
   private Stage stage;     // defining the stage
   @FXML 
    private Scene scene;  // defining the scene 
   @FXML 
   private Parent root;  //defining the root
   @FXML
   private TextField Uemail; // creating id for email
   @FXML
   private PasswordField Upass; // creating id for password field
   @FXML
   private Label invalidMessageLabel; //creating id for label that appears on login screen if email or pass are wrong
 
     @FXML
    private void exit(ActionEvent event){    // exit function that enable us to close the window by clicking 'X'
     stage = (Stage) closeButton.getScene().getWindow();
     stage.close();
    }

    
    @FXML
private void loginAction(ActionEvent event) throws IOException { // login function that reads parameters that user enters in login textfields
                                                                //and switch to the next screen according to the result of response that comes
                                                                //from API that connects us to EXTERNAl Database
    String email = Uemail.getText(); 
    String password = Upass.getText(); 
    String response = network.login(email, password);
    System.out.println(response);
    if(response.equalsIgnoreCase("Admin")){
    switchToAdminHomeScreen(event);
    }
    else if (response.equalsIgnoreCase("Professor")){
    switchToProfessorHomeScreen(event);
    }
    else if(response.equalsIgnoreCase("Student")){
    switchToStudentHomeScreen(event);
    }
    else{
      invalidMessageLabel.setText("Wrong email or password!");  
    }
    emailClass.email = email;  // sent the email that entered by user to a public class to can be accessed globaly as it's needed 
                               // in another functions parameters 
}
public void switchToLogin(ActionEvent event ) throws IOException {  
  root = FXMLLoader.load(getClass().getResource("Interface.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
public void switchToAdminHomeScreen(ActionEvent event) throws IOException { // function used if the user is administrator to switch user's to 
  root = FXMLLoader.load(getClass().getResource("adminHomeScreen.fxml"));   // adminHomeScreen after logging in
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
public void switchToProfessorHomeScreen(ActionEvent event) throws IOException { // function used if the user is professor to swtich user's to
  root = FXMLLoader.load(getClass().getResource("professorHomeScreen.fxml"));   //professorHomeScreen after logging in 
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
public void switchToStudentHomeScreen(ActionEvent event) throws IOException {  //function used if the user is student to switch user's screen to
  root = FXMLLoader.load(getClass().getResource("studentHomeScreen.fxml"));    // studentHomeScreen after logging in
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }

@Override
    public void initialize(URL url, ResourceBundle rb) {
       network network;     //calls class network that connects us to API
    }    
    
}
