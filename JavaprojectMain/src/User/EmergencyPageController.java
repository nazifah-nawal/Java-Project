/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package User;

import Emergency.Accident;
import Emergency.Crime;
import Emergency.Earthquake;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import Emergency.Fire;
import javafx.fxml.FXML;




public class EmergencyPageController  {

    private Stage stage;
    public void LoadTodoPage(ActionEvent event,String s1,String s2 ) throws IOException
    {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/User/TodoPage.fxml"));
         Parent root=loader.load();
         
         TodoPageController controller= loader.getController();
         controller.setTextT(s1);
         controller.setTextN(s2);
         
         
         Scene scene= new Scene(root);
         stage=(Stage)((Node)event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();
    }
    
    @FXML
    public void FireButtonClicked(ActionEvent event) throws IOException{
        
        Fire ob=new Fire();
        
        LoadTodoPage(event,ob.to_do(),ob.not_to_do());
    }
    
    @FXML
    public void AccidentButtonClicked(ActionEvent event) throws IOException{
        
        Accident ob=new Accident();
        
        LoadTodoPage(event,ob.to_do(),ob.not_to_do());
    }
    
    @FXML
    public void EarthquakeButtonClicked(ActionEvent event) throws IOException{
        
        Earthquake ob=new Earthquake();
        
        LoadTodoPage(event,ob.to_do(),ob.not_to_do());
    }
    
     @FXML
    public void CrimeButtonClicked(ActionEvent event) throws IOException{
        
        Crime ob=new Crime();
        
        LoadTodoPage(event,ob.to_do(),ob.not_to_do());
    }
    
}
