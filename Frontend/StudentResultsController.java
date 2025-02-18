
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

public class StudentResultsController implements Initializable {

    @FXML
    private Button closeButton; // id for close button
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
    public void switchToStudHomeScreen(ActionEvent event) throws IOException { // to switch to student main screen on clicking prev button 
         root = FXMLLoader.load(getClass().getResource("studentHomeScreen.fxml"));
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resultColumn.setCellValueFactory(cellData -> cellData.getValue().resultProperty());
        loadStudentResults(); // call it in initialize function to  run it on displaying the screen
    }    
    private void loadStudentResults() {  // takes the email from the global variable in class email and send it to the network function viewStudentGrades
        String email = emailClass.email;  // to return with the response in response string and display the results in the table column 
       String response = network.viewStudentGrades(email);
           registrationResult registration = new registrationResult( response);
            resultTableView.getItems().add(registration);
           
       
}
    
}
    

