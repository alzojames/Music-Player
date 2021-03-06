/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 

/**
 *
 * @author jndemera2
 * 
 */

package musicplayer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import static musicplayer.MusicPlayer.playPause;
import static org.apache.tika.mime.MediaType.*;
import org.controlsfx.control.*;
import org.controlsfx.control.cell.ColorGridCell;


public class Widget extends GridPane{

    Label albumLabel = new Label("");
    
    Label artistLabel = new Label("");
    
    Text albumText;
    
    Text artistText;
    
    SongWidget top10 = new SongWidget();
    
    JFXButton albumArt = new JFXButton("");
    
    JFXListView<String> list = new JFXListView<String>();
    
    ObservableList<String> items = FXCollections.observableArrayList();
    
    ObservableList<Song> songs = FXCollections.observableArrayList();
    
    
    public Widget()
    
    {

        setPadding(new Insets (10,10,10,10));
        
        setVgap(10);
        
        setHgap(10);
        
        
        /*
        Get the image (artwork), put it in the imageView and put that on the button
        then set the size
        Size may have to be variable in the the case that the window grows or skrinks
        */
        
        Image image = new Image(getClass().getResourceAsStream("albumArt.jpg"));
        
        ImageView imageView = new ImageView(image);
        
        imageView.setFitWidth(125);
        
        imageView.setFitHeight(125);
        
        albumArt.setGraphic(imageView);
        
         
        add(albumArt,0,0);
        
        add(albumLabel,0,1);
        

        Region r = new Region();
        
        GridPane.setHgrow(r, Priority.ALWAYS);
        
        GridPane.setVgrow(r, Priority.ALWAYS);
        
        GridPane.setHgrow(list, Priority.ALWAYS);
        
        GridPane.setVgrow(list, Priority.ALWAYS);
        
        items.add(String.format("%-80s \t%-30s \t%-20s", "Title","Artist","Album"));
        
        list.setItems(items);
        
        add(list,2,2);
        
        albumArt.setOnAction(new EventHandler<ActionEvent>()
        
        {
            
            @Override
            
            public void handle(ActionEvent arg0)
            
            {
                
                MusicPlayer.artistTab.setContent(MusicPlayer.sp2);
                
            }
                    
        }
        );

    }
    
        public void addSong(Song song)
        
        {

        
        artistText = new Text(song.getArtist());
        
        artistText.setFont(Font.font ("Verdana", 20));
        
        add(artistText,2,0);
        
        //System.out.println(String.format("%-80s%-30s%-20s", song.getTitle(),song.getArtist(),song.getAlbum()));
        
        songs.add(song);
        
        items.add(String.format("%-80s%-30s%-20s", song.getTitle(),song.getArtist(),song.getAlbum()));
        
        list.setItems(items);
        

        list.setOnMouseClicked(new ListViewHandler()
        
        {

            @Override
            
            public void handle(javafx.scene.input.MouseEvent event)
            
            {

                System.out.print("Something worked");
                
                int x = list.getSelectionModel().getSelectedIndex()-1;
                
                System.out.println(x);
                

                Album album = Library.albums.get(song.getAlbum());
                
                ArrayList<Song> songs = album.getSongs();
                
                MusicPlayer.selectSong(x,songs);
                
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
