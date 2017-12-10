package chess;

import javax.swing.ImageIcon;

public class Bishop extends Piece{

    public Bishop(String color, int positionX, int positionY, ImageIcon img, String pieceType) {
        super(color, positionX, positionY, img, pieceType);
    }

    @Override
    public void move(int x, int y) {
        if (validateMove(x, y)){
            this.position.setLocation(x, y);
        }
    }

    @Override
    public boolean validateMove(int x, int y) {
        if (Math.abs(this.position.getX() - x) == Math.abs(this.position.getY() - y)){
            if(isPathClear(x, y)){
                return true;
            }
        }
        return false;
    }
    
    public boolean isPathClear(int x, int y){
        //implement it
        return false;
    }
}
