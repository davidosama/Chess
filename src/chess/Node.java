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
 * @author ToniGeorge
 */
public class Node {
    
    ArrayList<Piece> PiecesState;
    int heuristic;
    int alpha;//=-ve inf
    int beta;
    static int RootDepth= GameBoard.depth;
    static ArrayList<Node> SecondNodesToChoose=new ArrayList();
    boolean isMax;
    
    //init the maximizer player in constractor
    
    
    public Node(){}
    
        public Node(ArrayList<Piece> nodeState, int alpha , int beta,boolean isMax){
        this.PiecesState=nodeState;
            
        this.alpha = alpha;
        this.beta = beta;
        this.isMax=isMax;
        
        
    }
        
    public static ArrayList<Piece> ClonePieces (ArrayList<Piece> AllPieces) throws CloneNotSupportedException{
        ArrayList<Piece> cloneList = new ArrayList<Piece>();
            for (int i = 0; i < AllPieces.size(); i++) {
                switch(AllPieces.get(i).pieceType){
                    case "Pawn":
                        Pawn p = (Pawn)AllPieces.get(i).clone();
                        cloneList.add(p);
                        break;
                    case "Rook":
                        Rook r = (Rook)AllPieces.get(i).clone();
                        cloneList.add(r);
                        break;
                    case "Queen":
                        Queen q = (Queen)AllPieces.get(i).clone();
                        cloneList.add(q);
                        break;
                    case "Knight":
                        Knight k = (Knight)AllPieces.get(i).clone();
                        cloneList.add(k);
                        break;
                    case "Bishop":
                       Bishop b = (Bishop)AllPieces.get(i).clone();
                       cloneList.add(b);
                       break;
                    case "King":
                       King king = (King)AllPieces.get(i).clone();
                       cloneList.add(king);
                       break;
                }
            }
            return cloneList;
    }
    
    private static int minimax(Node node,int depth ,int a,int b,boolean isMax ) throws CloneNotSupportedException{
        System.out.println("Beginning minimax");
        //coun
        if(depth==0){
            node.heuristic=heuristic3(node);
            System.out.println("Heuristic .  "+node.heuristic);
            return node.heuristic ;
        }
        System.out.println("after checking leaf nodes");
        ArrayList <Node> childrenNodes = getChildrenAndAssignMax(node,isMax);//fill the above arraylist with possible moves (nodes level)
        if(isMax==true){
            System.out.println("is Maximizer");
            for (int i=0; i<childrenNodes.size(); i++){
                node.alpha= Integer.max(node.alpha, minimax(childrenNodes.get(i),depth-1,node.alpha,node.beta,false));
                
                if(node.alpha >= node.beta)
                    break;
            }
            node.heuristic=node.alpha;
            if(depth == RootDepth){
                SecondNodesToChoose=childrenNodes;
            }
            System.out.println("Alpha IS "+node.alpha);
            System.out.println("depth is "+depth);
            return node.alpha ;
        }
        else{
            System.out.println("is Minimizer");
            for (int i=0; i<childrenNodes.size(); i++){
                node.beta = Integer.min(node.beta, minimax(childrenNodes.get(i),depth-1,node.alpha,node.beta,true));
                
                if(node.alpha >= node.beta)
                   break;
            }
            node.heuristic =node.beta;
            if(depth == RootDepth){
                SecondNodesToChoose=childrenNodes;
            }
            System.out.println("BETA IS "+node.beta);
            System.out.println("depth is "+depth);
            return node.beta ;
            
        }    
          
    }
    
