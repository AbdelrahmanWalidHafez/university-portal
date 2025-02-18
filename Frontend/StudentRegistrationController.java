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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class StudentRegistrationController implements Initializable {

       @FXML
private Button closeButton;

    @FXML
    private void exit(ActionEvent event){
    Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
    } 

      
    @FXML
    private Stage stage;
    private Scene scene;
 private Parent root;
 
 
    
//    public void switchToStudentReg(ActionEvent event) throws IOException {
//  root = FXMLLoader.load(getClass().getResource("studentRegistration.fxml"));
//  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//  scene = new Scene(root);
//  stage.setScene(scene);
//  stage.show();
// }
//public void switchToHomeScren(ActionEvent event) throws IOException {
//  root = FXMLLoader.load(getClass().getResource("adminHomeScreen.fxml"));
//  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//  scene = new Scene(root);
//  stage.setScene(scene);
//  stage.show();
// }
    
}
