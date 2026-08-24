package Audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;


public abstract class InstructionAudio {

    protected MediaPlayer mediaPlayer;

    public abstract void loadAudio();
    
    public void play() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();   
            mediaPlayer.play();   
        }
    }
    
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}