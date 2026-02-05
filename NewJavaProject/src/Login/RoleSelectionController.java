
package Login;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



 
public class RoleSelectionController  {

    @FXML
    private Button User_rolebtn;
    
    @FXML
    private Button Authority_rolebtn;
    
    private Stage stage;
    
    public void UserPage(ActionEvent event) throws IOException
    {
         Parent root= FXMLLoader.load(getClass().getResource("/Login/UserSignUp.fxml"));
         Scene scene= new Scene(root);
         stage=(Stage)((Node)event.getSource()).getScene().getWindow();
         
         stage.setScene(scene);
         //stage.setMaximized(true);
         stage.show();
         
         
    }
    
    public void AuthorityPage(ActionEvent event)throws IOException
    {
        Parent root= FXMLLoader.load(getClass().getResource("/Login/AuthoritySignup.fxml"));
         Scene scene= new Scene(root);
         stage=(Stage)((Node)event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();
    }
    
    
}
