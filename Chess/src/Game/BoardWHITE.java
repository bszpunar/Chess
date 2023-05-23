package Game;

import Shapes.*;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


import static java.lang.Thread.sleep;

public class BoardWHITE extends JPanel implements MouseListener {

    public Square[][] gameBoard = new Square[8][8];


    private final static int WIDTH = 420;
    private final static int HEIGHT = 480;



    String[] alfabet = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};

    private int x, y;
    public int clickCounter = 0;

    public int rowBoard = 0;
    public int columnBoard = 0;

    public int rowBoardToDestroy, columnBoardToDestroy;

    public Socket socket;

    public int port;

    public String address;
    public ServerSocket serverSocket;
    public ObjectOutputStream oos;
    public ObjectInputStream ois;

    public int rowBoardToDestroyOdebrany;
    public int columnBoardToDestroyOdebrany;
    public int rowBoardOdebrany;
    public int columnBoardOdebrany;

    public int varForIdentity;


    public boolean moveBlackDone = false;
    public boolean moveWhiteDone = false;
    public boolean blackMove = false;
    public boolean whiteMove = true;


    Image whitePawnImage = new ImageIcon(getClass().getClassLoader().getResource("whitePawn.png")).getImage();
    Image blackPawnImage = new ImageIcon(getClass().getClassLoader().getResource("blackPawn.png")).getImage();

    Image whiteQueenImage = new ImageIcon(getClass().getClassLoader().getResource("whiteQueen.png")).getImage();
    Image blackQueenImage = new ImageIcon(getClass().getClassLoader().getResource("blackQueen.png")).getImage();
    //King
    Image whiteKingImage = new ImageIcon(getClass().getClassLoader().getResource("whiteKing.png")).getImage();
    Image blackKingImage = new ImageIcon(getClass().getClassLoader().getResource("blackKing.png")).getImage();
    //Rook
    Image whiteRookImage = new ImageIcon(getClass().getClassLoader().getResource("whiteRook.png")).getImage();
    Image blackRookImage = new ImageIcon(getClass().getClassLoader().getResource("blackRook.png")).getImage();
    //Bishop
    Image whiteBishopImage = new ImageIcon(getClass().getClassLoader().getResource("whiteBishop.png")).getImage();
    Image blackBishopImage = new ImageIcon(getClass().getClassLoader().getResource("blackBishop.png")).getImage();
    //Knight
    Image whiteKnightImage = new ImageIcon(getClass().getClassLoader().getResource("whiteKnight.png")).getImage();
    Image blackKnightImage = new ImageIcon(getClass().getClassLoader().getResource("blackKnight.png")).getImage();

    Image greenLight = new ImageIcon(getClass().getClassLoader().getResource("greenLight.png")).getImage();
    Image redLight = new ImageIcon(getClass().getClassLoader().getResource("redLight.png")).getImage();

    //BIALE
    Knight whiteKnight1 = new Knight(1, 2, "whiteKnight1", false, true);
    Knight whiteKnight2 = new Knight(1, 7, "whiteKnight2", false, true);

    Bishop whiteBishop1 = new Bishop(1, 3, "whiteBishop1", false, true);
    Bishop whiteBishop2 = new Bishop(1, 6, "whiteBishop2", false, true);

    Rook whiteRook1 = new Rook(1, 1, "whiteRook1", false, true);
    Rook whiteRook2 = new Rook(1, 8, "whiteRook2", false, true);

    King whiteKing = new King(1, 5, "whiteKing", false);

    Queen whiteQueen = new Queen(1, 4, "whiteQueen", false, true);

    Pawn whitePawn1 = new Pawn(2, 1, "whitePawn1", false, false, whitePawnImage, 0, true);
    Pawn whitePawn2 = new Pawn(2, 2, "whitePawn2", false, false, whitePawnImage, 0, true);
    Pawn whitePawn3 = new Pawn(2, 3, "whitePawn3", false, false, whitePawnImage, 0, true);
    Pawn whitePawn4 = new Pawn(2, 4, "whitePawn4", false, false, whitePawnImage, 0, true);
    Pawn whitePawn5 = new Pawn(2, 5, "whitePawn5", false, false, whitePawnImage, 0, true);
    Pawn whitePawn6 = new Pawn(2, 6, "whitePawn6", false, false, whitePawnImage, 0, true);
    Pawn whitePawn7 = new Pawn(2, 7, "whitePawn7", false, false, whitePawnImage, 0, true);
    Pawn whitePawn8 = new Pawn(2, 8, "whitePawn8", false, false, whitePawnImage, 0, true);


    //CZARNE
    Knight blackKnight1 = new Knight(8, 2, "blackKnight1", false, false);
    Knight blackKnight2 = new Knight(8, 7, "blackKnight2", false, false);

    Bishop blackBishop1 = new Bishop(8, 3, "balckBishop1", false, false);
    Bishop blackBishop2 = new Bishop(8, 6, "blackBishop2", false, false);

    Rook blackRook1 = new Rook(8, 1, "blackRook1", false, false);
    Rook blackRook2 = new Rook(8, 8, "blackRook2", false, false);

    King blackKing = new King(8, 5, "blackKing", false);

    Queen blackQueen = new Queen(8, 4, "blackQueen", false, false);

    Pawn blackPawn1 = new Pawn(7, 1, "blackPawn1", false, false, blackPawnImage, 0, false);
    Pawn blackPawn2 = new Pawn(7, 2, "blackPawn2", false, false, blackPawnImage, 0, false);
    Pawn blackPawn3 = new Pawn(7, 3, "blackPawn3", false, false, blackPawnImage, 0, false);
    Pawn blackPawn4 = new Pawn(7, 4, "blackPawn4", false, false, blackPawnImage, 0, false);

    Pawn blackPawn5 = new Pawn(7, 5, "blackPawn5", false, false, blackPawnImage, 0, false);
    Pawn blackPawn6 = new Pawn(7, 6, "blackPawn6", false, false, blackPawnImage, 0, false);
    Pawn blackPawn7 = new Pawn(7, 7, "blackPawn7", false, false, blackPawnImage, 0, false);
    Pawn blackPawn8 = new Pawn(7, 8, "blackPawn8", false, false, blackPawnImage, 0, false);


    public BoardWHITE(int port) {

        setBackground(Color.BLACK);
        addMouseListener(this);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.port = port;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                gameBoard[i][j] = new Square(i, j, "" + (i + 1) + "" + (j + 1), false,
                        false, 0);
                if (i == 0 && j <= 7) gameBoard[i][j].identityOfSquare = j + 1;
                if (i == 1 && j <= 7) gameBoard[i][j].identityOfSquare = j + 1 + 8;
                if (i == 6 && j <= 7) gameBoard[i][j].identityOfSquare = j + 1 + 48;
                if (i == 7 && j <= 7) gameBoard[i][j].identityOfSquare = j + 1 + 48 + 8;

            }

        }
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 7; j++) {
                gameBoard[i][j].occupiedFieldByWHITE = true;
                gameBoard[i][j].occupiedFieldByBlack = false;
            }
        }
        for (int i = 6; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                gameBoard[i][j].occupiedFieldByBlack = true;
                gameBoard[i][j].occupiedFieldByWHITE = false;
            }
        }
        callServer();
    }

    public BoardWHITE(String address, int port) {


        setBackground(Color.BLACK);
        addMouseListener(this);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.address = address;
        this.port = port;


        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                gameBoard[i][j] = new Square(i, j, "" + (i + 1) + "" + (j + 1), false, false, 0);
                if (i == 0 && j <= 7) gameBoard[i][j].identityOfSquare = j + 1;
                if (i == 1 && j <= 7) gameBoard[i][j].identityOfSquare = j + 1 + 8;

                if (i == 6 && j <= 7) gameBoard[i][j].identityOfSquare = j + 1 + 48;
                if (i == 7 && j <= 7) gameBoard[i][j].identityOfSquare = j + 1 + 48 + 8;

            }

        }


        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 7; j++) {
                gameBoard[i][j].occupiedFieldByWHITE = true;
                gameBoard[i][j].occupiedFieldByBlack = false;
            }
        }

        for (int i = 6; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                gameBoard[i][j].occupiedFieldByBlack = true;
                gameBoard[i][j].occupiedFieldByWHITE = false;
            }
        }
        callClient();


    }

    public static void moveSound(String filepath) {
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filepath)));
            clip.start();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeWhitePossibles() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                gameBoard[i][j].possibleMoveWhite = false;
            }
        }
    }

    public void removeBlackPossibles() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                gameBoard[i][j].possibleMoveBlack = false;
            }
        }
    }


    public void callServer() {
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        thread1.start();
        thread3.start();

        System.out.println("Let's Play Chess");
    }


    public void setOptionsForAll() {
        whiteKnight1.setMoveOptionsForWhite(gameBoard, blackKing);
        whiteKnight2.setMoveOptionsForWhite(gameBoard, blackKing);
        whitePawn1.setMoveOptionsForWhite(gameBoard, blackKing);
        whitePawn2.setMoveOptionsForWhite(gameBoard, blackKing);
        whitePawn3.setMoveOptionsForWhite(gameBoard, blackKing);
        whitePawn4.setMoveOptionsForWhite(gameBoard, blackKing);
        whitePawn5.setMoveOptionsForWhite(gameBoard, blackKing);
        whitePawn6.setMoveOptionsForWhite(gameBoard, blackKing);
        whitePawn7.setMoveOptionsForWhite(gameBoard, blackKing);
        whitePawn8.setMoveOptionsForWhite(gameBoard, blackKing);

        whiteBishop1.setMoveOptionsForWhite(gameBoard, blackKing);
        whiteBishop2.setMoveOptionsForWhite(gameBoard, blackKing);

        whiteRook1.setMoveOptionsForWhite(gameBoard, blackKing);
        whiteRook2.setMoveOptionsForWhite(gameBoard, blackKing);

        whiteQueen.setMoveOptionForWhite(gameBoard, blackKing);


        blackKnight1.setMoveOptionsForBlack(gameBoard, whiteKing);
        blackKnight2.setMoveOptionsForBlack(gameBoard, whiteKing);
        blackPawn1.setMoveOptionsForBlack(gameBoard, whiteKing);
        blackPawn2.setMoveOptionsForBlack(gameBoard, whiteKing);
        blackPawn3.setMoveOptionsForBlack(gameBoard, whiteKing);
        blackPawn4.setMoveOptionsForBlack(gameBoard, whiteKing);
        blackPawn5.setMoveOptionsForBlack(gameBoard, whiteKing);
        blackPawn6.setMoveOptionsForBlack(gameBoard, whiteKing);
        blackPawn7.setMoveOptionsForBlack(gameBoard, whiteKing);
        blackPawn8.setMoveOptionsForBlack(gameBoard, whiteKing);

        blackBishop1.setMoveOptionsForBlack(gameBoard, whiteKing);
        blackBishop2.setMoveOptionsForBlack(gameBoard, whiteKing);

        blackRook1.setMoveOptionsForBlack(gameBoard, whiteKing);
        blackRook2.setMoveOptionsForBlack(gameBoard, whiteKing);

        blackQueen.setMoveOptionForBlack(gameBoard, whiteKing);

        //King
        blackKing.setMoveOptionsForBlack(gameBoard);
        whiteKing.setMoveOptionsForWhite(gameBoard);

    }

    public void removeOptionsForAll() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                gameBoard[i][j].possibleMoveBlack = false;
                gameBoard[i][j].possibleMoveWhite = false;
                gameBoard[i][j].possibleMoveBlackKING = false;
                gameBoard[i][j].possibleMoveWhiteKING = false;
            }
        }
    }

    public void callClient() {
        try {
            socket = new Socket(address, port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            System.out.println("Server is not available!");
        }

        thread1.start();
        thread3.start();

        System.out.println("Let's Play Chess");

    }

    public void setColorsForFields() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + 1) % 2 == 0 && (j + 1) % 2 == 0 || (i + 1) % 2 != 0 && (j + 1) % 2 != 0) {
                    //czarne
                    gameBoard[i][j].czarnePole = true;
                    gameBoard[i][j].białePole = false;
                    gameBoard[i][j].kolorPola = "Czarne";

                }

                if ((i + 1) % 2 != 0 && (j + 1) % 2 == 0 || (i + 1) % 2 == 0 && (j + 1) % 2 != 0) {
                    //biale
                    gameBoard[i][j].białePole = true;
                    gameBoard[i][j].czarnePole = false;
                    gameBoard[i][j].kolorPola = "Białe";
                }
            }
        }
    }


    public void pokazDane() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                setColorsForFields();
                gameBoard[i][j].pokazNazwyPol();
                System.out.print("       ");
            }
            System.out.println();
        }
    }

    //ustawianie danych które są porzebne by sprawdzic czy ruch na szachu jest mozliwy potem je usuwamy
    public void setAdditionalOptionsForWhite(int rD, int cD, int r, int c) {
        gameBoard[rD - 1][cD - 1].occupiedFieldByWHITE = false;
        gameBoard[rD - 1][cD - 1].occupiedFieldByBlack = false;
        gameBoard[r - 1][c - 1].occupiedFieldByWHITE = true;
        gameBoard[r - 1][c - 1].occupiedFieldByBlack = false;

        varForIdentity = gameBoard[rD - 1][cD - 1].identityOfSquare;
        gameBoard[rD - 1][cD - 1].identityOfSquare = 0;
        gameBoard[r - 1][c - 1].identityOfSquare = varForIdentity;

    }

    public void setAdditionalOptionsForBlack(int rD, int cD, int r, int c) {
        gameBoard[rD - 1][cD - 1].occupiedFieldByWHITE = false;
        gameBoard[rD - 1][cD - 1].occupiedFieldByBlack = false;
        gameBoard[r - 1][c - 1].occupiedFieldByWHITE = false;
        gameBoard[r - 1][c - 1].occupiedFieldByBlack = true;

        varForIdentity = gameBoard[rD - 1][cD - 1].identityOfSquare;
        gameBoard[rD - 1][cD - 1].identityOfSquare = 0;
        gameBoard[r - 1][c - 1].identityOfSquare = varForIdentity;
    }


    //usuwanie danych ustawionych podczas sprawdzania czy ruch danej figury na szachu jest mozliwy
    public void removeAdditionalOptionsForALL(int r, int c) {


        if(gameBoard[r - 1][c - 1].identityOfSquare >= 49 && gameBoard[r - 1][c - 1].identityOfSquare <= 64){
            gameBoard[r - 1][c - 1].occupiedFieldByWHITE = false;
            gameBoard[r - 1][c - 1].occupiedFieldByBlack = true;
        }else if(gameBoard[r - 1][c - 1].identityOfSquare >= 1 && gameBoard[r - 1][c - 1].identityOfSquare <= 16){
            gameBoard[r - 1][c - 1].occupiedFieldByWHITE = true;
            gameBoard[r - 1][c - 1].occupiedFieldByBlack = false;
        }else if(gameBoard[r - 1][c - 1].identityOfSquare == 0){
            gameBoard[r - 1][c - 1].occupiedFieldByWHITE = false;
            gameBoard[r - 1][c - 1].occupiedFieldByBlack = false;
        }

    }

    public void addAdditionalOptionsForALLinRealase(int row, int column, int identity) {

        if(identity >= 49 && identity <= 64){
            gameBoard[row - 1][column - 1].occupiedFieldByWHITE = false;
            gameBoard[row - 1][column - 1].occupiedFieldByBlack = true;
            gameBoard[row - 1][column - 1].identityOfSquare = identity;
        }else if(identity >= 1 && identity <= 16){
            gameBoard[row - 1][column - 1].occupiedFieldByWHITE = true;
            gameBoard[row - 1][column - 1].occupiedFieldByBlack = false;
            gameBoard[row - 1][column - 1].identityOfSquare = identity;
        }



    }


    public void removeAdditionalOptionsForALLinRealase(int r, int c, int var) {

        switch (var){
            case 49: case 64: case 50: case 51: case 52: case 53: case 54:
                case 55: case 56: case 57: case 58: case 59: case 60: case 61: case 62: case 63:
                gameBoard[r-1][c-1].occupiedFieldByWHITE = false;
                gameBoard[r-1][c-1].occupiedFieldByBlack = true;
                gameBoard[r-1][c-1].identityOfSquare = var;
                break;
            case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8:
                case 9: case 10: case 11: case 12: case 13: case 14: case 15: case 16:
                gameBoard[r-1][c-1].occupiedFieldByWHITE = true;
                gameBoard[r-1][c-1].occupiedFieldByBlack = false;
                gameBoard[r-1][c-1].identityOfSquare = var;
                break;
            case 0:
                gameBoard[r-1][c-1].occupiedFieldByWHITE = false;
                gameBoard[r-1][c-1].occupiedFieldByBlack = false;
                gameBoard[r-1][c-1].identityOfSquare = 0;
                break;

        }
    }






    public void endGame() {
        if (whiteKing.checkMate) {
            JOptionPane.showMessageDialog(this, "You lost !");
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
            new GameStart();


        } else if (blackKing.checkMate) {
            JOptionPane.showMessageDialog(this, "Victory !");
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
            new GameStart();


        }

        if((whiteKing.draw && !whiteKing.check) || (blackKing.draw && !blackKing.check)){
            JOptionPane.showMessageDialog(this, "Draw !");
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
            new GameStart();
        }


    }


    public void checkCollision(){

        Pawn[] pawnsTab = new Pawn[16];
        pawnsTab[0] = whitePawn1;
        pawnsTab[1] = whitePawn2;
        pawnsTab[2] = whitePawn3;
        pawnsTab[3] = whitePawn4;
        pawnsTab[4] = whitePawn5;
        pawnsTab[5] = whitePawn6;
        pawnsTab[6] = whitePawn7;
        pawnsTab[7] = whitePawn8;
        pawnsTab[8] = blackPawn1;
        pawnsTab[9] = blackPawn2;
        pawnsTab[10] = blackPawn3;
        pawnsTab[11] = blackPawn4;
        pawnsTab[12] = blackPawn5;
        pawnsTab[13] = blackPawn6;
        pawnsTab[14] = blackPawn7;
        pawnsTab[15] = blackPawn8;

        Knight[] knightsTab = new Knight[4];
        knightsTab[0] = whiteKnight1;
        knightsTab[1] = whiteKnight2;
        knightsTab[2] = blackKnight1;
        knightsTab[3] = blackKnight2;

        Rook[] rooksTab = new Rook[4];
        rooksTab[0] = whiteRook1;
        rooksTab[1] = whiteRook2;
        rooksTab[2] = blackRook1;
        rooksTab[3] = blackRook2;

        Bishop[] bishopsTab = new Bishop[4];
        bishopsTab[0] = whiteBishop1;
        bishopsTab[1] = whiteBishop2;
        bishopsTab[2] = blackBishop1;
        bishopsTab[3] = blackBishop2;

        Queen[] queensTab = new Queen[2];
        queensTab[0] = whiteQueen;
        queensTab[1] = blackQueen;



        //pawnsCollision
        for (int i = 0; i < pawnsTab.length; i++) {
            for (int j = 0; j < pawnsTab.length; j++) {
                if (pawnsTab[i].row == pawnsTab[j].row && pawnsTab[i].column == pawnsTab[j].column && whiteMove && i != j) {

                    if (!pawnsTab[i].whitePawn) {
                        pawnsTab[i].row = 0;
                        pawnsTab[i].column = 0;
                        break;
                    } else if (!pawnsTab[j].whitePawn) {
                        pawnsTab[j].row = 0;
                        pawnsTab[j].column = 0;
                        break;
                    }

                } else if (pawnsTab[i].row == pawnsTab[j].row && pawnsTab[i].column == pawnsTab[j].column && !whiteMove && i != j) {
                    if (pawnsTab[i].whitePawn) {
                        pawnsTab[i].row = 0;
                        pawnsTab[i].column = 0;
                        break;
                    } else if (pawnsTab[j].whitePawn) {
                        pawnsTab[j].row = 0;
                        pawnsTab[j].column = 0;
                        break;
                    }
                }

            }
        }
        //knightsCollision
        for (int i = 0; i < knightsTab.length; i++) {
            for (int j = 0; j < knightsTab.length; j++) {
                if (knightsTab[i].row == knightsTab[j].row && knightsTab[i].column == knightsTab[j].column && whiteMove && i != j) {

                    if (!knightsTab[i].whiteKnight) {
                        knightsTab[i].row = 0;
                        knightsTab[i].column = 0;
                        break;
                    } else if (!knightsTab[j].whiteKnight) {
                        knightsTab[j].row = 0;
                        knightsTab[j].column = 0;
                        break;
                    }

                } else if (knightsTab[i].row == knightsTab[j].row && knightsTab[i].column == knightsTab[j].column && !whiteMove && i != j) {
                    if (knightsTab[i].whiteKnight) {
                        knightsTab[i].row = 0;
                        knightsTab[i].column = 0;
                        break;
                    } else if (knightsTab[j].whiteKnight) {
                        knightsTab[j].row = 0;
                        knightsTab[j].column = 0;
                        break;
                    }
                }

            }
        }

        //rooksCollision

        for (int i = 0; i < rooksTab.length; i++) {
            for (int j = 0; j < rooksTab.length; j++) {
                if (rooksTab[i].row == rooksTab[j].row && rooksTab[i].column == rooksTab[j].column && whiteMove && i != j) {

                    if (!rooksTab[i].whiteRook) {
                        rooksTab[i].row = 0;
                        rooksTab[i].column = 0;
                        break;
                    } else if (!rooksTab[j].whiteRook) {
                        rooksTab[j].row = 0;
                        rooksTab[j].column = 0;
                        break;
                    }

                } else if (rooksTab[i].row == rooksTab[j].row && rooksTab[i].column == rooksTab[j].column && !whiteMove && i != j) {
                    if (rooksTab[i].whiteRook) {
                        rooksTab[i].row = 0;
                        rooksTab[i].column = 0;
                        break;
                    } else if (rooksTab[j].whiteRook) {
                        rooksTab[j].row = 0;
                        rooksTab[j].column = 0;
                        break;
                    }
                }

            }
        }

        //bishopsCollision

        for (int i = 0; i < bishopsTab.length; i++) {
            for (int j = 0; j < bishopsTab.length; j++) {
                if (bishopsTab[i].row == bishopsTab[j].row && bishopsTab[i].column == bishopsTab[j].column && whiteMove && i != j) {

                    if (!bishopsTab[i].whiteBishop) {
                        bishopsTab[i].row = 0;
                        bishopsTab[i].column = 0;
                        break;
                    } else if (!bishopsTab[j].whiteBishop) {
                        bishopsTab[j].row = 0;
                        bishopsTab[j].column = 0;
                        break;
                    }

                } else if (bishopsTab[i].row == bishopsTab[j].row && bishopsTab[i].column == bishopsTab[j].column && !whiteMove && i != j) {
                    if (bishopsTab[i].whiteBishop) {
                        bishopsTab[i].row = 0;
                        bishopsTab[i].column = 0;
                        break;
                    } else if (bishopsTab[j].whiteBishop) {
                        bishopsTab[j].row = 0;
                        bishopsTab[j].column = 0;
                        break;
                    }
                }

            }
        }



        //queensCollision

        for (int i = 0; i < queensTab.length; i++) {
            for (int j = 0; j < queensTab.length; j++) {
                if (queensTab[i].row == queensTab[j].row && queensTab[i].column == queensTab[j].column && whiteMove && i != j) {

                    if (!queensTab[i].whiteQueen) {
                        queensTab[i].row = 0;
                        queensTab[i].column = 0;
                        break;
                    } else if (!queensTab[j].whiteQueen) {
                        queensTab[j].row = 0;
                        queensTab[j].column = 0;
                        break;
                    }

                } else if (queensTab[i].row == queensTab[j].row && queensTab[i].column == queensTab[j].column && !whiteMove && i != j) {
                    if (queensTab[i].whiteQueen) {
                        queensTab[i].row = 0;
                        queensTab[i].column = 0;
                        break;
                    } else if (queensTab[j].whiteQueen) {
                        queensTab[j].row = 0;
                        queensTab[j].column = 0;
                        break;
                    }
                }

            }
        }








        //collision whiteRook1

        /*if ((whiteRook1.row == blackPawn1.row && whiteRook1.column == blackPawn1.column)) {
            if (blackMove) {
                whiteRook1.row = 0;
                whiteRook1.column = 0;
            } else if (whiteMove) {
                blackPawn1.row = 0;
                blackPawn1.column = 0;
            }


        } else if ((whiteRook1.row == blackPawn2.row && whiteRook1.column == blackPawn2.column)) {
            if (blackMove) {
                whiteRook1.row = 0;
                whiteRook1.column = 0;
            } else if (whiteMove) {
                blackPawn2.row = 0;
                blackPawn2.column = 0;
            }

        } else if ((whiteRook1.row == blackPawn3.row && whiteRook1.column == blackPawn3.column)) {
            if (blackMove) {
                whiteRook1.row = 0;
                whiteRook1.column = 0;
            } else if (whiteMove) {
                blackPawn3.row = 0;
                blackPawn3.column = 0;
            }

        } else if ((whiteRook1.row == blackPawn4.row && whiteRook1.column == blackPawn4.column)) {
            if (blackMove) {
                whiteRook1.row = 0;
                whiteRook1.column = 0;
            } else if (whiteMove) {
                blackPawn4.row = 0;
                blackPawn4.column = 0;
            }

        } else if ((whiteRook1.row == blackPawn5.row && whiteRook1.column == blackPawn5.column)) {
            if (blackMove) {
                whiteRook1.row = 0;
                whiteRook1.column = 0;
            } else if (whiteMove) {
                blackPawn5.row = 0;
                blackPawn5.column = 0;
            }

        } else if ((whiteRook1.row == blackPawn6.row && whiteRook1.column == blackPawn6.column)) {
            if (blackMove) {
                whiteRook1.row = 0;
                whiteRook1.column = 0;
            } else if (whiteMove) {
                blackPawn6.row = 0;
                blackPawn6.column = 0;
            }

        } else if ((whiteRook1.row == blackPawn7.row && whiteRook1.column == blackPawn7.column)) {
            if (blackMove) {
                whiteRook1.row = 0;
                whiteRook1.column = 0;
            } else if (whiteMove) {
                blackPawn7.row = 0;
                blackPawn7.column = 0;
            }

        } else if ((whiteRook1.row == blackPawn8.row && whiteRook1.column == blackPawn8.column)) {
            if (blackMove) {
                whiteRook1.row = 0;
                whiteRook1.column = 0;
            } else if (whiteMove) {
                blackPawn8.row = 0;
                blackPawn8.column = 0;
            }

        } else if ((whiteRook1.row == blackBishop1.row && whiteRook1.column == blackBishop1.column)) {
            if (blackMove) {
                whiteRook1.row = 0;
                whiteRook1.column = 0;
            } else if (whiteMove) {
                blackBishop1.row = 0;
                blackBishop1.column = 0;
            }

        } else if ((whiteRook1.row == blackBishop2.row && whiteRook1.column == blackBishop2.column)) {
            if (blackMove) {
                whiteRook1.row = 0;
                whiteRook1.column = 0;
            } else if (whiteMove) {
                blackBishop2.row = 0;
                blackBishop2.column = 0;
            }

        } else if ((whiteRook1.row == blackRook1.row && whiteRook1.column == blackRook1.column)) {
            if (blackMove) {
                whiteRook1.row = 0;
                whiteRook1.column = 0;
            } else if (whiteMove) {
                blackRook1.row = 0;
                blackRook1.column = 0;
            }

        } else if ((whiteRook1.row == blackRook2.row && whiteRook1.column == blackRook2.column)) {
            if (blackMove) {
                whiteRook1.row = 0;
                whiteRook1.column = 0;
            } else if (whiteMove) {
                blackRook2.row = 0;
                blackRook2.column = 0;
            }

        } else if ((whiteRook1.row == blackKnight1.row && whiteRook1.column == blackKnight1.column)) {
            if (blackMove) {
                whiteRook1.row = 0;
                whiteRook1.column = 0;
            } else if (whiteMove) {
                blackKnight1.row = 0;
                blackKnight1.column = 0;
            }

        } else if ((whiteRook1.row == blackKnight2.row && whiteRook1.column == blackKnight2.column)) {
            if (blackMove) {
                whiteRook1.row = 0;
                whiteRook1.column = 0;
            } else if (whiteMove) {
                blackKnight2.row = 0;
                blackKnight2.column = 0;
            }

        } else if ((whiteRook1.row == blackQueen.row && whiteRook1.column == blackQueen.column)) {
            if (blackMove) {
                whiteRook1.row = 0;
                whiteRook1.column = 0;
            } else if (whiteMove) {
                blackQueen.row = 0;
                blackQueen.column = 0;
            }

        } else if ((whiteRook1.row == blackKing.row && whiteRook1.column == blackKing.column)) {
            if (blackMove) {
                whiteRook1.row = 0;
                whiteRook1.column = 0;
            } else if (whiteMove) {
                blackKing.row = 0;
                blackKing.column = 0;
            }

        }

        //kolizja whiteRook2
        if ((whiteRook2.row == blackPawn1.row && whiteRook2.column == blackPawn1.column)) {
            if (blackMove) {
                whiteRook2.row = 0;
                whiteRook2.column = 0;
            } else if (whiteMove) {
                blackPawn1.row = 0;
                blackPawn1.column = 0;
            }

        } else if ((whiteRook2.row == blackPawn2.row && whiteRook2.column == blackPawn2.column)) {
            if (blackMove) {
                whiteRook2.row = 0;
                whiteRook2.column = 0;
            } else if (whiteMove) {
                blackPawn2.row = 0;
                blackPawn2.column = 0;
            }

        } else if ((whiteRook2.row == blackPawn3.row && whiteRook2.column == blackPawn3.column)) {
            if (blackMove) {
                whiteRook2.row = 0;
                whiteRook2.column = 0;
            } else if (whiteMove) {
                blackPawn3.row = 0;
                blackPawn3.column = 0;
            }

        } else if ((whiteRook2.row == blackPawn4.row && whiteRook2.column == blackPawn4.column)) {
            if (blackMove) {
                whiteRook2.row = 0;
                whiteRook2.column = 0;
            } else if (whiteMove) {
                blackPawn4.row = 0;
                blackPawn4.column = 0;
            }

        } else if ((whiteRook2.row == blackPawn5.row && whiteRook2.column == blackPawn5.column)) {
            if (blackMove) {
                whiteRook2.row = 0;
                whiteRook2.column = 0;
            } else if (whiteMove) {
                blackPawn5.row = 0;
                blackPawn5.column = 0;
            }

        } else if ((whiteRook2.row == blackPawn6.row && whiteRook2.column == blackPawn6.column)) {
            if (blackMove) {
                whiteRook2.row = 0;
                whiteRook2.column = 0;
            } else if (whiteMove) {
                blackPawn6.row = 0;
                blackPawn6.column = 0;
            }

        } else if ((whiteRook2.row == blackPawn7.row && whiteRook2.column == blackPawn7.column)) {
            if (blackMove) {
                whiteRook2.row = 0;
                whiteRook2.column = 0;
            } else if (whiteMove) {
                blackPawn7.row = 0;
                blackPawn7.column = 0;
            }

        } else if ((whiteRook2.row == blackPawn8.row && whiteRook2.column == blackPawn8.column)) {
            if (blackMove) {
                whiteRook2.row = 0;
                whiteRook2.column = 0;
            } else if (whiteMove) {
                blackPawn8.row = 0;
                blackPawn8.column = 0;
            }

        } else if ((whiteRook2.row == blackBishop1.row && whiteRook2.column == blackBishop1.column)) {
            if (blackMove) {
                whiteRook2.row = 0;
                whiteRook2.column = 0;
            } else if (whiteMove) {
                blackBishop1.row = 0;
                blackBishop1.column = 0;
            }

        } else if ((whiteRook2.row == blackBishop2.row && whiteRook2.column == blackBishop2.column)) {
            if (blackMove) {
                whiteRook2.row = 0;
                whiteRook2.column = 0;
            } else if (whiteMove) {
                blackBishop2.row = 0;
                blackBishop2.column = 0;
            }

        } else if ((whiteRook2.row == blackRook1.row && whiteRook2.column == blackRook1.column)) {
            if (blackMove) {
                whiteRook2.row = 0;
                whiteRook2.column = 0;
            } else if (whiteMove) {
                blackRook1.row = 0;
                blackRook1.column = 0;
            }

        } else if ((whiteRook2.row == blackRook2.row && whiteRook2.column == blackRook2.column)) {
            if (blackMove) {
                whiteRook2.row = 0;
                whiteRook2.column = 0;
            } else if (whiteMove) {
                blackRook2.row = 0;
                blackRook2.column = 0;
            }

        } else if ((whiteRook2.row == blackKnight1.row && whiteRook2.column == blackKnight1.column)) {
            if (blackMove) {
                whiteRook2.row = 0;
                whiteRook2.column = 0;
            } else if (whiteMove) {
                blackKnight1.row = 0;
                blackKnight1.column = 0;
            }

        } else if ((whiteRook2.row == blackKnight2.row && whiteRook2.column == blackKnight2.column)) {
            if (blackMove) {
                whiteRook2.row = 0;
                whiteRook2.column = 0;
            } else if (whiteMove) {
                blackKnight2.row = 0;
                blackKnight2.column = 0;
            }

        } else if ((whiteRook2.row == blackQueen.row && whiteRook2.column == blackQueen.column)) {
            if (blackMove) {
                whiteRook2.row = 0;
                whiteRook2.column = 0;
            } else if (whiteMove) {
                blackQueen.row = 0;
                blackQueen.column = 0;
            }

        } else if ((whiteRook2.row == blackKing.row && whiteRook2.column == blackKing.column)) {
            if (blackMove) {
                whiteRook2.row = 0;
                whiteRook2.column = 0;
            } else if (whiteMove) {
                blackKing.row = 0;
                blackKing.column = 0;
            }

        }

        //kolizja whiteBishop1
        if ((whiteBishop1.row == blackPawn1.row && whiteBishop1.column == blackPawn1.column)) {
            if (blackMove) {
                whiteBishop1.row = 0;
                whiteBishop1.column = 0;
            } else if (whiteMove) {
                blackPawn1.row = 0;
                blackPawn1.column = 0;
            }

        } else if ((whiteBishop1.row == blackPawn2.row && whiteBishop1.column == blackPawn2.column)) {
            if (blackMove) {
                whiteBishop1.row = 0;
                whiteBishop1.column = 0;
            } else if (whiteMove) {
                blackPawn2.row = 0;
                blackPawn2.column = 0;
            }

        } else if ((whiteBishop1.row == blackPawn3.row && whiteBishop1.column == blackPawn3.column)) {
            if (blackMove) {
                whiteBishop1.row = 0;
                whiteBishop1.column = 0;
            } else if (whiteMove) {
                blackPawn3.row = 0;
                blackPawn3.column = 0;
            }

        } else if ((whiteBishop1.row == blackPawn4.row && whiteBishop1.column == blackPawn4.column)) {
            if (blackMove) {
                whiteBishop1.row = 0;
                whiteBishop1.column = 0;
            } else if (whiteMove) {
                blackPawn4.row = 0;
                blackPawn4.column = 0;
            }

        } else if ((whiteBishop1.row == blackPawn5.row && whiteBishop1.column == blackPawn5.column)) {
            if (blackMove) {
                whiteBishop1.row = 0;
                whiteBishop1.column = 0;
            } else if (whiteMove) {
                blackPawn5.row = 0;
                blackPawn5.column = 0;
            }

        } else if ((whiteBishop1.row == blackPawn6.row && whiteBishop1.column == blackPawn6.column)) {
            if (blackMove) {
                whiteBishop1.row = 0;
                whiteBishop1.column = 0;
            } else if (whiteMove) {
                blackPawn6.row = 0;
                blackPawn6.column = 0;
            }

        } else if ((whiteBishop1.row == blackPawn7.row && whiteBishop1.column == blackPawn7.column)) {
            if (blackMove) {
                whiteBishop1.row = 0;
                whiteBishop1.column = 0;
            } else if (whiteMove) {
                blackPawn7.row = 0;
                blackPawn7.column = 0;
            }

        } else if ((whiteBishop1.row == blackPawn8.row && whiteBishop1.column == blackPawn8.column)) {
            if (blackMove) {
                whiteBishop1.row = 0;
                whiteBishop1.column = 0;
            } else if (whiteMove) {
                blackPawn8.row = 0;
                blackPawn8.column = 0;
            }

        } else if ((whiteBishop1.row == blackBishop1.row && whiteBishop1.column == blackBishop1.column)) {
            if (blackMove) {
                whiteBishop1.row = 0;
                whiteBishop1.column = 0;
            } else if (whiteMove) {
                blackBishop1.row = 0;
                blackBishop1.column = 0;
            }

        } else if ((whiteBishop1.row == blackBishop2.row && whiteBishop1.column == blackBishop2.column)) {
            if (blackMove) {
                whiteBishop1.row = 0;
                whiteBishop1.column = 0;
            } else if (whiteMove) {
                blackBishop2.row = 0;
                blackBishop2.column = 0;
            }

        } else if ((whiteBishop1.row == blackRook1.row && whiteBishop1.column == blackRook1.column)) {
            if (blackMove) {
                whiteBishop1.row = 0;
                whiteBishop1.column = 0;
            } else if (whiteMove) {
                blackRook1.row = 0;
                blackRook1.column = 0;
            }

        } else if ((whiteBishop1.row == blackRook2.row && whiteBishop1.column == blackRook2.column)) {
            if (blackMove) {
                whiteBishop1.row = 0;
                whiteBishop1.column = 0;
            } else if (whiteMove) {
                blackRook2.row = 0;
                blackRook2.column = 0;
            }

        } else if ((whiteBishop1.row == blackKnight1.row && whiteBishop1.column == blackKnight1.column)) {
            if (blackMove) {
                whiteBishop1.row = 0;
                whiteBishop1.column = 0;
            } else if (whiteMove) {
                blackKnight1.row = 0;
                blackKnight1.column = 0;
            }

        } else if ((whiteBishop1.row == blackKnight2.row && whiteBishop1.column == blackKnight2.column)) {
            if (blackMove) {
                whiteBishop1.row = 0;
                whiteBishop1.column = 0;
            } else if (whiteMove) {
                blackKnight2.row = 0;
                blackKnight2.column = 0;
            }

        } else if ((whiteBishop1.row == blackQueen.row && whiteBishop1.column == blackQueen.column)) {
            if (blackMove) {
                whiteBishop1.row = 0;
                whiteBishop1.column = 0;
            } else if (whiteMove) {
                blackQueen.row = 0;
                blackQueen.column = 0;
            }

        } else if ((whiteBishop1.row == blackKing.row && whiteBishop1.column == blackKing.column)) {
            if (blackMove) {
                whiteBishop1.row = 0;
                whiteBishop1.column = 0;
            } else if (whiteMove) {
                blackKing.row = 0;
                blackKing.column = 0;
            }

        }


        //kolizja whiteBishop2
        if ((whiteBishop2.row == blackPawn1.row && whiteBishop2.column == blackPawn1.column)) {
            if (blackMove) {
                whiteBishop2.row = 0;
                whiteBishop2.column = 0;
            } else if (whiteMove) {
                blackPawn1.row = 0;
                blackPawn1.column = 0;
            }

        } else if ((whiteBishop2.row == blackPawn2.row && whiteBishop2.column == blackPawn2.column)) {
            if (blackMove) {
                whiteBishop2.row = 0;
                whiteBishop2.column = 0;
            } else if (whiteMove) {
                blackPawn2.row = 0;
                blackPawn2.column = 0;
            }

        } else if ((whiteBishop2.row == blackPawn3.row && whiteBishop2.column == blackPawn3.column)) {
            if (blackMove) {
                whiteBishop2.row = 0;
                whiteBishop2.column = 0;
            } else if (whiteMove) {
                blackPawn3.row = 0;
                blackPawn3.column = 0;
            }

        } else if ((whiteBishop2.row == blackPawn4.row && whiteBishop2.column == blackPawn4.column)) {
            if (blackMove) {
                whiteBishop2.row = 0;
                whiteBishop2.column = 0;
            } else if (whiteMove) {
                blackPawn4.row = 0;
                blackPawn4.column = 0;
            }

        } else if ((whiteBishop2.row == blackPawn5.row && whiteBishop2.column == blackPawn5.column)) {
            if (blackMove) {
                whiteBishop2.row = 0;
                whiteBishop2.column = 0;
            } else if (whiteMove) {
                blackPawn5.row = 0;
                blackPawn5.column = 0;
            }

        } else if ((whiteBishop2.row == blackPawn6.row && whiteBishop2.column == blackPawn6.column)) {
            if (blackMove) {
                whiteBishop2.row = 0;
                whiteBishop2.column = 0;
            } else if (whiteMove) {
                blackPawn6.row = 0;
                blackPawn6.column = 0;
            }

        } else if ((whiteBishop2.row == blackPawn7.row && whiteBishop2.column == blackPawn7.column)) {
            if (blackMove) {
                whiteBishop2.row = 0;
                whiteBishop2.column = 0;
            } else if (whiteMove) {
                blackPawn7.row = 0;
                blackPawn7.column = 0;
            }

        } else if ((whiteBishop2.row == blackPawn8.row && whiteBishop2.column == blackPawn8.column)) {
            if (blackMove) {
                whiteBishop2.row = 0;
                whiteBishop2.column = 0;
            } else if (whiteMove) {
                blackPawn8.row = 0;
                blackPawn8.column = 0;
            }

        } else if ((whiteBishop2.row == blackBishop1.row && whiteBishop2.column == blackBishop1.column)) {
            if (blackMove) {
                whiteBishop2.row = 0;
                whiteBishop2.column = 0;
            } else if (whiteMove) {
                blackBishop1.row = 0;
                blackBishop1.column = 0;
            }

        } else if ((whiteBishop2.row == blackBishop2.row && whiteBishop2.column == blackBishop2.column)) {
            if (blackMove) {
                whiteBishop2.row = 0;
                whiteBishop2.column = 0;
            } else if (whiteMove) {
                blackBishop2.row = 0;
                blackBishop2.column = 0;
            }

        } else if ((whiteBishop2.row == blackRook1.row && whiteBishop2.column == blackRook1.column)) {
            if (blackMove) {
                whiteBishop2.row = 0;
                whiteBishop2.column = 0;
            } else if (whiteMove) {
                blackRook1.row = 0;
                blackRook1.column = 0;
            }

        } else if ((whiteBishop2.row == blackRook2.row && whiteBishop2.column == blackRook2.column)) {
            if (blackMove) {
                whiteBishop2.row = 0;
                whiteBishop2.column = 0;
            } else if (whiteMove) {
                blackRook2.row = 0;
                blackRook2.column = 0;
            }

        } else if ((whiteBishop2.row == blackKnight1.row && whiteBishop2.column == blackKnight1.column)) {
            if (blackMove) {
                whiteBishop2.row = 0;
                whiteBishop2.column = 0;
            } else if (whiteMove) {
                blackKnight1.row = 0;
                blackKnight1.column = 0;
            }

        } else if ((whiteBishop2.row == blackKnight2.row && whiteBishop2.column == blackKnight2.column)) {
            if (blackMove) {
                whiteBishop2.row = 0;
                whiteBishop2.column = 0;
            } else if (whiteMove) {
                blackKnight2.row = 0;
                blackKnight2.column = 0;
            }

        } else if ((whiteBishop2.row == blackQueen.row && whiteBishop2.column == blackQueen.column)) {
            if (blackMove) {
                whiteBishop2.row = 0;
                whiteBishop2.column = 0;
            } else if (whiteMove) {
                blackQueen.row = 0;
                blackQueen.column = 0;
            }

        } else if ((whiteBishop2.row == blackKing.row && whiteBishop2.column == blackKing.column)) {
            if (blackMove) {
                whiteBishop2.row = 0;
                whiteBishop2.column = 0;
            } else if (whiteMove) {
                blackKing.row = 0;
                blackKing.column = 0;
            }

        }


        //kolizja whiteKnight1
        if ((whiteKnight1.row == blackPawn1.row && whiteKnight1.column == blackPawn1.column)) {
            if (blackMove) {
                whiteKnight1.row = 0;
                whiteKnight1.column = 0;
            } else if (whiteMove) {
                blackPawn1.row = 0;
                blackPawn1.column = 0;
            }

        } else if ((whiteKnight1.row == blackPawn2.row && whiteKnight1.column == blackPawn2.column)) {
            if (blackMove) {
                whiteKnight1.row = 0;
                whiteKnight1.column = 0;
            } else if (whiteMove) {
                blackPawn2.row = 0;
                blackPawn2.column = 0;
            }

        } else if ((whiteKnight1.row == blackPawn3.row && whiteKnight1.column == blackPawn3.column)) {
            if (blackMove) {
                whiteKnight1.row = 0;
                whiteKnight1.column = 0;
            } else if (whiteMove) {
                blackPawn3.row = 0;
                blackPawn3.column = 0;
            }

        } else if ((whiteKnight1.row == blackPawn4.row && whiteKnight1.column == blackPawn4.column)) {
            if (blackMove) {
                whiteKnight1.row = 0;
                whiteKnight1.column = 0;
            } else if (whiteMove) {
                blackPawn4.row = 0;
                blackPawn4.column = 0;
            }

        } else if ((whiteKnight1.row == blackPawn5.row && whiteKnight1.column == blackPawn5.column)) {
            if (blackMove) {
                whiteKnight1.row = 0;
                whiteKnight1.column = 0;
            } else if (whiteMove) {
                blackPawn5.row = 0;
                blackPawn5.column = 0;
            }

        } else if ((whiteKnight1.row == blackPawn6.row && whiteKnight1.column == blackPawn6.column)) {
            if (blackMove) {
                whiteKnight1.row = 0;
                whiteKnight1.column = 0;
            } else if (whiteMove) {
                blackPawn6.row = 0;
                blackPawn6.column = 0;
            }

        } else if ((whiteKnight1.row == blackPawn7.row && whiteKnight1.column == blackPawn7.column)) {
            if (blackMove) {
                whiteKnight1.row = 0;
                whiteKnight1.column = 0;
            } else if (whiteMove) {
                blackPawn7.row = 0;
                blackPawn7.column = 0;
            }

        } else if ((whiteKnight1.row == blackPawn8.row && whiteKnight1.column == blackPawn8.column)) {
            if (blackMove) {
                whiteKnight1.row = 0;
                whiteKnight1.column = 0;
            } else if (whiteMove) {
                blackPawn8.row = 0;
                blackPawn8.column = 0;
            }

        } else if ((whiteKnight1.row == blackBishop1.row && whiteKnight1.column == blackBishop1.column)) {
            if (blackMove) {
                whiteKnight1.row = 0;
                whiteKnight1.column = 0;
            } else if (whiteMove) {
                blackBishop1.row = 0;
                blackBishop1.column = 0;
            }

        } else if ((whiteKnight1.row == blackBishop2.row && whiteKnight1.column == blackBishop2.column)) {
            if (blackMove) {
                whiteKnight1.row = 0;
                whiteKnight1.column = 0;
            } else if (whiteMove) {
                blackBishop2.row = 0;
                blackBishop2.column = 0;
            }

        } else if ((whiteKnight1.row == blackRook1.row && whiteKnight1.column == blackRook1.column)) {
            if (blackMove) {
                whiteKnight1.row = 0;
                whiteKnight1.column = 0;
            } else if (whiteMove) {
                blackRook1.row = 0;
                blackRook1.column = 0;
            }

        } else if ((whiteKnight1.row == blackRook2.row && whiteKnight1.column == blackRook2.column)) {
            if (blackMove) {
                whiteKnight1.row = 0;
                whiteKnight1.column = 0;
            } else if (whiteMove) {
                blackRook2.row = 0;
                blackRook2.column = 0;
            }

        } else if ((whiteKnight1.row == blackKnight1.row && whiteKnight1.column == blackKnight1.column)) {
            if (blackMove) {
                whiteKnight1.row = 0;
                whiteKnight1.column = 0;
            } else if (whiteMove) {
                blackKnight1.row = 0;
                blackKnight1.column = 0;
            }

        } else if ((whiteKnight1.row == blackKnight2.row && whiteKnight1.column == blackKnight2.column)) {
            if (blackMove) {
                whiteKnight1.row = 0;
                whiteKnight1.column = 0;
            } else if (whiteMove) {
                blackKnight2.row = 0;
                blackKnight2.column = 0;
            }

        } else if ((whiteKnight1.row == blackQueen.row && whiteKnight1.column == blackQueen.column)) {
            if (blackMove) {
                whiteKnight1.row = 0;
                whiteKnight1.column = 0;
            } else if (whiteMove) {
                blackQueen.row = 0;
                blackQueen.column = 0;
            }

        } else if ((whiteKnight1.row == blackKing.row && whiteKnight1.column == blackKing.column)) {
            if (blackMove) {
                whiteKnight1.row = 0;
                whiteKnight1.column = 0;
            } else if (whiteMove) {
                blackKing.row = 0;
                blackKing.column = 0;
            }

        }


        //kolizja whiteKnight2
        if ((whiteKnight2.row == blackPawn1.row && whiteKnight2.column == blackPawn1.column)) {
            if (blackMove) {
                whiteKnight2.row = 0;
                whiteKnight2.column = 0;
            } else if (whiteMove) {
                blackPawn1.row = 0;
                blackPawn1.column = 0;
            }

        } else if ((whiteKnight2.row == blackPawn2.row && whiteKnight2.column == blackPawn2.column)) {
            if (blackMove) {
                whiteKnight2.row = 0;
                whiteKnight2.column = 0;
            } else if (whiteMove) {
                blackPawn2.row = 0;
                blackPawn2.column = 0;
            }

        } else if ((whiteKnight2.row == blackPawn3.row && whiteKnight2.column == blackPawn3.column)) {
            if (blackMove) {
                whiteKnight2.row = 0;
                whiteKnight2.column = 0;
            } else if (whiteMove) {
                blackPawn3.row = 0;
                blackPawn3.column = 0;
            }

        } else if ((whiteKnight2.row == blackPawn4.row && whiteKnight2.column == blackPawn4.column)) {
            if (blackMove) {
                whiteKnight2.row = 0;
                whiteKnight2.column = 0;
            } else if (whiteMove) {
                blackPawn4.row = 0;
                blackPawn4.column = 0;
            }

        } else if ((whiteKnight2.row == blackPawn5.row && whiteKnight2.column == blackPawn5.column)) {
            if (blackMove) {
                whiteKnight2.row = 0;
                whiteKnight2.column = 0;
            } else if (whiteMove) {
                blackPawn5.row = 0;
                blackPawn5.column = 0;
            }

        } else if ((whiteKnight2.row == blackPawn6.row && whiteKnight2.column == blackPawn6.column)) {
            if (blackMove) {
                whiteKnight2.row = 0;
                whiteKnight2.column = 0;
            } else if (whiteMove) {
                blackPawn6.row = 0;
                blackPawn6.column = 0;
            }

        } else if ((whiteKnight2.row == blackPawn7.row && whiteKnight2.column == blackPawn7.column)) {
            if (blackMove) {
                whiteKnight2.row = 0;
                whiteKnight2.column = 0;
            } else if (whiteMove) {
                blackPawn7.row = 0;
                blackPawn7.column = 0;
            }

        } else if ((whiteKnight2.row == blackPawn8.row && whiteKnight2.column == blackPawn8.column)) {
            if (blackMove) {
                whiteKnight2.row = 0;
                whiteKnight2.column = 0;
            } else if (whiteMove) {
                blackPawn8.row = 0;
                blackPawn8.column = 0;
            }

        } else if ((whiteKnight2.row == blackBishop1.row && whiteKnight2.column == blackBishop1.column)) {
            if (blackMove) {
                whiteKnight2.row = 0;
                whiteKnight2.column = 0;
            } else if (whiteMove) {
                blackBishop1.row = 0;
                blackBishop1.column = 0;
            }

        } else if ((whiteKnight2.row == blackBishop2.row && whiteKnight2.column == blackBishop2.column)) {
            if (blackMove) {
                whiteKnight2.row = 0;
                whiteKnight2.column = 0;
            } else if (whiteMove) {
                blackBishop2.row = 0;
                blackBishop2.column = 0;
            }

        } else if ((whiteKnight2.row == blackRook1.row && whiteKnight2.column == blackRook1.column)) {
            if (blackMove) {
                whiteKnight2.row = 0;
                whiteKnight2.column = 0;
            } else if (whiteMove) {
                blackRook1.row = 0;
                blackRook1.column = 0;
            }

        } else if ((whiteKnight2.row == blackRook2.row && whiteKnight2.column == blackRook2.column)) {
            if (blackMove) {
                whiteKnight2.row = 0;
                whiteKnight2.column = 0;
            } else if (whiteMove) {
                blackRook2.row = 0;
                blackRook2.column = 0;
            }

        } else if ((whiteKnight2.row == blackKnight1.row && whiteKnight2.column == blackKnight1.column)) {
            if (blackMove) {
                whiteKnight2.row = 0;
                whiteKnight2.column = 0;
            } else if (whiteMove) {
                blackKnight1.row = 0;
                blackKnight1.column = 0;
            }

        } else if ((whiteKnight2.row == blackKnight2.row && whiteKnight2.column == blackKnight2.column)) {
            if (blackMove) {
                whiteKnight2.row = 0;
                whiteKnight2.column = 0;
            } else if (whiteMove) {
                blackKnight2.row = 0;
                blackKnight2.column = 0;
            }

        } else if ((whiteKnight2.row == blackQueen.row && whiteKnight2.column == blackQueen.column)) {
            if (blackMove) {
                whiteKnight2.row = 0;
                whiteKnight2.column = 0;
            } else if (whiteMove) {
                blackQueen.row = 0;
                blackQueen.column = 0;
            }

        } else if ((whiteKnight2.row == blackKing.row && whiteKnight2.column == blackKing.column)) {
            if (blackMove) {
                whiteKnight2.row = 0;
                whiteKnight2.column = 0;
            } else if (whiteMove) {
                blackKing.row = 0;
                blackKing.column = 0;
            }

        }

        //kolizja whiteQueen
        if ((whiteQueen.row == blackPawn1.row && whiteQueen.column == blackPawn1.column)) {
            if (blackMove) {
                whiteQueen.row = 0;
                whiteQueen.column = 0;
            } else if (whiteMove) {
                blackPawn1.row = 0;
                blackPawn1.column = 0;
            }

        } else if ((whiteQueen.row == blackPawn2.row && whiteQueen.column == blackPawn2.column)) {
            if (blackMove) {
                whiteQueen.row = 0;
                whiteQueen.column = 0;
            } else if (whiteMove) {
                blackPawn2.row = 0;
                blackPawn2.column = 0;
            }

        } else if ((whiteQueen.row == blackPawn3.row && whiteQueen.column == blackPawn3.column)) {
            if (blackMove) {
                whiteQueen.row = 0;
                whiteQueen.column = 0;
            } else if (whiteMove) {
                blackPawn3.row = 0;
                blackPawn3.column = 0;
            }

        } else if ((whiteQueen.row == blackPawn4.row && whiteQueen.column == blackPawn4.column)) {
            if (blackMove) {
                whiteQueen.row = 0;
                whiteQueen.column = 0;
            } else if (whiteMove) {
                blackPawn4.row = 0;
                blackPawn4.column = 0;
            }

        } else if ((whiteQueen.row == blackPawn5.row && whiteQueen.column == blackPawn5.column)) {
            if (blackMove) {
                whiteQueen.row = 0;
                whiteQueen.column = 0;
            } else if (whiteMove) {
                blackPawn5.row = 0;
                blackPawn5.column = 0;
            }

        } else if ((whiteQueen.row == blackPawn6.row && whiteQueen.column == blackPawn6.column)) {
            if (blackMove) {
                whiteQueen.row = 0;
                whiteQueen.column = 0;
            } else if (whiteMove) {
                blackPawn6.row = 0;
                blackPawn6.column = 0;
            }

        } else if ((whiteQueen.row == blackPawn7.row && whiteQueen.column == blackPawn7.column)) {
            if (blackMove) {
                whiteQueen.row = 0;
                whiteQueen.column = 0;
            } else if (whiteMove) {
                blackPawn7.row = 0;
                blackPawn7.column = 0;
            }

        } else if ((whiteQueen.row == blackPawn8.row && whiteQueen.column == blackPawn8.column)) {
            if (blackMove) {
                whiteQueen.row = 0;
                whiteQueen.column = 0;
            } else if (whiteMove) {
                blackPawn8.row = 0;
                blackPawn8.column = 0;
            }

        } else if ((whiteQueen.row == blackBishop1.row && whiteQueen.column == blackBishop1.column)) {
            if (blackMove) {
                whiteQueen.row = 0;
                whiteQueen.column = 0;
            } else if (whiteMove) {
                blackBishop1.row = 0;
                blackBishop1.column = 0;
            }

        } else if ((whiteQueen.row == blackBishop2.row && whiteQueen.column == blackBishop2.column)) {
            if (blackMove) {
                whiteQueen.row = 0;
                whiteQueen.column = 0;
            } else if (whiteMove) {
                blackBishop2.row = 0;
                blackBishop2.column = 0;
            }

        } else if ((whiteQueen.row == blackRook1.row && whiteQueen.column == blackRook1.column)) {
            if (blackMove) {
                whiteQueen.row = 0;
                whiteQueen.column = 0;
            } else if (whiteMove) {
                blackRook1.row = 0;
                blackRook1.column = 0;
            }

        } else if ((whiteQueen.row == blackRook2.row && whiteQueen.column == blackRook2.column)) {
            if (blackMove) {
                whiteQueen.row = 0;
                whiteQueen.column = 0;
            } else if (whiteMove) {
                blackRook2.row = 0;
                blackRook2.column = 0;
            }

        } else if ((whiteQueen.row == blackKnight1.row && whiteQueen.column == blackKnight1.column)) {
            if (blackMove) {
                whiteQueen.row = 0;
                whiteQueen.column = 0;
            } else if (whiteMove) {
                blackKnight1.row = 0;
                blackKnight1.column = 0;
            }

        } else if ((whiteQueen.row == blackKnight2.row && whiteQueen.column == blackKnight2.column)) {
            if (blackMove) {
                whiteQueen.row = 0;
                whiteQueen.column = 0;
            } else if (whiteMove) {
                blackKnight2.row = 0;
                blackKnight2.column = 0;
            }

        } else if ((whiteQueen.row == blackQueen.row && whiteQueen.column == blackQueen.column)) {
            if (blackMove) {
                whiteQueen.row = 0;
                whiteQueen.column = 0;
            } else if (whiteMove) {
                blackQueen.row = 0;
                blackQueen.column = 0;
            }

        } else if ((whiteQueen.row == blackKing.row && whiteQueen.column == blackKing.column)) {
            if (blackMove) {
                whiteQueen.row = 0;
                whiteQueen.column = 0;
            } else if (whiteMove) {
                blackKing.row = 0;
                blackKing.column = 0;
            }

        }


        //kolizja whiteKing
        if ((whiteKing.row == blackPawn1.row && whiteKing.column == blackPawn1.column)) {
            if (blackMove) {
                whiteKing.row = 0;
                whiteKing.column = 0;
            } else if (whiteMove) {
                blackPawn1.row = 0;
                blackPawn1.column = 0;
            }

        } else if ((whiteKing.row == blackPawn2.row && whiteKing.column == blackPawn2.column)) {
            if (blackMove) {
                whiteKing.row = 0;
                whiteKing.column = 0;
            } else if (whiteMove) {
                blackPawn2.row = 0;
                blackPawn2.column = 0;
            }

        } else if ((whiteKing.row == blackPawn3.row && whiteKing.column == blackPawn3.column)) {
            if (blackMove) {
                whiteKing.row = 0;
                whiteKing.column = 0;
            } else if (whiteMove) {
                blackPawn3.row = 0;
                blackPawn3.column = 0;
            }

        } else if ((whiteKing.row == blackPawn4.row && whiteKing.column == blackPawn4.column)) {
            if (blackMove) {
                whiteKing.row = 0;
                whiteKing.column = 0;
            } else if (whiteMove) {
                blackPawn4.row = 0;
                blackPawn4.column = 0;
            }

        } else if ((whiteKing.row == blackPawn5.row && whiteKing.column == blackPawn5.column)) {
            if (blackMove) {
                whiteKing.row = 0;
                whiteKing.column = 0;
            } else if (whiteMove) {
                blackPawn5.row = 0;
                blackPawn5.column = 0;
            }

        } else if ((whiteKing.row == blackPawn6.row && whiteKing.column == blackPawn6.column)) {
            if (blackMove) {
                whiteKing.row = 0;
                whiteKing.column = 0;
            } else if (whiteMove) {
                blackPawn6.row = 0;
                blackPawn6.column = 0;
            }

        } else if ((whiteKing.row == blackPawn7.row && whiteKing.column == blackPawn7.column)) {
            if (blackMove) {
                whiteKing.row = 0;
                whiteKing.column = 0;
            } else if (whiteMove) {
                blackPawn7.row = 0;
                blackPawn7.column = 0;
            }

        } else if ((whiteKing.row == blackPawn8.row && whiteKing.column == blackPawn8.column)) {
            if (blackMove) {
                whiteKing.row = 0;
                whiteKing.column = 0;
            } else if (whiteMove) {
                blackPawn8.row = 0;
                blackPawn8.column = 0;
            }

        } else if ((whiteKing.row == blackBishop1.row && whiteKing.column == blackBishop1.column)) {
            if (blackMove) {
                whiteKing.row = 0;
                whiteKing.column = 0;
            } else if (whiteMove) {
                blackBishop1.row = 0;
                blackBishop1.column = 0;
            }

        } else if ((whiteKing.row == blackBishop2.row && whiteKing.column == blackBishop2.column)) {
            if (blackMove) {
                whiteKing.row = 0;
                whiteKing.column = 0;
            } else if (whiteMove) {
                blackBishop2.row = 0;
                blackBishop2.column = 0;
            }

        } else if ((whiteKing.row == blackRook1.row && whiteKing.column == blackRook1.column)) {
            if (blackMove) {
                whiteKing.row = 0;
                whiteKing.column = 0;
            } else if (whiteMove) {
                blackRook1.row = 0;
                blackRook1.column = 0;
            }

        } else if ((whiteKing.row == blackRook2.row && whiteKing.column == blackRook2.column)) {
            if (blackMove) {
                whiteKing.row = 0;
                whiteKing.column = 0;
            } else if (whiteMove) {
                blackRook2.row = 0;
                blackRook2.column = 0;
            }

        } else if ((whiteKing.row == blackKnight1.row && whiteKing.column == blackKnight1.column)) {
            if (blackMove) {
                whiteKing.row = 0;
                whiteKing.column = 0;
            } else if (whiteMove) {
                blackKnight1.row = 0;
                blackKnight1.column = 0;
            }

        } else if ((whiteKing.row == blackKnight2.row && whiteKing.column == blackKnight2.column)) {
            if (blackMove) {
                whiteKing.row = 0;
                whiteKing.column = 0;
            } else if (whiteMove) {
                blackKnight2.row = 0;
                blackKnight2.column = 0;
            }

        } else if ((whiteKing.row == blackQueen.row && whiteKing.column == blackQueen.column)) {
            if (blackMove) {
                whiteKing.row = 0;
                whiteKing.column = 0;
            } else if (whiteMove) {
                blackQueen.row = 0;
                blackQueen.column = 0;
            }

        } else if ((whiteKing.row == blackKing.row && whiteKing.column == blackKing.column)) {
            if (blackMove) {
                whiteKing.row = 0;
                whiteKing.column = 0;
            } else if (whiteMove) {
                blackKing.row = 0;
                blackKing.column = 0;
            }

        }

        //kolizja whitePawn1
        if ((whitePawn1.row == blackPawn1.row && whitePawn1.column == blackPawn1.column)) {
            if (blackMove) {
                whitePawn1.row = 0;
                whitePawn1.column = 0;
            } else if (whiteMove) {
                blackPawn1.row = 0;
                blackPawn1.column = 0;
            }

        } else if ((whitePawn1.row == blackPawn2.row && whitePawn1.column == blackPawn2.column)) {
            if (blackMove) {
                whitePawn1.row = 0;
                whitePawn1.column = 0;
            } else if (whiteMove) {
                blackPawn2.row = 0;
                blackPawn2.column = 0;
            }

        } else if ((whitePawn1.row == blackPawn3.row && whitePawn1.column == blackPawn3.column)) {
            if (blackMove) {
                whitePawn1.row = 0;
                whitePawn1.column = 0;
            } else if (whiteMove) {
                blackPawn3.row = 0;
                blackPawn3.column = 0;
            }

        } else if ((whitePawn1.row == blackPawn4.row && whitePawn1.column == blackPawn4.column)) {
            if (blackMove) {
                whitePawn1.row = 0;
                whitePawn1.column = 0;
            } else if (whiteMove) {
                blackPawn4.row = 0;
                blackPawn4.column = 0;
            }

        } else if ((whitePawn1.row == blackPawn5.row && whitePawn1.column == blackPawn5.column)) {
            if (blackMove) {
                whitePawn1.row = 0;
                whitePawn1.column = 0;
            } else if (whiteMove) {
                blackPawn5.row = 0;
                blackPawn5.column = 0;
            }

        } else if ((whitePawn1.row == blackPawn6.row && whitePawn1.column == blackPawn6.column)) {
            if (blackMove) {
                whitePawn1.row = 0;
                whitePawn1.column = 0;
            } else if (whiteMove) {
                blackPawn6.row = 0;
                blackPawn6.column = 0;
            }

        } else if ((whitePawn1.row == blackPawn7.row && whitePawn1.column == blackPawn7.column)) {
            if (blackMove) {
                whitePawn1.row = 0;
                whitePawn1.column = 0;
            } else if (whiteMove) {
                blackPawn7.row = 0;
                blackPawn7.column = 0;
            }

        } else if ((whitePawn1.row == blackPawn8.row && whitePawn1.column == blackPawn8.column)) {
            if (blackMove) {
                whitePawn1.row = 0;
                whitePawn1.column = 0;
            } else if (whiteMove) {
                blackPawn8.row = 0;
                blackPawn8.column = 0;
            }

        } else if ((whitePawn1.row == blackBishop1.row && whitePawn1.column == blackBishop1.column)) {
            if (blackMove) {
                whitePawn1.row = 0;
                whitePawn1.column = 0;
            } else if (whiteMove) {
                blackBishop1.row = 0;
                blackBishop1.column = 0;
            }

        } else if ((whitePawn1.row == blackBishop2.row && whitePawn1.column == blackBishop2.column)) {
            if (blackMove) {
                whitePawn1.row = 0;
                whitePawn1.column = 0;
            } else if (whiteMove) {
                blackBishop2.row = 0;
                blackBishop2.column = 0;
            }

        } else if ((whitePawn1.row == blackRook1.row && whitePawn1.column == blackRook1.column)) {
            if (blackMove) {
                whitePawn1.row = 0;
                whitePawn1.column = 0;
            } else if (whiteMove) {
                blackRook1.row = 0;
                blackRook1.column = 0;
            }

        } else if ((whitePawn1.row == blackRook2.row && whitePawn1.column == blackRook2.column)) {
            if (blackMove) {
                whitePawn1.row = 0;
                whitePawn1.column = 0;
            } else if (whiteMove) {
                blackRook2.row = 0;
                blackRook2.column = 0;
            }

        } else if ((whitePawn1.row == blackKnight1.row && whitePawn1.column == blackKnight1.column)) {
            if (blackMove) {
                whitePawn1.row = 0;
                whitePawn1.column = 0;
            } else if (whiteMove) {
                blackKnight1.row = 0;
                blackKnight1.column = 0;
            }

        } else if ((whitePawn1.row == blackKnight2.row && whitePawn1.column == blackKnight2.column)) {
            if (blackMove) {
                whitePawn1.row = 0;
                whitePawn1.column = 0;
            } else if (whiteMove) {
                blackKnight2.row = 0;
                blackKnight2.column = 0;
            }

        } else if ((whitePawn1.row == blackQueen.row && whitePawn1.column == blackQueen.column)) {
            if (blackMove) {
                whitePawn1.row = 0;
                whitePawn1.column = 0;
            } else if (whiteMove) {
                blackQueen.row = 0;
                blackQueen.column = 0;
            }

        } else if ((whitePawn1.row == blackKing.row && whitePawn1.column == blackKing.column)) {
            if (blackMove) {
                whitePawn1.row = 0;
                whitePawn1.column = 0;
            } else if (whiteMove) {
                blackKing.row = 0;
                blackKing.column = 0;
            }

        }


        //kolizja whitePawn2
        if ((whitePawn2.row == blackPawn1.row && whitePawn2.column == blackPawn1.column)) {
            if (blackMove) {
                whitePawn2.row = 0;
                whitePawn2.column = 0;
            } else if (whiteMove) {
                blackPawn1.row = 0;
                blackPawn1.column = 0;
            }

        } else if ((whitePawn2.row == blackPawn2.row && whitePawn2.column == blackPawn2.column)) {
            if (blackMove) {
                whitePawn2.row = 0;
                whitePawn2.column = 0;
            } else if (whiteMove) {
                blackPawn2.row = 0;
                blackPawn2.column = 0;
            }

        } else if ((whitePawn2.row == blackPawn3.row && whitePawn2.column == blackPawn3.column)) {
            if (blackMove) {
                whitePawn2.row = 0;
                whitePawn2.column = 0;
            } else if (whiteMove) {
                blackPawn3.row = 0;
                blackPawn3.column = 0;
            }

        } else if ((whitePawn2.row == blackPawn4.row && whitePawn2.column == blackPawn4.column)) {
            if (blackMove) {
                whitePawn2.row = 0;
                whitePawn2.column = 0;
            } else if (whiteMove) {
                blackPawn4.row = 0;
                blackPawn4.column = 0;
            }

        } else if ((whitePawn2.row == blackPawn5.row && whitePawn2.column == blackPawn5.column)) {
            if (blackMove) {
                whitePawn2.row = 0;
                whitePawn2.column = 0;
            } else if (whiteMove) {
                blackPawn5.row = 0;
                blackPawn5.column = 0;
            }

        } else if ((whitePawn2.row == blackPawn6.row && whitePawn2.column == blackPawn6.column)) {
            if (blackMove) {
                whitePawn2.row = 0;
                whitePawn2.column = 0;
            } else if (whiteMove) {
                blackPawn6.row = 0;
                blackPawn6.column = 0;
            }

        } else if ((whitePawn2.row == blackPawn7.row && whitePawn2.column == blackPawn7.column)) {
            if (blackMove) {
                whitePawn2.row = 0;
                whitePawn2.column = 0;
            } else if (whiteMove) {
                blackPawn7.row = 0;
                blackPawn7.column = 0;
            }

        } else if ((whitePawn2.row == blackPawn8.row && whitePawn2.column == blackPawn8.column)) {
            if (blackMove) {
                whitePawn2.row = 0;
                whitePawn2.column = 0;
            } else if (whiteMove) {
                blackPawn8.row = 0;
                blackPawn8.column = 0;
            }

        } else if ((whitePawn2.row == blackBishop1.row && whitePawn2.column == blackBishop1.column)) {
            if (blackMove) {
                whitePawn2.row = 0;
                whitePawn2.column = 0;
            } else if (whiteMove) {
                blackBishop1.row = 0;
                blackBishop1.column = 0;
            }

        } else if ((whitePawn2.row == blackBishop2.row && whitePawn2.column == blackBishop2.column)) {
            if (blackMove) {
                whitePawn2.row = 0;
                whitePawn2.column = 0;
            } else if (whiteMove) {
                blackBishop2.row = 0;
                blackBishop2.column = 0;
            }

        } else if ((whitePawn2.row == blackRook1.row && whitePawn2.column == blackRook1.column)) {
            if (blackMove) {
                whitePawn2.row = 0;
                whitePawn2.column = 0;
            } else if (whiteMove) {
                blackRook1.row = 0;
                blackRook1.column = 0;
            }

        } else if ((whitePawn2.row == blackRook2.row && whitePawn2.column == blackRook2.column)) {
            if (blackMove) {
                whitePawn2.row = 0;
                whitePawn2.column = 0;
            } else if (whiteMove) {
                blackRook2.row = 0;
                blackRook2.column = 0;
            }

        } else if ((whitePawn2.row == blackKnight1.row && whitePawn2.column == blackKnight1.column)) {
            if (blackMove) {
                whitePawn2.row = 0;
                whitePawn2.column = 0;
            } else if (whiteMove) {
                blackKnight1.row = 0;
                blackKnight1.column = 0;
            }

        } else if ((whitePawn2.row == blackKnight2.row && whitePawn2.column == blackKnight2.column)) {
            if (blackMove) {
                whitePawn2.row = 0;
                whitePawn2.column = 0;
            } else if (whiteMove) {
                blackKnight2.row = 0;
                blackKnight2.column = 0;
            }

        } else if ((whitePawn2.row == blackQueen.row && whitePawn2.column == blackQueen.column)) {
            if (blackMove) {
                whitePawn2.row = 0;
                whitePawn2.column = 0;
            } else if (whiteMove) {
                blackQueen.row = 0;
                blackQueen.column = 0;
            }

        } else if ((whitePawn2.row == blackKing.row && whitePawn2.column == blackKing.column)) {
            if (blackMove) {
                whitePawn2.row = 0;
                whitePawn2.column = 0;
            } else if (whiteMove) {
                blackKing.row = 0;
                blackKing.column = 0;
            }

        }


        //kolizja whitePawn3
        if ((whitePawn3.row == blackPawn1.row && whitePawn3.column == blackPawn1.column)) {
            if (blackMove) {
                whitePawn3.row = 0;
                whitePawn3.column = 0;
            } else if (whiteMove) {
                blackPawn1.row = 0;
                blackPawn1.column = 0;
            }

        } else if ((whitePawn3.row == blackPawn2.row && whitePawn3.column == blackPawn2.column)) {
            if (blackMove) {
                whitePawn3.row = 0;
                whitePawn3.column = 0;
            } else if (whiteMove) {
                blackPawn2.row = 0;
                blackPawn2.column = 0;
            }

        } else if ((whitePawn3.row == blackPawn3.row && whitePawn3.column == blackPawn3.column)) {
            if (blackMove) {
                whitePawn3.row = 0;
                whitePawn3.column = 0;
            } else if (whiteMove) {
                blackPawn3.row = 0;
                blackPawn3.column = 0;
            }

        } else if ((whitePawn3.row == blackPawn4.row && whitePawn3.column == blackPawn4.column)) {
            if (blackMove) {
                whitePawn3.row = 0;
                whitePawn3.column = 0;
            } else if (whiteMove) {
                blackPawn4.row = 0;
                blackPawn4.column = 0;
            }

        } else if ((whitePawn3.row == blackPawn5.row && whitePawn3.column == blackPawn5.column)) {
            if (blackMove) {
                whitePawn3.row = 0;
                whitePawn3.column = 0;
            } else if (whiteMove) {
                blackPawn5.row = 0;
                blackPawn5.column = 0;
            }

        } else if ((whitePawn3.row == blackPawn6.row && whitePawn3.column == blackPawn6.column)) {
            if (blackMove) {
                whitePawn3.row = 0;
                whitePawn3.column = 0;
            } else if (whiteMove) {
                blackPawn6.row = 0;
                blackPawn6.column = 0;
            }

        } else if ((whitePawn3.row == blackPawn7.row && whitePawn3.column == blackPawn7.column)) {
            if (blackMove) {
                whitePawn3.row = 0;
                whitePawn3.column = 0;
            } else if (whiteMove) {
                blackPawn7.row = 0;
                blackPawn7.column = 0;
            }

        } else if ((whitePawn3.row == blackPawn8.row && whitePawn3.column == blackPawn8.column)) {
            if (blackMove) {
                whitePawn3.row = 0;
                whitePawn3.column = 0;
            } else if (whiteMove) {
                blackPawn8.row = 0;
                blackPawn8.column = 0;
            }

        } else if ((whitePawn3.row == blackBishop1.row && whitePawn3.column == blackBishop1.column)) {
            if (blackMove) {
                whitePawn3.row = 0;
                whitePawn3.column = 0;
            } else if (whiteMove) {
                blackBishop1.row = 0;
                blackBishop1.column = 0;
            }

        } else if ((whitePawn3.row == blackBishop2.row && whitePawn3.column == blackBishop2.column)) {
            if (blackMove) {
                whitePawn3.row = 0;
                whitePawn3.column = 0;
            } else if (whiteMove) {
                blackBishop2.row = 0;
                blackBishop2.column = 0;
            }

        } else if ((whitePawn3.row == blackRook1.row && whitePawn3.column == blackRook1.column)) {
            if (blackMove) {
                whitePawn3.row = 0;
                whitePawn3.column = 0;
            } else if (whiteMove) {
                blackRook1.row = 0;
                blackRook1.column = 0;
            }

        } else if ((whitePawn3.row == blackRook2.row && whitePawn3.column == blackRook2.column)) {
            if (blackMove) {
                whitePawn3.row = 0;
                whitePawn3.column = 0;
            } else if (whiteMove) {
                blackRook2.row = 0;
                blackRook2.column = 0;
            }

        } else if ((whitePawn3.row == blackKnight1.row && whitePawn3.column == blackKnight1.column)) {
            if (blackMove) {
                whitePawn3.row = 0;
                whitePawn3.column = 0;
            } else if (whiteMove) {
                blackKnight1.row = 0;
                blackKnight1.column = 0;
            }

        } else if ((whitePawn3.row == blackKnight2.row && whitePawn3.column == blackKnight2.column)) {
            if (blackMove) {
                whitePawn3.row = 0;
                whitePawn3.column = 0;
            } else if (whiteMove) {
                blackKnight2.row = 0;
                blackKnight2.column = 0;
            }

        } else if ((whitePawn3.row == blackQueen.row && whitePawn3.column == blackQueen.column)) {
            if (blackMove) {
                whitePawn3.row = 0;
                whitePawn3.column = 0;
            } else if (whiteMove) {
                blackQueen.row = 0;
                blackQueen.column = 0;
            }

        } else if ((whitePawn3.row == blackKing.row && whitePawn3.column == blackKing.column)) {
            if (blackMove) {
                whitePawn3.row = 0;
                whitePawn3.column = 0;
            } else if (whiteMove) {
                blackKing.row = 0;
                blackKing.column = 0;
            }

        }


        //kolizja whitePawn4
        if ((whitePawn4.row == blackPawn1.row && whitePawn4.column == blackPawn1.column)) {
            if (blackMove) {
                whitePawn4.row = 0;
                whitePawn4.column = 0;
            } else if (whiteMove) {
                blackPawn1.row = 0;
                blackPawn1.column = 0;
            }

        } else if ((whitePawn4.row == blackPawn2.row && whitePawn4.column == blackPawn2.column)) {
            if (blackMove) {
                whitePawn4.row = 0;
                whitePawn4.column = 0;
            } else if (whiteMove) {
                blackPawn2.row = 0;
                blackPawn2.column = 0;
            }

        } else if ((whitePawn4.row == blackPawn3.row && whitePawn4.column == blackPawn3.column)) {
            if (blackMove) {
                whitePawn4.row = 0;
                whitePawn4.column = 0;
            } else if (whiteMove) {
                blackPawn3.row = 0;
                blackPawn3.column = 0;
            }

        } else if ((whitePawn4.row == blackPawn4.row && whitePawn4.column == blackPawn4.column)) {
            if (blackMove) {
                whitePawn4.row = 0;
                whitePawn4.column = 0;
            } else if (whiteMove) {
                blackPawn4.row = 0;
                blackPawn4.column = 0;
            }

        } else if ((whitePawn4.row == blackPawn5.row && whitePawn4.column == blackPawn5.column)) {
            if (blackMove) {
                whitePawn4.row = 0;
                whitePawn4.column = 0;
            } else if (whiteMove) {
                blackPawn5.row = 0;
                blackPawn5.column = 0;
            }

        } else if ((whitePawn4.row == blackPawn6.row && whitePawn4.column == blackPawn6.column)) {
            if (blackMove) {
                whitePawn4.row = 0;
                whitePawn4.column = 0;
            } else if (whiteMove) {
                blackPawn6.row = 0;
                blackPawn6.column = 0;
            }

        } else if ((whitePawn4.row == blackPawn7.row && whitePawn4.column == blackPawn7.column)) {
            if (blackMove) {
                whitePawn4.row = 0;
                whitePawn4.column = 0;
            } else if (whiteMove) {
                blackPawn7.row = 0;
                blackPawn7.column = 0;
            }

        } else if ((whitePawn4.row == blackPawn8.row && whitePawn4.column == blackPawn8.column)) {
            if (blackMove) {
                whitePawn4.row = 0;
                whitePawn4.column = 0;
            } else if (whiteMove) {
                blackPawn8.row = 0;
                blackPawn8.column = 0;
            }

        } else if ((whitePawn4.row == blackBishop1.row && whitePawn4.column == blackBishop1.column)) {
            if (blackMove) {
                whitePawn4.row = 0;
                whitePawn4.column = 0;
            } else if (whiteMove) {
                blackBishop1.row = 0;
                blackBishop1.column = 0;
            }

        } else if ((whitePawn4.row == blackBishop2.row && whitePawn4.column == blackBishop2.column)) {
            if (blackMove) {
                whitePawn4.row = 0;
                whitePawn4.column = 0;
            } else if (whiteMove) {
                blackBishop2.row = 0;
                blackBishop2.column = 0;
            }

        } else if ((whitePawn4.row == blackRook1.row && whitePawn4.column == blackRook1.column)) {
            if (blackMove) {
                whitePawn4.row = 0;
                whitePawn4.column = 0;
            } else if (whiteMove) {
                blackRook1.row = 0;
                blackRook1.column = 0;
            }

        } else if ((whitePawn4.row == blackRook2.row && whitePawn4.column == blackRook2.column)) {
            if (blackMove) {
                whitePawn4.row = 0;
                whitePawn4.column = 0;
            } else if (whiteMove) {
                blackRook2.row = 0;
                blackRook2.column = 0;
            }

        } else if ((whitePawn4.row == blackKnight1.row && whitePawn4.column == blackKnight1.column)) {
            if (blackMove) {
                whitePawn4.row = 0;
                whitePawn4.column = 0;
            } else if (whiteMove) {
                blackKnight1.row = 0;
                blackKnight1.column = 0;
            }

        } else if ((whitePawn4.row == blackKnight2.row && whitePawn4.column == blackKnight2.column)) {
            if (blackMove) {
                whitePawn4.row = 0;
                whitePawn4.column = 0;
            } else if (whiteMove) {
                blackKnight2.row = 0;
                blackKnight2.column = 0;
            }

        } else if ((whitePawn4.row == blackQueen.row && whitePawn4.column == blackQueen.column)) {
            if (blackMove) {
                whitePawn4.row = 0;
                whitePawn4.column = 0;
            } else if (whiteMove) {
                blackQueen.row = 0;
                blackQueen.column = 0;
            }

        } else if ((whitePawn4.row == blackKing.row && whitePawn4.column == blackKing.column)) {
            if (blackMove) {
                whitePawn4.row = 0;
                whitePawn4.column = 0;
            } else if (whiteMove) {
                blackKing.row = 0;
                blackKing.column = 0;
            }

        }


        //kolizja whitePawn5
        if ((whitePawn5.row == blackPawn1.row && whitePawn5.column == blackPawn1.column)) {
            if (blackMove) {
                whitePawn5.row = 0;
                whitePawn5.column = 0;
            } else if (whiteMove) {
                blackPawn1.row = 0;
                blackPawn1.column = 0;
            }

        } else if ((whitePawn5.row == blackPawn2.row && whitePawn5.column == blackPawn2.column)) {
            if (blackMove) {
                whitePawn5.row = 0;
                whitePawn5.column = 0;
            } else if (whiteMove) {
                blackPawn2.row = 0;
                blackPawn2.column = 0;
            }

        } else if ((whitePawn5.row == blackPawn3.row && whitePawn5.column == blackPawn3.column)) {
            if (blackMove) {
                whitePawn5.row = 0;
                whitePawn5.column = 0;
            } else if (whiteMove) {
                blackPawn3.row = 0;
                blackPawn3.column = 0;
            }

        } else if ((whitePawn5.row == blackPawn4.row && whitePawn5.column == blackPawn4.column)) {
            if (blackMove) {
                whitePawn5.row = 0;
                whitePawn5.column = 0;
            } else if (whiteMove) {
                blackPawn4.row = 0;
                blackPawn4.column = 0;
            }

        } else if ((whitePawn5.row == blackPawn5.row && whitePawn5.column == blackPawn5.column)) {
            if (blackMove) {
                whitePawn5.row = 0;
                whitePawn5.column = 0;
            } else if (whiteMove) {
                blackPawn5.row = 0;
                blackPawn5.column = 0;
            }

        } else if ((whitePawn5.row == blackPawn6.row && whitePawn5.column == blackPawn6.column)) {
            if (blackMove) {
                whitePawn5.row = 0;
                whitePawn5.column = 0;
            } else if (whiteMove) {
                blackPawn6.row = 0;
                blackPawn6.column = 0;
            }

        } else if ((whitePawn5.row == blackPawn7.row && whitePawn5.column == blackPawn7.column)) {
            if (blackMove) {
                whitePawn5.row = 0;
                whitePawn5.column = 0;
            } else if (whiteMove) {
                blackPawn7.row = 0;
                blackPawn7.column = 0;
            }

        } else if ((whitePawn5.row == blackPawn8.row && whitePawn5.column == blackPawn8.column)) {
            if (blackMove) {
                whitePawn5.row = 0;
                whitePawn5.column = 0;
            } else if (whiteMove) {
                blackPawn8.row = 0;
                blackPawn8.column = 0;
            }

        } else if ((whitePawn5.row == blackBishop1.row && whitePawn5.column == blackBishop1.column)) {
            if (blackMove) {
                whitePawn5.row = 0;
                whitePawn5.column = 0;
            } else if (whiteMove) {
                blackBishop1.row = 0;
                blackBishop1.column = 0;
            }

        } else if ((whitePawn5.row == blackBishop2.row && whitePawn5.column == blackBishop2.column)) {
            if (blackMove) {
                whitePawn5.row = 0;
                whitePawn5.column = 0;
            } else if (whiteMove) {
                blackBishop2.row = 0;
                blackBishop2.column = 0;
            }

        } else if ((whitePawn5.row == blackRook1.row && whitePawn5.column == blackRook1.column)) {
            if (blackMove) {
                whitePawn5.row = 0;
                whitePawn5.column = 0;
            } else if (whiteMove) {
                blackRook1.row = 0;
                blackRook1.column = 0;
            }

        } else if ((whitePawn5.row == blackRook2.row && whitePawn5.column == blackRook2.column)) {
            if (blackMove) {
                whitePawn5.row = 0;
                whitePawn5.column = 0;
            } else if (whiteMove) {
                blackRook2.row = 0;
                blackRook2.column = 0;
            }

        } else if ((whitePawn5.row == blackKnight1.row && whitePawn5.column == blackKnight1.column)) {
            if (blackMove) {
                whitePawn5.row = 0;
                whitePawn5.column = 0;
            } else if (whiteMove) {
                blackKnight1.row = 0;
                blackKnight1.column = 0;
            }

        } else if ((whitePawn5.row == blackKnight2.row && whitePawn5.column == blackKnight2.column)) {
            if (blackMove) {
                whitePawn5.row = 0;
                whitePawn5.column = 0;
            } else if (whiteMove) {
                blackKnight2.row = 0;
                blackKnight2.column = 0;
            }

        } else if ((whitePawn5.row == blackQueen.row && whitePawn5.column == blackQueen.column)) {
            if (blackMove) {
                whitePawn5.row = 0;
                whitePawn5.column = 0;
            } else if (whiteMove) {
                blackQueen.row = 0;
                blackQueen.column = 0;
            }

        } else if ((whitePawn5.row == blackKing.row && whitePawn5.column == blackKing.column)) {
            if (blackMove) {
                whitePawn5.row = 0;
                whitePawn5.column = 0;
            } else if (whiteMove) {
                blackKing.row = 0;
                blackKing.column = 0;
            }

        }


        //kolizja whitePawn6
        if ((whitePawn6.row == blackPawn1.row && whitePawn6.column == blackPawn1.column)) {
            if (blackMove) {
                whitePawn6.row = 0;
                whitePawn6.column = 0;
            } else if (whiteMove) {
                blackPawn1.row = 0;
                blackPawn1.column = 0;
            }

        } else if ((whitePawn6.row == blackPawn2.row && whitePawn6.column == blackPawn2.column)) {
            if (blackMove) {
                whitePawn6.row = 0;
                whitePawn6.column = 0;
            } else if (whiteMove) {
                blackPawn2.row = 0;
                blackPawn2.column = 0;
            }

        } else if ((whitePawn6.row == blackPawn3.row && whitePawn6.column == blackPawn3.column)) {
            if (blackMove) {
                whitePawn6.row = 0;
                whitePawn6.column = 0;
            } else if (whiteMove) {
                blackPawn3.row = 0;
                blackPawn3.column = 0;
            }

        } else if ((whitePawn6.row == blackPawn4.row && whitePawn6.column == blackPawn4.column)) {
            if (blackMove) {
                whitePawn6.row = 0;
                whitePawn6.column = 0;
            } else if (whiteMove) {
                blackPawn4.row = 0;
                blackPawn4.column = 0;
            }

        } else if ((whitePawn6.row == blackPawn5.row && whitePawn6.column == blackPawn5.column)) {
            if (blackMove) {
                whitePawn6.row = 0;
                whitePawn6.column = 0;
            } else if (whiteMove) {
                blackPawn5.row = 0;
                blackPawn5.column = 0;
            }

        } else if ((whitePawn6.row == blackPawn6.row && whitePawn6.column == blackPawn6.column)) {
            if (blackMove) {
                whitePawn6.row = 0;
                whitePawn6.column = 0;
            } else if (whiteMove) {
                blackPawn6.row = 0;
                blackPawn6.column = 0;
            }

        } else if ((whitePawn6.row == blackPawn7.row && whitePawn6.column == blackPawn7.column)) {
            if (blackMove) {
                whitePawn6.row = 0;
                whitePawn6.column = 0;
            } else if (whiteMove) {
                blackPawn7.row = 0;
                blackPawn7.column = 0;
            }

        } else if ((whitePawn6.row == blackPawn8.row && whitePawn6.column == blackPawn8.column)) {
            if (blackMove) {
                whitePawn6.row = 0;
                whitePawn6.column = 0;
            } else if (whiteMove) {
                blackPawn8.row = 0;
                blackPawn8.column = 0;
            }

        } else if ((whitePawn6.row == blackBishop1.row && whitePawn6.column == blackBishop1.column)) {
            if (blackMove) {
                whitePawn6.row = 0;
                whitePawn6.column = 0;
            } else if (whiteMove) {
                blackBishop1.row = 0;
                blackBishop1.column = 0;
            }

        } else if ((whitePawn6.row == blackBishop2.row && whitePawn6.column == blackBishop2.column)) {
            if (blackMove) {
                whitePawn6.row = 0;
                whitePawn6.column = 0;
            } else if (whiteMove) {
                blackBishop2.row = 0;
                blackBishop2.column = 0;
            }

        } else if ((whitePawn6.row == blackRook1.row && whitePawn6.column == blackRook1.column)) {
            if (blackMove) {
                whitePawn6.row = 0;
                whitePawn6.column = 0;
            } else if (whiteMove) {
                blackRook1.row = 0;
                blackRook1.column = 0;
            }

        } else if ((whitePawn6.row == blackRook2.row && whitePawn6.column == blackRook2.column)) {
            if (blackMove) {
                whitePawn6.row = 0;
                whitePawn6.column = 0;
            } else if (whiteMove) {
                blackRook2.row = 0;
                blackRook2.column = 0;
            }

        } else if ((whitePawn6.row == blackKnight1.row && whitePawn6.column == blackKnight1.column)) {
            if (blackMove) {
                whitePawn6.row = 0;
                whitePawn6.column = 0;
            } else if (whiteMove) {
                blackKnight1.row = 0;
                blackKnight1.column = 0;
            }

        } else if ((whitePawn6.row == blackKnight2.row && whitePawn6.column == blackKnight2.column)) {
            if (blackMove) {
                whitePawn6.row = 0;
                whitePawn6.column = 0;
            } else if (whiteMove) {
                blackKnight2.row = 0;
                blackKnight2.column = 0;
            }

        } else if ((whitePawn6.row == blackQueen.row && whitePawn6.column == blackQueen.column)) {
            if (blackMove) {
                whitePawn6.row = 0;
                whitePawn6.column = 0;
            } else if (whiteMove) {
                blackQueen.row = 0;
                blackQueen.column = 0;
            }

        } else if ((whitePawn6.row == blackKing.row && whitePawn6.column == blackKing.column)) {
            if (blackMove) {
                whitePawn6.row = 0;
                whitePawn6.column = 0;
            } else if (whiteMove) {
                blackKing.row = 0;
                blackKing.column = 0;
            }

        }


        //kolizja whitePawn7
        if ((whitePawn7.row == blackPawn1.row && whitePawn7.column == blackPawn1.column)) {
            if (blackMove) {
                whitePawn1.row = 0;
                whitePawn1.column = 0;
            } else if (whiteMove) {
                blackPawn1.row = 0;
                blackPawn1.column = 0;
            }

        } else if ((whitePawn7.row == blackPawn2.row && whitePawn7.column == blackPawn2.column)) {
            if (blackMove) {
                whitePawn7.row = 0;
                whitePawn7.column = 0;
            } else if (whiteMove) {
                blackPawn2.row = 0;
                blackPawn2.column = 0;
            }

        } else if ((whitePawn7.row == blackPawn3.row && whitePawn7.column == blackPawn3.column)) {
            if (blackMove) {
                whitePawn7.row = 0;
                whitePawn7.column = 0;
            } else if (whiteMove) {
                blackPawn3.row = 0;
                blackPawn3.column = 0;
            }

        } else if ((whitePawn7.row == blackPawn4.row && whitePawn7.column == blackPawn4.column)) {
            if (blackMove) {
                whitePawn7.row = 0;
                whitePawn7.column = 0;
            } else if (whiteMove) {
                blackPawn4.row = 0;
                blackPawn4.column = 0;
            }

        } else if ((whitePawn7.row == blackPawn5.row && whitePawn7.column == blackPawn5.column)) {
            if (blackMove) {
                whitePawn7.row = 0;
                whitePawn7.column = 0;
            } else if (whiteMove) {
                blackPawn5.row = 0;
                blackPawn5.column = 0;
            }

        } else if ((whitePawn7.row == blackPawn6.row && whitePawn7.column == blackPawn6.column)) {
            if (blackMove) {
                whitePawn7.row = 0;
                whitePawn7.column = 0;
            } else if (whiteMove) {
                blackPawn6.row = 0;
                blackPawn6.column = 0;
            }

        } else if ((whitePawn7.row == blackPawn7.row && whitePawn7.column == blackPawn7.column)) {
            if (blackMove) {
                whitePawn7.row = 0;
                whitePawn7.column = 0;
            } else if (whiteMove) {
                blackPawn7.row = 0;
                blackPawn7.column = 0;
            }

        } else if ((whitePawn7.row == blackPawn8.row && whitePawn7.column == blackPawn8.column)) {
            if (blackMove) {
                whitePawn7.row = 0;
                whitePawn7.column = 0;
            } else if (whiteMove) {
                blackPawn8.row = 0;
                blackPawn8.column = 0;
            }

        } else if ((whitePawn7.row == blackBishop1.row && whitePawn7.column == blackBishop1.column)) {
            if (blackMove) {
                whitePawn7.row = 0;
                whitePawn7.column = 0;
            } else if (whiteMove) {
                blackBishop1.row = 0;
                blackBishop1.column = 0;
            }

        } else if ((whitePawn7.row == blackBishop2.row && whitePawn7.column == blackBishop2.column)) {
            if (blackMove) {
                whitePawn7.row = 0;
                whitePawn7.column = 0;
            } else if (whiteMove) {
                blackBishop2.row = 0;
                blackBishop2.column = 0;
            }

        } else if ((whitePawn7.row == blackRook1.row && whitePawn7.column == blackRook1.column)) {
            if (blackMove) {
                whitePawn7.row = 0;
                whitePawn7.column = 0;
            } else if (whiteMove) {
                blackRook1.row = 0;
                blackRook1.column = 0;
            }

        } else if ((whitePawn7.row == blackRook2.row && whitePawn7.column == blackRook2.column)) {
            if (blackMove) {
                whitePawn7.row = 0;
                whitePawn7.column = 0;
            } else if (whiteMove) {
                blackRook2.row = 0;
                blackRook2.column = 0;
            }

        } else if ((whitePawn7.row == blackKnight1.row && whitePawn7.column == blackKnight1.column)) {
            if (blackMove) {
                whitePawn7.row = 0;
                whitePawn7.column = 0;
            } else if (whiteMove) {
                blackKnight1.row = 0;
                blackKnight1.column = 0;
            }

        } else if ((whitePawn7.row == blackKnight2.row && whitePawn7.column == blackKnight2.column)) {
            if (blackMove) {
                whitePawn7.row = 0;
                whitePawn7.column = 0;
            } else if (whiteMove) {
                blackKnight2.row = 0;
                blackKnight2.column = 0;
            }

        } else if ((whitePawn7.row == blackQueen.row && whitePawn7.column == blackQueen.column)) {
            if (blackMove) {
                whitePawn7.row = 0;
                whitePawn7.column = 0;
            } else if (whiteMove) {
                blackQueen.row = 0;
                blackQueen.column = 0;
            }

        } else if ((whitePawn7.row == blackKing.row && whitePawn7.column == blackKing.column)) {
            if (blackMove) {
                whitePawn7.row = 0;
                whitePawn7.column = 0;
            } else if (whiteMove) {
                blackKing.row = 0;
                blackKing.column = 0;
            }

        }


        //kolizja whitePawn8
        if ((whitePawn8.row == blackPawn1.row && whitePawn8.column == blackPawn1.column)) {
            if (blackMove) {
                whitePawn8.row = 0;
                whitePawn8.column = 0;
            } else if (whiteMove) {
                blackPawn1.row = 0;
                blackPawn1.column = 0;
            }

        } else if ((whitePawn8.row == blackPawn2.row && whitePawn8.column == blackPawn2.column)) {
            if (blackMove) {
                whitePawn8.row = 0;
                whitePawn8.column = 0;
            } else if (whiteMove) {
                blackPawn2.row = 0;
                blackPawn2.column = 0;
            }

        } else if ((whitePawn8.row == blackPawn3.row && whitePawn8.column == blackPawn3.column)) {
            if (blackMove) {
                whitePawn8.row = 0;
                whitePawn8.column = 0;
            } else if (whiteMove) {
                blackPawn3.row = 0;
                blackPawn3.column = 0;
            }

        } else if ((whitePawn8.row == blackPawn4.row && whitePawn8.column == blackPawn4.column)) {
            if (blackMove) {
                whitePawn8.row = 0;
                whitePawn8.column = 0;
            } else if (whiteMove) {
                blackPawn4.row = 0;
                blackPawn4.column = 0;
            }

        } else if ((whitePawn8.row == blackPawn5.row && whitePawn8.column == blackPawn5.column)) {
            if (blackMove) {
                whitePawn8.row = 0;
                whitePawn8.column = 0;
            } else if (whiteMove) {
                blackPawn5.row = 0;
                blackPawn5.column = 0;
            }

        } else if ((whitePawn8.row == blackPawn6.row && whitePawn8.column == blackPawn6.column)) {
            if (blackMove) {
                whitePawn8.row = 0;
                whitePawn8.column = 0;
            } else if (whiteMove) {
                blackPawn6.row = 0;
                blackPawn6.column = 0;
            }

        } else if ((whitePawn8.row == blackPawn7.row && whitePawn8.column == blackPawn7.column)) {
            if (blackMove) {
                whitePawn8.row = 0;
                whitePawn8.column = 0;
            } else if (whiteMove) {
                blackPawn7.row = 0;
                blackPawn7.column = 0;
            }

        } else if ((whitePawn8.row == blackPawn8.row && whitePawn8.column == blackPawn8.column)) {
            if (blackMove) {
                whitePawn8.row = 0;
                whitePawn8.column = 0;
            } else if (whiteMove) {
                blackPawn8.row = 0;
                blackPawn8.column = 0;
            }

        } else if ((whitePawn8.row == blackBishop1.row && whitePawn8.column == blackBishop1.column)) {
            if (blackMove) {
                whitePawn8.row = 0;
                whitePawn8.column = 0;
            } else if (whiteMove) {
                blackBishop1.row = 0;
                blackBishop1.column = 0;
            }

        } else if ((whitePawn8.row == blackBishop2.row && whitePawn8.column == blackBishop2.column)) {
            if (blackMove) {
                whitePawn8.row = 0;
                whitePawn8.column = 0;
            } else if (whiteMove) {
                blackBishop2.row = 0;
                blackBishop2.column = 0;
            }

        } else if ((whitePawn8.row == blackRook1.row && whitePawn8.column == blackRook1.column)) {
            if (blackMove) {
                whitePawn8.row = 0;
                whitePawn8.column = 0;
            } else if (whiteMove) {
                blackRook1.row = 0;
                blackRook1.column = 0;
            }

        } else if ((whitePawn8.row == blackRook2.row && whitePawn8.column == blackRook2.column)) {
            if (blackMove) {
                whitePawn8.row = 0;
                whitePawn8.column = 0;
            } else if (whiteMove) {
                blackRook2.row = 0;
                blackRook2.column = 0;
            }

        } else if ((whitePawn8.row == blackKnight1.row && whitePawn8.column == blackKnight1.column)) {
            if (blackMove) {
                whitePawn8.row = 0;
                whitePawn8.column = 0;
            } else if (whiteMove) {
                blackKnight1.row = 0;
                blackKnight1.column = 0;
            }

        } else if ((whitePawn8.row == blackKnight2.row && whitePawn8.column == blackKnight2.column)) {
            if (blackMove) {
                whitePawn8.row = 0;
                whitePawn8.column = 0;
            } else if (whiteMove) {
                blackKnight2.row = 0;
                blackKnight2.column = 0;
            }

        } else if ((whitePawn8.row == blackQueen.row && whitePawn8.column == blackQueen.column)) {
            if (blackMove) {
                whitePawn8.row = 0;
                whitePawn8.column = 0;
            } else if (whiteMove) {
                blackQueen.row = 0;
                blackQueen.column = 0;
            }

        } else if ((whitePawn8.row == blackKing.row && whitePawn8.column == blackKing.column)) {
            if (blackMove) {
                whitePawn8.row = 0;
                whitePawn8.column = 0;
            } else if (whiteMove) {
                blackKing.row = 0;
                blackKing.column = 0;
            }

        }

        */
    }











    public void justMove(){
        //Poruszanie się Białe
        if (columnBoard < 9 && rowBoard < 9 && columnBoard > 0 && rowBoard > 0) {
            if (whiteBishop1.clicked) {
                moveWhiteDone = whiteBishop1.moveWhite(rowBoard, columnBoard, gameBoard, whiteKing, this, blackKnight1, blackKnight2,
                        blackBishop1, blackBishop2,
                        blackRook1, blackRook2,
                        blackQueen,
                        blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                        blackPawn5, blackPawn6, blackPawn7, blackPawn8, blackKing);
                repaint();
            } else if (whiteBishop2.clicked) {
                moveWhiteDone = whiteBishop2.moveWhite(rowBoard, columnBoard, gameBoard, whiteKing, this, blackKnight1, blackKnight2,
                        blackBishop1, blackBishop2,
                        blackRook1, blackRook2,
                        blackQueen,
                        blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                        blackPawn5, blackPawn6, blackPawn7, blackPawn8, blackKing);
                repaint();
            } else if (whiteKnight1.clicked) {
                moveWhiteDone = whiteKnight1.moveWhite(rowBoard, columnBoard, gameBoard, whiteKing, this, blackKnight1, blackKnight2,
                        blackBishop1, blackBishop2,
                        blackRook1, blackRook2,
                        blackQueen,
                        blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                        blackPawn5, blackPawn6, blackPawn7, blackPawn8, blackKing);
                repaint();
            } else if (whiteKnight2.clicked) {
                moveWhiteDone = whiteKnight2.moveWhite(rowBoard, columnBoard, gameBoard, whiteKing, this, blackKnight1, blackKnight2,
                        blackBishop1, blackBishop2,
                        blackRook1, blackRook2,
                        blackQueen,
                        blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                        blackPawn5, blackPawn6, blackPawn7, blackPawn8, blackKing);
                repaint();
            } else if (whiteRook1.clicked) {
                moveWhiteDone = whiteRook1.moveWhite(rowBoard, columnBoard, gameBoard, whiteKing, this, blackKnight1, blackKnight2,
                        blackBishop1, blackBishop2,
                        blackRook1, blackRook2,
                        blackQueen,
                        blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                        blackPawn5, blackPawn6, blackPawn7, blackPawn8, blackKing);
                if (moveWhiteDone) whiteKing.whiteRook1CastlingAccess = false;
                repaint();
            } else if (whiteRook2.clicked) {
                moveWhiteDone = whiteRook2.moveWhite(rowBoard, columnBoard, gameBoard, whiteKing, this, blackKnight1, blackKnight2,
                        blackBishop1, blackBishop2,
                        blackRook1, blackRook2,
                        blackQueen,
                        blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                        blackPawn5, blackPawn6, blackPawn7, blackPawn8, blackKing);
                if (moveWhiteDone) whiteKing.whiteRook2CastlingAccess = false;
                repaint();
            } else if (whiteQueen.clicked) {
                moveWhiteDone = whiteQueen.moveWhite(rowBoard, columnBoard, gameBoard, whiteKing, this, blackKnight1, blackKnight2,
                        blackBishop1, blackBishop2,
                        blackRook1, blackRook2,
                        blackQueen,
                        blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                        blackPawn5, blackPawn6, blackPawn7, blackPawn8, blackKing);
                repaint();
            } else if (whiteKing.clicked) {
                moveWhiteDone = whiteKing.moveWhite(rowBoard, columnBoard, gameBoard, blackKing);
                if (whiteKing.castlingLong) {
                    whiteRook1.row = 1;
                    whiteRook1.column = 4;
                    gameBoard[whiteRook1.row - 1][whiteRook1.column - 1].identityOfSquare = 1;
                    gameBoard[0][0].identityOfSquare = 0;
                } else if (whiteKing.castlingShort) {
                    whiteRook2.row = 1;
                    whiteRook2.column = 6;
                    gameBoard[whiteRook2.row - 1][whiteRook2.column - 1].identityOfSquare = 8;
                    gameBoard[0][7].identityOfSquare = 0;
                }
                repaint();
            } else if (whitePawn1.clicked) {
                if (whitePawn1.column != 8 && whitePawn1.row == 5 && gameBoard[whitePawn1.row - 1][whitePawn1.column].occupiedFieldByBlack && whitePawn1.row + 1 == rowBoard && whitePawn1.column + 1 == columnBoard) {
                    //BICIE PIONKA DZIAŁA , NIE MOZE TEZ WDEPNAC PIONEK W PIONKA , RUCH Z POZYCJI WYJSCIOWEJ O 2 POLA DOZWOLONY, BRAK BICIA W PRZELOCIE
                    if (gameBoard[whitePawn1.row - 1][whitePawn1.column].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn1.row - 1][whitePawn1.column].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn1.row - 1][whitePawn1.column].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn1.row - 1][whitePawn1.column].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn1.row - 1][whitePawn1.column].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn1.row - 1][whitePawn1.column].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn1.row - 1][whitePawn1.column].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn1.row - 1][whitePawn1.column].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                        moveWhiteDone = true;
                    }

                    if (moveWhiteDone) {
                        gameBoard[whitePawn1.row - 1][whitePawn1.column].identityOfSquare = 0;

                        gameBoard[whitePawn1.row - 1][whitePawn1.column].occupiedFieldByBlack = false;
                        whitePawn1.row = rowBoard;
                        whitePawn1.column = columnBoard;
                    }

                } else if (whitePawn1.column != 1 && whitePawn1.row == 5 && gameBoard[whitePawn1.row - 1][whitePawn1.column - 2].occupiedFieldByBlack && whitePawn1.row + 1 == rowBoard && whitePawn1.column - 1 == columnBoard) {

                    if (gameBoard[whitePawn1.row - 1][whitePawn1.column - 2].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn1.row - 1][whitePawn1.column - 2].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn1.row - 1][whitePawn1.column - 2].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn1.row - 1][whitePawn1.column - 2].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn1.row - 1][whitePawn1.column - 2].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn1.row - 1][whitePawn1.column - 2].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn1.row - 1][whitePawn1.column - 2].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn1.row - 1][whitePawn1.column - 2].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                        moveWhiteDone = true;
                    }

                    if (moveWhiteDone) {
                        gameBoard[whitePawn1.row - 1][whitePawn1.column - 2].identityOfSquare = 0;
                        gameBoard[whitePawn1.row - 1][whitePawn1.column - 2].occupiedFieldByBlack = false;
                        whitePawn1.row = rowBoard;
                        whitePawn1.column = columnBoard;

                    }

                } else {
                    moveWhiteDone = whitePawn1.moveWhite(rowBoard, columnBoard, gameBoard, whiteKing, this, blackKnight1, blackKnight2,
                            blackBishop1, blackBishop2,
                            blackRook1, blackRook2,
                            blackQueen,
                            blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                            blackPawn5, blackPawn6, blackPawn7, blackPawn8, blackKing);
                }
                repaint();
            } else if (whitePawn2.clicked) {

                if (whitePawn2.column != 8 && whitePawn2.row == 5 && gameBoard[whitePawn2.row - 1][whitePawn2.column].occupiedFieldByBlack && whitePawn2.row + 1 == rowBoard && whitePawn2.column + 1 == columnBoard) {
                    if (gameBoard[whitePawn2.row - 1][whitePawn2.column].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn2.row - 1][whitePawn2.column].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn2.row - 1][whitePawn2.column].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn2.row - 1][whitePawn2.column].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn2.row - 1][whitePawn2.column].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn2.row - 1][whitePawn2.column].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn2.row - 1][whitePawn2.column].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn2.row - 1][whitePawn2.column].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                        moveWhiteDone = true;
                    }

                    if (moveWhiteDone) {
                        gameBoard[whitePawn2.row - 1][whitePawn2.column].identityOfSquare = 0;

                        gameBoard[whitePawn2.row - 1][whitePawn2.column].occupiedFieldByBlack = false;
                        whitePawn2.row = rowBoard;
                        whitePawn2.column = columnBoard;
                    }

                } else if (whitePawn2.column != 1 && whitePawn2.row == 5 && gameBoard[whitePawn2.row - 1][whitePawn2.column - 2].occupiedFieldByBlack && whitePawn2.row + 1 == rowBoard && whitePawn2.column - 1 == columnBoard) {

                    if (gameBoard[whitePawn2.row - 1][whitePawn2.column - 2].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn2.row - 1][whitePawn2.column - 2].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn2.row - 1][whitePawn2.column - 2].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn2.row - 1][whitePawn2.column - 2].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn2.row - 1][whitePawn2.column - 2].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn2.row - 1][whitePawn2.column - 2].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn2.row - 1][whitePawn2.column - 2].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn2.row - 1][whitePawn2.column - 2].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                        moveWhiteDone = true;
                    }

                    if (moveWhiteDone) {
                        gameBoard[whitePawn2.row - 1][whitePawn2.column - 2].identityOfSquare = 0;
                        gameBoard[whitePawn2.row - 1][whitePawn2.column - 2].occupiedFieldByBlack = false;
                        whitePawn2.row = rowBoard;
                        whitePawn2.column = columnBoard;
                    }

                } else {
                    moveWhiteDone = whitePawn2.moveWhite(rowBoard, columnBoard, gameBoard, whiteKing, this, blackKnight1, blackKnight2,
                            blackBishop1, blackBishop2,
                            blackRook1, blackRook2,
                            blackQueen,
                            blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                            blackPawn5, blackPawn6, blackPawn7, blackPawn8, blackKing);
                }
                repaint();
            } else if (whitePawn3.clicked) {

                if (whitePawn3.column != 8 && whitePawn3.row == 5 && gameBoard[whitePawn3.row - 1][whitePawn3.column].occupiedFieldByBlack && whitePawn3.row + 1 == rowBoard && whitePawn3.column + 1 == columnBoard) {
                    if (gameBoard[whitePawn3.row - 1][whitePawn3.column].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn3.row - 1][whitePawn3.column].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn3.row - 1][whitePawn3.column].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn3.row - 1][whitePawn3.column].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn3.row - 1][whitePawn3.column].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn3.row - 1][whitePawn3.column].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn3.row - 1][whitePawn3.column].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn3.row - 1][whitePawn3.column].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                        moveWhiteDone = true;
                    }

                    if (moveWhiteDone) {
                        gameBoard[whitePawn3.row - 1][whitePawn3.column].identityOfSquare = 0;
                        gameBoard[whitePawn3.row - 1][whitePawn3.column].occupiedFieldByBlack = false;
                        whitePawn3.row = rowBoard;
                        whitePawn3.column = columnBoard;
                    }

                } else if (whitePawn3.column != 1 && whitePawn3.row == 5 && gameBoard[whitePawn3.row - 1][whitePawn3.column - 2].occupiedFieldByBlack && whitePawn3.row + 1 == rowBoard && whitePawn3.column - 1 == columnBoard) {

                    if (gameBoard[whitePawn3.row - 1][whitePawn3.column - 2].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn3.row - 1][whitePawn3.column - 2].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn3.row - 1][whitePawn3.column - 2].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn3.row - 1][whitePawn3.column - 2].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn3.row - 1][whitePawn3.column - 2].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn3.row - 1][whitePawn3.column - 2].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn3.row - 1][whitePawn3.column - 2].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn3.row - 1][whitePawn3.column - 2].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                        moveWhiteDone = true;
                    }

                    if (moveWhiteDone) {
                        gameBoard[whitePawn3.row - 1][whitePawn3.column - 2].identityOfSquare = 0;
                        gameBoard[whitePawn3.row - 1][whitePawn3.column - 2].occupiedFieldByBlack = false;
                        whitePawn3.row = rowBoard;
                        whitePawn3.column = columnBoard;
                    }

                } else {
                    moveWhiteDone = whitePawn3.moveWhite(rowBoard, columnBoard, gameBoard, whiteKing, this, blackKnight1, blackKnight2,
                            blackBishop1, blackBishop2,
                            blackRook1, blackRook2,
                            blackQueen,
                            blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                            blackPawn5, blackPawn6, blackPawn7, blackPawn8, blackKing);
                }
                repaint();
            } else if (whitePawn4.clicked) {

                if (whitePawn4.column != 8 && whitePawn4.row == 5 && gameBoard[whitePawn4.row - 1][whitePawn4.column].occupiedFieldByBlack && whitePawn4.row + 1 == rowBoard && whitePawn4.column + 1 == columnBoard) {
                    if (gameBoard[whitePawn4.row - 1][whitePawn4.column].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn4.row - 1][whitePawn4.column].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn4.row - 1][whitePawn4.column].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn4.row - 1][whitePawn4.column].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn4.row - 1][whitePawn4.column].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn4.row - 1][whitePawn4.column].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn4.row - 1][whitePawn4.column].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn4.row - 1][whitePawn4.column].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                        moveWhiteDone = true;
                    }

                    if (moveWhiteDone) {
                        gameBoard[whitePawn4.row - 1][whitePawn4.column].identityOfSquare = 0;
                        gameBoard[whitePawn4.row - 1][whitePawn4.column].occupiedFieldByBlack = false;
                        whitePawn4.row = rowBoard;
                        whitePawn4.column = columnBoard;
                    }


                } else if (whitePawn4.column != 1 && whitePawn4.row == 5 && gameBoard[whitePawn4.row - 1][whitePawn4.column - 2].occupiedFieldByBlack && whitePawn4.row + 1 == rowBoard && whitePawn4.column - 1 == columnBoard) {

                    if (gameBoard[whitePawn4.row - 1][whitePawn4.column - 2].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn4.row - 1][whitePawn4.column - 2].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn4.row - 1][whitePawn4.column - 2].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn4.row - 1][whitePawn4.column - 2].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn4.row - 1][whitePawn4.column - 2].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;
                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn4.row - 1][whitePawn4.column - 2].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn4.row - 1][whitePawn4.column - 2].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn4.row - 1][whitePawn4.column - 2].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                        moveWhiteDone = true;
                    }

                    if (moveWhiteDone) {
                        gameBoard[whitePawn4.row - 1][whitePawn4.column - 2].identityOfSquare = 0;
                        gameBoard[whitePawn4.row - 1][whitePawn4.column - 2].occupiedFieldByBlack = false;
                        whitePawn4.row = rowBoard;
                        whitePawn4.column = columnBoard;
                    }

                } else {
                    moveWhiteDone = whitePawn4.moveWhite(rowBoard, columnBoard, gameBoard, whiteKing, this, blackKnight1, blackKnight2,
                            blackBishop1, blackBishop2,
                            blackRook1, blackRook2,
                            blackQueen,
                            blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                            blackPawn5, blackPawn6, blackPawn7, blackPawn8, blackKing);
                }
                repaint();
            } else if (whitePawn5.clicked) {

                if (whitePawn5.column != 8 && whitePawn5.row == 5 && gameBoard[whitePawn5.row - 1][whitePawn5.column].occupiedFieldByBlack && whitePawn5.row + 1 == rowBoard && whitePawn5.column + 1 == columnBoard) {
                    if (gameBoard[whitePawn5.row - 1][whitePawn5.column].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn5.row - 1][whitePawn5.column].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn5.row - 1][whitePawn5.column].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn5.row - 1][whitePawn5.column].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn5.row - 1][whitePawn5.column].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn5.row - 1][whitePawn5.column].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn5.row - 1][whitePawn5.column].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn5.row - 1][whitePawn5.column].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                        moveWhiteDone = true;
                    }

                    if (moveWhiteDone) {
                        gameBoard[whitePawn5.row - 1][whitePawn5.column].identityOfSquare = 0;
                        gameBoard[whitePawn5.row - 1][whitePawn5.column].occupiedFieldByBlack = false;
                        whitePawn5.row = rowBoard;
                        whitePawn5.column = columnBoard;
                    }

                } else if (whitePawn5.column != 1 && whitePawn5.row == 5 && gameBoard[whitePawn5.row - 1][whitePawn5.column - 2].occupiedFieldByBlack && whitePawn5.row + 1 == rowBoard && whitePawn5.column - 1 == columnBoard) {

                    if (gameBoard[whitePawn5.row - 1][whitePawn5.column - 2].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn5.row - 1][whitePawn5.column - 2].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn5.row - 1][whitePawn5.column - 2].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn5.row - 1][whitePawn5.column - 2].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn5.row - 1][whitePawn5.column - 2].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn5.row - 1][whitePawn5.column - 2].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn5.row - 1][whitePawn5.column - 2].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn5.row - 1][whitePawn5.column - 2].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                        moveWhiteDone = true;
                    }

                    if (moveWhiteDone) {
                        gameBoard[whitePawn5.row - 1][whitePawn5.column - 2].identityOfSquare = 0;
                        gameBoard[whitePawn5.row - 1][whitePawn5.column - 2].occupiedFieldByBlack = false;
                        whitePawn5.row = rowBoard;
                        whitePawn5.column = columnBoard;
                    }
                } else {
                    moveWhiteDone = whitePawn5.moveWhite(rowBoard, columnBoard, gameBoard, whiteKing, this, blackKnight1, blackKnight2,
                            blackBishop1, blackBishop2,
                            blackRook1, blackRook2,
                            blackQueen,
                            blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                            blackPawn5, blackPawn6, blackPawn7, blackPawn8, blackKing);
                }
                repaint();
            } else if (whitePawn6.clicked) {

                if (whitePawn6.column != 8 && whitePawn6.row == 5 && gameBoard[whitePawn6.row - 1][whitePawn6.column].occupiedFieldByBlack && whitePawn6.row + 1 == rowBoard && whitePawn6.column + 1 == columnBoard) {
                    if (gameBoard[whitePawn6.row - 1][whitePawn6.column].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn6.row - 1][whitePawn6.column].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn6.row - 1][whitePawn6.column].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn6.row - 1][whitePawn6.column].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn6.row - 1][whitePawn6.column].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn6.row - 1][whitePawn6.column].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn6.row - 1][whitePawn6.column].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn6.row - 1][whitePawn6.column].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                        moveWhiteDone = true;
                    }


                    if (moveWhiteDone) {
                        gameBoard[whitePawn6.row - 1][whitePawn6.column].identityOfSquare = 0;
                        gameBoard[whitePawn6.row - 1][whitePawn6.column].occupiedFieldByBlack = false;
                        whitePawn6.row = rowBoard;
                        whitePawn6.column = columnBoard;
                    }

                } else if (whitePawn6.column != 1 && whitePawn6.row == 5 && gameBoard[whitePawn6.row - 1][whitePawn6.column - 2].occupiedFieldByBlack && whitePawn6.row + 1 == rowBoard && whitePawn6.column - 1 == columnBoard) {

                    if (gameBoard[whitePawn6.row - 1][whitePawn6.column - 2].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn6.row - 1][whitePawn6.column - 2].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn6.row - 1][whitePawn6.column - 2].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn6.row - 1][whitePawn6.column - 2].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn6.row - 1][whitePawn6.column - 2].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn6.row - 1][whitePawn6.column - 2].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn6.row - 1][whitePawn6.column - 2].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn6.row - 1][whitePawn6.column - 2].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                        moveWhiteDone = true;
                    }

                    if (moveWhiteDone) {
                        gameBoard[whitePawn6.row - 1][whitePawn6.column - 2].identityOfSquare = 0;
                        gameBoard[whitePawn6.row - 1][whitePawn6.column - 2].occupiedFieldByBlack = false;
                        whitePawn6.row = rowBoard;
                        whitePawn6.column = columnBoard;
                    }

                } else {
                    moveWhiteDone = whitePawn6.moveWhite(rowBoard, columnBoard, gameBoard, whiteKing, this, blackKnight1, blackKnight2,
                            blackBishop1, blackBishop2,
                            blackRook1, blackRook2,
                            blackQueen,
                            blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                            blackPawn5, blackPawn6, blackPawn7, blackPawn8, blackKing);
                }
                repaint();
            } else if (whitePawn7.clicked) {

                if (whitePawn7.column != 8 && whitePawn7.row == 5 && gameBoard[whitePawn7.row - 1][whitePawn7.column].occupiedFieldByBlack && whitePawn7.row + 1 == rowBoard && whitePawn7.column + 1 == columnBoard) {
                    if (gameBoard[whitePawn7.row - 1][whitePawn7.column].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn7.row - 1][whitePawn7.column].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn7.row - 1][whitePawn7.column].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn7.row - 1][whitePawn7.column].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn7.row - 1][whitePawn7.column].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn7.row - 1][whitePawn7.column].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn7.row - 1][whitePawn7.column].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn7.row - 1][whitePawn7.column].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;
                        moveWhiteDone = true;
                    }

                    if (moveWhiteDone) {
                        gameBoard[whitePawn7.row - 1][whitePawn7.column].identityOfSquare = 0;
                        gameBoard[whitePawn7.row - 1][whitePawn7.column].occupiedFieldByBlack = false;
                        whitePawn7.row = rowBoard;
                        whitePawn7.column = columnBoard;
                    }


                } else if (whitePawn7.column != 1 && whitePawn7.row == 5 && gameBoard[whitePawn7.row - 1][whitePawn7.column - 2].occupiedFieldByBlack && whitePawn7.row + 1 == rowBoard && whitePawn7.column - 1 == columnBoard) {

                    if (gameBoard[whitePawn7.row - 1][whitePawn7.column - 2].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn7.row - 1][whitePawn7.column - 2].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn7.row - 1][whitePawn7.column - 2].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn7.row - 1][whitePawn7.column - 2].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn7.row - 1][whitePawn7.column - 2].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn7.row - 1][whitePawn7.column - 2].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn7.row - 1][whitePawn7.column - 2].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn7.row - 1][whitePawn7.column - 2].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                        moveWhiteDone = true;
                    }

                    if (moveWhiteDone) {
                        gameBoard[whitePawn7.row - 1][whitePawn7.column - 2].identityOfSquare = 0;
                        gameBoard[whitePawn7.row - 1][whitePawn7.column - 2].occupiedFieldByBlack = false;
                        whitePawn7.row = rowBoard;
                        whitePawn7.column = columnBoard;
                    }

                } else {
                    moveWhiteDone = whitePawn7.moveWhite(rowBoard, columnBoard, gameBoard, whiteKing, this, blackKnight1, blackKnight2,
                            blackBishop1, blackBishop2,
                            blackRook1, blackRook2,
                            blackQueen,
                            blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                            blackPawn5, blackPawn6, blackPawn7, blackPawn8, blackKing);
                }
                repaint();
            } else if (whitePawn8.clicked) {

                if (whitePawn8.column != 8 && whitePawn8.row == 5 && gameBoard[whitePawn8.row - 1][whitePawn8.column].occupiedFieldByBlack && whitePawn8.row + 1 == rowBoard && whitePawn8.column + 1 == columnBoard) {
                    if (gameBoard[whitePawn8.row - 1][whitePawn8.column].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn8.row - 1][whitePawn8.column].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn8.row - 1][whitePawn8.column].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn8.row - 1][whitePawn8.column].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;
                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn8.row - 1][whitePawn8.column].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn8.row - 1][whitePawn8.column].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn8.row - 1][whitePawn8.column].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn8.row - 1][whitePawn8.column].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                        moveWhiteDone = true;
                    }

                    if (moveWhiteDone) {
                        gameBoard[whitePawn8.row - 1][whitePawn8.column].identityOfSquare = 0;
                        gameBoard[whitePawn8.row - 1][whitePawn8.column].occupiedFieldByBlack = false;
                        whitePawn8.row = rowBoard;
                        whitePawn8.column = columnBoard;
                    }


                } else if (whitePawn8.column != 1 && whitePawn8.row == 5 && gameBoard[whitePawn8.row - 1][whitePawn8.column - 2].occupiedFieldByBlack && whitePawn8.row + 1 == rowBoard && whitePawn8.column - 1 == columnBoard) {

                    if (gameBoard[whitePawn8.row - 1][whitePawn8.column - 2].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn8.row - 1][whitePawn8.column - 2].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn8.row - 1][whitePawn8.column - 2].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn8.row - 1][whitePawn8.column - 2].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn8.row - 1][whitePawn8.column - 2].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn8.row - 1][whitePawn8.column - 2].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn8.row - 1][whitePawn8.column - 2].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                        moveWhiteDone = true;
                    } else if (gameBoard[whitePawn8.row - 1][whitePawn8.column - 2].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                        moveWhiteDone = true;
                    }

                    if (moveWhiteDone) {
                        gameBoard[whitePawn8.row - 1][whitePawn8.column - 2].identityOfSquare = 0;
                        gameBoard[whitePawn8.row - 1][whitePawn8.column - 2].occupiedFieldByBlack = false;
                        whitePawn8.row = rowBoard;
                        whitePawn8.column = columnBoard;

                    }

                } else {
                    moveWhiteDone = whitePawn8.moveWhite(rowBoard, columnBoard, gameBoard, whiteKing, this, blackKnight1, blackKnight2,
                            blackBishop1, blackBishop2,
                            blackRook1, blackRook2,
                            blackQueen,
                            blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                            blackPawn5, blackPawn6, blackPawn7, blackPawn8, blackKing);
                }
                repaint();
            }


        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        Graphics2D g2 = (Graphics2D) g;

        Color bezowy = new Color(245, 222, 179);
        Color brazowy = new Color(139, 69, 19);
        Color czarny = new Color(0, 0, 0);
        Color bialy = new Color(255, 255, 255);
        Color blue = new Color(0,0,255);


        //czarne pola
        for (int i = 40; i <= 320; i = i + 40) {
            for (int j = 40; j <= 320; j = j + 40) {
                g2.setColor(brazowy);
                g2.fillRect(i, j, 40, 40);
            }
        }
        //biale pola od 1 linii
        for (int i = 80; i <= 320; i = i + 80) {
            for (int j = 40; j <= 320; j = j + 80) {
                g2.setColor(bezowy);
                g2.fillRect(i, j, 40, 40);
            }
        }
        //białe pola od 2 linii
        for (int i = 40; i <= 320; i = i + 80) {
            for (int j = 80; j <= 320; j = j + 80) {
                g2.setColor(bezowy);
                g2.fillRect(i, j, 40, 40);
            }
        }

        //czarne linie pomiedzy polami
        for (int i = 40; i <= 320; i = i + 40) {
            for (int j = 40; j <= 320; j = j + 40) {
                g2.setColor(czarny);
                g2.drawRect(i, j, 40, 40);
            }
        }

        //literki gora
        int k = 0;
        for (int i = 55; i <= 340; i = i + 40) {
            g2.setColor(bialy);
            g2.drawString(alfabet[k], i, 30);
            k++;
        }
        // literki dol
        k = 0;
        for (int i = 55; i <= 340; i = i + 40) {
            g2.setColor(bialy);
            g2.drawString(alfabet[k], i, 380);
            k++;
        }

        //cyferki
        int l = 1;
        for (int j = 65; j <= 360; j = j + 40) {
            g2.setColor(bialy);
            g2.drawString("" + l, 25, j);
            l++;

        }

        l = 1;
        for (int j = 65; j <= 360; j = j + 40) {
            g2.setColor(bialy);
            g2.drawString("" + l, 370, j);
            l++;

        }

        //Scieżki do Ikon
        //Queen


        //logoUczelni
        g2.setColor(brazowy);
        g2.drawString("KPU KROSNO - B.SZ.", 140, 420);

        g2.setColor(bezowy);
        g2.drawString("Play again!", 50, 420);

        g2.setColor(bialy);

        //whiteKnight1.row = whiteKnight1.row*40;
        //whiteKnight1.column = whiteKnight1.column*40;
        g2.drawImage(whiteKnightImage, whiteKnight1.column * 40 + 8, whiteKnight1.row * 40 + 8, null);
        //g2.drawRect(whiteKnight1.column+9,whiteKnight1.row+9,20,20);


        //whiteKnight2.row = whiteKnight2.row*40;
        //whiteKnight2.column = whiteKnight2.column*40;
        g2.drawImage(whiteKnightImage, whiteKnight2.column * 40 + 8, whiteKnight2.row * 40 + 8, null);
        //g2.drawRect(whiteKnight2.column+9,whiteKnight2.row+9,20,20);


        //whiteBishop1.row = whiteBishop1.row*40;
        //whiteBishop1.column = whiteBishop1.column*40;
        g2.drawImage(whiteBishopImage, whiteBishop1.column * 40 + 8, whiteBishop1.row * 40 + 8, null);
        //g2.drawRect(whiteBishop1.column+9, whiteBishop1.row+9,20,20);


        //whiteBishop2.row = whiteBishop2.row*40;
        //whiteBishop2.column = whiteBishop2.column*40;
        g2.drawImage(whiteBishopImage, whiteBishop2.column * 40 + 8, whiteBishop2.row * 40 + 8, null);
        //g2.drawRect(whiteBishop2.column+9, whiteBishop2.row+9,20,20);

        //whiteRook1.row = whiteRook1.row*40;
        //whiteRook1.column = whiteRook1.column*40;
        g2.drawImage(whiteRookImage, whiteRook1.column * 40 + 8, whiteRook1.row * 40 + 8, null);
        //g2.drawRect(whiteRook1.column+9, whiteRook1.row+9,20,20);


        //whiteRook2.row = whiteRook2.row*40;
        //whiteRook2.column = whiteRook2.column*40;
        g2.drawImage(whiteRookImage, whiteRook2.column * 40 + 8, whiteRook2.row * 40 + 8, null);
        //g2.drawRect(whiteRook2.column+9, whiteRook2.row+9,20,20);


        //whiteKing.row = whiteKing.row*40;
        //whiteKing.column = whiteKing.column*40;
        g2.drawImage(whiteKingImage, whiteKing.column * 40 + 8, whiteKing.row * 40 + 8, null);
        //g2.drawRect(whiteKing.column+9, whiteKing.row+9,20,20);


        //whiteQueen.row = whiteQueen.row*40;
        //whiteQueen.column = whiteQueen.column*40;
        g2.drawImage(whiteQueenImage, whiteQueen.column * 40 + 8, whiteQueen.row * 40 + 8, null);
        //g2.drawRect(whiteQueen.column+9, whiteQueen.row+9,20,20);

        // PIONKI BIALE
        //whitePawn1.row = whitePawn1.row*40;
        //whitePawn1.column = whitePawn1.column*40;
        g2.drawImage(whitePawn1.image, whitePawn1.column * 40 + 8, whitePawn1.row * 40 + 8, null);
        //g2.drawRect(whitePawn1.column+9,whitePawn1.row+9,20,20);


        //whitePawn2.row = whitePawn2.row*40;
        //whitePawn2.column = whitePawn2.column*40;
        g2.drawImage(whitePawn2.image, whitePawn2.column * 40 + 8, whitePawn2.row * 40 + 8, null);
        //g2.drawRect(whitePawn2.column+9,whitePawn2.row+9,20,20);


        //whitePawn3.row = whitePawn3.row*40;
        //whitePawn3.column = whitePawn3.column*40;
        g2.drawImage(whitePawn3.image, whitePawn3.column * 40 + 8, whitePawn3.row * 40 + 8, null);
        // g2.drawRect(whitePawn3.column+9,whitePawn3.row+9,20,20);


        //whitePawn4.row = whitePawn4.row*40;
        //whitePawn4.column = whitePawn4.column*40;
        g2.drawImage(whitePawn4.image, whitePawn4.column * 40 + 8, whitePawn4.row * 40 + 8, null);
        //g2.drawRect(whitePawn4.column+9,whitePawn4.row+9,20,20);


        //whitePawn5.row = whitePawn5.row*40;
        //whitePawn5.column = whitePawn5.column*40;
        g2.drawImage(whitePawn5.image, whitePawn5.column * 40 + 8, whitePawn5.row * 40 + 8, null);
        //g2.drawRect(whitePawn5.column+9,whitePawn5.row+9,20,20);


        //whitePawn6.row = whitePawn6.row*40;
        //whitePawn6.column = whitePawn6.column*40;
        g2.drawImage(whitePawn6.image, whitePawn6.column * 40 + 8, whitePawn6.row * 40 + 8, null);
        //g2.drawRect(whitePawn6.column+9,whitePawn6.row+9,20,20);


        //whitePawn7.row = whitePawn7.row*40;
        //whitePawn7.column = whitePawn7.column*40;
        g2.drawImage(whitePawn7.image, whitePawn7.column * 40 + 8, whitePawn7.row * 40 + 8, null);
        //g2.drawRect(whitePawn7.column+9,whitePawn7.row+9,20,20);


        //whitePawn8.row = whitePawn8.row*40;
        //whitePawn8.column = whitePawn8.column*40;
        g2.drawImage(whitePawn8.image, whitePawn8.column * 40 + 8, whitePawn8.row * 40 + 8, null);
        //g2.drawRect(whitePawn8.column+9,whitePawn8.row+9,20,20);
        //PIONKI BIALE


        /////////////////////////////////////////////////////////////////


        //blackKnight1.row = blackKnight1.row*40;
        //blackKnight1.column = blackKnight1.column*40;
        g2.drawImage(blackKnightImage, blackKnight1.column * 40 + 8, blackKnight1.row * 40 + 8, null);
        //g2.drawRect(blackKnight1.column+9,blackKnight1.row+9,20,20);


        //blackKnight2.row = blackKnight2.row*40;
        //blackKnight2.column = blackKnight2.column*40;
        g2.drawImage(blackKnightImage, blackKnight2.column * 40 + 8, blackKnight2.row * 40 + 8, null);
        //g2.drawRect(blackKnight2.column+9,blackKnight2.row+9,20,20);


        //blackBishop1.row = blackBishop1.row*40;
        //blackBishop1.column = blackBishop1.column*40;
        g2.drawImage(blackBishopImage, blackBishop1.column * 40 + 8, blackBishop1.row * 40 + 8, null);
        //g2.drawRect(blackBishop1.column+9, blackBishop1.row+9,20,20);


        //blackBishop2.row = blackBishop2.row*40;
        //blackBishop2.column = blackBishop2.column*40;
        g2.drawImage(blackBishopImage, blackBishop2.column * 40 + 8, blackBishop2.row * 40 + 8, null);
        //g2.drawRect(blackBishop2.column+9, blackBishop2.row+9,20,20);

        //blackRook1.row = blackRook1.row*40;
        //blackRook1.column = blackRook1.column*40;
        g2.drawImage(blackRookImage, blackRook1.column * 40 + 8, blackRook1.row * 40 + 8, null);
        //g2.drawRect(blackRook1.column+9, blackRook1.row+9,20,20);


        //blackRook2.row = blackRook2.row*40;
        //blackRook2.column = blackRook2.column*40;
        g2.drawImage(blackRookImage, blackRook2.column * 40 + 8, blackRook2.row * 40 + 8, null);
        //g2.drawRect(blackRook2.column+9, blackRook2.row+9,20,20);


        //blackKing.row = blackKing.row*40;
        //blackKing.column = blackKing.column*40;
        g2.drawImage(blackKingImage, blackKing.column * 40 + 8, blackKing.row * 40 + 8, null);
        //g2.drawRect(blackKing.column+9, blackKing.row+9,20,20);


        //blackQueen.row = blackQueen.row*40;
        //blackQueen.column = blackQueen.column*40;
        g2.drawImage(blackQueenImage, blackQueen.column * 40 + 8, blackQueen.row * 40 + 8, null);
        //g2.drawRect(blackQueen.column+9, blackQueen.row+9,20,20);

        // PIONKI CZARNE
        //blackPawn1.row = blackPawn1.row*40;
        //blackPawn1.column = blackPawn1.column*40;
        g2.drawImage(blackPawn1.image, blackPawn1.column * 40 + 8, blackPawn1.row * 40 + 8, null);
        //g2.drawRect(blackPawn1.column+9,blackPawn1.row+9,20,20);

        //blackPawn2.row = blackPawn2.row*40;
        //blackPawn2.column = blackPawn2.column*40;
        g2.drawImage(blackPawn2.image, blackPawn2.column * 40 + 8, blackPawn2.row * 40 + 8, null);
        //g2.drawRect(blackPawn2.column+9,blackPawn2.row+9,20,20);

        //blackPawn3.row = blackPawn3.row*40;
        //blackPawn3.column = blackPawn3.column*40;
        g2.drawImage(blackPawn3.image, blackPawn3.column * 40 + 8, blackPawn3.row * 40 + 8, null);
        //g2.drawRect(blackPawn3.column+9,blackPawn3.row+9,20,20);

        //blackPawn4.row = blackPawn4.row*40;
        //blackPawn4.column = blackPawn4.column*40;
        g2.drawImage(blackPawn4.image, blackPawn4.column * 40 + 8, blackPawn4.row * 40 + 8, null);
        //g2.drawRect(blackPawn4.column+9,blackPawn4.row+9,20,20);

        //blackPawn5.row = blackPawn5.row*40;
        //blackPawn5.column = blackPawn5.column*40;
        g2.drawImage(blackPawn5.image, blackPawn5.column * 40 + 8, blackPawn5.row * 40 + 8, null);
        //g2.drawRect(blackPawn5.column+9,blackPawn5.row+9,20,20);

        //blackPawn6.row = blackPawn6.row*40;
        //blackPawn6.column = blackPawn6.column*40;
        g2.drawImage(blackPawn6.image, blackPawn6.column * 40 + 8, blackPawn6.row * 40 + 8, null);
        //g2.drawRect(blackPawn6.column+9,blackPawn6.row+9,20,20);

        //blackPawn7.row = blackPawn7.row*40;
        //blackPawn7.column = blackPawn7.column*40;
        g2.drawImage(blackPawn7.image, blackPawn7.column * 40 + 8, blackPawn7.row * 40 + 8, null);
        //g2.drawRect(blackPawn7.column+9,blackPawn7.row+9,20,20);

        //blackPawn8.row = blackPawn8.row*40;
        //blackPawn8.column = blackPawn8.column*40;
        g2.drawImage(blackPawn8.image, blackPawn8.column * 40 + 8, blackPawn8.row * 40 + 8, null);
        //g2.drawRect(blackPawn8.column+9,blackPawn8.row+9,20,20);

        //PIONKI CZARNE


        if (whiteMove && !blackMove) {
            g2.drawImage(greenLight, 15, 15, null);
            g2.drawImage(redLight, 15, 365, null);
        } else if (blackMove && !whiteMove) {
            g2.drawImage(greenLight, 15, 365, null);
            g2.drawImage(redLight, 15, 15, null);
        }



        g2.setColor(blue);
        g2.setStroke(new BasicStroke(3));

        if(whiteBishop1.clicked){
            g2.drawRect(whiteBishop1.column*40+4,whiteBishop1.row*40+4,32,32);
        }else if(whiteBishop2.clicked){
            g2.drawRect(whiteBishop2.column*40+4,whiteBishop2.row*40+4,32,32);
        }else if(whiteKnight1.clicked){
            g2.drawRect(whiteKnight1.column*40+4,whiteKnight1.row*40+4,32,32);
        }else if(whiteKnight2.clicked){
            g2.drawRect(whiteKnight2.column*40+4,whiteKnight2.row*40+4,32,32);
        }else if(whiteRook1.clicked){
            g2.drawRect(whiteRook1.column*40+4,whiteRook1.row*40+4,32,32);
        }else if(whiteRook2.clicked){
            g2.drawRect(whiteRook2.column*40+4,whiteRook2.row*40+4,32,32);
        }else if(whiteKing.clicked){
            g2.drawRect(whiteKing.column*40+4,whiteKing.row*40+4,32,32);
        }else if(whiteQueen.clicked){
            g2.drawRect(whiteQueen.column*40+4,whiteQueen.row*40+4,32,32);
        }else if(whitePawn1.clicked){
            g2.drawRect(whitePawn1.column*40+4,whitePawn1.row*40+4,32,32);
        }else if(whitePawn2.clicked){
            g2.drawRect(whitePawn2.column*40+4,whitePawn2.row*40+4,32,32);
        }else if(whitePawn3.clicked){
            g2.drawRect(whitePawn3.column*40+4,whitePawn3.row*40+4,32,32);
        }else if(whitePawn4.clicked){
            g2.drawRect(whitePawn4.column*40+4,whitePawn4.row*40+4,32,32);
        }else if(whitePawn5.clicked){
            g2.drawRect(whitePawn5.column*40+4,whitePawn5.row*40+4,32,32);
        }else if(whitePawn6.clicked){
            g2.drawRect(whitePawn6.column*40+4,whitePawn6.row*40+4,32,32);
        }else if(whitePawn7.clicked){
            g2.drawRect(whitePawn7.column*40+4,whitePawn7.row*40+4,32,32);
        }else if(whitePawn8.clicked){
            g2.drawRect(whitePawn8.column*40+4,whitePawn8.row*40+4,32,32);
        }

        /*
        gameBoard[whitePawn1.row-1][whitePawn1.column-1].occupiedFieldByWHITE = true;
        gameBoard[whitePawn1.row-1][whitePawn1.column-1].occupiedFieldByBlack = false;

        gameBoard[whitePawn2.row-1][whitePawn2.column-1].occupiedFieldByWHITE = true;
        gameBoard[whitePawn2.row-1][whitePawn2.column-1].occupiedFieldByBlack = false;

        gameBoard[whitePawn3.row-1][whitePawn3.column-1].occupiedFieldByWHITE = true;
        gameBoard[whitePawn3.row-1][whitePawn3.column-1].occupiedFieldByBlack = false;

        gameBoard[whitePawn4.row-1][whitePawn4.column-1].occupiedFieldByWHITE = true;
        gameBoard[whitePawn4.row-1][whitePawn4.column-1].occupiedFieldByBlack = false;

        gameBoard[whitePawn5.row-1][whitePawn5.column-1].occupiedFieldByWHITE = true;
        gameBoard[whitePawn5.row-1][whitePawn5.column-1].occupiedFieldByBlack = false;

        gameBoard[whitePawn6.row-1][whitePawn6.column-1].occupiedFieldByWHITE = true;
        gameBoard[whitePawn6.row-1][whitePawn6.column-1].occupiedFieldByBlack = false;

        gameBoard[whitePawn7.row-1][whitePawn7.column-1].occupiedFieldByWHITE = true;
        gameBoard[whitePawn7.row-1][whitePawn7.column-1].occupiedFieldByBlack = false;

        gameBoard[whitePawn8.row-1][whitePawn8.column-1].occupiedFieldByWHITE = true;
        gameBoard[whitePawn8.row-1][whitePawn8.column-1].occupiedFieldByBlack = false;
        */


        repaint();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("MOUSE CLICK");
        clickCounter += 1;

        x = e.getX();
        y = e.getY();

        columnBoard = x / 40;
        rowBoard = y / 40;

        if(clickCounter == 1){

            rowBoardToDestroy = rowBoard;
            columnBoardToDestroy = columnBoard;

            whiteBishop1.clicked = false;
            whiteBishop2.clicked = false;
            whiteKnight1.clicked = false;
            whiteKnight2.clicked = false;
            whiteRook1.clicked = false;
            whiteRook2.clicked = false;
            whiteQueen.clicked = false;
            whiteKing.clicked = false;
            whitePawn1.clicked = false;
            whitePawn2.clicked = false;
            whitePawn3.clicked = false;
            whitePawn4.clicked = false;
            whitePawn5.clicked = false;
            whitePawn6.clicked = false;
            whitePawn7.clicked = false;
            whitePawn8.clicked = false;


            if (whiteMove && !blackMove) {
                if (rowBoard == whiteBishop1.row && columnBoard == whiteBishop1.column) {
                    whiteBishop1.clicked = true;
                } else if (rowBoard == whiteBishop2.row && columnBoard == whiteBishop2.column) {
                    whiteBishop2.clicked = true;
                } else if (rowBoard == whiteKnight1.row && columnBoard == whiteKnight1.column) {
                    whiteKnight1.clicked = true;
                } else if (rowBoard == whiteKnight2.row && columnBoard == whiteKnight2.column) {
                    whiteKnight2.clicked = true;
                } else if (rowBoard == whiteRook1.row && columnBoard == whiteRook1.column) {
                    whiteRook1.clicked = true;
                } else if (rowBoard == whiteRook2.row && columnBoard == whiteRook2.column) {
                    whiteRook2.clicked = true;
                } else if (rowBoard == whiteQueen.row && columnBoard == whiteQueen.column) {
                    whiteQueen.clicked = true;
                } else if (rowBoard == whiteKing.row && columnBoard == whiteKing.column) {
                    whiteKing.clicked = true;
                } else if (rowBoard == whitePawn1.row && columnBoard == whitePawn1.column) {
                    whitePawn1.clicked = true;
                } else if (rowBoard == whitePawn2.row && columnBoard == whitePawn2.column) {
                    whitePawn2.clicked = true;
                } else if (rowBoard == whitePawn3.row && columnBoard == whitePawn3.column) {
                    whitePawn3.clicked = true;
                } else if (rowBoard == whitePawn4.row && columnBoard == whitePawn4.column) {
                    whitePawn4.clicked = true;
                } else if (rowBoard == whitePawn8.row && columnBoard == whitePawn8.column) {
                    whitePawn8.clicked = true;
                } else if (rowBoard == whitePawn5.row && columnBoard == whitePawn5.column) {
                    whitePawn5.clicked = true;
                } else if (rowBoard == whitePawn6.row && columnBoard == whitePawn6.column) {
                    whitePawn6.clicked = true;
                } else if (rowBoard == whitePawn7.row && columnBoard == whitePawn7.column) {
                    whitePawn7.clicked = true;
                }
            }


            repaint();

        }else if(clickCounter == 2){

            if(rowBoard == rowBoardToDestroy && columnBoard == columnBoardToDestroy){
                whiteBishop1.clicked = false;
                whiteBishop2.clicked = false;
                whiteKnight1.clicked = false;
                whiteKnight2.clicked = false;
                whiteRook1.clicked = false;
                whiteRook2.clicked = false;
                whiteQueen.clicked = false;
                whiteKing.clicked = false;
                whitePawn1.clicked = false;
                whitePawn2.clicked = false;
                whitePawn3.clicked = false;
                whitePawn4.clicked = false;
                whitePawn5.clicked = false;
                whitePawn6.clicked = false;
                whitePawn7.clicked = false;
                whitePawn8.clicked = false;
                clickCounter = 0;
                repaint();
            }else{
                justMove();
                clickCounter = 0;
                if(!moveWhiteDone){
                    whiteBishop1.clicked = false;
                    whiteBishop2.clicked = false;
                    whiteKnight1.clicked = false;
                    whiteKnight2.clicked = false;
                    whiteRook1.clicked = false;
                    whiteRook2.clicked = false;
                    whiteQueen.clicked = false;
                    whiteKing.clicked = false;
                    whitePawn1.clicked = false;
                    whitePawn2.clicked = false;
                    whitePawn3.clicked = false;
                    whitePawn4.clicked = false;
                    whitePawn5.clicked = false;
                    whitePawn6.clicked = false;
                    whitePawn7.clicked = false;
                    whitePawn8.clicked = false;

                    repaint();
                }

            }


        }





        if (rowBoard >= 1 && columnBoard >= 1 && rowBoard <= 8 && columnBoard <= 8) {
            System.out.println("Identyfikator = " + gameBoard[rowBoard - 1][columnBoard - 1].identityOfSquare);
            System.out.println();
            System.out.println("Occupied field white = " + gameBoard[rowBoard - 1][columnBoard - 1].occupiedFieldByWHITE);
            System.out.println("Occupied field black = " + gameBoard[rowBoard - 1][columnBoard - 1].occupiedFieldByBlack);
            System.out.println();
            System.out.println("possible move white = " + gameBoard[rowBoard - 1][columnBoard - 1].possibleMoveWhite);
            System.out.println("possible move black = " + gameBoard[rowBoard - 1][columnBoard - 1].possibleMoveBlack);
            System.out.println();
            System.out.println("krol czarny szach = " + gameBoard[blackKing.row - 1][blackKing.column - 1].possibleMoveWhite);
            System.out.println("krol bialy szach = " + gameBoard[whiteKing.row - 1][whiteKing.column - 1].possibleMoveBlack);
            System.out.println();
            System.out.println("mozliwy ruch krola bialego = " + gameBoard[rowBoard - 1][columnBoard - 1].possibleMoveWhiteKING);
            System.out.println("mozliwy ruch krola czarnego = " + gameBoard[rowBoard - 1][columnBoard - 1].possibleMoveBlackKING);
        }



        if (x >= 50 && y >= 390 && x <= 110 && y <= 430) {

            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
            new GameStart();
            thread1.interrupt();

            thread3.interrupt();
        }



    }

    @Override
    public void mousePressed(MouseEvent e) {

        /*
        System.out.println("MOUSE PRESSED  ");

        x = e.getX();
        y = e.getY();

        //System.out.println(""+x+""+y);

        columnBoard = x / 40;
        rowBoard = y / 40;

        rowBoardToDestroy = rowBoard;
        columnBoardToDestroy = columnBoard;

        System.out.println(rowBoard + " " + columnBoard);

        whiteBishop1.clicked = false;
        whiteBishop2.clicked = false;
        whiteKnight1.clicked = false;
        whiteKnight2.clicked = false;
        whiteRook1.clicked = false;
        whiteRook2.clicked = false;
        whiteQueen.clicked = false;
        whiteKing.clicked = false;
        whitePawn1.clicked = false;
        whitePawn2.clicked = false;
        whitePawn3.clicked = false;
        whitePawn4.clicked = false;
        whitePawn5.clicked = false;
        whitePawn6.clicked = false;
        whitePawn7.clicked = false;
        whitePawn8.clicked = false;


        if (whiteMove && !blackMove) {
            if (rowBoard == whiteBishop1.row && columnBoard == whiteBishop1.column) {
                whiteBishop1.clicked = true;
            } else if (rowBoard == whiteBishop2.row && columnBoard == whiteBishop2.column) {
                whiteBishop2.clicked = true;
            } else if (rowBoard == whiteKnight1.row && columnBoard == whiteKnight1.column) {
                whiteKnight1.clicked = true;
            } else if (rowBoard == whiteKnight2.row && columnBoard == whiteKnight2.column) {
                whiteKnight2.clicked = true;
            } else if (rowBoard == whiteRook1.row && columnBoard == whiteRook1.column) {
                whiteRook1.clicked = true;
            } else if (rowBoard == whiteRook2.row && columnBoard == whiteRook2.column) {
                whiteRook2.clicked = true;
            } else if (rowBoard == whiteQueen.row && columnBoard == whiteQueen.column) {
                whiteQueen.clicked = true;
            } else if (rowBoard == whiteKing.row && columnBoard == whiteKing.column) {
                whiteKing.clicked = true;
            } else if (rowBoard == whitePawn1.row && columnBoard == whitePawn1.column) {
                whitePawn1.clicked = true;
            } else if (rowBoard == whitePawn2.row && columnBoard == whitePawn2.column) {
                whitePawn2.clicked = true;
            } else if (rowBoard == whitePawn3.row && columnBoard == whitePawn3.column) {
                whitePawn3.clicked = true;
            } else if (rowBoard == whitePawn4.row && columnBoard == whitePawn4.column) {
                whitePawn4.clicked = true;
            } else if (rowBoard == whitePawn8.row && columnBoard == whitePawn8.column) {
                whitePawn8.clicked = true;
            } else if (rowBoard == whitePawn5.row && columnBoard == whitePawn5.column) {
                whitePawn5.clicked = true;
            } else if (rowBoard == whitePawn6.row && columnBoard == whitePawn6.column) {
                whitePawn6.clicked = true;
            } else if (rowBoard == whitePawn7.row && columnBoard == whitePawn7.column) {
                whitePawn7.clicked = true;
            }

        }


        if(blackMove && whiteMove == false)
        {
            if(rowBoard == blackBishop1.row && columnBoard == blackBishop1.column ){
                blackBishop1.clicked = true;
                blackBishop2.clicked = false;
                blackKnight1.clicked = false;
                blackKnight2.clicked = false;
                blackRook1.clicked = false;
                blackRook2.clicked = false;
                blackQueen.clicked = false;
                blackKing.clicked = false;
                blackPawn1.clicked = false;
                blackPawn2.clicked = false;
                blackPawn3.clicked = false;
                blackPawn4.clicked = false;
                blackPawn5.clicked = false;
                blackPawn6.clicked = false;
                blackPawn7.clicked = false;
                blackPawn8.clicked = false;
            }else if(rowBoard == blackBishop2.row && columnBoard == blackBishop2.column){
                blackBishop1.clicked = false;
                blackBishop2.clicked = true;
                blackKnight1.clicked = false;
                blackKnight2.clicked = false;
                blackRook1.clicked = false;
                blackRook2.clicked = false;
                blackQueen.clicked = false;
                blackKing.clicked = false;
                blackPawn1.clicked = false;
                blackPawn2.clicked = false;
                blackPawn3.clicked = false;
                blackPawn4.clicked = false;
                blackPawn5.clicked = false;
                blackPawn6.clicked = false;
                blackPawn7.clicked = false;
                blackPawn8.clicked = false;
            }else if(rowBoard == blackKnight1.row && columnBoard == blackKnight1.column){
                blackBishop1.clicked = false;
                blackBishop2.clicked = false;
                blackKnight1.clicked = true;
                blackKnight2.clicked = false;
                blackRook1.clicked = false;
                blackRook2.clicked = false;
                blackQueen.clicked = false;
                blackKing.clicked = false;
                blackPawn1.clicked = false;
                blackPawn2.clicked = false;
                blackPawn3.clicked = false;
                blackPawn4.clicked = false;
                blackPawn5.clicked = false;
                blackPawn6.clicked = false;
                blackPawn7.clicked = false;
                blackPawn8.clicked = false;
            }else if(rowBoard == blackKnight2.row && columnBoard == blackKnight2.column){
                blackBishop1.clicked = false;
                blackBishop2.clicked = false;
                blackKnight1.clicked = false;
                blackKnight2.clicked = true;
                blackRook1.clicked = false;
                blackRook2.clicked = false;
                blackQueen.clicked = false;
                blackKing.clicked = false;
                blackPawn1.clicked = false;
                blackPawn2.clicked = false;
                blackPawn3.clicked = false;
                blackPawn4.clicked = false;
                blackPawn5.clicked = false;
                blackPawn6.clicked = false;
                blackPawn7.clicked = false;
                blackPawn8.clicked = false;
            }else if(rowBoard == blackRook1.row && columnBoard == blackRook1.column){
                blackBishop1.clicked = false;
                blackBishop2.clicked = false;
                blackKnight1.clicked = false;
                blackKnight2.clicked = false;
                blackRook1.clicked = true;
                blackRook2.clicked = false;
                blackQueen.clicked = false;
                blackKing.clicked = false;
                blackPawn1.clicked = false;
                blackPawn2.clicked = false;
                blackPawn3.clicked = false;
                blackPawn4.clicked = false;
                blackPawn5.clicked = false;
                blackPawn6.clicked = false;
                blackPawn7.clicked = false;
                blackPawn8.clicked = false;
            }else if(rowBoard == blackRook2.row && columnBoard == blackRook2.column){
                blackBishop1.clicked = false;
                blackBishop2.clicked = false;
                blackKnight1.clicked = false;
                blackKnight2.clicked = false;
                blackRook1.clicked = false;
                blackRook2.clicked = true;
                blackQueen.clicked = false;
                blackKing.clicked = false;
                blackPawn1.clicked = false;
                blackPawn2.clicked = false;
                blackPawn3.clicked = false;
                blackPawn4.clicked = false;
                blackPawn5.clicked = false;
                blackPawn6.clicked = false;
                blackPawn7.clicked = false;
                blackPawn8.clicked = false;
            }else if(rowBoard == blackQueen.row && columnBoard == blackQueen.column){
                blackBishop1.clicked = false;
                blackBishop2.clicked = false;
                blackKnight1.clicked = false;
                blackKnight2.clicked = false;
                blackRook1.clicked = false;
                blackRook2.clicked = false;
                blackQueen.clicked = true;
                blackKing.clicked = false;
                blackPawn1.clicked = false;
                blackPawn2.clicked = false;
                blackPawn3.clicked = false;
                blackPawn4.clicked = false;
                blackPawn5.clicked = false;
                blackPawn6.clicked = false;
                blackPawn7.clicked = false;
                blackPawn8.clicked = false;
            }else if(rowBoard == blackKing.row && columnBoard == blackKing.column){
                blackBishop1.clicked = false;
                blackBishop2.clicked = false;
                blackKnight1.clicked = false;
                blackKnight2.clicked = false;
                blackRook1.clicked = false;
                blackRook2.clicked = false;
                blackQueen.clicked = false;
                blackKing.clicked = true;
                blackPawn1.clicked = false;
                blackPawn2.clicked = false;
                blackPawn3.clicked = false;
                blackPawn4.clicked = false;
                blackPawn5.clicked = false;
                blackPawn6.clicked = false;
                blackPawn7.clicked = false;
                blackPawn8.clicked = false;
            }else if(rowBoard == blackPawn1.row && columnBoard == blackPawn1.column){
                blackBishop1.clicked = false;
                blackBishop2.clicked = false;
                blackKnight1.clicked = false;
                blackKnight2.clicked = false;
                blackRook1.clicked = false;
                blackRook2.clicked = false;
                blackQueen.clicked = false;
                blackKing.clicked = false;
                blackPawn1.clicked = true;
                blackPawn2.clicked = false;
                blackPawn3.clicked = false;
                blackPawn4.clicked = false;
                blackPawn5.clicked = false;
                blackPawn6.clicked = false;
                blackPawn7.clicked = false;
                blackPawn8.clicked = false;
            }else if(rowBoard == blackPawn2.row && columnBoard == blackPawn2.column){
                blackBishop1.clicked = false;
                blackBishop2.clicked = false;
                blackKnight1.clicked = false;
                blackKnight2.clicked = false;
                blackRook1.clicked = false;
                blackRook2.clicked = false;
                blackQueen.clicked = false;
                blackKing.clicked = false;
                blackPawn1.clicked = false;
                blackPawn2.clicked = true;
                blackPawn3.clicked = false;
                blackPawn4.clicked = false;
                blackPawn5.clicked = false;
                blackPawn6.clicked = false;
                blackPawn7.clicked = false;
                blackPawn8.clicked = false;
            }else if(rowBoard == blackPawn3.row && columnBoard == blackPawn3.column){
                blackBishop1.clicked = false;
                blackBishop2.clicked = false;
                blackKnight1.clicked = false;
                blackKnight2.clicked = false;
                blackRook1.clicked = false;
                blackRook2.clicked = false;
                blackQueen.clicked = false;
                blackKing.clicked = false;
                blackPawn1.clicked = false;
                blackPawn2.clicked = false;
                blackPawn3.clicked = true;
                blackPawn4.clicked = false;
                blackPawn5.clicked = false;
                blackPawn6.clicked = false;
                blackPawn7.clicked = false;
                blackPawn8.clicked = false;
            }else if(rowBoard == blackPawn4.row && columnBoard == blackPawn4.column){
                blackBishop1.clicked = false;
                blackBishop2.clicked = false;
                blackKnight1.clicked = false;
                blackKnight2.clicked = false;
                blackRook1.clicked = false;
                blackRook2.clicked = false;
                blackQueen.clicked = false;
                blackKing.clicked = false;
                blackPawn1.clicked = false;
                blackPawn2.clicked = false;
                blackPawn3.clicked = false;
                blackPawn4.clicked = true;
                blackPawn5.clicked = false;
                blackPawn6.clicked = false;
                blackPawn7.clicked = false;
                blackPawn8.clicked = false;
            }else if(rowBoard == blackPawn8.row && columnBoard == blackPawn8.column){
                blackBishop1.clicked = false;
                blackBishop2.clicked = false;
                blackKnight1.clicked = false;
                blackKnight2.clicked = false;
                blackRook1.clicked = false;
                blackRook2.clicked = false;
                blackQueen.clicked = false;
                blackKing.clicked = false;
                blackPawn1.clicked = false;
                blackPawn2.clicked = false;
                blackPawn3.clicked = false;
                blackPawn4.clicked = false;
                blackPawn5.clicked = false;
                blackPawn6.clicked = false;
                blackPawn7.clicked = false;
                blackPawn8.clicked = true;
            }else if(rowBoard == blackPawn5.row && columnBoard == blackPawn5.column){
                blackBishop1.clicked = false;
                blackBishop2.clicked = false;
                blackKnight1.clicked = false;
                blackKnight2.clicked = false;
                blackRook1.clicked = false;
                blackRook2.clicked = false;
                blackQueen.clicked = false;
                blackKing.clicked = false;
                blackPawn1.clicked = false;
                blackPawn2.clicked = false;
                blackPawn3.clicked = false;
                blackPawn4.clicked = false;
                blackPawn5.clicked = true;
                blackPawn6.clicked = false;
                blackPawn7.clicked = false;
                blackPawn8.clicked = false;
            }else if(rowBoard == blackPawn6.row && columnBoard == blackPawn6.column){
                blackBishop1.clicked = false;
                blackBishop2.clicked = false;
                blackKnight1.clicked = false;
                blackKnight2.clicked = false;
                blackRook1.clicked = false;
                blackRook2.clicked = false;
                blackQueen.clicked = false;
                blackKing.clicked = false;
                blackPawn1.clicked = false;
                blackPawn2.clicked = false;
                blackPawn3.clicked = false;
                blackPawn4.clicked = false;
                blackPawn5.clicked = false;
                blackPawn6.clicked = true;
                blackPawn7.clicked = false;
                blackPawn8.clicked = false;
            }else if(rowBoard == blackPawn7.row && columnBoard == blackPawn7.column){
                blackBishop1.clicked = false;
                blackBishop2.clicked = false;
                blackKnight1.clicked = false;
                blackKnight2.clicked = false;
                blackRook1.clicked = false;
                blackRook2.clicked = false;
                blackQueen.clicked = false;
                blackKing.clicked = false;
                blackPawn1.clicked = false;
                blackPawn2.clicked = false;
                blackPawn3.clicked = false;
                blackPawn4.clicked = false;
                blackPawn5.clicked = false;
                blackPawn6.clicked = false;
                blackPawn7.clicked = true;
                blackPawn8.clicked = false;
            }
        }
        */


    }

    @Override
    public void mouseReleased(MouseEvent e) {

        /*
        System.out.println("MOUSE RELEASED  ");

        x = e.getX();
        y = e.getY();

        columnBoard = x / 40;
        rowBoard = y / 40;

        justMove();



        //Poruszanie się czarne

            if(blackBishop1.clicked){
                if(rowBoard > blackBishop1.row && columnBoard > blackBishop1.column ) {
                    //move down-right bishop
                    shapeDiff = rowBoard - blackBishop1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[blackBishop1.row+i-1][blackBishop1.column+i-1].occupiedFieldByWHITE == true || gameBoard[blackBishop1.row+i-1][blackBishop1.column+i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackBishop1.row][blackBishop1.column].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;



                    if(rowBoard - blackBishop1.row == columnBoard - blackBishop1.column){
                        blackBishop1.row = rowBoard;
                        blackBishop1.column = columnBoard;
                        blackBishop1.clicked = false;
                        moveBlackDone = true;
                        repaint();
                    }

                }else if(rowBoard < blackBishop1.row && columnBoard > blackBishop1.column){
                    //move up-right bishop

                    shapeDiff = -rowBoard + blackBishop1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[blackBishop1.row-i-1][blackBishop1.column+i-1].occupiedFieldByWHITE == true || gameBoard[blackBishop1.row-i-1][blackBishop1.column+i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackBishop1.row-2][blackBishop1.column].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;



                    if(-rowBoard + blackBishop1.row == columnBoard - blackBishop1.column){
                        blackBishop1.row = rowBoard;
                        blackBishop1.column = columnBoard;
                        blackBishop1.clicked = false;
                        moveBlackDone = true;
                        repaint();
                    }


                }else if(rowBoard < blackBishop1.row && columnBoard < blackBishop1.column){
                    //move up-left bishop

                    shapeDiff = -rowBoard + blackBishop1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[blackBishop1.row-i-1][blackBishop1.column-i-1].occupiedFieldByWHITE == true || gameBoard[blackBishop1.row-i-1][blackBishop1.column-i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackBishop1.row-2][blackBishop1.column-2].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;



                    if(-rowBoard + blackBishop1.row == -columnBoard + blackBishop1.column){
                        blackBishop1.row = rowBoard;
                        blackBishop1.column = columnBoard;
                        blackBishop1.clicked = false;
                        moveBlackDone = true;
                        repaint();
                    }


                }else if(rowBoard > blackBishop1.row && columnBoard < blackBishop1.column){
                    //move down-left bishop

                    shapeDiff = rowBoard - blackBishop1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[blackBishop1.row+i-1][blackBishop1.column-i-1].occupiedFieldByWHITE == true || gameBoard[blackBishop1.row+i-1][blackBishop1.column-i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackBishop1.row][blackBishop1.column-2].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;



                    if(rowBoard - blackBishop1.row == -columnBoard + blackBishop1.column){
                        blackBishop1.row = rowBoard;
                        blackBishop1.column = columnBoard;
                        blackBishop1.clicked = false;
                        moveBlackDone = true;
                        repaint();
                    }

                }
            }else if(blackBishop2.clicked){
                if(rowBoard > blackBishop2.row && columnBoard > blackBishop2.column ) {
                    //move down-right bishop
                    shapeDiff = rowBoard - blackBishop2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[blackBishop2.row+i-1][blackBishop2.column+i-1].occupiedFieldByWHITE == true || gameBoard[blackBishop2.row+i-1][blackBishop2.column+i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackBishop2.row][blackBishop2.column].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;



                    if(rowBoard - blackBishop2.row == columnBoard - blackBishop2.column){
                        blackBishop2.row = rowBoard;
                        blackBishop2.column = columnBoard;
                        blackBishop2.clicked = false;
                        moveBlackDone = true;
                        repaint();
                    }

                }else if(rowBoard < blackBishop2.row && columnBoard > blackBishop2.column){
                    //move up-right bishop

                    shapeDiff = -rowBoard + blackBishop2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[blackBishop2.row-i-1][blackBishop2.column+i-1].occupiedFieldByWHITE == true || gameBoard[blackBishop2.row-i-1][blackBishop2.column+i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackBishop2.row-2][blackBishop2.column].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;



                    if(-rowBoard + blackBishop2.row == columnBoard - blackBishop2.column){
                        blackBishop2.row = rowBoard;
                        blackBishop2.column = columnBoard;
                        blackBishop2.clicked = false;
                        moveBlackDone = true;
                        repaint();
                    }


                }else if(rowBoard < blackBishop2.row && columnBoard < blackBishop2.column){
                    //move up-left bishop

                    shapeDiff = -rowBoard + blackBishop2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[blackBishop2.row-i-1][blackBishop2.column-i-1].occupiedFieldByWHITE == true || gameBoard[blackBishop2.row-i-1][blackBishop2.column-i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackBishop2.row-2][blackBishop2.column-2].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;



                    if(-rowBoard + blackBishop2.row == -columnBoard + blackBishop2.column){
                        blackBishop2.row = rowBoard;
                        blackBishop2.column = columnBoard;
                        blackBishop2.clicked = false;
                        moveBlackDone = true;
                        repaint();
                    }


                }else if(rowBoard > blackBishop2.row && columnBoard < blackBishop2.column){
                    //move down-left bishop

                    shapeDiff = rowBoard - blackBishop2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[blackBishop2.row+i-1][blackBishop2.column-i-1].occupiedFieldByWHITE == true || gameBoard[blackBishop2.row+i-1][blackBishop2.column-i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackBishop2.row][blackBishop2.column-2].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;



                    if(rowBoard - blackBishop2.row == -columnBoard + blackBishop2.column){
                        blackBishop2.row = rowBoard;
                        blackBishop2.column = columnBoard;
                        blackBishop2.clicked = false;
                        moveBlackDone = true;
                        repaint();
                    }

                }
            }else if(blackKnight1.clicked){
                if(((rowBoard == blackKnight1.row-2 && columnBoard == blackKnight1.column-1) ||
                        (rowBoard == blackKnight1.row-1 && columnBoard == blackKnight1.column-2) ||
                        (rowBoard == blackKnight1.row-2 && columnBoard == blackKnight1.column+1) ||
                        (rowBoard == blackKnight1.row-1 && columnBoard == blackKnight1.column+2) ||
                        (rowBoard == blackKnight1.row+1 && columnBoard == blackKnight1.column+2) ||
                        (rowBoard == blackKnight1.row+2 && columnBoard == blackKnight1.column+1) ||
                        (rowBoard == blackKnight1.row+2 && columnBoard == blackKnight1.column-1) ||
                        (rowBoard == blackKnight1.row+1 && columnBoard == blackKnight1.column-2))
                        && (gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == false && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == false || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == false)

                ) {
                    blackKnight1.row = rowBoard;
                    blackKnight1.column = columnBoard;
                    blackKnight1.clicked = false;
                    moveBlackDone = true;
                    repaint();
                }
            }else if(blackKnight2.clicked){
                if(((rowBoard == blackKnight2.row-2 && columnBoard == blackKnight2.column-1) ||
                        (rowBoard == blackKnight2.row-1 && columnBoard == blackKnight2.column-2) ||
                        (rowBoard == blackKnight2.row-2 && columnBoard == blackKnight2.column+1) ||
                        (rowBoard == blackKnight2.row-1 && columnBoard == blackKnight2.column+2) ||
                        (rowBoard == blackKnight2.row+1 && columnBoard == blackKnight2.column+2) ||
                        (rowBoard == blackKnight2.row+2 && columnBoard == blackKnight2.column+1) ||
                        (rowBoard == blackKnight2.row+2 && columnBoard == blackKnight2.column-1) ||
                        (rowBoard == blackKnight2.row+1 && columnBoard == blackKnight2.column-2))
                        && (gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == false && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == false || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == false)

                ) {
                    blackKnight2.row = rowBoard;
                    blackKnight2.column = columnBoard;
                    blackKnight2.clicked = false;
                    moveBlackDone = true;
                    repaint();
                }
            }else if(blackRook1.clicked){
                if(blackRook1.row < rowBoard && blackRook1.column == columnBoard)
                {
                    //move down Rook
                    shapeDiff = rowBoard - blackRook1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[blackRook1.row+i-1][columnBoard-1].occupiedFieldByWHITE == true || gameBoard[blackRook1.row+i-1][columnBoard-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackRook1.row][blackRook1.column-1].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;

                    blackRook1.row = rowBoard;
                    blackRook1.column = columnBoard;
                    blackRook1.clicked = false;
                    moveBlackDone = true;
                    blackRook1CastlingAccess = false;
                    repaint();

                }else if(blackRook1.row == rowBoard && blackRook1.column < columnBoard)
                {
                    //move right Rook
                    shapeDiff = columnBoard - blackRook1.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[rowBoard-1][blackRook1.column+i-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][blackRook1.column+i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackRook1.row-1][blackRook1.column].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;

                    blackRook1.row = rowBoard;
                    blackRook1.column = columnBoard;
                    blackRook1.clicked = false;
                    moveBlackDone = true;
                    blackRook1CastlingAccess = false;
                    repaint();

                }else if(blackRook1.row > rowBoard && blackRook1.column == columnBoard)
                {
                    //move up Rook
                    shapeDiff = -rowBoard + blackRook1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[blackRook1.row-i-1][columnBoard-1].occupiedFieldByWHITE == true || gameBoard[blackRook1.row-i-1][columnBoard-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackRook1.row-2][blackRook1.column-1].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;

                    blackRook1.row = rowBoard;
                    blackRook1.column = columnBoard;
                    blackRook1.clicked = false;
                    moveBlackDone = true;
                    blackRook1CastlingAccess = false;
                    repaint();

                }else if(blackRook1.row == rowBoard && blackRook1.column > columnBoard)
                {
                    //move left Rook
                    shapeDiff = -columnBoard + blackRook1.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[rowBoard-1][blackRook1.column-i-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][blackRook1.column-i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackRook1.row-1][blackRook1.column-2].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;

                    blackRook1.row = rowBoard;
                    blackRook1.column = columnBoard;
                    blackRook1.clicked = false;
                    moveBlackDone = true;
                    blackRook1CastlingAccess = false;
                    repaint();

                }
            }else if(blackRook2.clicked) {
                if(blackRook2.row < rowBoard && blackRook2.column == columnBoard)
                {
                    //move down Rook
                    shapeDiff = rowBoard - blackRook2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[blackRook2.row+i-1][columnBoard-1].occupiedFieldByWHITE == true || gameBoard[blackRook2.row+i-1][columnBoard-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackRook2.row][blackRook2.column-1].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;

                    blackRook2.row = rowBoard;
                    blackRook2.column = columnBoard;
                    blackRook2.clicked = false;
                    moveBlackDone = true;
                    blackRook2CastlingAccess = false;
                    repaint();

                }else if(blackRook2.row == rowBoard && blackRook2.column < columnBoard)
                {
                    //move right Rook
                    shapeDiff = columnBoard - blackRook2.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[rowBoard-1][blackRook2.column+i-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][blackRook2.column+i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackRook2.row-1][blackRook2.column].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;

                    blackRook2.row = rowBoard;
                    blackRook2.column = columnBoard;
                    blackRook2.clicked = false;
                    moveBlackDone = true;
                    blackRook2CastlingAccess = false;
                    repaint();

                }else if(blackRook2.row > rowBoard && blackRook2.column == columnBoard)
                {
                    //move up Rook
                    shapeDiff = -rowBoard + blackRook2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[blackRook2.row-i-1][columnBoard-1].occupiedFieldByWHITE == true || gameBoard[blackRook2.row-i-1][columnBoard-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackRook2.row-2][blackRook2.column-1].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;

                    blackRook2.row = rowBoard;
                    blackRook2.column = columnBoard;
                    blackRook2.clicked = false;
                    moveBlackDone = true;
                    blackRook2CastlingAccess = false;
                    repaint();

                }else if(blackRook2.row == rowBoard && blackRook2.column > columnBoard)
                {
                    //move left Rook
                    shapeDiff = -columnBoard + blackRook2.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[rowBoard-1][blackRook2.column-i-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][blackRook2.column-i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackRook2.row-1][blackRook2.column-2].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;

                    blackRook2.row = rowBoard;
                    blackRook2.column = columnBoard;
                    blackRook2.clicked = false;
                    moveBlackDone = true;
                    blackRook2CastlingAccess = false;
                    repaint();

                }
            }else if(blackQueen.clicked){
                if(rowBoard > blackQueen.row && columnBoard > blackQueen.column ) {
                    //move down-right bishop
                    shapeDiff = rowBoard - blackQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[blackQueen.row+i-1][blackQueen.column+i-1].occupiedFieldByWHITE == true || gameBoard[blackQueen.row+i-1][blackQueen.column+i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackQueen.row][blackQueen.column].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;



                    if(rowBoard - blackQueen.row == columnBoard - blackQueen.column){
                        blackQueen.row = rowBoard;
                        blackQueen.column = columnBoard;
                        blackQueen.clicked = false;
                        moveBlackDone = true;
                        repaint();
                    }

                }else if(rowBoard < blackQueen.row && columnBoard > blackQueen.column){
                    //move up-right bishop

                    shapeDiff = -rowBoard + blackQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[blackQueen.row-i-1][blackQueen.column+i-1].occupiedFieldByWHITE == true || gameBoard[blackQueen.row-i-1][blackQueen.column+i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackQueen.row-2][blackQueen.column].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;



                    if(-rowBoard + blackQueen.row == columnBoard - blackQueen.column){
                        blackQueen.row = rowBoard;
                        blackQueen.column = columnBoard;
                        blackQueen.clicked = false;
                        moveBlackDone = true;
                        repaint();
                    }


                }else if(rowBoard < blackQueen.row && columnBoard < blackQueen.column){
                    //move up-left bishop

                    shapeDiff = -rowBoard + blackQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[blackQueen.row-i-1][blackQueen.column-i-1].occupiedFieldByWHITE == true || gameBoard[blackQueen.row-i-1][blackQueen.column-i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackQueen.row-2][blackQueen.column-2].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;



                    if(-rowBoard + blackQueen.row == -columnBoard + blackQueen.column){
                        blackQueen.row = rowBoard;
                        blackQueen.column = columnBoard;
                        blackQueen.clicked = false;
                        moveBlackDone = true;
                        repaint();
                    }


                }else if(rowBoard > blackQueen.row && columnBoard < blackQueen.column){
                    //move down-left bishop

                    shapeDiff = rowBoard - blackQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[blackQueen.row+i-1][blackQueen.column-i-1].occupiedFieldByWHITE == true || gameBoard[blackQueen.row+i-1][blackQueen.column-i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackQueen.row][blackQueen.column-2].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;



                    if(rowBoard - blackQueen.row == -columnBoard + blackQueen.column){
                        blackQueen.row = rowBoard;
                        blackQueen.column = columnBoard;
                        blackQueen.clicked = false;
                        moveBlackDone = true;
                        repaint();
                    }

                }else if(blackQueen.row < rowBoard && blackQueen.column == columnBoard)
                {
                    //move down Rook
                    shapeDiff = rowBoard - blackQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[blackQueen.row+i-1][columnBoard-1].occupiedFieldByWHITE == true || gameBoard[blackQueen.row+i-1][columnBoard-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackQueen.row][blackQueen.column-1].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;

                    blackQueen.row = rowBoard;
                    blackQueen.column = columnBoard;
                    blackQueen.clicked = false;
                    moveBlackDone = true;
                    repaint();

                }else if(blackQueen.row == rowBoard && blackQueen.column < columnBoard)
                {
                    //move right Rook
                    shapeDiff = columnBoard - blackQueen.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[rowBoard-1][blackQueen.column+i-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][blackQueen.column+i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackQueen.row-1][blackQueen.column].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;

                    blackQueen.row = rowBoard;
                    blackQueen.column = columnBoard;
                    blackQueen.clicked = false;
                    moveBlackDone = true;
                    repaint();

                }else if(blackQueen.row > rowBoard && blackQueen.column == columnBoard)
                {
                    //move up Rook
                    shapeDiff = -rowBoard + blackQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[blackQueen.row-i-1][columnBoard-1].occupiedFieldByWHITE == true || gameBoard[blackQueen.row-i-1][columnBoard-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackQueen.row-2][blackQueen.column-1].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;

                    blackQueen.row = rowBoard;
                    blackQueen.column = columnBoard;
                    blackQueen.clicked = false;
                    moveBlackDone = true;
                    repaint();

                }else if(blackQueen.row == rowBoard && blackQueen.column > columnBoard)
                {
                    //move left Rook
                    shapeDiff = -columnBoard + blackQueen.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[rowBoard-1][blackQueen.column-i-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][blackQueen.column-i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[blackQueen.row-1][blackQueen.column-2].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) return;

                    blackQueen.row = rowBoard;
                    blackQueen.column = columnBoard;
                    blackQueen.clicked = false;
                    moveBlackDone = true;
                    repaint();

                }
            }else if(blackKing.clicked) {
                if (((rowBoard == blackKing.row-1 && columnBoard == blackKing.column-1 ) ||
                        (rowBoard == blackKing.row && columnBoard == blackKing.column-1) ||
                        (rowBoard == blackKing.row-1 && columnBoard == blackKing.column) ||
                        (rowBoard == blackKing.row-1 && columnBoard == blackKing.column+1) ||
                        (rowBoard == blackKing.row && columnBoard == blackKing.column+1) ||
                        (rowBoard == blackKing.row+1 && columnBoard == blackKing.column+1) ||
                        (rowBoard == blackKing.row+1 && columnBoard == blackKing.column) ||
                        (rowBoard == blackKing.row+1 && columnBoard == blackKing.column-1)) && (gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == false ||gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == false && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == false)

                ){
                    blackKing.row = rowBoard;
                    blackKing.column = columnBoard;
                    blackKing.clicked = false;
                    blackKingCastlingAccess = false;
                    moveBlackDone = true;
                    repaint();
                }else if(rowBoard == blackKing.row && columnBoard == blackKing.column+2
                        && gameBoard[blackKing.row-1][blackKing.column].occupiedFieldByWHITE == false
                        && gameBoard[blackKing.row-1][blackKing.column].occupiedFieldByBlack == false
                        && gameBoard[blackKing.row-1][blackKing.column+1].occupiedFieldByWHITE == false
                        && gameBoard[blackKing.row-1][blackKing.column+1].occupiedFieldByBlack == false
                        && blackKingCastlingAccess == true
                        && blackRook2CastlingAccess == true)
                {
                    blackKing.row = rowBoard;
                    blackKing.column = columnBoard;
                    blackRook2.row = 8;
                    blackRook2.column = 6;
                    gameBoard[7][7].occupiedFieldByWHITE = false;
                    gameBoard[7][7].occupiedFieldByBlack = false;
                    gameBoard[7][5].occupiedFieldByWHITE = false;
                    gameBoard[7][5].occupiedFieldByBlack = true;
                    blackKing.clicked = false;
                    moveBlackDone = true;
                    blackKingCastlingAccess = false;
                    blackRook2CastlingAccess = false;
                    repaint();

                }else if(rowBoard == blackKing.row && columnBoard == blackKing.column-2
                                && gameBoard[blackKing.row-1][blackKing.column-2].occupiedFieldByWHITE == false
                                && gameBoard[blackKing.row-1][blackKing.column-2].occupiedFieldByBlack == false
                                && gameBoard[blackKing.row-1][blackKing.column-3].occupiedFieldByWHITE == false
                                && gameBoard[blackKing.row-1][blackKing.column-3].occupiedFieldByBlack == false
                                && gameBoard[blackKing.row-1][blackKing.column-4].occupiedFieldByWHITE == false
                                && gameBoard[blackKing.row-1][blackKing.column-4].occupiedFieldByBlack == false
                                && blackKingCastlingAccess == true
                                && blackRook1CastlingAccess == true
                ){
                    blackKing.row = rowBoard;
                    blackKing.column = columnBoard;
                    blackRook1.row = 8;
                    blackRook1.column = 4;
                    gameBoard[7][0].occupiedFieldByWHITE = false;
                    gameBoard[7][0].occupiedFieldByBlack = false;
                    gameBoard[7][2].occupiedFieldByWHITE = false;
                    gameBoard[7][2].occupiedFieldByBlack = true;
                    blackKing.clicked = false;
                    moveBlackDone = true;
                    blackKingCastlingAccess = false;
                    blackRook1CastlingAccess = false;
                    repaint();
                }

            }else if(blackPawn1.clicked){
                if((rowBoard == blackPawn1.row - 2 && blackPawn1.row == 7 && blackPawn1.column == 1 && columnBoard == blackPawn1.column && gameBoard[blackPawn1.row-2][blackPawn1.column-1].occupiedFieldByBlack == false && gameBoard[blackPawn1.row-2][blackPawn1.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == blackPawn1.row - 1 && columnBoard == blackPawn1.column && gameBoard[blackPawn1.row-2][blackPawn1.column-1].occupiedFieldByWHITE == false && gameBoard[blackPawn1.row-2][blackPawn1.column-1].occupiedFieldByBlack == false) ||
                        (rowBoard == blackPawn1.row - 1 && columnBoard != blackPawn1.column && blackPawn1.column != 8 && (gameBoard[blackPawn1.row-2][blackPawn1.column].occupiedFieldByWHITE == true || gameBoard[blackPawn1.row-2][blackPawn1.column-2].occupiedFieldByWHITE == true) ) ||
                        (rowBoard == blackPawn1.row - 1 && columnBoard != blackPawn1.column && blackPawn1.column == 8 && gameBoard[blackPawn1.row-2][blackPawn1.column-2].occupiedFieldByWHITE == true)
                ) {

                    blackPawn1.row = rowBoard;
                    blackPawn1.column = columnBoard;
                    blackPawn1.clicked = false;
                    moveBlackDone = true;
                    repaint();
                }
            }else if(blackPawn2.clicked){
                if((rowBoard == blackPawn2.row - 2 && blackPawn2.row == 7 && blackPawn2.column == 2 && columnBoard == blackPawn2.column && gameBoard[blackPawn2.row-2][blackPawn2.column-1].occupiedFieldByBlack == false && gameBoard[blackPawn2.row-2][blackPawn2.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == blackPawn2.row - 1 && columnBoard == blackPawn2.column && gameBoard[blackPawn2.row-2][blackPawn2.column-1].occupiedFieldByWHITE == false && gameBoard[blackPawn2.row-2][blackPawn2.column-1].occupiedFieldByBlack == false) ||
                        (rowBoard == blackPawn2.row - 1 && columnBoard != blackPawn2.column && blackPawn2.column != 8 && (gameBoard[blackPawn2.row-2][blackPawn2.column].occupiedFieldByWHITE == true || gameBoard[blackPawn2.row-2][blackPawn2.column-2].occupiedFieldByWHITE == true) ) ||
                        (rowBoard == blackPawn2.row - 1 && columnBoard != blackPawn2.column && blackPawn2.column == 8 && gameBoard[blackPawn2.row-2][blackPawn2.column-2].occupiedFieldByWHITE == true)
                ) {

                    blackPawn2.row = rowBoard;
                    blackPawn2.column = columnBoard;
                    blackPawn2.clicked = false;
                    moveBlackDone = true;
                    repaint();}
            }else if(blackPawn3.clicked){
                if((rowBoard == blackPawn3.row - 2 && blackPawn3.row == 7 && blackPawn3.column == 3 && columnBoard == blackPawn3.column && gameBoard[blackPawn3.row-2][blackPawn3.column-1].occupiedFieldByBlack == false && gameBoard[blackPawn3.row-2][blackPawn3.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == blackPawn3.row - 1 && columnBoard == blackPawn3.column && gameBoard[blackPawn3.row-2][blackPawn3.column-1].occupiedFieldByWHITE == false && gameBoard[blackPawn3.row-2][blackPawn3.column-1].occupiedFieldByBlack == false) ||
                        (rowBoard == blackPawn3.row - 1 && columnBoard != blackPawn3.column && blackPawn3.column != 8 && (gameBoard[blackPawn3.row-2][blackPawn3.column].occupiedFieldByWHITE == true || gameBoard[blackPawn3.row-2][blackPawn3.column-2].occupiedFieldByWHITE == true) ) ||
                        (rowBoard == blackPawn3.row - 1 && columnBoard != blackPawn3.column && blackPawn3.column == 8 && gameBoard[blackPawn3.row-2][blackPawn3.column-2].occupiedFieldByWHITE == true)
                ) {

                    blackPawn3.row = rowBoard;
                    blackPawn3.column = columnBoard;
                    blackPawn3.clicked = false;
                    moveBlackDone = true;
                    repaint();
                }
            }else if(blackPawn4.clicked){
                if((rowBoard == blackPawn4.row - 2 && blackPawn4.row == 7 && blackPawn4.column == 4 && columnBoard == blackPawn4.column && gameBoard[blackPawn4.row-2][blackPawn4.column-1].occupiedFieldByBlack == false && gameBoard[blackPawn4.row-2][blackPawn4.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == blackPawn4.row - 1 && columnBoard == blackPawn4.column && gameBoard[blackPawn4.row-2][blackPawn4.column-1].occupiedFieldByWHITE == false && gameBoard[blackPawn4.row-2][blackPawn4.column-1].occupiedFieldByBlack == false) ||
                        (rowBoard == blackPawn4.row - 1 && columnBoard != blackPawn4.column && blackPawn4.column != 8 && (gameBoard[blackPawn4.row-2][blackPawn4.column].occupiedFieldByWHITE == true || gameBoard[blackPawn4.row-2][blackPawn4.column-2].occupiedFieldByWHITE == true) ) ||
                        (rowBoard == blackPawn4.row - 1 && columnBoard != blackPawn4.column && blackPawn4.column == 8 && gameBoard[blackPawn4.row-2][blackPawn4.column-2].occupiedFieldByWHITE == true)
                ) {

                    blackPawn4.row = rowBoard;
                    blackPawn4.column = columnBoard;
                    blackPawn4.clicked = false;
                    moveBlackDone = true;
                    repaint();
                }
            }else if(blackPawn5.clicked){
                if((rowBoard == blackPawn5.row - 2 && blackPawn5.row == 7 && blackPawn5.column == 5 && columnBoard == blackPawn5.column && gameBoard[blackPawn5.row-2][blackPawn5.column-1].occupiedFieldByBlack == false && gameBoard[blackPawn5.row-2][blackPawn5.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == blackPawn5.row - 1 && columnBoard == blackPawn5.column && gameBoard[blackPawn5.row-2][blackPawn5.column-1].occupiedFieldByWHITE == false && gameBoard[blackPawn5.row-2][blackPawn5.column-1].occupiedFieldByBlack == false) ||
                        (rowBoard == blackPawn5.row - 1 && columnBoard != blackPawn5.column && blackPawn5.column != 8 && (gameBoard[blackPawn5.row-2][blackPawn5.column].occupiedFieldByWHITE == true || gameBoard[blackPawn5.row-2][blackPawn5.column-2].occupiedFieldByWHITE == true) ) ||
                        (rowBoard == blackPawn5.row - 1 && columnBoard != blackPawn5.column && blackPawn5.column == 8 && gameBoard[blackPawn5.row-2][blackPawn5.column-2].occupiedFieldByWHITE == true)
                ) {

                    blackPawn5.row = rowBoard;
                    blackPawn5.column = columnBoard;
                    blackPawn5.clicked = false;
                    moveBlackDone = true;
                    repaint();
                }
            }else if(blackPawn6.clicked){
                if((rowBoard == blackPawn6.row - 2 && blackPawn6.row == 7 && blackPawn6.column == 6 && columnBoard == blackPawn6.column && gameBoard[blackPawn6.row-2][blackPawn6.column-1].occupiedFieldByBlack == false && gameBoard[blackPawn6.row-2][blackPawn6.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == blackPawn6.row - 1 && columnBoard == blackPawn6.column && gameBoard[blackPawn6.row-2][blackPawn6.column-1].occupiedFieldByWHITE == false && gameBoard[blackPawn6.row-2][blackPawn6.column-1].occupiedFieldByBlack == false) ||
                        (rowBoard == blackPawn6.row - 1 && columnBoard != blackPawn6.column && blackPawn6.column != 8 && (gameBoard[blackPawn6.row-2][blackPawn6.column].occupiedFieldByWHITE == true || gameBoard[blackPawn6.row-2][blackPawn6.column-2].occupiedFieldByWHITE == true) ) ||
                        (rowBoard == blackPawn6.row - 1 && columnBoard != blackPawn6.column && blackPawn6.column == 8 && gameBoard[blackPawn6.row-2][blackPawn6.column-2].occupiedFieldByWHITE == true)
                ) {

                    blackPawn6.row = rowBoard;
                    blackPawn6.column = columnBoard;
                    blackPawn6.clicked = false;
                    moveBlackDone = true;
                    repaint();
                }
            }else if(blackPawn7.clicked){
                if((rowBoard == blackPawn7.row - 2 && blackPawn7.row == 7 && blackPawn7.column == 7 && columnBoard == blackPawn7.column && gameBoard[blackPawn7.row-2][blackPawn7.column-1].occupiedFieldByBlack == false && gameBoard[blackPawn7.row-2][blackPawn7.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == blackPawn7.row - 1 && columnBoard == blackPawn7.column && gameBoard[blackPawn7.row-2][blackPawn7.column-1].occupiedFieldByWHITE == false && gameBoard[blackPawn7.row-2][blackPawn7.column-1].occupiedFieldByBlack == false) ||
                        (rowBoard == blackPawn7.row - 1 && columnBoard != blackPawn7.column && blackPawn7.column != 8 && (gameBoard[blackPawn7.row-2][blackPawn7.column].occupiedFieldByWHITE == true || gameBoard[blackPawn7.row-2][blackPawn7.column-2].occupiedFieldByWHITE == true) ) ||
                        (rowBoard == blackPawn7.row - 1 && columnBoard != blackPawn7.column && blackPawn7.column == 8 && gameBoard[blackPawn7.row-2][blackPawn7.column-2].occupiedFieldByWHITE == true)
                ) {

                    blackPawn7.row = rowBoard;
                    blackPawn7.column = columnBoard;
                    blackPawn7.clicked = false;
                    moveBlackDone = true;
                    repaint();
                }
            }else if(blackPawn8.clicked){
                if((rowBoard == blackPawn8.row - 2 && blackPawn8.row == 7 && blackPawn8.column == 8 && columnBoard == blackPawn8.column && gameBoard[blackPawn8.row-2][blackPawn8.column-1].occupiedFieldByBlack == false && gameBoard[blackPawn8.row-2][blackPawn8.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == blackPawn8.row - 1 && columnBoard == blackPawn8.column && gameBoard[blackPawn8.row-2][blackPawn8.column-1].occupiedFieldByWHITE == false && gameBoard[blackPawn8.row-2][blackPawn8.column-1].occupiedFieldByBlack == false) ||
                        (rowBoard == blackPawn8.row - 1 && columnBoard != blackPawn8.column && blackPawn8.column != 8 && (gameBoard[blackPawn8.row-2][blackPawn8.column].occupiedFieldByWHITE == true || gameBoard[blackPawn8.row-2][blackPawn8.column-2].occupiedFieldByWHITE == true) ) ||
                        (rowBoard == blackPawn8.row - 1 && columnBoard != blackPawn8.column && blackPawn8.column == 8 && gameBoard[blackPawn8.row-2][blackPawn8.column-2].occupiedFieldByWHITE == true)
                ) {

                    blackPawn8.row = rowBoard;
                    blackPawn8.column = columnBoard;
                    blackPawn8.clicked = false;
                    moveBlackDone = true;
                    repaint();
                }
            }

             */


        // W momencie klikniecia zapisujemy wcisniete pole aby moc je pozniej ustawic na FALSE, kiedy ruch sie dokona
        // ustawiamy zmienne według logiki poniżej i w polu w którym myszka została puszczona ustawiamy zmienną na TRUE


    }


    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("MOUSE ENTERED");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("MOUSE EXITED");
    }


    Thread thread3 = new Thread(() -> {
        while (true) {

            if (moveWhiteDone) {
                moveSound("moveSound.wav");

                checkCollision();
                removeOptionsForAll();
                setAdditionalOptionsForWhite(rowBoardToDestroy, columnBoardToDestroy, rowBoard, columnBoard);
                setOptionsForAll();


                whiteKing.checkMateWHITE(gameBoard, blackKnight1, blackKnight2,
                        blackBishop1, blackBishop2,
                        blackRook1, blackRook2,
                        blackQueen,
                        blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                        blackPawn5, blackPawn6, blackPawn7, blackPawn8);

                blackKing.checkMateBLACK(gameBoard, whiteKnight1, whiteKnight2,
                        whiteBishop1, whiteBishop2,
                        whiteRook1, whiteRook2,
                        whiteQueen,
                        whitePawn1, whitePawn2, whitePawn3, whitePawn4,
                        whitePawn5, whitePawn6, whitePawn7, whitePawn8);


                whiteKing.checkDrawWHITE(gameBoard);
                blackKing.checkDrawBLACK(gameBoard);



                //wysylanie

                try {
                    if (whitePawn1.row == 8 || whitePawn2.row == 8 || whitePawn3.row == 8 || whitePawn4.row == 8 ||
                            whitePawn5.row == 8 || whitePawn6.row == 8 || whitePawn7.row == 8 || whitePawn8.row == 8
                    ) {

                        oos.writeObject(rowBoardToDestroy);
                        oos.flush();
                        oos.writeObject(columnBoardToDestroy);
                        oos.flush();
                        oos.writeObject(rowBoard);
                        oos.flush();
                        oos.writeObject(columnBoard);
                        oos.flush();

                        if (whitePawn1.row == 8) {
                            while (whitePawn1.transformation == 0) {
                                sleep(500);
                            }
                            oos.writeObject(whitePawn1.transformation);
                            oos.flush();
                        } else if (whitePawn2.row == 8) {
                            while (whitePawn2.transformation == 0) {
                                sleep(500);
                            }
                            oos.writeObject(whitePawn2.transformation);
                            oos.flush();
                        } else if (whitePawn3.row == 8) {
                            while (whitePawn3.transformation == 0) {
                                sleep(500);
                            }
                            oos.writeObject(whitePawn3.transformation);
                            oos.flush();
                        } else if (whitePawn4.row == 8) {
                            while (whitePawn4.transformation == 0) {
                                sleep(500);
                            }
                            oos.writeObject(whitePawn4.transformation);
                            oos.flush();
                        } else if (whitePawn5.row == 8) {
                            while (whitePawn5.transformation == 0) {
                                sleep(500);
                            }
                            oos.writeObject(whitePawn5.transformation);
                            oos.flush();
                        } else if (whitePawn6.row == 8) {
                            while (whitePawn6.transformation == 0) {
                                sleep(500);
                            }
                            oos.writeObject(whitePawn6.transformation);
                            oos.flush();
                        } else if (whitePawn7.row == 8) {
                            while (whitePawn7.transformation == 0) {
                                sleep(500);
                            }
                            oos.writeObject(whitePawn7.transformation);
                            oos.flush();
                        } else if (whitePawn8.row == 8) {
                            while (whitePawn8.transformation == 0) {
                                sleep(500);
                            }
                            oos.writeObject(whitePawn8.transformation);
                            oos.flush();
                        }
                    } else {
                        oos.writeObject(rowBoardToDestroy);
                        oos.flush();
                        oos.writeObject(columnBoardToDestroy);
                        oos.flush();
                        oos.writeObject(rowBoard);
                        oos.flush();
                        oos.writeObject(columnBoard);
                        oos.flush();

                    }


                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                endGame();
                blackMove = true;
                whiteMove = false;
                moveWhiteDone = false;

            } else if (moveBlackDone) {
                blackMove = false;
                whiteMove = true;
                moveBlackDone = false;

            }


            //System.out.println(gameBoard[rowBoard -1][columnBoard -1].occupiedFieldByWHITE +"   "+gameBoard[rowBoard -1][columnBoard -1].occupiedFieldByBlack +"  Identyfikator = "+gameBoard[rowBoard -1][columnBoard -1].identityOfSquare);

            repaint();
        }
    });

    Thread thread1 = new Thread(() -> {
        //odbieranie

        while (true) {
            try {

                rowBoardToDestroyOdebrany = (int) ois.readObject();
                columnBoardToDestroyOdebrany = (int) ois.readObject();
                rowBoardOdebrany = (int) ois.readObject();
                columnBoardOdebrany = (int) ois.readObject();
                if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 49 && rowBoardOdebrany == 1) {
                    blackPawn1.transformation = (int) ois.readObject();
                    if (blackPawn1.transformation == 1) {
                        blackPawn1.image = blackRookImage;
                    } else if (blackPawn1.transformation == 2) {
                        blackPawn1.image = blackQueenImage;
                    } else if (blackPawn1.transformation == 3) {
                        blackPawn1.image = blackBishopImage;
                    } else if (blackPawn1.transformation == 4) {
                        blackPawn1.image = blackKnightImage;
                    }
                } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 50 && rowBoardOdebrany == 1) {
                    blackPawn2.transformation = (int) ois.readObject();
                    if (blackPawn2.transformation == 1) {
                        blackPawn2.image = blackRookImage;
                    } else if (blackPawn2.transformation == 2) {
                        blackPawn2.image = blackQueenImage;
                    } else if (blackPawn2.transformation == 3) {
                        blackPawn2.image = blackBishopImage;
                    } else if (blackPawn2.transformation == 4) {
                        blackPawn2.image = blackKnightImage;
                    }
                } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 51 && rowBoardOdebrany == 1) {
                    blackPawn3.transformation = (int) ois.readObject();
                    if (blackPawn3.transformation == 1) {
                        blackPawn3.image = blackRookImage;
                    } else if (blackPawn3.transformation == 2) {
                        blackPawn3.image = blackQueenImage;
                    } else if (blackPawn3.transformation == 3) {
                        blackPawn3.image = blackBishopImage;
                    } else if (blackPawn3.transformation == 4) {
                        blackPawn3.image = blackKnightImage;
                    }
                } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 52 && rowBoardOdebrany == 1) {
                    blackPawn4.transformation = (int) ois.readObject();
                    if (blackPawn4.transformation == 1) {
                        blackPawn4.image = blackRookImage;
                    } else if (blackPawn4.transformation == 2) {
                        blackPawn4.image = blackQueenImage;
                    } else if (blackPawn4.transformation == 3) {
                        blackPawn4.image = blackBishopImage;
                    } else if (blackPawn4.transformation == 4) {
                        blackPawn4.image = blackKnightImage;
                    }
                } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 53 && rowBoardOdebrany == 1) {
                    blackPawn5.transformation = (int) ois.readObject();
                    if (blackPawn5.transformation == 1) {
                        blackPawn5.image = blackRookImage;
                    } else if (blackPawn5.transformation == 2) {
                        blackPawn5.image = blackQueenImage;
                    } else if (blackPawn5.transformation == 3) {
                        blackPawn5.image = blackBishopImage;
                    } else if (blackPawn5.transformation == 4) {
                        blackPawn5.image = blackKnightImage;
                    }
                } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 54 && rowBoardOdebrany == 1) {
                    blackPawn6.transformation = (int) ois.readObject();
                    if (blackPawn6.transformation == 1) {
                        blackPawn6.image = blackRookImage;
                    } else if (blackPawn6.transformation == 2) {
                        blackPawn6.image = blackQueenImage;
                    } else if (blackPawn6.transformation == 3) {
                        blackPawn6.image = blackBishopImage;
                    } else if (blackPawn6.transformation == 4) {
                        blackPawn6.image = blackKnightImage;
                    }
                } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 55 && rowBoardOdebrany == 1) {
                    blackPawn7.transformation = (int) ois.readObject();
                    if (blackPawn7.transformation == 1) {
                        blackPawn7.image = blackRookImage;
                    } else if (blackPawn7.transformation == 2) {
                        blackPawn7.image = blackQueenImage;
                    } else if (blackPawn7.transformation == 3) {
                        blackPawn7.image = blackBishopImage;
                    } else if (blackPawn7.transformation == 4) {
                        blackPawn7.image = blackKnightImage;
                    }
                } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 56 && rowBoardOdebrany == 1) {
                    blackPawn8.transformation = (int) ois.readObject();
                    if (blackPawn8.transformation == 1) {
                        blackPawn8.image = blackRookImage;
                    } else if (blackPawn8.transformation == 2) {
                        blackPawn8.image = blackQueenImage;
                    } else if (blackPawn8.transformation == 3) {
                        blackPawn8.image = blackBishopImage;
                    } else if (blackPawn8.transformation == 4) {
                        blackPawn8.image = blackKnightImage;
                    }
                }
                moveSound("moveSound.wav");


            } catch (IOException ex) {
                throw new RuntimeException(ex);

            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 49) {
                if (blackPawn1.row - 2 == rowBoardOdebrany && blackPawn1.column == columnBoardOdebrany && gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany].occupiedFieldByWHITE) {
                    blackPawn1.beatingInTransit = true;
                }

                if (blackPawn1.row == 4 && !gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 1].occupiedFieldByWHITE && blackPawn1.column != columnBoardOdebrany) {
                    if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;

                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;
                    }
                    gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare = 0;
                    gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].occupiedFieldByWHITE = false;
                    blackPawn1.row = rowBoardOdebrany;
                    blackPawn1.column = columnBoardOdebrany;
                } else {
                    blackPawn1.row = rowBoardOdebrany;
                    blackPawn1.column = columnBoardOdebrany;
                }
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 50) {

                if (blackPawn2.row - 2 == rowBoardOdebrany && blackPawn2.column == columnBoardOdebrany && (gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany].occupiedFieldByWHITE || gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 2].occupiedFieldByWHITE)) {
                    blackPawn2.beatingInTransit = true;
                }

                if (blackPawn2.row == 4 && !gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 1].occupiedFieldByWHITE && blackPawn2.column != columnBoardOdebrany) {
                    if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;
                    }
                    gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare = 0;
                    gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].occupiedFieldByWHITE = false;
                    blackPawn2.row = rowBoardOdebrany;
                    blackPawn2.column = columnBoardOdebrany;
                } else {
                    blackPawn2.row = rowBoardOdebrany;
                    blackPawn2.column = columnBoardOdebrany;
                }
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 51) {
                if (blackPawn3.row - 2 == rowBoardOdebrany && blackPawn3.column == columnBoardOdebrany && (gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany].occupiedFieldByWHITE || gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 2].occupiedFieldByWHITE)) {
                    blackPawn3.beatingInTransit = true;
                }

                if (blackPawn3.row == 4 && !gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 1].occupiedFieldByWHITE && blackPawn3.column != columnBoardOdebrany) {
                    if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;
                        gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;
                    }
                    gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare = 0;
                    gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].occupiedFieldByWHITE = false;
                    blackPawn3.row = rowBoardOdebrany;
                    blackPawn3.column = columnBoardOdebrany;
                } else {
                    blackPawn3.row = rowBoardOdebrany;
                    blackPawn3.column = columnBoardOdebrany;
                }
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 52) {
                if (blackPawn4.row - 2 == rowBoardOdebrany && blackPawn4.column == columnBoardOdebrany && (gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany].occupiedFieldByWHITE || gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 2].occupiedFieldByWHITE)) {
                    blackPawn4.beatingInTransit = true;
                }

                if (blackPawn4.row == 4 && !gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 1].occupiedFieldByWHITE && blackPawn4.column != columnBoardOdebrany) {
                    if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;
                    }
                    gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare = 0;
                    gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].occupiedFieldByWHITE = false;
                    blackPawn4.row = rowBoardOdebrany;
                    blackPawn4.column = columnBoardOdebrany;
                } else {
                    blackPawn4.row = rowBoardOdebrany;
                    blackPawn4.column = columnBoardOdebrany;
                }
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 53) {
                if (blackPawn5.row - 2 == rowBoardOdebrany && blackPawn5.column == columnBoardOdebrany && (gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany].occupiedFieldByWHITE || gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 2].occupiedFieldByWHITE)) {
                    blackPawn5.beatingInTransit = true;
                }

                if (blackPawn5.row == 4 && !gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 1].occupiedFieldByWHITE && blackPawn5.column != columnBoardOdebrany) {
                    if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;
                    }
                    gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare = 0;
                    gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].occupiedFieldByWHITE = false;
                    blackPawn5.row = rowBoardOdebrany;
                    blackPawn5.column = columnBoardOdebrany;
                } else {
                    blackPawn5.row = rowBoardOdebrany;
                    blackPawn5.column = columnBoardOdebrany;
                }
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 54) {
                if (blackPawn6.row - 2 == rowBoardOdebrany && blackPawn6.column == columnBoardOdebrany && (gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany].occupiedFieldByWHITE || gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 2].occupiedFieldByWHITE)) {
                    blackPawn6.beatingInTransit = true;
                }

                if (blackPawn6.row == 4 && !gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 1].occupiedFieldByWHITE && blackPawn6.column != columnBoardOdebrany) {
                    if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;
                    }
                    gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare = 0;
                    gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].occupiedFieldByWHITE = false;
                    blackPawn6.row = rowBoardOdebrany;
                    blackPawn6.column = columnBoardOdebrany;
                } else {
                    blackPawn6.row = rowBoardOdebrany;
                    blackPawn6.column = columnBoardOdebrany;
                }
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 55) {
                if (blackPawn7.row - 2 == rowBoardOdebrany && blackPawn7.column == columnBoardOdebrany && (gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany].occupiedFieldByWHITE || gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 2].occupiedFieldByWHITE)) {
                    blackPawn7.beatingInTransit = true;
                }

                if (blackPawn7.row == 4 && !gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 1].occupiedFieldByWHITE && blackPawn7.column != columnBoardOdebrany) {
                    if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;
                    }
                    gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare = 0;
                    gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].occupiedFieldByWHITE = false;
                    blackPawn7.row = rowBoardOdebrany;
                    blackPawn7.column = columnBoardOdebrany;
                } else {
                    blackPawn7.row = rowBoardOdebrany;
                    blackPawn7.column = columnBoardOdebrany;
                }
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 56) {
                if (blackPawn8.row - 2 == rowBoardOdebrany && blackPawn8.column == columnBoardOdebrany && gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 2].occupiedFieldByWHITE) {
                    blackPawn8.beatingInTransit = true;
                }

                if (blackPawn8.row == 4 && !gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 1].occupiedFieldByWHITE && blackPawn8.column != columnBoardOdebrany) {
                    if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;
                    } else if (gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;
                    }
                    gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].identityOfSquare = 0;
                    gameBoard[rowBoardOdebrany][columnBoardOdebrany - 1].occupiedFieldByWHITE = false;
                    blackPawn8.row = rowBoardOdebrany;
                    blackPawn8.column = columnBoardOdebrany;
                } else {
                    blackPawn8.row = rowBoardOdebrany;
                    blackPawn8.column = columnBoardOdebrany;
                }
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 57) {
                blackRook1.row = rowBoardOdebrany;
                blackRook1.column = columnBoardOdebrany;
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 58) {
                blackKnight1.row = rowBoardOdebrany;
                blackKnight1.column = columnBoardOdebrany;

            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 59) {
                blackBishop1.row = rowBoardOdebrany;
                blackBishop1.column = columnBoardOdebrany;
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 60) {
                blackQueen.row = rowBoardOdebrany;
                blackQueen.column = columnBoardOdebrany;
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 61) {

                if (blackKing.column == columnBoardOdebrany + 2) {
                    gameBoard[blackRook1.row - 1][blackRook1.column - 1].occupiedFieldByBlack = false;
                    blackRook1.row = 8;
                    blackRook1.column = 4;
                    gameBoard[blackRook1.row - 1][blackRook1.column - 1].identityOfSquare = 57;

                    gameBoard[7][0].identityOfSquare = 0;
                } else if (blackKing.column == columnBoardOdebrany - 2) {
                    gameBoard[blackRook2.row - 1][blackRook2.column - 1].occupiedFieldByBlack = false;
                    blackRook2.row = 8;
                    blackRook2.column = 6;
                    gameBoard[blackRook2.row - 1][blackRook2.column - 1].identityOfSquare = 64;

                    gameBoard[7][7].identityOfSquare = 0;
                }

                blackKing.row = rowBoardOdebrany;
                blackKing.column = columnBoardOdebrany;


            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 62) {
                blackBishop2.row = rowBoardOdebrany;
                blackBishop2.column = columnBoardOdebrany;
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 63) {
                blackKnight2.row = rowBoardOdebrany;
                blackKnight2.column = columnBoardOdebrany;
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 64) {
                blackRook2.row = rowBoardOdebrany;
                blackRook2.column = columnBoardOdebrany;
            }


            //Possible Moves

            checkCollision();
            removeOptionsForAll();
            setAdditionalOptionsForBlack(rowBoardToDestroyOdebrany, columnBoardToDestroyOdebrany, rowBoardOdebrany, columnBoardOdebrany);
            //move options here
            setOptionsForAll();


            whiteKing.checkMateWHITE(gameBoard, blackKnight1, blackKnight2,
                    blackBishop1, blackBishop2,
                    blackRook1, blackRook2,
                    blackQueen,
                    blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                    blackPawn5, blackPawn6, blackPawn7, blackPawn8);

            blackKing.checkMateBLACK(gameBoard, whiteKnight1, whiteKnight2,
                    whiteBishop1, whiteBishop2,
                    whiteRook1, whiteRook2,
                    whiteQueen,
                    whitePawn1, whitePawn2, whitePawn3, whitePawn4,
                    whitePawn5, whitePawn6, whitePawn7, whitePawn8);

            whiteKing.checkDrawWHITE(gameBoard);
            blackKing.checkDrawBLACK(gameBoard);

            endGame();


            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            blackMove = false;
            whiteMove = true;

            repaint();
        }

    });





}
