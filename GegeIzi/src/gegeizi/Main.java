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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Awkbak
 */
public class Main extends Application {
    Keyboard mainKeyboard;
    @Override
    public void start(Stage primaryStage) {
        String[] kek = new String[10];
        for(int i =0;i<10;i++){
            kek[i] = "test.mp3";
        }
        mainKeyboard = new Keyboard(kek);
        Image img = new Image(getClass().getResourceAsStream("Triurfant.jpg"));
        Button btn = new Button("",new ImageView(img));
        btn.setOnAction((ActionEvent event) -> {
            mainKeyboard.PlaySound(1);
        });
        
        Pane root = new Pane();
        root.getChildren().addAll(mainKeyboard,btn);
        
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
