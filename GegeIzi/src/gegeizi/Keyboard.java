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

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Arranges a group of 10 keys to create a keyboard. Each key is respective to each champion and their splash art.
 * @author Awkbak BR, Bobjrsenior
 */
public class Keyboard extends Group{ 
    final private Key[] board;
    
    public Keyboard(String[] keySound, int[] champImage){
        board = new Key[10];
        for(int i = 0;i<10;i++){
            board[i] = new Key(keySound[i]);
            
            if(i>=5)board[i].relocate(175+50*i,400);
            else board[i].relocate(125+50*i,400);
            board[i].setOnMouseClicked(this::testKey);
            board[i].setImage(champImage[i]);
            if(i<5)board[i].setStroke(Color.ROYALBLUE);
            else board[i].setStroke(Color.RED);
            this.getChildren().add(board[i]);
        }
    }
    /**
     * Plays sound according to which key is pressed
     * @param i 
     */
    public void PlaySound(int i){
        board[i].PlaySound();
    }
    /**
     * Plays sound according to which key is Clicked
     * @param e 
     */
    private void testKey(MouseEvent e){
        ((Key)e.getSource()).PlaySound();
    }
    /**
     * Gets the key array
     * @return Keyboard
     */
    public Key[] getBoard(){
        return board;
    }
    /**
     * When called changes the instrument referring to the instrument selected my the user
     * @param instrument 
     */
    public void setInstrument(String instrument){
        int program = 0;
        switch (instrument) {
            case "Acoustic Piano":
                program = 0;
                break;
            case "Bright Piano":
                program = 2;
                break;
            case "Xylophone":
                program = 14;
                break;
            case "Gunshots":
                program = 128;
                break;
            case "Honky Piano":
                program = 4;
                break;
            case "Melodic Drum":
                program = 118;
                break;
            case "Harpsichord":
                program = 7;
                break;
        }
        for(Key e : board){
            e.setInstrument(program);
        }
    }
}
