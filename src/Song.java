
package musicplayer;

/*
 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 */

/**
 *
 * @author jndemera2
 * 
 */

import javafx.scene.media.Media;

import javafx.scene.media.MediaPlayer;

import java.io.*;

import org.apache.tika.exception.TikaException;

import org.apache.tika.metadata.Metadata;

import org.apache.tika.parser.ParseContext;

import org.apache.tika.parser.Parser;

import org.apache.tika.parser.mp3.Mp3Parser;

import org.xml.sax.ContentHandler;

import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;

import javafx.scene.media.*;


public class Song

{
    private String id;
    
    private String title;
    
    private String artist;
    
    private String album;
    
    //private String length = "length";
    
    private String composer;
    
    private String genre;
    
    private String duration;
    
    

    
    public Song(String filelocation)
    
    {
        //getmetaData(filelocation);
    	
        
        // String fileLocation = "";//"C:\\Users\\ndeme\\Documents\\New folder\\test.mp3";
    	
        id = filelocation;
        
        String metaData[] = new String[3];
        
        try {

        	String fileLocation = id;
        	

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

            if(title == null){
            	
                title = "Unkown";
                
            }
            
            if(artist == null){
            	
                artist = "Unkown";
                
            }
            
            
            if(composer == null){
            	
                composer = "Unkown";
                
            }
            
            
            if(genre == null){
            	
                genre = "Unkown";
                
                
            }
            
            if(album == null){
            	
                album = "Unkown";
                
            }
            
            
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

            //return metaData;
            
            //System.out.println("Title: " + title + "\nArtist: " + artist + "\nComposer: " + composer + "\nGenre: " + genre + "\nAlbum: " +album);
            

            }catch (FileNotFoundException e)
        
            {
            	
            e.printStackTrace();
            
            } catch (IOException e) {
            	
            e.printStackTrace();
            
            } catch (SAXException e) {
            	
            e.printStackTrace();
            
            } catch (TikaException e) {
            	
            e.printStackTrace();
            
            }



    }
    
    /**
     * 
     * Gets title
     *
     * @return title
     * 
     */
    
    public String getTitle(){
    	
        return title;
        
    }
    
    /**
     * 
     * Gets artist name
     *
     * @return artist name
     * 
     */
    
    public String getArtist(){
    	
        return artist;
        
    }
    /**
     * 
     * Gets album name
     * 
     * @return album name
     */
    
    public String getAlbum(){
    	
        return album;
        
    }
    
    /**
     * 
     * Gets album ID.
     *
     * @return album ID
     * 
     */
    
    public String getId(){
    	
        return id;
        
    }
    
    /**
     * 
     * Sets the title for the song from the metadata
     * 
     */
    
    public void setTitle(String Title){
    	
        this.title =  Title;
        
    }
    
    /**
     * 
     * Sets the artist name for the song from the metadata
     * 
     */
    
    public void setArtist(String newArtist){
    	
        this.artist =  newArtist;
        
    }
    
    /**
     * 
     *  Sets the album name for the song from the metadata
     *  
     */
    
    public void setAlbum(String newAlbum){
    	
        this.album =  newAlbum;
        
    }
    
    /**
     * 
     *  Sets the format for the display of song information( title, artist and album) 
     * 
     * @return String.format
     * 
     */
    
    public String toString()
    
    {
    	
        return String.format("%-80s \t%-30s \t%-20s", getTitle(),getArtist(),getAlbum());
        
    }
    
}