
package userlogin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class CourseRegestrationController implements Initializable {

 @FXML
 private Button closeButton; // id for close button
 @FXML
 private TextField cN; // id for course name text field
 @FXML
 private TextField cC; // id for course code text field
 @FXML
 private Button addBtn;  // id for add button
 @FXML
 private Button deleteBtn;  // id for delete button
 @FXML
 private Button prevBtn;  // id for prev button
  @FXML
  private Stage stage;
  private Scene scene;
  private Parent root;
   
   @FXML
    private TableView<Result> resultTableView;  // initializing TableView for displaying registration results which has id resultTableView
   @FXML
    private TableColumn<Result, String> resultColumn;  // id for the column in the table

        
   @FXML
    private void exit(ActionEvent event){    // to close the window
     stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
    }
    @FXML
    private void prev(ActionEvent event) throws IOException{ // to go to prev page (homescreen)
    switchToAdminHomeScreen(event);
    }

   @FXML
  public void switchToAdminHomeScreen(ActionEvent event) throws IOException {
  root = FXMLLoader.load(getClass().getResource("adminHomeScreen.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
  @FXML
   private void add(ActionEvent event) { // function that reads the parameters enetered by admin and 
    String courseName = cN.getText();    // send it to network class that connect us to API by the function
    String courseCode = cC.getText();    // courseRegistration that return the registartion result to the String response
    String[] parts = courseName.split(" "); // then it gets to the object registration that sent to the table column to be displayed 
    String firstWord = parts[0];    // i split the string to two strings by space to match my api requirements
    String secondWord = parts[1];
        String response = network.courseRegistration(firstWord,secondWord);
            registrationResult registration = new registrationResult( response);
            resultTableView.getItems().add(registration);
        }
         @FXML
   private void delete(ActionEvent event) {        // deletes the course by entering the course code and sending it to the network function 
           String courseCode = cC.getText();      // deleteCourse 
            // Call the deleteUser method from the Network class to delete the user from the database
             String response = network.deleteCourse(courseCode);
               registrationResult APregistrationResult = new registrationResult(response);
               resultTableView.getItems().add(APregistrationResult);

   }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resultColumn.setCellValueFactory(cellData -> cellData.getValue().resultProperty()); //This method is called automatically after the FXML file has been loaded.
                                                                    //It sets up the resultColumn to dispaly outputs from the Result objects to display data in the table view.
    }    
    
}
