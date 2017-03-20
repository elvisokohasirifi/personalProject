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
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Sir Elvis
 */
public class SignUpController implements Initializable {

    static String path = null;
    @FXML
    private TextField fullname;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirm;
    @FXML
    private Button signupBut;
    @FXML
    private Button backBut;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private ToggleGroup gender;
    @FXML
    private Label status;
    @FXML
    private TextField email;
    @FXML
    private Hyperlink profilePic;
    @FXML
    private ImageView picArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void signUp(ActionEvent event) {
        if(fullname.getText().equals("") ||
           username.getText().equals("") ||
           password.getText().equals("") ||
           confirm.getText().equals("") || 
           email.getText().equals("")){
        status.setText("Please fill in all values");
        }
        
        else if(!password.getText().equals(confirm.getText()))
            status.setText("Passwords are not the same!");
        
        else{
            String g;
            if(male.isSelected())
                g = "Male";
            else
                g = "Female";
            
            Boolean c = User.signUp(fullname.getText(), password.getText(),username.getText(),email.getText(),g, path);
            if(c)
                status.setText("Sign up successful");
            else
                status.setText("Sign up failed. Username already exists");
        }
    }

    @FXML
    private void back(ActionEvent event) {
        Stage prevStage = (Stage) backBut.getScene().getWindow();
        prevStage.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChatFXML.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1, 600, 450)); 
                    stage.getIcons().add(new Image("file:C:\\Users\\Sir Elvis\\OneDrive\\ChatApp\\Capture.PNG"));
                    
                    stage.setTitle("ChatApp");
                    stage.show();
        } 
        catch(Exception e) {
               e.printStackTrace();
        }
    }

    @FXML
    private void choosePic(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose profile picture");
        File file = fc.showOpenDialog(stage);
        if(file != null && (file.getName().endsWith("jpg") || file.getName().endsWith("jpeg") || file.getName().endsWith("png"))){
            
            path = file.getCanonicalPath().replace("\\", "/");
            BufferedImage bufferedImage;
            bufferedImage = ImageIO.read(new File(path));
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            this.picArea.setImage(image);
            //System.out.println(file.getCanonicalPath());
        }
    }
    
}
