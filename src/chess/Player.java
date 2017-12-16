/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.util.ArrayList;

/**
 *
 * @author Mina
 */
public class Player {
    
    
    public ArrayList<Piece> PiecesSet;
    
    private Pawn pawn;
    private Bishop bishop;
    
    public void initSet(String color){
        //pawn = new Pawn();
    }
    
    Player(String color){
        initSet(color);
    }
    
}
