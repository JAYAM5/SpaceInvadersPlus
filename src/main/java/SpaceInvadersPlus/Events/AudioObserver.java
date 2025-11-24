package SpaceInvadersPlus.Events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class AudioObserver implements IObserver {
    private static final Logger log = LoggerFactory.getLogger(AudioObserver.class);
//    private static AudioObserver instance;
//
//    private AudioObserver(){};
//    public static AudioObserver getInstance(){
//        if (instance == null){
//            instance = new AudioObserver();
//        }
//            return instance;
//    }

    @Override
    public void update(EventType eventType, String eventDescription) {
        switch (eventType){
            case EventType.Explosion -> playSound("src/audio/invaderkilled.wav");
            case EventType.ItemExplosion -> playSound("src/audio/smb_powerup.wav");
            case EventType.GameLost -> playSound("src/audio/the-price-is-right-losing-horn.wav");
            case EventType.LevelStart -> playSound("src/audio/LTTP_Get_HeartPiece.wav");
            case EventType.Death -> playSound("src/audio/LTTP_Link_Dying.wav");
            case EventType.GameWon -> playSound("src/audio/smb_world_clear.wav");
            case EventType.Shoot -> playSound("src/audio/shoot.wav");
        }
    }



    private void playSound(String filepath) {

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filepath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            log.error(e.toString());
        }
    }
}
