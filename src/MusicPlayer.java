/**
 * This class will display all the assests of the program

 * and will handle operation regurding the play, pause, next song
 * prevous song etc.
 *
 */

/**
 *
 * @author jndemera2
 */
package musicplayer;


import javafx.application.Application;


import javafx.event.ActionEvent;


import javafx.event.EventHandler;


import javafx.scene.Scene;


import javafx.scene.layout.*;


import javafx.stage.Stage;


import com.jfoenix.controls.JFXButton;


import com.jfoenix.controls.JFXTabPane;


import java.awt.Color;


import java.io.File;


import static java.lang.Math.log;


import java.lang.reflect.InvocationTargetException;


import java.util.ArrayList;


import java.util.Collections;


import java.util.Comparator;


import java.util.HashMap;


import java.util.Iterator;


import java.util.List;


import java.util.concurrent.TimeUnit;


import java.util.logging.Level;


import java.util.logging.Logger;


import javafx.application.Platform;


import javafx.beans.InvalidationListener;


import javafx.beans.Observable;


import javafx.beans.value.ChangeListener;


import javafx.beans.value.ObservableValue;


import javafx.collections.ObservableList;


import javafx.geometry.Pos;



import javafx.scene.Group;


import javafx.scene.control.Alert;


import javafx.scene.control.Button;


import javafx.scene.control.Label;


import javafx.scene.control.ScrollPane;


import javafx.scene.control.Tab;


import javafx.scene.image.Image;


import javafx.scene.image.ImageView;


import static javafx.scene.layout.GridPane.setHgrow;


import javafx.scene.media.Media;


import javafx.scene.media.MediaPlayer;


import javafx.scene.media.MediaView;


import javafx.stage.FileChooser;


import javafx.util.Duration;


import static musicplayer.MusicPlayer.mediaPlayer;


/**
 * This class sets up the GUI for the Music Player. Assigns the tabs for PLaylists, Artists, Albums, Songs, and Genres. 
 * 
 * 
 */
public class MusicPlayer extends Application {
        
    private static final double minChange = 0.5;
    
    static MediaPlayer mediaPlayer;
    
    static Media media;
    
    private Label time;
    
    static MediaView mediaView;
    
    static String path;
    
    Duration duration;
    
    Button fullScreenButton;
  
    double width;
    
    double height;

    PlayListWidget playList = new PlayListWidget();
    
    static PlayPauseWidget playPause;
    
    SongWidget songWidget;
    
    AlbumArt albumArt;
    
    ArtistWidgetHolder artistWidgetHolder;
    
    static AlbumArtWidget albumArtWidget;
    //ArtistWidget artistWidget;

    static ScrollPane sp = new ScrollPane();
    
    static ScrollPane sp2 = new ScrollPane();
    
    static Tab playListTab = new Tab("Playlists");
    
    static Tab artistTab = new Tab("Artists");
    
    static Tab albumTab = new Tab("Albums");
    
    static Tab songTab = new Tab("Songs");
    
    static Tab Genre = new Tab("Genres");
        
    static ArrayList<Song> playing;
    
    static int songIndex;
    
    ArrayList<Album> albumToMake;
    
    ArrayList<Artist> artistToMake;
    
    static int numOfImports;
    
