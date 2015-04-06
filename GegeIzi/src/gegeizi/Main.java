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
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.net.ssl.HttpsURLConnection;

/**
Completable Future Sample
* CompletableFuture<Summoner> F = CompletableFuture.supplyAsync(() ->{
            current = getInfo(i);
            return null;
        });
        F.whenComplete((ok,ex)->{
            Platform.runLater(()->{
                finished();
                update();
            });
        });
 */

/**
 * 
 * @author Awkbak
 */
public class Main extends Application {
    ComboBox pickMatches;
    Keyboard mainKeyboard;
    ImageView spinning;
    
    //ExecutorService threadpool; //Keeps too many threads from running
    ArrayList<Match> matches; //A list of currently loaded matches
    ObservableList<Long> matchIds; //A list of currently loaded match ids
    boolean callingAPI; //Is the api currently being used?
    
    public void initKeyboard(){
        String[] kek = new String[10];
        for(int i =0;i<10;i++){
            String s = "Key" + i + ".mp3";
            kek[i] = s;
        }
        mainKeyboard = new Keyboard(kek);
    }
    
    public void spinDisk(){
        for(int i = 0;i<3600;i++){
            spinning.setRotate(i);
            try {
                wait(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void start(Stage primaryStage) {
        Image j = new Image(getClass().getResourceAsStream("UrfDisk.png"));
        spinning = new ImageView(j);
        spinning.relocate(100, 100);
        
        /*CompletableFuture<Integer> F = CompletableFuture.supplyAsync(() ->{
            spinDisk();
            return null;
        });
        F.whenComplete((ok,ex)->{
            Platform.runLater(()->{
                
            });
        });*/
        //threadpool = Executors.newFixedThreadPool(4);
        matches = new ArrayList<>();
        matchIds = FXCollections.observableArrayList();
        callingAPI = false;
        
        pickMatches = new ComboBox(matchIds);
        pickMatches.relocate(550, 250);
        pickMatches.getSelectionModel().select(0);
        
        Label elList = new Label("Match ID: ");
        elList.setFont(new Font("Cambria",14));
        elList.relocate(490, 255);
        
        TextField inTempo = new TextField();
        inTempo.setPrefSize(75, 30);
        inTempo.relocate(550,200); 
        inTempo.setText("100");
        
        Label elTempo = new Label("Tempo: ");
        elTempo.setFont(new Font("Cambria",14));
        elTempo.relocate(500,205);
        
        initKeyboard();

        Image img = new Image(getClass().getResourceAsStream("Triurfant.jpg"));
        Button btn = new Button("",new ImageView(img));
        btn.relocate(550, 290);
        btn.setOnAction((ActionEvent event) -> {
            //Random s = new Random();
            //int n = s.nextInt(10);
            //mainKeyboard.PlaySound(n);

            
            //Make sure you have a match loaded before using
            //PlaySong song = new PlaySong();
            //Thread th1 = new Thread(song);
            //th1.start();
        });
        
                
        Pane root = new Pane();
        root.getChildren().addAll(mainKeyboard,pickMatches,spinning,btn,inTempo,elTempo,elList);
        
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
     
    class PlaySongConcept implements Runnable{
        
        int sound = 0;
        
        @Override
        public void run() {
            //Note the time is multiplied by tempo for testing
            //so everything isn't played at once/easier to make example times
            int tempo = 10; 
            ArrayList<Integer> testEvents = new ArrayList<>();
            testEvents.add(500);
            testEvents.add(750);
            testEvents.add(1000);
            testEvents.add(1100);
            testEvents.add(1200);
            testEvents.add(1500);
            testEvents.add(1550);
            long time = System.currentTimeMillis();
            //Go through every event
            for(Integer e : testEvents){ 
                //Wait until the sound is ready to play
                while(System.currentTimeMillis() - time < e * tempo){

                }
                //Change sonds every time (Will be getting sound based on event type or participantId)
                sound ++;
                //Sounds have to be played on the JavaFX thread. THis does that
                Platform.runLater(() -> {
                    mainKeyboard.PlaySound(sound);
                });
             }
        }
    }
    
    class PlaySong implements Runnable{

        int sound = 1;

        @Override
        public void run() {
            int tempo = 100;
            //Get all events
            ArrayList<Event> events = matches.get(0).getEventList();
            long time = System.currentTimeMillis();
            //Go through every event
            for(Event e : events){
                //Wait until the sound is ready to play
                while(System.currentTimeMillis() - time < e.getTimeStamp() / tempo){

                }
                //Get the sond based on participantId
                sound = (int) e.getParticipantId();
                //Play sound on JavaFX thread (Can only play a sound on that thread)
                Platform.runLater(() -> {
                    mainKeyboard.PlaySound(sound);
                });
            }
        }
        
    }
    
    //Get a match using the current time (rounded to 5 minutes)
    public void getMatch(){
        long epoch = 1427986200;
        
        //long epoch = System.currentTimeMillis() / 1000;
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
    
    //Called whenever a match is constructed from the api
    public void recievedMatch(Match match){
        System.out.println("Parsed Match");
        matches.add(match);
        callingAPI = false;
        //FXCollections.observableArrayList(matches)
    }
    //Called whenever a list of match ids is retrieved from the server
    public void recievedMatchIds(ArrayList<Long> ids){
        System.out.println("Got Match Ids");
        if(ids != null){
            matchIds.addAll(ids);
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
                        recievedMatch(JSONUtils.MatchParser.parseMatch(sb.toString()));
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
