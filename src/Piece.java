
import java.awt.Point;
import javax.swing.ImageIcon;

public abstract class Piece {
    int numOfMoves;
    String color;
    Point position;
    boolean alive;
    ImageIcon img;
    
    public Piece(String color, int positionX, int positionY, ImageIcon img){
        this.numOfMoves = 0;
        this.color = color;
        this.position = new Point(positionX, positionY);
        this.alive = true;
        this.img = img;
    }
    
    public abstract void move();
    
    public abstract boolean validateMove();
    
}
