/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Point;
import javax.swing.ImageIcon;

/**
 *
 * @author ToniGeorge
 */
public class Rook extends Piece {

    public Rook(String color, int positionX, int positionY, ImageIcon img, String pieceType) {
        super(color, positionX, positionY, img, pieceType);
    }

    @Override
    public void move(int x, int y) {
        if(validateMove(x, y)){
            this.position.setLocation(x, y);
            //add fucntion attack(abstract Piece) to check if the new position eats the opponent position
        }
    }

    @Override
    public boolean validateMove(int x, int y) {
        if((this.position.getX()-x)==0||(this.position.getY()-y)==0){
            if(isPathClear(x, y)){
                return true;
            }
        
        }
        return false ;
    }

    private boolean isPathClear(int x, int y) {
        Point currentPosition = this.position;
        int counter = (int) Math.abs(this.position.getX() - x)+(int) Math.abs(this.position.getY() - y);
        if (counter == 1)
            return true;
        for(int i=0 ;i<counter-1;i++){
            if(x==this.position.getX()&&y>this.position.getY()){//up
                currentPosition.y--;
            }
            else if(x>this.position.getX()&&y==this.position.getY()){//right
                currentPosition.x++;
            }
            else if(x==this.position.getX()&&y<this.position.getY()){//down
                currentPosition.y++;
            }
            else if(x<this.position.getX()&&y==this.position.getY()){//left
                currentPosition.x--;
            }
            if(false){// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                return false;
            }
            
        }
        return true ;
    }
    
}
