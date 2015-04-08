package gegeizi;


import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


/**
 *
 * @author Awkbak
 */
public class Key extends Rectangle {
    private Image mainimage;
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
        System.out.println(resource == null);
        tone = new Media(resource.toString());
        tonePlayer = new MediaPlayer(tone);
        //tonePlayer.setVolume(100);
        this.setWidth(50);
        this.setHeight(140);
        this.setStrokeWidth(3);
        this.setStroke(Color.STEELBLUE);
        this.setFill(Color.SNOW);
        this.setArcHeight(5);
        this.setArcWidth(5);
    }
    public void setImage(int imID){
        String ad = "Splash/" + imID +".jpg";
        mainimage = new Image(getClass().getResourceAsStream(ad),50,140,false,false);
        ImagePattern min = new ImagePattern(mainimage);
        this.setFill(min);
    }
    public void PlaySound(){
        //tonePlayer = new MediaPlayer(tone);
        if(tonePlayer.getStatus().equals(MediaPlayer.Status.PLAYING)){
            tonePlayer.seek(new Duration(0));
            this.setOpacity(0.4);
        }
        if(!tonePlayer.getStatus().equals(MediaPlayer.Status.PLAYING)){
            this.setOpacity(0.4);
            tonePlayer.setOnEndOfMedia(() -> {
                this.setOpacity(1);

            });
            tonePlayer.play();
        }
    }
}
