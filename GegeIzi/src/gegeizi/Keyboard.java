/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gegeizi;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Awkbak
 */
public class Keyboard extends Group{ 
    final private Key[] board;
    
    public Keyboard(){
        board = new Key[10];
        for(int i = 0;i<10;i++){
            board[i] = new Key();
            board[i].relocate(150+50*i,400);
            board[i].setOnMouseClicked(this::testKey);
            this.getChildren().add(board[i]);
        }
    }
    
    public Keyboard(String[] kek){
        board = new Key[10];
        for(int i = 0;i<10;i++){
            board[i] = new Key(kek[i]);
            board[i].relocate(150+50*i,400);
            board[i].setOnMouseClicked(this::testKey);
            board[i].setImage(8);
            this.getChildren().add(board[i]);
        }
    }
    
    public Keyboard(String[] kek, int[] chk){
        board = new Key[10];
        for(int i = 0;i<10;i++){
            board[i] = new Key(kek[i]);
            board[i].relocate(150+50*i,400);
            board[i].setOnMouseClicked(this::testKey);
            board[i].setImage(chk[i]);
            this.getChildren().add(board[i]);
        }
    }
    
    public void PlaySound(int i){
        board[i].PlaySound();
    }
    
    public void testKey(MouseEvent e){
        ((Key)e.getSource()).PlaySound();
    }
}
