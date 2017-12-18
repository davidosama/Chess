package chess;

import java.awt.Point;

public class King extends Piece{

    public King(String color, Point position){
        super(color,position);
    }
    
    @Override
    public boolean move(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validateMove(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
