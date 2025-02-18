
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */

public class StudentInfoController implements Initializable {
    @FXML
    private Button closeButton; // id for close button 'X
    @FXML
    private Button prevBtn; // id for prev button
   
    @FXML
    private TableView<Result> resultTableView; // initializing TableView for displaying registration results which has id resultTableView
    @FXML
    private TableColumn<Result, String> resultColumn; // id for the column in the table
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    private void exit(ActionEvent event) { // to close window
        stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
  
    
     @FXML
    public void switchToStudHomeScreen(ActionEvent event) throws IOException { // to return to student home screen on clicking the prev button
         root = FXMLLoader.load(getClass().getResource("studentHomeScreen.fxml"));
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        resultColumn.setCellValueFactory(cellData -> cellData.getValue().resultProperty());
        loadStudentResults();
    }    
    private void loadStudentResults() {  // function to run on loading the screen so i made a call in function initialize for it to make it run when the screen is loaded 
        String email = emailClass.email; // as its function is to get the results immediately on opening the student results screen
       String[] response = network.viewStudentPersonalInformation(email); // recieving the response from network function in array to display the student infrmation 
          
           for(int i=0 ; i<response.length; i++){
           registrationResult registration = new registrationResult( response[i]);
            resultTableView.getItems().add(registration);
       
}
}}