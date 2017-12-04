/*
 * This class dynamically adds ablum art when a new album in a grid
 * 
 */

/**
 *
 * @author Japhet
 */

package musicplayer;

import com.google.common.io.Resources;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashSet;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.imageio.ImageIO;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import musicplayer.Album;

public final class Artist implements Comparable<Artist> {

    private String name;
    HashSet<Album> albums = new HashSet<Album>();
    private Image artistImage;
    private SimpleObjectProperty<Image> artistImageProperty;
    ArtistWidget artistWidget;
    ArtistArtWork artWork;
    private String title;

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
        //this.artistImageProperty = new SimpleObjectProperty<>(getArtistImage());

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
//    public ArrayList<Album> getAlbums()
//    {
//        return new ArrayList<Album>(this.albums);
//    }

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
         if (artistImage == null) {
            try {
                File file = new File("/img/" + this.name + ".jpg");
                if (artistImage.equals(file)) {
                    return artistImage;
                    
                }
            } catch (Exception ex) {
                File file = new File("/img/" + this.name + ".jpg");
                file.delete();
            }
            return artistImage;
        }
         return artistImage;
    }
    
    

    public void downloadArtistImage()
    {
        try {
            File file = new File("/img/" + this.title + ".jpg");
            file.mkdirs();
            XMLInputFactory factory = XMLInputFactory.newInstance();
            URL xmlData = new URL("method=artist.getinfo"
                    + "&artist=" + URLEncoder.encode(this.title, "UTF-8")
                    + "&api_key=");
            XMLStreamReader reader = factory.createXMLStreamReader(xmlData.openStream(), "UTF-8");
            boolean imageFound = false;

            while (reader.hasNext() && !imageFound) {
                reader.next();

                if (reader.isStartElement()
                        && reader.getName().getLocalPart().equals("image")
                        && reader.getAttributeValue(0).equals("extralarge")) {

                    reader.next();

                    if (reader.hasText()) {
                        BufferedImage bufferedImage = ImageIO.read(new URL(reader.getText()));
                        BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
                                bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
                        newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
                        ImageIO.write(newBufferedImage, "jpg", file);
                        imageFound = true;
                    }
                }
            }

            
            if (artistImage.equals(file)) {
                file.delete();
                
            }
            this.artistImageProperty.setValue(artistImage);

        } catch (Exception ex) {
            File file = new File("/img/" + this.name + ".jpg");
            file.delete();
        }
    }

    public String removeArticle(String title)
    {
        
        //removes "a", "an" and "the" from the name of the artist
        String arr[];
        arr = title.split(" ", 2);

        if (arr.length < 2) {
            return title;
        } else {

            String firstWord = arr[0];
            String theRest = arr[1];

            switch (firstWord) {
                case "A":
                case "An":
                case "The":
                    
                    return theRest;
                default:
                    return title;
            }
        }
    }
    
    public void setArt()
    {
        artWork = new ArtistArtWork(this.name);
    }
    
    public ArtistArtWork getArt(){
        return artWork; 
    }

    @Override
    public int compareTo(Artist other)
    {
        String first = removeArticle(this.title);
        String second = removeArticle(other.title);

        return first.compareTo(second);
    }
    
    public void addSong(Song song)
    {
        artistWidget.addSong(song);
    } 
    
    public ArtistWidget getArtistWidget()
    {
        return artistWidget;
    }
    
    public HashSet<Album> getAlbums(){
        return this.albums;
    }
    

    
    
}