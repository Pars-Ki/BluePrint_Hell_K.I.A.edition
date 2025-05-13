package controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MediaController {
    private static MediaPlayer mediaPlayer;
    public static double volume = 0.5;

    public static void initializeBackgroundMedia() {
        if (mediaPlayer == null) {
            File file = new File("src/resources/MainMusic.mp3");
            Media media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(volume);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setAutoPlay(true);
        }
    }

    public static void ResetMedia(){
        mediaPlayer.stop();
    }

    public static void ChangeToGameSoundtrack(){
        File file = new File("src/resources/GameSoundtrack.mp3");
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
    }

    public static void  setVolume(){
        mediaPlayer.setVolume(volume);
    }
}
