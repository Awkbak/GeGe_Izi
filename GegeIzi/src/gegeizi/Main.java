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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
    Keyboard mainKeyboard;
    
    ExecutorService threadpool; //Keeps too many threads from running
    ArrayList<Match> matches; //A list of currently loaded matches
    ArrayList<Long> matchIds; //A list of currently loaded match ids
    boolean callingAPI; //Is the api currently being used?
    
    public void initKeyboard(){
        String[] kek = new String[10];
        for(int i =0;i<10;i++){
            String s = "Key" + i + ".mp3";
            kek[i] = s;
        }
        mainKeyboard = new Keyboard(kek);
    }
    
    @Override
    public void start(Stage primaryStage) {
        Image j = new Image(getClass().getResourceAsStream("UrfDisk.png"));
        ImageView m = new ImageView(j);
        m.relocate(100, 100);
        threadpool = Executors.newFixedThreadPool(4);
        matches = new ArrayList<>();
        matchIds = new ArrayList<>();
        callingAPI = false;
        
        initKeyboard();

        Image img = new Image(getClass().getResourceAsStream("Triurfant.jpg"));
        Button btn = new Button("",new ImageView(img));
        btn.setOnAction((ActionEvent event) -> {
            Random s = new Random();
            int n = s.nextInt(10);
            mainKeyboard.PlaySound(n);

            long time = 1428009000; //time to retrieve match ids from
            getMatchIds(time);
        });
        
        Pane root = new Pane();
        root.getChildren().addAll(mainKeyboard,btn,m);
        
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
    
    //Get a match by its id
    public void getMatch(long matchId){
        GetMatchInfo call = new GetMatchInfo("https://na.api.pvp.net/api/lol/na/v2.2/match/" + matchId + "?includeTimeline=true&api_key=" + ApiKey.API_KEY);
        threadpool.execute(call);
    }
    
    //Get a match by its index in matchIds
    public void getMatch(int index){
        getMatch(matchIds.get(index));
    }
    
    //Note: the time is in epoch seconds (unix time in seconds) and must be in a multiple of 5 minutes
    public void getMatchIds(long time){
        GetIds call = new GetIds("https://na.api.pvp.net/api/lol/na/v4.1/game/ids?beginDate=" + time + "&api_key=" + ApiKey.API_KEY);
        threadpool.execute(call);
    }
    
    //Called whenever a match is constructed from the api
    public void recievedMatch(Match match){
        System.out.println("Parsed Match");
        matches.add(match);
        callingAPI = false;
    }
    //Called whenever a list of match ids is retrieved from the server
    public void recievedMatchIds(ArrayList<Long> ids){
        System.out.println("Got Match Ids");
        matchIds = ids;
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
                        recievedMatchIds(JSONUtils.MatchParser.parseIds(sb.toString()));                        
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
    
}
