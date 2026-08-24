package User;

import Audio.AccidentAudio;
import Audio.CrimeAudio;
import Audio.EarthquakeAudio;
import Audio.FireAudio;
import Audio.RedAudio;
import Database.DBConnectionUser;
import Emergency.Accident;
import Emergency.Crime;
import Emergency.Earthquake;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import Emergency.Fire;
import Emergency.Red;
import Emergency.Structure;
import java.sql.Connection;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import javafx.application.Platform;

public class EmergencyPageController {

    private Stage stage;

    @FXML
    private Button cancelButton;
    private Timeline countdown;
    private int timeSeconds = 10;
    private boolean emergencyActive = false;

    @FXML
    private Label timerLabel;

    @FXML
    private Label status;
    @FXML
    private Timeline statusChecker;

    private UserRequests currentUser;

    @FXML
    private Label GreetingsLabel;
    @FXML
    private Button viewProfileBtn;

    private String username;

    // NEW: Track already notified emergencies to prevent duplicate popups
    private static final Set<Integer> notifiedDispatches = new HashSet<>();

    AccidentAudio accidentAudio;
    CrimeAudio crimeAudio;
    EarthquakeAudio earthquakeAudio;
    FireAudio fireAudio;
    RedAudio redAudio;

    @FXML
    public void initialize() {

        accidentAudio = new AccidentAudio();
        accidentAudio.loadAudio();

        crimeAudio = new CrimeAudio();
        crimeAudio.loadAudio();

        earthquakeAudio = new EarthquakeAudio();
        earthquakeAudio.loadAudio();

        fireAudio = new FireAudio();
        fireAudio.loadAudio();
        
        redAudio= new RedAudio();
        redAudio.loadAudio();
        
        

        if (statusChecker == null || statusChecker.getStatus() != javafx.animation.Animation.Status.RUNNING) {
            startStatusChecker();
        }

    }

