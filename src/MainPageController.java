/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Sir Elvis
 */
public class MainPageController implements Initializable {

    @FXML
    private MenuItem quitBut;
    @FXML
    private Menu logoutBut;
    @FXML
    private MenuItem editProfile;
    @FXML
    private MenuItem logOut;
    @FXML
    private Button sendBut;
    @FXML
    private Label userName;
    @FXML
    private ImageView profilePic;
    @FXML
    private TextArea message;
    @FXML
    private MenuBar menu;
    @FXML
    private AnchorPane face1;
    @FXML
    private ScrollPane face2;
    @FXML
    private AnchorPane face4;
    @FXML
    private ScrollPane face3;
    @FXML
    private Label userName1;
    @FXML
    private TextField searchfriends;
    @FXML
    private Label status;
    @FXML
    private Button searchBut;
    @FXML
    private Hyperlink friendrequests;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new File(DashBoard.currentUser.profilePic));
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            this.profilePic.setImage(image);
            userName.setText(DashBoard.currentUser.name);
            } catch (IOException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        status.setText(DashBoard.currentUser.status);
        updateFriends();
    }    

    @FXML
    private void quit(ActionEvent event) {
        Platform.exit();
    }


    @FXML
    private void logOut(ActionEvent event) {
        Stage prevStage = (Stage) sendBut.getScene().getWindow();
        prevStage.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChatFXML.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.getIcons().add(new Image("file:C:\\Users\\Sir Elvis\\OneDrive\\ChatApp\\Capture.PNG"));
                    stage.setScene(new Scene(root1, 600, 450)); 
                    stage.setTitle("ChatApp");
                    stage.show();
        } 
        catch(Exception e) {
               e.printStackTrace();  
        }
    }

    @FXML
    private void editProfilePage(ActionEvent event) {
    }

    @FXML
    private void findFriends(ActionEvent event) {
        //System.out.println("starting");
        VBox vb = new VBox();
        vb.setPrefWidth(300);
        face2.setHbarPolicy(ScrollBarPolicy.NEVER);
        for(User i : DashBoard.users.values()){
            if((i.name.contains(searchfriends.getText()) || 
                    i.username.contains(searchfriends.getText()) && 
                    !i.name.equalsIgnoreCase(DashBoard.currentUser.name) )){
                HBox hb = new HBox();
                hb.setMinWidth(face2.getMaxWidth());
                hb.setStyle("-fx-padding: 5 10 5 10; -fx-border-color: #f5f5f5; -fx-background-color: white");
                Hyperlink hl = new Hyperlink();
                hl.setText(i.name + " (" + i.username + ")");
                hl.setOnAction((ActionEvent e) -> {
                    String name;
                    name = hl.getText().substring(hl.getText().indexOf("(") + 1, hl.getText().indexOf(")"));
                            //.substring(1,hl.getText().split(" ")[1].indexOf(")"));
                    //System.out.print(name);
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Add friend");
                    alert.setHeaderText(null);
                    alert.setContentText("Do you send " + hl.getText() + " a friend request?");
                    ButtonType buttonTypeSend = new ButtonType("Send friend request");
                    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(buttonTypeSend, buttonTypeCancel);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeSend){
                        DashBoard.currentUser.sendFriendRequest(name);
                    } 
                    else{alert.close();}
                });
                hb.getChildren().add(hl);
                vb.getChildren().add(hb);
            }
        }
        face2.setContent(vb);
    }


    @FXML
    private void showAllRequests(ActionEvent event) {
        VBox vb = new VBox();
        vb.setPrefWidth(300);
        face3.setHbarPolicy(ScrollBarPolicy.NEVER);
        for(String i : DashBoard.currentUser.friendRequests){
                HBox hb = new HBox();
                hb.setMinWidth(face2.getMaxWidth());
                hb.setStyle("-fx-padding: 5 10 5 10; -fx-border-color: #f5f5f5");
                Label hl = new Label();
                User b = DashBoard.users.get(i);
                hl.setText(b.name + " (" + b.username + ")");
                Button add = new Button("Add friend");
                add.setOnAction((ActionEvent e) -> {
                    DashBoard.currentUser.confirmRequest(b.username);
                    updateFriends();
                });
                Button delete = new Button("Delete request");
                delete.setOnAction((ActionEvent e) -> {
                    DashBoard.currentUser.friendRequests.remove(i);
                });
                hb.setSpacing(10);
                hb.getChildren().addAll(hl,add,delete);
                vb.getChildren().add(hb);
            }
        
        face3.setContent(vb);
    }
    
    public void updateFriends(){
        friendrequests.setText("Friend requests (" + DashBoard.currentUser.friendRequests.size() + ")");
        VBox vb = new VBox();
        vb.setPrefWidth(300);
        for(User i : DashBoard.currentUser.friends.values()){
                HBox hb = new HBox();
                hb.setMinWidth(face2.getMaxWidth());
                hb.setStyle("-fx-padding: 5 10 5 10; -fx-border-color: #f5f5f5; -fx-background-color: white");
                Hyperlink hl = new Hyperlink();
                
                hl.setText(i.name + " (" + i.username + ")");
                hl.setOnAction((ActionEvent e) -> {
                    for(Message k : DashBoard.currentUser.all){
                        // to be done later
                    }
                      
                });
                hb.getChildren().add(hl);
                vb.getChildren().add(hb);
            }
        face2.setContent(vb);
    }
}
