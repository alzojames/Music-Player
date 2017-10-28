
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
import java.awt.Color;
import javafx.scene.Group;
import static javafx.scene.layout.GridPane.setHgrow;


/**
 *
 * @author jndemera2
 */
public class MusicPlayer extends Application {
    

    private PlayPauseWidget play;
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Tabs");
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600);

        JFXTabPane tabPane = new JFXTabPane();
        GridPane grid = new GridPane();
        //PlayPauseWidget playMenu = new PlayPauseWidget();
        Tab[] tabs = new Tab[5];
        String tabName[] = {"Playlists","Artists","Albums","Songs","Genres"};
        BorderPane borderPane = new BorderPane();
        play = new PlayPauseWidget();
        borderPane.setBottom(play);
        
        
        Tab tab;
        for (int i = 0; i < 5; i++) {
            
            tab = new Tab();
            tab.setText(tabName[i]);
            HBox hbox = new HBox();
            
            //Call the Playlist Artist... Widget classes
            switch (i){
                case 0: tab.setContent(new AlbumArt());
                        break;
                case 1: tab.setContent(new PlayPauseWidget());
                        break;
                case 2: tab.setContent(new PlayPauseWidget());
                        break;
                case 3: tab.setContent(new PlayPauseWidget());
                        break;
                case 4: tab.setContent(new PlayPauseWidget());
                        break; 
                
            }

            hbox.setAlignment(Pos.CENTER);
            tabPane.getTabs().add(tab);
        }
        

        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        
        borderPane.setCenter(tabPane);
        root.getChildren().addAll(borderPane,grid);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}