/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

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
    boolean isMax;
    //init the maximizer player in constractor
    
    public Node(ArrayList<Piece> nodeState, int alpha , int beta,boolean isMax){
        this.alpha = alpha;
        this.beta = beta;
        this.isMax=isMax;
        
    }
    
    private int minimax(Node node,int depth ,int a,int b,boolean isMax ){
        //coun
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
            //if(node.alpha > bestAlpha) { best node = node bestAlpha = node.alpha;
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
    //var count=0 
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
                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    for(int y =0 ; y<8;y++){//kol el amaken eli 3ala el x
                        if(y==node.PiecesState.get(i).position.getY())
                            continue;
                        ArrayList<Piece> listCopy = (ArrayList<Piece>)node.PiecesState.clone();
                        int x = (int)listCopy.get(i).position.getX();
                        if(listCopy.get(i).move(x,y)){
                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                    }
                }
                else if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Rook")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
                    
                }
                else if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Rook")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
                    
                }
                else if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("King")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
                    
                    ArrayList<Piece> listCopy = (ArrayList<Piece>)node.PiecesState.clone();
                    int x = (int)listCopy.get(i).position.getX();
                    int y = (int)listCopy.get(i).position.getY();
                    
                    //et7arak 7arrak yemiin
                    if(listCopy.get(i).move(x+1,y)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arraka shemal
                    if(listCopy.get(i).move(x-1,y)){
                       Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arrakka fo2
                    if(listCopy.get(i).move(x,y+1)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7araka ta7t
                    if(listCopy.get(i).move(x,y-1)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arraka diagonal fo2 yemin 
                    if(listCopy.get(i).move(x+1,y+1)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arrakka diagonal fo2 shemal
                    if(listCopy.get(i).move(x-1,y+1)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arrakka diagonal ta7t shemal
                    if(listCopy.get(i).move(x-1,y-1)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arrakka diagonal ta7t yemin
                    if(listCopy.get(i).move(x+1,y-1)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                }
                if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Knight")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
                    
                    ArrayList<Piece> listCopy = (ArrayList<Piece>)node.PiecesState.clone();
                    int x = (int)listCopy.get(i).position.getX();
                    int y = (int)listCopy.get(i).position.getY();
                    
                    if(listCopy.get(i).move(x+1, y+2)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    if(listCopy.get(i).move(x-1, y+2)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    if(listCopy.get(i).move(x+1, y-2)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    if(listCopy.get(i).move(x-1, y-2)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    if(listCopy.get(i).move(x+2, y+1)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    if(listCopy.get(i).move(x+2, y-1)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    if(listCopy.get(i).move(x-2, y+1)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    if(listCopy.get(i).move(x-2, y-1)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    
                    
                    
                    
                    
                }
                if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Rook")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
                    
                }
                if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Rook")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
                    
                }if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Rook")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
                    
                }
                
                
                
                
            }
        }
        //assign with isMax//assign with isMax
        return childrenNodesList;
    }
    public int getHeuristic (Node n){
        n.heuristic=1;
        return n.heuristic;
    }
}
