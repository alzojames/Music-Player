package musicplayer;
import java.awt.Image;
import java.util.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import musicplayer.Song;

public final class Album implements Comparable<Album> {

    private int id;
    private String title;
    private String artist;
    private Image artwork;
    AlbumArt art;
    private HashSet<Song> songs = new HashSet<Song>();
    private SimpleObjectProperty<Image> artworkProperty;

    /**
     * Constructor for the Album class. 
     * 
     * Creates an album object and obtains the album artwork.
     *
     * @param id
     * 
     * @param title
     * 
     * @param artist
     * 
     * @param songs
     */
    public Album(String title, String artist) {
        //this.id = id;
        if(title == null){
            this.title = "Unkown";
        }else{
            this.title = title;
        }

        if(artist == null){
            this.artist = "Unkown";
        }else{
            this.artist = artist;
        }
        
        this.songs = songs;
        this.artworkProperty = new SimpleObjectProperty<>(getArtwork());
    }
    

    /**
     * Gets album ID.
     *
     * @return album ID
     */
    public int getId() {
        return this.id;
    }

    public void test(){
        System.out.print("apples" + this.getArtist());
    }
    /**
     * Gets album title
     *
     * @return album title
     */
    public String getTitle() {
        return this.title;
    }
    /**
     * Gets artist name
     *
     * @return artist name
     */	
    public String getArtist() {
        return this.artist;
    }
    /**
     * Gets a list of songs
     * 
     * @return song list
     */
    public ArrayList<Song> getSongs() {
        return new ArrayList<>(this.songs);
    }
    /**
     * Gets album artwork
     * 
     * @return album artwork
     * 
     */
    public ObjectProperty<Image> artworkProperty() {
        return this.artworkProperty;
    }
    /**
     * Stores album artwork for a corresponding artist
     * 
     * @return null
     */
    public Image getArtwork() {
        return null;
        
    }

    public void downloadArtwork() {
        
    }
    /**
     * Adds the list of songs from an artist to the corresponding album
     */
    public void addSongs(Song song){
        songs.add(song);
    }
    /**
     * Sets the artwork to the matching artist and album title
     */
    public void setArtWork(){
        art = new AlbumArt(this.title,this.artist);
    }
    /**
     * Compares two albums with each other to determine duplictates
     * 
     * @return first instance of album
     */
    @Override
    public int compareTo(Album other) {
        String first = removeArticle(this.title);
        String second = removeArticle(other.title);

        return first.compareTo(second);
    }
    /**
     * Removes articles( "a", "an" and "the") from album titles
     * 
     * @return null
     */
    private String removeArticle(String title) {
        return null;
        //removes "a", "an" and "the" from the name of the album
    }
}
