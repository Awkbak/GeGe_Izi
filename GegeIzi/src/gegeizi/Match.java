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
public class Match {
    private long mapId;
    private long matchCreation;
    private long matchDuration;
    private long matchId;
    private String matchMode;
    private String matchType;
    private String matchVersion;
    private ArrayList<Participant> participants;
    private String platformId;
    private String queueType;
    private String region;
    private String season;
    private ArrayList<Team> teams;
    private MatchTimeline timeline;
   
    public Match(){
        //Initialize ArrayLists
        teams = new ArrayList<>();
        participants = new ArrayList<>();
    }
    
    public Match(JSONObject obj){
        //Initialize ArrayLists
        teams = new ArrayList<>();
        participants = new ArrayList<>();
        //longs
        matchDuration = (long) obj.get("matchDuration");
        mapId = (long) obj.get("mapId");
        matchCreation = (long) obj.get("matchCreation");
        matchId = (long) obj.get("matchId");
        //String
        matchMode = (String) obj.get("matchMode");
        matchType = (String) obj.get("matchType");
        matchVersion = (String) obj.get("matchVersion");
        platformId = (String) obj.get("platformId");
        queueType = (String) obj.get("queueType");
        region = (String) obj.get("region");
        season = (String) obj.get("season");
        //Objects
        timeline = new MatchTimeline((JSONObject) obj.get("timeline"));
        //Arrays
        JSONArray arr = (JSONArray) obj.get("participants");
        for (Object arr1 : arr) {
            participants.add(new Participant((JSONObject) arr1));
        }
        
        arr = (JSONArray) obj.get("teams");
        for (Object arr1 : arr) {
            teams.add(new Team((JSONObject) arr1));
        }
    }

    public long getMapId() {
        return mapId;
    }

    public void setMapId(long mapId) {
        this.mapId = mapId;
    }

    public long getMatchCreation() {
        return matchCreation;
    }

    public void setMatchCreation(long matchCreation) {
        this.matchCreation = matchCreation;
    }

    public long getMatchDuration() {
        return matchDuration;
    }

    public void setMatchDuration(long matchDuration) {
        this.matchDuration = matchDuration;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public String getMatchMode() {
        return matchMode;
    }

    public void setMatchMode(String matchMode) {
        this.matchMode = matchMode;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getMatchVersion() {
        return matchVersion;
    }

    public void setMatchVersion(String matchVersion) {
        this.matchVersion = matchVersion;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public MatchTimeline getTimeline() {
        return timeline;
    }

    public void setTimeline(MatchTimeline timeline) {
        this.timeline = timeline;
    }
    
    public void addParticipant(Participant participant){
        participants.add(participant);
    }
    
    public void addTeam(Team team){
        teams.add(team);
    }
}
