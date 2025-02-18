
package userlogin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AdminRegisterCourseController implements Initializable {

     @FXML
    private Button closeButton; //creating id for close button 'X'
    @FXML
    private Button  addCourseBtn; //creating id for add button
    @FXML
    private Button  prevPageBtn; //creating id for previous page button
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    private TextField SCc; // creating id for the textfield that takes course code parameter from admin
    @FXML
    private TextField Semail;// creating id for the textfield that takes student email parameter from admin
    @FXML
    private TextField SregNo;// creating id for the textfield that takes student registration number parameter from admin
    @FXML
    private TableView<Result> resultTableView; // initializing TableView for displaying registration results which has id resultTableView
    @FXML
    private TableColumn<Result, String> resultColumn; // id for the column in the table

    @Override
    public void initialize(URL url, ResourceBundle rb) { //This method is called automatically after the FXML file has been loaded.
                                                         //It sets up the resultColumn to dispaly outputs from the Result objects to display data in the table view.
        resultColumn.setCellValueFactory(cellData -> cellData.getValue().resultProperty());
    } 
    @FXML
  public void switchToAdminHomeScreen(ActionEvent event) throws IOException { // to switch to admin home screen if the user clicked prev button
  root = FXMLLoader.load(getClass().getResource("adminHomeScreen.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
  @FXML
    private void exit(ActionEvent event){                      // to close the window
     stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
    }
    
 @FXML
    private void prev(ActionEvent event) throws IOException{  // to get to prev window (homescreen)
    switchToAdminHomeScreen(event);
    }
  @FXML
   private void add(ActionEvent event) {   // function that reads the parameters enetered by admin and 
    String courseCode = SCc.getText();    // send it to network class that connect us to API by the function
    String email = Semail.getText();    // registerCourseForStudent that return the registartion result to the String response
    String RegNo = SregNo.getText();   // then it gets to the object registration that sent to the table column to be displayed 
   
    String response = network.registerCourseForStudent(courseCode,email,RegNo);

            registrationResult registration = new registrationResult(response);
            resultTableView.getItems().add(registration);

    
}
}
