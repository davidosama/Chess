package chess;

import java.awt.Point;
import java.util.ArrayList;

public class King extends Piece{

    public King(String color, Point position){
        super(color,position);
    }
    
    @Override
    public boolean move(int x, int y) {
        if(validateMove(x, y)){
            if (GameBoard.isKing(x, y, this.color)){
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
    public boolean moveAI(int x, int y, ArrayList<Piece> AllPieces) {
        if(validateMoveAI(x, y, AllPieces)){
            if (isKingAI(x, y, this.color, AllPieces)){
                CheckmateAI(this.color, AllPieces);
            }
            else{
               if(!isEmptyAI(x, y, AllPieces)){
                    attackAI(x, y,AllPieces);
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
    
    @Override
    public boolean validateMoveAI(int x, int y, ArrayList<Piece> AllPieces) {
        if(Math.abs(x-this.position.getX())<=1&&(Math.abs(y-this.position.getY()))<=1){
            
            if(isEmptyAI(x,y,AllPieces)){
                if(GameBoard.isTileThreatened(this.color, x, y))//
                    return false;
                else 
                    return true;
                
            }
            else{
                if(isEnemyAI(x, y, this.color, AllPieces)){
                    attackAI(x, y, AllPieces);
                }
            }
        }
        else {
            return false;
        }
        return true;
    }
    
}
