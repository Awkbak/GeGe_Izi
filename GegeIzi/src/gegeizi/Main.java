/**©Awkbak BR, BobJrSenior
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
    
    ExecutorService threadpool;
    
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
        
        initKeyboard();

        Image img = new Image(getClass().getResourceAsStream("Triurfant.jpg"));
        Button btn = new Button("",new ImageView(img));
        btn.setOnAction((ActionEvent event) -> {
            Random s = new Random();
            int n = s.nextInt(10);
            mainKeyboard.PlaySound(n);
            CallAPI test = new CallAPI("https://na.api.pvp.net/api/lol/na/v2.2/match/1778704162?includeTimeline=true&api_key=" + ApiKey.API_KEY);
            threadpool.execute(test);
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
    
    public void recievedMatch(Match match){
        System.out.println("yay");
    }
    
    
    //Used to access the riot api
    class CallAPI implements Runnable{
        //url to retrieve from
        //public RequestParams params;
        public URL url;
        
        public CallAPI(String url){
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
            
            try {
                url = url;
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
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
            }
            finally{
            }
        }
        
        
    }
    
}
