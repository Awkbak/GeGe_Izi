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
    
    public Keyboard(String[] kek, int[] chk){
        board = new Key[10];
        for(int i = 0;i<10;i++){
            board[i] = new Key(kek[i]);
            
            if(i>=5)board[i].relocate(175+50*i,400);
            else board[i].relocate(125+50*i,400);
            board[i].setOnMouseClicked(this::testKey);
            board[i].setImage(chk[i]);
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
}
