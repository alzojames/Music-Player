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
import javafx.geometry.*;
import musicplayer.Song;
/**
 *
 * @author jndemera2
 */
public class PlayPauseWidget extends GridPane{
    
        
    JFXButton play = new JFXButton("Play");
    JFXButton pause = new JFXButton("Pause");
    JFXButton next = new JFXButton("Next");
    JFXButton last = new JFXButton("Last");
    JFXTextArea currentSongDisplay = new JFXTextArea();
    Label volume = new Label("Volume");
    JFXSlider volumeSlider = new JFXSlider();
    JFXSlider scrubThrough = new JFXSlider();
    
    //JFXLabel currentSongDisplay = new JFXLabel("Current song");
    public  PlayPauseWidget(){
        
        setPadding(new Insets (10,10,10,10));
        setVgap(10);
        setHgap(10);
           
        Region region = new Region();
        GridPane.setHgrow(region, Priority.ALWAYS);
        GridPane.setVgrow(region, Priority.ALWAYS);
        
        Region r = new Region();
        GridPane.setHgrow(r, Priority.ALWAYS);
        GridPane.setVgrow(r, Priority.ALWAYS);
        
        
        add(last,0,2);
        add(play,1,2);
        add(pause,2,2);
        add(next,3,2);
        add(region,4,0);
        add(currentSongDisplay, 5,2);
        add(scrubThrough,5,1);
        add(r,6,0);
        add(volume,7,2);
        add(volumeSlider,8,2);
        
        scrubThrough.setMaxWidth(Double.MAX_VALUE);
        setHgrow(scrubThrough, Priority.ALWAYS);
        currentSongDisplay.setPrefWidth(250);
        currentSongDisplay.setPrefHeight(50);
        currentSongDisplay.setDisable(true);
        currentSongDisplay.appendText("Song: I'm here \nArtist: Russ \nAlbum: There's Really A Wolf");
        
        
        last.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Hello World!");
                Song d = new Song();
                d.last();
            }
        });
                
        play.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Hello World!");
                //Song d = new Song();
                //d.playMp3();
            }
        });
        
        next.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Hello World!");
                Song d = new Song();
                d.next();
            }
        });

    }
    

}
