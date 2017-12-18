package chess;

import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Piece {
    int numOfMoves;
    String color;
    Point position;
    boolean alive;
    JLabel label;
    String pieceType;
    
    public Piece(String color, Point position){
        this.numOfMoves = 0;
        this.color = color;
        this.position = position;
        this.alive = true;
        loadImage();
        this.pieceType = this.getClass().getName().replace("chess.", "");
    }
    
    public Piece(String color, Point position, boolean alive, String pieceType, int numOfMoves){
        this.numOfMoves = numOfMoves;
        this.color = color;
        this.position = position;
        this.alive = alive;
        this.pieceType = this.getClass().getName();
    }
    
    public void loadImage()
    {
        String imageName = this.getClass().getName();
        imageName = imageName.replace("chess.", "");
        imageName=color+imageName;
        System.out.println("Image Name is : "+imageName);
        ImageIcon i = new ImageIcon (getClass().getResource("/chess/imgs/"+imageName+".png")) ; 
        label=new JLabel(i);
        //label.setSize(60, 60);
    }
    
    public Point getPosition(){
        return this.position;
    }
    
    public void setPosition(Point position){
        this.position = position;
    }

    public abstract boolean move(int x, int y);
    
    public abstract boolean validateMove(int x, int y);
    
}
