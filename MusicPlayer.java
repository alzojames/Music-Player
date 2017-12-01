
package musicplayer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.jfoenix.controls.*;
import javafx.geometry.*;
import com.jfoenix.controls.JFXButton;
import java.awt.Color;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.layout.GridPane.setHgrow;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;


/**
 *
 * @author jndemera2
 */
public class MusicPlayer extends Application {
        
   private static final double minChange = 0.5;
    static MediaPlayer mediaPlayer;
    private Label time;
    Duration duration;
    //Button fullScreenButton;
    //Scene scene;
    static Media media;
    double width;
    double height;
    static MediaView mediaView;
    static String path;
    ArrayList<Song> playing;
    
    static PlayPauseWidget playPause;
    SongWidget songWidget;
    AlbumArt albumArt;
    PlayListWidget playList = new PlayListWidget();
    //ArtistWidget artistWidget;
    ArtistWidgetHolder artistWidgetHolder;
    int songIndex;
    
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Tabs");
        Group root = new Group();
        Scene scene = new Scene(root, 1000, 600);

        JFXTabPane tabPane = new JFXTabPane();
        GridPane grid = new GridPane();
        //PlayPauseWidget playMenu = new PlayPauseWidget();
        Tab[] tabs = new Tab[5];
        String tabName[] = {"Playlists","Artists","Albums","Songs","Genres"};
        BorderPane borderPane = new BorderPane();
        playPause = new PlayPauseWidget();
        borderPane.setBottom(playPause);
        
        //Add tabs to the the TabPane
        Tab tab;
        for (int i = 0; i < 5; i++) {
            
            tab = new Tab();
            tab.setText(tabName[i]);
            HBox hbox = new HBox();
            
            //Call the Playlist Artist... Widget classes and put the on the TabPane
            switch (i){
                case 0: tab.setContent(new PlayPauseWidget());
                        break;
                case 1: tab.setContent(artistWidgetHolder = new ArtistWidgetHolder());
                        break;
                case 2: tab.setContent(albumArt = new AlbumArt());
                        break;
                case 3: tab.setContent(songWidget = new SongWidget());
                        break;
                case 4: tab.setContent(new PlayPauseWidget());
                        break;
                
            }
    
            hbox.setAlignment(Pos.CENTER);
            tabPane.getTabs().add(tab);
        }
        
        playPause.timeSlider.valueProperty().addListener((obs, oldValue, newValue)->{
            if(!playPause.timeSlider.isValueChanging()) {
            double currentTime = mediaPlayer.getCurrentTime().toSeconds();
            if(Math.abs(currentTime - newValue.doubleValue())> minChange) {
                mediaPlayer.seek(playPause.duration.multiply((long) newValue.doubleValue()));
                //mediaPlayer.see
            }
            }
        });
        
        playPause.volumeSlider.valueProperty().addListener(new InvalidationListener(){
            public void invalidated(Observable ov) {
                if (playPause.volumeSlider.isValueChanging()) {
                    mediaPlayer.setMute(false);
                    mediaPlayer.setVolume(playPause.volumeSlider.getValue() / 100.0);
                }
            }
        });
        
