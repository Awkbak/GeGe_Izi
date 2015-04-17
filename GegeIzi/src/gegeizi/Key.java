package gegeizi;



import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;

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
        //resource = getClass().getResource(s);
        //tone = new Media(resource.toString());
        resource = null;
        tone = null;
        tonePlayer = null;
        //tonePlayer = new MediaPlayer(tone);
        
        animate = new FadeTransition(Duration.millis(70),this);
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
        volume = 80; // between 0 and 127
        duration = 50; // in milliseconds
        
        switch (s) {
            case "0":
                note = 45 + 12;
                break;
            case "1":
                note = 47 + 12;
                break;
            case "2":
                note = 48 + 12;
                break;
            case "3":
                note = 50 + 12;
                break;
            case "4":
                note = 52 + 12;
                break;
            case "5":
                note = 53 + 12;
                break;
            case "6":
                note = 55 + 12;
                break;
            case "7":
                note = 57 + 12;
                break;
            case "8":
                note = 59 + 12;
                break;
            case "9":
                note = 60 + 12;
                break;
            default:
                note = 40;
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
    
    public void closeSynth(){
        //channels[channel].
        synth.close();
    }   
}