    private static ArrayList<Node> getChildrenAndAssignMax(Node node,boolean isMax) throws CloneNotSupportedException {

        ArrayList<Node> childrenNodesList = new ArrayList();
        String color;
        if (isMax) {
            color = "White";
        } else {
            color = "Black";
        }

        if (color=="Black") {
            for (int i = 0; i <= 7; i++) {
                node.isMax = isMax;
                if (node.PiecesState.get(i).pieceType.equalsIgnoreCase("Pawn") && node.PiecesState.get(i).color.equalsIgnoreCase(color) && node.PiecesState.get(i).alive) {
                    if (node.PiecesState.get(i).numOfMoves == 0) {
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        int y = (int) listCopy.get(i).position.getY() - 2;
                        int x = (int) listCopy.get(i).position.getX();
                        if (listCopy.get(i).moveAI(x, y, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            childrenNodesList.add(n);
                        }

                    }

                    ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                    int y = (int) listCopy.get(i).position.getY() - 1;
                    int x = (int) listCopy.get(i).position.getX();
                    if (listCopy.get(i).moveAI(x, y, listCopy)) {
                        Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                        childrenNodesList.add(n);
                    }

                    listCopy = Node.ClonePieces(node.PiecesState);
                    y = (int) listCopy.get(i).position.getY() - 1;
                    x = (int) listCopy.get(i).position.getX() + 1;
                    if (listCopy.get(i).moveAI(x, y, listCopy)) {
                        Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                        childrenNodesList.add(n);
                    }
                    listCopy = Node.ClonePieces(node.PiecesState);
                    y = (int) listCopy.get(i).position.getY() - 1;
                    x = (int) listCopy.get(i).position.getX() - 1;
                    if (listCopy.get(i).moveAI(x, y, listCopy)) {
                        Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                        childrenNodesList.add(n);
                    }

                }

            }
            for (int i = 16; i <= 17; i++) {
                node.isMax = isMax;
                if (node.PiecesState.get(i).pieceType.equalsIgnoreCase("Knight") && node.PiecesState.get(i).color.equalsIgnoreCase(color) && node.PiecesState.get(i).alive) {

                    ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                    int x = (int) listCopy.get(i).position.getX();
                    int y = (int) listCopy.get(i).position.getY();
                    if (x + 1 >= 0 && x + 1 <= 7 && y + 2 >= 0 && y + 2 <= 7) {
                        if (listCopy.get(i).moveAI(x + 1, y + 2, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    if (x - 1 >= 0 && x - 1 <= 7 && y + 2 >= 0 && y + 2 <= 7) {
                        if (listCopy.get(i).moveAI(x - 1, y + 2, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    if (x + 1 >= 0 && x + 1 <= 7 && y - 2 >= 0 && y - 2 <= 7) {
                        if (listCopy.get(i).moveAI(x + 1, y - 2, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    if (x - 1 >= 0 && x - 1 <= 7 && y - 2 >= 0 && y - 2 <= 7) {
                        if (listCopy.get(i).moveAI(x - 1, y - 2, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    if (x + 2 >= 0 && x + 2 <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                        if (listCopy.get(i).moveAI(x + 2, y + 1, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    if (x + 2 >= 0 && x + 2 <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                        if (listCopy.get(i).moveAI(x + 2, y - 1, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    if (x - 2 >= 0 && x - 2 <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                        if (listCopy.get(i).moveAI(x - 2, y + 1, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    if (x - 2 >= 0 && x - 2 <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                        if (listCopy.get(i).moveAI(x - 2, y - 1, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            childrenNodesList.add(n);
                        }
                    }

                }
            }

            for (int i = 22; i <= 23; i++) {
                node.isMax=isMax;
                if (node.PiecesState.get(i).pieceType.equalsIgnoreCase("Rook") && node.PiecesState.get(i).color.equalsIgnoreCase(color)&& node.PiecesState.get(i).alive) {
                    ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                    int y = (int) listCopy.get(i).position.getY();
                    //loop on x values--> same row
                    for (int x = 0; x < 8; x++) {
                        //kol el amaken eli 3ala el x
                        listCopy = Node.ClonePieces(node.PiecesState);
                        if (x == node.PiecesState.get(i).position.getX()) {
                            continue;
                        } else if (listCopy.get(i).moveAI(x, y, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            System.out.println("Node.Alpha is " + node.alpha + "  Node.Beta is " + node.beta);
                            childrenNodesList.add(n);
                        }
                    }
                    //loop on different y values --> same column
                    int x = (int) listCopy.get(i).position.getX();
                    for (y = 0; y < 8; y++) {//kol el amaken eli 3ala el x
                        listCopy = Node.ClonePieces(node.PiecesState);
                        if (y == node.PiecesState.get(i).position.getY()) {
                            continue;
                        } else if (listCopy.get(i).moveAI(x, y, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            childrenNodesList.add(n);

                        }
                    }
                }
            }
            for(int i=24;i<=25;i++){
                node.isMax=isMax;
                if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Bishop")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)&& node.PiecesState.get(i).alive){
                    ////// This is for fooo2 shemaaal
                    int y = (int) node.PiecesState.get(i).position.getY() - 1;
                    for (int x = (int) node.PiecesState.get(i).position.getX() - 1; x >= 0; x--) {
//                        for(int y=(int) node.PiecesState.get(i).position.getY()-1;y>0; y--)
                        if (y < 0) {
                            break;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        //int y = (int)listCopy.get(i).position.getY();
                        if (listCopy.get(i).moveAI(x,y,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y--;
                        if (y < 0) {
                            break;
                        }
                    }

                    //////This if or ta7t yemeeeen
                    int y2 = (int) node.PiecesState.get(i).position.getY() + 1;
                    for (int x = (int) node.PiecesState.get(i).position.getX() + 1; x < 8; x++) {
                        if (y2 >= 8) {
                            break;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        //int y = (int)listCopy.get(i).position.getY();
                        if (listCopy.get(i).moveAI(x,y2,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y2++;
                        if (y2 >= 8) {
                            break;
                        }
                    }
                    
                    //Thiss is for fooo2 yemeeeen 
                    int y3 = (int) node.PiecesState.get(i).position.getY() - 1;
                    for (int x = (int) node.PiecesState.get(i).position.getX() + 1; x < 8; x++) {
                         if (y < 0) {
                            break;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        if (listCopy.get(i).moveAI(x, y3,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y3--;
                        if (y3 <0 ) {
                            break;
                        }
                    }
                    
                    /////// This is for ta7tt shemaaaalll
                    int y4 = (int) node.PiecesState.get(i).position.getY() + 1;
                    for (int x = (int) node.PiecesState.get(i).position.getX() - 1; x >= 0; x--) {
                         if (y4 >= 8) {
                            break;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        if (listCopy.get(i).moveAI(x, y4,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y4++;
                        if (y4 >= 8) {
                            break;
                        }
                    }

                }
            
            }
            if (node.PiecesState.get(28).pieceType.equalsIgnoreCase("Queen") && node.PiecesState.get(28).color.equalsIgnoreCase(color) && node.PiecesState.get(28).alive) {
                    node.isMax=isMax;
                    for (int x = 0; x < 8; x++) {//kol el amaken eli 3ala el y (VERTICALLY)
                        if (x == node.PiecesState.get(28).position.getX()) {
                            continue;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        int y = (int) listCopy.get(28).position.getY();
                        if (listCopy.get(28).moveAI(x, y,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    for (int y = 0; y < 8; y++) {//kol el amaken eli 3ala el x (HORIZONTALLY)
                        if (y == node.PiecesState.get(28).position.getY()) {
                            continue;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        int x = (int) listCopy.get(28).position.getX();
                        if (listCopy.get(28).moveAI(x, y,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    
              ///////////////////// DIAGONAL ////////////////////
                    
                    ////// This is for fooo2 shemaaal
                    int y = (int) node.PiecesState.get(28).position.getY() - 1;
                    for (int x = (int) node.PiecesState.get(28).position.getX() - 1; x >= 0; x--) {
//                        for(int y=(int) node.PiecesState.get(i).position.getY()-1;y>0; y--)
                        if (y < 0) {
                            break;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        //int y = (int)listCopy.get(i).position.getY();
                        if (listCopy.get(28).moveAI(x, y,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y--;
                        if (y < 0) {
                            break;
                        }
                    }

                    //////This if or ta7t yemeeeen
                    int y2 = (int) node.PiecesState.get(28).position.getY() + 1;
                    for (int x = (int) node.PiecesState.get(28).position.getX() + 1; x < 8; x++) {
                        if (y2 >= 8) {
                            break;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        //int y = (int)listCopy.get(i).position.getY();
                        if (listCopy.get(28).moveAI(x, y2,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y2++;
                        if (y2 >= 8) {
                            break;
                        }
                    }
                    
                    //Thiss is for fooo2 yemeeeen 
                    int y3 = (int) node.PiecesState.get(28).position.getY() - 1;
                    for (int x = (int) node.PiecesState.get(28).position.getX() + 1; x < 8; x++) {
                         if (y < 0) {
                            break;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        if (listCopy.get(28).moveAI(x, y3,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y3--;
                        if (y3 <0 ) {
                            break;
                        }
                    }
                    
                    /////// This is for ta7tt shemaaaalll
                    int y4 = (int) node.PiecesState.get(28).position.getY() + 1;
                    for (int x = (int) node.PiecesState.get(28).position.getX() - 1; x >= 0; x--) {
                         if (y4 >= 8) {
                            break;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        if (listCopy.get(28).moveAI(x, y4,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y4++;
                        if (y4 >= 8) {
                            break;
                        }
                    }
                }
            if(node.PiecesState.get(30).pieceType.equalsIgnoreCase("King")&&node.PiecesState.get(30).color.equalsIgnoreCase(color)&& node.PiecesState.get(30).alive){
                    node.isMax=isMax;
                    ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                    int x = (int)listCopy.get(30).position.getX();
                    int y = (int)listCopy.get(30).position.getY();
                    
                    //et7arak 7arrak yemiin
                    if(listCopy.get(30).moveAI(x+1,y,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arraka shemal
                    if(listCopy.get(30).moveAI(x-1,y,listCopy)){
                       Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arrakka fo2
                    if(listCopy.get(30).moveAI(x,y-1,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7araka ta7t
                    if(listCopy.get(30).moveAI(x,y+1,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arraka diagonal fo2 yemin 
                    if(listCopy.get(30).moveAI(x+1,y-1,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arrakka diagonal fo2 shemal
                    if(listCopy.get(30).moveAI(x-1,y-1,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arrakka diagonal ta7t shemal
                    if(listCopy.get(30).moveAI(x-1,y+1,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arrakka diagonal ta7t yemin
                    if(listCopy.get(30).moveAI(x+1,y+1,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                }
            

        } else {
             for (int i = 8; i <= 15; i++) {
                node.isMax = isMax;
                if (node.PiecesState.get(i).pieceType.equalsIgnoreCase("Pawn") && node.PiecesState.get(i).color.equalsIgnoreCase(color) && node.PiecesState.get(i).alive) {
                        if(node.PiecesState.get(i).numOfMoves==0){
                            ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                            int y = (int)listCopy.get(i).position.getY()+2;
                            int x = (int)listCopy.get(i).position.getX();
                            if(listCopy.get(i).moveAI(x,y,listCopy)){
                                Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                                childrenNodesList.add(n);
                        }
                        }
                        
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        int y = (int)listCopy.get(i).position.getY()+1;
                        int x = (int)listCopy.get(i).position.getX();
                        if(listCopy.get(i).moveAI(x,y,listCopy)){
                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                        listCopy = Node.ClonePieces(node.PiecesState);
                         y = (int)listCopy.get(i).position.getY()+1;
                         x = (int)listCopy.get(i).position.getX()+1;
                        if(listCopy.get(i).moveAI(x,y,listCopy)){
                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                       listCopy = Node.ClonePieces(node.PiecesState);
                        y = (int)listCopy.get(i).position.getY()+1;
                        x = (int)listCopy.get(i).position.getX()-1;
                        if(listCopy.get(i).moveAI(x,y,listCopy)){
                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                            childrenNodesList.add(n);
                        }

  

   


                }

            }
            for (int i = 18; i <= 19; i++) {
                node.isMax = isMax;
                if (node.PiecesState.get(i).pieceType.equalsIgnoreCase("Knight") && node.PiecesState.get(i).color.equalsIgnoreCase(color) && node.PiecesState.get(i).alive) {

                    ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                    int x = (int) listCopy.get(i).position.getX();
                    int y = (int) listCopy.get(i).position.getY();
                    if (x + 1 >= 0 && x + 1 <= 7 && y + 2 >= 0 && y + 2 <= 7) {
                        if (listCopy.get(i).moveAI(x + 1, y + 2, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    if (x - 1 >= 0 && x - 1 <= 7 && y + 2 >= 0 && y + 2 <= 7) {
                        if (listCopy.get(i).moveAI(x - 1, y + 2, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    if (x + 1 >= 0 && x + 1 <= 7 && y - 2 >= 0 && y - 2 <= 7) {
                        if (listCopy.get(i).moveAI(x + 1, y - 2, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    if (x - 1 >= 0 && x - 1 <= 7 && y - 2 >= 0 && y - 2 <= 7) {
                        if (listCopy.get(i).moveAI(x - 1, y - 2, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    if (x + 2 >= 0 && x + 2 <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                        if (listCopy.get(i).moveAI(x + 2, y + 1, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    if (x + 2 >= 0 && x + 2 <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                        if (listCopy.get(i).moveAI(x + 2, y - 1, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    if (x - 2 >= 0 && x - 2 <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                        if (listCopy.get(i).moveAI(x - 2, y + 1, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    if (x - 2 >= 0 && x - 2 <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                        if (listCopy.get(i).moveAI(x - 2, y - 1, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            childrenNodesList.add(n);
                        }
                    }

                }
            }

            for (int i = 20; i <= 21; i++) {
                node.isMax = isMax;
                if (node.PiecesState.get(i).pieceType.equalsIgnoreCase("Rook") && node.PiecesState.get(i).color.equalsIgnoreCase(color)&& node.PiecesState.get(i).alive) {
                    ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                    int y = (int) listCopy.get(i).position.getY();
                    //loop on x values--> same row
                    for (int x = 0; x < 8; x++) {
                        //kol el amaken eli 3ala el x
                        listCopy = Node.ClonePieces(node.PiecesState);
                        if (x == node.PiecesState.get(i).position.getX()) {
                            continue;
                        } else if (listCopy.get(i).moveAI(x, y, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            System.out.println("Node.Alpha is " + node.alpha + "  Node.Beta is " + node.beta);
                            childrenNodesList.add(n);
                        }
                    }
                    //loop on different y values --> same column
                    int x = (int) listCopy.get(i).position.getX();
                    for (y = 0; y < 8; y++) {//kol el amaken eli 3ala el x
                        listCopy = Node.ClonePieces(node.PiecesState);
                        if (y == node.PiecesState.get(i).position.getY()) {
                            continue;
                        } else if (listCopy.get(i).moveAI(x, y, listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta, isMax);
                            childrenNodesList.add(n);

                        }
                    }
                }
            }
            for(int i=26;i<=27;i++){
                node.isMax = isMax;
                if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Bishop")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)&& node.PiecesState.get(i).alive){
                    ////// This is for fooo2 shemaaal
                    int y = (int) node.PiecesState.get(i).position.getY() - 1;
                    for (int x = (int) node.PiecesState.get(i).position.getX() - 1; x >= 0; x--) {
//                        for(int y=(int) node.PiecesState.get(i).position.getY()-1;y>0; y--)
                        if (y < 0) {
                            break;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        //int y = (int)listCopy.get(i).position.getY();
                        if (listCopy.get(i).moveAI(x,y,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y--;
                        if (y < 0) {
                            break;
                        }
                    }

                    //////This if or ta7t yemeeeen
                    int y2 = (int) node.PiecesState.get(i).position.getY() + 1;
                    for (int x = (int) node.PiecesState.get(i).position.getX() + 1; x < 8; x++) {
                        if (y2 >= 8) {
                            break;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        //int y = (int)listCopy.get(i).position.getY();
                        if (listCopy.get(i).moveAI(x,y2,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y2++;
                        if (y2 >= 8) {
                            break;
                        }
                    }
                    
                    //Thiss is for fooo2 yemeeeen 
                    int y3 = (int) node.PiecesState.get(i).position.getY() - 1;
                    for (int x = (int) node.PiecesState.get(i).position.getX() + 1; x < 8; x++) {
                         if (y < 0) {
                            break;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        if (listCopy.get(i).moveAI(x, y3,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y3--;
                        if (y3 <0 ) {
                            break;
                        }
                    }
                    
                    /////// This is for ta7tt shemaaaalll
                    int y4 = (int) node.PiecesState.get(i).position.getY() + 1;
                    for (int x = (int) node.PiecesState.get(i).position.getX() - 1; x >= 0; x--) {
                         if (y4 >= 8) {
                            break;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        if (listCopy.get(i).moveAI(x, y4,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y4++;
                        if (y4 >= 8) {
                            break;
                        }
                    }

                }
            
            }
            if (node.PiecesState.get(29).pieceType.equalsIgnoreCase("Queen") && node.PiecesState.get(29).color.equalsIgnoreCase(color) && node.PiecesState.get(29).alive) {
                    node.isMax = isMax;
                    for (int x = 0; x < 8; x++) {//kol el amaken eli 3ala el y (VERTICALLY)
                        if (x == node.PiecesState.get(28).position.getX()) {
                            continue;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        int y = (int) listCopy.get(29).position.getY();
                        if (listCopy.get(29).moveAI(x, y,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    for (int y = 0; y < 8; y++) {//kol el amaken eli 3ala el x (HORIZONTALLY)
                        if (y == node.PiecesState.get(29).position.getY()) {
                            continue;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        int x = (int) listCopy.get(29).position.getX();
                        if (listCopy.get(29).moveAI(x, y,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    
              ///////////////////// DIAGONAL ////////////////////
                    
                    ////// This is for fooo2 shemaaal
                    int y = (int) node.PiecesState.get(29).position.getY() - 1;
                    for (int x = (int) node.PiecesState.get(29).position.getX() - 1; x >= 0; x--) {
//                        for(int y=(int) node.PiecesState.get(i).position.getY()-1;y>0; y--)
                        if (y < 0) {
                            break;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        //int y = (int)listCopy.get(i).position.getY();
                        if (listCopy.get(29).moveAI(x, y,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y--;
                        if (y < 0) {
                            break;
                        }
                    }

                    //////This if or ta7t yemeeeen
                    int y2 = (int) node.PiecesState.get(29).position.getY() + 1;
                    for (int x = (int) node.PiecesState.get(29).position.getX() + 1; x < 8; x++) {
                        if (y2 >= 8) {
                            break;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        //int y = (int)listCopy.get(i).position.getY();
                        if (listCopy.get(29).moveAI(x, y2,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y2++;
                        if (y2 >= 8) {
                            break;
                        }
                    }
                    
                    //Thiss is for fooo2 yemeeeen 
                    int y3 = (int) node.PiecesState.get(29).position.getY() - 1;
                    for (int x = (int) node.PiecesState.get(29).position.getX() + 1; x < 8; x++) {
                         if (y < 0) {
                            break;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        if (listCopy.get(29).moveAI(x, y3,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y3--;
                        if (y3 <0 ) {
                            break;
                        }
                    }
                    
                    /////// This is for ta7tt shemaaaalll
                    int y4 = (int) node.PiecesState.get(29).position.getY() + 1;
                    for (int x = (int) node.PiecesState.get(29).position.getX() - 1; x >= 0; x--) {
                         if (y4 >= 8) {
                            break;
                        }
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        if (listCopy.get(29).moveAI(x, y4,listCopy)) {
                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
                            childrenNodesList.add(n);
                        } else {
                            break;
                        }
                        y4++;
                        if (y4 >= 8) {
                            break;
                        }
                    }
                }
            if(node.PiecesState.get(31).pieceType.equalsIgnoreCase("King")&&node.PiecesState.get(31).color.equalsIgnoreCase(color)&& node.PiecesState.get(31).alive){
                    node.isMax = isMax;
                    ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                    int x = (int)listCopy.get(31).position.getX();
                    int y = (int)listCopy.get(31).position.getY();
                    
                    //et7arak 7arrak yemiin
                    if(listCopy.get(31).moveAI(x+1,y,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arraka shemal
                    if(listCopy.get(31).moveAI(x-1,y,listCopy)){
                       Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arrakka fo2
                    if(listCopy.get(31).moveAI(x,y-1,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7araka ta7t
                    if(listCopy.get(31).moveAI(x,y+1,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arraka diagonal fo2 yemin 
                    if(listCopy.get(31).moveAI(x+1,y-1,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arrakka diagonal fo2 shemal
                    if(listCopy.get(31).moveAI(x-1,y-1,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arrakka diagonal ta7t shemal
                    if(listCopy.get(31).moveAI(x-1,y+1,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arrakka diagonal ta7t yemin
                    if(listCopy.get(31).moveAI(x+1,y+1,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                }           

        }
                return childrenNodesList;
    }
        
        
        
        
        
        
        
        
        //parent node which contains a game state ( pieces with their positions ) 
//        for(int i=20;i<=23;i++){
//            node.isMax=isMax;
//            if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Rook")&&node.PiecesState.get(i).color.equalsIgnoreCase(color) && node.PiecesState.get(i).alive){
//                    ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                    int y = (int)listCopy.get(i).position.getY();
//                    //loop on x values--> same row
//                    for(int x = 0 ; x<8;x++){
//                        //kol el amaken eli 3ala el x
//                        listCopy=Node.ClonePieces(node.PiecesState);
//                        if(x==node.PiecesState.get(i).position.getX())
//                            continue;
//                        else if(listCopy.get(i).moveAI(x,y,listCopy)){
//                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                            System.out.println("Node.Alpha is "+node.alpha+"  Node.Beta is "+node.beta);
//                            childrenNodesList.add(n);
//                        }
//                    }
//                    //loop on different y values --> same column
//                    int x = (int)listCopy.get(i).position.getX();
//                    for(y =0 ; y<8;y++){//kol el amaken eli 3ala el x
//                        listCopy=Node.ClonePieces(node.PiecesState);
//                        if(y==node.PiecesState.get(i).position.getY())
//                            continue;
//                        else if(listCopy.get(i).moveAI(x,y,listCopy)){
//                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                            childrenNodesList.add(n);
//                            
//                        }
//                    }
//                
//            }
//        }
//        for(int i=24 ;i<=27;i++){
//            node.isMax=isMax;
//        
//        }
        
            
//        for(int i = 0 ; i<node.PiecesState.size();i++){
//            node.isMax=isMax;
//            if(node.PiecesState.get(i).alive){
//                if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Pawn")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
//                    //kiro   get color > black up  y-1 > true >  
//                    // black
//                    if(node.PiecesState.get(i).color.equals("Black")){
//                        if(node.PiecesState.get(i).numOfMoves==0){
//                            ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                            int y = (int)listCopy.get(i).position.getY()-2;
//                            int x = (int)listCopy.get(i).position.getX();
//                            if(listCopy.get(i).moveAI(x,y,listCopy)){
//                                Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                                childrenNodesList.add(n);
//                        }
//                            
//                            
//                        
//                        }
//                        
//                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                            int y = (int)listCopy.get(i).position.getY()-1;
//                            int x = (int)listCopy.get(i).position.getX();
//                            if(listCopy.get(i).moveAI(x,y,listCopy)){
//                                Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                                childrenNodesList.add(n);
//                            }
//                        
//                            listCopy = Node.ClonePieces(node.PiecesState);
//                             y = (int)listCopy.get(i).position.getY()-1;
//                             x = (int)listCopy.get(i).position.getX()+1;
//                            if(listCopy.get(i).moveAI(x,y,listCopy)){
//                                Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                                childrenNodesList.add(n);
//                            }
//                           listCopy = Node.ClonePieces(node.PiecesState);
//                            y = (int)listCopy.get(i).position.getY()-1;
//                            x = (int)listCopy.get(i).position.getX()-1;
//                            if(listCopy.get(i).moveAI(x,y,listCopy)){
//                                Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                                childrenNodesList.add(n);
//                            }
//                    }
//                    if(node.PiecesState.get(i).color.equals("White")){
//                       
//                        if(node.PiecesState.get(i).numOfMoves==0){
//                            ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                            int y = (int)listCopy.get(i).position.getY()+2;
//                            int x = (int)listCopy.get(i).position.getX();
//                            if(listCopy.get(i).moveAI(x,y,listCopy)){
//                                Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                                childrenNodesList.add(n);
//                        }
//                        }
//                        
//                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                        int y = (int)listCopy.get(i).position.getY()+1;
//                        int x = (int)listCopy.get(i).position.getX();
//                        if(listCopy.get(i).moveAI(x,y,listCopy)){
//                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                            childrenNodesList.add(n);
//                        }
//                        listCopy = Node.ClonePieces(node.PiecesState);
//                         y = (int)listCopy.get(i).position.getY()+1;
//                         x = (int)listCopy.get(i).position.getX()+1;
//                        if(listCopy.get(i).moveAI(x,y,listCopy)){
//                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                            childrenNodesList.add(n);
//                        }
//                       listCopy = Node.ClonePieces(node.PiecesState);
//                        y = (int)listCopy.get(i).position.getY()+1;
//                        x = (int)listCopy.get(i).position.getX()-1;
//                        if(listCopy.get(i).moveAI(x,y,listCopy)){
//                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                            childrenNodesList.add(n);
//                        }
//                    }
//                }
//                //rook
//                else if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Rook")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
//                    ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                    int y = (int)listCopy.get(i).position.getY();
//                    //loop on x values--> same row
//                    for(int x = 0 ; x<8;x++){
//                        //kol el amaken eli 3ala el x
//                        listCopy=Node.ClonePieces(node.PiecesState);
//                        if(x==node.PiecesState.get(i).position.getX())
//                            continue;
//                        else if(listCopy.get(i).moveAI(x,y,listCopy)){
//                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                            System.out.println("Node.Alpha is "+node.alpha+"  Node.Beta is "+node.beta);
//                            childrenNodesList.add(n);
//                        }
//                    }
//                    //loop on different y values --> same column
//                    int x = (int)listCopy.get(i).position.getX();
//                    for(y =0 ; y<8;y++){//kol el amaken eli 3ala el x
//                        listCopy=Node.ClonePieces(node.PiecesState);
//                        if(y==node.PiecesState.get(i).position.getY())
//                            continue;
//                        else if(listCopy.get(i).moveAI(x,y,listCopy)){
//                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                            childrenNodesList.add(n);
//                            
//                        }
//                    }
//                }
//                
//                
//                else if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Bishop")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
//                    ////// This is for fooo2 shemaaal
//                    int y = (int) node.PiecesState.get(i).position.getY() - 1;
//                    for (int x = (int) node.PiecesState.get(i).position.getX() - 1; x >= 0; x--) {
////                        for(int y=(int) node.PiecesState.get(i).position.getY()-1;y>0; y--)
//                        if (y < 0) {
//                            break;
//                        }
//                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                        //int y = (int)listCopy.get(i).position.getY();
//                        if (listCopy.get(i).moveAI(x,y,listCopy)) {
//                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
//                            childrenNodesList.add(n);
//                        } else {
//                            break;
//                        }
//                        y--;
//                        if (y < 0) {
//                            break;
//                        }
//                    }
//
//                    //////This if or ta7t yemeeeen
//                    int y2 = (int) node.PiecesState.get(i).position.getY() + 1;
//                    for (int x = (int) node.PiecesState.get(i).position.getX() + 1; x < 8; x++) {
//                        if (y2 >= 8) {
//                            break;
//                        }
//                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                        //int y = (int)listCopy.get(i).position.getY();
//                        if (listCopy.get(i).moveAI(x,y2,listCopy)) {
//                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
//                            childrenNodesList.add(n);
//                        } else {
//                            break;
//                        }
//                        y2++;
//                        if (y2 >= 8) {
//                            break;
//                        }
//                    }
//                    
//                    //Thiss is for fooo2 yemeeeen 
//                    int y3 = (int) node.PiecesState.get(i).position.getY() - 1;
//                    for (int x = (int) node.PiecesState.get(i).position.getX() + 1; x < 8; x++) {
//                         if (y < 0) {
//                            break;
//                        }
//                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                        if (listCopy.get(i).moveAI(x, y3,listCopy)) {
//                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
//                            childrenNodesList.add(n);
//                        } else {
//                            break;
//                        }
//                        y3--;
//                        if (y3 <0 ) {
//                            break;
//                        }
//                    }
//                    
//                    /////// This is for ta7tt shemaaaalll
//                    int y4 = (int) node.PiecesState.get(i).position.getY() + 1;
//                    for (int x = (int) node.PiecesState.get(i).position.getX() - 1; x >= 0; x--) {
//                         if (y4 >= 8) {
//                            break;
//                        }
//                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                        if (listCopy.get(i).moveAI(x, y4,listCopy)) {
//                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
//                            childrenNodesList.add(n);
//                        } else {
//                            break;
//                        }
//                        y4++;
//                        if (y4 >= 8) {
//                            break;
//                        }
//                    }
//
//                }
//                else if (node.PiecesState.get(i).pieceType.equalsIgnoreCase("Queen") && node.PiecesState.get(i).color.equalsIgnoreCase(color)) {
//
//                    for (int x = 0; x < 8; x++) {//kol el amaken eli 3ala el y (VERTICALLY)
//                        if (x == node.PiecesState.get(i).position.getX()) {
//                            continue;
//                        }
//                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                        int y = (int) listCopy.get(i).position.getY();
//                        if (listCopy.get(i).moveAI(x, y,listCopy)) {
//                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
//                            childrenNodesList.add(n);
//                        }
//                    }
//                    for (int y = 0; y < 8; y++) {//kol el amaken eli 3ala el x (HORIZONTALLY)
//                        if (y == node.PiecesState.get(i).position.getY()) {
//                            continue;
//                        }
//                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                        int x = (int) listCopy.get(i).position.getX();
//                        if (listCopy.get(i).moveAI(x, y,listCopy)) {
//                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
//                            childrenNodesList.add(n);
//                        }
//                    }
//                    
//              ///////////////////// DIAGONAL ////////////////////
//                    
//                    ////// This is for fooo2 shemaaal
//                    int y = (int) node.PiecesState.get(i).position.getY() - 1;
//                    for (int x = (int) node.PiecesState.get(i).position.getX() - 1; x >= 0; x--) {
////                        for(int y=(int) node.PiecesState.get(i).position.getY()-1;y>0; y--)
//                        if (y < 0) {
//                            break;
//                        }
//                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                        //int y = (int)listCopy.get(i).position.getY();
//                        if (listCopy.get(i).moveAI(x, y,listCopy)) {
//                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
//                            childrenNodesList.add(n);
//                        } else {
//                            break;
//                        }
//                        y--;
//                        if (y < 0) {
//                            break;
//                        }
//                    }
//
//                    //////This if or ta7t yemeeeen
//                    int y2 = (int) node.PiecesState.get(i).position.getY() + 1;
//                    for (int x = (int) node.PiecesState.get(i).position.getX() + 1; x < 8; x++) {
//                        if (y2 >= 8) {
//                            break;
//                        }
//                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                        //int y = (int)listCopy.get(i).position.getY();
//                        if (listCopy.get(i).moveAI(x, y2,listCopy)) {
//                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
//                            childrenNodesList.add(n);
//                        } else {
//                            break;
//                        }
//                        y2++;
//                        if (y2 >= 8) {
//                            break;
//                        }
//                    }
//                    
//                    //Thiss is for fooo2 yemeeeen 
//                    int y3 = (int) node.PiecesState.get(i).position.getY() - 1;
//                    for (int x = (int) node.PiecesState.get(i).position.getX() + 1; x < 8; x++) {
//                         if (y < 0) {
//                            break;
//                        }
//                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                        if (listCopy.get(i).moveAI(x, y3,listCopy)) {
//                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
//                            childrenNodesList.add(n);
//                        } else {
//                            break;
//                        }
//                        y3--;
//                        if (y3 <0 ) {
//                            break;
//                        }
//                    }
//                    
//                    /////// This is for ta7tt shemaaaalll
//                    int y4 = (int) node.PiecesState.get(i).position.getY() + 1;
//                    for (int x = (int) node.PiecesState.get(i).position.getX() - 1; x >= 0; x--) {
//                         if (y4 >= 8) {
//                            break;
//                        }
//                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                        if (listCopy.get(i).moveAI(x, y4,listCopy)) {
//                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
//                            childrenNodesList.add(n);
//                        } else {
//                            break;
//                        }
//                        y4++;
//                        if (y4 >= 8) {
//                            break;
//                        }
//                    }
//                }
//                
//                
//                
//                else if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("King")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
//                    
//                    ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                    int x = (int)listCopy.get(i).position.getX();
//                    int y = (int)listCopy.get(i).position.getY();
//                    
//                    //et7arak 7arrak yemiin
//                    if(listCopy.get(i).moveAI(x+1,y,listCopy)){
//                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                        childrenNodesList.add(n);
//                    }
//                    //et7arak 7arraka shemal
//                    if(listCopy.get(i).moveAI(x-1,y,listCopy)){
//                       Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                        childrenNodesList.add(n);
//                    }
//                    //et7arak 7arrakka fo2
//                    if(listCopy.get(i).moveAI(x,y-1,listCopy)){
//                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                        childrenNodesList.add(n);
//                    }
//                    //et7arak 7araka ta7t
//                    if(listCopy.get(i).moveAI(x,y+1,listCopy)){
//                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                        childrenNodesList.add(n);
//                    }
//                    //et7arak 7arraka diagonal fo2 yemin 
//                    if(listCopy.get(i).moveAI(x+1,y-1,listCopy)){
//                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                        childrenNodesList.add(n);
//                    }
//                    //et7arak 7arrakka diagonal fo2 shemal
//                    if(listCopy.get(i).moveAI(x-1,y-1,listCopy)){
//                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                        childrenNodesList.add(n);
//                    }
//                    //et7arak 7arrakka diagonal ta7t shemal
//                    if(listCopy.get(i).moveAI(x-1,y+1,listCopy)){
//                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                        childrenNodesList.add(n);
//                    }
//                    //et7arak 7arrakka diagonal ta7t yemin
//                    if(listCopy.get(i).moveAI(x+1,y+1,listCopy)){
//                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                        childrenNodesList.add(n);
//                    }
//                }
//                if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Knight")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
//                    
//                    ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                    int x = (int)listCopy.get(i).position.getX();
//                    int y = (int)listCopy.get(i).position.getY();
//                    if(x+1>=0&&x+1<=7&&y+2>=0&&y+2<=7)
//                        if(listCopy.get(i).moveAI(x+1, y+2,listCopy)){
//                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                            childrenNodesList.add(n);
//                        }
//                    if(x-1>=0&&x-1<=7&&y+2>=0&&y+2<=7)
//                        if(listCopy.get(i).moveAI(x-1, y+2,listCopy)){
//                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                            childrenNodesList.add(n);
//                        }
//                    if(x+1>=0&&x+1<=7&&y-2>=0&&y-2<=7)
//                        if(listCopy.get(i).moveAI(x+1, y-2,listCopy)){
//                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                            childrenNodesList.add(n);
//                        }
//                    if(x-1>=0&&x-1<=7&&y-2>=0&&y-2<=7)
//                        if(listCopy.get(i).moveAI(x-1, y-2,listCopy)){
//                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                            childrenNodesList.add(n);
//                        }
//                    if(x+2>=0&&x+2<=7&&y+1>=0&&y+1<=7)
//                        if(listCopy.get(i).moveAI(x+2, y+1,listCopy)){
//                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                            childrenNodesList.add(n);
//                        }
//                    if(x+2>=0&&x+2<=7&&y-1>=0&&y-1<=7)
//                        if(listCopy.get(i).moveAI(x+2, y-1,listCopy)){
//                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                            childrenNodesList.add(n);
//                        }
//                    if(x-2>=0&&x-2<=7&&y+1>=0&&y+1<=7)
//                        if(listCopy.get(i).moveAI(x-2, y+1,listCopy)){
//                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                            childrenNodesList.add(n);
//                        }
//                    if(x-2>=0&&x-2<=7&&y-1>=0&&y-1<=7)
//                        if(listCopy.get(i).moveAI(x-2, y-1,listCopy)){
//                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                            childrenNodesList.add(n);
//                        }
//                    
//                }
//                else if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Pawn")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
//                    //kiro   get color > black up  y-1 > true >  
//                    // black
//                    if(node.PiecesState.get(i).color.equals("Black")){
//                        if(node.PiecesState.get(i).numOfMoves==0){
//                            ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                            int y = (int)listCopy.get(i).position.getY()-2;
//                            int x = (int)listCopy.get(i).position.getX();
//                            if(listCopy.get(i).moveAI(x,y,listCopy)){
//                                Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                                childrenNodesList.add(n);
//                        }
//                            
//                            
//                        
//                        }
//                        
//                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                            int y = (int)listCopy.get(i).position.getY()-1;
//                            int x = (int)listCopy.get(i).position.getX();
//                            if(listCopy.get(i).moveAI(x,y,listCopy)){
//                                Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                                childrenNodesList.add(n);
//                            }
//                        
//                            listCopy = Node.ClonePieces(node.PiecesState);
//                             y = (int)listCopy.get(i).position.getY()-1;
//                             x = (int)listCopy.get(i).position.getX()+1;
//                            if(listCopy.get(i).moveAI(x,y,listCopy)){
//                                Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                                childrenNodesList.add(n);
//                            }
//                           listCopy = Node.ClonePieces(node.PiecesState);
//                            y = (int)listCopy.get(i).position.getY()-1;
//                            x = (int)listCopy.get(i).position.getX()-1;
//                            if(listCopy.get(i).moveAI(x,y,listCopy)){
//                                Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                                childrenNodesList.add(n);
//                            }
//                    }
//                    if(node.PiecesState.get(i).color.equals("White")){
//                       
//                        if(node.PiecesState.get(i).numOfMoves==0){
//                            ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                            int y = (int)listCopy.get(i).position.getY()+2;
//                            int x = (int)listCopy.get(i).position.getX();
//                            if(listCopy.get(i).moveAI(x,y,listCopy)){
//                                Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                                childrenNodesList.add(n);
//                        }
//                        }
//                        
//                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                        int y = (int)listCopy.get(i).position.getY()+1;
//                        int x = (int)listCopy.get(i).position.getX();
//                        if(listCopy.get(i).moveAI(x,y,listCopy)){
//                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                            childrenNodesList.add(n);
//                        }
//                        listCopy = Node.ClonePieces(node.PiecesState);
//                         y = (int)listCopy.get(i).position.getY()+1;
//                         x = (int)listCopy.get(i).position.getX()+1;
//                        if(listCopy.get(i).moveAI(x,y,listCopy)){
//                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                            childrenNodesList.add(n);
//                        }
//                       listCopy = Node.ClonePieces(node.PiecesState);
//                        y = (int)listCopy.get(i).position.getY()+1;
//                        x = (int)listCopy.get(i).position.getX()-1;
//                        if(listCopy.get(i).moveAI(x,y,listCopy)){
//                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
//                            childrenNodesList.add(n);
//                        }
//                    }
//                }
//            }
//        }
        //assign with isMax//assign with isMax
//        return childrenNodesList;
//    }
    private static int heuristic (Node LeafNode){ //AI is the white set
        int Score=0;
        Point WhiteKing= new Point(),BlackKing= new Point () ;
        for(int i=0; i < LeafNode.PiecesState.size(); i++){
            if(LeafNode.PiecesState.get(i).pieceType.equalsIgnoreCase("King") && LeafNode.PiecesState.get(i).color.equalsIgnoreCase("White")){
                WhiteKing =LeafNode.PiecesState.get(i).position;
                }
                else if(LeafNode.PiecesState.get(i).pieceType.equalsIgnoreCase("King") && LeafNode.PiecesState.get(i).color.equalsIgnoreCase("Black")){
                BlackKing =LeafNode.PiecesState.get(i).position;
                }
        }        
        for(int i=0; i < LeafNode.PiecesState.size(); i++){
            if(LeafNode.PiecesState.get(i).alive){
                int pieceValue=0;
                switch(LeafNode.PiecesState.get(i).pieceType){
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
                if(LeafNode.PiecesState.get(i).color.equalsIgnoreCase("white")){
                    Score+=pieceValue;
                    int distance = (int) Math.sqrt( Math.pow(BlackKing.getX() - LeafNode.PiecesState.get(i).position.getX(),2)+Math.pow(BlackKing.getY() - LeafNode.PiecesState.get(i).position.getY(),2))*10;
                    Score-= distance ;
                }
                else {
                    Score-=pieceValue;
                    int distance = (int) Math.sqrt( Math.pow(WhiteKing.getX() - LeafNode.PiecesState.get(i).position.getX(),2)+Math.pow(WhiteKing.getY() - LeafNode.PiecesState.get(i).position.getY(),2))*10;
                    Score+= distance ;                    
                }
            }
        }
        System.out.println("Score is "+Score);
        return Score;
    }
    
    public static ArrayList<Piece> ReturnStateToMove(int minimax){
        System.out.println("Size is "+SecondNodesToChoose.size());
        for(int i =0;i<SecondNodesToChoose.size();i++){
            System.out.println("Last Heuristic   "+SecondNodesToChoose.get(i).heuristic+" i is "+i);
            if(SecondNodesToChoose.get(i).heuristic==minimax){
                System.out.println("MiniMax matched is "+minimax);
//                System.out.println(SecondNodesToChoose.get(i).PiecesState);
                return SecondNodesToChoose.get(i).PiecesState;
            }
            
        }
        System.out.println("RETURNING NULLL IN RETURN STATE TO MOVE!");
        return null;
    }

    public static ArrayList<Piece> Play(Node node,int depth ,int a,int b,boolean isMax) throws CloneNotSupportedException{
        for(int i =0;i<node.PiecesState.size();i++){
            System.out.println("{iece type : "+node.PiecesState.get(i).pieceType+"Position "+node.PiecesState.get(i).position);
        }
        
        ArrayList <Piece>l = ReturnStateToMove(minimax(node, depth, a, b, isMax));
        for(int i =0;i<l.size();i++){
            System.out.println("{iece type : "+l.get(i).pieceType+"Position "+l.get(i).position);
        }
        return l;
    }
    
    private static int heuristic2 (Node LeafNode){ //AI is the white set
        int Score=0;
        Point WhiteKing= new Point();
        Point BlackKing= new Point ();
        int BlackCount=0;
        int WhiteCount=0;
        
        for(int i=0; i < LeafNode.PiecesState.size(); i++){
            if(LeafNode.PiecesState.get(i).pieceType.equalsIgnoreCase("King") && LeafNode.PiecesState.get(i).color.equalsIgnoreCase("White")){
                WhiteKing =LeafNode.PiecesState.get(i).position;
                }
                else if(LeafNode.PiecesState.get(i).pieceType.equalsIgnoreCase("King") && LeafNode.PiecesState.get(i).color.equalsIgnoreCase("Black")){
                BlackKing =LeafNode.PiecesState.get(i).position;
                }
        }        
        for(int i=0; i < LeafNode.PiecesState.size(); i++){
            if(LeafNode.PiecesState.get(i).alive){
                int pieceValue=0;
                switch(LeafNode.PiecesState.get(i).pieceType){
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
                if(LeafNode.PiecesState.get(i).color.equalsIgnoreCase("white")){
                    WhiteCount++;
                    Score+=pieceValue;
                    int distance = (int) Math.sqrt( Math.pow(BlackKing.getX() - LeafNode.PiecesState.get(i).position.getX(),2)+Math.pow(BlackKing.getY() - LeafNode.PiecesState.get(i).position.getY(),2))*10;
                    Score-= distance ;
                }
                else {
                    BlackCount++;
                    Score-=pieceValue;
                    int distance = (int) Math.sqrt( Math.pow(WhiteKing.getX() - LeafNode.PiecesState.get(i).position.getX(),2)+Math.pow(WhiteKing.getY() - LeafNode.PiecesState.get(i).position.getY(),2))*10;
                    Score+= distance ;                    
                }
            }
            if(BlackCount>WhiteCount){
                Score-=BlackCount;
            }
            else
                Score+=WhiteCount*2;
        }
        
        
        System.out.println("Score is "+Score);
        return Score;
    }
        private static int heuristic3 (Node LeafNode){ //AI is the white set
        int Score=0;
        Point WhiteKing= new Point();
        Point BlackKing= new Point ();
        int BlackCount=0;
        int WhiteCount=0;
        
        for(int i=0; i < LeafNode.PiecesState.size(); i++){
            if(LeafNode.PiecesState.get(i).pieceType.equalsIgnoreCase("King") && LeafNode.PiecesState.get(i).color.equalsIgnoreCase("White")){
                WhiteKing =LeafNode.PiecesState.get(i).position;
                }
                else if(LeafNode.PiecesState.get(i).pieceType.equalsIgnoreCase("King") && LeafNode.PiecesState.get(i).color.equalsIgnoreCase("Black")){
                BlackKing =LeafNode.PiecesState.get(i).position;
                }
        }        
        for(int i=0; i < LeafNode.PiecesState.size(); i++){
            if(LeafNode.PiecesState.get(i).alive){
                int pieceValue=0;
                switch(LeafNode.PiecesState.get(i).pieceType){
                    case "Pawn":
                        pieceValue=10*2;
                        break;
                    case "Knight":
                        pieceValue=30*2;
                        break;
                    case "Bishop":
                        pieceValue=30*2;
                        break;
                    case "Rook":
                        pieceValue=50*2;
                        break;
                    case "Queen":
                        pieceValue=90*2;
                        break;
                    case "King":
                        pieceValue=2000*2;
                        break;    
                }
                if(LeafNode.PiecesState.get(i).color.equalsIgnoreCase("white")){
                    WhiteCount++;
                    Score+=pieceValue;
//                    int distance = (int) Math.sqrt( Math.pow(BlackKing.getX() - LeafNode.PiecesState.get(i).position.getX(),2)+Math.pow(BlackKing.getY() - LeafNode.PiecesState.get(i).position.getY(),2))*10;
//                    Score-= distance ;
                }
                else {
                    BlackCount++;
                    Score-=pieceValue;
//                    int distance = (int) Math.sqrt( Math.pow(WhiteKing.getX() - LeafNode.PiecesState.get(i).position.getX(),2)+Math.pow(WhiteKing.getY() - LeafNode.PiecesState.get(i).position.getY(),2))*10;
//                    Score+= distance ;                    
                }
            }
            if(BlackCount>WhiteCount){
                Score-=BlackCount*10;
            }
            else
                Score+=WhiteCount;
        }
//        //to deffence
        for (int i = 0; i < LeafNode.PiecesState.size(); i++) {
            if (LeafNode.PiecesState.get(i).alive && LeafNode.PiecesState.get(i).color.equalsIgnoreCase("White")) {
                        int posX = (int) LeafNode.PiecesState.get(i).getPosition().getX();
                        int posY = (int) LeafNode.PiecesState.get(i).getPosition().getY();
                for (int j = 0; j < LeafNode.PiecesState.size(); j++) {
                    if (LeafNode.PiecesState.get(j).alive && LeafNode.PiecesState.get(j).color.equalsIgnoreCase("Black")) {
                        if (LeafNode.PiecesState.get(j).validateMoveAI(posX, posY, LeafNode.PiecesState)) {
                            switch (LeafNode.PiecesState.get(i).pieceType) {
                                case "Pawn":
                                    Score-= 10;
                                case "Knight":
                                    Score-= 30;
                                case "Bishop":
                                    Score-= 30;
                                case "Rook":
                                    Score-= 50;
                                case "Queen":
                                    Score-= 90;
                                case "King":
                                    Score-= 2000;
                                default:
                                    Score-= 100 ;
                                
                            }
                        }
                    }
                }
            }
        }
    //    to attack
//        for (int i = 0; i < LeafNode.PiecesState.size(); i++) {
//            if (LeafNode.PiecesState.get(i).alive && LeafNode.PiecesState.get(i).color.equalsIgnoreCase("Black")) {
//                        int posX = (int) LeafNode.PiecesState.get(i).getPosition().getX();
//                        int posY = (int) LeafNode.PiecesState.get(i).getPosition().getY();
//                for (int j = 0; j < LeafNode.PiecesState.size(); j++) {
//                    if (LeafNode.PiecesState.get(j).alive && LeafNode.PiecesState.get(j).color.equalsIgnoreCase("White")) {
//                        if (LeafNode.PiecesState.get(j).validateMoveAI(posX, posY, LeafNode.PiecesState)) {
//                            switch (LeafNode.PiecesState.get(i).pieceType) {
//                                case "Pawn":
//                                    Score+= 10;
//                                case "Knight":
//                                    Score+= 30;
//                                case "Bishop":
//                                    Score+= 30;
//                                case "Rook":
//                                    Score+= 50;
//                                case "Queen":
//                                    Score+= 90;
//                                case "King":
//                                    Score+=2000;
//                                default:
//                                    Score+= 100 ;
//                                
//                            }
//                        }
//                    }
//                }
//            }
//        }
        
        
        
        System.out.println("Score is "+Score);
        return Score;
    }

}


