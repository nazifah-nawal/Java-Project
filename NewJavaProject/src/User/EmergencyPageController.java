
package User;

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
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EmergencyPageController {

    private Stage stage;

    @FXML
    private Button cancelButton;
    private Timeline countdown;
    private int timeSeconds = 10;
    private boolean emergencyActive = false;

    @FXML
    private Label timerLabel;   

    public void LoadTodoPage(ActionEvent event, String s1, String s2) throws IOException {
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

    @FXML
    public void FireButtonClicked(ActionEvent event) throws IOException {
        if (countdown != null) {
            countdown.stop();
        }
        Fire ob = new Fire();

        LoadTodoPage(event, ob.to_do(), ob.not_to_do());
    }

    @FXML
    public void AccidentButtonClicked(ActionEvent event) throws IOException {
        if (countdown != null) {
            countdown.stop();
        }
        Accident ob = new Accident();

        LoadTodoPage(event, ob.to_do(), ob.not_to_do());
    }

    @FXML
    public void EarthquakeButtonClicked(ActionEvent event) throws IOException {
        if (countdown != null) {
            countdown.stop();
        }
        Earthquake ob = new Earthquake();

        LoadTodoPage(event, ob.to_do(), ob.not_to_do());
    }

    @FXML
    public void CrimeButtonClicked(ActionEvent event) throws IOException {
        if (countdown != null) {
            countdown.stop();
        }
        Crime ob = new Crime();

        LoadTodoPage(event, ob.to_do(), ob.not_to_do());
    }

    @FXML
    public void EmergencyButtonClicked(ActionEvent event) {

        if (emergencyActive) {
            return; 
        }
        emergencyActive = true;
        timeSeconds = 10;
        timerLabel.setText("Time remaining: " + timeSeconds + "s");

        cancelButton.setVisible(true);
        countdown = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {

                    timeSeconds--;
                    timerLabel.setText("Time remaining: " + timeSeconds + "s");

                    if (timeSeconds <= 0) {
                        countdown.stop();
                        emergencyActive = false;
                        cancelButton.setVisible(false);
                        try {
                            Red ob = new Red();

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/TodoPage.fxml"));
                            Parent root = loader.load();

                            TodoPageController controller = loader.getController();
                            controller.setTextT(ob.to_do());
                            controller.setTextN(ob.not_to_do());

                            Stage stage = (Stage) timerLabel.getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    }
                })
        );

        countdown.setCycleCount(10);
        countdown.play();
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

}