/**©Awkbak BR, Bobjrsenior
 * º⌐⌐º
 * 
 * Sounds of URF
 * Goal: To create an interactive Application that generates a sound sequence based off the outcome of game IDs.
 * Description: Imports game data from a 'League of Legends' match using Riot Games API.
 * Then it proceeds to layout all game events in a sort of sheet music.
 * The Keys correspond to each champion/player in the selected match.
 * The Keys will be played according to their respective events in the match.
 * 
 * Start Date: 3/27/2015
 * End Date: 4/17/2015
 */
package gegeizi;

import org.json.simple.JSONObject;

/**
 *
 * @author Awkbak BR, Bobjrsenior
 */
public class ParticipantTimelineTuple {
    private double tenToTwenty;
    private double zeroToTen;
    
    public ParticipantTimelineTuple(){
        
    }
    
    public ParticipantTimelineTuple(JSONObject obj){
        //doubles
        if(obj.containsKey("tenToTwenty")){
            tenToTwenty = (double) obj.get("tenToTwenty");
        }
        if(obj.containsKey("zeroToTen")){
            zeroToTen = (double) obj.get("zeroToTen");
        }
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
