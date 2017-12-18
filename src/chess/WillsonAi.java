/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author ToniGeorge and DavidOsama
 */
public class WillsonAi {
    
    int depth;
    int a;
    int b;
    Boolean maximizingPlayer;
    // ArrayList <ArrayList> possibleStates ;
  
    public WillsonAi (int depth, boolean maximizingPlayer){
        this.a = Integer.MIN_VALUE;
        this.b = Integer.MAX_VALUE;
        this.maximizingPlayer = maximizingPlayer;
        this.depth = depth;
    }
    private int minimax(ArrayList<Piece> possibleState){
        if(this.depth==0){
            return heuristic(possibleState) ;
        }
        ArrayList <ArrayList> possibleChildrenStates = possibleMoves(possibleState);//fill the above arraylist with possible moves (nodes level)
        if(maximizingPlayer==true){
            for (int i=0; i<possibleChildrenStates.size(); i++){
                this.depth--;
                this.maximizingPlayer = false;
                this.a = Integer.max(a, minimax(possibleChildrenStates.get(i)));
                
                if(this.a >= this.b)
                    break;
            }
            return this.a ;
        }
        else{
            for (int i=0; i<possibleChildrenStates.size(); i++){
                this.depth--;
                this.maximizingPlayer = true;
                this.b = Integer.max(b, minimax(possibleChildrenStates.get(i)));
                
                if(this.a >= this.b)
                    break;
            }
            return this.b ;
        }    
          
    }
    
    private int heuristic (ArrayList<Piece> possibleState){ //AI is the white set
        int Score=0;
        Point WhiteKing= new Point(),BlackKing= new Point () ;
        for(int i=0; i < possibleState.size(); i++){
            if(possibleState.get(i).pieceType.equalsIgnoreCase("King") && possibleState.get(i).color.equalsIgnoreCase("White")){
            WhiteKing =possibleState.get(i).position;
            }
            else if(possibleState.get(i).pieceType.equalsIgnoreCase("King") && possibleState.get(i).color.equalsIgnoreCase("Black")){
            WhiteKing =possibleState.get(i).position;
            }
            
        
        }        
        
        for(int i=0; i < possibleState.size(); i++){
            if(possibleState.get(i).alive){
                int pieceValue=0;
                switch(possibleState.get(i).pieceType){
                    case "Pawn":
                        pieceValue=10;
                        break;
                    case "Knight":
                        pieceValue=30;
                        break;
                    case "Bishop":
                        pieceValue=30;
                        break;
                    case "Rook":
                        pieceValue=50;
                        break;
                    case "Queen":
                        pieceValue=90;
                        break;
                    case "King":
                        pieceValue=2000;
                        break;    
                }
                if(possibleState.get(i).color.equalsIgnoreCase("white")){
                    Score+=pieceValue;
                    int distance = (int) Math.sqrt( Math.pow(BlackKing.getX() - possibleState.get(i).position.getX(),2)+Math.pow(BlackKing.getY() - possibleState.get(i).position.getY(),2))*10;
                    Score-= distance ;
                }
                else {
                    Score-=pieceValue;
                    int distance = (int) Math.sqrt( Math.pow(WhiteKing.getX() - possibleState.get(i).position.getX(),2)+Math.pow(WhiteKing.getY() - possibleState.get(i).position.getY(),2))*10;
                    Score+= distance ;                    
                }
                
            }
        }
        return Score;
    }

    private ArrayList<ArrayList> possibleMoves(ArrayList<Piece> possibleState) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
