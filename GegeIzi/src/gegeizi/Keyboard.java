/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gegeizi;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author Awkbak
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
    
    public void PlaySound(int i){
        board[i].PlaySound();
    }
    
    public void testKey(MouseEvent e){
        ((Key)e.getSource()).PlaySound();
    }
    
    public Key[] getBoard(){
        return board;
    }
    
    public void setInstrument(String instrument){
        int program = 0;
        switch (instrument) {
            case "Piano":
                program = 0;
                break;
            case "Percussion":
                program = 9;
                break;
            case "Tuba":
                program = 59;
                break;
            case "Ocarina":
                program = 80;
                break;
        }
        for(Key e : board){
            e.setInstrument(0);
        }
    }
}
