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
