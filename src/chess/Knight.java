/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ToniGeorge
 */
public class Knight extends Piece implements Cloneable,Serializable{


    public Knight(String color,Point position) {
        super(color, position);
    }
    @Override
    public boolean move(int x, int y) {
       if (validateMove(x, y)) {
            int OldX = (int) this.position.getX();
            int OldY = (int) this.position.getY();
            this.position.setLocation(x, y);
            if (this.color.equalsIgnoreCase("Black") && GameBoard.isTileThreatened("Black", (int)GameBoard.AllPiecesCloned.get(30).getPosition().getX(), (int) GameBoard.AllPiecesCloned.get(30).getPosition().getY())) {
                //undo the setLocation 
                JOptionPane.showConfirmDialog(null, "CAN'T ! Black King will be threatened");
                this.position.setLocation(OldX, OldY);
                return false;
            }
            if (y == 0 || y == 7) {
                //switchPawn(x, y);
            }
            if (!GameBoard.isEmpty(x, y)) {
                GameBoard.attack(x, y);
            }
            this.numOfMoves++;
            return true;
        }
        return false;    }        
    
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
        if(x<0||x>7){
            return false;
        }
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
    @Override
    protected Knight clone() throws CloneNotSupportedException {
        Knight k = new Knight(this.color, new Point((int)this.position.getX(), (int)this.position.getY()));
        k.alive = this.alive;
        k.numOfMoves = this.numOfMoves;
        k.pieceType = this.pieceType;
        k.label = null;
       return k;
    }
    
}
