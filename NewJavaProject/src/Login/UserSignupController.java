
package Login;

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
  
  public void initialize(URL location, ResourceBundle resources)
  {
      Addressbox.setItems(list);
  }
  
  public void AddressInfo(ActionEvent event)
  {
      Address=Addressbox.getValue();
  }
  
  public void CollectInfo()
  {
      
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
      
      
      UserSignupInfo usi= new UserSignupInfo(userName,name,gender,age,phnNum,nid,email,emgContact,Address,blood,allergy);
      
      
      //usi.showInfo();
      
      
     
      
      
  }
       
    
}
