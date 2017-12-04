/*
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package musicplayer;

import com.jfoenix.controls.JFXButton;
import java.awt.Color;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.util.*;
import static java.util.Collections.list;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import static org.apache.tika.mime.MediaType.*;
import org.controlsfx.control.*;
import org.controlsfx.control.cell.ColorGridCell;
/**
 *
 * @author jndemera2
 * 
 */
public class PlayListWidget extends GridPane

{

    GridView<Color> myGrid = new GridView<>();


    public PlayListWidget()
    
    {
    	
        //GridView<MenuItem> menuItems = new GridView<>();
    	
        setPadding(new Insets (10,10,10,10));
        
        setVgap(10);
        
        setHgap(10);
        
        GridPane.setHgrow(myGrid, Priority.ALWAYS);
        
        GridPane.setVgrow(myGrid, Priority.ALWAYS);
        
        
        for(int i = 0; i < 10000; i++)
        	
        {
            
            myGrid.getItems().addAll(new Color(i));

        }

        add(myGrid,0,0);
        
    }

}