    /**
     * This will start of the program, loading all the assests
     * onto the Stage
     *
     * @author Stage primaryStage
     *
     */
    public void start(Stage primaryStage)
    {
        
        primaryStage.setTitle("Elysium Music Player");
        
        Group root = new Group();
        
        Scene scene = new Scene(root, 1000, 600);
        
        primaryStage.getIcons().add(new Image("file:logo.jpg"));
        
        JFXTabPane tabPane = new JFXTabPane();
        
        GridPane grid = new GridPane();
        
        PlayPauseWidget playMenu = new PlayPauseWidget();
        
        Tab[] tabs = new Tab[5];
        
        String tabName[] = {"Playlists","Artists","Albums","Songs","Genres"};
        
        BorderPane borderPane = new BorderPane();
        
        playPause = new PlayPauseWidget();
        
        borderPane.setBottom(playPause);
        
        
        
        Tab tab;
        
        playListTab = new Tab("Playlists");
        
        artistTab = new Tab("Artists");
        
        albumTab = new Tab("Albums");
        
        songTab = new Tab("Songs");
        
        Genre = new Tab("Genres");
        
        sp.setFitToWidth(true);
        
        sp.setContent(albumArtWidget = new AlbumArtWidget());
        
        sp2.setFitToWidth(true);
        
        sp2.setContent(artistWidgetHolder = new ArtistWidgetHolder());
        
        //create new tabs and put their widgets in them 
        
        for (int i = 0; i < 5; i++)
        
        {
            
            tab = new Tab();
            
            tab.setText(tabName[i]);
            
            HBox hbox = new HBox();
            
            //Call the Playlist Artist... Widget classes and put the on the TabPane
            
            switch (i)
           
            {
                case 0: playListTab.setContent(new PlayPauseWidget());
                
                        break;
                        
                case 1: artistTab.setContent(sp2);
                
                        break;
                        
                case 2: albumTab.setContent(sp);
                
                        break;
                        
                case 3: songTab.setContent(songWidget = new SongWidget());
                
                        break;
                        
                case 4: Genre.setContent(new PlayPauseWidget());
                
                        break;
                
            }
    
            hbox.setAlignment(Pos.CENTER);
            
        }
        
        
        tabPane.getTabs().addAll(songTab,albumTab,artistTab);


     
        /**
         * Allows the user to import files from the disk
         *
         */
        playPause.importFiles.setOnAction(
        		
            (ActionEvent event) -> {
            	
                FileChooser fileChooser = new FileChooser();
                
                //fileChooser.setMultiSelectionEnabled(true);
                
                //Open file explorer that allows user to pick mp3 files
                
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("mp3 files (*.mp4)", "*.mp3");
                
                fileChooser.getExtensionFilters().add(extFilter);


                
               //Put the files in a list and add them to the library
                
                List<File> list =
                		
                    fileChooser.showOpenMultipleDialog(primaryStage);
                    
                numOfImports = 0;
                
                albumToMake = new ArrayList<Album>();
                
                artistToMake = new ArrayList<Artist>();
                
                Thread th = new Thread()
                {
                    
                 
                	
                	@Override
                
                	public void run()
                
                	
                	{
                        
                        try
                
                        
                        {
                        
                        	
                        	importMusic(list);
                           
                        
                        } catch (Exception e) {
                        
                        	
                        }

                        
                    }
                    
                };
                                
                try
           
                {
                    th.start();
                    
                    //th2.start();
                    
                    TimeUnit.SECONDS.sleep(2);
                    
                    Collections.sort(albumToMake, new Comparator<Album>(){
                    	
                        public int compare(Album s1, Album s2) {
                            return s1.getTitle().compareToIgnoreCase(s2.getTitle());
                        }
                    });
                    
                    Collections.sort(artistToMake, new Comparator<Artist>(){
                        public int compare(Artist s1, Artist s2) {
                            return s1.getName().compareToIgnoreCase(s2.getName());
                        }
                    });
                    
                    for(int i = 0; i < albumToMake.size(); i++){
                        
                         try{
                             
                             System.out.println("trying to make artWork for: " + albumToMake.get(i).getTitle());
                             albumArtWidget.addArtWork(albumToMake.get(i).art);
                             //playListTab.setContent(albumToMake.get(i).art);
                         }catch(IllegalStateException e){
                             System.out.print("Unable to make art");
                         }
                    }
                    
                   
                    
                    for(int i = 0; i < artistToMake.size();i++){
                        
                        System.out.println(artistToMake.get(i).getName());
                        
                        artistToMake.get(i).setArt();
                        artistWidgetHolder.addArtist(artistToMake.get(i).getArt());
                        sp2.setContent(artistWidgetHolder);
                        
                    }
                       
                    Alert alert 
                            = new Alert(Alert.AlertType.INFORMATION);
                    
                    alert.setTitle("Import Complete");
                    alert.setHeaderText("Import Complete");
                    alert.setContentText("Songs where succesfully imported");
                    
                    alert.showAndWait();
        
                }catch(IllegalStateException a){
                    System.out.print("");
                } catch (InterruptedException ex) {
                    Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
                        
            }
                
        );
        
        /*
         *This event handler will remove the icon of the play buttun and replave it with the pause button.
         *It will then play the currently selected song
         *And update the currently playing song on the display
         *
         */
        playPause.play.setOnAction(new EventHandler<ActionEvent>()
        {
            
            @Override
            public void handle(ActionEvent arg0)
            {

                try
                {

                    playPause.getChildren().remove(playPause.play);
                    Image lastIcon = new Image(getClass().getResourceAsStream("pauseIcon.png"));
                    playPause.pause.setGraphic(new ImageView(lastIcon));
                    ImageView pauseView = new ImageView(lastIcon);
                    pauseView.setFitWidth(5);
                    pauseView.setFitHeight(5);
                    playPause.add(playPause.pause,1,2);
                    mediaPlayer.play();

                }catch(NullPointerException e){

                    System.out.print("Null Pointer");
                    playPause.getChildren().remove(playPause.pause);
                    Image lastIcon = new Image(getClass().getResourceAsStream("playIcon.png"));
                    playPause.play.setGraphic(new ImageView(lastIcon));
                    ImageView pauseView = new ImageView(lastIcon);
                    pauseView.setFitWidth(5);
                    pauseView.setFitHeight(5);
                    playPause.add(playPause.play,1,2);

                }

            }
                    
        });
        
        /*
         *This event handler will remove the icon of the pause buttun and replace it with the play button.
         *It will then pause the currently selected song
         *
         */
        playPause.pause.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent arg0)
            {

                try
                {
                    
                    System.out.print("Pause");
                    playPause.getChildren().remove(playPause.pause);
                    Image lastIcon = new Image(getClass().getResourceAsStream("playIcon.png"));
                    playPause.play.setGraphic(new ImageView(lastIcon));
                    ImageView pauseView = new ImageView(lastIcon);
                    pauseView.setFitWidth(5);
                    pauseView.setFitHeight(5);
                    playPause.add(playPause.play,1,2);

                    mediaPlayer.pause();
                    System.out.print("Pause!!!!!!");
                    
                }catch(NullPointerException e){
                    
                    playPause.getChildren().remove(playPause.play);
                    Image lastIcon = new Image(getClass().getResourceAsStream("pauseIcon.png"));
                    playPause.pause.setGraphic(new ImageView(lastIcon));
                    ImageView pauseView = new ImageView(lastIcon);
                    pauseView.setFitWidth(5);
                    pauseView.setFitHeight(5);
                    playPause.add(playPause.pause,1,2);
                    
                }

            }
            
        });
               
        
        /*
         *This event handler will allow the user to mute or unmute audio
         *
         */
        playPause.volume.setOnAction(new EventHandler<ActionEvent>()
        {

                @Override
                public void handle(ActionEvent arg0)
                {

                    System.out.print("Tryin to mute");
                    //mediaPlayer.setMute(true);
                    //playPause.volumeSlider.setDisable(true);
                    if(mediaPlayer.isMute())
                    {
                        mediaPlayer.setMute(false);
                    }else{
                        mediaPlayer.setMute(true);
                    }
                    
                }
                    
        });

        
        /*
         *This event handler will stop the currently playing song and and go to the next song
         *
         */
        playPause.next.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent arg0)
            {
                
                System.out.print(songIndex + " + 1 = " );
                System.out.print(songIndex+1);
                songIndex++;
                
                try
                {
                    mediaPlayer.stop();
                    mediaPlayer = null;
                    System.gc();
                    selectSong(songIndex);

                }catch(IndexOutOfBoundsException e){

                    songIndex = 0;
                    mediaPlayer.stop();
                    mediaPlayer = null;
                    System.gc();
                    selectSong(songIndex);

                }

            }
                    
        });
        
        /*
         *This event handler will stop the currently playing song and and go to the last song
         *
         */
        playPause.last.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent arg0)
            {
                
                System.out.print(songIndex + " - 1 = " );
                System.out.print(songIndex-1);
                songIndex = songIndex -1;
                
                
                try
                {
                    mediaPlayer.stop();
                    mediaPlayer = null;
                    System.gc();
                    selectSong(songIndex);

                }catch(IndexOutOfBoundsException e){
                    
                    songIndex = 0;
                    try
                    {

                        System.out.print("Index" + songIndex);
                        
                        mediaPlayer.stop();
                        mediaPlayer = null;
                        System.gc();
                        selectSong(songIndex);

                    }catch(NullPointerException a){
                        
                    }

                }

            }
                    
        });
        
       
        /*
         *This event handler will allow the user to change volume levels
         *
         */
        playPause.volumeSlider.valueProperty().addListener(new InvalidationListener()
        {

            public void invalidated(Observable ov)
            {

                if (playPause.volumeSlider.isValueChanging())
                {

                    mediaPlayer.setMute(false);
                    mediaPlayer.setVolume(playPause.volumeSlider.getValue() / 100.0);

                }

            }

        });
        
        
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        borderPane.setCenter(tabPane);
        root.getChildren().addAll(borderPane,grid);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    

    /**
     * This method will set the path for the selected song
     *
     * @param String fileLocation
     */    
    public static void setPath(String filelocation)
    {

        path = filelocation;
        media = new Media(new File(path).toURI().toString());
        System.out.print("New song playing");

    }
    
    /**
     * This method will check to see is the album the song belongs to in the library
     *
     * @param Song song
     *
     */   
    public void addSongToAlbum(Song song)
    {
        
        if (Library.albums.containsKey(song.getAlbum()))
        {
            
            //if the album already exists add the song to the album 
            Library.albums.get(song.getAlbum()).addSongs(song);
            System.out.println(song.getAlbum());
            Library.artists.get(song.getArtist()).artistWidget.addSong(song);
            //artistWidget.addSong(song, song.getAlbum(), song.getArtist());
            
        } else {
            
            //if it doesn't make new Album and add it to the library
            Album album = new Album(song.getAlbum(), song.getArtist());
            Library.albums.put(song.getAlbum(),album); 

            //add the song to the new album
            album.addSongs(song);

            //addAlbumToArtist(album);
            System.out.println(album.getTitle());
            addAlbumToArtist(album);

            //add artist to the artistWidgetHolder
            Library.artists.get(album.getArtist()).getArtistWidget().addSong(song);
            ArtistWidget artistWidget = Library.artists.get(album.getArtist()).getArtistWidget();
            
            
            //make art work
            album.setArtWork();
            albumToMake.add(album);

        }
 
    }
    
    /**
     * This method will add albums to  their respective Artists
     *
     * @param Album album
     *
     */  
    public void addAlbumToArtist(Album album)
    {
        
        if (Library.artists.containsKey(album.getArtist())){
                                
            Library.artists.get(album.getArtist()).albums.add(album);
            
        } else {
           
            Artist artist = new Artist(album.getArtist());
            
            
            Library.artists.put(album.getArtist(),artist); 
            artist.albums.add(album);
            artistToMake.add(artist);
            
            System.out.println("New Artist " + album.getArtist());
            
        }
        
    }
    
    /**
     * This method will add play selceted song from one of the widget
     *
     * @param int index
     *
     * @param ArrayList<Song> songList
     *
     */  
    public static void selectSong(int index, ArrayList<Song> songList)
    {

        playing = songList;
        songIndex = index;

        try
        {
            
            mediaPlayer.stop();
            mediaPlayer = null;
            System.gc();
        
        } catch(NullPointerException e) {
            
        }
         
        Song song = playing.get(index);
        //playing = songs.getItems();
        playPause.timeSlider.setDisable(false);
        media = new Media(new File(song.getId()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(playPause.volumeSlider.getValue() / 100.0);
        
        
        String len = song.getDuration();
        Double length = Double.parseDouble(len);
        playPause.timeSlider.setMax(length);
        
        /*
         * This event handler goes to the next song when the current song is finished playing
         *
         */
        mediaPlayer.setOnEndOfMedia(new Runnable()
        {

            @Override
            public void run()
            {
                selectSong(index+1);
            }

        });
        
        /*
         * This event handler links the timeSlider to the mediaPlayer
         * Allowing the user to see the progress of the 
         *
         */
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>()
        {

            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue)
            {
                playPause.timeSlider.setValue(newValue.toMillis());
            }
            
        });
        
        /*
         * This event handler links the timeSlider to the mediaPlayer
         * 
         * Allowing the user to select a point in the music they would like to go to
         *
         */
        playPause.timeSlider.setOnMouseClicked(new ListViewHandler()
        {

            @Override
            public void handle(javafx.scene.input.MouseEvent event)
            {
            
                mediaPlayer.seek(Duration.millis(playPause.timeSlider.getValue()));
                
                System.out.println("Time changing");
                
            }
            
        });
        

        mediaView = new MediaView(mediaPlayer);
        
        playPause.currentSongDisplay.clear();
        
        playPause.currentSongDisplay.appendText("Song: " +  song.getTitle() + "\nArtist: " + song.getArtist() + "\nAlbum: " + song.getAlbum());

    }
    
    /*
     * This method selects the next song to be played 
     * 
     * 
     * 
     * @param int index
     *
     */
    public static void selectSong(int index)
    
    {

        try
        {
            mediaPlayer.stop();
            
            
            mediaPlayer = null;
            
            System.gc();
            
        }catch(NullPointerException e){
            
        }


        try
        {
            songIndex = index;
            
            Song song = playing.get(index);

            
            media = new Media(new File(song.getId()).toURI().toString());
            
            mediaPlayer = new MediaPlayer(media);
            
            playPause.timeSlider.setDisable(false);
            
            mediaPlayer.setAutoPlay(true);
            
            mediaPlayer.setVolume(playPause.volumeSlider.getValue() / 100.0);
            
            mediaView = new MediaView(mediaPlayer);
            
            String len = song.getDuration();
            
            Double length = Double.parseDouble(len);
            
            playPause.timeSlider.setMax(length);
            
            
            mediaPlayer.setOnEndOfMedia(new Runnable()
            {

                @Override
                
                public void run()
                
                {
                    selectSong(index+1);
                
                }

            });
            
            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>()
            
            {

                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue)
                
                {
                    playPause.timeSlider.setValue(newValue.toMillis());
                    
                }
                
            });
            
            playPause.timeSlider.setOnMouseClicked(new ListViewHandler()
            
            {

                @Override
             
                public void handle(javafx.scene.input.MouseEvent event)
                
                {
                    mediaPlayer.seek(Duration.millis(playPause.timeSlider.getValue()));
                    
                    System.out.println("Time changing");
                }
                
            });
                    
            playPause.currentSongDisplay.clear();
            
            playPause.currentSongDisplay.appendText("Song: " +  song.getTitle() + "\nArtist: " + song.getArtist() + "\nAlbum: " + song.getAlbum());
            

        }catch (IndexOutOfBoundsException e){
                
        }

        
    }
    
    /*
     * Puts selected songs into the library
     * 
     * @param List<File> list
     *
     */
    public void importMusic(List<File> list)
    
    {
        
        System.out.print("New method");
        
        Song song;
                
                
        if (list != null) 
        
        {

        
        	for (File file : list)
        
        	{
                numOfImports ++;

                //openFile(file);
                
                String fileString = file.toString();
                
                //format string so it can be use as URL
                
                fileString = fileString.replace("\\", "\\\\");
                
                song = new Song(fileString);
                

                //add the song the library
                try
                {
                    Library.addSong(song);
                    
                }catch(NullPointerException haha){
                	
                    System.out.print("Error!!!");
                }

                //display song on the SongWidget
                
                songWidget.addSong(song);
                
                //add song to an album
                
                addSongToAlbum(song);
                

            }

        }
        
    }
        
    /*
     * This method plays selected song and alternates between the play and pause icon
     * 
     * 
     * @param int index
     *
     */
    public void playSong(int index)
    {

         try
        {
             
            System.out.print("Pause");
            
            playPause.getChildren().remove(playPause.pause);
            
            Image lastIcon = new Image(getClass().getResourceAsStream("playIcon.png"));
            
            playPause.play.setGraphic(new ImageView(lastIcon));
            
            ImageView pauseView = new ImageView(lastIcon);
            
            pauseView.setFitWidth(5);
            
            pauseView.setFitHeight(5);
            
            playPause.add(playPause.pause,1,2);

            MusicPlayer.mediaPlayer.pause();
            
            System.out.print("Pause!!!!!!");
        }

             
        catch(NullPointerException e){
        	
            System.out.print("Error");
            
        }
        
        MusicPlayer.selectSong(index);
        
        Image lastIcon = new Image(getClass().getResourceAsStream("pauseIcon.png"));
        
        playPause.pause.setGraphic(new ImageView(lastIcon));
        
        ImageView pauseView = new ImageView(lastIcon);
        
        pauseView.setFitWidth(5);
        
        pauseView.setFitHeight(5);
        
        playPause.add(playPause.pause,1,2);
        
        MusicPlayer.mediaPlayer.play();
                
    }
    
    

}