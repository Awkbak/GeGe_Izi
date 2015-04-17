package gegeizi;



import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;

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
    private Synthesizer synth;
    private  MidiChannel[] channels;
    private int note;
    private int channel; //Instrument
    private int volume;
    private FadeTransition animate;
    
    public Key(){
        this.setWidth(50);
        this.setHeight(140);
        this.setStrokeWidth(3);
        this.setStroke(Color.STEELBLUE);
        this.setFill(Color.SNOW);
        this.setArcHeight(5);
        this.setArcWidth(5);
    }
    public Key(String s){
       
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
        int octave = 12;
        //Choose the correct note based on the key
        switch (s) {
            case "0":
                note = 45 + octave;
                break;
            case "1":
                note = 47 + octave;
                break;
            case "2":
                note = 48 + octave;
                break;
            case "3":
                note = 50 + octave;
                break;
            case "4":
                note = 52 + octave;
                break;
            case "5":
                note = 53 + octave;
                break;
            case "6":
                note = 55 + octave;
                break;
            case "7":
                note = 57 + octave;
                break;
            case "8":
                note = 59 + octave;
                break;
            case "9":
                note = 60 + octave;
                break;
            default:
                note = 40 + octave;
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
        
        //Make sure the channel synth is open before playing
        if(!synth.isOpen()){
            try {
                synth.open();
            } catch (MidiUnavailableException ex) {
                Logger.getLogger(Key.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            
            //Play the sound
            animate.playFromStart();
            channels[channel].noteOn(note, volume);
            /*Thread th = new Thread(() -> {
                long time = System.currentTimeMillis() + 200;
                while(System.currentTimeMillis() < time){
                    
                }
                channels[channel].noteOff(note);
            });
            th.start();
            */
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
    }
    
    public void closeSynth(){
        //channels[channel].
        synth.close();
    }
    
    public void openSynth(){
        try {
            synth.open();
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(Key.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    public void setInstrument(int program){
        this.channels[channel].programChange(program);
    }
}
