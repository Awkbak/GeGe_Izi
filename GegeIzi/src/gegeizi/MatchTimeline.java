/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gegeizi;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author user
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
        for(int e = 0; e < arr.size(); e ++){
            frames.add(new Frame((JSONObject) arr.get(e)));
        }
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
