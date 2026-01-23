/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package Login;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Wellcome
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       
          Parent root= FXMLLoader.load(getClass().getResource("/Login/roleSelection.fxml"));
          Scene scene= new Scene(root);
          primaryStage.setScene(scene);
          primaryStage.setTitle("SOSync");
          
         Image icon = new Image(getClass().getResourceAsStream("/Login/images/Untitled-1.png"));
        primaryStage.getIcons().add(icon);

        primaryStage.show();
          
         primaryStage.show();
          
        
    }

   
    public static void main(String[] args) {
        launch(args);
    }
    
}
