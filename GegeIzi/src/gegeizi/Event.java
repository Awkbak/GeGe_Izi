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
    private long participantId; //When not directly given value, it is set as whatever is relevant
    //Multi-use
    private long killerId;
    private Position position;
    
    
    
    private long itemId;    
      
    
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
    
    //Skill level up
    private String levelUpType;
    private long skillSlot;
    //participantId
    
    
    public Event(){
        //Initialize ArrayLists
        assistIds = new ArrayList<>();
    }
    
    public Event(JSONObject obj){
        //Initialize ArrayLists
        assistIds = new ArrayList<>();
        
        //Strings
        eventType = (String) obj.get("eventType");
        
        //Get the id of whoever did the event and put it into participantId
        if(obj.containsKey("participantId")){
            participantId = (long) obj.get("participantId");
        }
        else if(obj.containsKey("killerId")){
            participantId = (long) obj.get("killerId");
        }
        else if(obj.containsKey("creatorId")){
            participantId = (long) obj.get("creatorId");
        }
        
        //Not necasary for this project, but nice to have implemented (even partially)
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
                
                participantId = killerId;                
                break;
            case "BUILDING_KILL":
                buildingType = (String) obj.get("buildingType");
                towerType = (String) obj.get("towerType");
                laneType = (String) obj.get("laneType");
                teamId = (long) obj.get("teamId");
                killerId = (long) obj.get("killerId");
                position = new Position((JSONObject) obj.get("position"));
                
                participantId = killerId;
                break;
            case "SKILL_LEVEL_UP":
                levelUpType = (String) obj.get("levelUpType");
                skillSlot = (long) obj.get("skillSlot");
                participantId = (long) obj.get("participantId");
                break;
            case "ITEM_PURCHASED":
                participantId = (long) obj.get("participantId");
                break;
            case "WARD_PLACED":
                    participantId = (long) obj.get("creatorId");
                break;
            case "ITEM_DESTROYED":
                participantId = (long) obj.get("participantId");
                break;
            case "ELITE_MONSTER_KILL":
                participantId = (long) obj.get("killerId");
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

    public String getLevelUpType() {
        return levelUpType;
    }

    public void setLevelUpType(String levelUpType) {
        this.levelUpType = levelUpType;
    }

    public long getSkillSlot() {
        return skillSlot;
    }

    public void setSkillSlot(long skillSlot) {
        this.skillSlot = skillSlot;
    }
    
    
}
