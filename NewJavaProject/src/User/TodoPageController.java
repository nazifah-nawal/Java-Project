package User;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TodoPageController {

    @FXML
    private Label TodoLabel;

    @FXML
    private Label NottodoLabel;
    
    private EmergencyPageController emergencyPageController;

    public void setEmergencyPageController(EmergencyPageController controller) {
        this.emergencyPageController = controller;
    }

    public void setTextT(String text) {
        animateText(TodoLabel, text, () -> {
            // after todo finishes, start not todo
            animateText(NottodoLabel, pendingNotTodoText, null);
        });
    }

    private String pendingNotTodoText;

    public void setTextN(String text) {
        pendingNotTodoText = text;
    }

    private void animateText(Label label, String fullText, Runnable onFinished) {

        String[] words = fullText.split(" ");
        label.setText("");

        Timeline timeline = new Timeline();

        for (int i = 0; i < words.length; i++) {
            final int index = i;
            KeyFrame keyFrame = new KeyFrame(
                    Duration.millis(300 * (i + 1)),
                    e -> label.setText(label.getText() + words[index] + " ")
            );
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.setOnFinished(e -> {
            if (onFinished != null) {
                onFinished.run();
            }
        });

        timeline.play();
    }
}
