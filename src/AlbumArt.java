package musicplayer;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import static org.apache.tika.mime.MediaType.image;

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
    
    //Pass getArtwork to set the artwork
    public AlbumArt() {
        setPadding(new Insets (10,10,10,10));
        setVgap(10);
        setHgap(10);
        
        Label albumLabel = new Label("Album Name\nArtist Name");
        Label artistLabel = new Label("Album Name\nArtist Name");
        
        Image image = new Image(getClass().getResourceAsStream("albumArt.jpg"));
        JFXButton albumArt = new JFXButton("");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(125);
        imageView.setFitHeight(125);
        
        Image image2 = new Image(getClass().getResourceAsStream("TheIncredibleTrueStory.jpg"));
        JFXButton albumArt2 = new JFXButton("");
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(125);
        imageView2.setFitHeight(125);
        
        albumArt.setGraphic(imageView);
        add(albumArt,0,0);
        add(albumLabel,0,1);
        
        albumArt2.setGraphic(imageView2);
        add(albumArt2,1,0);
        add(artistLabel,1,1);
        //add(artistLabel,0,2);
    }

  
    

}
