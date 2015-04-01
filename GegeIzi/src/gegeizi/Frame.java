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
public class Frame {
    
    private ArrayList<Event> events;
    private ArrayList<ParticipantFrame> participantFrames;
    private int timeStamp;

    public Frame(){
        events = new ArrayList<>();
        participantFrames = new ArrayList<>();
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
