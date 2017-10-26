
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
    
    
    /*
    Scene scene;
    @Override
	public void start(Stage primaryStage){
		PlayPauseWidget bw  = new PlayPauseWidget();
		//SongSlider bw =new SongSlider();
		primaryStage.setTitle("Elysium");
		
		primaryStage.setScene(new Scene(bw, 800, 600));
		primaryStage.show();
	}
    */
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
        
        int numOfTabs = 5;
        Tab tab;
        String name = "did not work";
        for (int i = 0; i < 5; i++) {
            //Tab tab = new Tab();
            tab = new Tab();
            tab.setText(tabName[i]);
            HBox hbox = new HBox();
            
            //Call the Playlist Artist... Widget classes
            switch (i){
                case 0: tab.setContent(new PlayPauseWidget());
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
            System.out.print(name);
                
            //hbox.getChildren().add(play);
            //hbox.getChildren().add(play);
            
            hbox.setAlignment(Pos.CENTER);
            //tab.setContent(hbox);
            tabPane.getTabs().add(tab);
        }
        
        //HBox hbox = new HBox();    
        //hbox.getChildren().add(play);
        // bind to take available space
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        
        borderPane.setCenter(tabPane);
        root.getChildren().addAll(borderPane,grid);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        /*
        Holder transcoder = new Holder();

        
        Scene scene = new Scene(transcoder, 800, 600);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        */
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
