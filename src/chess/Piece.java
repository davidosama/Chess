package chess;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Piece implements Cloneable, Serializable{
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
//        System.out.println("Image Name is : "+imageName);
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
    
    public abstract boolean moveAI(int x, int y, ArrayList<Piece> AllPieces);
    
    public abstract boolean validateMoveAI(int x, int y, ArrayList<Piece> AllPieces);
    //public abstract boolean validateNextMoveAI(int x, int y, ArrayList<Piece> AllPieces);
    
    public abstract boolean canAttack(int x, int y);
    public abstract boolean canAttackAI(int x, int y, ArrayList<Piece>AllPieces);
    
    public void CheckmateAI(String attackingColor, ArrayList<Piece> AllPieces){
        //implement
    }
    
    public boolean isEnemyAI(int x, int y, String attackingColor, ArrayList<Piece> AllPieces){
        for(int i=0; i<AllPieces.size(); i++){
            if(AllPieces.get(i).color.equalsIgnoreCase(attackingColor))
                continue;
            else{
                if(AllPieces.get(i).position.getX() == x && AllPieces.get(i).position.getY() == y && AllPieces.get(i).alive == true){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isEmptyAI(int x, int y, ArrayList<Piece> AllPieces){
        for(int i=0; i<AllPieces.size(); i++){
            if(AllPieces.get(i).position.getX() == x && AllPieces.get(i).position.getY() == y && AllPieces.get(i).alive == true){
                return false;
            }
        }
        return true;
    }
    
    public boolean isKingAI(int x, int y, String attackingColor, ArrayList<Piece> AllPieces){
        for(int i=0; i<AllPieces.size(); i++){
            if(AllPieces.get(i).color.equalsIgnoreCase(attackingColor))
                continue;
            else if(AllPieces.get(i).pieceType.equalsIgnoreCase("King") && AllPieces.get(i).position.getX() == x && AllPieces.get(i).position.getY() == y && AllPieces.get(i).alive == true){
                return true;
            }
        }
        return false;
    }
    
    public void attackAI(int x, int y, ArrayList<Piece> AllPieces){
        for(int i=0; i<AllPieces.size(); i++){
            if(AllPieces.get(i).position.getX() == x && AllPieces.get(i).position.getY() == y && AllPieces.get(i).alive == true){
                AllPieces.get(i).alive = false;
                return;
            }
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
