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

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Awkbak BR, Bobjrsenior
 */
public class MatchTimeline {
    
    private long frameInterval;
    private ArrayList<Frame> frames;
    
    public MatchTimeline(){
        frames = new ArrayList<>();
    }
    
    public MatchTimeline(JSONObject obj){
        //Initializing ArrayLists
        frames = new ArrayList<>();
        //ints
        frameInterval = (long) obj.get("frameInterval");
        //Arrays
        JSONArray arr = (JSONArray) obj.get("frames");
        arr.stream().forEach((arr1) -> {
            frames.add(new Frame((JSONObject) arr1));
        });
    }

    public long getFrameInterval() {
        return frameInterval;
    }

    public void setFrameInterval(long frameInterval) {
        this.frameInterval = frameInterval;
    }

    public ArrayList<Frame> getFrames() {
        return frames;
    }

    public void setFrames(ArrayList<Frame> frames) {
        this.frames = frames;
    }  
    
    public void addFrame(Frame frame){
        frames.add(frame);
    }
}
