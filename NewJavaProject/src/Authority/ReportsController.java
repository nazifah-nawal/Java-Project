/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Authority;

import Database.DBConnectionUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nazifah
 */
public class ReportsController implements Initializable {

    @FXML
    private PieChart Chart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            DBConnectionUser db = new DBConnectionUser();
            Connection con = db.connect();

            String query = "SELECT status, COUNT(*) as count FROM emergency_requests GROUP BY status";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            int newCount = 0;
            int dispatchedCount = 0;
            int resolvedCount = 0;

            while (rs.next()) {
                String status = rs.getString("status");
                int count = rs.getInt("count");

                switch (status) {
                    case "NEW":
                        newCount = count;
                        break;
                    case "DISPATCHED":
                        dispatchedCount = count;
                        break;
                    case "RESOLVED":
                        resolvedCount = count;
                        break;
                }
            }
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Active Cases", newCount),
                    new PieChart.Data("Dispatched Units", dispatchedCount),
                    new PieChart.Data("Resolved Cases", resolvedCount)
            );
            Chart.setData(pieChartData);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void goBack(ActionEvent event )throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AuthorityDashboard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
