
package Login;

import Database.DBConnectionUser;
import Database.InsertUserData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import SignUpInfo.UserSignupInfo;
import java.sql.Connection;
import java.awt.Toolkit;
import javafx.scene.control.Alert;


public class UserSignupController implements Initializable {

  private Stage stage;
  private Scene scene;
  
  @FXML
  private ComboBox<String> Addressbox;
  
  ObservableList<String> list=FXCollections.observableArrayList("Barishal", "Bogra", "Brahmanbaria", "Chandpur", "Chattogram", "Comilla", "Cox's Bazar", "Dhaka", "Dinajpur", "Faridpur", "Feni", "Gazipur", "Gopalganj", "Habiganj", "Jamalpur", "Jessore", "Jhalokathi", "Jhenaidah", "Joypurhat", "Khagrachhari", "Khulna", "Kishoreganj", "Kurigram", "Kushtia", "Lakshmipur", "Madaripur", "Magura", "Manikganj", "Meherpur", "Moulvibazar", "Munshiganj", "Mymensingh", "Naogaon", "Narail", "Narsingdi", "Natore", "Netrokona", "Nilphamari", "Noakhali", "Pabna", "Panchagarh", "Patuakhali", "Pirojpur", "Rajbari", "Rajshahi", "Rangamati", "Rangpur", "Satkhira", "Shariatpur", "Sherpur", "Sirajganj", "Sunamganj", "Sylhet", "Tangail", "Thakurgaon");
  
  @FXML
  private TextField UserName;
  @FXML
  private TextField Name;
  @FXML
  private TextField Gender;
  @FXML
  private TextField Age;
  @FXML
  private TextField PhoneNum;
  @FXML
  private TextField Nid;
  @FXML
  private TextField Email;
  @FXML
  private TextField EmgContact;
  @FXML
  private TextField Blood;
  @FXML
  private TextField Allergy;
  
  private String Address;
  
          
  
  
  
  
  public void LoginPageLink(ActionEvent event)throws IOException
  {
      Parent root = FXMLLoader.load(getClass().getResource("/Login/UserLogin.fxml"));
      scene=new Scene(root);
      stage=(Stage)((Node)event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
      
  }
  
  public void EmergencyPageLink(ActionEvent event)throws IOException{
      
      String userName= UserName.getText();
      String name= Name.getText();
      String gender= Gender.getText();
      String age= Age.getText();
      String phnNum= PhoneNum.getText();
      String nid= Nid.getText();
      String email= Email.getText();
      String emgContact= EmgContact.getText();
      String blood= Blood.getText();
      String allergy= Allergy.getText();
      
      if(userName.isEmpty()||name.isEmpty()||gender.isEmpty()||age.isEmpty()||phnNum.isEmpty()||nid.isEmpty()||email.isEmpty()||emgContact.isEmpty()||blood.isEmpty()||allergy.isEmpty())
      {
          showError("Incomplete Form","Missing Information","Please fill all fields before signing up.");
          return;
          
      }
      boolean unique=CollectInfo(userName,name,gender,age,phnNum,nid,email,emgContact,blood,allergy);
      if(!unique)
       {
           return;
       }
      
      
      
      
      
      
      Parent root = FXMLLoader.load(getClass().getResource("/User/EmergencyPage.fxml"));
      scene=new Scene(root);
      stage=(Stage)((Node)event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
  }
  
   private void showError(String message1,String message2,String message3)
    {
         Toolkit.getDefaultToolkit().beep();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(message1);
        alert.setHeaderText(message2);
        alert.setContentText(message3);
        alert.showAndWait();
        
    }
  
  public void initialize(URL location, ResourceBundle resources)
  {
      Addressbox.setItems(list);
      
      UserName.textProperty().addListener((obs, oldText, newText) -> clearStyles());
      Email.textProperty().addListener((obs, oldText, newText) -> clearStyles());
      PhoneNum.textProperty().addListener((obs, oldText, newText) -> clearStyles());
      Nid.textProperty().addListener((obs, oldText, newText) -> clearStyles());

  }
  
  public void AddressInfo(ActionEvent event)
  {
      Address=Addressbox.getValue();
  }
  
  public boolean CollectInfo(String userName,String name,String gender,String age,String phnNum,String nid,String email,String emgContact,String blood,String allergy)
  {
     
      
      
      UserSignupInfo usi= new UserSignupInfo(userName,name,gender,age,phnNum,nid,email,emgContact,Address,blood,allergy);
      
      Connection con= new DBConnectionUser().connect();
      InsertUserData Insert= new InsertUserData(con,usi);
      
      String success=Insert.insert();
        if(success.equals("SUCCESS"))
        {
            return true;
        }
        else
        {
             handleDuplicate(success);
             return false;
        }
      
      //usi.showInfo();   
      
      
  }
  
  private void handleDuplicate(String message) {

    clearStyles(); // reset previous red borders

    if (message.contains("username")) {
        markError(UserName);
    }
    else if (message.contains("email")) {
        markError(Email);
    }
    else if (message.contains("phone_num")) {
        markError(PhoneNum);
    }
    else if (message.contains("nid")) {
        markError(Nid);
    }

    showError("Duplicate Entry",
            "Already Exists",
            "This information already exists. Please use different value.");
    }
  
    private void markError(TextField field) {
    field.setStyle("-fx-border-color: red; -fx-border-width: 2;");
    }
    
    private void clearStyles() {
    UserName.setStyle("-fx-background-color: transparent; -fx-border-color: FDFAF6; -fx-border-width: 0px 0px 2px 0px; -fx-text-fill:white;");
    Email.setStyle("-fx-background-color: transparent; -fx-border-color: FDFAF6; -fx-border-width: 0px 0px 2px 0px; -fx-text-fill:white;");
    PhoneNum.setStyle("-fx-background-color: transparent; -fx-border-color: FDFAF6; -fx-border-width: 0px 0px 2px 0px; -fx-text-fill:white;");
    Nid.setStyle("-fx-background-color: transparent; -fx-border-color: FDFAF6; -fx-border-width: 0px 0px 2px 0px; -fx-text-fill:white;");
    }
  


       
    
}