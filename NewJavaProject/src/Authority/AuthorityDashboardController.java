package Authority;

import Database.DBConnectionUser;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AuthorityDashboardController {

    @FXML
    private Label InstituteNameLabel;

    @FXML
    private TableView<ViewEmergency> incidentTable;

    @FXML
    private TableColumn<ViewEmergency, String> typeColumn;

    @FXML
    private TableColumn<ViewEmergency, String> locationColumn;

    @FXML
    private TableColumn<ViewEmergency, String> statusColumn;

    @FXML
    private TableColumn<ViewEmergency, String> timeColumn;

    @FXML
    private TableColumn<ViewEmergency, Void> actionColumn;

    @FXML
    private TableColumn<ViewEmergency, Void> detailsColumn;

    private final java.util.Set<Integer> shownResolved = new java.util.HashSet<>();

    @FXML
    private Label ActiveLabel;

    @FXML
    private Label RespondingLabel;

    @FXML
    private Label ResolvedLabel;

    @FXML
    public void initialize() {

        typeColumn.setCellValueFactory(data
                -> new SimpleStringProperty(data.getValue().getType()));

        locationColumn.setCellValueFactory(data
                -> new SimpleStringProperty(data.getValue().getLocation()));

        statusColumn.setCellValueFactory(data
                -> new SimpleStringProperty(data.getValue().getStatus()));

        timeColumn.setCellValueFactory(data
                -> new SimpleStringProperty(data.getValue().getTime()));

        updateStatusCounts();
        addActionButtonToTable();
        addDetailsButtonToTable();
        colorStatusColumn();
        loadEmergencies();

        Timeline refresher = new Timeline(new KeyFrame(Duration.seconds(3), e
                -> {
            loadEmergencies();
            updateStatusCounts();

        }));
        refresher.setCycleCount(Timeline.INDEFINITE);
        refresher.play();
    }

    private void loadEmergencies() {

        ObservableList<ViewEmergency> list = FXCollections.observableArrayList();

        try {
            DBConnectionUser db = new DBConnectionUser();
            Connection con = db.connect();

            String query
                    = "SELECT e.request_id, e.user_id, e.emergency_type, e.status, e.request_time, "
                    + "u.address "
                    + "FROM emergency_requests e "
                    + "JOIN user_info u ON e.user_id = u.id "
                    + "WHERE e.status IN ('NEW','DISPATCHED','RESOLVED')";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("request_id");
                String status = rs.getString("status");

                // skip resolved already shown
                if (status.equals("RESOLVED") && shownResolved.contains(id)) {
                    continue;
                }

                list.add(new ViewEmergency(
                        rs.getInt("request_id"),
                        rs.getInt("user_id"),
                        rs.getString("emergency_type"),
                        rs.getString("address"),
                        rs.getString("status"),
                        rs.getString("request_time")
                ));
            }

            incidentTable.setItems(list);

            Timeline remover = new Timeline(new KeyFrame(Duration.seconds(2), ev -> {

                incidentTable.getItems().removeIf(item -> {
                    if (item.getStatus().equals("RESOLVED")) {
                        shownResolved.add(item.getEmergencyId()); // remember it
                        return true;
                    }
                    return false;
                });

            }));
            remover.play();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStatusCounts() {
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

            ActiveLabel.setText(String.valueOf(newCount));
            RespondingLabel.setText(String.valueOf(dispatchedCount));
            ResolvedLabel.setText(String.valueOf(resolvedCount));

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addActionButtonToTable() {

        // Set custom cell factory for action column
        actionColumn.setCellFactory(param -> new TableCell<>() {

            // Create button for each row
            private final Button acceptBtn = new Button("Accept");

            {
                // This block runs once when button is created

                acceptBtn.setStyle("-fx-background-color: green; -fx-text-fill: white;");

                acceptBtn.setOnAction(event -> {
                    ViewEmergency emergency
                            = getTableView().getItems().get(getIndex());

                    acceptEmergency(emergency.getEmergencyId());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                // If row is empty, don't show button
                if (empty) {
                    setGraphic(null);
                } else {
                    // Otherwise display the button

                    setGraphic(acceptBtn);
                }
            }
        });

    }

    private void addDetailsButtonToTable() {

        detailsColumn.setCellFactory(param -> new TableCell<>() {

            private final Button detailsBtn = new Button("View Details");

            {
                detailsBtn.setStyle("-fx-background-color: #123458; -fx-text-fill: white;");

                detailsBtn.setOnAction(event -> {

                    ViewEmergency emergency
                            = getTableView().getItems().get(getIndex());

                    openDetailsPopup(emergency.getUserId());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(detailsBtn);
                }
            }
        });
    }

    private void openDetailsPopup(int userId) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/Authority/ViewDetails.fxml")
            );

            Parent root = loader.load();

            ViewDetailsController controller
                    = loader.getController();

            controller.loadUserData(userId);

            Stage stage = new Stage();
            stage.setTitle("User Details");

            stage.initModality(Modality.APPLICATION_MODAL);

            // Set scene
            stage.setScene(new Scene(root));

            // Show popup
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void acceptEmergency(int requestId) {

        try {
            DBConnectionUser db = new DBConnectionUser();
            Connection con = db.connect();

            String query
                    = "UPDATE emergency_requests "
                    + "SET status = 'DISPATCHED' "
                    + "WHERE request_id = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, requestId);

            ps.executeUpdate();
            con.close();

            // Reload table (row will remain)
            loadEmergencies();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showUserDetails(int userId) {

        try {
            DBConnectionUser db = new DBConnectionUser();
            Connection con = db.connect();

            String query = "SELECT * FROM user_info WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String details
                        = "Username: " + rs.getString("username") + "\n"
                        + "Location: " + rs.getString("location") + "\n"
                        + "Phone: " + rs.getString("phone") + "\n"
                        + "Email: " + rs.getString("email");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Details");
                alert.setHeaderText("Full User Information");
                alert.setContentText(details);
                alert.showAndWait();
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void colorStatusColumn() {

        statusColumn.setCellFactory(column -> new TableCell<>() {

            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);

                if (empty || status == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(status);

                    switch (status) {

                        case "NEW":
                            setStyle("-fx-background-color: #ffcccc; -fx-text-fill: red;");
                            break;

                        case "DISPATCHED":
                            setStyle("-fx-background-color: #fff3cd; -fx-text-fill: orange;");
                            break;

                        case "RESOLVED":
                            setStyle("-fx-background-color: #d4edda; -fx-text-fill: green;");
                            break;

                        default:
                            setStyle("");
                    }
                }
            }
        });
    }

    public void setInstituteName(String name) {
        InstituteNameLabel.setText(name);
    }

    @FXML
    public void GoBack(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login/roleSelection.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    public void LoadReport(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Reports.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void LoadResponse(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Response.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
