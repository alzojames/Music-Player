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
import static java.util.Collections.list;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javax.swing.text.View;
import jdk.nashorn.internal.runtime.ListAdapter;
import static musicplayer.MusicPlayer.playPause;
import musicplayer.Song;
import org.omg.CORBA.portable.UnknownException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jndemera2
 */
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
    
    public SongWidget(){
        setPadding(new Insets (10,10,10,10));
        setVgap(10);
        setHgap(10);
        //Label label = new Label("Title \t Artist \t Album");
        final Label title = createLabel("Title");
        final Label artist = createLabel("Artist");
        final Label album = createLabel("Album");
        
        title.textProperty().bind(SongWidget.titlePropety());
        artist.textProperty().bind(SongWidget.artistPropety());
        album.textProperty().bind(SongWidget.albumPropety());
        
       grid.add(title, 1, 0);
       grid.add(artist, 1, 1);
       grid.add(album, 1, 2);
       
       
       
        //add(label,0,0);
        
        public SongWidget() {
        	
        }
        
        
        //Region r = new Region();
        //GridPane.setHgrow(r, Priority.ALWAYS);
        //GridPane.setVgrow(r, Priority.ALWAYS);

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
        items.add(String.format("%-80s%-30s%-20s", song.getTitle(),song.getArtist(),song.getAlbum()));
        list.setItems(items);
        
                                  
        
        list.getSelectionModel();
 


        list.setOnMouseClicked(new ListViewHandler(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                System.out.print("Something worked");
                int x = list.getSelectionModel().getSelectedIndex();;
                System.out.println(x);

                
                MusicPlayer.selectSong(x-1);
                MusicPlayer.songIndex = x-1;
                
                try{
                playPause.getChildren().remove(playPause.play);
                }catch(IllegalArgumentException e){
                    System.out.print("Unable to remove button");
                }
                
                Image lastIcon = new Image(getClass().getResourceAsStream("pauseIcon.png"));
                playPause.pause.setGraphic(new ImageView(lastIcon));
                ImageView pauseView = new ImageView(lastIcon);
                pauseView.setFitWidth(5);
                pauseView.setFitHeight(5);
                
                try{
                    playPause.add(playPause.pause,1,2);
                }catch(IllegalArgumentException a){
                    System.out.print("Unable to put button");
                }
            }
            
        });
        

    
    }
}