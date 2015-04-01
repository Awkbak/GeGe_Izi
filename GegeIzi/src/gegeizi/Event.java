/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gegeizi;

import org.json.simple.JSONObject;

/**
 *
 * @author user
 */
public class Event {
    private String eventType;
    private int itemId;
    private int participantId;
    private int timeStamp;
    
    public Event(){
        
    }
    
    public Event(JSONObject obj){
        //Strings
        eventType = (String) obj.get("eventType");
        //ints
        itemId = (int) obj.get("itemID");
        participantId = (int) obj.get("participantId");
        timeStamp = (int) obj.get("timestamp");
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    
}
