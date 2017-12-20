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
    static int RootDepth=3;
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
            node.heuristic=heuristic(node);
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
                System.out.println("beta is "+node.beta+"when depth is "+ depth );
                
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
        if(isMax)
            color="White";
        else
            color="Black";
        //parent node which contains a game state ( pieces with their positions ) 
        System.out.println("Piece State"+node.PiecesState);
        for(int i = 0 ; i<node.PiecesState.size();i++){
            node.isMax=isMax;
            if(node.PiecesState.get(i).alive){
                if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Rook")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
                    for(int x =0 ; x<8;x++){//kol el amaken eli 3ala el y 
                        if(x==node.PiecesState.get(i).position.getX())
                            continue;
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        int y = (int)listCopy.get(i).position.getY();
                        if(listCopy.get(i).moveAI(x,y,listCopy)){
                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                    }
                    for(int y =0 ; y<8;y++){//kol el amaken eli 3ala el x
                        if(y==node.PiecesState.get(i).position.getY())
                            continue;
                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        int x = (int)listCopy.get(i).position.getX();
                        if(listCopy.get(i).moveAI(x,y,listCopy)){
                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                    }
                }
                else if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Bishop")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
//                    ////// This is for shemaal l foo2
//                    int y = (int) node.PiecesState.get(i).position.getY() - 1;
//                    for (int x = (int) node.PiecesState.get(i).position.getX() - 1; x >= 0; x--) {
////                        for(int y=(int) node.PiecesState.get(i).position.getY()-1;y>0; y--)
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
//
//                    }
//
//                    //////This if or yemeeen l ta7ttt 
//                    int y2 = (int) node.PiecesState.get(i).position.getY() + 1;
//                    for (int x = (int) node.PiecesState.get(i).position.getX() + 1; x < 8; x++) {
////                        for(int y=(int) node.PiecesState.get(i).position.getY()-1;y>0; y--)
//                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                        //int y = (int)listCopy.get(i).position.getY();
//                        if (listCopy.get(i).moveAI(x,y2,listCopy)) {
//                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
//                            childrenNodesList.add(n);
//                        } else {
//                            break;
//                        }
//                        y2++;
//                        if (y2 > 8) {
//                            break;
//                        }
//                    }
//                    //Thiss s ofr fooo2 yemeeeen 
//                    int y3 = (int) node.PiecesState.get(i).position.getY() - 1;
//                    for (int x = (int) node.PiecesState.get(i).position.getX() + 1; x < 8; x++) {
//                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                        if (listCopy.get(i).moveAI(x,y3,listCopy)) {
//                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
//                            childrenNodesList.add(n);
//                        } else {
//                            break;
//                        }
//                        y3++;
//                        if (y3 > 8) {
//                            break;
//                        }
//                    }
//
//                    /////// This is for ta7tt shemaaaalll
//                    int y4 = (int) node.PiecesState.get(i).position.getY() + 1;
//                    for (int x = (int) node.PiecesState.get(i).position.getX() - 1; x >= 0; x--) {
//                        ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
//                        if (listCopy.get(i).moveAI(x,y4,listCopy)) {
//                            Node n = new Node(listCopy, node.alpha, node.beta,isMax);
//                            childrenNodesList.add(n);
//                        } else {
//                            break;
//                        }
//                        y4--;
//                        if (y4 < 0) {
//                            break;
//                        }
//                    }
//
                }
                else if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Pawn")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
                    //kiro   get color > black up  y-1 > true >  
                    // black
                    if(node.PiecesState.get(i).color.equals("Black")){
                    ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                        int y = (int)listCopy.get(i).position.getY()-1;
                        int x = (int)listCopy.get(i).position.getX();
                        if(listCopy.get(i).moveAI(x,y,listCopy)){
                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                        listCopy = Node.ClonePieces(node.PiecesState);
                         y = (int)listCopy.get(i).position.getY()-1;
                         x = (int)listCopy.get(i).position.getX()+1;
                        if(listCopy.get(i).moveAI(x,y,listCopy)){
                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                       listCopy = Node.ClonePieces(node.PiecesState);
                        y = (int)listCopy.get(i).position.getY()-1;
                        x = (int)listCopy.get(i).position.getX()-1;
                        if(listCopy.get(i).moveAI(x,y,listCopy)){
                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                }
                    if(node.PiecesState.get(i).color.equals("White")){
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
                else if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("King")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
                    
                    ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                    int x = (int)listCopy.get(i).position.getX();
                    int y = (int)listCopy.get(i).position.getY();
                    
                    //et7arak 7arrak yemiin
                    if(listCopy.get(i).moveAI(x+1,y,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arraka shemal
                    if(listCopy.get(i).moveAI(x-1,y,listCopy)){
                       Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arrakka fo2
                    if(listCopy.get(i).moveAI(x,y+1,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7araka ta7t
                    if(listCopy.get(i).moveAI(x,y-1,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arraka diagonal fo2 yemin 
                    if(listCopy.get(i).moveAI(x+1,y+1,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arrakka diagonal fo2 shemal
                    if(listCopy.get(i).moveAI(x-1,y+1,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arrakka diagonal ta7t shemal
                    if(listCopy.get(i).moveAI(x-1,y-1,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                    //et7arak 7arrakka diagonal ta7t yemin
                    if(listCopy.get(i).moveAI(x+1,y-1,listCopy)){
                        Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                        childrenNodesList.add(n);
                    }
                }
                if(node.PiecesState.get(i).pieceType.equalsIgnoreCase("Knight")&&node.PiecesState.get(i).color.equalsIgnoreCase(color)){
                    
                    ArrayList<Piece> listCopy = Node.ClonePieces(node.PiecesState);
                    int x = (int)listCopy.get(i).position.getX();
                    int y = (int)listCopy.get(i).position.getY();
                    if(x+1>=0&&x+1<=7&&y+2>=0&&y+2<=7)
                        if(listCopy.get(i).moveAI(x+1, y+2,listCopy)){
                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                    if(x-1>=0&&x-1<=7&&y+2>=0&&y+2<=7)
                        if(listCopy.get(i).moveAI(x-1, y+2,listCopy)){
                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                    if(x+1>=0&&x+1<=7&&y-2>=0&&y-2<=7)
                        if(listCopy.get(i).moveAI(x+1, y-2,listCopy)){
                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                    if(x-1>=0&&x-1<=7&&y-2>=0&&y-2<=7)
                        if(listCopy.get(i).moveAI(x-1, y-2,listCopy)){
                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                    if(x+2>=0&&x+2<=7&&y+1>=0&&y+1<=7)
                        if(listCopy.get(i).moveAI(x+2, y+1,listCopy)){
                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                    if(x+2>=0&&x+2<=7&&y-1>=0&&y-1<=7)
                        if(listCopy.get(i).moveAI(x+2, y-1,listCopy)){
                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                    if(x-2>=0&&x-2<=7&&y+1>=0&&y+1<=7)
                        if(listCopy.get(i).moveAI(x-2, y+1,listCopy)){
                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                    if(x-2>=0&&x-2<=7&&y-1>=0&&y-1<=7)
                        if(listCopy.get(i).moveAI(x-2, y-1,listCopy)){
                            Node n= new Node(listCopy,node.alpha,node.beta,isMax);
                            childrenNodesList.add(n);
                        }
                    
                }
            }
        }
        //assign with isMax//assign with isMax
        return childrenNodesList;
    }
    private static int heuristic (Node LeafNode){ //AI is the white set
        int Score=0;
        Point WhiteKing= new Point(),BlackKing= new Point () ;
        for(int i=0; i < LeafNode.PiecesState.size(); i++){
            if(LeafNode.PiecesState.get(i).pieceType.equalsIgnoreCase("King") && LeafNode.PiecesState.get(i).color.equalsIgnoreCase("White")){
            WhiteKing =LeafNode.PiecesState.get(i).position;
            }
            else if(LeafNode.PiecesState.get(i).pieceType.equalsIgnoreCase("King") && LeafNode.PiecesState.get(i).color.equalsIgnoreCase("Black")){
            WhiteKing =LeafNode.PiecesState.get(i).position;
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
                System.out.println(SecondNodesToChoose.get(i).PiecesState);
                return SecondNodesToChoose.get(i).PiecesState;
            }
            
        }
        System.out.println("RETURNING NULLLL IN RETURNSTATETOMOVE");
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

}


