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
 * @author Japhet
 */
public class NavigationWidget extends GridPane{
    JFXButton playLists = new JFXButton("PlayLists");
    JFXButton artists = new JFXButton("Artists");
    JFXButton albums = new JFXButton("Albums");
    JFXButton songs = new JFXButton("Songs");
    JFXButton genres = new JFXButton("Genres");
    
    public NavigationWidget(){
        setPadding(new Insets (10,10,10,10));
        setVgap(10);
        setHgap(10);
        
        add(playLists, 0, 0);
        add(artists, 0, 1);
        add(albums, 0, 2);
        add(songs, 0, 3);
        add(genres, 0, 4);
        
    }
}
