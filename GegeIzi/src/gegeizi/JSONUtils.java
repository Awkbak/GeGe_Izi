/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gegeizi;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author user
 */
public class JSONUtils {
    public static class ChampionParser{
        
        
    }
    //Parse mainly JSON objects into java objects
    public static class MatchParser{
        public static Match parseMatch(String in){
            try {
                JSONObject obj = (JSONObject) new JSONParser().parse(in);
                return new Match(obj); //Match and its inner classes all have a constructor for a JSONObject
                //return new Match(obj);
            } catch (ParseException ex) {
                Logger.getLogger(JSONUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return null;
        }
        
        public static ArrayList<Long> parseIds(String in){
            ArrayList<Long> ids = new ArrayList<>();
            //Split the returned (non-json) list into just spaces and longs
            String[] arr = in.split("[ |,|\n|\\[|\\]]"); //Split by ' ', '\n', '[', ']'
            //Add all longs into the ids ArrayList
            for(String e : arr){
                if(e.length() > 0){ //Make sure not to add spaces to the list
                    ids.add(new Long(e));
                }
            }
            return ids;
            
        }
    }
    
    public static class CurrentMatchParser{
        
    }
    
    
}
