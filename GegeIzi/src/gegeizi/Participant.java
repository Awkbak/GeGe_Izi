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
public class Participant {
    
    private int participantId;
    private int championId;
    private int teamId;
    private String highestTeir;
    private ArrayList<RuneMastery> masteries;
    private ArrayList<RuneMastery> runes;
    private int spell1Id;
    private int spell2Id;
    private ParticipantTimeline timeline;
    private Stats stats;
    
    
    public Participant(){
        masteries = new ArrayList<>();
        runes = new ArrayList<>();
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getHighestTeir() {
        return highestTeir;
    }

    public void setHighestTeir(String highestTeir) {
        this.highestTeir = highestTeir;
    }

    public ArrayList<RuneMastery> getMasteries() {
        return masteries;
    }

    public void setMasteries(ArrayList<RuneMastery> masteries) {
        this.masteries = masteries;
    }

    public ArrayList<RuneMastery> getRunes() {
        return runes;
    }

    public void setRunes(ArrayList<RuneMastery> runes) {
        this.runes = runes;
    }

    public int getSpell1Id() {
        return spell1Id;
    }

    public void setSpell1Id(int spell1Id) {
        this.spell1Id = spell1Id;
    }

    public int getSpell2Id() {
        return spell2Id;
    }

    public void setSpell2Id(int spell2Id) {
        this.spell2Id = spell2Id;
    }
    
    public void addMastery(RuneMastery mastery){
        masteries.add(mastery);
    }
    
    public void addRune(RuneMastery rune){
        runes.add(rune);
    }

    public ParticipantTimeline getTimeline() {
        return timeline;
    }

    public void setTimeline(ParticipantTimeline timeline) {
        this.timeline = timeline;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }
    
    
}
