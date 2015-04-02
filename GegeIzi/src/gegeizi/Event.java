/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gegeizi;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author user
 */
public class Event {
    
    //Global
    private String eventType;
    private long timeStamp;
    //Multi-use
    private long killerId;
    private Position position;
    
    private long itemId;
    private long participantId;
      
    
    //Champion Kill
    private long victimId;
    private ArrayList<Long> assistIds;
    //Killer id
    //position
    
    //Building Kill
    private String buildingType;
    private String towerType;
    private String laneType;
    private long teamId;
    //killer id
    //position
    
    
    public Event(){
        //Initialize ArrayLists
        assistIds = new ArrayList<>();
    }
    
    public Event(JSONObject obj){
        //Initialize ArrayLists
        assistIds = new ArrayList<>();
        
        //Strings
        eventType = (String) obj.get("eventType");
        
        switch(eventType){
            case "CHAMPION_KILL":
                killerId = (long) obj.get("killerId");
                victimId = (long) obj.get("victimId");
                
                position = new Position((JSONObject) obj.get("position"));
                if(obj.containsKey("assistingParticipantIds")){
                    JSONArray arr = (JSONArray) obj.get("assistingParticipantIds");
                    for (int e = 0; e < arr.size(); e ++) {
                        assistIds.add((long) arr.get(e));
                    }
                }
                
                
                break;
            case "BUILDING_KILL":
                buildingType = (String) obj.get("buildingType");
                towerType = (String) obj.get("towerType");
                laneType = (String) obj.get("laneType");
                teamId = (long) obj.get("teamId");
                killerId = (long) obj.get("killerId");
                position = new Position((JSONObject) obj.get("position"));
                break;
            
        }
        
        //itemId = (int) obj.get("itemID");
        //participantId = (int) obj.get("participantId");
        timeStamp = (long) obj.get("timestamp");
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(long participantId) {
        this.participantId = participantId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getKillerId() {
        return killerId;
    }

    public void setKillerId(long killerId) {
        this.killerId = killerId;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public long getVictimId() {
        return victimId;
    }

    public void setVictimId(long victimId) {
        this.victimId = victimId;
    }

    public ArrayList<Long> getAssistIds() {
        return assistIds;
    }

    public void setAssistIds(ArrayList<Long> assistIds) {
        this.assistIds = assistIds;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getTowerType() {
        return towerType;
    }

    public void setTowerType(String towerType) {
        this.towerType = towerType;
    }

    public String getLaneType() {
        return laneType;
    }

    public void setLaneType(String laneType) {
        this.laneType = laneType;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }
    
    
}
