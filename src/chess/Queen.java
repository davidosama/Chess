/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ToniGeorge
 */
public class Queen extends Piece implements Cloneable, Serializable {

    public Queen(String color, Point position) {
        super(color, position);
    }

    public boolean move(int x, int y) {
        if (validateMove(x, y)) {
            int OldX = (int) this.position.getX();
            int OldY = (int) this.position.getY();
            this.position.setLocation(x, y);
            if (this.color.equalsIgnoreCase("Black") && GameBoard.isTileThreatened("Black", (int) GameBoard.AllPiecesCloned.get(30).getPosition().getX(), (int) GameBoard.AllPiecesCloned.get(30).getPosition().getY())) {
                //undo the setLocation 
//                JOptionPane.showConfirmDialog(null, "CAN'T ! Black King will be threatened");
                this.position.setLocation(OldX, OldY);
                return false;
            }
            if (!GameBoard.isEmpty(x, y)) {
                GameBoard.attack(x, y);
            }
            this.numOfMoves++;
            return true;
        }
        return false;
    }

    @Override
    public boolean moveAI(int x, int y, ArrayList<Piece> AllPieces) {
        if (validateMoveAI(x, y, AllPieces)) {
            int OldX = (int) this.position.getX();
            int OldY = (int) this.position.getY();
            this.position.setLocation(x, y);
            if (this.color.equalsIgnoreCase("White") && GameBoard.isTileThreatenedAI("White", (int)AllPieces.get(31).getPosition().getX(), (int)AllPieces.get(31).getPosition().getY(), AllPieces)) {
                //undo the setLocation 
//                JOptionPane.showConfirmDialog(null, "WHITE KING IS THREATENED");
                this.position.setLocation(OldX, OldY);
                return false;
            }
            if (!isEmptyAI(x, y, AllPieces)) {
                attackAI(x, y, AllPieces);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean validateMove(int x, int y) {
        if(x>7 || x<0 || y>7 || y<0){
            return false;
        }
        if ((Math.abs(this.position.getX() - x) == Math.abs(this.position.getY() - y)) || ((this.position.getX() - x) == 0 || (this.position.getY() - y) == 0)) {
            if (isPathClear(x, y) && (GameBoard.isEnemy(x, y, this.color) || GameBoard.isEmpty(x, y))) {
                return true;
            }
        }
        return false;
    }

    public boolean canAttack(int x, int y) {
        if ((Math.abs(this.position.getX() - x) == Math.abs(this.position.getY() - y)) || ((this.position.getX() - x) == 0 || (this.position.getY() - y) == 0)) {
            if (isPathClear(x, y)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPathClear(int x, int y) {
        Point currentPosition = new Point((int) this.position.getX(), (int) this.position.getY());
        //int counter = (int) Math.sqrt( Math.pow(x - this.position.getX(),2)+Math.pow(y - this.position.getY(),2)); // change old counters by this one
        int counterDiagonal = (int) Math.abs(this.position.getX() - x);
        int counterStraight = (int) Math.abs(this.position.getX() - x) + (int) Math.abs(this.position.getY() - y);
        System.out.println("Counter of Queen diagonal = " + counterDiagonal);
        System.out.println("Counter of Queen st = " + counterStraight);
        if (counterDiagonal == 1) {
            System.out.println("Trueeeeeeee");
            return true;
        } else if (counterStraight == 1) {
            return true;
        }
        //for(int i = 0; i < counter-1 ; i++){
        if (x < this.position.getX() && y < this.position.getY()) {//top-left
            for (int i = 0; i < counterDiagonal - 1; i++) {
                currentPosition.x--;
                currentPosition.y--;
                if (!GameBoard.isEmpty((int) currentPosition.getX(), (int) currentPosition.getY())) {// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                    return false;
                }

            }
        } //}
        else if (x < this.position.getX() && y > this.position.getY()) {//down-left
            for (int i = 0; i < counterDiagonal - 1; i++) {
                currentPosition.x--;
                currentPosition.y++;
                if (!GameBoard.isEmpty((int) currentPosition.getX(), (int) currentPosition.getY())) {// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                    return false;
                }
            }
        } else if (x > this.position.getX() && y < this.position.getY()) {//top-right
            for (int i = 0; i < counterDiagonal - 1; i++) {
                currentPosition.x++;
                currentPosition.y--;
                if (!GameBoard.isEmpty((int) currentPosition.getX(), (int) currentPosition.getY())) {// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                    return false;
                }
            }
        } else if (x > this.position.getX() && y > this.position.getY()) {//down-right
            for (int i = 0; i < counterDiagonal - 1; i++) {
                currentPosition.x++;
                currentPosition.y++;
                if (!GameBoard.isEmpty((int) currentPosition.getX(), (int) currentPosition.getY())) {// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                    return false;
                }
            }
        } else if (x == this.position.getX() && y > this.position.getY()) {//down
            for (int i = 0; i < counterStraight - 1; i++) {
                currentPosition.y++;
                if (!GameBoard.isEmpty((int) currentPosition.getX(), (int) currentPosition.getY())) {// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                    return false;
                }
            }
        } else if (x > this.position.getX() && y == this.position.getY()) {//right
            for (int i = 0; i < counterStraight - 1; i++) {
                currentPosition.x++;
                if (!GameBoard.isEmpty((int) currentPosition.getX(), (int) currentPosition.getY())) {// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                    return false;
                }
            }
        } else if (x == this.position.getX() && y < this.position.getY()) {//up
            for (int i = 0; i < counterStraight - 1; i++) {
                currentPosition.y--;
                if (!GameBoard.isEmpty((int) currentPosition.getX(), (int) currentPosition.getY())) {// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                    return false;
                }
            }
        } else if (x < this.position.getX() && y == this.position.getY()) {//left
            for (int i = 0; i < counterStraight - 1; i++) {
                currentPosition.x--;
                if (!GameBoard.isEmpty((int) currentPosition.getX(), (int) currentPosition.getY())) {// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                    return false;
                }
            }

        }

        return true;
    }

    @Override
    public boolean validateMoveAI(int x, int y, ArrayList<Piece> AllPieces) {
        if (x > 7 || x < 0 || y > 7 || y < 0) {
            return false;
        }
        if ((Math.abs(this.position.getX() - x) == Math.abs(this.position.getY() - y)) || ((this.position.getX() - x) == 0 || (this.position.getY() - y) == 0)) {
            if (isPathClearAI(x, y, AllPieces) && (isEnemyAI(x, y, this.color, AllPieces) || isEmptyAI(x, y, AllPieces))) {
                return true;
            }
        }
        return false;
    }

    private boolean isPathClearAI(int x, int y, ArrayList<Piece> AllPieces) {
        Point currentPosition = new Point((int) this.position.getX(), (int) this.position.getY());
        //int counter = (int) Math.sqrt( Math.pow(x - this.position.getX(),2)+Math.pow(y - this.position.getY(),2)); // change old counters by this one
        int counterDiagonal = (int) Math.abs(this.position.getX() - x);
        int counterStraight = (int) Math.abs(this.position.getX() - x) + (int) Math.abs(this.position.getY() - y);
        System.out.println("Counter of Queen diagonal = " + counterDiagonal);
        System.out.println("Counter of Queen st = " + counterStraight);
        if (counterDiagonal == 1) {
            System.out.println("Trueeeeeeee");
            return true;
        } else if (counterStraight == 1) {
            return true;
        }
        //for(int i = 0; i < counter-1 ; i++){
        if (x < this.position.getX() && y < this.position.getY()) {//top-left
            for (int i = 0; i < counterDiagonal - 1; i++) {
                currentPosition.x--;
                currentPosition.y--;
                if (!isEmptyAI((int) currentPosition.getX(), (int) currentPosition.getY(), AllPieces)) {// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                    return false;
                }
            }
        } //}
        else if (x < this.position.getX() && y > this.position.getY()) {//down-left
            for (int i = 0; i < counterDiagonal - 1; i++) {
                currentPosition.x--;
                currentPosition.y++;
                if (!isEmptyAI((int) currentPosition.getX(), (int) currentPosition.getY(), AllPieces)) {// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                    return false;
                }
            }
        } else if (x > this.position.getX() && y < this.position.getY()) {//top-right
            for (int i = 0; i < counterDiagonal - 1; i++) {
                currentPosition.x++;
                currentPosition.y--;
                if (!isEmptyAI((int) currentPosition.getX(), (int) currentPosition.getY(), AllPieces)) {// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                    return false;
                }
            }
        } else if (x > this.position.getX() && y > this.position.getY()) {//down-right
            for (int i = 0; i < counterDiagonal - 1; i++) {
                currentPosition.x++;
                currentPosition.y++;
                if (!isEmptyAI((int) currentPosition.getX(), (int) currentPosition.getY(), AllPieces)) {// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                    return false;
                }
            }
        } else if (x == this.position.getX() && y > this.position.getY()) {//down
            for (int i = 0; i < counterStraight - 1; i++) {
                currentPosition.y++;
                if (!isEmptyAI((int) currentPosition.getX(), (int) currentPosition.getY(), AllPieces)) {// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                    return false;
                }
            }
        } else if (x > this.position.getX() && y == this.position.getY()) {//right
            for (int i = 0; i < counterStraight - 1; i++) {
                currentPosition.x++;
                if (!isEmptyAI((int) currentPosition.getX(), (int) currentPosition.getY(), AllPieces)) {// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                    return false;
                }
            }
        } else if (x == this.position.getX() && y < this.position.getY()) {//up
            for (int i = 0; i < counterStraight - 1; i++) {
                currentPosition.y--;
                if (!isEmptyAI((int) currentPosition.getX(), (int) currentPosition.getY(), AllPieces)) {// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                    return false;
                }
            }
        } else if (x < this.position.getX() && y == this.position.getY()) {//left
            for (int i = 0; i < counterStraight - 1; i++) {
                currentPosition.x--;
                if (!isEmptyAI((int) currentPosition.getX(), (int) currentPosition.getY(), AllPieces)) {// current position a missing function here implemented in gameboard that checks if there is a piece in (x,y) if true returns that object
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected Queen clone() throws CloneNotSupportedException {
        Queen k = new Queen(this.color, new Point((int) this.position.getX(), (int) this.position.getY()));
        k.alive = this.alive;
        k.numOfMoves = this.numOfMoves;
        k.pieceType = this.pieceType;
        k.label = null;
        return k;
    }

}
