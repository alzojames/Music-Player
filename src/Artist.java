/*
 * This class dynamically adds ablum art when a new album in a grid
 * 
 */

/**
 *
 * @author Japhet
 */

package musicplayer;

import java.awt.Image;
import java.util.HashSet;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import musicplayer.Album;
public final class Artist implements Comparable<Artist> {

    private String name;
    static HashSet<Album> albums = new HashSet<Album>();
    private Image artistImage;
    private SimpleObjectProperty<Image> artistImageProperty;
    ArtistWidget artistWidget;

    /**
     * Constructor for the Artist class.
     * Creates an artist object and obtains the artist artwork.
     *
     * @param name Artist name
     */
    public Artist(String name)
    {

        if(name == null)
        {
            this.name = "Unkown";
        }else{
            this.name = name;
        }
        
        artistWidget = new ArtistWidget();
        this.artistImageProperty = new SimpleObjectProperty<>(getArtistImage());

    }

    /**
     * Gets the artist title.
     * @return artist title
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Gets array list of artist albums
     * @return artist albums
     */

    //public ArrayList<Album> getAlbums() {
      //  return new ArrayList<>(this.albums);
    //}

    public ObjectProperty<Image> artistImageProperty()
    {
        return this.artistImageProperty;
    }

    /**
     * Gets images for artists
     * @return artist image
     */
    public Image getArtistImage()
    {
        return null;
        //gets Image of the artist for the gui
    }

    public void downloadArtistImage()
    {
        
    }

    private String removeArticle(String title)
    {
        return null;
        //removes "a", "an" and "the" from the name of the artist
        
    }

    @Override
    public int compareTo(Artist o)
    {
        return 0;
    }

    public static void addAlbums(Album album)
    {
        albums.add(album); 
        
    }
    
    public void addSong(Song song)
    {
        artistWidget.addSong(song);
    } 
    
    public ArtistWidget getArtistWidget()
    {
        return artistWidget;
    }
    
}
