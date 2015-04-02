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
public class Position {
    private long x;
    private long y;
    
    public Position(long x, long y){
        this.y = x;
        this.y = y;
    }
    
    public Position(){
        
    }
    
    public Position(JSONObject obj){
        //ints
        x = (long) obj.get("x");
        y = (long) obj.get("y");
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }
    
    
}
