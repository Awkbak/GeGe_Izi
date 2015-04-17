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

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Awkbak BR, Bobjrsenior
 */
public class Frame {
    
    private ArrayList<Event> events;
    private ArrayList<ParticipantFrame> participantFrames;
    private long timeStamp;

    public Frame(){
        events = new ArrayList<>();
        participantFrames = new ArrayList<>();
    }
    
    public Frame(JSONObject obj){
        //Initialize ArrayLists
        events = new ArrayList<>();
        participantFrames = new ArrayList<>();
        
        //ints
        timeStamp = (long) obj.get("timestamp");
        //Arrays
        
        JSONArray arr;// = (JSONArray) obj.get("participantFrames");
        JSONObject obj2 = (JSONObject) obj.get("participantFrames");
        //System.out.println("G: " + (arr == null));
        for(int e = 1; e <= 10; e ++){
            participantFrames.add(new ParticipantFrame((JSONObject) obj2.get(e + "")));
        }
        //for (Object arr1 : arr) {
        //    participantFrames.add(new ParticipantFrame((JSONObject) arr1));
        //}
        if(obj.containsKey("events"))
        {
            arr = (JSONArray) obj.get("events");
            arr.stream().forEach((arr1) -> {
                events.add(new Event((JSONObject) arr1));
            });
        }
    }
    
    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public ArrayList<ParticipantFrame> getParticipantFrames() {
        return participantFrames;
    }

    public void setParticipantFrames(ArrayList<ParticipantFrame> participantFrames) {
        this.participantFrames = participantFrames;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    public void addEvent(Event event){
        events.add(event);
    }
    
    public void addParticipantFrame(ParticipantFrame participantFrame){
        participantFrames.add(participantFrame);
    }
    
}
