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
    private int id;
    private int rank;
    
    public RuneMastery(){
        
    }
    
    public RuneMastery(JSONObject obj, String type){
        //ints
        rank = (int) obj.get("rank");
        //Make surte to find the right variable (class controls Runes and Masteries)
        if(type.equals("Rune")){
            id = (int) obj.get("runeId");
        }
        else{
            id = (int) obj.get("masteryId");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    
    
}
