/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Point;
import java.io.Serializable;

/**
 * 
 *
 * @author Mina
 */
public class Tile implements Serializable{
    
    private int x,y;
    public Point P1,P2,P3,P4;
    private Boolean Empty;
    Piece piece; 
    
    
    Tile(Point P1,Point P2,Point P3,Point P4){
        this.P1=P1;
        this.P2=P2;
        this.P3=P3;
        this.P4=P4;
    }
    
//    public Rectangle getRectangle(){
//        return new Rectangle(P1.x, P2, P3, P4);
//    }
    
    
    
//    static void initPiecesOnTiles(){
//        //Bishop bishop = new Bishop();
//        BoardTiles.get(BoardTilePointer[3][8]).piece=bishop;
////    }
    
    Boolean isEmpty(){
        return Empty;
    }
    
    void setEmpty(Boolean e){
        this.Empty=e;
    }
    
    void setXANDY(int x, int y){
        this.x=x;
        this.y=y;
    }
    
    Point getXANDY(){
        return new Point(x,y);
    }
    
    Boolean isOccupied(){
        if(!this.Empty){
            return true;
        }
        else
            return false;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    
    Piece getPiece(){
        return this.piece;
    }
      
    
    
    
}
