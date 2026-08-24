/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package User;

import Database.DBConnectionUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.*;
import javafx.fxml.Initializable;

public class EditProfileController implements Initializable {

    @FXML
    private ComboBox<String> Addressbox;

    ObservableList<String> list = FXCollections.observableArrayList("Barishal", "Bogra", "Brahmanbaria", "Chandpur", "Chattogram", "Comilla", "Cox's Bazar", "Dhaka", "Dinajpur", "Faridpur", "Feni", "Gazipur", "Gopalganj", "Habiganj", "Jamalpur", "Jessore", "Jhalokathi", "Jhenaidah", "Joypurhat", "Khagrachhari", "Khulna", "Kishoreganj", "Kurigram", "Kushtia", "Lakshmipur", "Madaripur", "Magura", "Manikganj", "Meherpur", "Moulvibazar", "Munshiganj", "Mymensingh", "Naogaon", "Narail", "Narsingdi", "Natore", "Netrokona", "Nilphamari", "Noakhali", "Pabna", "Panchagarh", "Patuakhali", "Pirojpur", "Rajbari", "Rajshahi", "Rangamati", "Rangpur", "Satkhira", "Shariatpur", "Sherpur", "Sirajganj", "Sunamganj", "Sylhet", "Tangail", "Thakurgaon");

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

    private String username;
    private UserRequests currentUser;

    public void initialize(URL location, ResourceBundle resources) {
        Addressbox.setItems(list);

    }

    @FXML
    public void updateProfile(ActionEvent event) {
        
        System.out.println("button clicked");
        if (currentUser == null) {
            System.out.println("No user is logged in!");
            return;
        }

        // Get new values from fields
        String newUsername = UserName.getText().trim();
        String newName = Name.getText().trim();
        String newGender = Gender.getText().trim();
        String newAge = Age.getText().trim();
        String newPhone = PhoneNum.getText().trim();
        String newNid = Nid.getText().trim();
        String newEmail = Email.getText().trim();
        String newEmgContact = EmgContact.getText().trim();
        String newBlood = Blood.getText().trim();
        String newAllergy = Allergy.getText().trim();
        String newAddress = Addressbox.getValue(); // combo box

        try {
            // Connect to DB
            Connection con = new DBConnectionUser().connect();

            // SQL Update
            String query = "UPDATE user_info SET username=?, name=?, gender=?, age=?, phone_num=?, nid=?, email=?, emergency_contact=?, blood_group=?, allergy=?, address=? WHERE username=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, newUsername);
            ps.setString(2, newName);
            ps.setString(3, newGender);
            ps.setString(4, newAge);
            ps.setString(5, newPhone);
            ps.setString(6, newNid);
            ps.setString(7, newEmail);
            ps.setString(8, newEmgContact);
            ps.setString(9, newBlood);
            ps.setString(10, newAllergy);
            ps.setString(11, newAddress);
            ps.setString(12, currentUser.getUsername()); // WHERE clause

            int updated = ps.executeUpdate();

            if (updated > 0) {
                System.out.println("Profile updated successfully!");

                // Optional: update currentUser object
            } else {
                System.out.println("Update failed!");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setName(String u) {
        this.username = u;
    }

    public void setUser(UserRequests e) {
        this.currentUser = e;
    }

    @FXML
    public void GoBack(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EmergencyPage.fxml"));
        Parent root = loader.load();

        EmergencyPageController controller = loader.getController();

        // pass user again
        controller.setUser(currentUser);
        controller.setlabel(currentUser.getName());
        controller.setStatus("Please push a button according to your Emergency.\n Keep calm, help will arrive soon!");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    private void hoverEnter(MouseEvent event) {
        Button btn = (Button) event.getSource();
        btn.setStyle("-fx-background-color: rgba(255,255,255,0.7); -fx-background-radius: 15; -fx-border-color: white; -fx-border-radius: 15;");
    }

    @FXML
    private void hoverExit(MouseEvent event) {
        Button btn = (Button) event.getSource();
        btn.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-background-radius: 15; -fx-border-color: white; -fx-border-radius: 15;");
    }
}
