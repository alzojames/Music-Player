/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
/**
 *
 * @author jndemera2
 */
public class ArtistWidgetHolder extends VBox{
    
    static VBox box=new VBox();
    
    public ArtistWidgetHolder(){
        box.getChildren().addAll();
        
    }
    
    public static void addArtist(ArtistWidget wigdet){
        box.getChildren().addAll(wigdet);
        
    }
}
