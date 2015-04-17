/**©Awkbak BR, Bobjrsenior
 * º⌐⌐º
 * 
 * Sounds of URF
 * Goal: To create an interactive Application that generates a sound sequence based off the outcome of game IDs.
 * Description: Imports game data from a 'League of Legends' match using Riot Games API.
 * Then it proceeds to layout all game events in a sort of sheet music.
 * The Keys correspond to each champion/player in the selected match.
 * The Keys will be played according to their respective events in the match.
 * 
 * Start Date: 3/27/2015
 * End Date: 4/17/2015
 */
package gegeizi;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;


/**
 * Musical key that when pressed generates a marvelous sound.
 * @author Awkbak BR, Bobjrsenior
 */
public class Key extends Rectangle {
    private Image mainimage; //Champion image for the key
    private Synthesizer synth; //MIDI synthesizer to generate sound
    private  MidiChannel[] channels; //Where the sounds are stored
    private int note; //Pitch of the sound
    private int channel; //Instrument
    private int volume; //Sound volume
    private int program = 1;
    private FadeTransition animate; //Animation for when the key is pressed.
    /**
     * Constructor method. Generates the key according to its sound. Also makes the graphics for the keys.
     * @param s 
     */
    public Key(String s){
       
        animate = new FadeTransition(Duration.millis(70),this);
        animate.setFromValue(1.0);
        animate.setToValue(0.5);
        animate.setCycleCount(2);
        animate.setAutoReverse(true);
        
        this.setWidth(50);
        this.setHeight(140);
        this.setStrokeWidth(3);
        this.setArcHeight(5);
        this.setArcWidth(5);
        
        channel = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments
        volume = 80; // between 0 and 127
        int octave = 12;//Changes the octave of the keys appropriately
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
    /**
     * Changes the image of the key according to the champion IDs.
     * @param imID 
     */
    public void setImage(int imID){
        String ad = "Splash/" + imID +".jpg";
        mainimage = new Image(getClass().getResourceAsStream(ad),50,140,false,false);
        ImagePattern min = new ImagePattern(mainimage);
        this.setFill(min);
    }
    /**
     * Plays the sound stored within key. The result is a sweet melody.
     */
    public void PlaySound(){
        
        //Make sure the channel synth is open before playing
        if(!synth.isOpen()){
            try {
                synth.open();
                channels[channel].programChange(program);
            } catch (MidiUnavailableException ex) {
                Logger.getLogger(Key.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            
            //Play the sound
            animate.playFromStart();
            channels[channel].noteOn(note, volume);
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
    }
    
    public void closeSynth(){
        synth.close();
    }
    
    public void openSynth(){
        try {
            synth.open();
            channels[channel].programChange(program);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(Key.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    /**
     * Changes the instrument played by the pressed key.
     * @param program What instrument to change to
     */
    public void setInstrument(int program){
        this.program = program;
        this.channels[channel].programChange(program);
    }
}
