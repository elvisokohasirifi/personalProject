/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Sir Elvis
 */
public class ChatMain extends Application {
    protected Stage another;
    @Override
    public void start(Stage primaryStage) {
        try{
            DashBoard.load();
            Parent root = FXMLLoader.load(getClass().getResource("ChatFXML.fxml"));

            Scene scene = new Scene(root, 600, 450);
            primaryStage.getIcons().add(new Image("file:C:\\Users\\Sir Elvis\\OneDrive\\ChatApp\\Capture.PNG"));
            primaryStage.setTitle("ChatApp");
            primaryStage.setScene(scene);
            primaryStage.show(); 
        }
        catch(IOException e){}
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
