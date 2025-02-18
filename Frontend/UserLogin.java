
package userlogin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class UserLogin extends Application { 
                                              
     private double xOffset = 0;   //These are used to store the mouse's X and Y positions. They help in making the window draggable.
     private double yOffset = 0;
    @Override
    public void start(Stage stage) throws Exception {  //This is the main entry point for a JavaFX application. It's called when the application is launched.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Interface.fxml"));  // loads the Interface.fxml file to set up the GUI.
        Parent root = loader.load(); //loads the root node of the scene graph from the FXML file.
        Scene scene = new Scene(root); //creates a new scene with the root node.
        stage.initStyle(StageStyle.DECORATED.UNDECORATED); // removes the default window decorations
        stage.setScene(scene);  // This sets the scene on the stage.
        root.setOnMousePressed(new EventHandler<MouseEvent>(){  // makes the window draggable by handling mouse event 
                                                                //saves initial position of the mouse when the user presses the mouse button
            @Override
            public void handle(MouseEvent event){
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){  //updates mouse position while dragging the window.
           stage.setX(event.getScreenX() - xOffset);
           stage.setY(event.getScreenY() - yOffset);
            }
        });
        stage.setScene(scene);
        stage.show();  // This shows the stage(window)to the user.
        }

    
    public static void main(String[] args) {
        launch(args);   //  launches the JavaFX application by calling the start method.
        
    }
    
}
