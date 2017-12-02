/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
/**
 *
 * @author jndemera2
 */
public class ArtistWidgetHolder extends VBox{
    
    final VBox vb = new VBox();
    VBox box=new VBox();
    final ScrollPane sp = new ScrollPane();
    
    public ArtistWidgetHolder(){
    //            VBox box=new VBox();
        box.getChildren().addAll();
        
        vb.getChildren().addAll(sp);
        sp.setContent(vb);
    }
    
    public void addArtist(ArtistWidget wigdet){
        
        //this.getChildren().add(wigdet);
        //vb.getChildren().addAll(sp,wigdet);
        
    }
    
    
}
