package gegeizi;



import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;


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
    private FadeTransition animate;
    private int duration;
    private int note;
    
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
        
        animate = new FadeTransition(Duration.millis(50),this);
        animate.setFromValue(1.0);
        animate.setToValue(0.5);
        animate.setCycleCount(2);
        animate.setAutoReverse(true);
        
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
        switch (s) {
            case "Key0.mp3":
                note = 45 + 12;
                break;
            case "Key1.mp3":
                note = 47 + 12;
                break;
            case "Key2.mp3":
                note = 48 + 12;
                break;
            case "Key3.mp4":
                note = 50 + 12;
                break;
            case "Key4.mp3":
                note = 52 + 12;
                break;
            case "Key5.mp3":
                note = 53 + 12;
                break;
            case "Key6.mp4":
                note = 55 + 12;
                break;
            case "Key7.mp3":
                note = 57 + 12;
                break;
            case "Key8.mp3":
                note = 59 + 12;
                break;
            case "Key9.mp4":
                note = 60 + 12;
                break;
        }
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
        animate.playFromStart();
        

        
        if(!synth.isOpen()){
            try {
                synth.open();
            } catch (MidiUnavailableException ex) {
                Logger.getLogger(Key.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            channels[channel].noteOn(note, volume);
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
    }
    
    public class MakeNoise implements Runnable{
        @Override
        public void run() {
            AudioListener listener = new AudioListener();
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream(name)));
                Clip clip = AudioSystem.getClip();
                clip.addLineListener(listener);
                clip.open(audioInputStream);
                
                try {
                    clip.start();
                    listener.waitUntilDone();
                    Platform.runLater(() -> {
                        setOpacity(1);
                    });
                    
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
    
    public void closeSynth(){
        //channels[channel].
        synth.close();
    }   
}
