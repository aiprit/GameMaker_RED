package structures.run;

import structures.data.DataSound;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class RunSound {
    private AudioStream myAudioStream;
    private String myName;
    
    public RunSound(DataSound dataSound) {
        myAudioStream = dataSound.getAudio();
        myName = dataSound.getFileName();
    }
    
    public void playSound() {
        AudioPlayer.player.start(myAudioStream);
    }
    
    public DataSound toData() {
        return new DataSound(myName);
    }
    
}
