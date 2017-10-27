import java.awt.Image;
import java.util.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
public final class Artist implements Comparable<Artist> {

    private String title;
    private ArrayList<Album> albums;
    private Image artistImage;
    private SimpleObjectProperty<Image> artistImageProperty;

    /**
     * Constructor for the Artist class.
     * Creates an artist object and obtains the artist artwork.
     *
     * @param title Artist name
     * @param albums List of artist albums
     */
    public Artist(String title, ArrayList<Album> albums) {
        this.title = title;
        this.albums = albums;
        this.artistImageProperty = new SimpleObjectProperty<>(getArtistImage());
    }

    /**
     * Gets the artist title.
     * @return artist title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets array list of artist albums
     * @return artist albums
     */
    public ArrayList<Album> getAlbums() {
        return new ArrayList<>(this.albums);
    }

    public ObjectProperty<Image> artistImageProperty() {
        return this.artistImageProperty;
    }

    /**
     * Gets images for artists
     * @return artist image
     */
    public Image getArtistImage() {
        return null;
        //gets Image of the artist for the gui
    }

    public void downloadArtistImage() {
        
    }

    private String removeArticle(String title) {
        return null;
        //removes "a", "an" and "the" from the name of the artist
        
    }

    @Override
    public int compareTo(Artist o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
