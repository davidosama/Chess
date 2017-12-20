package chess;

import java.awt.Point;
import java.util.ArrayList;

public class Bishop extends Piece{

    public Bishop(String color,Point position) {
        super(color,position);
    }

    @Override
    public boolean move(int x, int y) {
        if (validateMove(x, y)){
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
        if (validateMoveAI(x, y,AllPieces)){
            if (isKingAI(x, y,this.color,AllPieces)){
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
        if (Math.abs(this.position.getX() - x) == Math.abs(this.position.getY() - y)){
            if(isPathClear(x, y) && (GameBoard.isEnemy(x, y, this.color) || GameBoard.isEmpty(x, y))){
                return true;
            }
        }
        return false;
    }
    
    public boolean isPathClear(int x, int y){
        Point currentPosition =  new Point((int)this.position.getX(),(int)this.position.getY());
        int counter = (int) Math.abs(this.position.getX() - x);
        if (counter == 1)
            return true;
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
            if(!GameBoard.isEmpty(currentPosition.x, currentPosition.y)){// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean validateMoveAI(int x, int y, ArrayList<Piece> AllPieces) {
        if( x>7 || x <0 || y>7 || y<0){
            return false;
        }
        if (Math.abs(this.position.getX() - x) == Math.abs(this.position.getY() - y)){
            if(isPathClearAI(x, y,AllPieces) && (isEnemyAI(x, y, this.color,AllPieces) || isEmptyAI(x, y,AllPieces))){
                return true;
            }
        }
        return false;
    }
    
    public boolean isPathClearAI(int x, int y, ArrayList<Piece> AllPiece){
        Point currentPosition =  new Point((int)this.position.getX(),(int)this.position.getY());
        int counter = (int) Math.abs(this.position.getX() - x);
        if (counter == 1)
            return true;
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
            if(!isEmptyAI(currentPosition.x, currentPosition.y, AllPiece)){// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                return false;
            }
        }
        return true;
    }
}
