
package Login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import SignUpInfo.AuthoritySignupInfo;

public class AuthoritySignupController implements Initializable {
    
    @FXML
    private ComboBox<String> InstitutionBox;
    
    @FXML
    private ComboBox<String> AddressBox;
    
    @FXML
    private TextField InstitutionName;
    @FXML
    private TextField Contact;
    @FXML
    private TextField Email;
    @FXML
    private TextField Address;     
    
    private String InstitutionType;
    private String Area;
    
            
    
    ObservableList<String> list_inst= FXCollections.observableArrayList("Hospital","Police Station","Fire Service");
    ObservableList<String> list_address= FXCollections.observableArrayList("Barishal", "Bogra", "Brahmanbaria", "Chandpur", "Chattogram", "Comilla", "Cox's Bazar", "Dhaka", "Dinajpur", "Faridpur", "Feni", "Gazipur", "Gopalganj", "Habiganj", "Jamalpur", "Jessore", "Jhalokathi", "Jhenaidah", "Joypurhat", "Khagrachhari", "Khulna", "Kishoreganj", "Kurigram", "Kushtia", "Lakshmipur", "Madaripur", "Magura", "Manikganj", "Meherpur", "Moulvibazar", "Munshiganj", "Mymensingh", "Naogaon", "Narail", "Narsingdi", "Natore", "Netrokona", "Nilphamari", "Noakhali", "Pabna", "Panchagarh", "Patuakhali", "Pirojpur", "Rajbari", "Rajshahi", "Rangamati", "Rangpur", "Satkhira", "Shariatpur", "Sherpur", "Sirajganj", "Sunamganj", "Sylhet", "Tangail", "Thakurgaon");
    
    
    public void initialize(URL location, ResourceBundle resources)
    {
        InstitutionBox.setItems(list_inst);
        AddressBox.setItems(list_address);
        
    }
    
    public void ComboBoxInfo(ActionEvent event)
    {
        InstitutionType=InstitutionBox.getValue();
        
        Area=AddressBox.getValue();
    }
    
    public void CollectInfo()
    {
        
        String InstName=InstitutionName.getText();
        String contact=Contact.getText();
        String email=Email.getText();
        String address=Address.getText();
        
        AuthoritySignupInfo asi= new AuthoritySignupInfo(InstitutionType,InstName,contact,email,address,Area);
        asi.showInfo();
        
    }
    
}
