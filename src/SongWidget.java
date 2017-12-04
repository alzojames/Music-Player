/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 */

/**
 *
 * @author jndemera2
 * 
 */

package musicplayer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.jfoenix.controls.*;
import java.awt.event.MouseEvent;
import java.io.File;
import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import java.util.*;
import static java.util.Collections.list;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javax.swing.text.View;
import jdk.nashorn.internal.runtime.ListAdapter;
import static musicplayer.MusicPlayer.playPause;
import musicplayer.Song;
import org.omg.CORBA.portable.UnknownException;

public class SongWidget extends GridPane
{
    
    int count = 1;
    
    private HashMap<Song, String> pickSong = new HashMap<Song, String>();
    
    JFXButton songTitle;
    
    JFXButton artist;
    
    JFXButton album;
    
    JFXListView<String> list = new JFXListView<String>();
    
    ObservableList<String> items = FXCollections.observableArrayList();
    
    ObservableList<Song> songs = FXCollections.observableArrayList();
    
    // ListAdapter adapter;
    
    public SongWidget()
    
    {

        setPadding(new Insets (10,10,10,10));
        
        setVgap(10);
        
        setHgap(10);
        
        Label label = new Label("Title \t Artist \t Album");
        
        add(label,0,0);
        
        
        
        Region r = new Region();
        
        GridPane.setHgrow(r, Priority.ALWAYS);
        
        GridPane.setVgrow(r, Priority.ALWAYS);
        

        GridPane.setHgrow(list, Priority.ALWAYS);
        
        GridPane.setVgrow(list, Priority.ALWAYS);
        
        items.add(String.format("%-80s%-30s%-20s", "Title","Artist","Album"));
        
        list.setItems(items);
        
        add(list,0,0);

    }  
    
    public void addSong(Song song)
    
    {

        //System.out.println(String.format("%-80s%-30s%-20s", song.getTitle(),song.getArtist(),song.getAlbum()));
    	
        songs.add(song);
        
        try{
        	
            items.add(String.format("%-80s%-30s%-20s", song.getTitle(),song.getArtist(),song.getAlbum()));
            
        }catch(IllegalStateException e){
        	
            System.out.print("Flaag on the play");
            
        }
        
        list.setItems(items);
                
        list.getSelectionModel();

        EventHandler filter = (EventHandler) (Event event) -> {
        	
            System.out.print("Something worked");
            
            int x = list.getSelectionModel().getSelectedIndex()-1;
            
            System.out.println(x);
            
        };


        list.setOnMouseClicked(new ListViewHandler(){
        	
            @Override
            
            public void handle(javafx.scene.input.MouseEvent event) {
            	
                //if(filter == )
            	
                System.out.print("Something worked");
                
                int x = list.getSelectionModel().getSelectedIndex()-1;
                
                System.out.println(x);

                
                MusicPlayer.selectSong(x,Library.songs);
                
                MusicPlayer.songIndex = x;
                
                
                try
                
                {
                	
                    playPause.getChildren().remove(playPause.play);
                    
                }catch(IllegalArgumentException e){
                	
                    System.out.print("Unable to remove button");
                    
                }
                
                Image lastIcon = new Image(getClass().getResourceAsStream("pauseIcon.png"));
                
                playPause.pause.setGraphic(new ImageView(lastIcon));
                
                ImageView pauseView = new ImageView(lastIcon);
                
                pauseView.setFitWidth(5);
                
                pauseView.setFitHeight(5);
                
                try
                
                {
                	
                    playPause.add(playPause.pause,1,2);
                    
                }catch(IllegalArgumentException a){
                	
                    System.out.print("Unable to put button");
                
                }

            }
            
        }
        );

    }

}