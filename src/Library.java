package musicplayer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Japhet
 */
import java.io.*;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;
import musicplayer.Album;
public class Library {
    private static  String ID = "id";
    private static  String title = "title";
    private static  String artist = "artist";
    private static  String album = "album";
    private static  String length = "length"; 
    private static  String composer = "length";
    private static  String trackNumber = "trackNumber";
    private static  String discNumber = "discNumber";
    private static  String playCount = "playCount";
    private static  String playDate = "playDate";
    private static  String genre = "location"; 
    

    static ArrayList<Song> songs = new ArrayList<Song>();
    static HashMap<String,Artist> artists = new HashMap<String,Artist>();
    static HashMap<String,Album> albums = new HashMap<String,Album>();
    //HashMap<String,Album> al;
    //private static ArrayList<Playlist> playlists;
    //private static int maxProgress;

    
    /*
    This method uses the Apche Tika library to get the metadata from the files and
    store them in the fields
    */
    public String[] getmetaData(String fileLocation){
         //String fileLocation = "C:\\Users\\ndeme\\Documents\\New folder\\test.mp3";
         String metaData[] = new String[3];
        try {

        InputStream input = new FileInputStream(new File(fileLocation));
        ContentHandler handler = new DefaultHandler();
        Metadata metadata = new Metadata();
        Parser parser = new Mp3Parser();
        ParseContext parseCtx = new ParseContext();
        parser.parse(input, handler, metadata, parseCtx);
        input.close();

        // List all metadata
        String[] metadataNames = metadata.names();

        // Retrieve the necessary info from metadata
        // Names - title, xmpDM:artist etc. - mentioned below may differ based

        title = metadata.get("title");
        artist = metadata.get("xmpDM:artist");
        composer = metadata.get("xmpDM:composer");
        genre = metadata.get("xmpDM:genre");
        album = metadata.get("xmpDM:album");
        
        for(int i = 0; i < metaData.length; i++){
            
            switch (i){
                case 0: metaData[i] = title;
                        break;
                case 1: metaData[i] = artist;
                        break;
                case 2: metaData[i] = album;
                        break;
            }
        }
         
        return metaData;
        //System.out.println("Title: " + title + "\nArtist: " + artist + "\nComposer: " + composer + "\nGenre: " + genre + "\nAlbum: " +album);
                
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        } catch (SAXException e) {
        e.printStackTrace();
        } catch (TikaException e) {
        e.printStackTrace();
        }
        
        return metaData;
    }
    
    public static void addSong(Song song){
       songs.add(song); 
    }
}