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
public class ParticipantFrame {
    
    private int participantId;
    private int currentGold;
    private int dominionScore;
    private int jungleMinionsKilled;
    private int level;
    private int minionsKilled;
    private Position position;
    private int teamScore;
    private int totalGold;
    private int xp;
    
    public ParticipantFrame(){
        
    }
    
    public ParticipantFrame(JSONObject obj){
        //ints
        participantId = (int) obj.get("participantId");
        currentGold = (int) obj.get("currentGold");
        dominionScore = (int) obj.get("dominionScore");
        jungleMinionsKilled = (int) obj.get("jungleMinionsKilled");
        level = (int) obj.get("level");
        minionsKilled = (int) obj.get("minionsKilled");
        teamScore = (int) obj.get("teamScore");
        totalGold = (int) obj.get("totalGold");
        xp = (int) obj.get("xp");
        //Objects
        position = new Position((JSONObject) obj.get("position"));
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int ParticipantId) {
        this.participantId = ParticipantId;
    }

    public int getCurrentGold() {
        return currentGold;
    }

    public void setCurrentGold(int currentGold) {
        this.currentGold = currentGold;
    }

    public int getDominionScore() {
        return dominionScore;
    }

    public void setDominionScore(int dominionScore) {
        this.dominionScore = dominionScore;
    }

    public int getJungleMinionsKilled() {
        return jungleMinionsKilled;
    }

    public void setJungleMinionsKilled(int jungleMinionsKilled) {
        this.jungleMinionsKilled = jungleMinionsKilled;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMinionsKilled() {
        return minionsKilled;
    }

    public void setMinionsKilled(int minionsKilled) {
        this.minionsKilled = minionsKilled;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }

    public int getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(int totalGold) {
        this.totalGold = totalGold;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
    
    
    
}
