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



/**
 * 
 * @author Awkbak
 */
public class Main extends Application {
    final int numevents = 7; //Number of Possible events. Change as needed.
    Group allBoxes = new Group();
    ProgressBar songPlaying;
    ComboBox pickMatches;
    Keyboard mainKeyboard;
    boolean[] eventTypes;
    ImageView spinning;
    int songTempo;
    long matchLength = 0;
    String eventFilter;
    
    
    TextField inTempo;
    
    ArrayList<Match> matches; //A list of currently loaded matches
    ObservableList<Long> matchIds; //A list of currently loaded match ids

    boolean callingAPI; //Is the api currently being used?
    boolean isPlaying = false;
    
    public void initBoxes(){
        eventTypes = new boolean[numevents];
        String[] eve = {"Champion Kill","Building Kill","Skill level-up","Item Purchased","Ward Placed","Item Destroyed","Dragon/Baron Kill"};
        
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
    
    public void initKeyboard(){
        String[] kek = new String[10];
        int[] o = {11,3,6,12,4,5,9,8,7,10};
        for(int i =0;i<10;i++){
            String s = "Key" + i + ".mp3";
            kek[i] = s;
        }
        mainKeyboard = new Keyboard(kek,o);
    }
    
    public void spinDisk(){
        int cycle = 1000;
        int ncycles = (int)matchLength * 10 / cycle;
        Timeline n = new Timeline();
        n.setCycleCount(ncycles);
        n.setAutoReverse(false);
        n.getKeyFrames().add(new KeyFrame(Duration.millis(cycle),new KeyValue (spinning.rotateProperty(),360)));
        n.stop();
        n.play();
    }
    
    @Override
    public void start(Stage primaryStage) {
        Image j = new Image(getClass().getResourceAsStream("UrfDisk.png"));
        spinning = new ImageView(j);
        spinning.relocate(100, 100);
        
        //threadpool = Executors.newFixedThreadPool(4);
        
        initBoxes();
        
        matches = new ArrayList<>();
        matchIds = FXCollections.observableArrayList();
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

        //matches = new ArrayList<>();
        getMatchIds();
        
        Image img = new Image(getClass().getResourceAsStream("Triurfant.jpg"));
        Button btn = new Button("",new ImageView(img));
        
        btn.relocate(550, 290);
        btn.setOnAction((ActionEvent event) -> {
            //Make sure you have a match loaded before using
            if(matchIds.size() > 0 && !isPlaying){
                getMatch(pickMatches.getSelectionModel().getSelectedIndex());
                songPlaying.setProgress(-1);
            }
        });
        
                
        Pane root = new Pane();
        root.getChildren().addAll(mainKeyboard,pickMatches,spinning,btn,inTempo,elTempo,elList,allBoxes,songPlaying);
        
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Sounds of URF");
        primaryStage.setMaxHeight(650);
        primaryStage.setMaxWidth(810);
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(810);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    class PlaySong implements Runnable{

        int sound = 1;

        @Override
        public void run() {
            spinDisk();
            isPlaying = true;
            songTempo = Integer.parseInt(inTempo.getText());
            //Get all events
            ArrayList<Event> events = matches.get(0).getEventList();
            matches.remove(0);
            long time = System.currentTimeMillis();
            //Go through every event
            for(Event e : events){
                //Wait until the sound is ready to play
                while(System.currentTimeMillis() - time < e.getTimeStamp() / songTempo){

                }
                boolean play = false;
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
            Platform.runLater(()->{
                songPlaying.setProgress(1);
            });
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
            //threadpool.execute(new GetIds("https://na.api.pvp.net/api/lol/na/v4.1/game/ids?beginDate=" + time + "&api_key=" + ApiKey.API_KEY));
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
        matches.add(match);
        callingAPI = false;
        Key[] board = mainKeyboard.getBoard();
        ArrayList<Integer> champs = match.getChampionIds();
        for(int e = 0; e < board.length; e ++){
           board[e].setImage(champs.get(e));
        }
        PlaySong song = new PlaySong();
        Thread th1 = new Thread(song);
        th1.start();
        //FXCollections.observableArrayList(matches)
    }
    //Called whenever a list of match ids is retrieved from the server
    public void recievedMatchIds(ArrayList<Long> ids){
        System.out.println("Got Match Ids");
        if(ids != null){
            matchIds.addAll(ids);
            Platform.runLater(() -> {
                    pickMatches.getSelectionModel().select(0);
            });
            
        }
        callingAPI = false;
    }
    
    
    //Used get a matches information from the riot api
    class GetMatchInfo implements Runnable{
        //url to retrieve from
        //public RequestParams params;
        public URL url;
        
        public GetMatchInfo(String url){
            try {
                //this.params = params;
                this.url = new URL(url);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //
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
                    Platform.runLater(() -> {
                        recievedMatch(JSONUtils.MatchParser.parseMatch(sb.toString()));
                    });
                        
                }
            } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    callingAPI = false;
            }
            finally{
                if(con != null){
                    con.disconnect();
                }
                
            }
        }
    }
    //Used to get a set of match ids from the riot api
    class GetIds implements Runnable{

        URL url;
        
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
            ArrayList<Long> ids = null;
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
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    callingAPI = false;
            }
            finally{
                if(con != null){
                    con.disconnect();
                }
            }
            recievedMatchIds(ids);
        }
        
    }
    
}
