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
    boolean first = true;
    Point points[][] = new Point[8][8];
    private Tile[][] BoardTilesArray=new Tile[8][8];
    
    //3ashan a3raf el label hayetresem fen
    public PointMapper() {
        initBoardTiles();
        LabelsPositions();
       
        
    }

    public Tile[][] getBoardTilesArray() {
        return BoardTilesArray;
    }
    
    
    
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
    
    void initBoardTiles(){
        
        
        int x=5;
        int y=35;
        //BoardTilesArray[0][0] = new Tile(new Point(0,0),new Point(90,0),new Point(0,120),new Point(90,120));
        for(int i =0;i<8;i++){
            for(int j=0;j<8;j++){
           //     if(i==0 && j == 0) continue;
                Tile TileTemp = new Tile(new Point(x,y),new Point(x+85,y),new Point(x,y+85),new Point(x+85,y+85));
                TileTemp.setEmpty(true);
                BoardTilesArray[i][j]=TileTemp;
                x+=85;
            }
            x=5;
            y+=85;
        }
        //BoardTilesArray[0][0] = new Tile(new Point(0,0),new Point(90,0),new Point(0,120),new Point(90,120));
        System.out.println("finish");
    }

    
    public Point GetPoint(int x , int y)
    {
        return points[x][y];
    }
    
    //returns the coordinate of the Tile. Example [0,0] or [5,7] etc..
     public Point getTileCoordinate(int xPoint, int yPoint){
        int x = getTileRangeX(xPoint);
        System.out.println(x);
        int y = getTileRangeY(yPoint);
        System.out.println(y);
        return points[x][y];
        
    }
    
    
    private int getTileRangeX(int XOrY){
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
    private int getTileRangeY(int XOrY){
        if(XOrY>=35 && XOrY<120){
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
