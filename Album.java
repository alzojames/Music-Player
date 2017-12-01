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
     * Creates an album object and obtains the album artwork.
     *
     * @param id
     * @param title
     * @param artist
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

    public String getArtist() {
        return this.artist;
    }

    public ArrayList<Song> getSongs() {
        return new ArrayList<>(this.songs);
    }

    public ObjectProperty<Image> artworkProperty() {
        return this.artworkProperty;
    }

    public Image getArtwork() {
        return null;
        
    }

    public void downloadArtwork() {
        
    }
    
    public void addSongs(Song song){
        songs.add(song);
    }

    @Override
    public int compareTo(Album other) {
        String first = removeArticle(this.title);
        String second = removeArticle(other.title);

        return first.compareTo(second);
    }

    private String removeArticle(String title) {
        return null;
        //removes "a", "an" and "the" from the name of the album
    }
}