        playPause.timeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (playPause.timeSlider.isValueChanging()) {
                    //multiply duration by percentage calculated by slider position
                    //mediaPlayer.seek(duration.multipliedBy((long) (playPause.timeSlider.getValue() / 100.0)));
                    //mediaPlayer.seek(duration.multipliedBy(0));
                }
            }
        });
     
        
        playPause.importFiles.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                FileChooser fileChooser = new FileChooser();
                //fileChooser.setMultiSelectionEnabled(true);
                //Open file explorer that allows user to pick mp3 files
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("mp3 files (*.mp3)", "*.mp3");
                fileChooser.getExtensionFilters().add(extFilter);


                //Put the files in a list and add them to the library
                List<File> list =
                    fileChooser.showOpenMultipleDialog(primaryStage);
                Song song;
                
                
                if (list != null) {
                    for (File file : list) {
                        
                        //openFile(file);
                        String fileString = file.toString();
                        //format string so it can be use as URL
                        fileString = fileString.replace("\\", "\\\\");
                        song = new Song(fileString);
                        
                        //add the song the library
                        try{
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
                
        });
        
        /*
        This event handler will remove the icon of the play buttun and replave it with the pause button.
        It will then play the currently selected song
        And update the currently playing song on the display
        */
        playPause.play.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent arg0) {
                playPause.getChildren().remove(playPause.play);
                Image lastIcon = new Image(getClass().getResourceAsStream("pauseIcon.png"));
                playPause.pause.setGraphic(new ImageView(lastIcon));
                ImageView pauseView = new ImageView(lastIcon);
                pauseView.setFitWidth(5);
                pauseView.setFitHeight(5);
                playPause.add(playPause.pause,1,2);
                mediaPlayer.play();

            }
                    
        });
        
        playPause.pause.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
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
            }
            
        });
                
        playPause.volume.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent arg0) {
                    System.out.print("Tryin to mute");
                    //mediaPlayer.setMute(true);
                    //playPause.volumeSlider.setDisable(true);
                    if(mediaPlayer.isMute()){
                        mediaPlayer.setMute(false);
                    }else{
                        mediaPlayer.setMute(true);
                    }
                    
                }
                    
        });

        
        /*
        This event handler will remove the icon of the pause icon/buttun and replave it with the play icon/button.
        It will then pause the currently selected song
        */
        playPause.next.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                songIndex++;
                try{
                    mediaPlayer.stop();
                    selectSong(songIndex);
                }catch(IndexOutOfBoundsException e){
                    
                }
            }
                    
        });
        
        playPause.last.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                songIndex--;
                
                try{
                    mediaPlayer.stop();
                    selectSong(songIndex);
                }catch(IndexOutOfBoundsException e){
                    
                }
            }
                    
        });
        
        playPause.volumeSlider.valueProperty().addListener(new InvalidationListener(){
            public void invalidated(Observable ov) {
                if (playPause.volumeSlider.isValueChanging()) {
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
    public static void main(String[] args) {
        launch(args);
    }
    

    public static void setPath(String filelocation){
        path = filelocation;
        media = new Media(new File(path).toURI().toString());
        System.out.print("New song playing");
    }
    
    //this method will check to see is the album the song belongs to in the library
    public void addSongToAlbum(Song song){
        
        if (Library.albums.containsKey(song.getAlbum())){
            
            //if the album already exists add the song to the album 
            Library.albums.get(song.getAlbum()).addSongs(song);
            System.out.println(song.getAlbum());
            //artistWidget.addSong(song, song.getAlbum(), song.getArtist());
            
        }else{
            
            //if it doesn't make new Album and add it to the library
            Album album = new Album(song.getAlbum(), song.getArtist());
            Library.albums.put(song.getAlbum(),album); 
            //add the song to the new album
            album.addSongs(song);
            //addAlbumToArtist(album);
            System.out.println(album.getTitle());
            addAlbumToArtist(album);
            Library.artists.get(album.getArtist()).artistWidget.addSong(song);
            
            ArtistWidgetHolder.addArtist(Library.artists.get(album.getArtist()).artistWidget);
            
        }
 
    }
    
    //this method will add albums to  their respective Artists
    public void addAlbumToArtist(Album album){
        
        if (Library.artists.containsKey(album.getArtist())){
                                
            Library.artists.get(album.getArtist()).addAlbums(album);
            
        }else{
           
            Artist artist = new Artist(album.getArtist());
            Library.artists.put(album.getArtist(),artist); 
            artist.addAlbums(album);
            System.out.println("New Artist " + album.getArtist());
        
        }
        
    }
    
    public static void selectSong(Song song){
        
        //playing = songs.getItems();
        
        media = new Media(new File(song.getId()).toURI().toString());
        
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaView = new MediaView(mediaPlayer);
        playPause.currentSongDisplay.clear();
        playPause.currentSongDisplay.appendText("Song: " +  song.getTitle() + "\nArtist: " + song.getArtist() + "\nAlbum: " + song.getAlbum());
        
    }
    
    public static void selectSong(int index){
            //
            Song song = Library.songs.get(index);
            //playing = songs.getItems();

            media = new Media(new File(song.getId()).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaView = new MediaView(mediaPlayer);
            playPause.currentSongDisplay.clear();
            playPause.currentSongDisplay.appendText("Song: " +  song.getTitle() + "\nArtist: " + song.getArtist() + "\nAlbum: " + song.getAlbum());

//            playSong(song);    
    }
        
    public void playSong(Song song){
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
 
                     
                catch(NullPointerException e)
                {
                    System.out.print("Error");
                }
                    MusicPlayer.selectSong(song);
                    //playPause.getChildren().remove(playPause.play);
                    Image lastIcon = new Image(getClass().getResourceAsStream("pauseIcon.png"));
                    playPause.pause.setGraphic(new ImageView(lastIcon));
                    ImageView pauseView = new ImageView(lastIcon);
                    pauseView.setFitWidth(5);
                    pauseView.setFitHeight(5);
                    playPause.add(playPause.pause,1,2);
                    
                    
                    MusicPlayer.mediaPlayer.play();
                
    }
/*
    protected void updateValues() {
    if (playTime != null && playPause.timeSlider != null && playPause.volumeSlider != null && duration != null) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Duration currentTime = mediaPlayer.getCurrentTime();
                playTime.setText(formatTime(currentTime, duration));
                playPause.timeSlider.setDisable(duration.isUnknown());
                if (!playPause.timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !playPause.timeSlider.isValueChanging()) {
                    playPause.timeSlider.setValue(currentTime.divide(duration).toMillis() * 100.0);
                    playPause.timeSlider.setValue(currentTime.dividedBy((long)duration).toMillis()*100);
                }
                if (!playPause.volumeSlider.isValueChanging()) {
                    playPause.volumeSlider.setValue((int) Math.round(mediaPlayer.getVolume() * 100));
                }
            }
        });
    }

}
*/

}