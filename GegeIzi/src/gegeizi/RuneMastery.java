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
public class RuneMastery {
    private long id;
    private long rank;
    
    public RuneMastery(){
        
    }
    
    public RuneMastery(JSONObject obj, String type){
        //ints
        rank = (long) obj.get("rank");
        //Make surte to find the right variable (class controls Runes and Masteries)
        if(type.equals("Rune")){
            id = (long) obj.get("runeId");
        }
        else{
            id = (long) obj.get("masteryId");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }
    
    
}
