/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author Kero
 */
public class PointMapper implements Serializable{
    
    int ix;
    int iy;
    static Point points[][] = new Point[8][8];
    public static Tile[][] BoardTilesArray=new Tile[8][8];
    
    public PointMapper() {
        initBoardTiles();
        LabelsPositions();
    }

    public Tile[][] getBoardTilesArray() {
        return BoardTilesArray;
    }
    
    
    //3ashan a3raf el label hayetresem fen
    void LabelsPositions(){
        iy = 50;
        ix = 50;
        points[0][0]= new Point (50,50);
        for (int i = 0; i < 8; i++) {
            
            for (int j = 0; j < 8; j++) {
                
                points[j][i] = new Point(ix-30,iy-30);
                ix+= 85;
            }
            iy += 85;
            ix = 50;
        }   
    }
    
    //to initialize 64 empty tiles
    void initBoardTiles(){
        int x=0;
        int y=0;
        for(int i =0;i<8;i++){
            for(int j=0;j<8;j++){
                Tile TileTemp = new Tile(new Point(x,y),new Point(x+85,y),new Point(x,y+85),new Point(x+85,y+85));
                TileTemp.setEmpty(true);
                TileTemp.setXANDY(i, j);
                BoardTilesArray[i][j]=TileTemp;
                x+=85;
            }
            x=0;
            y+=85;
        }
    }

    
    public Point GetPoint(int x , int y)
    {
        return points[x][y];
    }
    
    //return the Location where we should draw the jLabel
    public static Point getTileCoordinate(int xPoint, int yPoint){
        Point p = getTileNumber(xPoint,yPoint);
        return points[p.x][p.y];
    }
    
//    public static ArrayList<Piece> ClonePieces (ArrayList<Piece> p  ) throws CloneNotSupportedException
//            {
//                ArrayList<Piece> pi =new ArrayList<>();
//                for (int i = 0; i < p.size(); i++) {
//                    pi.add(p.get(i).clone());
//                }
//            }
    
    //return Tile number
    static Point getTileNumber(int xPoint , int yPoint){
//        if(panel == 0)
//        {
            int x = getTileRangeX(xPoint+6);
        System.out.println("x is    "+x);
        int y = getTileRangeY(yPoint+27);
        System.out.println("y is     "+ y);
        return new Point(x,y);
//        }
//        else
//        {
//            
//        }
    }
    
    public static int getTileRangeX(int XOrY){
        if(XOrY>=0 && XOrY<91){
            return 0;
        }
        else if (XOrY>=91 && XOrY <177){
            return 1;
        }
        else if (XOrY>=177 && XOrY<263){
            return 2;
        }
        else if(XOrY>=263 && XOrY<349){
            return 3;
        }
        else if(XOrY>=349 && XOrY<435){
            return 4;
        }
        else if(XOrY>=435 && XOrY<521){
            return 5;
        }
        else if(XOrY>=521 && XOrY<607){
            return 6;
        }
        else if(XOrY>=607 && XOrY<700){
            return 7;
        }
        System.out.println("Error in X Range");
        return -1;
    }
    public static int getTileRangeY(int XOrY){
        if(XOrY>=0 && XOrY<120){
            return 0;
        }
        else if (XOrY>=120 && XOrY <205){
            return 1;
        }
        else if (XOrY>=205 && XOrY<290){
            return 2;
        }
        else if(XOrY>=290 && XOrY<377){
            return 3;
        }
        else if(XOrY>=377 && XOrY<462){
            return 4;
        }
        else if(XOrY>=462 && XOrY<548){
            return 5;
        }
        else if(XOrY>=548 && XOrY<634){
            return 6;
        }
        else if(XOrY>=634 && XOrY<730){
            return 7;
        }
        System.out.println("Error in X Range");
        return -1;
    }
    
}
