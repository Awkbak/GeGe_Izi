package gegeizi;


import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 *
 * @author Awkbak
 */
public class Key extends Rectangle {
    final private URL resource;
    final private Media tone;
    private MediaPlayer tonePlayer;
    
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
        this.setWidth(50);
        this.setHeight(140);
        this.setStrokeWidth(3);
        this.setStroke(Color.STEELBLUE);
        this.setFill(Color.SNOW);
        this.setArcHeight(5);
        this.setArcWidth(5);
    }
    public void PlaySound(){
        tonePlayer = new MediaPlayer(tone);
        setFill(Color.GREY);
        tonePlayer.setOnEndOfMedia(() -> {
            setFill(Color.SNOW);
        });
        tonePlayer.play();
    }
}
