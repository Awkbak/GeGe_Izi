/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gegeizi;

import java.util.ArrayList;

/**
 *
 * @author Awkbak
 */
public class Song {
    private ArrayList<Event> allEvents;
    private int Tempo;
    
    public Song(){
        allEvents = new ArrayList();
        Tempo = 60;
    }
    public Song(ArrayList<Event> o){
        allEvents = o;
        Tempo = 60;
    }
    public Song(ArrayList<Event> o, int i){
        allEvents = o;
        Tempo = i;
    }

    public ArrayList<Event> getAllEvents() {
        return allEvents;
    }

    public void setAllEvents(ArrayList<Event> allEvents) {
        this.allEvents = allEvents;
    }

    public int getTempo() {
        return Tempo;
    }

    public void setTempo(int Tempo) {
        this.Tempo = Tempo;
    }
    
    //Here we go babyyyyyyyyyy
    public void playSong(){
        
    }
    
}
