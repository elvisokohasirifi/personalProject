/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
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
        friendrequests.setText(friendrequests.getText() + " (" + DashBoard.currentUser.friendRequests.size() + ")");
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
    private void logOut(Event event) {
        Stage prevStage = (Stage) sendBut.getScene().getWindow();
        prevStage.close();
        logOut((ActionEvent) event);
    }

    @FXML
    private void findFriends(ActionEvent event) {
        System.out.print("starting");
        VBox vb = new VBox();
        vb.setPrefWidth(300);
        face2.setHbarPolicy(ScrollBarPolicy.NEVER);
        for(User i : DashBoard.users.values()){
            if(i.name.contains(searchfriends.getText()) || i.username.contains(searchfriends.getText())){
                HBox hb = new HBox();
                hb.setMinWidth(face2.getMaxWidth());
                hb.setStyle("-fx-padding: 5 10 5 10; -fx-border-color: #f5f5f5; -fx-background-color: white");
                Hyperlink hl = new Hyperlink();
                hl.setText(i.name + " (" + i.username + ")");
                hl.setOnAction((ActionEvent e) -> {
                    //to be done later
                });
                hb.getChildren().add(hl);
                vb.getChildren().add(hb);
            }
        }
        face2.setContent(vb);
    }

    @FXML
    private void findFriends(InputMethodEvent event) {
    }

    @FXML
    private void showAllRequests(ActionEvent event) {
    }
}
