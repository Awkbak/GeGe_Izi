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
public class BanTuple {
    private int championId;
    private int pickTurn;
    
    public BanTuple(){
        
    }
    
    public BanTuple(JSONObject obj){
        //ints
        championId = (int) obj.get("championId");
        pickTurn = (int) obj.get("pickTurn");
    }

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public int getPickTurn() {
        return pickTurn;
    }

    public void setPickTurn(int pickTurn) {
        this.pickTurn = pickTurn;
    }
    
    
}
