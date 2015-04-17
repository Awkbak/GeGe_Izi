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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
//All the JavaFX imports are directly related to creation and manipulation of the GUI.
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.net.ssl.HttpsURLConnection;



/**
 * Starts up and handles most of the operations of the Application.
 * 
 * @author Awkbak, BobJrSenior
 */
public class Main extends Application {
    final int numevents = 7; //Number of Possible events. Change as needed.
    Group allBoxes = new Group();//UI element that will hold all checkboxes.
    ProgressBar songPlaying; //UI element shoing whether a song is currently playing/loading or not
    ComboBox pickMatches; //UI element containing the match ids to choose from
    Keyboard mainKeyboard; //UI element that will hold and process all the d;
    ComboBox pickInstrument;    boolean[] eventTypes; //What events should be played in the song?
    ImageView spinning; //The urf disk in the UI
    int songTempo; //How fast the song is played
    long matchLength = 0; //How long the imported match lasts
    
    TextField inTempo; //The UI component holding the tempo
    String[] instruents;
    long[] matchIdList; //List of predefined match ids
    ArrayList<Match> matches; //A list of currently loaded matches
    ObservableList<Long> matchIds; //A list of currently loaded match ids
    ObservableList<String> instrumentTypes;
    boolean callingAPI; //Is the api currently being used?
    boolean isPlaying = false; //Whether the song is playing.
    PlaySong songRunnable = null;
    
    RotateTransition spinAnim; 
    
