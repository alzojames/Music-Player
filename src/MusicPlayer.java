
package musicplayer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.jfoenix.controls.*;
import javafx.geometry.*;
import com.jfoenix.controls.JFXButton;
import javafx.scene.paint.Color;


/**
 *
 * @author jndemera2
 */
public class MusicPlayer extends Application {
    
    Scene scene;
    
    @Override
	public void start(Stage primaryStage){
            
            
            
            PlayPauseWidget bw  = new PlayPauseWidget();
            //NavigationWidget bw = new NavigationWidget();
            primaryStage.setTitle("working");
		
            scene = new Scene(bw, 800, 600, Color.CORNSILK);
            primaryStage.setScene(scene);
            scene.getStylesheets().add("theme.css");
//            StackPane rootPane = new StackPane();
//            scene = new Scene(rootPane,800,600);
//            Pane pane1 = new Pane();
//            Pane pane2 = new Pane();
//            rootPane.getChildren().addAll(pane1,pane2);
            primaryStage.show();
	}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
