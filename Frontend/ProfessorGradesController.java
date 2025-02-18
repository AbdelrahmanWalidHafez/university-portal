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

public class ProfessorGradesController implements Initializable {

    @FXML
    private Button closeButton; // id for close button
    @FXML
    private Button prevBtn; // id for prev button
    @FXML
    private Button updateGradeBtn; // id for update grade button
    @FXML
    private TextField ScourseCode; // id for course code text field
    @FXML
    private TextField SRegNO; // id for registration number text field
    @FXML
    private TextField SstudGrade; // id for student grade text field
    @FXML
    private TextField SgradeType; // id for student grade type text field
    @FXML
    private TableView<Result> resultTableView; // initializing TableView for displaying registration results which has id resultTableView
    @FXML
    private TableColumn<Result, String> resultColumn;  // id for the column in the table
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Simulated network object, replace with actual implementation
    private network network = new network();

    @FXML
    private void exit(ActionEvent event) {  // to close the window
         stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void prev(ActionEvent event) throws IOException { // to get to prev screen (professor home screen )
        switchToProfHomeScreen(event);
    }

    @FXML
    private void update(ActionEvent event) {  // function executed on updateGradeBtn clicked that reads the professor inputs and
        try {                                         // send it to network class to its function updateStudentGrades
            String courseCode = ScourseCode.getText(); // to connect us to database through API 
            String regNumber = SRegNO.getText();       // and update student results
            String grade = SstudGrade.getText();
            String gradeType = SgradeType.getText();

            // Assuming network.updateStudentGrades is defined elsewhere
            String response = network.updateStudentGrades(courseCode, regNumber, grade, gradeType);
            System.out.println(response);

            registrationResult result = new registrationResult(response); // displaying the results in the table
            resultTableView.getItems().add(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  
    @FXML
    public void switchToProfHomeScreen(ActionEvent event) throws IOException {  // to get to the prev screen which is professor home screen
        try {
            root = FXMLLoader.load(getClass().getResource("ProfessorHomeScreen.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resultColumn.setCellValueFactory(cellData -> cellData.getValue().resultProperty());//This method is called automatically after the FXML file has been loaded.
                                                                    //It sets up the resultColumn to dispaly outputs from the Result objects to display data in the table view.
    }
}
