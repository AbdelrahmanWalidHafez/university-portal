
package userlogin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
public class ProfessorRegestrationController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb) { //This method is called automatically after the FXML file has been loaded.
                                                         //It sets up the resultColumn to dispaly outputs from the Result objects to display data in the table view. 
       // Ensure the resultColumn is set to display the result property
        resultColumn.setCellValueFactory(cellData -> cellData.getValue().resultProperty());
    }  
    @FXML
    private Button closeButton;
    @FXML
    private Button  PAdeleteBtn; // id for delete button
    @FXML
    private Button  PAaddBtn; // id for add button 
    @FXML
    private Button  PAprevBtn; // id for prev button
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    private TextField APssn; // id for ssn text field
    @FXML
    private TextField APfn; // id for first name text field
    @FXML
    private TextField APmn; // id for middle nametext field
    @FXML
    private TextField APln;// id for last name text field
    @FXML
    private TextField APaddress;// id for address text field
    @FXML
    private TextField APdob;// id for date of birth text field
    @FXML
    private TextField APphoneNo;// id for phone number text field
    @FXML
    private TextField APgender;// id for gender text field
    @FXML
    private TextField APusertype;// id for usertype text field
    @FXML
    private TextField APemail;// id for email text field

                                                    // initializing TableView for displaying registration results which has id resultTableView
    @FXML                                                     // id for the column in the table
    private TableView<Result> resultTableView;
    @FXML
    private TableColumn<Result, String> resultColumn;

   
    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
  public void switchToAdminHomeScreen(ActionEvent event) throws IOException { // to switch to admin home screen on clicking prev button
  root = FXMLLoader.load(getClass().getResource("adminHomeScreen.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
 
 @FXML
    private void exit(ActionEvent event){ // to close the window
    stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
    }
    
 @FXML
    private void prev(ActionEvent event) throws IOException{ // to go to prev screen
    switchToAdminHomeScreen(event);
    }
     
  @FXML
   private void add(ActionEvent event) { // function to read inputs from text fields that admin enters and to get its value
    String ssn = APssn.getText();       // then to check the user type if it's student it will call network function studentRegistration
    String fn = APfn.getText();         // else (prof or admin ) it will call adminOrProfessorRegistration network function 
    String mn = APmn.getText();         // and return with response that will be displayed in table
    String ln = APln.getText();
    String address = APaddress.getText();
    String dob = APdob.getText();   
    String phoneNo = APphoneNo.getText();
    String gender = APgender.getText();
    String userType = APusertype.getText();
    String response;
        if(userType.equalsIgnoreCase("student")){
         response =network.studentRegistration(ssn, fn, mn, ln, address, dob, phoneNo,gender);
        }else{
                response = network.adminOrProfessorRegistration(ssn, fn, mn, ln, address, dob, phoneNo, gender, userType);
        }
            registrationResult registration = new registrationResult(response);
            resultTableView.getItems().add(registration);
    }
  @FXML
   private void delete(ActionEvent event) { // function to delete user it's deleteed by his email and usertype 
   
            String email = APemail.getText();
            String userType = APusertype.getText();
            
            // Call the deleteUser method from the Network class to delete the user from the database
             String response = network.deleteUser(email,userType);
              registrationResult APregistrationResult = new registrationResult(response);
               resultTableView.getItems().add(APregistrationResult);
  
}
}
     

