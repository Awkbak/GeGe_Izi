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
public class Frame {
    
    private ArrayList<Event> events;
    private ArrayList<ParticipantFrame> participantFrames;
    private int timeStamp;

    public Frame(){
        events = new ArrayList<>();
        participantFrames = new ArrayList<>();
    }
    
    public Frame(JSONObject obj){
        //Initialize ArrayLists
        events = new ArrayList<>();
        participantFrames = new ArrayList<>();
        
        //ints
        timeStamp = (int) obj.get("timestamp");
        //Arrays
        JSONArray arr = (JSONArray) obj.get("frames");
        for (Object arr1 : arr) {
            participantFrames.add(new ParticipantFrame((JSONObject) arr1));
        }
        if(obj.containsKey("events"))
        {
            arr = (JSONArray) obj.get("events");
            for (Object arr1 : arr) {
                events.add(new Event((JSONObject) arr1));
            }
        }
    }
    
    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public ArrayList<ParticipantFrame> getParticipantFrames() {
        return participantFrames;
    }

    public void setParticipantFrames(ArrayList<ParticipantFrame> participantFrames) {
        this.participantFrames = participantFrames;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    public void addEvent(Event event){
        events.add(event);
    }
    
    public void addParticipantFrame(ParticipantFrame participantFrame){
        participantFrames.add(participantFrame);
    }
    
}
