
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
        
   // static HashMap<String,Album> albums =new HashMap<String,Album>();
    MediaPlayer mediaPlayer;
    //private Label time;
    //Duration duration;
    //Button fullScreenButton;
    //Scene scene;
    Media media;
    double width;
    double height;
    MediaView mediaView;

    private PlayPauseWidget playPause;
    public void start(Stage primaryStage) {
        MediaPlayer mediaPlayer;
        //private Label time;
        Duration duration;
        Button fullScreenButton;
        //Scene scene;
        Media media;
        double width;
        double height;
        MediaView mediaView;
        
        String path = "C:\\Users\\jndemera2\\Documents\\NetBeansProjects\\MusicPlayer\\src\\musicplayer\\test.mp3";

        media = new Media(new File(path).toURI().toString());

        mediaPlayer = new MediaPlayer(media);
        //AutoPlay set to false
        mediaPlayer.setAutoPlay(false);
        mediaView = new MediaView(mediaPlayer);
        
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
                case 3: tab.setContent(new SongWidget());
                        break;
                case 4: tab.setContent(new PlayPauseWidget());
                        break; 
                
            }
    

            hbox.setAlignment(Pos.CENTER);
            tabPane.getTabs().add(tab);
        }
        

        playPause.importFiles.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent arg0) {
                    FileChooser fileChooser = new FileChooser();
                    //fileChooser.setMultiSelectionEnabled(true);
                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("mp3 files (*.mp3)", "*.mp3");
                    fileChooser.getExtensionFilters().add(extFilter);
/*
                    File file = fileChooser.showOpenDialog(primaryStage);
                    
                    
                    if(file != null){
                        String fileString = file.toString();

                        fileString = fileString.replace("\\", "\\\\");
                    
                        Song song = new Song(fileString);
                        
                        try{
                            Library.addSong(song);
                        }catch(NullPointerException haha){
                            System.out.print("Error!!!");
                        }
                        
                        
                        System.out.println(file);
*/

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
//                            if (Library.albums.containsKey(song.getAlbum())){
//                                
//                                Library.albums.put(song.getAlbum(),"yes");
//                                Album album = new Album(song.getAlbum(), song.getArtist());
//                                album.addSongs(song);
//                            }else{
//                               // album.addSongs(song);
//                            }
//                            Album album;
//                            if (albums.containsKey(song.getAlbum())){
                                
//                                Library.albums.put(song.getAlbum(),"yes");
//                                Album album = new Album(song.getAlbum(), song.getArtist());
//                                    albums.get();
//                                String str = song.getAlbum();
//                                Album album; 
//                                albums.get();
//                                albums.get(album);
//                                album.addSongs(song);
//                            }else{
//                                Album album = new Album(song.getAlbum(), song.getArtist());
//                               albums.put(song.getAlbum(),album); 
//                               album.addSongs(song);
//                            }
                            
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
}