package musicplayer;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static org.apache.tika.mime.MediaType.*;
import org.controlsfx.control.*;
import org.controlsfx.control.cell.ColorGridCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
 import javafx.scene.shape.Rectangle;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Japhet
 */
public class AlbumArtWidget extends TilePane{
    
     
    int numOfAlbums = 0;
    int x = 0;
    int y = 0;
    Label albumLabel = new Label("Album Name\nArtist Name");
    Label artistLabel = new Label("Album Name\nArtist Name");
    JFXButton albumArtWidget = new JFXButton("");
    ScrollPane scrollPane = new ScrollPane();
    //ColorGridCell grid = new ColorGridCell();
    GridView g = new GridView();
    
    //GridCell g = new GridCell();
    
    /*
    This Puts the Ablum art work on the screen as well as a label with the album and artistt name
    Art work will come from the Album class' getArtwork method
    */
    public AlbumArtWidget() {
           
        setPadding(new Insets (10,10,10,10));
        setVgap(10);
        setHgap(10);
       getChildren().addAll();

    }
    public void addArtWork(AlbumArt art){
        
            System.out.println("album widet");
            Label label = new Label("albumArt.jpg");
            //Image image = new Image(getClass().getResourceAsStream("albumArt.jpg"))
            //ImageView imageview = new ImageView(new Image("albumArt.jpg"));
            //AlbumArt art = new AlbumArt();
            TilePane.setAlignment(label, Pos.BOTTOM_RIGHT);
            getChildren().addAll(art);

        
    }
}
