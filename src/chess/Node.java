/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Point;
import static java.lang.Integer.min;
import static java.lang.Math.max;
import java.util.ArrayList;

/**
 *
 * @author ToniGeorge
 */
public class Node {
    
    ArrayList<Piece> PiecesState;
    int heuristic;
    int alpha;//=-ve inf
    int beta;
//    int depth;
//    boolean isMax;
    //init the maximizer player in constractor
    
    public Node(ArrayList<Piece> nodeState, int alpha , int beta){
        this.alpha = alpha;
        this.beta = beta;
        
    }
    private int minimax(Node node,int depth ,int a,int b,boolean isMax ){
        if(depth==0){
            node.heuristic=getHeuristic(node);
            return node.heuristic ;
        }
        ////////////////////////////////
        ArrayList <Node> childrenNodes = getChildrenAndAssignMax(node,!isMax);//fill the above arraylist with possible moves (nodes level)
        if(isMax==true){
            for (int i=0; i<childrenNodes.size(); i++){
                node.alpha= Integer.max(node.alpha, minimax(childrenNodes.get(i),depth-1,a,b,false));
                
                if(node.alpha >= node.beta)
                    break;
            }
            node.heuristic=node.alpha;
            return node.alpha ;
        }
        else{
            for (int i=0; i<childrenNodes.size(); i++){
//                this.depth--;
//                this.maximizingPlayer = true;
                node.beta = Integer.min(node.beta, minimax(childrenNodes.get(i),depth-1,a,b,true));
                
                if(node.alpha >= node.beta)
                    break;
            }
            node.heuristic =node.beta;
            return node.beta ;
        }    
          
    }
    //possible moves
    private ArrayList<Node> getChildrenAndAssignMax(Node node,boolean isMax) {
        ArrayList<Node> childrenNodesList = new ArrayList();
        String color;
        if(isMax)
            color="White";
        else
            color="Black";
        for(int i =0;i<node.PiecesState.size();i++){
            
        }
        //parent node which contains a game state ( pieces with their positions ) 
        for(int i = 0 ; i<node.PiecesState.size();i++){
            
            if(node.PiecesState.get(i).alive){
                if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Rook")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
                    for(int x =0 ; x<8;x++){//kol el amaken eli 3ala el y 
                        if(x==node.PiecesState.get(i).position.getX())
                            continue;
                        ArrayList<Piece> listCopy = (ArrayList<Piece>)node.PiecesState.clone();
                        int y = (int)listCopy.get(i).position.getY();
                        if(listCopy.get(i).move(x,y)){
                            Node n= new Node(listCopy,node.alpha,node.beta);
                            childrenNodesList.add(n);
                        }
                    }
                    for(int y =0 ; y<8;y++){//kol el amaken eli 3ala el x
                        if(y==node.PiecesState.get(i).position.getY())
                            continue;
                        ArrayList<Piece> listCopy = (ArrayList<Piece>)node.PiecesState.clone();
                        int x = (int)listCopy.get(i).position.getX();
                        if(listCopy.get(i).move(x,y)){
                            Node n= new Node(listCopy,node.alpha,node.beta);
                            childrenNodesList.add(n);
                        }
                    }
                }
                else if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Bishop")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
                    ////////////////////TRIAL/////////////
//                    //*
//                    //  *
//                    //    *
//                    //      *
//                    int m = max((int)node.PiecesState.get(i).position.getX(),(int)node.PiecesState.get(i).position.getY());
//                    int nono = min((int)node.PiecesState.get(i).position.getX(),(int)node.PiecesState.get(i).position.getY());
//                    int start1X = (int)node.PiecesState.get(i).position.getX()-nono;
//                    int start1Y = (int)node.PiecesState.get(i).position.getY()-nono;
//                    int end1X = (int)node.PiecesState.get(i).position.getX()+ (7-m);
//                    int end1Y = (int)node.PiecesState.get(i).position.getY()+ (7-m);
////                    Point start1= new Point(start1X,start1Y);
////                    Point end1 = new Point(end1X, end1Y);
//                    
//                    //      *
//                    //    *
//                    //  *
//                    //*
//                    int start2X = (int)node.PiecesState.get(i).position.getX()+ nono;
//                    int start2Y = (int)node.PiecesState.get(i).position.getY()-nono;
//                    int end2X = (int)node.PiecesState.get(i).position.getX()-(7-nono);
//                    int end2Y = (int)node.PiecesState.get(i).position.getY()+(7-nono);
//
//                    
//                    
//                    
//                    for(int k = start1X; k <=end1X;k++)
//                    {
//                        if(k==node.PiecesState.get(i).position.getX()) {
//                            start1X++;
//                            start1Y++;
//                            continue;
//                        }
//                         ArrayList<Piece> listCopy = (ArrayList<Piece>) node.PiecesState.clone();
//                         if (listCopy.get(i).move(start1X, start1Y)) {
//                            Node n = new Node(listCopy, node.alpha, node.beta);
//                            childrenNodesList.add(n);
//                      } 
//                         start1X++;
//                         start1Y++;
//                    }
//                    
                    
                    ////// This is for shemaal l foo2
                    int y = (int) node.PiecesState.get(i).position.getY() - 1;
                    for (int x = (int) node.PiecesState.get(i).position.getX() - 1; x >= 0; x--) {
//                        for(int y=(int) node.PiecesState.get(i).position.getY()-1;y>0; y--)
                        ArrayList<Piece> listCopy = (ArrayList<Piece>) node.PiecesState.clone();
                        //int y = (int)listCopy.get(i).position.getY();
                        if (listCopy.get(i).move(x, y)) {
                            Node n = new Node(listCopy, node.alpha, node.beta);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y--;
                        if (y < 0) {
                            break;
                        }

                    }

                    //////This if or yemeeen l ta7ttt 
                    int y2 = (int) node.PiecesState.get(i).position.getY() + 1;
                    for (int x = (int) node.PiecesState.get(i).position.getX() + 1; x < 8; x++) {
//                        for(int y=(int) node.PiecesState.get(i).position.getY()-1;y>0; y--)
                        ArrayList<Piece> listCopy = (ArrayList<Piece>) node.PiecesState.clone();
                        //int y = (int)listCopy.get(i).position.getY();
                        if (listCopy.get(i).move(x, y2)) {
                            Node n = new Node(listCopy, node.alpha, node.beta);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y2++;
                        if (y2 > 8) {
                            break;
                        }
                    }
                    //Thiss s ofr fooo2 yemeeeen 
                    int y3 = (int) node.PiecesState.get(i).position.getY() - 1;
                    for (int x = (int) node.PiecesState.get(i).position.getX() + 1; x < 8; x++) {
                        ArrayList<Piece> listCopy = (ArrayList<Piece>) node.PiecesState.clone();
                        if (listCopy.get(i).move(x, y3)) {
                            Node n = new Node(listCopy, node.alpha, node.beta);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y3++;
                        if (y3 > 8) {
                            break;
                        }
                    }

                    /////// This is for ta7tt shemaaaalll
                    int y4 = (int) node.PiecesState.get(i).position.getY() + 1;
                    for (int x = (int) node.PiecesState.get(i).position.getX() - 1; x >= 0; x--) {
                        ArrayList<Piece> listCopy = (ArrayList<Piece>) node.PiecesState.clone();
                        if (listCopy.get(i).move(x, y4)) {
                            Node n = new Node(listCopy, node.alpha, node.beta);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y4--;
                        if (y4 < 0) {
                            break;
                        }
                    }

                }
                else if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Pawm")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
                    //kiro
                }
                else if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("King")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
                    
                    
                    
                    
                    
                    
                    
                    
                }
                if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Rook")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
                    
                }
                if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Rook")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
                    
                }
                if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Rook")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
                    
                }if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Rook")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
                    
                }
                
                
                
                
            }
        }
        //assign with isMax//assign with isMax
        return new ArrayList();
    }
    public int getHeuristic (Node n){
        n.heuristic=1;
        return n.heuristic;
    }
}
