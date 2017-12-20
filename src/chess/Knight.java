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
public class Knight extends Piece {


    public Knight(String color,Point position) {
        super(color, position);
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
            if (isKingAI(x, y,this.color,AllPieces)){
                CheckmateAI(this.color, AllPieces);
            }
            else{
               if(isEmptyAI(x, y,AllPieces)){
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
    //    Point currentPosition = this.position;
        if((Math.abs(x-this.position.getX())==2 && Math.abs(y-this.position.getY())==1)||(Math.abs(x-this.position.getX())==1 && Math.abs(y-this.position.getY())==2)){
            if(GameBoard.isEmpty(x, y) || GameBoard.isEnemy(x, y, this.color))
                return true ;
        }
//            if(x>this.position.getX()){
//                currentPosition.x+=2;
//                if(y<this.position.getY()){
//                    currentPosition.y-- ; //  *
//                                          //*** 
//                }else currentPosition.y++ ;
//                                          //***
//                                          //  *
//            }
//            else{
//                currentPosition.x-- ;
//                if(y<this.position.getY()){
//                    currentPosition.
//                }
//            }
//            
//            
//        }
//        else if((Math.abs(x-this.position.getX())==1 && Math.abs(y-this.position.getY())==1)){}
        
        return false;
    }

    @Override
    public boolean validateMoveAI(int x, int y, ArrayList<Piece> AllPieces) {
        if( x>7 || x <0 || y>7 || y<0){
            return false;
        }
        if((Math.abs(x-this.position.getX())==2 && Math.abs(y-this.position.getY())==1)||(Math.abs(x-this.position.getX())==1 && Math.abs(y-this.position.getY())==2)){
            if(isEmptyAI(x, y,AllPieces) || isEnemyAI(x, y, this.color,AllPieces))
                return true ;
        }
        return false;
    }
    
}
