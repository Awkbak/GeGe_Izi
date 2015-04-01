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
public class Team {
    
    private int teamId;
    private ArrayList<BanTuple> bans;
    private int baronKills;
    private int dominionVictoryScore;
    private int dragonKills;
    private boolean firstBaron;
    private boolean firstBlood;
    private boolean firstDragon;
    private boolean firstInhibitor;
    private boolean firstTower;
    private int inhibitorKills;
    private int towerKills;
    private int vileMawKills;
    private boolean winner;
    
    public Team(){
        bans = new ArrayList<>();        
    }
    
    public Team(JSONObject obj){
        //Initialize ArrayLists
        bans = new ArrayList<>();
        
        //ints
        teamId = (int) obj.get("teamId");
        baronKills = (int) obj.get("baronKills");
        dominionVictoryScore = (int) obj.get("dominionVictoryScore");       
        dragonKills = (int) obj.get("dragonKills");
        inhibitorKills = (int) obj.get("inhibitorKills");
        towerKills = (int) obj.get("towerKills");
        vileMawKills = (int) obj.get("vileMawKills");
        //booleans
        winner = (boolean) obj.get("winner");
        firstBaron = (boolean) obj.get("firstBaron");
        firstBlood = (boolean) obj.get("firstBlood");
        firstDragon = (boolean) obj.get("firstDragon");
        firstInhibitor = (boolean) obj.get("firstInhibitor");
        firstTower = (boolean) obj.get("firstTower");
        //arrays
        JSONArray arr = (JSONArray) obj.get("bans");
        for (Object arr1 : arr) {
            bans.add(new BanTuple((JSONObject) arr1));
        }
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public ArrayList<BanTuple> getBans() {
        return bans;
    }

    public void setBans(ArrayList<BanTuple> bans) {
        this.bans = bans;
    }

    public int getBaronKills() {
        return baronKills;
    }

    public void setBaronKills(int baronKills) {
        this.baronKills = baronKills;
    }

    public int getDominionVictoryScore() {
        return dominionVictoryScore;
    }

    public void setDominionVictoryScore(int dominionVictoryScore) {
        this.dominionVictoryScore = dominionVictoryScore;
    }

    public int getDragonKills() {
        return dragonKills;
    }

    public void setDragonKills(int dragonKills) {
        this.dragonKills = dragonKills;
    }

    public boolean isFirstBaron() {
        return firstBaron;
    }

    public void setFirstBaron(boolean firstBaron) {
        this.firstBaron = firstBaron;
    }

    public boolean isFirstBlood() {
        return firstBlood;
    }

    public void setFirstBlood(boolean firstBlood) {
        this.firstBlood = firstBlood;
    }

    public boolean isFirstDragon() {
        return firstDragon;
    }

    public void setFirstDragon(boolean firstDragon) {
        this.firstDragon = firstDragon;
    }

    public boolean isFirstInhibitor() {
        return firstInhibitor;
    }

    public void setFirstInhibitor(boolean firstInhibitor) {
        this.firstInhibitor = firstInhibitor;
    }

    public boolean isFirstTower() {
        return firstTower;
    }

    public void setFirstTower(boolean firstTower) {
        this.firstTower = firstTower;
    }

    public int getInhibitorKills() {
        return inhibitorKills;
    }

    public void setInhibitorKills(int inhibitorKills) {
        this.inhibitorKills = inhibitorKills;
    }

    public int getTowerKills() {
        return towerKills;
    }

    public void setTowerKills(int towerKills) {
        this.towerKills = towerKills;
    }

    public int getVileMawKills() {
        return vileMawKills;
    }

    public void setVileMawKills(int vileMawKills) {
        this.vileMawKills = vileMawKills;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }
    
    public void addBan(BanTuple ban){
        bans.add(ban);
    }
}
