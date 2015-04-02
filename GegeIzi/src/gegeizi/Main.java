/**©Murilo Costa
 * GeGe Izi
 * Goal: To create a convenient tool for streamers to show and analyze their stats.
 * Using Riot's API to create this app.
 * Start Date: 3/27/2015
 */
package gegeizi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Awkbak
 */
public class Main extends Application {
    
    
    ExecutorService threadpool;
    
    @Override
    public void start(Stage primaryStage) {
        threadpool = Executors.newFixedThreadPool(4);
        
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World!");
            CallAPI test = new CallAPI("https://na.api.pvp.net/api/lol/na/v2.2/match/1778704162?includeTimeline=true&api_key=04e61b3a-f876-4fc6-bc1e-1e5f3c6fc2af");
            threadpool.execute(test);
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
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
