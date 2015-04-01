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
public class ParticipantTimelineTuple {
    private double tenToTwenty;
    private double zeroToTen;
    
    public ParticipantTimelineTuple(){
        
    }
    
    public ParticipantTimelineTuple(JSONObject obj){
        //doubles
        tenToTwenty = (double) obj.get("tenToTwenty");
        zeroToTen = (double) obj.get("zeroToTen");
    }

    public double getTenToTwenty() {
        return tenToTwenty;
    }

    public void setTenToTwenty(double tenToTwenty) {
        this.tenToTwenty = tenToTwenty;
    }

    public double getZeroToTen() {
        return zeroToTen;
    }

    public void setZeroToTen(double zeroToTen) {
        this.zeroToTen = zeroToTen;
    }
    
    
}
