/**©Murilo Costa
 * GeGe Izi
 * Goal: To create a convenient tool for streamers to show and analyze their stats.
 * Using Riot's API to create this app.
 * Start Date: 3/27/2015
 */
package gegeizi;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Awkbak
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World!");
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
    
    
    //Used to access the riot api
    class CallAPI implements Runnable{
        //url to retrieve from
        public RequestParams params;
        
        public CallAPI(RequestParams params){
            this.params = params;
        }
        
        //
        @Override
        public void run() {
            
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        
    }
    
}
