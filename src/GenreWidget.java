package musicplayer;
import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GenreWidget extends TableView{
        JFXButton genreWidget = new JFXButton("");
    //@Override
        
    public GenreWidget(){
        TilePane pane = new TilePane();
        
        //Header
        Text txtHeader = new Text("Music Genre TableView");
        txtHeader.setFont(Font.font(20));
        txtHeader.setFill(Color.GREEN);
        
        //TableView + ObservableList
        TableView<MusicGenre> teamTable = new TableView<>();
        ObservableList<MusicGenre> teams = FXCollections.observableArrayList(
                    new MusicGenre("Artist", "Category", "Rap", "www.fcbarcelona.es"),
                    new MusicGenre("Artist", "Category", "Hip-Hop", "www.juventus.com/"),
                    new MusicGenre("Artist", "Category", "Country", "www.fcbayern.de"),
                    new MusicGenre("Artist", "Category", "Jazz", "www.manutd.com"),
                    new MusicGenre("Artist", "Category", "Pop", "www.psg.fr")
        );
        
        teamTable.setItems(teams);
        teamTable.setMaxSize(500, 600);
        teamTable.setMinSize(250, 300);
        
        //Set TableColumns
        TableColumn colClubInfo = new TableColumn("Club Information");
        
        TableColumn<MusicGenre, String> colName = new TableColumn<>("Team Name");
        colName.setCellValueFactory(new PropertyValueFactory<MusicGenre, String>("name"));
        colName.setMinWidth(teamTable.getMaxWidth()/4);
        
        
        TableColumn<MusicGenre, String> colCategory= new TableColumn<>("Category");
        colCategory.setCellValueFactory(new PropertyValueFactory<MusicGenre, String>("category"));
        colCategory.setMinWidth(teamTable.getMaxWidth()/4);
        
        TableColumn<MusicGenre, String> colGenre = new TableColumn<>("Genre");
        colGenre.setCellValueFactory(new PropertyValueFactory<MusicGenre, String>("Genre"));
        colGenre.setMinWidth(teamTable.getMaxWidth()/4);
        
        TableColumn<MusicGenre, String> colWeb = new TableColumn<>("Web Site");
        colWeb.setCellValueFactory(new PropertyValueFactory<MusicGenre, String>("webSite"));
        colWeb.setMinWidth(teamTable.getMaxWidth()/4);
        
        
        //Asign Columns to TableView
        colClubInfo.getColumns().addAll(colName, colCategory, colGenre);
        teamTable.getColumns().addAll(colClubInfo, colWeb);
        
        //Nodes
        Text txtName = new Text("Name");
        Text txtCategory = new Text("Category");
        Text txtGenre = new Text("Genre");
        Text txtWeb = new Text("WebSite");
        Text txtNotification = new Text("Notifications");
        txtNotification.setFont(Font.font(20));
        txtNotification.setFill(Color.BLUE);
        
        TextField fldName = new TextField();
        TextField fldCategory = new TextField();
        TextField fldGenre = new TextField();
        TextField fldWeb = new TextField();
        
        Button btnAdd = new Button("Add");
        btnAdd.setMinWidth(85);
        
        //Add Information to TableView
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
               if(fldName.getText().isEmpty() || fldCategory.getText().isEmpty() || fldGenre.getText().isEmpty() ||
                       fldWeb.getText().isEmpty()){
                   txtNotification.setText("Please Add information to all the fields");
               }else{
                   teams.add(new MusicGenre(
                                fldName.getText(),
                                fldCategory.getText(),
                                fldGenre.getText(),
                                fldWeb.getText()));
                   
                   }
               
                   
            }
        });
        
        Button btnReplace = new Button("Replace");
        btnReplace.setMinWidth(85);
        
        //Replace Information in TableView
        btnReplace.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                
               if(teamTable.getSelectionModel().isEmpty() || 
                       (fldName.getText().isEmpty() && fldCategory.getText().isEmpty() && fldGenre.getText().isEmpty() &&
                       fldWeb.getText().isEmpty())){
                   
                   txtNotification.setText("Please Add information to all the fields and Select an Item in the Table");
                   
               }else{
                    String strName = teams.get(teamTable.getSelectionModel().getSelectedIndex()).getName();
                    String strCategory = teams.get(teamTable.getSelectionModel().getSelectedIndex()).getCategory();
                    String strGenre= teams.get(teamTable.getSelectionModel().getSelectedIndex()).getGenre();
                    String strWeb = teams.get(teamTable.getSelectionModel().getSelectedIndex()).getWebSite();
                    if(!fldName.getText().isEmpty()){
                        strName = fldName.getText();
                    }
                    if(!fldCategory.getText().isEmpty()){
                        strCategory = fldCategory.getText();
                    }
                    if(!fldGenre.getText().isEmpty()){
                        strGenre = fldGenre.getText();
                    }
                    if(!fldWeb.getText().isEmpty()){
                        strWeb = fldWeb.getText();
                        
                    }
                    
                    teams.set(teamTable.getSelectionModel().getSelectedIndex(), 
                                new MusicGenre(strName, strCategory, strGenre, strWeb));
               }
            }
        });
        
        Button btnRemove = new Button("Remove");
        btnRemove.setMinWidth(85);
        
        //Remove Row from TableView
        btnRemove.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if(teamTable.getSelectionModel().isEmpty()){
                    txtNotification.setText("Please Select an Item from the List");
                }else{
                    teams.remove(teamTable.getSelectionModel().getSelectedItem());
                }
                
            }
        });
        
        //Adding Change Listener to TableView
        teams.addListener(new ListChangeListener() {
 
            @Override
            public void onChanged(ListChangeListener.Change change) {
                
                txtNotification.setText("TableView Successfully changed");
            }
        });
        
        //Layouts
        GridPane center = new GridPane();
        center.setHgap(10);
        center.setVgap(10);
        center.setPadding(new Insets(50));
        
        center.add(txtHeader, 0, 0, 5, 1);
        GridPane.setHalignment(txtHeader, HPos.CENTER);
        center.add(teamTable, 0, 1, 5, 5);
        
        center.add(txtName, 0, 7);
        center.add(txtCategory, 1, 7);
        center.add(txtGenre, 2, 7);
        center.add(txtWeb, 3, 7);
        
        center.add(fldName, 0, 8);
        center.add(fldCategory, 1, 8);
        center.add(fldGenre, 2, 8);
        center.add(fldWeb, 3, 8);
        
        VBox right = new VBox(15);
        right.setAlignment(Pos.CENTER);
        right.setPadding(new Insets(5, 100,5,5));
        
        right.getChildren().addAll(btnAdd, btnReplace, btnRemove);
        
        BorderPane root = new BorderPane();
        
        root.setCenter(center);
        root.setRight(right);
        root.setBottom(txtNotification);
        
        Scene scene = new Scene(root, 800, 500);
        
        //primaryStage.setTitle("JavaFX 8 - TableView");
        //primaryStage.setScene(scene);
        //primaryStage.show();

    }
    
    
    //Class example
    public static class MusicGenre{
        
        private final SimpleStringProperty name;
        private final SimpleStringProperty category;
        private final SimpleStringProperty Genre;
        private final SimpleStringProperty webSite;
        
        private MusicGenre(String strName, String strCategory, String strGenre,String strWeb) {
            this.name = new SimpleStringProperty(strName);
            this.category = new SimpleStringProperty(strCategory);
            this.Genre = new SimpleStringProperty(strGenre);
            this.webSite = new SimpleStringProperty(strWeb);
            
        }

        public String getName() {
            return name.get();
        }

        public String getCategory() {
            return category.get();
        }
        
        public String getGenre() {
            return Genre.get();
        }

        public String getWebSite() {
            return webSite.get();
        }
        
        public void setName(String strName) {
           name.set(strName);
        }

        public void setCategory(String strCategory) {
            category.set(strCategory);
        }
        
        public void setGenre(String strGenre) {
            Genre.set(strGenre);
        }

        public void setWebSite(String strWeb) {
            webSite.set(strWeb);

        }
    }
}
    

    //public static void main(String[] args) {
        //(args);
    
    
