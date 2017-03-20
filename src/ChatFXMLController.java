/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Sir Elvis
 */
public class ChatFXMLController implements Initializable {

    @FXML
    private Button loginBut;
    @FXML
    private Hyperlink signUpBut;
    @FXML
    private Hyperlink forgotPassword;
    //private String path;
    @FXML
    private ImageView boximage;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        try{
            BufferedImage bufferedImage;
            bufferedImage = ImageIO.read(new File("C:\\Users\\Sir Elvis\\OneDrive\\ChatApp\\Capture.PNG"));
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            this.boximage.setImage(image);
        }
        catch(Exception e){}
    }    

    @FXML
    private void logIn(ActionEvent event) {
        if(User.logIn(username.getText(), password.getText())){
            Stage prevStage = (Stage) loginBut.getScene().getWindow();
            prevStage.close();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.getIcons().add(new Image("file:C:\\Users\\Sir Elvis\\OneDrive\\ChatApp\\Capture.PNG"));
                    
                        stage.setTitle(DashBoard.currentUser.name);
                        stage.setScene(new Scene(root1, 600, 450));  
                        stage.show();
            } 
            catch(Exception e) {
                   e.printStackTrace();
            }
        }
        
        else{
            status.setText("Wrong username and password combination!");
        }
    
    }

    @FXML
    private void signUpPage(ActionEvent event) {
        Stage prevStage = (Stage) signUpBut.getScene().getWindow();
        prevStage.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1, 600, 550)); 
                    stage.getIcons().add(new Image("file:C:\\Users\\Sir Elvis\\OneDrive\\ChatApp\\Capture.PNG"));
                    
                    stage.setTitle("Sign Up");
                    stage.show();
        } 
        catch(Exception e) {
               e.printStackTrace();
        }
    }

    @FXML
    private void forgotPasswordPage(ActionEvent event) {
        Stage prevStage = (Stage) forgotPassword.getScene().getWindow();
        prevStage.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ForgotPassword.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Reset Password");
                    stage.getIcons().add(new Image("file:C:\\Users\\Sir Elvis\\OneDrive\\ChatApp\\Capture.PNG"));
                    stage.setScene(new Scene(root1, 600, 450));  
                    stage.show();
        } 
        catch(Exception e) {
               e.printStackTrace();
        }
    }
    
}
