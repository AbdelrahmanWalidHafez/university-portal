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
import javafx.stage.Stage;

public class AdminHomeScreenController implements Initializable {

    @FXML
    private Button closeButton; // creating id for close button
    @FXML
    private Button ADprofBtn; //creating id for adding user button
    @FXML
    private Button RegisterCourseBtn;  // creating id for registering course for a student button
    @FXML
    private Button ADcourseBtn; // creating id for adding or deleting course button
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    @FXML
    private void exit(ActionEvent event) {    //// exit function that enable us to close the window by clicking 'X'
        Stage stage = (Stage) closeButton.getScene().getWindow(); //to close the stage (window)
        stage.close();
    }


   @FXML
  public void switchToAdminRegisterCourseForStudent(ActionEvent event) throws IOException { // to switch to admin register courses for students if the admin clicked its button
  root = FXMLLoader.load(getClass().getResource("adminRegisterCourse.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }

    public void switchToProfReg(ActionEvent event) throws IOException {             // to switch to registering user screen  if the admin clicked its button
        root = FXMLLoader.load(getClass().getResource("professorRegestration.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     public void switchToCourseReg(ActionEvent event) throws IOException {          // to switch to add or delete course screen if the admin clicked its button
        root = FXMLLoader.load(getClass().getResource("courseRegestration.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
     }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
}
