
package Login;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class UserSignupController {

  private Stage stage;
  private Scene scene;
  
  public void LoginPageLink(ActionEvent event)throws IOException
  {
      Parent root = FXMLLoader.load(getClass().getResource("/Login/UserLogin.fxml"));
      scene=new Scene(root);
      stage=(Stage)((Node)event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
      
  }
       
    
}
