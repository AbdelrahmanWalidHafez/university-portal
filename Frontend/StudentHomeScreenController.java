/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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


public class StudentHomeScreenController implements Initializable {

   @FXML
private Button closeButton;
   @FXML
private Button resultBtn; //id for result button to get student results on click
  @FXML
private Button personalInfoBtn; // id for personal info button get student info on click
  @FXML
private Stage stage;
   @FXML
private Scene scene;
  @FXML
private Parent root;

    @FXML
    private void exit(ActionEvent event){ // to close the window
     stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
    }
 
 @FXML
    public void switchToStudResults(ActionEvent event) throws IOException { // to switch to student results screen on button click
         root = FXMLLoader.load(getClass().getResource("studentResults.fxml"));
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     @FXML
    public void switchToStudInfo(ActionEvent event) throws IOException { // to switch to student info scren on button click
         root = FXMLLoader.load(getClass().getResource("studentInfo.fxml"));
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
