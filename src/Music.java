import javax.sound.sampled.*;
import java.io.File;

public class Music {

        private Clip clip;
        private long clipTimePosition;
        private AudioInputStream sound;

        public Music(String filename)
        {
            setFile(filename);
        }

        public void setFile(String soundFile)
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

        public void play()
        {
            clip.setFramePosition(0);
            //clip.start(); //TODO Add back in
        }

        public void pause()
        {
           clipTimePosition = clip.getMicrosecondPosition();
            clip.stop();
        }

        public void resume()
        {
            clip.setMicrosecondPosition(clipTimePosition);
            clip.start();
        }

        public void loop()
        {
            clip.setFramePosition(0);
            //clip.loop(Clip.LOOP_CONTINUOUSLY); //TODO Add back in
        }

        public void stop()
        {
            clip.stop();
        }
}
