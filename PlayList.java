/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;
import java.util.*;
/**
 *
 * @author Japhet
 */
public class PlayList {
    private int id;
    private String title;
    private ArrayList<Song> songs;
    
    /**
     * Constructor for the Playlist class.
     * 
     * Creates a playlist object and obtains the songs
     * 
     * @param id
     * 
     * @param title
     * 
     * @param songs
     */

    public PlayList(int id, String title, ArrayList<Song> songs) {
        this.id = id;
        this.title = title;
        this.songs = songs;
    }
    /**
     * Gets album ID.
     *
     * @return album ID
     */
    public int getId() {
        return this.id;
    }
    /**
     * Gets title
     *
     * @return title
     */
    public String getTitle() {
        return this.title;
    }
    /**
     * Gets a list of songs
     * 
     * @return song list
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }
    
    public void addSong(Song song) {
    	//adds a song to a playlist
    }
    
    public void removeSong(int songId) {
      //remove song from playlist
    }

    @Override
    public String toString() {
        return this.title;
    }
}