    /**
     * Initializes all checkboxes for the user to choose which events they want to hear.
     */
    public void initBoxes(){
        eventTypes = new boolean[numevents];
        //All event types you can choose from
        String[] eve = {"Champion Kill","Building Kill","Skill level-up","Item Purchased","Ward Placed","Item Destroyed","Dragon/Baron Kill"};
        
        //Create checkbox UI for all eventTypes
        for(int i=0;i<numevents;i++){
            CheckBox k = new CheckBox(eve[i]);
            k.relocate(125,100+40*i);
            k.setFont(new Font("Cambria",14));
            if(i==0)
            k.setSelected(true);
            else
                k.setSelected(false);
            allBoxes.getChildren().add(k);
            k.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                int x = allBoxes.getChildren().indexOf(k);
                eventTypes[x] = newValue;
            });
            if(i==0)
            eventTypes[i] = true;
            else
                eventTypes[i] = false;
        }
    }
    /**
     * Initialize the keyboard with their corresponding sounds.
     * In This case, we need 10 teemos as a default.
     * 
     */
    public void initKeyboard(){
        String[] fileName = new String[10];
        int[] o = {17,17,17,17,17,17,17,17,17,17};
        for(int i =0;i<10;i++){
            String s = "" + i;
            fileName[i] = s;
        }
        mainKeyboard = new Keyboard(fileName,o);
    }
    /**
     * Creates an animation for the URF disk to spin while your hot mixtape is playing.
     * Uses the Javafx RotateTransition as a means of rotation.
     * Called whenever the song begins.
     * REQUIRES: song tempo, and match length
     */
    public void spinDisk(){
        int cycle = 2000;
        try{
            songTempo = Integer.parseInt(inTempo.getText());
        }catch(Exception e){
            songTempo = 50;
        }
        int ncycles = (int)matchLength * 1000 / (cycle*songTempo);
        spinAnim = new RotateTransition(Duration.millis(cycle), spinning);
        spinAnim.setFromAngle(0);
        spinAnim.setToAngle(360);
        spinAnim.setCycleCount(ncycles);
        spinAnim.setInterpolator(Interpolator.LINEAR);
        spinAnim.setAutoReverse(false);
        spinAnim.playFromStart();
    }
    /**
     * Creates an information pop-up for whenever the InfoButton is clicked.
     * Informs the user on how to use the Application.
     * @param primaryStage 
     */
    public void showInfo(Stage primaryStage){
        Stage dialog = new Stage();
        dialog.setTitle("Instructions");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        StackPane dbox = new StackPane();
        Label instruct = new Label("This program is designed to turn League matches into music.\nEach event on the checkboxes corresponds to what"
                + " is played.\nEvery time to event occurs, the key of the corresponding champion will be played.\n"
                + "1) Select which events become notes.\n"
                + "2) Select Tempo in which the song is played\n"
                + "3) Select the Match ID from the match that will be transformed.\n"
                + "4) Press the TriURFant button to hear the magic happen.\n"
                + "5) If you don't like what you hear, just press the button again to stop it.");
        instruct.setFont(new Font("Cambria",14));
        instruct.textAlignmentProperty().set(TextAlignment.CENTER);
        dbox.getChildren().add(instruct);
        Scene dScene = new Scene(dbox, 500, 200);
        dialog.setScene(dScene);
        dialog.show();
    }
    
    @Override
    /**
     * Initiates the GUI as well as their event listeners.
     * 
     * @param primaryStage
     */
    public void start(Stage primaryStage) {
        //List of predefined matchids
        matchIdList = new long[]{1790884442,1790381478,1791238379, 1791239062, 1791239372, 1791266755, 1791267114, 1791267187, 1791267641, 1791362112, 1791237335, 1791238434, 1791265938, 1791267178, 1791267489, 1791267843, 1791267494, 1791268565, 1791269062, 1791269195, 1791365109, 1791267806, 1791269119, 1791269160, 1791366505, 1791267491, 1791269575, 1791335941, 1791218726, 1791237424, 1791237745, 1791238646, 1791238709, 1791238910, 1791263069, 1791219410, 1791236314, 1791237040, 1791237332, 1791238460, 1791238840, 1791239703, 1791219622, 1791237286, 1791237665, 1791238981, 1791239047, 1791265470, 1791238602, 1791264975, 1791237995, 1791239487, 1791215810, 1791217900, 1791235128, 1791235522, 1791236189, 1791237093, 1791237429, 1791237539, 1791237628, 1791237694, 1791237722, 1791238625, 1791332815, 1791215988, 1791216999, 1791218195, 1791219023, 1791219108, 1791234900, 1791236766, 1791237054, 1791237108, 1791238382, 1791238597, 1791239692, 1791218446, 1791236730, 1791237362, 1791237588, 1791238836, 1791238972, 1791238995, 1791239096, 1791239252, 1791239298, 1791215634, 1791235161, 1791238012, 1791238534, 1791238642, 1791238830, 1791238885, 1791239073, 1791239105, 1791239238, 1791239264, 1791239301, 1791239396, 1791239552, 1791334888, 1791217253, 1791219723, 1791235062, 1791237057, 1791237734, 1791238692, 1791238967, 1791239309, 1791239584, 1791214897, 1791215058, 1791215446, 1791216467, 1791216532, 1791216587, 1791217923, 1791218531, 1791218674, 1791219026, 1791219116, 1791219278, 1791219658, 1791219731, 1791231666, 1791232350, 1791233176, 1791215237, 1791216586, 1791216777, 1791217254, 1791217393, 1791217478, 1791218390, 1791218541, 1791218981, 1791219036, 1791219100, 1791219162, 1791219490, 1791219601, 1791219715, 1791233450, 1791215302, 1791217468, 1791218274, 1791218549, 1791218651, 1791219028, 1791219186, 1791219287, 1791235422, 1791269676, 1791236780, 1791216349, 1791218440, 1791218670, 1791218702, 1791218966, 1791219203, 1791219638, 1791235116, 1791216054, 1791218550, 1791219051, 1791219166, 1791219390, 1791219732, 1791235975, 1791236538, 1791236593, 1791236784, 1791212587, 1791215064, 1791215134, 1791215625, 1791216129, 1791216255, 1791216610, 1791216664, 1791216785, 1791217013, 1791217410, 1791217839, 1791217965,1793129699,1793549721,};

        String[] instruments = {"Acoustic Piano", "Bright Piano", "Xylophone", "Honky Piano", "Melodic Drum", "Harpsichord"};

        Image j = new Image(getClass().getResourceAsStream("UrfDisk.png"));
        spinning = new ImageView(j);
        spinning.relocate(300, 70);//sets up the spinning disk
        
        initBoxes();
        
        matches = new ArrayList<>();//gathers match IDS
        matchIds = FXCollections.observableArrayList();
        for(long e : matchIdList){
            matchIds.add(e);
        }
        
        instrumentTypes = FXCollections.observableArrayList();
        instrumentTypes.addAll(Arrays.asList(instruments));        
        
        callingAPI = false;
        
        pickMatches = new ComboBox(matchIds);//creates a dropbox for picking match IDs
        pickMatches.relocate(580, 300);
        pickMatches.getSelectionModel().select(0);
        
        Label instrumentLabel = new Label("Instruments: ");
        instrumentLabel.setFont(new Font("Cambria",14));
        instrumentLabel.relocate(490,355);
        
        pickInstrument = new ComboBox(instrumentTypes);
        pickInstrument.getSelectionModel().select(0);
        pickInstrument.relocate(570, 350);
        //Set on change listener to Instrument list
        pickInstrument.setOnAction((event) -> {
            //CHange what instrument will play
            mainKeyboard.setInstrument((String) pickInstrument.getSelectionModel().getSelectedItem());
        });
        
        Label elList = new Label("Match ID: ");
        elList.setFont(new Font("Cambria",14));
        elList.relocate(510, 305);
        
        Image im = new Image(getClass().getResourceAsStream("Info.png"));
        Rectangle getInfo = new Rectangle(690, 70, 32, 32);//Creates the info button for showing info
        ImagePattern s = new ImagePattern(im);
        getInfo.setFill(s);
        
        getInfo.setOnMouseClicked((MouseEvent e)->{//When the info button is clicked
            showInfo(primaryStage);
        });
        
        inTempo = new TextField();//TextBox for inputting song Tempo
        inTempo.setPrefSize(75, 30);
        inTempo.relocate(610,250); 
        inTempo.setText("50");
        
        Label elTempo = new Label("Tempo: ");
        elTempo.setFont(new Font("Cambria",14));
        elTempo.relocate(560,255);
        
        songPlaying = new ProgressBar(0);//Indicator that the song is loading/playing
        songPlaying.relocate(100, 550);
        songPlaying.setPrefSize(600, 20);
        
        initKeyboard();//Initialize Keyboard
        
        Image img = new Image(getClass().getResourceAsStream("Triurfant.png"));
        Button btn = new Button("",new ImageView(img));//TriURFant button for playing the song.
        
        btn.relocate(350, 290);
        btn.setOnAction((ActionEvent event) -> {
            //Are their matchids loaded and a song isn't already playing?
            if(matchIds.size() > 0 && !isPlaying){
                //get the match info for the selection matchId
                getMatch(pickMatches.getSelectionModel().getSelectedIndex());
                songPlaying.setProgress(-1);
            }
            else if(isPlaying){
                songRunnable.terminate();
            }
        });
        
        
        Pane root = new Pane();
        //Add everything to the UI
        root.getChildren().addAll(mainKeyboard,pickMatches,pickInstrument,spinning,btn,inTempo,elTempo,elList,allBoxes,songPlaying,getInfo
        ,instrumentLabel);
        
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
        //Let the Show begin
        primaryStage.show();

    }
    
    //Starts up the program
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        if(songRunnable != null){
            songRunnable.terminate();
        }
        super.stop();
    }
    
    
    /**
     * Play a song based on events in a League Match
     */
    class PlaySong implements Runnable{

        /**
         * Referes to what champion preformed an action
         */
        int sound = 1;
        boolean keepPlaying = true;
        
        //End the song/Close the thread
        public void terminate(){
            keepPlaying = false;
        }
        
        @Override
        public void run() {
            isPlaying = true;
            try{
                songTempo = Integer.parseInt(inTempo.getText());
            }catch(Exception e){
                songTempo = 50;
            }
            
            //Get all events
            ArrayList<Event> events = matches.get(0).getEventList();
            matches.remove(0);
            
            spinDisk();

            //Start the play timer
            long time = System.currentTimeMillis();
            //Go through every event
            for(Event e : events){
                if(!keepPlaying){
                    //Finish the progress bar on JavaFX Thread
                    Platform.runLater(()->{
                        songPlaying.setProgress(1);
                        spinAnim.stop();
                    });
                    isPlaying = false;
                    //Close the synths to immediately stop playing sounds
                    for(Key key : mainKeyboard.getBoard()){
                        key.closeSynth();
                    }
                    return;
                }
                //Wait until the sound is ready to play
                while(System.currentTimeMillis() - time < e.getTimeStamp() / songTempo){
                    //End the song if thread is called to terminate
                    if(!keepPlaying){
                        //Finish the progress bar on JavaFX Thread
                        Platform.runLater(()->{
                            songPlaying.setProgress(1);
                            spinAnim.stop();
                        });
                        isPlaying = false;
                        for(Key key : mainKeyboard.getBoard()){
                            key.closeSynth();
                        }
                        return;
                    }
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
                    //Get the sound/key to play based on participantId
                    sound = (int) e.getParticipantId() - 1;
                    
                    //If it's not a minion kill, play it
                    if(sound != -1){
                        mainKeyboard.PlaySound(sound);
                    }
                }
            }
            //Wait 2 seconds before closing synths
            //Avoids a sudden cutoff
            time = System.currentTimeMillis() + 2000;
            while(time > System.currentTimeMillis()){}
            for(Key key : mainKeyboard.getBoard()){
                key.closeSynth();
            }
            //Finish the progress bar on JavaFX Thread
            Platform.runLater(()->{
                songPlaying.setProgress(1);
            });
            isPlaying = false;            
        }
        
    }
    
    /**
     * Get a match by its id 
     * Callback = recievedMatch
     * @param matchId MatchId to retrieve data for
     */
    public void getMatch(long matchId){
        GetMatchInfo call = new GetMatchInfo("https://na.api.pvp.net/api/lol/na/v2.2/match/" + matchId + "?includeTimeline=true&api_key=" + ApiKey.API_KEY);
        //threadpool.execute(call);
        Thread th = new Thread(call);
        th.start();
    }
    
    /**
     * Get a match by its index in matchIds
     * Callback = recievedMatch
     * @param index Index within matchIds that contains the match Id
     */
    public void getMatch(int index){
        getMatch(matchIds.get(index));
    }
    
    //
    /**
     * Get Match ids from a certain time bucket in the Riot API
     * Callback = recievedMatchIds
     * @param time in epoch seconds (unix time in seconds) and must be in a multiple of 5 minutes
     */
    public void getMatchIds(long time){
        if(!callingAPI){
            GetIds call = new GetIds("https://na.api.pvp.net/api/lol/na/v4.1/game/ids?beginDate=" + time + "&api_key=" + ApiKey.API_KEY);
            Thread th = new Thread(call);
            th.start();
        }
        
    }
    
    
    /**
     * Gets a list of match Ids
     * Uses a generic time
     */
    public void getMatchIds(){
        long epoch = 1428198000;
        epoch = epoch + (((int) (Math.random() * 10)) * 300);
        getMatchIds(epoch);
    }
    
    
    /**
     * Called whenever a match is constructed from the api
     * @param match The match containing all of the data retrieved
     */
    public void recievedMatch(Match match){
        matchLength = match.getMatchDuration();
        System.out.println("Parsed Match");
        
        //Add match to the match list
        matches.add(match);
        callingAPI = false;
        
        //Get the keyboard and set the backgrounds to the corresponding champion splash art
        Key[] board = mainKeyboard.getBoard();
        ArrayList<Integer> champs = match.getChampionIds();
        for(int e = 0; e < board.length; e ++){
           board[e].setImage(champs.get(e));
        }
        
        //Play the song in its own thread
        songRunnable = new PlaySong();
        Thread th = new Thread(songRunnable);
        th.start();
    }
    
   
    /**
     * Called whenever a list of match ids is retrieved from the Riot API
     * @param ids The ids retrieved from the API
     */
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
    
    
    /**
     * Used to retrieve and parse a match through the Riot API
     * Requires a URL to call
     */
    class GetMatchInfo implements Runnable{
        
        /**
         * url to retrieve from2
        */
        public URL url;
        
        /**
         * @param url The URL to retrieve the Match Info from
         */
        public GetMatchInfo(String url){
            try {
                this.url = new URL(url);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        /**
         * Retrieves and Parses a Match from the Riot API
         * Uses url declared from the constructor
         */
        @Override
        public void run() {
            callingAPI = true;
            HttpsURLConnection con = null;
            try {
                //Open the connection
                con = (HttpsURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                int statusCode = con.getResponseCode();
                
                if(statusCode == HttpsURLConnection.HTTP_OK){
                    //Read in the result into a StringBuilder
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
                //If it fails (no internet connet/bad id, ect)
                //Reset the progress bar
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
    
    /**
     * Used to retrieve a list of Match Ids through the Riot API
     * Requires a URL to call
     */
    class GetIds implements Runnable{

        /**
         * url to retrieve from2
        */
        URL url;
        /**
         * Contains the list of ids retrieved from the Riot API
        */
        ArrayList<Long> ids;
        
        /**
         * @param url The URL to retrieve the Match Ids from
         */
        public GetIds(String url){
            try {
                this.url = new URL(url);
                //this.ids = ids;
            } catch (MalformedURLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        /**
         * Retrieves a list of match Ids from the Riot API 
         * The url to retrieve from should be initialized in the constructor
         */
        @Override
        public void run() {
            callingAPI = true;
            HttpsURLConnection con = null;
            ids = null;
            try {
                 //Open the connection
                con = (HttpsURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                int statusCode = con.getResponseCode();
                if(statusCode == HttpsURLConnection.HTTP_OK){
                    //Read in the result into a StringBuilder
                        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line = null;
                        while((line = reader.readLine()) != null){
                                sb.append(line);
                        }
                        //Parse the ids
                        ids = (JSONUtils.MatchParser.parseIds(sb.toString()));
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
