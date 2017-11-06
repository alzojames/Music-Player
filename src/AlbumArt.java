package musicplayer;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import static org.apache.tika.mime.MediaType.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Japhet
 */
public class AlbumArt extends GridPane{
    

    int numOfAlbums = 0;
    
    /*
    This Puts the Ablum art work on the screen as well as a label with the album and artistt name
    Art work will come from the Album class' getArtwork method
    */
    public AlbumArt() {
        setPadding(new Insets (10,10,10,10));
        setVgap(10);
        setHgap(10);
        
        Label albumLabel = new Label("Album Name\nArtist Name");
        Label artistLabel = new Label("Album Name\nArtist Name");
        
        /*
        Get the image (artwork), put it in the imageView and put that on the button
        then set the size
        Size may have to be variable in the the case that the window grows or skrinks
        */
        Image image = new Image(getClass().getResourceAsStream("albumArt.jpg"));
        JFXButton albumArt = new JFXButton("");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(125);
        imageView.setFitHeight(125);
        albumArt.setGraphic(imageView);
        
        //This is for demo purposes, but this constucter will only make one album artwork at a time
        
        Image image2 = new Image(getClass().getResourceAsStream("TheIncredibleTrueStory.jpg"));
        JFXButton albumArt2 = new JFXButton("");
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(125);
        imageView2.setFitHeight(125);
        albumArt2.setGraphic(imageView2);
        
        /*
        The position will have to depend on some kind of equation that will add each 
        in the right spot
        */
        add(albumArt,0,0);
        add(albumLabel,0,1);
        
        
        add(albumArt2,1,0);
        add(artistLabel,1,1);
        //add(artistLabel,0,2);
    }

  
    

}
