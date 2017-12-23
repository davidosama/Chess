package chess;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

public class King extends Piece implements Cloneable,Serializable{

    static Point BlackKingPosition;
    static Point WhiteKingPosition;
    public King(String color, Point position){
        super(color,position);
    }
    
    @Override
    public boolean move(int x, int y) {
        if (validateMove(x, y)) {
            if (GameBoard.isKing(x, y, this.color)) {
                GameBoard.Checkmate(this.color);
            } else {
                if (!GameBoard.isEmpty(x, y)) {
                    GameBoard.attack(x, y);
                }
            }
            this.position.setLocation(x, y);
            this.numOfMoves++;
            
            return true;
        }
        return false;
    }

    public boolean moveAI(int x, int y, ArrayList<Piece> AllPieces) {
        if (validateMoveAI(x, y, AllPieces)) {
            int OldX = (int) this.position.getX();
            int OldY = (int) this.position.getY();
            this.position.setLocation(x, y);
            if (this.color.equalsIgnoreCase("White") && GameBoard.isTileThreatenedAI("White", (int)AllPieces.get(31).getPosition().getX(), (int)AllPieces.get(31).getPosition().getX(), AllPieces)) {
                //undo the setLocation 
//                JOptionPane.showConfirmDialog(null, "WHITE KING IS THREATENED");
                this.position.setLocation(OldX, OldY);
                return false;
            }
            if (!isEmptyAI(x, y, AllPieces)) {
                attackAI(x, y, AllPieces);
            }
            return true;
        }
        return false;
    }
    
    @Override
    public boolean validateMove(int x, int y) {
        if(Math.abs(x-this.position.getX())<=1&&(Math.abs(y-this.position.getY()))<=1){
            if(GameBoard.isEmpty(x,y)||GameBoard.isEnemy(x, y, this.color)){
                if(GameBoard.isTileThreatened(this.color, x, y))
                    return false;
                else 
                    return true;
            }
            return false;
        }
        return false;
        
    }
    
    @Override
    public boolean validateMoveAI(int x, int y, ArrayList<Piece> AllPieces) {
        if( x>7 || x <0 || y>7 || y<0){
            return false;
        }
        if(Math.abs(x-this.position.getX())<=1&&(Math.abs(y-this.position.getY()))<=1){
            if(isEmptyAI(x,y,AllPieces)||isEnemyAI(x, y, this.color, AllPieces)){
                if(GameBoard.isTileThreatenedAI(this.color, x, y,AllPieces))
                    return false;
                else 
                    return true;
                
            }
            return false;
        }
        return false;
            
    }
    @Override
    public King clone() throws CloneNotSupportedException {
        King k = new King(this.color, new Point((int)this.position.getX(), (int)this.position.getY()));
        k.alive = this.alive;
        k.numOfMoves = this.numOfMoves;
        k.pieceType = this.pieceType;
        k.label = null;
       return k;
    }
}
