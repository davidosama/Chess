/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Kero
 */
public class GameBoard extends javax.swing.JFrame implements MouseListener ,Serializable{

    public static int WhiteTurns = 0;
    public static int BlackTurns = 0;
    public ArrayList<Pawn> blackPawns;
    public ArrayList<Pawn> whitePawns;

    public ArrayList<Knight> blackKnights;
    public ArrayList<Knight> whiteKnights;

    public ArrayList<Rook> whiteRooks;
    public ArrayList<Rook> blackRooks;

    public ArrayList<Bishop> blackBishops;
    public ArrayList<Bishop> whiteBishops;

    public Queen blackQueen;
    public Queen whiteQueen;
    
    public King blackKing;
    public King whiteKing;
    
    public static int depth = 0;
    

    public static ArrayList<Piece> AllPieces;
    public static ArrayList<Piece> AllPiecesCloned;
    
    boolean PlayerSelected = false;
    static PointMapper pm;
    int x = 0;
    int y = 0;
    Tile[][] GameBoardTile;
    private boolean First = true;
    Point FirstSelectedPoint= null;
    boolean WhiteTurn = false;//0 for black 1 for white
    private final JLabel SelectedLbl;
    static boolean checkMate=false ;
    private final JButton SaveBtn;

    public GameBoard(int dep,boolean load, ArrayList<Object> data) throws CloneNotSupportedException {
        
        this.depth = dep;
        initComponents();
        pm = new PointMapper();
        
                

        
        SaveBtn = new javax.swing.JButton();

        SaveBtn.setText("Save Game");
        SaveBtn.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        try {
            SaveBtnActionPerformed(evt);
        } catch (IOException ex) {
            Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

            
        });

        jPanel1.add(SaveBtn);
        SaveBtn.setBounds(610, 240, 100, 26);

        SelectedLbl = new javax.swing.JLabel();

        SelectedLbl.setBackground(new java.awt.Color(255, 51, 51));

        SelectedLbl.setForeground(new java.awt.Color(255, 51, 51));

        SelectedLbl.setText("");

