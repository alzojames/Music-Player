package musicplayer;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.jfoenix.controls.*;
import java.awt.Image;
import javafx.geometry.*;
import javafx.scene.image.ImageView;
import musicplayer.Song;
/**
 *
 * @author jndemera2
 */
public class PlayPauseWidget extends GridPane{
    JFXButton playLists = new JFXButton("PlayLists");
    JFXButton artists = new JFXButton("Artists");
    JFXButton albums = new JFXButton("Albums");
    JFXButton songs = new JFXButton("Songs");
    JFXButton genres = new JFXButton("Genres");
        
    JFXButton play = new JFXButton("Play");
    JFXButton pause = new JFXButton("Pause");
    JFXButton next = new JFXButton("Next");
    JFXButton last = new JFXButton("Last");
    JFXTextArea currentSongDisplay = new JFXTextArea();
    Label volume = new Label("Volume");
    JFXSlider volumeSlider = new JFXSlider();
    JFXSlider scrubThrough = new JFXSlider();
    
    //JFXLabel currentSongDisplay = new JFXLabel("Current song");
    public PlayPauseWidget(){
        
        setPadding(new Insets (10,10,10,10));
        setVgap(10);
        setHgap(10);
           
        Region region = new Region();
        GridPane.setHgrow(region, Priority.ALWAYS);
        GridPane.setVgrow(region, Priority.ALWAYS);
        Region r = new Region();
        GridPane.setHgrow(r, Priority.ALWAYS);
        GridPane.setVgrow(r, Priority.ALWAYS);
        
        scrubThrough.setMaxWidth(Double.MAX_VALUE);
        setHgrow(scrubThrough, Priority.ALWAYS);
        currentSongDisplay.setPrefWidth(250);
        currentSongDisplay.setPrefHeight(50);
        
        currentSongDisplay.setDisable(true);
        currentSongDisplay.setText("Song: I'm Here \nArtist: Russ \nAlbum: There's Really a Wolf ");
        //last.setStyle("-fx-background-image: url('C:/Users/ndeme/Documents/NetBeansProjects/MusicPlayer/src/musicplayer/last.png')");
        
        //Image imageOk = new Image(getClass().getResourceAsStream("ok.png"));
        last.setBackground(Background.EMPTY);

        //last.setStyle("-fx-graphic: 22 arial; -fx-base: #b6e7c9;");
        //Image imageOk = new Image(getClass().getResourceAsStream("ok.png"));
        add(playLists, 0, 0);
        add(artists, 0, 1);
        add(albums, 0, 2);
        add(songs, 0, 3);
        add(genres, 0, 4);
        
        add(last,0,7);
        add(play,1,7);
        add(pause,2,7);
        add(next,3,7);
        add(region,4,5);
        add(currentSongDisplay, 5,7);
        add(scrubThrough,5,6);
        add(r,6,5);
        add(volume,7,7);
        add(volumeSlider,8,7);
        

        
        last.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                Song d = new Song();
                d.last();
            }
        });
                
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                //Song d = new Song();
                //d.playMp3();
            }
        });
        
        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                //Song d = new Song();
                //d.playMp3();
            }
        });
        
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                Song d = new Song();
                d.next();
            }
        }); 
    }
}
