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
public class Knight extends Piece {

    public Knight(String color,Point position) {
        super(color, position);
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
    //    Point currentPosition = this.position;
        if((Math.abs(x-this.position.getX())==2 && Math.abs(y-this.position.getY())==1)||(Math.abs(x-this.position.getX())==1 && Math.abs(y-this.position.getY())==2)){
            //check for the piece in that (x,y) white OR black OR empty
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
        
        else return false;
    }


    
}