        SelectedLbl.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(255, 51, 51)));

        jPanel1.add(SelectedLbl);
        SelectedLbl.setBounds(160, 110, 60, 60);
        SelectedLbl.setVisible(false);

        jPanel1.setLayout(null);
        
        this.setLocationRelativeTo(null);
        
        this.addMouseListener(this);
        if(load == false)
        {
        initializePiecesPositions();
        }
        else{
            //1- depth
            // used in Gameframe when makin new gameBoard
            
            //2- whiteTurn
            this.WhiteTurn = (boolean) data.get(1);
            //3- array lists with order
            /*
            public ArrayList<Pawn> blackPawns;
            public ArrayList<Pawn> whitePawns;
            public ArrayList<Knight> blackKnights;
            public ArrayList<Knight> whiteKnights;
            public ArrayList<Rook> whiteRooks;
            public ArrayList<Rook> blackRooks;
            public ArrayList<Bishop> blackBishops;
            public ArrayList<Bishop> whiteBishops;
            public Queen blackQueen;
            public Queen whiteQueen;
            public King blackKing;
            public King whiteKing;
        */
            blackPawns = (ArrayList<Pawn>) data.get(2);
            whitePawns = (ArrayList<Pawn>) data.get(3);
              blackKnights = (ArrayList<Knight>) data.get(4);
              whiteKnights = (ArrayList<Knight>) data.get(5);
              whiteRooks = (ArrayList<Rook>) data.get(6);
              blackRooks = (ArrayList<Rook>) data.get(7);
              blackBishops = (ArrayList<Bishop>) data.get(8);
              whiteBishops = (ArrayList<Bishop>) data.get(9);
              blackQueen = (Queen) data.get(10);
              whiteQueen = (Queen) data.get(11);
              blackKing = (King) data.get(12);
              whiteKing= (King) data.get(13);
              
              //4- point mapper tile array
              pm.BoardTilesArray = (Tile[][]) data.get(14);
              
              // paint
              for (int i = 0; i < 8; i++) {

            
            
            jPanel1.add(blackPawns.get(i).label);
//            blackPawn.label.setVisible(true);
        }
        //white Pawns

        for (int i = 0; i < 8; i++) {
            
            
            jPanel1.add(whitePawns.get(i).label);
        }
        //black blackKnights
       
        jPanel1.add(blackKnights.get(0).label);

        //PointMapper.BoardTilesArray[1][7].piece.position = new Point(1,2);
        
        jPanel1.add(blackKnights.get(1).label);

        //white blackKnights
        
        jPanel1.add(whiteKnights.get(0).label);

        
        jPanel1.add(whiteKnights.get(1).label);

        //black Rooks
        
        jPanel1.add(blackRooks.get(0).label);


        jPanel1.add(blackRooks.get(1).label);

        
        jPanel1.add(whiteRooks.get(0).label);

        
        jPanel1.add(whiteRooks.get(1).label);

        //black bishops
        
        jPanel1.add(blackBishops.get(0).label);

        
        jPanel1.add(blackBishops.get(1).label);

        //white bishops
        
        jPanel1.add(whiteBishops.get(0).label);

        
        jPanel1.add(whiteBishops.get(1).label);

        //black Queen
        
        jPanel1.add(blackQueen.label);

        //white Queen
        
        jPanel1.add(whiteQueen.label);
        
        
        jPanel1.add(whiteKing.label);
        
        
        jPanel1.add(blackKing.label);
              
//        for (int i = 0; i < 8; i++) {
//
//
//            PointMapper.BoardTilesArray[(int)blackPawns.get(i).position.getX()][(int)blackPawns.get(i).position.getY()].piece = blackPawns.get(i);
//            PointMapper.BoardTilesArray[(int)blackPawns.get(i).position.getX()][(int)blackPawns.get(i).position.getY()].setEmpty(false);
//            jPanel1.add(blackPawns.get(i).label);
////            blackPawn.label.setVisible(true);
//        }
//        //white Pawns
//        for (int i = 0; i < 8; i++) {
//   
//            PointMapper.BoardTilesArray[(int)whitePawns.get(i).position.getX()][(int)whitePawns.get(i).position.getY()].piece = whitePawns.get(i);
//            PointMapper.BoardTilesArray[(int)whitePawns.get(i).position.getX()][(int)whitePawns.get(i).position.getY()].setEmpty(false);
//            jPanel1.add(whitePawns.get(i).label);
//        }
//        //black blackKnights
//        PointMapper.BoardTilesArray[(int)blackKnights.get(0).position.getX()][(int)blackKnights.get(0).position.getY()].piece = blackKnights.get(0);
//        PointMapper.BoardTilesArray[(int)blackKnights.get(0).position.getX()][(int)blackKnights.get(0).position.getY()].setEmpty(false);
//        jPanel1.add(blackKnights.get(0).label);
//
//        //PointMapper.BoardTilesArray[1][7].piece.position = new Point(1,2);
//
//        PointMapper.BoardTilesArray[(int)blackKnights.get(1).position.getX()][(int)blackKnights.get(1).position.getY()].piece = blackKnights.get(1);
//        PointMapper.BoardTilesArray[(int)blackKnights.get(1).position.getX()][(int)blackKnights.get(1).position.getY()].setEmpty(false);
//        jPanel1.add(blackKnights.get(1).label);
//
//        //white blackKnights
//
//        PointMapper.BoardTilesArray[(int)whiteKnights.get(0).position.getX()][(int)whiteKnights.get(0).position.getY()].piece = whiteKnights.get(0);
//        PointMapper.BoardTilesArray[(int)whiteKnights.get(0).position.getX()][(int)whiteKnights.get(0).position.getY()].setEmpty(false);
//        jPanel1.add(whiteKnights.get(0).label);
//
//
//        PointMapper.BoardTilesArray[(int)whiteKnights.get(1).position.getX()][(int)whiteKnights.get(1).position.getY()].piece = whiteKnights.get(1);
//        PointMapper.BoardTilesArray[(int)whiteKnights.get(1).position.getX()][(int)whiteKnights.get(1).position.getY()].setEmpty(false);
//        jPanel1.add(whiteKnights.get(1).label);
//
//        //black Rooks
//  
//        PointMapper.BoardTilesArray[(int)blackRooks.get(0).position.getX()][(int)blackRooks.get(0).position.getY()].piece = blackRooks.get(0);
//        PointMapper.BoardTilesArray[(int)blackRooks.get(0).position.getX()][(int)blackRooks.get(0).position.getY()].setEmpty(false);
//        jPanel1.add(blackRooks.get(0).label);
//
//        PointMapper.BoardTilesArray[(int)blackRooks.get(1).position.getX()][(int)blackRooks.get(1).position.getY()].piece = blackRooks.get(1);
//        PointMapper.BoardTilesArray[(int)blackRooks.get(1).position.getX()][(int)blackRooks.get(1).position.getY()].setEmpty(false);
//        jPanel1.add(blackRooks.get(1).label);
//
//        //white Rooks
//
//        PointMapper.BoardTilesArray[(int)whiteRooks.get(0).position.getX()][(int)whiteRooks.get(0).position.getY()].piece = whiteRooks.get(0);
//        PointMapper.BoardTilesArray[(int)whiteRooks.get(0).position.getX()][(int)whiteRooks.get(0).position.getY()].setEmpty(false);
//        jPanel1.add(whiteRooks.get(0).label);
//
//
//        PointMapper.BoardTilesArray[(int)whiteRooks.get(1).position.getX()][(int)whiteRooks.get(1).position.getY()].piece = whiteRooks.get(1);
//        PointMapper.BoardTilesArray[(int)whiteRooks.get(1).position.getX()][(int)whiteRooks.get(1).position.getY()].setEmpty(false);
//        jPanel1.add(whiteRooks.get(1).label);
//
//        //black bishops
//
//        PointMapper.BoardTilesArray[(int)blackBishops.get(0).position.getX()][(int)blackBishops.get(0).position.getY()].piece = blackBishops.get(0);
//        PointMapper.BoardTilesArray[(int)blackBishops.get(0).position.getX()][(int)blackBishops.get(0).position.getY()].setEmpty(false);
//        jPanel1.add(blackBishops.get(0).label);
//
//
//        PointMapper.BoardTilesArray[(int)blackBishops.get(1).position.getX()][(int)blackBishops.get(1).position.getY()].piece = blackBishops.get(1);
//        PointMapper.BoardTilesArray[(int)blackBishops.get(1).position.getX()][(int)blackBishops.get(1).position.getY()].setEmpty(false);
//        jPanel1.add(blackBishops.get(1).label);
//
//        //white bishops
//
//        PointMapper.BoardTilesArray[(int)whiteBishops.get(0).position.getX()][(int)whiteBishops.get(0).position.getY()].piece = whiteBishops.get(0);
//        PointMapper.BoardTilesArray[(int)whiteBishops.get(0).position.getX()][(int)whiteBishops.get(0).position.getY()].setEmpty(false);
//        jPanel1.add(whiteBishops.get(0).label);
//
//        PointMapper.BoardTilesArray[(int)whiteBishops.get(1).position.getX()][(int)whiteBishops.get(1).position.getY()].piece = whiteBishops.get(1);
//        PointMapper.BoardTilesArray[(int)whiteBishops.get(1).position.getX()][(int)whiteBishops.get(1).position.getY()].setEmpty(false);
//        jPanel1.add(whiteBishops.get(1).label);
//
//        //black Queen
//
//        PointMapper.BoardTilesArray[(int)blackQueen.position.getX()][(int)blackQueen.position.getY()].piece = blackQueen;
//        PointMapper.BoardTilesArray[(int)blackQueen.position.getX()][(int)blackQueen.position.getY()].setEmpty(false);
//        jPanel1.add(blackQueen.label);
//
//        //white Queen
//
//        PointMapper.BoardTilesArray[(int)whiteQueen.position.getX()][(int)whiteQueen.position.getY()].piece = whiteQueen;
//        PointMapper.BoardTilesArray[(int)whiteQueen.position.getX()][(int)whiteQueen.position.getY()].setEmpty(false);
//        jPanel1.add(whiteQueen.label);
//        
//        PointMapper.BoardTilesArray[(int)whiteKing.position.getX()][(int)whiteKing.position.getY()].piece=whiteKing;
//        PointMapper.BoardTilesArray[(int)whiteKing.position.getX()][(int)whiteKing.position.getY()].setEmpty(false);
//        jPanel1.add(whiteKing.label);
//        
//
//        PointMapper.BoardTilesArray[(int)blackKing.position.getX()][(int)blackKing.position.getY()].piece=blackKing;
//        PointMapper.BoardTilesArray[(int)blackKing.position.getX()][(int)blackKing.position.getY()].setEmpty(false);
//        jPanel1.add(blackKing.label);
//              
        }
        JLabel jLabel1 = new javax.swing.JLabel();
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chess/imgs/background.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 710, 700);

