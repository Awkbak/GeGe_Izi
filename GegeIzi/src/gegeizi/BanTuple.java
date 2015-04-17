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
 * Gets the championIDs and the turn they were picked from the JSON object
 * @author Awkbak BR, BobJrSenior
 */
public class BanTuple {
    private final long championId;
    private final long pickTurn;

    /**
     * Constructor using the JSON Object.
     * @param obj 
     */
    public BanTuple(JSONObject obj){
        championId = (long) obj.get("championId");
        pickTurn = (long) obj.get("pickTurn");
    }

    public long getChampionId() {
        return championId;
    }

    public long getPickTurn() {
        return pickTurn;
    }
    
}
