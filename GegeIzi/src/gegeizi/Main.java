/**©Awkbak BR, Bobjrsenior
 * Sounds of URF
 * Goal: To create an App that generates a sound sequence based off the outcome of game IDs
 * Using Riot's API to create this app.
 * Start Date: 3/27/2015
 */
package gegeizi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.net.ssl.HttpsURLConnection;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;



/**
 * 
 * @author Awkbak
 */
public class Main extends Application {
    final int numevents = 7; //Number of Possible events. Change as needed.
    Group allBoxes = new Group();
    ProgressBar songPlaying; //UI element shoing whether a song is currently playing/loading or not
    ComboBox pickMatches; //UI element containing the match ids to choose from
    Keyboard mainKeyboard;
    boolean[] eventTypes; //What events should be played in the song?
    ImageView spinning; //The urf disk in the UI
    int songTempo;
    long matchLength = 0;
    String eventFilter;    
    
    TextField inTempo; //The UI component holding the tempo
    long[] matchIdList; //List of predefined match ids
    ArrayList<Match> matches; //A list of currently loaded matches
    ObservableList<Long> matchIds; //A list of currently loaded match ids
    boolean callingAPI; //Is the api currently being used?
    boolean isPlaying = false;
    
    //Create checkboxes to determine what sounds to play in the song
    public void initBoxes(){
        eventTypes = new boolean[numevents];
        //All event types you can choose from
        String[] eve = {"Champion Kill","Building Kill","Skill level-up","Item Purchased","Ward Placed","Item Destroyed","Dragon/Baron Kill"};
        //Create checkbox UI for all eventTypes
        for(int i=0;i<numevents;i++){
            CheckBox k = new CheckBox(eve[i]);
            k.relocate(350,180+30*i);
            k.setSelected(true);
            allBoxes.getChildren().add(k);
            k.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                int x = allBoxes.getChildren().indexOf(k);
                eventTypes[x] = newValue;
            });
            eventTypes[i] = true;
        }
    }
    
    //Initialize the keyboard with ther corresponding sounds
    public void initKeyboard(){
        String[] fileName = new String[10];
        int[] o = {17,17,17,17,17,17,17,17,17,17};
        for(int i =0;i<10;i++){
            String s = "" + i;
            fileName[i] = s;
        }
        mainKeyboard = new Keyboard(fileName,o);
    }
    
    public void spinDisk(){
        int cycle = 2000;
        try{
            songTempo = Integer.parseInt(inTempo.getText());
        }catch(Exception e){
            songTempo = 100;
        }
        int ncycles = (int)matchLength * 1000 / (cycle*songTempo);
        Timeline n = new Timeline();
        n.setCycleCount(ncycles);
        n.setAutoReverse(false);
        n.getKeyFrames().add(new KeyFrame(Duration.millis(cycle),new KeyValue (spinning.rotateProperty(),360)));
        n.playFromStart();
    }
    
    @Override
    public void start(Stage primaryStage) {
        //List of predefined matchids
        matchIdList = new long[]{1791238379, 1791239062, 1791239372, 1791266755, 1791267114, 1791267187, 1791267641, 1791362112, 1791237335, 1791238434, 1791265938, 1791267178, 1791267489, 1791267843, 1791267494, 1791268565, 1791269062, 1791269195, 1791365109, 1791267806, 1791269119, 1791269160, 1791366505, 1791267491, 1791269575, 1791335941, 1791218726, 1791237424, 1791237745, 1791238646, 1791238709, 1791238910, 1791263069, 1791219410, 1791236314, 1791237040, 1791237332, 1791238460, 1791238840, 1791239703, 1791219622, 1791237286, 1791237665, 1791238981, 1791239047, 1791265470, 1791238602, 1791264975, 1791237995, 1791239487, 1791215810, 1791217900, 1791235128, 1791235522, 1791236189, 1791237093, 1791237429, 1791237539, 1791237628, 1791237694, 1791237722, 1791238625, 1791332815, 1791215988, 1791216999, 1791218195, 1791219023, 1791219108, 1791234900, 1791236766, 1791237054, 1791237108, 1791238382, 1791238597, 1791239692, 1791218446, 1791236730, 1791237362, 1791237588, 1791238836, 1791238972, 1791238995, 1791239096, 1791239252, 1791239298, 1791215634, 1791235161, 1791238012, 1791238534, 1791238642, 1791238830, 1791238885, 1791239073, 1791239105, 1791239238, 1791239264, 1791239301, 1791239396, 1791239552, 1791334888, 1791217253, 1791219723, 1791235062, 1791237057, 1791237734, 1791238692, 1791238967, 1791239309, 1791239584, 1791214897, 1791215058, 1791215446, 1791216467, 1791216532, 1791216587, 1791217923, 1791218531, 1791218674, 1791219026, 1791219116, 1791219278, 1791219658, 1791219731, 1791231666, 1791232350, 1791233176, 1791215237, 1791216586, 1791216777, 1791217254, 1791217393, 1791217478, 1791218390, 1791218541, 1791218981, 1791219036, 1791219100, 1791219162, 1791219490, 1791219601, 1791219715, 1791233450, 1791215302, 1791217468, 1791218274, 1791218549, 1791218651, 1791219028, 1791219186, 1791219287, 1791235422, 1791269676, 1791236780, 1791216349, 1791218440, 1791218670, 1791218702, 1791218966, 1791219203, 1791219638, 1791235116, 1791216054, 1791218550, 1791219051, 1791219166, 1791219390, 1791219732, 1791235975, 1791236538, 1791236593, 1791236784, 1791212587, 1791215064, 1791215134, 1791215625, 1791216129, 1791216255, 1791216610, 1791216664, 1791216785, 1791217013, 1791217410, 1791217839, 1791217965};
        

        Image j = new Image(getClass().getResourceAsStream("UrfDisk.png"));
        spinning = new ImageView(j);
        spinning.relocate(100, 100);
        
        //threadpool = Executors.newFixedThreadPool(4);
        
        initBoxes();
        
        matches = new ArrayList<>();
        matchIds = FXCollections.observableArrayList();
        for(long e : matchIdList){
            matchIds.add(e);
        }
        
        callingAPI = false;
        
        pickMatches = new ComboBox(matchIds);
        pickMatches.relocate(550, 250);
        pickMatches.getSelectionModel().select(0);
        
        Label elList = new Label("Match ID: ");
        elList.setFont(new Font("Cambria",14));
        elList.relocate(490, 255);
        
        inTempo = new TextField();
        inTempo.setPrefSize(75, 30);
        inTempo.relocate(550,200); 
        inTempo.setText("100");
        
        Label elTempo = new Label("Tempo: ");
        elTempo.setFont(new Font("Cambria",14));
        elTempo.relocate(500,205);
        
        songPlaying = new ProgressBar(0);
        songPlaying.relocate(100, 550);
        songPlaying.setPrefSize(600, 20);
        
        initKeyboard();

        
        Image img = new Image(getClass().getResourceAsStream("Triurfant.jpg"));
        Button btn = new Button("",new ImageView(img));
        
        btn.relocate(550, 290);
        btn.setOnAction((ActionEvent event) -> {
            //Are their matchids loaded and a song isn't already playing?
            if(matchIds.size() > 0 && !isPlaying){
                //get the match info for the selection matchId
                getMatch(pickMatches.getSelectionModel().getSelectedIndex());
                songPlaying.setProgress(-1);
            }
        });
        
        
        Pane root = new Pane();
        //Add everything to the UI
        root.getChildren().addAll(mainKeyboard,pickMatches,spinning,btn,inTempo,elTempo,elList,allBoxes,songPlaying);
        
        //Create the scene and set it's properties
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        //Set the Stage properties
        primaryStage.setTitle("Sounds of URF");
        primaryStage.setMaxHeight(650);
        primaryStage.setMaxWidth(810);
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(810);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    

    public static void main(String[] args) {
        launch(args);
    }
    
    class PlaySong implements Runnable{

        int sound = 1;

        @Override
        public void run() {
            isPlaying = true;
            try{
                songTempo = Integer.parseInt(inTempo.getText());
            }catch(Exception e){
                songTempo = 100;
            }
            //Get all events
            ArrayList<Event> events = matches.get(0).getEventList();
            matches.remove(0);
            spinDisk();
           
            long time = System.currentTimeMillis();
            //Go through every event
            for(Event e : events){
                //Wait until the sound is ready to play
                while(System.currentTimeMillis() - time < e.getTimeStamp() / songTempo){

                }
                boolean play = false;
                //Only play a sound if the certain event type is checked in the UI
                if(e.getEventType().equals("CHAMPION_KILL") && eventTypes[0]){
                    play = true;
                }
                else if(e.getEventType().equals("BUILDING_KILL") && eventTypes[1]){
                    play = true;
                }
                else if(e.getEventType().equals("SKILL_LEVEL_UP") && eventTypes[2]){
                    play = true;
                }
                else if(e.getEventType().equals("ITEM_PURCHASED") && eventTypes[3]){
                    play = true;
                }
                else if(e.getEventType().equals("WARD_PLACED") && eventTypes[4]){
                    play = true;
                }
                else if(e.getEventType().equals("ITEM_DESTROYED") && eventTypes[5]){
                    play = true;
                }
                else if(e.getEventType().equals("ELITE_MONSTER_KILL") && eventTypes[6]){
                    play = true;
                }
                if(play){
                    //Get the sond based on participantId
                    sound = (int) e.getParticipantId() - 1;
                    if(sound == -1){
                        sound = 0;
                    }
                    //Play sound on JavaFX thread (Can only play a sound on that thread)
                    Platform.runLater(() -> {
                        mainKeyboard.PlaySound(sound);
                    });
                }
            }
            //Finish the progress bar
            Platform.runLater(()->{
                songPlaying.setProgress(1);
            });
            //Close the midi synth on keys
            for(Key e : mainKeyboard.getBoard()){
                e.closeSynth();
            }
            isPlaying = false;            
        }
        
    }
    
    //Get a match by its id
    public void getMatch(long matchId){
        GetMatchInfo call = new GetMatchInfo("https://na.api.pvp.net/api/lol/na/v2.2/match/" + matchId + "?includeTimeline=true&api_key=" + ApiKey.API_KEY);
        //threadpool.execute(call);
        Thread th = new Thread(call);
        th.start();
    }
    
    //Get a match by its index in matchIds
    public void getMatch(int index){
        getMatch(matchIds.get(index));
    }
    
    //Note: the time is in epoch seconds (unix time in seconds) and must be in a multiple of 5 minutes
    public void getMatchIds(long time){
        if(!callingAPI){
            GetIds call = new GetIds("https://na.api.pvp.net/api/lol/na/v4.1/game/ids?beginDate=" + time + "&api_key=" + ApiKey.API_KEY);
            Thread th = new Thread(call);
            th.start();
        }
        
    }
    
    public void getMatchIds(){
        long epoch = 1428198000;
        epoch = epoch + (((int) (Math.random() * 10)) * 300);
        getMatchIds(epoch);
        
    }
    
    //Called whenever a match is constructed from the api
    public void recievedMatch(Match match){
        matchLength = match.getMatchDuration();
        System.out.println("Parsed Match");
        //Add match to the match list
        matches.add(match);
        callingAPI = false;
        
        //Get the keyboard and set the backgrounds to the corresponding champino image
        Key[] board = mainKeyboard.getBoard();
        ArrayList<Integer> champs = match.getChampionIds();
        for(int e = 0; e < board.length; e ++){
           board[e].setImage(champs.get(e));
        }
        
        //Play the song in a new thread
        PlaySong song = new PlaySong();
        Thread th1 = new Thread(song);
        th1.start();
    }
    //Called whenever a list of match ids is retrieved from the server
    public void recievedMatchIds(ArrayList<Long> ids){
        System.out.println("Got Match Ids");
        
        if(ids != null){
            //Add the ids to the list
            matchIds.addAll(ids);
            //Select the first id (Makes sure you don't have a blank spot selected)
            
            pickMatches.getSelectionModel().select(0);
            
        }
        callingAPI = false;
    }
    
    
    //Used get a matches information from the riot api
    class GetMatchInfo implements Runnable{
        
        //url to retrieve from
        public URL url;
        
        public GetMatchInfo(String url){
            try {
                this.url = new URL(url);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        @Override
        public void run() {
            callingAPI = true;
            HttpsURLConnection con = null;
            try {
                con = (HttpsURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                int statusCode = con.getResponseCode();
                if(statusCode == HttpsURLConnection.HTTP_OK){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while((line = reader.readLine()) != null){
                            sb.append(line);
                    }
                    //Call callback on main (JavaFX) thread
                    Platform.runLater(() -> {
                        recievedMatch(JSONUtils.MatchParser.parseMatch(sb.toString()));
                    });
                        
                }
            } catch (IOException e) {
                //e.printStackTrace();
                callingAPI = false;
                Platform.runLater(() -> {
                    songPlaying.setProgress(1);
                });                    
            }
            finally{
                //Make sure to disconnect when done
                if(con != null){
                    con.disconnect();
                }
            }
        }
    }
    //Used to get a set of match ids from the riot api
    class GetIds implements Runnable{

        URL url;
        ArrayList<Long> ids;
        
        public GetIds(String url){
            try {
                this.url = new URL(url);
                //this.ids = ids;
            } catch (MalformedURLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        @Override
        public void run() {
            callingAPI = true;
            HttpsURLConnection con = null;
            ids = null;
            try {
                con = (HttpsURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                int statusCode = con.getResponseCode();
                if(statusCode == HttpsURLConnection.HTTP_OK){
                        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line = null;
                        while((line = reader.readLine()) != null){
                                sb.append(line);
                        }
                        ids = (JSONUtils.MatchParser.parseIds(sb.toString()));
                        System.out.println("after recieve");
                }
            } catch (IOException e) {
                    //e.printStackTrace();
                    callingAPI = false;
            }
            finally{
                //Make sure to disconnect when done
                if(con != null){
                    con.disconnect();
                }
            }
            //Call callback on main (JavaFX) thread
            Platform.runLater(() -> {
                recievedMatchIds(ids);
            });
        }
        
    }
    
}
