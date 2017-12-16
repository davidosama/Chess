package chess;

import java.awt.Point;
import javax.swing.ImageIcon;

public class Pawn extends Piece{

    public Pawn(String color,Point position) {
        super(color,position);
    }

    @Override
    public void move(int x, int y) {
        //check if it's the king at that position
        if (validateMove(x, y)){
            this.position.setLocation(x, y);
            if(y == 0 || y == 7){
                switchPawn(x, y);
            }
        }
        //add fucntion attack(abstract Piece) to check if the new position eats the opponent position
    }

    @Override
    public boolean validateMove(int x, int y) {
        if (this.color.equals("Black")){//black sets are above so the Pawn is allowed to move down only
            // a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
            if(false){//if there is no piece and the return of the previous function is null/false
                if( x == this.position.getX() && y == this.position.getY()+1){
                    return true;
                }
                else if( this.numOfMoves == 0 && x == this.position.getX() && y == this.position.getY()+2){
                    return true;
                }
            }
            else if(true){//if there is a piece and it's the of the opposite color so it's attack that piece
                if( (x == this.position.getX()-1 || x == this.position.getX()+1) && y == this.position.getY()+1){
                    return true;
                }  
            }
        }
        else if(this.color.equals("White")){//white sets are below so the Pawn is allowed to move up only
            // a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
            if(false){//if there is no piece and the return of the previous function is null/false
                if( x == this.position.getX() && y == this.position.getY()-1){
                    return true;
                }
                else if( this.numOfMoves == 0 && x == this.position.getX() && y == this.position.getY()-2){
                    return true;
                }
            }
            else if(false){//if there is a piece and it's the of the opposite color so it's attack that piece
                if( (x == this.position.getX()-1 || x == this.position.getX()+1) && y == this.position.getY()-1){
                    return true;
                }  
            }
        }
        return false;
    }
    
    public void switchPawn(int x, int y){// swich the pawn with a dead piece when it reaches the end
        
    }
}
