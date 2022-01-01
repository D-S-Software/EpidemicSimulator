package backend;

import javax.sound.sampled.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class Music {

    private static Clip clip;
    private static long clipTimePosition;
    private static AudioInputStream input;
    private static String[] songs = {"ColeAcoustic.wav", "MarchOfMidnight.wav", "ActionEpic.wav", "TheSummoning.wav"};
    private static int previousSong = 1;

    private static FloatControl gainControl;

    /**Finds the requested file and loads it to be played
     *
     * @param soundFile The location of the sound file
     */
    private static void setFile(String soundFile)
        {
            System.out.println(songs.length);
            try {
                URL url = Music.class.getClassLoader().getResource("res/" + soundFile);

                input = AudioSystem.getAudioInputStream(url);

                AudioFormat baseFormat = input.getFormat();
                AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
                        baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
                AudioInputStream decodedInput = AudioSystem.getAudioInputStream(decodeFormat, input);
                clip = AudioSystem.getClip();
                clip.open(decodedInput);

                gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-25); //Lowers volume by x decibels

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    /**
     * Plays the sound file
     */
    public static void play()
        {
            clip.setFramePosition(0);
            clip.start();
        }

    /**
     * Pauses the sound file
     */
    public static void pause()
        {
           clipTimePosition = clip.getMicrosecondPosition();
            clip.stop();
        }

    /**
     * Resumes the sound file after it is paused
     */
    public static void resume()
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
    public static void stop()
    {
        clip.stop();
    }

    public static Clip getClip()
    {
        return clip;
    }

    /**
     * Changes the current background song
     */
    public static void changeSong()
    {
        int randomIndex = (int)(songs.length*Math.random());
        if(randomIndex == previousSong)
            changeSong();
        else
        {
            previousSong = randomIndex;
            setFile(songs[randomIndex]);
        }
    }
}
