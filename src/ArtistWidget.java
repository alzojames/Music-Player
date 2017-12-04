/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import static org.apache.tika.mime.MediaType.*;
import org.controlsfx.control.*;
import org.controlsfx.control.cell.ColorGridCell;
/**
 *
 * @author jndemera2
 */
public class ArtistWidget extends GridPane{
    Label albumLabel = new Label("Album Name\nArtist Name");
    Label artistLabel = new Label("Album Name\nArtist Name");
    Text albumText;
    Text artistText;
    SongWidget top10 = new SongWidget();
    JFXButton albumArt = new JFXButton("");
    JFXListView<String> list = new JFXListView<String>();
    ObservableList<String> items = FXCollections.observableArrayList();
    ObservableList<Song> songs = FXCollections.observableArrayList();
    
    public ArtistWidget() {
        
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

        //Region r = new Region();
        //GridPane.setHgrow(r, Priority.ALWAYS);
        //GridPane.setVgrow(r, Priority.ALWAYS);
        
        GridPane.setHgrow(list, Priority.ALWAYS);
        GridPane.setVgrow(list, Priority.ALWAYS);
        items.add(String.format("%-80s \t%-30s \t%-20s", "Title","Artist","Album"));
        list.setItems(items);
        add(list,2,2);
        
    }
     /**
      *  Adds the album name and artist name, sets the font and postion of the text. Adds the song to the album and sets the format for the string of text (Title, Artist name, and Album name)
      *  
      *  @param song
      */
    public void addSong(Song song){

        albumText = new Text(song.getAlbum());
        artistText = new Text(song.getArtist());
        
        albumText.setFont(Font.font ("Verdana", 20));
        add(albumText,2,0);
        add(artistText,2,1);
        //System.out.println(String.format("%-80s%-30s%-20s", song.getTitle(),song.getArtist(),song.getAlbum()));
        songs.add(song);
        items.add(String.format("%-80s%-30s%-20s", song.getTitle(),song.getArtist(),song.getAlbum()));
        list.setItems(items);
        
    }
                
}
