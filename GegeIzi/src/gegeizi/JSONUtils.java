/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gegeizi;

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
    
    public static class MatchParser{
        public static Match parseMatch(String in){
            try {
                JSONObject obj = (JSONObject) new JSONParser().parse(in);
                Match match = new Match(obj);
                //return new Match(obj);
            } catch (ParseException ex) {
                Logger.getLogger(JSONUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return null;
        }
    }
    
    public static class CurrentMatchParser{
        
    }
}
