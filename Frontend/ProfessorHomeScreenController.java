
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


public class ProfessorHomeScreenController implements Initializable {

      @FXML
    private Button closeButton; 
    
      @FXML
    private void exit(ActionEvent event) { // to close the window
        stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
      @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToStudGrades(ActionEvent event) throws IOException { // to switch to the window where professor updates students gardes onclicking the button 
        root = FXMLLoader.load(getClass().getResource("professorGrades.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
