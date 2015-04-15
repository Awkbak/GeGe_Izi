package gegeizi;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 *
 * @author Awkbak
 */
public class Key extends Rectangle {
    private Image mainimage;
    final private URL resource;
    final private Media tone;
    private MediaPlayer tonePlayer;
    private Synthesizer synth;
    private  MidiChannel[] channels;
    private int channel;
    private int volume;
    private int duration;
    private File sound;
    private String name;
    
    public Key(){
        resource = null;
        tone = null;
        tonePlayer = null;
        this.setWidth(50);
        this.setHeight(140);
        this.setStrokeWidth(3);
        this.setStroke(Color.STEELBLUE);
        this.setFill(Color.SNOW);
        this.setArcHeight(5);
        this.setArcWidth(5);
    }
    public Key(String s){
        resource = getClass().getResource(s);
        tone = new Media(resource.toString());
        tonePlayer = new MediaPlayer(tone);
        name = s;
        this.setWidth(50);
        this.setHeight(140);
        this.setStrokeWidth(3);
        this.setStroke(Color.STEELBLUE);
        this.setFill(Color.SNOW);
        this.setArcHeight(5);
        this.setArcWidth(5);
        
        channel = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments
        volume = 80; // between 0 et 127
        duration = 50; // in milliseconds
        try {
            this.synth = MidiSystem.getSynthesizer();
            this.synth.open();
            this.channels = synth.getChannels();
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(Key.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void setImage(int imID){
        String ad = "Splash/" + imID +".jpg";
        mainimage = new Image(getClass().getResourceAsStream(ad),50,140,false,false);
        ImagePattern min = new ImagePattern(mainimage);
        this.setFill(min);
    }
    public void PlaySound(){
        
        //tonePlayer = new MediaPlayer(tone);
        //tonePlayer.seek(new Duration(0));
        /*if(tonePlayer.getStatus().equals(MediaPlayer.Status.PLAYING)){
           
            this.setOpacity(0.4);
        }
        //if(!tonePlayer.getStatus().equals(MediaPlayer.Status.PLAYING)){
            this.setOpacity(0.4);
            tonePlayer.setOnEndOfMedia(() -> {
                this.setOpacity(1);

            });
            tonePlayer.play();
        //}*/
       
       //this.setOpacity(0.4);
       //Thread th = new Thread(new MakeNoise());
       //th.start();
        
        /*if(!synth.isOpen()){
            try {
                synth.open();
            } catch (MidiUnavailableException ex) {
                Logger.getLogger(Key.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        midiTest();
                */
    }
    
    public void closeSynth(){
        //channels[channel].
        synth.close();
    }
    
    public void midiTest(){


        try {
                //synth.open();

                // --------------------------------------
                // Play a few notes.
                // The two arguments to the noteOn() method are:
                // "MIDI note number" (pitch of the note),
                // and "velocity" (i.e., volume, or intensity).
                // Each of these arguments is between 0 and 127.
                channels[channel].noteOn( 60, volume ); // C note


                // --------------------------------------
                // Play a C major chord.



                //synth.close();
        }
        catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    public class MakeNoise implements Runnable{

        @Override
        public void run() {
            AudioListener listener = new AudioListener();
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(Key.class.getClassLoader().getResourceAsStream(name));
                Clip clip = AudioSystem.getClip();
                clip.addLineListener(listener);
                clip.open(audioInputStream);
                try {
                    clip.start();
                    listener.waitUntilDone();
                    setOpacity(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Key.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    clip.close();
                }
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Key.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Key.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Key.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(audioInputStream != null){
                        audioInputStream.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Key.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    

    }
    
    class AudioListener implements LineListener {
        private boolean done = false;
        @Override public synchronized void update(LineEvent event) {
            Type eventType = event.getType();
            if (eventType == Type.STOP || eventType == Type.CLOSE) {
                done = true;
                notifyAll();
            }
        }
        public synchronized void waitUntilDone() throws InterruptedException {
            while (!done) { wait(); }
        }
    }
}
