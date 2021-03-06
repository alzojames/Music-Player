/*
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jndemera2
 */

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ArtistWidgetHolder extends GridPane{
    
    int numOfAlbums = 0;
    int x = 0;
    int y = 0;
    Label albumLabel = new Label("");
    Label artistLabel = new Label("");
    JFXButton albumArtWidget = new JFXButton("");
    ScrollPane scrollPane = new ScrollPane();
    GridView g = new GridView();
    ScrollPane sp = new ScrollPane();
    HBox hbox ;
    TilePane tilePane;
    
    public ArtistWidgetHolder()
    {

        tilePane = new TilePane();
        tilePane.setTileAlignment(Pos.CENTER);
        //tilePane.setPrefRows();

        tilePane.setPadding(new Insets (10,10,10,10));
        tilePane.setVgap(10);
        tilePane.setHgap(10);
        

        // dont grow more than the preferred number of columns:
        tilePane.setMaxWidth(Double.MAX_VALUE);
        HBox tiles[] = new HBox[100];
        hbox = new HBox();

        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(tilePane); 

        sp.setFitToHeight(true);
        sp.setFitToWidth(true);
        sp.setContent(hbox);
        add(tilePane,0,0);
        
        
    }
    
    public void addArtist(ArtistArtWork pic)
    {
        
        System.out.println("album widet");
        Label label = new Label("albumArt.jpg");
        TilePane.setAlignment(label, Pos.BOTTOM_RIGHT);
        tilePane.getChildren().addAll(pic);
        
    }
    
    
}
