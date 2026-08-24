/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package User;

import Database.DBConnectionUser;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class ViewProfileController  {

    @FXML
    private Label nameLabel;
    
    @FXML
    private Label ageLabel;
    
    @FXML
    private Label genderLabel;
    
    @FXML
    private Label phoneLabel;
    
    @FXML
    private Label EmgCLabel;
    
    @FXML
    private Label bloodLabel;
    
    @FXML
    private Label allergyLabel;
    
    @FXML
    private Button closeButton;
    
    private String username;
    private UserRequests currentUser;
    
//    @Override
//    public void initialize(URL location, ResourceBundle resources){
//        loadUserData();
//    }
    
    public void loadUserData() {

    try {
        DBConnectionUser db = new DBConnectionUser();
        Connection con = db.connect();

        String query = "SELECT * FROM user_info WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            // Example labels — change according to your FXML
            nameLabel.setText(rs.getString("name"));
            phoneLabel.setText(rs.getString("phone_num"));
            ageLabel.setText(rs.getString("age"));
            genderLabel.setText(rs.getString("gender"));
            EmgCLabel.setText(rs.getString("emergency_contact"));
            bloodLabel.setText(rs.getString("blood_group"));
            allergyLabel.setText(rs.getString("allergy"));
            
            
            
        }

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
   
    public void setName(String n){
        this.username=n;
        loadUserData();
    }
    
    public void setUser(UserRequests e){
        this.currentUser=e;
    }
    @FXML
   public void GoBack(ActionEvent event) throws IOException{
        
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
}
