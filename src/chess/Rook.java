/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author ToniGeorge
 */
public class Rook extends Piece implements Cloneable{


    public Rook(String color, Point position) {
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
    public boolean validateMove(int x, int y) {
        if((this.position.getX()-x)==0||(this.position.getY()-y)==0){
            if(isPathClear(x, y) && (GameBoard.isEnemy(x, y, this.color) || GameBoard.isEmpty(x, y))){
                return true;
            }
        
        }
        return false ;
    }
    
    

    private boolean isPathClear(int x, int y) {
        Point currentPosition = new Point ((int)this.position.getX(),(int)this.position.getY());
        int counter = (int) Math.abs(this.position.getX() - x)+(int) Math.abs(this.position.getY() - y);
        if (counter == 1)
            return true;
        for(int i=0 ;i<counter-1;i++){
            if(x==this.position.getX()&&y>this.position.getY()){//down
                currentPosition.y++;
            }
            else if(x>this.position.getX()&&y==this.position.getY()){//right
                currentPosition.x++;
            }
            else if(x==this.position.getX()&&y<this.position.getY()){//up
                currentPosition.y--;
            }
            else if(x<this.position.getX()&&y==this.position.getY()){//left
                currentPosition.x--;
            }
            if(!GameBoard.isEmpty((int)currentPosition.getX(),(int)currentPosition.getY())){// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                return false;
            }   
        }
        return true ;
    }
    
//    public ArrayList AImove(int x, int y, ArrayList<Piece> arrayList , int MovingPieceIndex) {
//        if(validateMove(x, y)){
////            if (GameBoard.isKing(x, y)){
////                GameBoard.Checkmate(this.color);
////            }
////            else{
////               if(!GameBoard.isEmpty(x, y)){
////                    GameBoard.attack(x, y);
////                }
//            //arrayList.get(MovingPieceIndex).
//            
//               this.position.setLocation(x, y);
//            }
//            return true;
//        }
//        return false;
//    }

    @Override
    public boolean moveAI(int x, int y, ArrayList<Piece> AllPieces) {
        if(validateMoveAI(x, y,AllPieces)){
            if (isKingAI(x, y, this.color,AllPieces)){
                CheckmateAI(this.color,AllPieces);
            }
            else{
               if(!isEmptyAI(x, y,AllPieces)){
                    attackAI(x, y,AllPieces);
                }
               this.position.setLocation(x, y);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean validateMoveAI(int x, int y, ArrayList<Piece> AllPieces) {
        if( x>7 || x <0 || y>7 || y<0){
            return false;
        }
        if((this.position.getX()-x)==0||(this.position.getY()-y)==0){
            if(isPathClearAI(x, y,AllPieces) && (isEnemyAI(x, y, this.color,AllPieces) || isEmptyAI(x, y,AllPieces))){
                return true;
            }
        
        }
        return false ;
    }
    
    private boolean isPathClearAI(int x, int y, ArrayList<Piece> AllPieces) {
        Point currentPosition = new Point ((int)this.position.getX(),(int)this.position.getY());
        int counter = (int) Math.abs(this.position.getX() - x)+(int) Math.abs(this.position.getY() - y);
        if (counter == 1)
            return true;
        for(int i=0 ;i<counter-1;i++){
            if(x==this.position.getX()&&y>this.position.getY()){//down
                currentPosition.y++;
            }
            else if(x>this.position.getX()&&y==this.position.getY()){//right
                currentPosition.x++;
            }
            else if(x==this.position.getX()&&y<this.position.getY()){//up
                currentPosition.y--;
            }
            else if(x<this.position.getX()&&y==this.position.getY()){//left
                currentPosition.x--;
            }
            if(!isEmptyAI((int)currentPosition.getX(),(int)currentPosition.getY(), AllPieces)){// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                return false;
            }   
        }
        return true ;
    }
    
    @Override
    protected Rook clone() throws CloneNotSupportedException {
        Rook k = new Rook(this.color, new Point((int)this.position.getX(), (int)this.position.getY()));
        k.alive = this.alive;
        k.numOfMoves = this.numOfMoves;
       return k;
    }
}

