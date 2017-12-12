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
    public PointMapper() {
        iy = 50;
        ix = 50;
        points[0][0]= new Point (50,50);
        for (int i = 0; i < 8; i++) {
            
            for (int j = 0; j < 8; j++) {
                
                points[i][j] = new Point(ix-30,iy-30);
                ix+= 85;
            }
            iy += 85;
            ix = 50;
        }   
    }
    
    
    public Point GetPoint(int x , int y)
    {
        return points[x][y];
    }
}
