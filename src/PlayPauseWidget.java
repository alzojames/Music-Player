/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.jfoenix.controls.*;
import java.io.File;
import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import musicplayer.Song;

/**
 *
 * @author jndemera2
 */

public class PlayPauseWidget extends GridPane{
    
        
    JFXButton play = new JFXButton("");
    JFXButton pause = new JFXButton("");
    JFXButton next = new JFXButton("");
    JFXButton last = new JFXButton("");
    JFXTextArea currentSongDisplay = new JFXTextArea();
    Label volume = new Label("");
    JFXSlider volumeSlider = new JFXSlider();
    JFXSlider scrubThrough = new JFXSlider();
    JFXButton importFiles = new JFXButton("Import");
    
    Library lib = new Library();
    

    
            MediaPlayer mediaPlayer;
    //private Label time;
    //Duration duration;
    //Button fullScreenButton;
    //Scene scene;
    Media media;
    double width;
    double height;
    MediaView mediaView;
    //JFXLabel currentSongDisplay = new JFXLabel("Current song");
    public  PlayPauseWidget(){
        
        setPadding(new Insets (10,10,10,10));
        setVgap(10);
        setHgap(10);
           
        //region will be used to make the GUI more reponsive
        Region region = new Region();
        GridPane.setHgrow(region, Priority.ALWAYS);
        GridPane.setVgrow(region, Priority.ALWAYS);
        
        Region r = new Region();
        GridPane.setHgrow(r, Priority.ALWAYS);
        GridPane.setVgrow(r, Priority.ALWAYS);
        
        /*
        put image on the icons on the buttons to make the GUI
        icon image files must be in the same directory as this class file,
        or specifiy the loction on the drive where the files can be found 
        */
        Image lastIcon = new Image(getClass().getResourceAsStream("lastIcon.png"));
        last.setGraphic(new ImageView(lastIcon));
        ImageView lastView = new ImageView(lastIcon);
        lastView.setFitWidth(5);
        lastView.setFitHeight(5);
        
        Image playIcon = new Image(getClass().getResourceAsStream("playIcon.png"));
        play.setGraphic(new ImageView(playIcon));
        ImageView playView = new ImageView(lastIcon);
        playView.setFitWidth(5);
        playView.setFitHeight(5);
        
        Image nextIcon = new Image(getClass().getResourceAsStream("nextIcon.png"));
        next.setGraphic(new ImageView(nextIcon));
        ImageView nextView = new ImageView(lastIcon);
        nextView.setFitWidth(5);
        nextView.setFitHeight(5);
        
        Image volumeIcon = new Image(getClass().getResourceAsStream("volumeIcon.png"));
        volume.setGraphic(new ImageView(volumeIcon));
        ImageView volumeView = new ImageView(lastIcon);
        volumeView.setFitWidth(5);
        volumeView.setFitHeight(5);
        
        /*
        Add the elements on to the gridPane 
        */
        add(last,0,2);
        add(play,1,2);
        add(next,3,2);
        add(region,4,0);
        add(importFiles,4,2);
        add(currentSongDisplay, 5,2);
        add(scrubThrough,5,1);
        add(r,6,0);
        add(volume,7,2);
        add(volumeSlider,8,2);
        //add(importFiles,9,2);
        

        scrubThrough.setMaxWidth(Double.MAX_VALUE);
        setHgrow(scrubThrough, Priority.ALWAYS);
        
        currentSongDisplay.setPrefWidth(250);
        currentSongDisplay.setPrefHeight(50);
        currentSongDisplay.setDisable(true);
        currentSongDisplay.appendText("Song: ----- \nArtist: ----- \nAlbum: -----");
        
        
        /*
        This event handler will play the previous track
        As of now it's being used to test if the getmetaData method works
        */
        last.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Hello World!");
                
                //lib.getmetaData();
                //Song d = new Song();
                //d.last();
            }
        });
                

        play.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                getChildren().remove(play);
                Image lastIcon = new Image(getClass().getResourceAsStream("pauseIcon.png"));
                pause.setGraphic(new ImageView(lastIcon));
                ImageView pauseView = new ImageView(lastIcon);
                pauseView.setFitWidth(5);
                pauseView.setFitHeight(5);
                add(pause,1,2);
                
                //MusicPlayer.play();
                
                
//                String metaData[] = lib.getmetaData("");
//                updatecurrentSongDisplay(metaData[0],metaData[1],metaData[2]);
            }
        });
        

        
        /*
        This event handler will play the previous track
        As of now it's being used to test if the getmetaData method works
        */
        next.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.print("Working");
            }
        });
    }
    
    //This updates the info about the currrntly playing song
    public void updatecurrentSongDisplay(String song, String Artist, String Album){
        currentSongDisplay.clear();
        currentSongDisplay.appendText("Song: " +  song + "\nArtist: " + Artist + "\nAlbum: " + Album);
    }

}
