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
public class ParticipantTimeline {
    
    private String lane;
    private String role;
    private ParticipantTimelineTuple creepsPerMinDeltas;
    private ParticipantTimelineTuple csDiffPerMinDeltas;
    private ParticipantTimelineTuple damageTakenDiffPerMinDeltas;
    private ParticipantTimelineTuple damageTakenPerMinDeltas;
    private ParticipantTimelineTuple goldPerMinDeltas;
    private ParticipantTimelineTuple xpDiffPerMinDeltas;
    private ParticipantTimelineTuple xpPerMinDeltas;
    
    public ParticipantTimeline(){
        
    }
    
    public ParticipantTimeline(JSONObject obj){
        //Strings
        lane = (String) obj.get("lane");
        role = (String) obj.get("role");
        //Objects
        creepsPerMinDeltas = new ParticipantTimelineTuple((JSONObject) obj.get("creepsPerMinDeltas"));
        csDiffPerMinDeltas = new ParticipantTimelineTuple((JSONObject) obj.get("csDiffPerMinDeltas"));
        damageTakenDiffPerMinDeltas = new ParticipantTimelineTuple((JSONObject) obj.get("damageTakenDiffPerMinDeltas"));
        damageTakenPerMinDeltas = new ParticipantTimelineTuple((JSONObject) obj.get("damageTakenPerMinDeltas"));
        goldPerMinDeltas = new ParticipantTimelineTuple((JSONObject) obj.get("goldPerMinDeltas"));
        xpDiffPerMinDeltas = new ParticipantTimelineTuple((JSONObject) obj.get("xpDiffPerMinDeltas"));
        xpPerMinDeltas = new ParticipantTimelineTuple((JSONObject) obj.get("xpPerMinDeltas"));
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ParticipantTimelineTuple getCreepsPerMinDeltas() {
        return creepsPerMinDeltas;
    }

    public void setCreepsPerMinDeltas(ParticipantTimelineTuple creepsPerMinDeltas) {
        this.creepsPerMinDeltas = creepsPerMinDeltas;
    }

    public ParticipantTimelineTuple getCsDiffPerMinDeltas() {
        return csDiffPerMinDeltas;
    }

    public void setCsDiffPerMinDeltas(ParticipantTimelineTuple csDiffPerMinDeltas) {
        this.csDiffPerMinDeltas = csDiffPerMinDeltas;
    }

    public ParticipantTimelineTuple getDamageTakenDiffPerMinDeltas() {
        return damageTakenDiffPerMinDeltas;
    }

    public void setDamageTakenDiffPerMinDeltas(ParticipantTimelineTuple damageTakenDiffPerMinDeltas) {
        this.damageTakenDiffPerMinDeltas = damageTakenDiffPerMinDeltas;
    }

    public ParticipantTimelineTuple getDamageTakenPerMinDeltas() {
        return damageTakenPerMinDeltas;
    }

    public void setDamageTakenPerMinDeltas(ParticipantTimelineTuple damageTakenPerMinDeltas) {
        this.damageTakenPerMinDeltas = damageTakenPerMinDeltas;
    }

    public ParticipantTimelineTuple getGoldPerMinDeltas() {
        return goldPerMinDeltas;
    }

    public void setGoldPerMinDeltas(ParticipantTimelineTuple goldPerMinDeltas) {
        this.goldPerMinDeltas = goldPerMinDeltas;
    }

    public ParticipantTimelineTuple getXpDiffPerMinDeltas() {
        return xpDiffPerMinDeltas;
    }

    public void setXpDiffPerMinDeltas(ParticipantTimelineTuple xpDiffPerMinDeltas) {
        this.xpDiffPerMinDeltas = xpDiffPerMinDeltas;
    }

    public ParticipantTimelineTuple getXpPerMinDeltas() {
        return xpPerMinDeltas;
    }

    public void setXpPerMinDeltas(ParticipantTimelineTuple xpPerMinDeltas) {
        this.xpPerMinDeltas = xpPerMinDeltas;
    }
    
    
    
}
