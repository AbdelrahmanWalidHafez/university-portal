
package userlogin;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class adminHomeScreen extends Application {
    private double xOffset = 0; //These are used to store the mouse's X and Y positions so they're initiallized to zero
                                //They help in making the window draggable.

    private double yOffset = 0;
    @Override
    public void start(Stage stage) throws Exception {  //This is the main entry point for this screen. 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adminHomeScreen.fxml")); // load adminHomeScreen fxml file to set up the GUI
        loader.setController(new InterfaceController()); 
        Parent root = loader.load(); //loads the root node
        Scene scene = new Scene(root); // creates new scene with root node  
        stage.initStyle(StageStyle.DECORATED.UNDECORATED); // remove all default window decorates
        root.setOnMousePressed(new EventHandler<MouseEvent>(){ //makes the window dragabble by handeling mouse event
            @Override
            public void handle(MouseEvent event){
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>(){ 
            @Override
            public void handle(MouseEvent event){   //updates mouse position while dragging the window
           stage.setX(event.getScreenX() - xOffset);
           stage.setY(event.getScreenY() - yOffset);
            }
        });
        stage.setScene(scene); // sets the scene on the stage
        stage.show(); // show scene (window)
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);         //launches javafx application by calling the start method
        
        
    }
    
}
