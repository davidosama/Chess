package chess;

import java.awt.Point;
import javax.swing.ImageIcon;

public class Bishop extends Piece{

    public Bishop(String color,Point position) {
        super(color,position);
    }

    @Override
    public void move(int x, int y) {
        if (validateMove(x, y)){
            this.position.setLocation(x, y);
        }
        //add fucntion attack(abstract Piece) to check if the new position eats the opponent position
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
        Point currentPosition = this.position;
        int counter = (int) Math.abs(this.position.getX() - x);
        if (counter == 1)
            return true;
        for(int i = 0; i < counter-1 ; i++){
            if (x < this.position.getX() && y < this.position.getY()){//top-left
                currentPosition.x--;
                currentPosition.y--;
            }
            else if(x < this.position.getX() && y > this.position.getY()){//down-left
                currentPosition.x--;
                currentPosition.y++;
            }
            else if(x > this.position.getX() && y < this.position.getY()){//top-right
                currentPosition.x++;
                currentPosition.y--;
            }
            else if (x > this.position.getX() && y > this.position.getY()){//down-right
                currentPosition.x++;
                currentPosition.y++;
            }
            if(false){// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                return false;
            }
        }
        return true;
    }
}
