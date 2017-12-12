package chess;


import java.awt.Point;
import javax.swing.ImageIcon;

public abstract class Piece {
    int numOfMoves;
    String color;
    Point position;
    boolean alive;
    ImageIcon img;
    String pieceType;
    
    public Piece(String color, int positionX, int positionY, ImageIcon img, String pieceType){
        this.numOfMoves = 0;
        this.color = color;
        this.position = new Point(positionX, positionY);
        this.alive = true;
        this.img = img;
        this.pieceType = pieceType;
    }
    
    public abstract void move(int x, int y);
    
    public abstract boolean validateMove(int x, int y);
    
}
