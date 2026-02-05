
package Login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Database.DBConnectionUser;


public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
         createTableUser();
       
          Parent root= FXMLLoader.load(getClass().getResource("/Login/roleSelection.fxml"));
          Scene scene= new Scene(root);
          primaryStage.setScene(scene);
         // primaryStage.setMaximized(true);
          primaryStage.setTitle("SOSync");
          
         Image icon = new Image(getClass().getResourceAsStream("/Login/images/Untitled-1.png"));
        primaryStage.getIcons().add(icon);

       // primaryStage.show();
          
         primaryStage.show();
          
        
    }
    
    public void createTableUser()
    {
         String sql="""
                    
                    CREATE TABLE IF NOT EXISTS User_Info(
                    serial INTEGER PRIMARY KEY AUTOINCREMENT,
                    User_Name TEXT,
                    Name TEXT,
                    Gender TEXT,
                    Age INTEGER,
                    Phone TEXT,
                    Nid TEXT,
                    Email TEXT,
                    Emergency_Contact TEXT,
                    Blood TEXT,
                    Allergy TEXT
                    );
                   """;
        try(Connection con=DBConnectionUser.connect();
                Statement stmt=con.createStatement()){
            
            stmt.execute(sql);
            System.out.println("table created");
            
        }
        catch(Exception e){
            
            System.out.println("Not created");
            e.printStackTrace();
            
        }
    }

   
    public static void main(String[] args) {
        launch(args);
    }
    
}
