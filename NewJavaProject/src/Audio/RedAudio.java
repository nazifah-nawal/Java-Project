package Audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class RedAudio extends InstructionAudio {

    @Override
    public void loadAudio() {
        // Path to your MP3 file
        String path = "audio_files/red.mpeg";
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }
}