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
public class ForgotPasswordController implements Initializable {

    @FXML
    private Button resendPasswordBut;
    @FXML
    private Hyperlink backBut;
    @FXML
    private Label status;
    @FXML
    private ImageView boximage;
    @FXML
    private TextField email;
    @FXML
    private TextField username;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            BufferedImage bufferedImage;
            bufferedImage = ImageIO.read(new File("C:\\Users\\Sir Elvis\\OneDrive\\ChatApp\\Capture.PNG"));
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            this.boximage.setImage(image);
        }
        catch(Exception e){};
    }    

    @FXML
    private void resendPassword(ActionEvent event) {
        if(DashBoard.users.containsKey(username.getText()) && DashBoard.users.get(username.getText()).email.equalsIgnoreCase(email.getText())){
            String name = DashBoard.users.get(username.getText()).name;
            String pass = DashBoard.users.get(username.getText()).password;
            SendEmail.sendMessage(email.getText(), name, pass);
            status.setText("Password sent");
        }
        else
            status.setText("Password not sent!");
    }

    @FXML
    private void loginPage(ActionEvent event) {
        Stage prevStage = (Stage) backBut.getScene().getWindow();
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
    
}
