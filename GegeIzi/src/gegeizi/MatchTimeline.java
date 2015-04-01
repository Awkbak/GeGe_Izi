/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gegeizi;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class MatchTimeline {
    
    private int frameInterval;
    private ArrayList<Frame> frames;
    
    public MatchTimeline(){
        frames = new ArrayList<>();
    }

    public int getFrameInterval() {
        return frameInterval;
    }

    public void setFrameInterval(int frameInterval) {
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
