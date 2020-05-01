import javax.sound.sampled.*;
import java.io.File;

public class Music {

        private Clip clip;
        private long clipTimePosition;
        private AudioInputStream sound;

    /**Creates a music object that can be used to play sounds on demand
     *
     * @param filename The name of the sound file to be played
     */
    public Music(String filename)
        {
            setFile(filename);
        }

    /**Finds the requested file and loads it to be played
     *
     * @param soundFile
     */
    private void setFile(String soundFile)
        {
            try
            {
                File file = new File("src/res/" + soundFile);
                sound = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(sound);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-10); //Lowers volume by 10 decibels
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    /**
     * Plays the sound file
     */
    public void play()
        {
            clip.setFramePosition(0);
            clip.start();
        }

    /**
     * Pauses the sound file
     */
    public void pause()
        {
           clipTimePosition = clip.getMicrosecondPosition();
            clip.stop();
        }

    /**
     * Resumes the sound file after it is paused
     */
    public void resume()
        {
            clip.setMicrosecondPosition(clipTimePosition);
            clip.start();
        }

    /**
     * Plays the sound file and loops over it so it does not stop
     */
    public void loop()
        {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

    /**
     * Stops the current sound file from playing
     */
    public void stop()
    {
        clip.stop();
    }
}
