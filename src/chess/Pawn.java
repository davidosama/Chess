package chess;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Pawn extends Piece implements Cloneable, Serializable{

    public Pawn(String color, Point position) {
        super(color, position);
    }
    
    public Pawn(String color, Point position, boolean alive, String pieceType, int numOfMoves){
        super(color, position, alive, pieceType, numOfMoves);
    }
    
    @Override
    public boolean move(int x, int y) {
        if (validateMove(x, y)) {
            int OldX = (int) this.position.getX();
            int OldY = (int) this.position.getY();
            this.position.setLocation(x, y);
            if (this.color.equalsIgnoreCase("Black") && GameBoard.isTileThreatened("Black", (int)GameBoard.AllPiecesCloned.get(30).getPosition().getX(), (int) GameBoard.AllPiecesCloned.get(30).getPosition().getY())) {
                //undo the setLocation 
                //JOptionPane.showConfirmDialog(null, "CAN'T ! Black King will be threatened");
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
        return false;
    }

    @Override
    public boolean moveAI(int x, int y, ArrayList<Piece> AllPiece) {
        if (validateMoveAI(x, y, AllPiece)) {
            int OldX = (int) this.position.getX();
            int OldY = (int) this.position.getY();
            this.position.setLocation(x, y);
            if (this.color.equalsIgnoreCase("White") && GameBoard.isTileThreatenedAI("White", (int) GameBoard.AllPiecesCloned.get(31).getPosition().getX(), (int) GameBoard.AllPiecesCloned.get(31).getPosition().getX(), AllPiece)) {
                //undo the setLocation 
                //JOptionPane.showConfirmDialog(null, "WHITE KING IS THREATENED");
                this.position.setLocation(OldX, OldY);
                return false;
            }
            if (!isEmptyAI(x, y, AllPiece)) {
                attackAI(x, y, AllPiece);
            }
            return true;
        }
        return false;
    }
    
    public boolean canAttack(int x, int y) {
        if (this.color.equals("Black")) {
            if ((x == this.position.getX() - 1 || x == this.position.getX() + 1) && y == this.position.getY() - 1) {
                return true;
            }
            return false;
        } 
        else{
             if ((x == this.position.getX() - 1 || x == this.position.getX() + 1) && y == this.position.getY() + 1) {
                    return true;
                }
             return false;
        }
        
    }

    @Override
    public boolean validateMove(int x, int y) {
        if (this.color.equals("Black")){//black sets are above so the Pawn is allowed to move down only
            if (GameBoard.isEmpty(x,y)){//if there is no piece and the return of the previous function is null/false
                if( x == this.position.getX() && y == this.position.getY()-1){
                    return true;
                }
                else if( this.numOfMoves == 0 && x == this.position.getX() && y == this.position.getY()-2){
                    return true;
                }
            }
            else if (GameBoard.isEnemy(x, y, this.color)){//if there is a piece and it's the of the opposite color so it's attacking that piece
                if( (x == this.position.getX()-1 || x == this.position.getX()+1) && y == this.position.getY()-1){
                    return true;
                }  
            }
        }
        else if(this.color.equals("White")){//white sets are below so the Pawn is allowed to move up only
            if(GameBoard.isEmpty(x,y)){//if there is no piece and the return of the previous function is null/false
                if( x == this.position.getX() && y == this.position.getY()+1){
                    return true;
                }
                else if( this.numOfMoves == 0  &&  x == this.position.getX() && y == this.position.getY()+2){
                    return true;
                }
            }
            else if(GameBoard.isEnemy(x, y, this.color)){//if there is a piece and it's the of the opposite color so it's attacking that piece
                if( (x == this.position.getX()-1 || x == this.position.getX()+1) && y == this.position.getY()+1){
                    return true;
                }  
            }
        }
        return false;
    }

    @Override
    public boolean validateMoveAI(int x, int y, ArrayList<Piece> AllPieces) {
        if( x>7 || x <0 || y>7 || y<0){
            return false;
        }
        if (this.color.equals("Black")){//black sets are above so the Pawn is allowed to move down only
            if (isEmptyAI(x,y,AllPieces)){//if there is no piece and the return of the previous function is null/false
                if( x == this.position.getX() && y == this.position.getY()-1){
                    return true;
                }
                else if( this.numOfMoves == 0 && x == this.position.getX() && y == this.position.getY()-2){
                    return true;
                }
            }
            else if (isEnemyAI(x, y, this.color,AllPieces)){//if there is a piece and it's the of the opposite color so it's attacking that piece
                if( (x == this.position.getX()-1 || x == this.position.getX()+1) && y == this.position.getY()-1){
                    return true;
                }  
            }
        }
        else if(this.color.equals("White")){//white sets are below so the Pawn is allowed to move up only
            if(isEmptyAI(x,y,AllPieces)){//if there is no piece and the return of the previous function is null/false
                if( x == this.position.getX() && y == this.position.getY()+1){
                    return true;
                }
                else if( this.numOfMoves == 0  &&  x == this.position.getX() && y == this.position.getY()+2){
                    return true;
                }
            }
            else if(isEnemyAI(x, y, this.color,AllPieces)){//if there is a piece and it's the of the opposite color so it's attacking that piece
                if( (x == this.position.getX()-1 || x == this.position.getX()+1) && y == this.position.getY()+1){
                    return true;
                }  
            }
        }
        return false;
    }
    
    public void switchPawn(int x, int y) {// swich the pawn with a dead piece when it reaches the end. should be placed in GameBoard??

    }

    @Override
    protected Pawn clone() throws CloneNotSupportedException {
        Pawn k = new Pawn(this.color, new Point((int)this.position.getX(), (int)this.position.getY()));
        k.alive = this.alive;
        k.numOfMoves = this.numOfMoves;
        k.pieceType = this.pieceType;
        k.label = null;
       return k;
    }
    
    
    
}
