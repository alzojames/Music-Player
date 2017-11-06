package musicplayer;


import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jndemera2
 */
public class SongWidget extends GridPane{
    
    int count = 1;
    public SongWidget(){
        
    }  
    public void addSong(Song song){
      //  Label label = new Label(song.getTitle() + song.getArtist() + song.getAlbum());
        //add(label,0,count);
    }
}
