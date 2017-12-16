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
public class Queen extends Piece {

    public Queen(String color, int positionX, int positionY, ImageIcon img, String pieceType) {
        super(color, positionX, positionY, img, pieceType);
    }

    @Override
    public void move(int x, int y) {
        if (validateMove(x, y)){
            if (GameBoard.isKing(x, y)){
                GameBoard.Checkmate(this.color);
            }
            else{
               this.position.setLocation(x, y);
                if(!GameBoard.isEmpty(x, y)){
                    GameBoard.attack(x, y);
                }
            }
        }
    }

    @Override
    public boolean validateMove(int x, int y) {
        if ((Math.abs(this.position.getX() - x) == Math.abs(this.position.getY() - y))||((this.position.getX()-x)==0||(this.position.getY()-y)==0)){
            if(isPathClear(x, y)){
                return true;
            }
        }
        return false;
    }

    private boolean isPathClear(int x, int y) {
        Point currentPosition = this.position;
        int counter = (int) Math.sqrt( Math.pow(x - this.position.getX(),2)+Math.pow(y - this.position.getY(),2)); // change old counters by this one
        if (counter==1)
            return true ;
        for(int i = 0; i < counter-1 ; i++){
            if (x < this.position.getX() && y < this.position.getY()){//top-left
                currentPosition.x--;
                currentPosition.y--;
            }
            else if(x < this.position.getX() && y > this.position.getY()){//down-left
                currentPosition.x--;
                currentPosition.y++;
            }
            else if(x > this.position.getX() && y < this.position.getY()){//top-right
                currentPosition.x++;
                currentPosition.y--;
            }
            else if (x > this.position.getX() && y > this.position.getY()){//down-right
                currentPosition.x++;
                currentPosition.y++;
            }
            
            else if(x==this.position.getX()&&y>this.position.getY()){//up
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
            if(!GameBoard.isEmpty(x, y)){// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                return false;
            }
        }
        return true;
    }
    
    
}