//        GameBoardTile[0][0].setPiece(piece);

        /*
                public ArrayList<Pawn> blackPawns;
    public ArrayList<Pawn> whitePawns;
    
    public ArrayList<Knight>blackKnights;
    public ArrayList<Knight>whiteKnights;
    
    public ArrayList<Rook>whiteRooks;
    public ArrayList<Rook>blackRooks;
    
    public ArrayList<Bishop>blackBishops;
    public ArrayList<Bishop>whiteBishops;
    
    public Queen blackQueen;
    public Queen whiteQueen;
         */
        
        
        
        
//        //AllPiecesCloned = (ArrayList<Piece>) AllPieces.clone();
//            for (int i = 0; i < AllPieces.size(); i++) {
//                AllPiecesCloned.add(AllPieces.get(i).clone());
//    
//            
//            }
        
        
        
        
        setPosions();

    }

    private void SaveBtnActionPerformed(ActionEvent evt) throws FileNotFoundException, IOException {
        JFileChooser fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Willson", "willson"));
        if(fc.showSaveDialog(this) == fc.APPROVE_OPTION)
        {
           String filename = fc.getSelectedFile().getAbsolutePath();
           if (!filename .endsWith(".willson")){
                filename += ".willson";
                ArrayList<Object> data = new ArrayList<Object>();
        data.add(depth);
        data.add(WhiteTurn);
        data.add(blackPawns);
        data.add(whitePawns);
        data.add(blackKnights);
        data.add(whiteKnights);
        data.add(whiteRooks);
        data.add(blackRooks);
        data.add(blackBishops);
        data.add(whiteBishops);
        data.add(whiteQueen);
        data.add(blackQueen);
        data.add(blackKing);
        data.add(whiteKing);
        data.add(PointMapper.BoardTilesArray);
        FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(data);
        out.close();
        fileOut.close();
        
        

         }

              
        }
    }
    
    
    public static boolean isKing(int x, int y, String attackingColor) {
       if(!PointMapper.BoardTilesArray[x][y].isEmpty()){
           if(!PointMapper.BoardTilesArray[x][y].piece.color.equalsIgnoreCase(attackingColor)){
               if(PointMapper.BoardTilesArray[x][y].piece.pieceType.equalsIgnoreCase("King") && PointMapper.BoardTilesArray[x][y].piece.alive == true){
                   return true;
               }
           }
       }
        return false;
    }

    public static void Checkmate(String attackingColor) {
        //implement
    }

    public static void attack(int x, int y) {
        PointMapper.BoardTilesArray[x][y].piece.alive = false;
    }

    public static boolean isEnemy(int x, int y, String attackingColor) {
        if (attackingColor.equalsIgnoreCase("White")) {
            if (!PointMapper.BoardTilesArray[x][y].isEmpty()) {
                if (PointMapper.BoardTilesArray[x][y].piece.color.equalsIgnoreCase("Black")) {
                    return true;
                }
            }
        }
        else if (attackingColor.equalsIgnoreCase("Black")) {
            if (!PointMapper.BoardTilesArray[x][y].isEmpty()) {
                if (PointMapper.BoardTilesArray[x][y].piece.color.equalsIgnoreCase("White")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isEmpty(int x, int y) {
//        System.out.println("x : "+x+"y : "+y);
        if(x<0||x>7||y<0||y>7){
            return false;
        }
        return PointMapper.BoardTilesArray[x][y].isEmpty();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setMinimumSize(new java.awt.Dimension(700, 725));
        jPanel1.setPreferredSize(new java.awt.Dimension(720, 750));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    new GameBoard().setVisible(true);
//                } catch (CloneNotSupportedException ex) {
//                    Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            }
//        });
//
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mousePressed(MouseEvent e) {
//        if(PlayerSelected){
//            PlayerSelected=!PlayerSelected;
//            Point p = e.getPoint();
//            p=pm.getTileNumber(p.x, p.y);
//            Tile temp = pm.BoardTilesArray[p.x][p.y];
//            
//        }
//        Point p = e.getPoint();
//        WhiteBishop1Lbl.setLocation(pm.getTileCoordinate(p.x, p.y));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(WhiteTurn== false){
        // first select
        if(First == true)
        {
            // if tile empty
            if(!(PointMapper.BoardTilesArray[PointMapper.getTileRangeX(e.getX())][PointMapper.getTileRangeY(e.getY())].isEmpty()))
            {
                //your turn >> save point
                if(WhiteTurn && "White".equals(PointMapper.BoardTilesArray[PointMapper.getTileRangeX(e.getX())][PointMapper.getTileRangeY(e.getY())].piece.color))
                {
                    FirstSelectedPoint = new Point(PointMapper.getTileRangeX(e.getX()), PointMapper.getTileRangeY(e.getY()));
                    First = false;
                    SelectedLbl.setLocation(PointMapper.getTileCoordinate(e.getX(), e.getY()));
                    SelectedLbl.setVisible(true);
                }
                else if(WhiteTurn == false && "Black".equals(PointMapper.BoardTilesArray[PointMapper.getTileRangeX(e.getX())][PointMapper.getTileRangeY(e.getY())].piece.color)) // show error message to select piece
                {
                    FirstSelectedPoint = new Point(PointMapper.getTileRangeX(e.getX()), PointMapper.getTileRangeY(e.getY()));
                    First = false;
                    SelectedLbl.setLocation(PointMapper.getTileCoordinate(e.getX(), e.getY()));
                    SelectedLbl.setVisible(true);
                }
                else
                {
                    if(WhiteTurn)
                        JOptionPane.showMessageDialog(null, "Please select your piece it is the white turn");
                    else
                        JOptionPane.showMessageDialog(null, "Please select your piece it is the Black turn");
                    First = true;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please select a piece");
                First = true;
            }
        }
        else if (First == false)
        {
            // tile not empty & saved point & your color
            if(((!PointMapper.BoardTilesArray[PointMapper.getTileRangeX(e.getX())][PointMapper.getTileRangeY(e.getY())].isEmpty())&& (FirstSelectedPoint!=null) && WhiteTurn && PointMapper.BoardTilesArray[PointMapper.getTileRangeX(e.getX())][PointMapper.getTileRangeY(e.getY())].piece.color == "White"))
            {
                FirstSelectedPoint = new Point(PointMapper.getTileRangeX(e.getX()), PointMapper.getTileRangeY(e.getY()));
                SelectedLbl.setLocation(PointMapper.getTileCoordinate(e.getX(), e.getY()));
                    SelectedLbl.setVisible(true);
            }
            else if(((!PointMapper.BoardTilesArray[PointMapper.getTileRangeX(e.getX())][PointMapper.getTileRangeY(e.getY())].isEmpty())&& (FirstSelectedPoint!=null) && !WhiteTurn && "Black".equals(PointMapper.BoardTilesArray[PointMapper.getTileRangeX(e.getX())][PointMapper.getTileRangeY(e.getY())].piece.color)))
            {
                FirstSelectedPoint = new Point(PointMapper.getTileRangeX(e.getX()), PointMapper.getTileRangeY(e.getY()));
                SelectedLbl.setLocation(PointMapper.getTileCoordinate(e.getX(), e.getY()));
                    SelectedLbl.setVisible(true);
            }
            
            // already saved & empty tile & can move 
            else if((FirstSelectedPoint!=null)  && (PointMapper.BoardTilesArray[PointMapper.getTileRangeX(e.getX())][PointMapper.getTileRangeY(e.getY())].isEmpty()))
            {
                //move 
                if(PointMapper.BoardTilesArray[FirstSelectedPoint.x][FirstSelectedPoint.y].piece.move(PointMapper.getTileRangeX(e.getX()), PointMapper.getTileRangeY(e.getY())))
                {
                    // checkmate
                WhiteTurn = !WhiteTurn;
                FirstSelectedPoint = null;     
                First = true;
                setPosions();
                jPanel1.repaint();
                
                SelectedLbl.setVisible(false);
                Thread t2 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    AIPlay();
                                } catch (CloneNotSupportedException ex) {
                                    Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                     t2.start();   
                    
                
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "ERROR move");
                }
            }
            // already saved point & not empty & not your color & can move >> attack and move
            else if((FirstSelectedPoint!=null) && (!PointMapper.BoardTilesArray[PointMapper.getTileRangeX(e.getX())][PointMapper.getTileRangeY(e.getY())].isEmpty()) && (WhiteTurn&&PointMapper.BoardTilesArray[PointMapper.getTileRangeX(e.getX())][PointMapper.getTileRangeY(e.getY())].piece.color.equals("Black")) )
            {
                //atack and move 
                if(PointMapper.BoardTilesArray[FirstSelectedPoint.x][FirstSelectedPoint.y].piece.move(PointMapper.getTileRangeX(e.getX()), PointMapper.getTileRangeY(e.getY())))
                {
                     // checkmate
                WhiteTurn = !WhiteTurn;
                FirstSelectedPoint = null;
                First = true;
                setPosions();
                jPanel1.repaint();
                SelectedLbl.setVisible(false);
                     Thread t2 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    AIPlay();
                                } catch (CloneNotSupportedException ex) {
                                    Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                     t2.start();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "ERROR move");
                }
            }
            //can move
            else if((FirstSelectedPoint!=null) && (!PointMapper.BoardTilesArray[PointMapper.getTileRangeX(e.getX())][PointMapper.getTileRangeY(e.getY())].isEmpty()) && (!WhiteTurn&&PointMapper.BoardTilesArray[PointMapper.getTileRangeX(e.getX())][PointMapper.getTileRangeY(e.getY())].piece.color.equals("White")) )
            {
                if(checkCheckmate(AllPiecesCloned).equalsIgnoreCase("Black"))// checkmate
                {

                //atack and move 
                if(PointMapper.BoardTilesArray[FirstSelectedPoint.x][FirstSelectedPoint.y].piece.move(PointMapper.getTileRangeX(e.getX()), PointMapper.getTileRangeY(e.getY())))
                {
                WhiteTurn = !WhiteTurn;
                FirstSelectedPoint = null;
                First = true;
                setPosions();
                jPanel1.repaint();
                SelectedLbl.setVisible(false);
                     Thread t1 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    AIPlay();
                                } catch (CloneNotSupportedException ex) {
                                    Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                     t1.start();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "ERROR move");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "ERROR Can't move");
                First= false;
            }
            }
        }
 
        }
        else
        {
            JOptionPane.showMessageDialog(null, "it is white turn");
        }
        setPosions();
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void initializePiecesPositions() {
        //put the Black Pawns
        blackPawns = new ArrayList();
        for (int i = 0; i < 8; i++) {

            Point p = PointMapper.points[i][6];
            blackPawns.add(new Pawn("Black", new Point(i, 6)));
            blackPawns.get(i).label.setBounds(p.x, p.y, 60, 60);
            PointMapper.BoardTilesArray[i][6].piece = blackPawns.get(i);
            PointMapper.BoardTilesArray[i][6].setEmpty(false);
            jPanel1.add(blackPawns.get(i).label);
//            blackPawn.label.setVisible(true);
        }
        //white Pawns
        whitePawns = new ArrayList();
        for (int i = 0; i < 8; i++) {
            Point p = PointMapper.points[i][1];
            whitePawns.add(new Pawn("White", new Point(i, 1)));
            whitePawns.get(i).label.setBounds(p.x, p.y, 60, 60);
            PointMapper.BoardTilesArray[i][1].piece = whitePawns.get(i);
            PointMapper.BoardTilesArray[i][1].setEmpty(false);
            jPanel1.add(whitePawns.get(i).label);
        }
        //black blackKnights
        blackKnights = new ArrayList(2);
        Point p = PointMapper.points[1][7];
        blackKnights.add(new Knight("Black", new Point(1, 7)));
        blackKnights.get(0).label.setBounds(p.x, p.y, 60, 60);
        PointMapper.BoardTilesArray[1][7].piece = blackKnights.get(0);
        PointMapper.BoardTilesArray[1][7].setEmpty(false);
        jPanel1.add(blackKnights.get(0).label);

        //PointMapper.BoardTilesArray[1][7].piece.position = new Point(1,2);
        p = PointMapper.points[6][7];
        blackKnights.add(new Knight("Black", new Point(6, 7)));
        blackKnights.get(1).label.setBounds(p.x, p.y, 60, 60);
        PointMapper.BoardTilesArray[6][7].piece = blackKnights.get(1);
        PointMapper.BoardTilesArray[6][7].setEmpty(false);
        jPanel1.add(blackKnights.get(1).label);

        //white blackKnights
        whiteKnights = new ArrayList();
        p = PointMapper.points[1][0];
        whiteKnights.add(new Knight("White", new Point(1, 0)));
        whiteKnights.get(0).label.setBounds(p.x, p.y, 60, 60);
        PointMapper.BoardTilesArray[1][0].piece = whiteKnights.get(0);
        PointMapper.BoardTilesArray[1][0].setEmpty(false);
        jPanel1.add(whiteKnights.get(0).label);

        p = PointMapper.points[6][0];
        whiteKnights.add(new Knight("White", new Point(6, 0)));
        whiteKnights.get(1).label.setBounds(p.x, p.y, 60, 60);
        PointMapper.BoardTilesArray[6][0].piece = whiteKnights.get(1);
        PointMapper.BoardTilesArray[6][0].setEmpty(false);
        jPanel1.add(whiteKnights.get(1).label);

        //black Rooks
        blackRooks = new ArrayList();
        p = PointMapper.points[0][7];
        blackRooks.add(new Rook("Black", new Point(0, 7)));
        blackRooks.get(0).label.setBounds(p.x, p.y, 60, 60);
        PointMapper.BoardTilesArray[0][7].piece = blackRooks.get(0);
        PointMapper.BoardTilesArray[0][7].setEmpty(false);
        jPanel1.add(blackRooks.get(0).label);

        p = PointMapper.points[7][7];
        blackRooks.add(new Rook("Black", new Point(7, 7)));
        blackRooks.get(1).label.setBounds(p.x, p.y, 60, 60);
        PointMapper.BoardTilesArray[7][7].piece = blackRooks.get(1);
        PointMapper.BoardTilesArray[7][7].setEmpty(false);
        jPanel1.add(blackRooks.get(1).label);

        //white Rooks
        whiteRooks = new ArrayList();
        p = PointMapper.points[0][0];
        whiteRooks.add(new Rook("White", new Point(0, 0)));
        whiteRooks.get(0).label.setBounds(p.x, p.y, 60, 60);
        PointMapper.BoardTilesArray[0][0].piece = whiteRooks.get(0);
        PointMapper.BoardTilesArray[0][0].setEmpty(false);
        jPanel1.add(whiteRooks.get(0).label);

        p = PointMapper.points[7][0];
        whiteRooks.add(new Rook("White", new Point(7, 0)));
        whiteRooks.get(1).label.setBounds(p.x, p.y, 60, 60);
        PointMapper.BoardTilesArray[7][0].piece = whiteRooks.get(1);
        PointMapper.BoardTilesArray[7][0].setEmpty(false);
        jPanel1.add(whiteRooks.get(1).label);

        //black bishops
        blackBishops = new ArrayList();
        p = PointMapper.points[2][7];
        blackBishops.add(new Bishop("Black", new Point(2, 7)));
        blackBishops.get(0).label.setBounds(p.x, p.y, 60, 60);
        PointMapper.BoardTilesArray[2][7].piece = blackBishops.get(0);
        PointMapper.BoardTilesArray[2][7].setEmpty(false);
        jPanel1.add(blackBishops.get(0).label);

        p = PointMapper.points[5][7];
        blackBishops.add(new Bishop("Black", new Point(5, 7)));
        blackBishops.get(1).label.setBounds(p.x, p.y, 60, 60);
        PointMapper.BoardTilesArray[5][7].piece = blackBishops.get(1);
        PointMapper.BoardTilesArray[5][7].setEmpty(false);
        jPanel1.add(blackBishops.get(1).label);

        //white bishops
        whiteBishops = new ArrayList();
        p = PointMapper.points[2][0];
        whiteBishops.add(new Bishop("White", new Point(2, 0)));
        whiteBishops.get(0).label.setBounds(p.x, p.y, 60, 60);
        PointMapper.BoardTilesArray[2][0].piece = whiteBishops.get(0);
        PointMapper.BoardTilesArray[2][0].setEmpty(false);
        jPanel1.add(whiteBishops.get(0).label);

        p = PointMapper.points[5][0];
        whiteBishops.add(new Bishop("White", new Point(5, 0)));
        whiteBishops.get(1).label.setBounds(p.x, p.y, 60, 60);
        PointMapper.BoardTilesArray[5][0].piece = whiteBishops.get(1);
        PointMapper.BoardTilesArray[5][0].setEmpty(false);
        jPanel1.add(whiteBishops.get(1).label);

        //black Queen
        p = PointMapper.points[4][7];
        blackQueen = new Queen("Black", new Point(4, 7));
        blackQueen.label.setBounds(p.x, p.y, 60, 60);
        PointMapper.BoardTilesArray[4][7].piece = blackQueen;
        PointMapper.BoardTilesArray[4][7].setEmpty(false);
        jPanel1.add(blackQueen.label);

        //white Queen
        p = PointMapper.points[4][0];
        whiteQueen = new Queen("White", new Point(4, 0));
        whiteQueen.label.setBounds(p.x, p.y, 60, 60);
        PointMapper.BoardTilesArray[4][0].piece = whiteQueen;
        PointMapper.BoardTilesArray[4][0].setEmpty(false);
        jPanel1.add(whiteQueen.label);
        
        p=PointMapper.points[3][0];
        whiteKing=new King("White", new Point(3,0));
        whiteKing.label.setBounds(p.x, p.y, 60, 60);
        PointMapper.BoardTilesArray[3][0].piece=whiteKing;
        PointMapper.BoardTilesArray[3][0].setEmpty(false);
        jPanel1.add(whiteKing.label);
        
        p=PointMapper.points[3][7];
        blackKing=new King("Black", new Point(3,7));
        blackKing.label.setBounds(p.x, p.y, 60, 60);
        PointMapper.BoardTilesArray[3][7].piece=blackKing;
        PointMapper.BoardTilesArray[3][7].setEmpty(false);
        jPanel1.add(blackKing.label);

    }

    public void setPosions() {
        //super.paintComponents(g); //To change body of generated methods, choose Tools | Templates.
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (PointMapper.BoardTilesArray[i][j].isEmpty() == false) {
                    //Piece p = t.getPiece();

                    if (!PointMapper.BoardTilesArray[i][j].getPiece().alive )
                    {
                        PointMapper.BoardTilesArray[i][j].getPiece().label.setLocation(900, 900);
                    }
            }
        }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //Tile t = PointMapper.BoardTilesArray[i][j];
                
                
                if (PointMapper.BoardTilesArray[i][j].isEmpty() == false) {
                    //Piece p = t.getPiece();

                    if (PointMapper.BoardTilesArray[i][j].getPiece().alive ) {
                        if(!(PointMapper.BoardTilesArray[i][j].getPiece().position.equals(PointMapper.getTileNumber(PointMapper.BoardTilesArray[i][j].getPiece().label.getLocation().x, PointMapper.BoardTilesArray[i][j].getPiece().label.getLocation().y))))
                        {
                        PointMapper.BoardTilesArray[i][j].getPiece().label.setLocation(PointMapper.points[PointMapper.BoardTilesArray[i][j].getPiece().position.x][PointMapper.BoardTilesArray[i][j].getPiece().position.y]);
                        PointMapper.BoardTilesArray[i][j].setEmpty(true);
                        
                        PointMapper.BoardTilesArray[PointMapper.BoardTilesArray[i][j].getPiece().position.x][PointMapper.BoardTilesArray[i][j].getPiece().position.y].setEmpty(false);
                        PointMapper.BoardTilesArray[PointMapper.BoardTilesArray[i][j].getPiece().position.x][PointMapper.BoardTilesArray[i][j].getPiece().position.y].piece = PointMapper.BoardTilesArray[i][j].getPiece();
                    
                        }
                    }
                    
                   
                    
                }
            }
        }
        
        
    
        System.out.println("done ");
    
    
    
    
    
    
    }
    
    public void AIPlay() throws CloneNotSupportedException
            
    {
        if(WhiteTurn){
            AllPieces = new ArrayList<Piece>();
        for (int i = 0; i < blackPawns.size(); i++) {
            AllPieces.add(blackPawns.get(i).clone());
        }
        for (int i = 0; i < whitePawns.size(); i++) {
            AllPieces.add(whitePawns.get(i).clone());
        }
        for (int i = 0; i < blackKnights.size(); i++) {
            AllPieces.add(blackKnights.get(i).clone());
        }
        for (int i = 0; i < whiteKnights.size(); i++) {
            AllPieces.add(whiteKnights.get(i).clone());
        }
        for (int i = 0; i < whiteRooks.size(); i++) {
            AllPieces.add(whiteRooks.get(i).clone());
        }
        for (int i = 0; i < blackRooks.size(); i++) {
            AllPieces.add(blackRooks.get(i).clone());
        }
        for (int i = 0; i < blackBishops.size(); i++) {
            AllPieces.add(blackBishops.get(i).clone());
        }
        for (int i = 0; i < whiteBishops.size(); i++) {
            AllPieces.add(whiteBishops.get(i).clone());
        }
        AllPieces.add(blackQueen.clone());
        AllPieces.add(whiteQueen.clone());
        AllPieces.add(blackKing.clone());
        AllPieces.add(whiteKing.clone());
            //AI Plays
//            System.out.println("All Pieces"+AllPieces);
            AllPiecesCloned = new ArrayList<Piece>();
        for (int i = 0; i < blackPawns.size(); i++) {
            AllPiecesCloned.add(blackPawns.get(i));
        }
        for (int i = 0; i < whitePawns.size(); i++) {
            AllPiecesCloned.add(whitePawns.get(i));
        }
        for (int i = 0; i < blackKnights.size(); i++) {
            AllPiecesCloned.add(blackKnights.get(i));
        }
        for (int i = 0; i < whiteKnights.size(); i++) {
            AllPiecesCloned.add(whiteKnights.get(i));
        }
        for (int i = 0; i < whiteRooks.size(); i++) {
            AllPiecesCloned.add(whiteRooks.get(i));
        }
        for (int i = 0; i < blackRooks.size(); i++) {
            AllPiecesCloned.add(blackRooks.get(i));
        }
        for (int i = 0; i < blackBishops.size(); i++) {
            AllPiecesCloned.add(blackBishops.get(i));
        }
        for (int i = 0; i < whiteBishops.size(); i++) {
            AllPiecesCloned.add(whiteBishops.get(i));
        }
        AllPiecesCloned.add(blackQueen);
        AllPiecesCloned.add(whiteQueen);
        AllPiecesCloned.add(blackKing);
        AllPiecesCloned.add(whiteKing);
            Node n = new Node(AllPieces,Integer.MIN_VALUE,Integer.MAX_VALUE,true);
            ArrayList<Piece> newList = Node.Play(n,depth,n.alpha,n.beta,true);
            WhiteTurn=!WhiteTurn;
            for (int i = 0; i <newList.size(); i++) {
                if(!(newList.get(i).position.getX() == AllPiecesCloned.get(i).position.getX() && newList.get(i).position.getY() == AllPiecesCloned.get(i).position.getY()))
                {
                    int x = newList.get(i).position.x;
                    int y = newList.get(i).position.y;
                    if(AllPiecesCloned.get(i).move(x, y))
                    {
                        JOptionPane.showMessageDialog(null, newList.get(i).pieceType+" moved to ("+ x + " , "+y +")");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, newList.get(i).pieceType+" DIDN'T move ("+ x + " , "+y +")");
                    }
                    
                    
                    
                }
            }
        }
        setPosions();
       // checkMate = checkCheckmate(AllPiecesCloned,); 
    }
    
    public static boolean isTileThreatened(String Color,int x, int y){
        
        for(int i =0 ; i<AllPieces.size();i++){
            if(AllPieces.get(i).color!=Color &&AllPieces.get(i).alive){
                if(AllPieces.get(i).pieceType.equalsIgnoreCase("Pawn")){
                    Pawn p =(Pawn) AllPieces.get(i);
                    if(p.canAttack(x, y))
                        return true;
                    else 
                        return false;
                }
                else if(AllPieces.get(i).validateMove(x, y))
                    return true;
            }
        }
        return false;
        
    }
    public static String checkCheckmate(ArrayList<Piece> AllPieceCheck){
        int WhiteX=0;
        int WhiteY=0;
        int BlackX=0;
        int BlackY=0;
        for(int i=0;i<AllPieces.size();i++){
            if(AllPieceCheck.get(i).color.equalsIgnoreCase("White") &&AllPieceCheck.get(i).pieceType.equalsIgnoreCase("King") && AllPieceCheck.get(i).alive)
            {
                 WhiteX = (int) AllPieceCheck.get(i).position.getX();
                 WhiteY = (int) AllPieceCheck.get(i).position.getY();

            }
            else if(AllPieceCheck.get(i).color.equalsIgnoreCase("Black") &&AllPieceCheck.get(i).pieceType.equalsIgnoreCase("King") && AllPieceCheck.get(i).alive)
            {
                 BlackX = (int) AllPieceCheck.get(i).position.getX();
                 BlackY = (int) AllPieceCheck.get(i).position.getY();

            }
        }
        for(int i=0;i<AllPieces.size();i++){
            if(AllPieceCheck.get(i).color.equalsIgnoreCase("Black") && AllPieceCheck.get(i).alive){
                if(AllPieceCheck.get(i).validateMove(WhiteX, WhiteY))
                {
                    return "White";
                }
            }
            else if(AllPieceCheck.get(i).color.equalsIgnoreCase("White") && AllPieceCheck.get(i).alive){
                if(AllPieceCheck.get(i).validateMove(BlackX, BlackY))
                {
                    return "Black";
                }
            }
        }
        return null;
    }


    
    

}