    public void LoadTodoPage(ActionEvent event, String s1, String s2) throws IOException {

        if (statusChecker != null) {
            statusChecker.stop();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/TodoPage.fxml"));
        Parent root = loader.load();

        TodoPageController controller = loader.getController();
        controller.setTextT(s1);
        controller.setTextN(s2);

        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void setUser(UserRequests user) {
        this.currentUser = user;
        //if (statusChecker == null) {
        startStatusChecker();
        //}
    }

    private void insertEmergency(String type) {

        String query = "INSERT INTO emergency_requests (user_id, emergency_type, status) VALUES (?, ?, ?)";

        DBConnectionUser dbUser = new DBConnectionUser();

        try (Connection con = dbUser.connect(); PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, currentUser.getId());  // VERY IMPORTANT
            pst.setString(2, type);
            pst.setString(3, "NEW");

            pst.executeUpdate();

            System.out.println("Emergency inserted successfully!");
            // No need to startStatusChecker here anymore, it's already running
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void startStatusChecker() {
        // Timeline checks every 3 seconds
        statusChecker = new Timeline(
                new KeyFrame(Duration.seconds(3), e -> checkStatus())
        );
        statusChecker.setCycleCount(Timeline.INDEFINITE);
        statusChecker.play();
    }

    private void checkStatus() {
        // NEW: Query all dispatched emergencies that haven't been notified yet
        String query
                = "SELECT request_id, status "
                + "FROM emergency_requests "
                + "WHERE user_id = ? AND status = 'DISPATCHED' "
                + "ORDER BY request_id ASC";

        DBConnectionUser dbUser = new DBConnectionUser();

        try (Connection con = dbUser.connect(); PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, currentUser.getId());
            var rs = pst.executeQuery();

            while (rs.next()) {
                int requestId = rs.getInt("request_id");
                if (!notifiedDispatches.contains(requestId)) {
                    notifiedDispatches.add(requestId); // Mark as notified
                    // Show popup safely on JavaFX thread
                    Platform.runLater(() -> showDispatchPopup(requestId));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDispatchPopup(int requestId) {

        javafx.scene.control.Alert alert
                = new javafx.scene.control.Alert(
                        javafx.scene.control.Alert.AlertType.CONFIRMATION);

        alert.setTitle("Help Dispatched");
        alert.setHeaderText("🚑 Help is on the way!");
        alert.setContentText("Have you received help?");

        javafx.scene.control.ButtonType yesButton
                = new javafx.scene.control.ButtonType("YES");

        javafx.scene.control.ButtonType noButton
                = new javafx.scene.control.ButtonType("NO");

        alert.getButtonTypes().setAll(yesButton, noButton);

        var result = alert.showAndWait();

        if (result.isPresent() && result.get() == yesButton) {
            resolveEmergency(requestId);
            loadEmergencyPage();

        }
    }

    private void resolveEmergency(int requestId) {

        String query
                = "UPDATE emergency_requests "
                + "SET status = 'RESOLVED' "
                + "WHERE request_id = ?";

        DBConnectionUser dbUser = new DBConnectionUser();

        try (Connection con = dbUser.connect(); PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, requestId);
            pst.executeUpdate();

            System.out.println("Emergency marked RESOLVED");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /// Here runtime Polymorphism is used
    /// Here runtime Polymorphism is used
    private void handleEmergency(ActionEvent event, String name, Structure e,Runnable audioPlay) throws IOException {
        if (countdown != null) {
            countdown.stop();
        }

       
        emergencyActive = true;
        timeSeconds = 10;
        timerLabel.setText("Time remaining to cancel request: " + timeSeconds + "s");

        cancelButton.setVisible(true);
        cancelButton.toFront();

        countdown = new Timeline(
                new KeyFrame(Duration.seconds(1), ev -> {
                    timeSeconds--;
                    timerLabel.setText("Time remaining to cancel request: " + timeSeconds + "s");

                    if (timeSeconds <= 0) {
                        countdown.stop();
                        emergencyActive = false;
                        cancelButton.setVisible(false);
 
                        insertEmergency(name); // insert into DB
                        
                        if (audioPlay != null) {
                        audioPlay.run();
                    }
                        // After timer ends, load ToDo page for the emergency
                        Platform.runLater(() -> {
                            try {
                                LoadTodoPage(event, e.to_do(), e.not_to_do());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        });
                    }
                })
        );

        countdown.setCycleCount(10);
        countdown.play();
    }

    @FXML
    public void FireButtonClicked(ActionEvent event) throws IOException {

        handleEmergency(event, "Fire", new Fire(),
                () -> fireAudio.play());
       
    }

    @FXML
    public void AccidentButtonClicked(ActionEvent event) throws IOException {
        handleEmergency(event, "Accident", new Accident(),
                ()->accidentAudio.play());
        
    }

    @FXML
    public void EarthquakeButtonClicked(ActionEvent event) throws IOException {
        handleEmergency(event, "Earthquake", new Earthquake(),
                ()->earthquakeAudio.play());
        
    }

    @FXML
    public void CrimeButtonClicked(ActionEvent event) throws IOException {
        handleEmergency(event, "Crime", new Crime(),
                ()->crimeAudio.play());
        
    }

    @FXML
    public void EmergencyButtonClicked(ActionEvent event) throws IOException {

        handleEmergency(event,"Unknown emergency",new Red(),
                ()->redAudio.play());
        
        
    }

    @FXML
    public void CancelEmergency(ActionEvent event) {
        if (countdown != null) {
            countdown.stop();
        }
        emergencyActive = false;
        cancelButton.setVisible(false);
        timerLabel.setText("");
    }

    @FXML
    private void LoadViewProfile(ActionEvent event) {

        System.out.println("VIEW PROFILE CLICKED");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewProfile.fxml"));
            Parent root = loader.load();

            ViewProfileController controller = loader.getController();
            controller.setName(currentUser.getUsername());
            controller.setUser(currentUser);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void LoadEditProfile(ActionEvent event) {

        System.out.println("edit PROFILE CLICKED");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProfile.fxml"));
            Parent root = loader.load();

            EditProfileController controller = loader.getController();
            controller.setName(currentUser.getUsername());
            controller.setUser(currentUser);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void LogOut(ActionEvent event) {

        System.out.println("LogOut CLICKED");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login/roleSelection.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setlabel(String name) {

        GreetingsLabel.setText("Hello, " + name);
    }

    public void setStatus(String msg) {
        status.setText(msg);
    }

    public void setUsername(String un) {
        username = un;
    }

    private void loadEmergencyPage() {
        try {

            if (statusChecker != null) {
                statusChecker.stop();
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/EmergencyPage.fxml"));
            Parent root = loader.load();

            EmergencyPageController controller = loader.getController();

            // pass user again
            controller.setUser(currentUser);
            controller.setlabel(currentUser.getName());
            controller.setUsername(currentUser.getUsername());
            controller.setStatus("Assistance has reached you. Take care! 🏥");

            Stage stage = (Stage) javafx.stage.Window.getWindows()
                    .filtered(w -> w.isShowing() && w instanceof Stage)
                    .get(0);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
