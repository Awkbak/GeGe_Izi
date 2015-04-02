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
    private long championId;
    private long pickTurn;
    
    public BanTuple(){
        
    }
    
    public BanTuple(JSONObject obj){
        //ints
        championId = (long) obj.get("championId");
        pickTurn = (long) obj.get("pickTurn");
    }

    public long getChampionId() {
        return championId;
    }

    public void setChampionId(long championId) {
        this.championId = championId;
    }

    public long getPickTurn() {
        return pickTurn;
    }

    public void setPickTurn(long pickTurn) {
        this.pickTurn = pickTurn;
    }
    
    
}
