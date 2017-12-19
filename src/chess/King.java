package chess;

import java.awt.Point;

public class King extends Piece{

    public King(String color, Point position){
        super(color,position);
    }
    
    @Override
    public boolean move(int x, int y) {
        if(validateMove(x, y)){
            if (GameBoard.isKing(x, y)){
                GameBoard.Checkmate(this.color);
            }
            else{
               if(!GameBoard.isEmpty(x, y)){
                    GameBoard.attack(x, y);
                }
               this.position.setLocation(x, y);
            }
            return true;
        }
        return false;
    }

     @Override
    public boolean validateMove(int x, int y) {
        if(Math.abs(x-this.position.getX())<=1&&(Math.abs(y-this.position.getY()))<=1){
            
            if(GameBoard.isEmpty(x,y)){
                if(GameBoard.isTileThreatened(this.color, x, y))
                    return false;
                else 
                    return true;
                
            }
            else{
                if(GameBoard.isEnemy(x, y, this.color)){
                    GameBoard.attack(x, y);
                }
            }
        }
        else {
            return false;
        }
        return true;
    }
    
}
