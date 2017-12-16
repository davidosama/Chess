package chess;

import javax.swing.ImageIcon;

public class Pawn extends Piece{

    public Pawn(String color, int positionX, int positionY, ImageIcon img, String pieceType) {
        super(color, positionX, positionY, img, pieceType);
    }

    @Override
    public void move(int x, int y) {
        if (validateMove(x, y)){
            if (GameBoard.isKing(x, y)){
                GameBoard.Checkmate(this.color);
            }
            else{
               this.position.setLocation(x, y);
                if(y == 0 || y == 7){
                    switchPawn(x, y);
                }
                if(!GameBoard.isEmpty(x, y)){
                    GameBoard.attack(x, y);
                }
            }
        }
    }

    @Override
    public boolean validateMove(int x, int y) {
        if (this.color.equals("Black")){//black sets are above so the Pawn is allowed to move down only
            if (GameBoard.isEmpty(x,y)){//if there is no piece and the return of the previous function is null/false
                if( x == this.position.getX() && y == this.position.getY()+1){
                    return true;
                }
                else if( this.numOfMoves == 0 && x == this.position.getX() && y == this.position.getY()+2){
                    return true;
                }
            }
            else if (GameBoard.isEnemy(x, y, this.color)){//if there is a piece and it's the of the opposite color so it's attacking that piece
                if( (x == this.position.getX()-1 || x == this.position.getX()+1) && y == this.position.getY()+1){
                    return true;
                }  
            }
        }
        else if(this.color.equals("White")){//white sets are below so the Pawn is allowed to move up only
            if(GameBoard.isEmpty(x,y)){//if there is no piece and the return of the previous function is null/false
                if( x == this.position.getX() && y == this.position.getY()-1){
                    return true;
                }
                else if( this.numOfMoves == 0 && x == this.position.getX() && y == this.position.getY()-2){
                    return true;
                }
            }
            else if(GameBoard.isEnemy(x, y, this.color)){//if there is a piece and it's the of the opposite color so it's attacking that piece
                if( (x == this.position.getX()-1 || x == this.position.getX()+1) && y == this.position.getY()-1){
                    return true;
                }  
            }
        }
        return false;
    }
    
    public void switchPawn(int x, int y){// swich the pawn with a dead piece when it reaches the end. should be placed in GameBoard??
        
    }
}
