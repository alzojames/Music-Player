
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
import java.util.HashMap;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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
    static PlayPauseWidget playPause;
    SongWidget songWidget;
    
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
        
        
        Tab tab;
        for (int i = 0; i < 5; i++) {
            
            tab = new Tab();
            tab.setText(tabName[i]);
            HBox hbox = new HBox();
            
            //Call the Playlist Artist... Widget classes
            switch (i){
                case 0: tab.setContent(new AlbumArt());
                        break;
                case 1: tab.setContent(new PlayPauseWidget());
                        break;
                case 2: tab.setContent(new PlayPauseWidget());
                        break;
                case 3: tab.setContent(songWidget = new SongWidget());
                        break;
                case 4: tab.setContent(new PlayPauseWidget());
                        break; 
                
            }
    

            hbox.setAlignment(Pos.CENTER);
            tabPane.getTabs().add(tab);
        }
        

        playPause.volumeSlider.valueProperty().addListener(new InvalidationListener(){
            public void invalidated(Observable ov) {
                if (playPause.volumeSlider.isValueChanging()) {
                    mediaPlayer.setVolume(playPause.volumeSlider.getValue() / 100.0);
                }
            }
        });
        
        playPause.timeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (playPause.timeSlider.isValueChanging()) {
                    // multiply duration by percentage calculated by slider position
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
                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("mp3 files (*.mp3)", "*.mp3");
                    fileChooser.getExtensionFilters().add(extFilter);


                     List<File> list =
                        fileChooser.showOpenMultipleDialog(primaryStage);
                     Song song;
                    if (list != null) {
                        for (File file : list) {
                            //openFile(file);
                            String fileString = file.toString();
                            fileString = fileString.replace("\\", "\\\\");
                            song = new Song(fileString);
                            try{
                            Library.addSong(song);
                            }catch(NullPointerException haha){
                                System.out.print("Error!!!");
                            }
                            songWidget.addSong(song);
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
        

       
        
        /*
        This event handler will remove the icon of the pause icon/buttun and replave it with the play icon/button.
        It will then pause the currently selected song
        */
        playPause.next.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
//                int i = 0;
//                while(!Library.songs.isEmpty()){
//                    System.out.print(Library.songs.get(i).getTitle());
//                }

                    System.out.println(Library.songs.get(0).getTitle() + " " + Library.songs.get(0).getArtist());
                    System.out.println(Library.songs.get(1).getTitle() + " " + Library.songs.get(1).getArtist());
                    System.out.println(Library.songs.get(2).getTitle() + " " + Library.songs.get(2).getArtist());
            }
                    
        });
        
        /*
        playPause.volume.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                
            }
                    
        });
        */
        
        
        
        
        
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
    
    public void addSongToAlbum(Song song){
        if (Library.albums.containsKey(song.getAlbum())){
                                
            Library.albums.get(song.getAlbum()).addSongs(song);
            //Album a = Library.albums.get.(song.getAlbum());
            //System.out.println(album.getTitle());
            }else{
                Album album = new Album(song.getAlbum(), song.getArtist());
                Library.albums.put(song.getAlbum(),album); 
                album.addSongs(song);
                System.out.println(album.getTitle());
            }
    }
//    public void addSongToArtist(Song song){
//        if (Library.artists.containsKey(song.getArtist())){
//                                
//            Library.albums.get(song.getAlbum()).addSongs(song);
//            //Album a = Library.albums.get.(song.getAlbum());
//            //System.out.println(album.getTitle());
//            }else{
//                Artist album = new Artist(song.getAlbum().song.getArtist());
//                Library.artists.put(song.getAlbum(),album); 
//                Artist.addSongs(song);
//                System.out.println(album.getTitle());
//            }
//    }
    
    public static void selectSong(String path){
        media = new Media(new File(path).toURI().toString());

        mediaPlayer = new MediaPlayer(media);
        //AutoPlay set to false
        mediaPlayer.setAutoPlay(false);
        mediaView = new MediaView(mediaPlayer);
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