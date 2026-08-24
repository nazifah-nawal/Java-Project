package Authority;

import Database.DBConnectionUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.stage.Stage;



public class ResponseController implements Initializable{

    private int TotalAvailable=40;
    
    @FXML
    private Label ALabel;
    
    @FXML
    private Label DLabel;
    
    @FXML
    private Label SpotLabel;
    
    @FXML
    private Label MLabel;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            DBConnectionUser db = new DBConnectionUser();
            Connection con = db.connect();

            String query = "SELECT status, COUNT(*) as count FROM emergency_requests GROUP BY status";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            
            int dispatchedCount = 0;
            int resolvedCount = 0;

            while (rs.next()) {
                String status = rs.getString("status");
                int count = rs.getInt("count");

                switch (status) {
                    
                    case "DISPATCHED":
                        dispatchedCount = count;
                        break;
                    case "RESOLVED":
                        resolvedCount = count;
                        break;
                }
            }
            
            int av=TotalAvailable-(dispatchedCount+resolvedCount);
            ALabel.setText("🚓 Available\n"+av);
            DLabel.setText("🚨 Dispatched\n"+dispatchedCount);
            SpotLabel.setText("📍 On Spot\n"+resolvedCount);
            MLabel.setText("🔧 Maintenance\n2");
            
            
            
            
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     @FXML
    public void goBack(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AuthorityDashboard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        
    }

    
}