/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Point;

/**
 *
 * @author Kero
 */
public class PointMapper {
    
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
    public Point getTileCoordinate(int xPoint, int yPoint){
        Point p = getTileNumber(xPoint,yPoint);
        return points[p.x][p.y];
    }
    
    //return Tile number
    static Point getTileNumber(int xPoint , int yPoint){
//        if(panel == 0)
//        {
            int x = getTileRangeX(xPoint);
        System.out.println("x is    "+x);
        int y = getTileRangeY(yPoint+25);
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
        else if (XOrY>=91 && XOrY <178){
            return 1;
        }
        else if (XOrY>=178 && XOrY<265){
            return 2;
        }
        else if(XOrY>=265 && XOrY<352){
            return 3;
        }
        else if(XOrY>=352 && XOrY<439){
            return 4;
        }
        else if(XOrY>=439 && XOrY<526){
            return 5;
        }
        else if(XOrY>=526 && XOrY<613){
            return 6;
        }
        else if(XOrY>=613 && XOrY<700){
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
        else if (XOrY>=205 && XOrY<291){
            return 2;
        }
        else if(XOrY>=291 && XOrY<378){
            return 3;
        }
        else if(XOrY>=378 && XOrY<462){
            return 4;
        }
        else if(XOrY>=462 && XOrY<548){
            return 5;
        }
        else if(XOrY>=548 && XOrY<635){
            return 6;
        }
        else if(XOrY>=635 && XOrY<718){
            return 7;
        }
        System.out.println("Error in X Range");
        return -1;
    }
    
}
