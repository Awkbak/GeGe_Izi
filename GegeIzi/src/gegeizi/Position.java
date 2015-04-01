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
    private int x;
    private int y;
    
    public Position(int x, int y){
        this.y = x;
        this.y = y;
    }
    
    public Position(){
        
    }
    
    public Position(JSONObject obj){
        //ints
        x = (int) obj.get("x");
        y = (int) obj.get("y");
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}