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
public class RuneMastery {
    private long id;
    private long rank;
    
    public RuneMastery(){
        
    }
    
    public RuneMastery(JSONObject obj, String type){
        //ints
        rank = (long) obj.get("rank");
        //Make surte to find the right variable (class controls Runes and Masteries)
        if(type.equals("Rune")){
            id = (long) obj.get("runeId");
        }
        else{
            id = (long) obj.get("masteryId");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }
    
    
}
