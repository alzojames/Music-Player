package musicplayer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javax.swing.text.View;
import static musicplayer.MusicPlayer.playPause;
import musicplayer.Song;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jndemera2
 */
public class SongWidget extends GridPane{
    
    int count = 1;
    private HashMap<Song, String> pickSong = new HashMap<Song, String>();
    JFXButton songTitle;
    JFXButton artist;
    JFXButton album;
    JFXListView<String> list = new JFXListView<String>();
    ObservableList<String> items = FXCollections.observableArrayList();
    
    public SongWidget(){
        setPadding(new Insets (10,10,10,10));
        setVgap(10);
        setHgap(10);
        Label label = new Label("Title \t Artist \t Album");
        add(label,0,0);
        
        //Region r = new Region();
        //GridPane.setHgrow(r, Priority.ALWAYS);
        //GridPane.setVgrow(r, Priority.ALWAYS);
        
        GridPane.setHgrow(list, Priority.ALWAYS);
        GridPane.setVgrow(list, Priority.ALWAYS);
        items.add(String.format("%-80s%-30s%-20s", "Title","Artist","Album"));
        list.setItems(items);
        add(list,0,0);
    }  
    
    public void addSong(Song song){
//        songTitle = new JFXButton(song.getTitle() + "\t " + song.getArtist() + "\t " + song.getAlbum());
//        add(songTitle,0,count);
//        pickSong.put(song,song.getId());
//        count++;
        System.out.println(String.format("%-80s%-30s%-20s", song.getTitle(),song.getArtist(),song.getAlbum()));
        items.add(String.format("%-80s%-30s%-20s", song.getTitle(),song.getArtist(),song.getAlbum()));
        list.setItems(items);
        
                
        list.getSelectionModel();
//        items.addListener(new ListChangeListener() {
//            @Override
//            public void onChanged(ListChangeListener.Change change) {
//                System.out.println("Detected a change! ");
//            }       
//        });
        
        //System.out.println(list.getSelectionModel().getSelectedItem());

        list.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> 
                observable, String oldValue, String newValue) -> {
                //System.out.println("ListView selection changed from oldValue = " + oldValue + " to newValue = " + newValue);
                try{
                    MusicPlayer.playPause.pause.setOnAction(new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent arg0) {
                            System.out.print("Pause");
                            playPause.getChildren().remove(playPause.pause);
                            Image lastIcon = new Image(getClass().getResourceAsStream("playIcon.png"));
                            playPause.play.setGraphic(new ImageView(lastIcon));
                            ImageView pauseView = new ImageView(lastIcon);
                            pauseView.setFitWidth(5);
                            pauseView.setFitHeight(5);
                            playPause.add(playPause.play,1,2);

                            MusicPlayer.mediaPlayer.pause();
                            //MusicPlayer.selectSong(song.getId());
                            System.out.print("Pause!!!!!!");
                            
                        }

                    });
                    }catch(NullPointerException e){
                        
                    }
                
                    playPause.getChildren().remove(playPause.play);
                    Image lastIcon = new Image(getClass().getResourceAsStream("pauseIcon.png"));
                    playPause.pause.setGraphic(new ImageView(lastIcon));
                    ImageView pauseView = new ImageView(lastIcon);
                    pauseView.setFitWidth(5);
                    pauseView.setFitHeight(5);
                    playPause.add(playPause.pause,1,2);
                    
                    MusicPlayer.selectSong(song.getId());
                    System.out.println(song.getTitle() + "is playing");
                    MusicPlayer.mediaPlayer.play();
                    
                    playPause.currentSongDisplay.clear();
                    playPause.currentSongDisplay.appendText("Song: " +  song.getTitle() + "\nArtist: " + song.getArtist() + "\nAlbum: " + song.getAlbum());
        });
        //list.setItems(items);


    }

}
