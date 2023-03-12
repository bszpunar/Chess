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

public class BoardBLACK extends JPanel implements MouseListener {

    public Square[][] gameBoard = new Square[8][8];

    private final static int WIDTH = 420;
    private final static int HEIGHT = 480;

    public int clickCounter = 0;


    String[] alfabet = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};

    private int x, y;

    public int rowBoard = 0;
    public int columnBoard = 0;

    public int rowBoardToDestroy, columnBoardToDestroy;

    public Socket socket;

    public int port;


    public ServerSocket serverSocket;
    public String address;
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
    Knight whiteKnight1 = new Knight(1, 2, "whiteKnight1", false);
    Knight whiteKnight2 = new Knight(1, 7, "whiteKnight2", false);

    Bishop whiteBishop1 = new Bishop(1, 3, "whiteBishop1", false);
    Bishop whiteBishop2 = new Bishop(1, 6, "whiteBishop2", false);

    Rook whiteRook1 = new Rook(1, 1, "whiteRook1", false);
    Rook whiteRook2 = new Rook(1, 8, "whiteRook2", false);

    King whiteKing = new King(1, 5, "whiteKing", false);

    Queen whiteQueen = new Queen(1, 4, "whiteQueen", false);

    Pawn whitePawn1 = new Pawn(2, 1, "whitePawn1", false, false, whitePawnImage, 0);
    Pawn whitePawn2 = new Pawn(2, 2, "whitePawn2", false, false, whitePawnImage, 0);
    Pawn whitePawn3 = new Pawn(2, 3, "whitePawn3", false, false, whitePawnImage, 0);
    Pawn whitePawn4 = new Pawn(2, 4, "whitePawn4", false, false, whitePawnImage, 0);
    Pawn whitePawn5 = new Pawn(2, 5, "whitePawn5", false, false, whitePawnImage, 0);
    Pawn whitePawn6 = new Pawn(2, 6, "whitePawn6", false, false, whitePawnImage, 0);
    Pawn whitePawn7 = new Pawn(2, 7, "whitePawn7", false, false, whitePawnImage, 0);
    Pawn whitePawn8 = new Pawn(2, 8, "whitePawn8", false, false, whitePawnImage, 0);

    //CZARNE
    Knight blackKnight1 = new Knight(8, 2, "blackKnight1", false);
    Knight blackKnight2 = new Knight(8, 7, "blackKnight2", false);

    Bishop blackBishop1 = new Bishop(8, 3, "balckBishop1", false);
    Bishop blackBishop2 = new Bishop(8, 6, "blackBishop2", false);

    Rook blackRook1 = new Rook(8, 1, "blackRook1", false);
    Rook blackRook2 = new Rook(8, 8, "blackRook2", false);

    King blackKing = new King(8, 5, "blackKing", false);

    Queen blackQueen = new Queen(8, 4, "blackQueen", false);

    Pawn blackPawn1 = new Pawn(7, 1, "blackPawn1", false, false, blackPawnImage, 0);
    Pawn blackPawn2 = new Pawn(7, 2, "blackPawn2", false, false, blackPawnImage, 0);
    Pawn blackPawn3 = new Pawn(7, 3, "blackPawn3", false, false, blackPawnImage, 0);
    Pawn blackPawn4 = new Pawn(7, 4, "blackPawn4", false, false, blackPawnImage, 0);
    Pawn blackPawn5 = new Pawn(7, 5, "blackPawn5", false, false, blackPawnImage, 0);
    Pawn blackPawn6 = new Pawn(7, 6, "blackPawn6", false, false, blackPawnImage, 0);
    Pawn blackPawn7 = new Pawn(7, 7, "blackPawn7", false, false, blackPawnImage, 0);
    Pawn blackPawn8 = new Pawn(7, 8, "blackPawn8", false, false, blackPawnImage, 0);


    public BoardBLACK(String address, int port) {


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

    public BoardBLACK(int port) {


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
        callServer();

    }

    public void callClient() {
        try {
            socket = new Socket(address, port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        thread1.start();
        //thread2.start();
        thread3.start();

        System.out.println("Let's Play Chess");

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
        //thread2.start();
        thread3.start();

        System.out.println("Let's Play Chess");

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

    //usuwanie danych które są porzebne by sprawdzic czy ruch na szachu jest mozliwy potem je usuwamy
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


        if (gameBoard[r - 1][c - 1].identityOfSquare >= 49 && gameBoard[r - 1][c - 1].identityOfSquare <= 64) {
            gameBoard[r - 1][c - 1].occupiedFieldByWHITE = false;
            gameBoard[r - 1][c - 1].occupiedFieldByBlack = true;
        } else if (gameBoard[r - 1][c - 1].identityOfSquare >= 1 && gameBoard[r - 1][c - 1].identityOfSquare <= 16) {
            gameBoard[r - 1][c - 1].occupiedFieldByWHITE = true;
            gameBoard[r - 1][c - 1].occupiedFieldByBlack = false;
        } else if (gameBoard[r - 1][c - 1].identityOfSquare == 0) {
            gameBoard[r - 1][c - 1].occupiedFieldByWHITE = false;
            gameBoard[r - 1][c - 1].occupiedFieldByBlack = false;
        }

    }

    public void addAdditionalOptionsForALLinRealase(int row, int column, int identity) {

        if (identity >= 49 && identity <= 64) {
            gameBoard[row - 1][column - 1].occupiedFieldByWHITE = false;
            gameBoard[row - 1][column - 1].occupiedFieldByBlack = true;
            gameBoard[row - 1][column - 1].identityOfSquare = identity;
        } else if (identity >= 1 && identity <= 16) {
            gameBoard[row - 1][column - 1].occupiedFieldByWHITE = true;
            gameBoard[row - 1][column - 1].occupiedFieldByBlack = false;
            gameBoard[row - 1][column - 1].identityOfSquare = identity;
        }


    }


    public void removeAdditionalOptionsForALLinRealase(int r, int c, int var) {


        switch (var) {
            case 49:
            case 64:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
                gameBoard[r - 1][c - 1].occupiedFieldByWHITE = false;
                gameBoard[r - 1][c - 1].occupiedFieldByBlack = true;
                gameBoard[r - 1][c - 1].identityOfSquare = var;
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                gameBoard[r - 1][c - 1].occupiedFieldByWHITE = true;
                gameBoard[r - 1][c - 1].occupiedFieldByBlack = false;
                gameBoard[r - 1][c - 1].identityOfSquare = var;
                break;
            case 0:
                gameBoard[r - 1][c - 1].occupiedFieldByWHITE = false;
                gameBoard[r - 1][c - 1].occupiedFieldByBlack = false;
                gameBoard[r - 1][c - 1].identityOfSquare = 0;
                break;

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


    public void endGame() {
        if (whiteKing.checkMate) {
            JOptionPane.showMessageDialog(this, "Victory !");
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
            new GameStart();

        } else if (blackKing.checkMate) {
            JOptionPane.showMessageDialog(this, "You lost !");
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


    public void checkCollision() {

        //kolizja whiteRook1

        if ((whiteRook1.row == blackPawn1.row && whiteRook1.column == blackPawn1.column)) {
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
    }







    public void justMove() {
        //Poruszanie się czarne

        if (columnBoard < 9 && rowBoard < 9 && columnBoard > 0 && rowBoard > 0) {
            if (blackBishop1.clicked) {
                moveBlackDone = blackBishop1.moveBlack(rowBoard, columnBoard, gameBoard, blackKing, this, whiteKnight1, whiteKnight2,
                        whiteBishop1, whiteBishop2,
                        whiteRook1, whiteRook2,
                        whiteQueen,
                        whitePawn1, whitePawn2, whitePawn3, whitePawn4,
                        whitePawn5, whitePawn6, whitePawn7, whitePawn8, whiteKing);
                repaint();
            } else if (blackBishop2.clicked) {
                moveBlackDone = blackBishop2.moveBlack(rowBoard, columnBoard, gameBoard, blackKing, this, whiteKnight1, whiteKnight2,
                        whiteBishop1, whiteBishop2,
                        whiteRook1, whiteRook2,
                        whiteQueen,
                        whitePawn1, whitePawn2, whitePawn3, whitePawn4,
                        whitePawn5, whitePawn6, whitePawn7, whitePawn8, whiteKing);
                repaint();
            } else if (blackKnight1.clicked) {
                moveBlackDone = blackKnight1.moveBlack(rowBoard, columnBoard, gameBoard, blackKing, this, whiteKnight1, whiteKnight2,
                        whiteBishop1, whiteBishop2,
                        whiteRook1, whiteRook2,
                        whiteQueen,
                        whitePawn1, whitePawn2, whitePawn3, whitePawn4,
                        whitePawn5, whitePawn6, whitePawn7, whitePawn8, whiteKing);
                repaint();
            } else if (blackKnight2.clicked) {
                moveBlackDone = blackKnight2.moveBlack(rowBoard, columnBoard, gameBoard, blackKing, this, whiteKnight1, whiteKnight2,
                        whiteBishop1, whiteBishop2,
                        whiteRook1, whiteRook2,
                        whiteQueen,
                        whitePawn1, whitePawn2, whitePawn3, whitePawn4,
                        whitePawn5, whitePawn6, whitePawn7, whitePawn8, whiteKing);
                repaint();
            } else if (blackRook1.clicked) {
                moveBlackDone = blackRook1.moveBlack(rowBoard, columnBoard, gameBoard, blackKing, this, whiteKnight1, whiteKnight2,
                        whiteBishop1, whiteBishop2,
                        whiteRook1, whiteRook2,
                        whiteQueen,
                        whitePawn1, whitePawn2, whitePawn3, whitePawn4,
                        whitePawn5, whitePawn6, whitePawn7, whitePawn8, whiteKing);
                if (moveBlackDone) blackKing.blackRook1CastlingAccess = false;
                repaint();

            } else if (blackRook2.clicked) {
                moveBlackDone = blackRook2.moveBlack(rowBoard, columnBoard, gameBoard, blackKing, this, whiteKnight1, whiteKnight2,
                        whiteBishop1, whiteBishop2,
                        whiteRook1, whiteRook2,
                        whiteQueen,
                        whitePawn1, whitePawn2, whitePawn3, whitePawn4,
                        whitePawn5, whitePawn6, whitePawn7, whitePawn8, whiteKing);
                if (moveBlackDone) blackKing.blackRook2CastlingAccess = false;
                repaint();

            } else if (blackQueen.clicked) {
                moveBlackDone = blackQueen.moveBlack(rowBoard, columnBoard, gameBoard, blackKing, this, whiteKnight1, whiteKnight2,
                        whiteBishop1, whiteBishop2,
                        whiteRook1, whiteRook2,
                        whiteQueen,
                        whitePawn1, whitePawn2, whitePawn3, whitePawn4,
                        whitePawn5, whitePawn6, whitePawn7, whitePawn8, whiteKing);
                repaint();
            } else if (blackKing.clicked) {
                moveBlackDone = blackKing.moveBlack(rowBoard, columnBoard, gameBoard, whiteKing);
                if (blackKing.castlingLong) {
                    blackRook1.row = 8;
                    blackRook1.column = 4;
                    gameBoard[blackRook1.row - 1][blackRook1.column - 1].identityOfSquare = 57;
                    gameBoard[7][0].identityOfSquare = 0;
                } else if (blackKing.castlingShort) {
                    blackRook2.row = 8;
                    blackRook2.column = 6;
                    gameBoard[blackRook2.row - 1][blackRook2.column - 1].identityOfSquare = 64;
                    gameBoard[7][7].identityOfSquare = 0;
                }
                repaint();
            } else if (blackPawn1.clicked) {

                if (blackPawn1.column != 8 && blackPawn1.row == 4 && gameBoard[blackPawn1.row - 1][blackPawn1.column].occupiedFieldByWHITE && blackPawn1.row - 1 == rowBoard && blackPawn1.column + 1 == columnBoard) {
                    if (gameBoard[blackPawn1.row - 1][blackPawn1.column].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;

                        moveBlackDone = true;

                    } else if (gameBoard[blackPawn1.row - 1][blackPawn1.column].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn1.row - 1][blackPawn1.column].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn1.row - 1][blackPawn1.column].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn1.row - 1][blackPawn1.column].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn1.row - 1][blackPawn1.column].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn1.row - 1][blackPawn1.column].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn1.row - 1][blackPawn1.column].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;

                        moveBlackDone = true;
                    }
                    if (moveBlackDone) {
                        gameBoard[blackPawn1.row - 1][blackPawn1.column].identityOfSquare = 0;
                        gameBoard[blackPawn1.row - 1][blackPawn1.column].occupiedFieldByWHITE = false;
                        blackPawn1.row = rowBoard;
                        blackPawn1.column = columnBoard;
                    }

                } else if (blackPawn1.column != 1 && blackPawn1.row == 4 && gameBoard[blackPawn1.row - 1][blackPawn1.column - 2].occupiedFieldByWHITE && blackPawn1.row - 1 == rowBoard && blackPawn1.column - 1 == columnBoard) {

                    if (gameBoard[blackPawn1.row - 1][blackPawn1.column - 2].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;

                        moveBlackDone = true;

                    } else if (gameBoard[blackPawn1.row - 1][blackPawn1.column - 2].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn1.row - 1][blackPawn1.column - 2].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn1.row - 1][blackPawn1.column - 2].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn1.row - 1][blackPawn1.column - 2].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn1.row - 1][blackPawn1.column - 2].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn1.row - 1][blackPawn1.column - 2].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn1.row - 1][blackPawn1.column - 2].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;

                        moveBlackDone = true;
                    }
                    if (moveBlackDone) {
                        gameBoard[blackPawn1.row - 1][blackPawn1.column - 2].identityOfSquare = 0;
                        gameBoard[blackPawn1.row - 1][blackPawn1.column - 2].occupiedFieldByWHITE = false;
                        blackPawn1.row = rowBoard;
                        blackPawn1.column = columnBoard;
                    }


                } else {
                    moveBlackDone = blackPawn1.moveBlack(rowBoard, columnBoard, gameBoard, blackKing, this, whiteKnight1, whiteKnight2,
                            whiteBishop1, whiteBishop2,
                            whiteRook1, whiteRook2,
                            whiteQueen,
                            whitePawn1, whitePawn2, whitePawn3, whitePawn4,
                            whitePawn5, whitePawn6, whitePawn7, whitePawn8, whiteKing);
                }
                repaint();

            } else if (blackPawn2.clicked) {

                if (blackPawn2.column != 8 && blackPawn2.row == 4 && gameBoard[blackPawn2.row - 1][blackPawn2.column].occupiedFieldByWHITE && blackPawn2.row - 1 == rowBoard && blackPawn2.column + 1 == columnBoard) {
                    if (gameBoard[blackPawn2.row - 1][blackPawn2.column].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn2.row - 1][blackPawn2.column].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn2.row - 1][blackPawn2.column].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn2.row - 1][blackPawn2.column].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn2.row - 1][blackPawn2.column].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn2.row - 1][blackPawn2.column].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn2.row - 1][blackPawn2.column].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn2.row - 1][blackPawn2.column].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;

                        moveBlackDone = true;
                    }
                    if (moveBlackDone) {
                        gameBoard[blackPawn2.row - 1][blackPawn2.column].identityOfSquare = 0;
                        gameBoard[blackPawn2.row - 1][blackPawn2.column].occupiedFieldByWHITE = false;
                        blackPawn2.row = rowBoard;
                        blackPawn2.column = columnBoard;
                    }


                } else if (blackPawn2.column != 1 && blackPawn2.row == 4 && gameBoard[blackPawn2.row - 1][blackPawn2.column - 2].occupiedFieldByWHITE && blackPawn2.row - 1 == rowBoard && blackPawn2.column - 1 == columnBoard) {

                    if (gameBoard[blackPawn2.row - 1][blackPawn2.column - 2].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn2.row - 1][blackPawn2.column - 2].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn2.row - 1][blackPawn2.column - 2].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn2.row - 1][blackPawn2.column - 2].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn2.row - 1][blackPawn2.column - 2].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn2.row - 1][blackPawn2.column - 2].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn2.row - 1][blackPawn2.column - 2].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn2.row - 1][blackPawn2.column - 2].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;

                        moveBlackDone = true;
                    }
                    if (moveBlackDone) {
                        gameBoard[blackPawn2.row - 1][blackPawn2.column - 2].identityOfSquare = 0;
                        gameBoard[blackPawn2.row - 1][blackPawn2.column - 2].occupiedFieldByWHITE = false;
                        blackPawn2.row = rowBoard;
                        blackPawn2.column = columnBoard;
                    }


                } else {
                    moveBlackDone = blackPawn2.moveBlack(rowBoard, columnBoard, gameBoard, blackKing, this, whiteKnight1, whiteKnight2,
                            whiteBishop1, whiteBishop2,
                            whiteRook1, whiteRook2,
                            whiteQueen,
                            whitePawn1, whitePawn2, whitePawn3, whitePawn4,
                            whitePawn5, whitePawn6, whitePawn7, whitePawn8, whiteKing);
                }
                repaint();
            } else if (blackPawn3.clicked) {

                if (blackPawn3.column != 8 && blackPawn3.row == 4 && gameBoard[blackPawn3.row - 1][blackPawn3.column].occupiedFieldByWHITE && blackPawn3.row - 1 == rowBoard && blackPawn3.column + 1 == columnBoard) {
                    if (gameBoard[blackPawn3.row - 1][blackPawn3.column].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn3.row - 1][blackPawn3.column].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn3.row - 1][blackPawn3.column].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn3.row - 1][blackPawn3.column].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn3.row - 1][blackPawn3.column].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn3.row - 1][blackPawn3.column].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn3.row - 1][blackPawn3.column].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn3.row - 1][blackPawn3.column].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;

                        moveBlackDone = true;
                    }
                    if (moveBlackDone) {
                        gameBoard[blackPawn3.row - 1][blackPawn3.column].identityOfSquare = 0;
                        gameBoard[blackPawn3.row - 1][blackPawn3.column].occupiedFieldByWHITE = false;
                        blackPawn3.row = rowBoard;
                        blackPawn3.column = columnBoard;
                    }


                } else if (blackPawn3.column != 1 && blackPawn3.row == 4 && gameBoard[blackPawn3.row - 1][blackPawn3.column - 2].occupiedFieldByWHITE && blackPawn3.row - 1 == rowBoard && blackPawn3.column - 1 == columnBoard) {

                    if (gameBoard[blackPawn3.row - 1][blackPawn3.column - 2].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn3.row - 1][blackPawn3.column - 2].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn3.row - 1][blackPawn3.column - 2].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn3.row - 1][blackPawn3.column - 2].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn3.row - 1][blackPawn3.column - 2].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn3.row - 1][blackPawn3.column - 2].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn3.row - 1][blackPawn3.column - 2].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn3.row - 1][blackPawn3.column - 2].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;

                        moveBlackDone = true;
                    }
                    if (moveBlackDone) {
                        gameBoard[blackPawn3.row - 1][blackPawn3.column - 2].identityOfSquare = 0;
                        gameBoard[blackPawn3.row - 1][blackPawn3.column - 2].occupiedFieldByWHITE = false;
                        blackPawn3.row = rowBoard;
                        blackPawn3.column = columnBoard;
                    }


                } else {
                    moveBlackDone = blackPawn3.moveBlack(rowBoard, columnBoard, gameBoard, blackKing, this, whiteKnight1, whiteKnight2,
                            whiteBishop1, whiteBishop2,
                            whiteRook1, whiteRook2,
                            whiteQueen,
                            whitePawn1, whitePawn2, whitePawn3, whitePawn4,
                            whitePawn5, whitePawn6, whitePawn7, whitePawn8, whiteKing);
                }
                repaint();
            } else if (blackPawn4.clicked) {

                if (blackPawn4.column != 8 && blackPawn4.row == 4 && gameBoard[blackPawn4.row - 1][blackPawn4.column].occupiedFieldByWHITE && blackPawn4.row - 1 == rowBoard && blackPawn4.column + 1 == columnBoard) {
                    if (gameBoard[blackPawn4.row - 1][blackPawn4.column].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn4.row - 1][blackPawn4.column].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn4.row - 1][blackPawn4.column].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn4.row - 1][blackPawn4.column].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn4.row - 1][blackPawn4.column].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn4.row - 1][blackPawn4.column].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn4.row - 1][blackPawn4.column].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn4.row - 1][blackPawn4.column].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;

                        moveBlackDone = true;
                    }

                    if (moveBlackDone) {
                        gameBoard[blackPawn4.row - 1][blackPawn4.column].identityOfSquare = 0;
                        gameBoard[blackPawn4.row - 1][blackPawn4.column].occupiedFieldByWHITE = false;
                        blackPawn4.row = rowBoard;
                        blackPawn4.column = columnBoard;
                    }

                } else if (blackPawn4.column != 1 && blackPawn4.row == 4 && gameBoard[blackPawn4.row - 1][blackPawn4.column - 2].occupiedFieldByWHITE && blackPawn4.row - 1 == rowBoard && blackPawn4.column - 1 == columnBoard) {

                    if (gameBoard[blackPawn4.row - 1][blackPawn4.column - 2].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn4.row - 1][blackPawn4.column - 2].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn4.row - 1][blackPawn4.column - 2].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn4.row - 1][blackPawn4.column - 2].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn4.row - 1][blackPawn4.column - 2].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn4.row - 1][blackPawn4.column - 2].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn4.row - 1][blackPawn4.column - 2].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn4.row - 1][blackPawn4.column - 2].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;

                        moveBlackDone = true;
                    }

                    if (moveBlackDone) {
                        gameBoard[blackPawn4.row - 1][blackPawn4.column - 2].identityOfSquare = 0;
                        gameBoard[blackPawn4.row - 1][blackPawn4.column - 2].occupiedFieldByWHITE = false;
                        blackPawn4.row = rowBoard;
                        blackPawn4.column = columnBoard;
                    }

                } else {
                    moveBlackDone = blackPawn4.moveBlack(rowBoard, columnBoard, gameBoard, blackKing, this, whiteKnight1, whiteKnight2,
                            whiteBishop1, whiteBishop2,
                            whiteRook1, whiteRook2,
                            whiteQueen,
                            whitePawn1, whitePawn2, whitePawn3, whitePawn4,
                            whitePawn5, whitePawn6, whitePawn7, whitePawn8, whiteKing);
                }
                repaint();
            } else if (blackPawn5.clicked) {

                if (blackPawn5.column != 8 && blackPawn5.row == 4 && gameBoard[blackPawn5.row - 1][blackPawn5.column].occupiedFieldByWHITE && blackPawn5.row - 1 == rowBoard && blackPawn5.column + 1 == columnBoard) {
                    if (gameBoard[blackPawn5.row - 1][blackPawn5.column].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn5.row - 1][blackPawn5.column].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn5.row - 1][blackPawn5.column].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn5.row - 1][blackPawn5.column].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn5.row - 1][blackPawn5.column].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn5.row - 1][blackPawn5.column].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn5.row - 1][blackPawn5.column].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn5.row - 1][blackPawn5.column].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;

                        moveBlackDone = true;
                    }

                    if (moveBlackDone) {
                        gameBoard[blackPawn5.row - 1][blackPawn5.column].identityOfSquare = 0;
                        gameBoard[blackPawn5.row - 1][blackPawn5.column].occupiedFieldByWHITE = false;
                        blackPawn5.row = rowBoard;
                        blackPawn5.column = columnBoard;
                    }

                } else if (blackPawn5.column != 1 && blackPawn5.row == 4 && gameBoard[blackPawn5.row - 1][blackPawn5.column - 2].occupiedFieldByWHITE && blackPawn5.row - 1 == rowBoard && blackPawn5.column - 1 == columnBoard) {

                    if (gameBoard[blackPawn5.row - 1][blackPawn5.column - 2].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn5.row - 1][blackPawn5.column - 2].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn5.row - 1][blackPawn5.column - 2].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn5.row - 1][blackPawn5.column - 2].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn5.row - 1][blackPawn5.column - 2].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn5.row - 1][blackPawn5.column - 2].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn5.row - 1][blackPawn5.column - 2].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn5.row - 1][blackPawn5.column - 2].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;

                        moveBlackDone = true;
                    }
                    if (moveBlackDone) {
                        gameBoard[blackPawn5.row - 1][blackPawn5.column - 2].identityOfSquare = 0;
                        gameBoard[blackPawn5.row - 1][blackPawn5.column - 2].occupiedFieldByWHITE = false;
                        blackPawn5.row = rowBoard;
                        blackPawn5.column = columnBoard;
                    }


                } else {
                    moveBlackDone = blackPawn5.moveBlack(rowBoard, columnBoard, gameBoard, blackKing, this, whiteKnight1, whiteKnight2,
                            whiteBishop1, whiteBishop2,
                            whiteRook1, whiteRook2,
                            whiteQueen,
                            whitePawn1, whitePawn2, whitePawn3, whitePawn4,
                            whitePawn5, whitePawn6, whitePawn7, whitePawn8, whiteKing);
                }
                repaint();

            } else if (blackPawn6.clicked) {

                if (blackPawn6.column != 8 && blackPawn6.row == 4 && gameBoard[blackPawn6.row - 1][blackPawn6.column].occupiedFieldByWHITE && blackPawn6.row - 1 == rowBoard && blackPawn6.column + 1 == columnBoard) {
                    if (gameBoard[blackPawn6.row - 1][blackPawn6.column].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn6.row - 1][blackPawn6.column].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn6.row - 1][blackPawn6.column].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn6.row - 1][blackPawn6.column].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn6.row - 1][blackPawn6.column].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn6.row - 1][blackPawn6.column].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn6.row - 1][blackPawn6.column].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn6.row - 1][blackPawn6.column].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;

                        moveBlackDone = true;
                    }

                    if (moveBlackDone) {
                        gameBoard[blackPawn6.row - 1][blackPawn6.column].identityOfSquare = 0;
                        gameBoard[blackPawn6.row - 1][blackPawn6.column].occupiedFieldByWHITE = false;
                        blackPawn6.row = rowBoard;
                        blackPawn6.column = columnBoard;
                    }

                } else if (blackPawn6.column != 1 && blackPawn6.row == 4 && gameBoard[blackPawn6.row - 1][blackPawn6.column - 2].occupiedFieldByWHITE && blackPawn6.row - 1 == rowBoard && blackPawn6.column - 1 == columnBoard) {

                    if (gameBoard[blackPawn6.row - 1][blackPawn6.column - 2].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn6.row - 1][blackPawn6.column - 2].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn6.row - 1][blackPawn6.column - 2].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn6.row - 1][blackPawn6.column - 2].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn6.row - 1][blackPawn6.column - 2].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn6.row - 1][blackPawn6.column - 2].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn6.row - 1][blackPawn6.column - 2].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn6.row - 1][blackPawn6.column - 2].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;

                        moveBlackDone = true;
                    }
                    if (moveBlackDone) {
                        gameBoard[blackPawn6.row - 1][blackPawn6.column - 2].identityOfSquare = 0;
                        gameBoard[blackPawn6.row - 1][blackPawn6.column - 2].occupiedFieldByWHITE = false;
                        blackPawn6.row = rowBoard;
                        blackPawn6.column = columnBoard;
                    }


                } else {
                    moveBlackDone = blackPawn6.moveBlack(rowBoard, columnBoard, gameBoard, blackKing, this, whiteKnight1, whiteKnight2,
                            whiteBishop1, whiteBishop2,
                            whiteRook1, whiteRook2,
                            whiteQueen,
                            whitePawn1, whitePawn2, whitePawn3, whitePawn4,
                            whitePawn5, whitePawn6, whitePawn7, whitePawn8, whiteKing);
                }
                repaint();
            } else if (blackPawn7.clicked) {

                if (blackPawn7.column != 8 && blackPawn7.row == 4 && gameBoard[blackPawn7.row - 1][blackPawn7.column].occupiedFieldByWHITE && blackPawn7.row - 1 == rowBoard && blackPawn7.column + 1 == columnBoard) {
                    if (gameBoard[blackPawn7.row - 1][blackPawn7.column].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn7.row - 1][blackPawn7.column].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn7.row - 1][blackPawn7.column].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn7.row - 1][blackPawn7.column].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn7.row - 1][blackPawn7.column].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn7.row - 1][blackPawn7.column].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn7.row - 1][blackPawn7.column].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn7.row - 1][blackPawn7.column].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;

                        moveBlackDone = true;
                    }
                    if (moveBlackDone) {
                        gameBoard[blackPawn7.row - 1][blackPawn7.column].identityOfSquare = 0;
                        gameBoard[blackPawn7.row - 1][blackPawn7.column].occupiedFieldByWHITE = false;
                        blackPawn7.row = rowBoard;
                        blackPawn7.column = columnBoard;
                    }


                } else if (blackPawn7.column != 1 && blackPawn7.row == 4 && gameBoard[blackPawn7.row - 1][blackPawn7.column - 2].occupiedFieldByWHITE && blackPawn7.row - 1 == rowBoard && blackPawn7.column - 1 == columnBoard) {

                    if (gameBoard[blackPawn7.row - 1][blackPawn7.column - 2].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn7.row - 1][blackPawn7.column - 2].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn7.row - 1][blackPawn7.column - 2].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn7.row - 1][blackPawn7.column - 2].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn7.row - 1][blackPawn7.column - 2].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn7.row - 1][blackPawn7.column - 2].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn7.row - 1][blackPawn7.column - 2].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn7.row - 1][blackPawn7.column - 2].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;

                        moveBlackDone = true;
                    }

                    if (moveBlackDone) {
                        gameBoard[blackPawn7.row - 1][blackPawn7.column - 2].identityOfSquare = 0;
                        gameBoard[blackPawn7.row - 1][blackPawn7.column - 2].occupiedFieldByWHITE = false;
                        blackPawn7.row = rowBoard;
                        blackPawn7.column = columnBoard;
                    }

                } else {
                    moveBlackDone = blackPawn7.moveBlack(rowBoard, columnBoard, gameBoard, blackKing, this, whiteKnight1, whiteKnight2,
                            whiteBishop1, whiteBishop2,
                            whiteRook1, whiteRook2,
                            whiteQueen,
                            whitePawn1, whitePawn2, whitePawn3, whitePawn4,
                            whitePawn5, whitePawn6, whitePawn7, whitePawn8, whiteKing);
                }
                repaint();
            } else if (blackPawn8.clicked) {

                if (blackPawn8.column != 8 && blackPawn8.row == 4 && gameBoard[blackPawn8.row - 1][blackPawn8.column].occupiedFieldByWHITE && blackPawn8.row - 1 == rowBoard && blackPawn8.column + 1 == columnBoard) {
                    if (gameBoard[blackPawn8.row - 1][blackPawn8.column].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn8.row - 1][blackPawn8.column].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn8.row - 1][blackPawn8.column].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn8.row - 1][blackPawn8.column].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn8.row - 1][blackPawn8.column].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn8.row - 1][blackPawn8.column].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn8.row - 1][blackPawn8.column].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn8.row - 1][blackPawn8.column].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;

                        moveBlackDone = true;
                    }
                    if (moveBlackDone) {
                        gameBoard[blackPawn8.row - 1][blackPawn8.column].identityOfSquare = 0;
                        gameBoard[blackPawn8.row - 1][blackPawn8.column].occupiedFieldByWHITE = false;
                        blackPawn8.row = rowBoard;
                        blackPawn8.column = columnBoard;
                    }


                } else if (blackPawn8.column != 1 && blackPawn8.row == 4 && gameBoard[blackPawn8.row - 1][blackPawn8.column - 2].occupiedFieldByWHITE && blackPawn8.row - 1 == rowBoard && blackPawn8.column - 1 == columnBoard) {

                    if (gameBoard[blackPawn8.row - 1][blackPawn8.column - 2].identityOfSquare == 9 && whitePawn1.beatingInTransit) {
                        whitePawn1.row = 0;
                        whitePawn1.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn8.row - 1][blackPawn8.column - 2].identityOfSquare == 10 && whitePawn2.beatingInTransit) {
                        whitePawn2.row = 0;
                        whitePawn2.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn8.row - 1][blackPawn8.column - 2].identityOfSquare == 11 && whitePawn3.beatingInTransit) {
                        whitePawn3.row = 0;
                        whitePawn3.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn8.row - 1][blackPawn8.column - 2].identityOfSquare == 12 && whitePawn4.beatingInTransit) {
                        whitePawn4.row = 0;
                        whitePawn4.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn8.row - 1][blackPawn8.column - 2].identityOfSquare == 13 && whitePawn5.beatingInTransit) {
                        whitePawn5.row = 0;
                        whitePawn5.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn8.row - 1][blackPawn8.column - 2].identityOfSquare == 14 && whitePawn6.beatingInTransit) {
                        whitePawn6.row = 0;
                        whitePawn6.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn8.row - 1][blackPawn8.column - 2].identityOfSquare == 15 && whitePawn7.beatingInTransit) {
                        whitePawn7.row = 0;
                        whitePawn7.column = 0;

                        moveBlackDone = true;
                    } else if (gameBoard[blackPawn8.row - 1][blackPawn8.column - 2].identityOfSquare == 16 && whitePawn8.beatingInTransit) {
                        whitePawn8.row = 0;
                        whitePawn8.column = 0;

                        moveBlackDone = true;
                    }

                    if (moveBlackDone) {
                        gameBoard[blackPawn8.row - 1][blackPawn8.column - 2].identityOfSquare = 0;
                        gameBoard[blackPawn8.row - 1][blackPawn8.column - 2].occupiedFieldByWHITE = false;
                        blackPawn8.row = rowBoard;
                        blackPawn8.column = columnBoard;
                    }


                } else {
                    moveBlackDone = blackPawn8.moveBlack(rowBoard, columnBoard, gameBoard, blackKing, this, whiteKnight1, whiteKnight2,
                            whiteBishop1, whiteBishop2,
                            whiteRook1, whiteRook2,
                            whiteQueen,
                            whitePawn1, whitePawn2, whitePawn3, whitePawn4,
                            whitePawn5, whitePawn6, whitePawn7, whitePawn8, whiteKing);
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

        if(blackBishop1.clicked){
            g2.drawRect(blackBishop1.column*40+4,blackBishop1.row*40+4,32,32);
        }else if(blackBishop2.clicked){
            g2.drawRect(blackBishop2.column*40+4,blackBishop2.row*40+4,32,32);
        }else if(blackKnight1.clicked){
            g2.drawRect(blackKnight1.column*40+4,blackKnight1.row*40+4,32,32);
        }else if(blackKnight2.clicked){
            g2.drawRect(blackKnight2.column*40+4,blackKnight2.row*40+4,32,32);
        }else if(blackRook1.clicked){
            g2.drawRect(blackRook1.column*40+4,blackRook1.row*40+4,32,32);
        }else if(blackRook2.clicked){
            g2.drawRect(blackRook2.column*40+4,blackRook2.row*40+4,32,32);
        }else if(blackKing.clicked){
            g2.drawRect(blackKing.column*40+4,blackKing.row*40+4,32,32);
        }else if(blackQueen.clicked){
            g2.drawRect(blackQueen.column*40+4,blackQueen.row*40+4,32,32);
        }else if(blackPawn1.clicked){
            g2.drawRect(blackPawn1.column*40+4,blackPawn1.row*40+4,32,32);
        }else if(blackPawn2.clicked){
            g2.drawRect(blackPawn2.column*40+4,blackPawn2.row*40+4,32,32);
        }else if(blackPawn3.clicked){
            g2.drawRect(blackPawn3.column*40+4,blackPawn3.row*40+4,32,32);
        }else if(blackPawn4.clicked){
            g2.drawRect(blackPawn4.column*40+4,blackPawn4.row*40+4,32,32);
        }else if(blackPawn5.clicked){
            g2.drawRect(blackPawn5.column*40+4,blackPawn5.row*40+4,32,32);
        }else if(blackPawn6.clicked){
            g2.drawRect(blackPawn6.column*40+4,blackPawn6.row*40+4,32,32);
        }else if(blackPawn7.clicked){
            g2.drawRect(blackPawn7.column*40+4,blackPawn7.row*40+4,32,32);
        }else if(blackPawn8.clicked){
            g2.drawRect(blackPawn8.column*40+4,blackPawn8.row*40+4,32,32);
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
            blackPawn8.clicked = false;


            if (blackMove && !whiteMove) {
                if (rowBoard == blackBishop1.row && columnBoard == blackBishop1.column) {
                    blackBishop1.clicked = true;
                } else if (rowBoard == blackBishop2.row && columnBoard == blackBishop2.column) {
                    blackBishop2.clicked = true;
                } else if (rowBoard == blackKnight1.row && columnBoard == blackKnight1.column) {
                    blackKnight1.clicked = true;
                } else if (rowBoard == blackKnight2.row && columnBoard == blackKnight2.column) {
                    blackKnight2.clicked = true;
                } else if (rowBoard == blackRook1.row && columnBoard == blackRook1.column) {
                    blackRook1.clicked = true;
                } else if (rowBoard == blackRook2.row && columnBoard == blackRook2.column) {
                    blackRook2.clicked = true;
                } else if (rowBoard == blackQueen.row && columnBoard == blackQueen.column) {
                    blackQueen.clicked = true;
                } else if (rowBoard == blackKing.row && columnBoard == blackKing.column) {
                    blackKing.clicked = true;
                } else if (rowBoard == blackPawn1.row && columnBoard == blackPawn1.column) {
                    blackPawn1.clicked = true;
                } else if (rowBoard == blackPawn2.row && columnBoard == blackPawn2.column) {
                    blackPawn2.clicked = true;
                } else if (rowBoard == blackPawn3.row && columnBoard == blackPawn3.column) {
                    blackPawn3.clicked = true;
                } else if (rowBoard == blackPawn4.row && columnBoard == blackPawn4.column) {
                    blackPawn4.clicked = true;
                } else if (rowBoard == blackPawn8.row && columnBoard == blackPawn8.column) {
                    blackPawn8.clicked = true;
                } else if (rowBoard == blackPawn5.row && columnBoard == blackPawn5.column) {
                    blackPawn5.clicked = true;
                } else if (rowBoard == blackPawn6.row && columnBoard == blackPawn6.column) {
                    blackPawn6.clicked = true;
                } else if (rowBoard == blackPawn7.row && columnBoard == blackPawn7.column) {
                    blackPawn7.clicked = true;
                }
            }


            repaint();

        }else if(clickCounter == 2) {

            if (rowBoard == rowBoardToDestroy && columnBoard == columnBoardToDestroy) {
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
                blackPawn8.clicked = false;
                clickCounter = 0;
                repaint();
            } else {
                justMove();
                clickCounter = 0;
                if (!moveBlackDone) {
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
                    blackPawn8.clicked = false;

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
        blackPawn8.clicked = false;


        if(whiteMove && blackMove == false){
            if(rowBoard == whiteBishop1.row && columnBoard == whiteBishop1.column ){
                whiteBishop1.clicked = true;
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
            }else if(rowBoard == whiteBishop2.row && columnBoard == whiteBishop2.column){
                whiteBishop1.clicked = false;
                whiteBishop2.clicked = true;
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
            }else if(rowBoard == whiteKnight1.row && columnBoard == whiteKnight1.column){
                whiteBishop1.clicked = false;
                whiteBishop2.clicked = false;
                whiteKnight1.clicked = true;
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
            }else if(rowBoard == whiteKnight2.row && columnBoard == whiteKnight2.column){
                whiteBishop1.clicked = false;
                whiteBishop2.clicked = false;
                whiteKnight1.clicked = false;
                whiteKnight2.clicked = true;
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
            }else if(rowBoard == whiteRook1.row && columnBoard == whiteRook1.column){
                whiteBishop1.clicked = false;
                whiteBishop2.clicked = false;
                whiteKnight1.clicked = false;
                whiteKnight2.clicked = false;
                whiteRook1.clicked = true;
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
            }else if(rowBoard == whiteRook2.row && columnBoard == whiteRook2.column){
                whiteBishop1.clicked = false;
                whiteBishop2.clicked = false;
                whiteKnight1.clicked = false;
                whiteKnight2.clicked = false;
                whiteRook1.clicked = false;
                whiteRook2.clicked = true;
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
            }else if(rowBoard == whiteQueen.row && columnBoard == whiteQueen.column){
                whiteBishop1.clicked = false;
                whiteBishop2.clicked = false;
                whiteKnight1.clicked = false;
                whiteKnight2.clicked = false;
                whiteRook1.clicked = false;
                whiteRook2.clicked = false;
                whiteQueen.clicked = true;
                whiteKing.clicked = false;
                whitePawn1.clicked = false;
                whitePawn2.clicked = false;
                whitePawn3.clicked = false;
                whitePawn4.clicked = false;
                whitePawn5.clicked = false;
                whitePawn6.clicked = false;
                whitePawn7.clicked = false;
                whitePawn8.clicked = false;
            }else if(rowBoard == whiteKing.row && columnBoard == whiteKing.column){
                whiteBishop1.clicked = false;
                whiteBishop2.clicked = false;
                whiteKnight1.clicked = false;
                whiteKnight2.clicked = false;
                whiteRook1.clicked = false;
                whiteRook2.clicked = false;
                whiteQueen.clicked = false;
                whiteKing.clicked = true;
                whitePawn1.clicked = false;
                whitePawn2.clicked = false;
                whitePawn3.clicked = false;
                whitePawn4.clicked = false;
                whitePawn5.clicked = false;
                whitePawn6.clicked = false;
                whitePawn7.clicked = false;
                whitePawn8.clicked = false;
            }else if(rowBoard == whitePawn1.row && columnBoard == whitePawn1.column){
                whiteBishop1.clicked = false;
                whiteBishop2.clicked = false;
                whiteKnight1.clicked = false;
                whiteKnight2.clicked = false;
                whiteRook1.clicked = false;
                whiteRook2.clicked = false;
                whiteQueen.clicked = false;
                whiteKing.clicked = false;
                whitePawn1.clicked = true;
                whitePawn2.clicked = false;
                whitePawn3.clicked = false;
                whitePawn4.clicked = false;
                whitePawn5.clicked = false;
                whitePawn6.clicked = false;
                whitePawn7.clicked = false;
                whitePawn8.clicked = false;
            }else if(rowBoard == whitePawn2.row && columnBoard == whitePawn2.column){
                whiteBishop1.clicked = false;
                whiteBishop2.clicked = false;
                whiteKnight1.clicked = false;
                whiteKnight2.clicked = false;
                whiteRook1.clicked = false;
                whiteRook2.clicked = false;
                whiteQueen.clicked = false;
                whiteKing.clicked = false;
                whitePawn1.clicked = false;
                whitePawn2.clicked = true;
                whitePawn3.clicked = false;
                whitePawn4.clicked = false;
                whitePawn5.clicked = false;
                whitePawn6.clicked = false;
                whitePawn7.clicked = false;
                whitePawn8.clicked = false;
            }else if(rowBoard == whitePawn3.row && columnBoard == whitePawn3.column){
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
                whitePawn3.clicked = true;
                whitePawn4.clicked = false;
                whitePawn5.clicked = false;
                whitePawn6.clicked = false;
                whitePawn7.clicked = false;
                whitePawn8.clicked = false;
            }else if(rowBoard == whitePawn4.row && columnBoard == whitePawn4.column){
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
                whitePawn4.clicked = true;
                whitePawn5.clicked = false;
                whitePawn6.clicked = false;
                whitePawn7.clicked = false;
                whitePawn8.clicked = false;
            }else if(rowBoard == whitePawn8.row && columnBoard == whitePawn8.column){
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
                whitePawn8.clicked = true;
            }else if(rowBoard == whitePawn5.row && columnBoard == whitePawn5.column){
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
                whitePawn5.clicked = true;
                whitePawn6.clicked = false;
                whitePawn7.clicked = false;
                whitePawn8.clicked = false;
            }else if(rowBoard == whitePawn6.row && columnBoard == whitePawn6.column){
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
                whitePawn6.clicked = true;
                whitePawn7.clicked = false;
                whitePawn8.clicked = false;
            }else if(rowBoard == whitePawn7.row && columnBoard == whitePawn7.column){
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
                whitePawn7.clicked = true;
                whitePawn8.clicked = false;
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

        //System.out.println(rowBoard+" "+ columnBoard);

        //Poruszanie się Białe
        if (columnBoard < 9 && rowBoard < 9 && columnBoard > 0 && rowBoard > 0) {

            if(whiteBishop1.clicked){
                if(rowBoard > whiteBishop1.row && columnBoard > whiteBishop1.column ) {
                    //move down-right bishop
                    shapeDiff = rowBoard - whiteBishop1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[whiteBishop1.row+i-1][whiteBishop1.column+i-1].occupiedFieldByWHITE == true || gameBoard[whiteBishop1.row+i-1][whiteBishop1.column+i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteBishop1.row][whiteBishop1.column].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;




                    if(rowBoard - whiteBishop1.row == columnBoard - whiteBishop1.column){
                        whiteBishop1.row = rowBoard;
                        whiteBishop1.column = columnBoard;
                        whiteBishop1.clicked = false;
                        moveWhiteDone = true;
                        repaint();
                    }

                }else if(rowBoard < whiteBishop1.row && columnBoard > whiteBishop1.column){
                    //move up-right bishop

                    shapeDiff = -rowBoard + whiteBishop1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[whiteBishop1.row-i-1][whiteBishop1.column+i-1].occupiedFieldByWHITE == true || gameBoard[whiteBishop1.row-i-1][whiteBishop1.column+i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteBishop1.row-2][whiteBishop1.column].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;



                    if(-rowBoard + whiteBishop1.row == columnBoard - whiteBishop1.column){
                        whiteBishop1.row = rowBoard;
                        whiteBishop1.column = columnBoard;
                        whiteBishop1.clicked = false;
                        moveWhiteDone = true;
                        repaint();
                    }


                }else if(rowBoard < whiteBishop1.row && columnBoard < whiteBishop1.column){
                    //move up-left bishop

                    shapeDiff = -rowBoard + whiteBishop1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[whiteBishop1.row-i-1][whiteBishop1.column-i-1].occupiedFieldByWHITE == true || gameBoard[whiteBishop1.row-i-1][whiteBishop1.column-i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteBishop1.row-2][whiteBishop1.column-2].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;



                    if(-rowBoard + whiteBishop1.row == -columnBoard + whiteBishop1.column){
                        whiteBishop1.row = rowBoard;
                        whiteBishop1.column = columnBoard;
                        whiteBishop1.clicked = false;
                        moveWhiteDone = true;
                        repaint();
                    }


                }else if(rowBoard > whiteBishop1.row && columnBoard < whiteBishop1.column){
                    //move down-left bishop

                    shapeDiff = rowBoard - whiteBishop1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[whiteBishop1.row+i-1][whiteBishop1.column-i-1].occupiedFieldByWHITE == true || gameBoard[whiteBishop1.row+i-1][whiteBishop1.column-i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteBishop1.row][whiteBishop1.column-2].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;



                    if(rowBoard - whiteBishop1.row == -columnBoard + whiteBishop1.column){
                        whiteBishop1.row = rowBoard;
                        whiteBishop1.column = columnBoard;
                        whiteBishop1.clicked = false;
                        moveWhiteDone = true;
                        repaint();
                    }

                }
            }else if(whiteBishop2.clicked){
                if(rowBoard > whiteBishop2.row && columnBoard > whiteBishop2.column ) {
                    //move down-right bishop
                    shapeDiff = rowBoard - whiteBishop2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[whiteBishop2.row+i-1][whiteBishop2.column+i-1].occupiedFieldByWHITE == true || gameBoard[whiteBishop2.row+i-1][whiteBishop2.column+i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteBishop2.row][whiteBishop2.column].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;



                    if(rowBoard - whiteBishop2.row == columnBoard - whiteBishop2.column){
                        whiteBishop2.row = rowBoard;
                        whiteBishop2.column = columnBoard;
                        whiteBishop2.clicked = false;
                        moveWhiteDone = true;
                        repaint();
                    }

                }else if(rowBoard < whiteBishop2.row && columnBoard > whiteBishop2.column){
                    //move up-right bishop

                    shapeDiff = -rowBoard + whiteBishop2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[whiteBishop2.row-i-1][whiteBishop2.column+i-1].occupiedFieldByWHITE == true || gameBoard[whiteBishop2.row-i-1][whiteBishop2.column+i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteBishop2.row-2][whiteBishop2.column].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;



                    if(-rowBoard + whiteBishop2.row == columnBoard - whiteBishop2.column){
                        whiteBishop2.row = rowBoard;
                        whiteBishop2.column = columnBoard;
                        whiteBishop2.clicked = false;
                        moveWhiteDone = true;
                        repaint();
                    }


                }else if(rowBoard < whiteBishop2.row && columnBoard < whiteBishop2.column){
                    //move up-left bishop

                    shapeDiff = -rowBoard + whiteBishop2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[whiteBishop2.row-i-1][whiteBishop2.column-i-1].occupiedFieldByWHITE == true || gameBoard[whiteBishop2.row-i-1][whiteBishop2.column-i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteBishop2.row-2][whiteBishop2.column-2].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;



                    if(-rowBoard + whiteBishop2.row == -columnBoard + whiteBishop2.column){
                        whiteBishop2.row = rowBoard;
                        whiteBishop2.column = columnBoard;
                        whiteBishop2.clicked = false;
                        moveWhiteDone = true;
                        repaint();
                    }


                }else if(rowBoard > whiteBishop2.row && columnBoard < whiteBishop2.column){
                    //move down-left bishop

                    shapeDiff = rowBoard - whiteBishop2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[whiteBishop2.row+i-1][whiteBishop2.column-i-1].occupiedFieldByWHITE == true || gameBoard[whiteBishop2.row+i-1][whiteBishop2.column-i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteBishop2.row][whiteBishop2.column-2].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;



                    if(rowBoard - whiteBishop2.row == -columnBoard + whiteBishop2.column){
                        whiteBishop2.row = rowBoard;
                        whiteBishop2.column = columnBoard;
                        whiteBishop2.clicked = false;
                        moveWhiteDone = true;
                        repaint();
                    }

                }
            }else if(whiteKnight1.clicked){
                if(((rowBoard == whiteKnight1.row-2 && columnBoard == whiteKnight1.column-1) ||
                        (rowBoard == whiteKnight1.row-1 && columnBoard == whiteKnight1.column-2) ||
                        (rowBoard == whiteKnight1.row-2 && columnBoard == whiteKnight1.column+1) ||
                        (rowBoard == whiteKnight1.row-1 && columnBoard == whiteKnight1.column+2) ||
                        (rowBoard == whiteKnight1.row+1 && columnBoard == whiteKnight1.column+2) ||
                        (rowBoard == whiteKnight1.row+2 && columnBoard == whiteKnight1.column+1) ||
                        (rowBoard == whiteKnight1.row+2 && columnBoard == whiteKnight1.column-1) ||
                        (rowBoard == whiteKnight1.row+1 && columnBoard == whiteKnight1.column-2))
                        && (gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == false && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == false || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == false && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true)

                ){
                    whiteKnight1.row = rowBoard;
                    whiteKnight1.column = columnBoard;
                    whiteKnight1.clicked = false;
                    moveWhiteDone = true;
                    repaint();
                }
            }else if(whiteKnight2.clicked){
                if(((rowBoard == whiteKnight2.row-2 && columnBoard == whiteKnight2.column-1) ||
                        (rowBoard == whiteKnight2.row-1 && columnBoard == whiteKnight2.column-2) ||
                        (rowBoard == whiteKnight2.row-2 && columnBoard == whiteKnight2.column+1) ||
                        (rowBoard == whiteKnight2.row-1 && columnBoard == whiteKnight2.column+2) ||
                        (rowBoard == whiteKnight2.row+1 && columnBoard == whiteKnight2.column+2) ||
                        (rowBoard == whiteKnight2.row+2 && columnBoard == whiteKnight2.column+1) ||
                        (rowBoard == whiteKnight2.row+2 && columnBoard == whiteKnight2.column-1) ||
                        (rowBoard == whiteKnight2.row+1 && columnBoard == whiteKnight2.column-2))
                        && (gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == false && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == false || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == false && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true)

                ) {
                    whiteKnight2.row = rowBoard;
                    whiteKnight2.column = columnBoard;
                    whiteKnight2.clicked = false;
                    moveWhiteDone = true;
                    repaint();
                }
            }else if(whiteRook1.clicked){
                if(whiteRook1.row < rowBoard && whiteRook1.column == columnBoard)
                {
                    //move down Rook
                    shapeDiff = rowBoard - whiteRook1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[whiteRook1.row+i-1][columnBoard-1].occupiedFieldByWHITE == true || gameBoard[whiteRook1.row+i-1][columnBoard-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteRook1.row][whiteRook1.column-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;

                    whiteRook1.row = rowBoard;
                    whiteRook1.column = columnBoard;
                    whiteRook1.clicked = false;
                    moveWhiteDone = true;
                    whiteRook1CastlingAccess = false;
                    repaint();

                }else if(whiteRook1.row == rowBoard && whiteRook1.column < columnBoard)
                {
                    //move right Rook
                    shapeDiff = columnBoard - whiteRook1.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[rowBoard-1][whiteRook1.column+i-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][whiteRook1.column+i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteRook1.row-1][whiteRook1.column].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;

                    whiteRook1.row = rowBoard;
                    whiteRook1.column = columnBoard;
                    whiteRook1.clicked = false;
                    moveWhiteDone = true;
                    whiteRook1CastlingAccess = false;
                    repaint();

                }else if(whiteRook1.row > rowBoard && whiteRook1.column == columnBoard)
                {
                    //move up Rook
                    shapeDiff = -rowBoard + whiteRook1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[whiteRook1.row-i-1][columnBoard-1].occupiedFieldByWHITE == true || gameBoard[whiteRook1.row-i-1][columnBoard-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteRook1.row-2][whiteRook1.column-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;

                    whiteRook1.row = rowBoard;
                    whiteRook1.column = columnBoard;
                    whiteRook1.clicked = false;
                    moveWhiteDone = true;
                    whiteRook1CastlingAccess = false;
                    repaint();

                }else if(whiteRook1.row == rowBoard && whiteRook1.column > columnBoard)
                {
                    //move left Rook
                    shapeDiff = -columnBoard + whiteRook1.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[rowBoard-1][whiteRook1.column-i-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][whiteRook1.column-i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteRook1.row-1][whiteRook1.column-2].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;

                    whiteRook1.row = rowBoard;
                    whiteRook1.column = columnBoard;
                    whiteRook1.clicked = false;
                    moveWhiteDone = true;
                    whiteRook1CastlingAccess = false;
                    repaint();

                }


            }else if(whiteRook2.clicked){
                if(whiteRook2.row < rowBoard && whiteRook2.column == columnBoard)
                {
                    //move down Rook
                    shapeDiff = rowBoard - whiteRook2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[whiteRook2.row+i-1][columnBoard-1].occupiedFieldByWHITE == true || gameBoard[whiteRook2.row+i-1][columnBoard-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteRook2.row][whiteRook2.column-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;

                    whiteRook2.row = rowBoard;
                    whiteRook2.column = columnBoard;
                    whiteRook2.clicked = false;
                    moveWhiteDone = true;
                    whiteRook2CastlingAccess = false;
                    repaint();

                }else if(whiteRook2.row == rowBoard && whiteRook2.column < columnBoard)
                {
                    //move right Rook
                    shapeDiff = columnBoard - whiteRook2.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[rowBoard-1][whiteRook2.column+i-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][whiteRook2.column+i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteRook2.row-1][whiteRook2.column].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;

                    whiteRook2.row = rowBoard;
                    whiteRook2.column = columnBoard;
                    whiteRook2.clicked = false;
                    moveWhiteDone = true;
                    whiteRook2CastlingAccess = false;
                    repaint();

                }else if(whiteRook2.row > rowBoard && whiteRook2.column == columnBoard)
                {
                    //move up Rook
                    shapeDiff = -rowBoard + whiteRook2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[whiteRook2.row-i-1][columnBoard-1].occupiedFieldByWHITE == true || gameBoard[whiteRook2.row-i-1][columnBoard-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteRook2.row-2][whiteRook2.column-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;

                    whiteRook2.row = rowBoard;
                    whiteRook2.column = columnBoard;
                    whiteRook2.clicked = false;
                    moveWhiteDone = true;
                    whiteRook2CastlingAccess = false;
                    repaint();

                }else if(whiteRook2.row == rowBoard && whiteRook2.column > columnBoard)
                {
                    //move left Rook
                    shapeDiff = -columnBoard + whiteRook2.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[rowBoard-1][whiteRook2.column-i-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][whiteRook2.column-i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteRook2.row-1][whiteRook2.column-2].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;

                    whiteRook2.row = rowBoard;
                    whiteRook2.column = columnBoard;
                    whiteRook2.clicked = false;
                    moveWhiteDone = true;
                    whiteRook2CastlingAccess = false;
                    repaint();

                }


            }else if(whiteQueen.clicked){
                if(rowBoard > whiteQueen.row && columnBoard > whiteQueen.column ) {
                    //move down-right bishop
                    shapeDiff = rowBoard - whiteQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[whiteQueen.row+i-1][whiteQueen.column+i-1].occupiedFieldByWHITE == true || gameBoard[whiteQueen.row+i-1][whiteQueen.column+i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteQueen.row][whiteQueen.column].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;




                    if(rowBoard - whiteQueen.row == columnBoard - whiteQueen.column){
                        whiteQueen.row = rowBoard;
                        whiteQueen.column = columnBoard;
                        whiteQueen.clicked = false;
                        moveWhiteDone = true;
                        repaint();
                    }

                }else if(rowBoard < whiteQueen.row && columnBoard > whiteQueen.column){
                    //move up-right bishop

                    shapeDiff = -rowBoard + whiteQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[whiteQueen.row-i-1][whiteQueen.column+i-1].occupiedFieldByWHITE == true || gameBoard[whiteQueen.row-i-1][whiteQueen.column+i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteQueen.row-2][whiteQueen.column].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;



                    if(-rowBoard + whiteQueen.row == columnBoard - whiteQueen.column){
                        whiteQueen.row = rowBoard;
                        whiteQueen.column = columnBoard;
                        whiteQueen.clicked = false;
                        moveWhiteDone = true;
                        repaint();
                    }


                }else if(rowBoard < whiteQueen.row && columnBoard < whiteQueen.column){
                    //move up-left bishop

                    shapeDiff = -rowBoard + whiteQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[whiteQueen.row-i-1][whiteQueen.column-i-1].occupiedFieldByWHITE == true || gameBoard[whiteQueen.row-i-1][whiteQueen.column-i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteQueen.row-2][whiteQueen.column-2].occupiedFieldByWHITE || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;


                    if(-rowBoard + whiteQueen.row == -columnBoard + whiteQueen.column){
                        whiteQueen.row = rowBoard;
                        whiteQueen.column = columnBoard;
                        whiteQueen.clicked = false;
                        moveWhiteDone = true;
                        repaint();
                    }


                }else if(rowBoard > whiteQueen.row && columnBoard < whiteQueen.column){
                    //move down-left bishop

                    shapeDiff = rowBoard - whiteQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[whiteQueen.row+i-1][whiteQueen.column-i-1].occupiedFieldByWHITE == true || gameBoard[whiteQueen.row+i-1][whiteQueen.column-i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteQueen.row][whiteQueen.column-2].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;



                    if(rowBoard - whiteQueen.row == -columnBoard + whiteQueen.column){
                        whiteQueen.row = rowBoard;
                        whiteQueen.column = columnBoard;
                        whiteQueen.clicked = false;
                        moveWhiteDone = true;
                        repaint();
                    }

                }else if(whiteQueen.row < rowBoard && whiteQueen.column == columnBoard)
                {
                    //move down Rook
                    shapeDiff = rowBoard - whiteQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[whiteQueen.row+i-1][columnBoard-1].occupiedFieldByWHITE == true || gameBoard[whiteQueen.row+i-1][columnBoard-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteQueen.row][whiteQueen.column-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;

                    whiteQueen.row = rowBoard;
                    whiteQueen.column = columnBoard;
                    whiteQueen.clicked = false;
                    moveWhiteDone = true;
                    repaint();

                }else if(whiteQueen.row == rowBoard && whiteQueen.column < columnBoard)
                {
                    //move right Rook
                    shapeDiff = columnBoard - whiteQueen.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[rowBoard-1][whiteQueen.column+i-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][whiteQueen.column+i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteQueen.row-1][whiteQueen.column].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;

                    whiteQueen.row = rowBoard;
                    whiteQueen.column = columnBoard;
                    whiteQueen.clicked = false;
                    moveWhiteDone = true;
                    repaint();

                }else if(whiteQueen.row > rowBoard && whiteQueen.column == columnBoard)
                {
                    //move up Rook
                    shapeDiff = -rowBoard + whiteQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[whiteQueen.row-i-1][columnBoard-1].occupiedFieldByWHITE == true || gameBoard[whiteQueen.row-i-1][columnBoard-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteQueen.row-2][whiteQueen.column-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;

                    whiteQueen.row = rowBoard;
                    whiteQueen.column = columnBoard;
                    whiteQueen.clicked = false;
                    moveWhiteDone = true;
                    repaint();

                }else if(whiteQueen.row == rowBoard && whiteQueen.column > columnBoard)
                {
                    //move left Rook
                    shapeDiff = -columnBoard + whiteQueen.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(gameBoard[rowBoard-1][whiteQueen.column-i-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][whiteQueen.column-i-1].occupiedFieldByBlack == true){
                            return;
                        }
                    }
                    if(gameBoard[whiteQueen.row-1][whiteQueen.column-2].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) return;

                    whiteQueen.row = rowBoard;
                    whiteQueen.column = columnBoard;
                    whiteQueen.clicked = false;
                    moveWhiteDone = true;
                    repaint();

                }

            }else if(whiteKing.clicked){
                if(((rowBoard == whiteKing.row-1 && columnBoard == whiteKing.column-1 ) ||
                        (rowBoard == whiteKing.row && columnBoard == whiteKing.column-1) ||
                        (rowBoard == whiteKing.row-1 && columnBoard == whiteKing.column) ||
                        (rowBoard == whiteKing.row-1 && columnBoard == whiteKing.column+1) ||
                        (rowBoard == whiteKing.row && columnBoard == whiteKing.column+1) ||
                        (rowBoard == whiteKing.row+1 && columnBoard == whiteKing.column+1) ||
                        (rowBoard == whiteKing.row+1 && columnBoard == whiteKing.column) ||
                        (rowBoard == whiteKing.row+1 && columnBoard == whiteKing.column-1)) && (gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == false && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == false && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == false)
                ){
                    whiteKing.row = rowBoard;
                    whiteKing.column = columnBoard;
                    whiteKing.clicked = false;
                    moveWhiteDone = true;
                    whiteKingCastlingAccess = false;
                    repaint();
                }else if(rowBoard == whiteKing.row && columnBoard == whiteKing.column+2
                        && gameBoard[whiteKing.row-1][whiteKing.column].occupiedFieldByWHITE == false
                        && gameBoard[whiteKing.row-1][whiteKing.column].occupiedFieldByBlack == false
                        && gameBoard[whiteKing.row-1][whiteKing.column+1].occupiedFieldByWHITE == false
                        && gameBoard[whiteKing.row-1][whiteKing.column+1].occupiedFieldByBlack == false
                        && whiteKingCastlingAccess == true
                        && whiteRook2CastlingAccess == true)
                {
                    whiteKing.row = rowBoard;
                    whiteKing.column = columnBoard;
                    whiteRook2.row = 1;
                    whiteRook2.column = 6;
                    gameBoard[0][7].occupiedFieldByWHITE = false;
                    gameBoard[0][7].occupiedFieldByBlack = false;
                    gameBoard[0][5].occupiedFieldByWHITE = true;
                    gameBoard[0][5].occupiedFieldByBlack = false;
                    whiteKing.clicked = false;
                    moveWhiteDone = true;
                    whiteKingCastlingAccess = false;
                    whiteRook2CastlingAccess = false;
                    repaint();
                }else if(rowBoard == whiteKing.row && columnBoard == whiteKing.column-2
                        && gameBoard[whiteKing.row-1][whiteKing.column-2].occupiedFieldByWHITE == false
                        && gameBoard[whiteKing.row-1][whiteKing.column-2].occupiedFieldByBlack == false
                        && gameBoard[whiteKing.row-1][whiteKing.column-3].occupiedFieldByWHITE == false
                        && gameBoard[whiteKing.row-1][whiteKing.column-3].occupiedFieldByBlack == false
                        && gameBoard[whiteKing.row-1][whiteKing.column-4].occupiedFieldByWHITE == false
                        && gameBoard[whiteKing.row-1][whiteKing.column-4].occupiedFieldByBlack == false
                        && whiteKingCastlingAccess == true
                        && whiteRook1CastlingAccess == true
                ){
                    whiteKing.row = rowBoard;
                    whiteKing.column = columnBoard;
                    whiteRook1.row = 1;
                    whiteRook1.column = 4;
                    gameBoard[0][0].occupiedFieldByWHITE = false;
                    gameBoard[0][0].occupiedFieldByBlack = false;
                    gameBoard[0][3].occupiedFieldByWHITE = true;
                    gameBoard[0][3].occupiedFieldByBlack = false;
                    whiteKing.clicked = false;
                    moveWhiteDone = true;
                    whiteKingCastlingAccess = false;
                    whiteRook1CastlingAccess = false;
                    repaint();

                }
            }else if(whitePawn1.clicked){
                //BICIE PIONKA DZIAŁA , NIE MOZE TEZ WDEPNAC PIONEK W PIONKA , RUCH Z POZYCJI WYJSCIOWEJ O 2 POLA DOZWOLONY, BRAK BICIA W PRZELOCIE
                if((rowBoard == whitePawn1.row + 2 && whitePawn1.row == 2 && whitePawn1.column == 1 && columnBoard == whitePawn1.column && gameBoard[whitePawn1.row][whitePawn1.column-1].occupiedFieldByBlack == false && gameBoard[whitePawn1.row][whitePawn1.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == whitePawn1.row + 1 && columnBoard == whitePawn1.column && gameBoard[whitePawn1.row][whitePawn1.column-1].occupiedFieldByBlack == false && gameBoard[whitePawn1.row][whitePawn1.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == whitePawn1.row + 1 && columnBoard != whitePawn1.column && whitePawn1.column != 8 && (gameBoard[whitePawn1.row][whitePawn1.column].occupiedFieldByBlack == true || gameBoard[whitePawn1.row][whitePawn1.column-2].occupiedFieldByBlack == true) ) ||
                        (rowBoard == whitePawn1.row + 1 && columnBoard != whitePawn1.column && whitePawn1.column == 8 && gameBoard[whitePawn1.row][whitePawn1.column-2].occupiedFieldByBlack == true)
                ) {
                    whitePawn1.row = rowBoard;
                    whitePawn1.column = columnBoard;
                    whitePawn1.clicked = false;
                    moveWhiteDone = true;
                    repaint();
                }
            }else if(whitePawn2.clicked){
                if((rowBoard == whitePawn2.row + 2 && whitePawn2.row == 2 && whitePawn2.column == 2 && columnBoard == whitePawn2.column && gameBoard[whitePawn2.row][whitePawn2.column-1].occupiedFieldByBlack == false && gameBoard[whitePawn2.row][whitePawn2.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == whitePawn2.row + 1 && columnBoard == whitePawn2.column && gameBoard[whitePawn2.row][whitePawn2.column-1].occupiedFieldByBlack == false && gameBoard[whitePawn2.row][whitePawn2.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == whitePawn2.row + 1 && columnBoard != whitePawn2.column && whitePawn2.column != 8 && (gameBoard[whitePawn2.row][whitePawn2.column].occupiedFieldByBlack == true || gameBoard[whitePawn2.row][whitePawn2.column-2].occupiedFieldByBlack == true) ) ||
                        (rowBoard == whitePawn2.row + 1 && columnBoard != whitePawn2.column && whitePawn2.column == 8 && gameBoard[whitePawn2.row][whitePawn2.column-2].occupiedFieldByBlack == true)
                ) {
                    whitePawn2.row = rowBoard;
                    whitePawn2.column = columnBoard;
                    whitePawn2.clicked = false;
                    moveWhiteDone = true;
                    repaint();
                }
            }else if(whitePawn3.clicked){
                if((rowBoard == whitePawn3.row + 2 && whitePawn3.row == 2 && whitePawn3.column == 3 && columnBoard == whitePawn3.column && gameBoard[whitePawn3.row][whitePawn3.column-1].occupiedFieldByBlack == false && gameBoard[whitePawn3.row][whitePawn3.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == whitePawn3.row + 1 && columnBoard == whitePawn3.column && gameBoard[whitePawn3.row][whitePawn3.column-1].occupiedFieldByBlack == false && gameBoard[whitePawn3.row][whitePawn3.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == whitePawn3.row + 1 && columnBoard != whitePawn3.column && whitePawn3.column != 8 && (gameBoard[whitePawn3.row][whitePawn3.column].occupiedFieldByBlack == true || gameBoard[whitePawn3.row][whitePawn3.column-2].occupiedFieldByBlack == true) ) ||
                        (rowBoard == whitePawn3.row + 1 && columnBoard != whitePawn3.column && whitePawn3.column == 8 && gameBoard[whitePawn3.row][whitePawn3.column-2].occupiedFieldByBlack == true)
                )
                {whitePawn3.row = rowBoard;
                    whitePawn3.column = columnBoard;
                    whitePawn3.clicked = false;
                    moveWhiteDone = true;
                    repaint();}

            }else if(whitePawn4.clicked){
                if((rowBoard == whitePawn4.row + 2 && whitePawn4.row == 2 && whitePawn4.column == 4 && columnBoard == whitePawn4.column && gameBoard[whitePawn4.row][whitePawn4.column-1].occupiedFieldByBlack == false && gameBoard[whitePawn4.row][whitePawn4.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == whitePawn4.row + 1 && columnBoard == whitePawn4.column && gameBoard[whitePawn4.row][whitePawn4.column-1].occupiedFieldByBlack == false && gameBoard[whitePawn4.row][whitePawn4.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == whitePawn4.row + 1 && columnBoard != whitePawn4.column && whitePawn4.column != 8 && (gameBoard[whitePawn4.row][whitePawn4.column].occupiedFieldByBlack == true || gameBoard[whitePawn4.row][whitePawn4.column-2].occupiedFieldByBlack == true) ) ||
                        (rowBoard == whitePawn4.row + 1 && columnBoard != whitePawn4.column && whitePawn4.column == 8 && gameBoard[whitePawn4.row][whitePawn4.column-2].occupiedFieldByBlack == true)
                )
                {whitePawn4.row = rowBoard;
                    whitePawn4.column = columnBoard;
                    whitePawn4.clicked = false;
                    moveWhiteDone = true;
                    repaint();}

            }else if(whitePawn5.clicked){
                if((rowBoard == whitePawn5.row + 2 && whitePawn5.row == 2 && whitePawn5.column == 5 && columnBoard == whitePawn5.column && gameBoard[whitePawn5.row][whitePawn5.column-1].occupiedFieldByBlack == false && gameBoard[whitePawn5.row][whitePawn5.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == whitePawn5.row + 1 && columnBoard == whitePawn5.column && gameBoard[whitePawn5.row][whitePawn5.column-1].occupiedFieldByBlack == false && gameBoard[whitePawn5.row][whitePawn5.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == whitePawn5.row + 1 && columnBoard != whitePawn5.column && whitePawn5.column != 8 && (gameBoard[whitePawn5.row][whitePawn5.column].occupiedFieldByBlack == true || gameBoard[whitePawn5.row][whitePawn5.column-2].occupiedFieldByBlack == true) ) ||
                        (rowBoard == whitePawn5.row + 1 && columnBoard != whitePawn5.column && whitePawn5.column == 8 && gameBoard[whitePawn5.row][whitePawn5.column-2].occupiedFieldByBlack == true)
                )
                {whitePawn5.row = rowBoard;
                    whitePawn5.column = columnBoard;
                    whitePawn5.clicked = false;
                    moveWhiteDone = true;
                    repaint();}

            }else if(whitePawn6.clicked){
                if((rowBoard == whitePawn6.row + 2 && whitePawn6.row == 2 && whitePawn6.column == 6 && columnBoard == whitePawn6.column && gameBoard[whitePawn6.row][whitePawn6.column-1].occupiedFieldByBlack == false && gameBoard[whitePawn6.row][whitePawn6.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == whitePawn6.row + 1 && columnBoard == whitePawn6.column && gameBoard[whitePawn6.row][whitePawn6.column-1].occupiedFieldByBlack == false && gameBoard[whitePawn6.row][whitePawn6.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == whitePawn6.row + 1 && columnBoard != whitePawn6.column && whitePawn6.column != 8 && (gameBoard[whitePawn6.row][whitePawn6.column].occupiedFieldByBlack == true || gameBoard[whitePawn6.row][whitePawn6.column-2].occupiedFieldByBlack == true) ) ||
                        (rowBoard == whitePawn6.row + 1 && columnBoard != whitePawn6.column && whitePawn6.column == 8 && gameBoard[whitePawn6.row][whitePawn6.column-2].occupiedFieldByBlack == true)
                )
                {whitePawn6.row = rowBoard;
                    whitePawn6.column = columnBoard;
                    whitePawn6.clicked = false;
                    moveWhiteDone = true;
                    repaint();}

            }else if(whitePawn7.clicked){
                if((rowBoard == whitePawn7.row + 2 && whitePawn7.row == 2 && whitePawn7.column == 7 && columnBoard == whitePawn7.column && gameBoard[whitePawn7.row][whitePawn7.column-1].occupiedFieldByBlack == false && gameBoard[whitePawn7.row][whitePawn7.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == whitePawn7.row + 1 && columnBoard == whitePawn7.column && gameBoard[whitePawn7.row][whitePawn7.column-1].occupiedFieldByBlack == false && gameBoard[whitePawn7.row][whitePawn7.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == whitePawn7.row + 1 && columnBoard != whitePawn7.column && whitePawn7.column != 8 && (gameBoard[whitePawn7.row][whitePawn7.column].occupiedFieldByBlack == true || gameBoard[whitePawn7.row][whitePawn7.column-2].occupiedFieldByBlack == true) ) ||
                        (rowBoard == whitePawn7.row + 1 && columnBoard != whitePawn7.column && whitePawn7.column == 8 && gameBoard[whitePawn7.row][whitePawn7.column-2].occupiedFieldByBlack == true)
                )
                {whitePawn7.row = rowBoard;
                    whitePawn7.column = columnBoard;
                    whitePawn7.clicked = false;
                    moveWhiteDone = true;
                    repaint();}

            }else if(whitePawn8.clicked){
                if((rowBoard == whitePawn8.row + 2 && whitePawn8.row == 2 && whitePawn8.column == 8 && columnBoard == whitePawn8.column && gameBoard[whitePawn8.row][whitePawn8.column-1].occupiedFieldByBlack == false && gameBoard[whitePawn8.row][whitePawn8.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == whitePawn8.row + 1 && columnBoard == whitePawn8.column && gameBoard[whitePawn8.row][whitePawn8.column-1].occupiedFieldByBlack == false && gameBoard[whitePawn8.row][whitePawn8.column-1].occupiedFieldByWHITE == false) ||
                        (rowBoard == whitePawn8.row + 1 && columnBoard != whitePawn8.column && whitePawn8.column != 8 && (gameBoard[whitePawn8.row][whitePawn8.column].occupiedFieldByBlack == true || gameBoard[whitePawn8.row][whitePawn8.column-2].occupiedFieldByBlack == true) ) ||
                        (rowBoard == whitePawn8.row + 1 && columnBoard != whitePawn8.column && whitePawn8.column == 8 && gameBoard[whitePawn8.row][whitePawn8.column-2].occupiedFieldByBlack == true)
                )
                {whitePawn8.row = rowBoard;
                    whitePawn8.column = columnBoard;
                    whitePawn8.clicked = false;
                    moveWhiteDone = true;
                    repaint();}

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
                blackMove = true;
                whiteMove = false;
                moveWhiteDone = false;

            } else if (moveBlackDone) {
                moveSound("moveSound.wav");


                checkCollision();
                removeOptionsForAll();

                setAdditionalOptionsForBlack(rowBoardToDestroy, columnBoardToDestroy, rowBoard, columnBoard);


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

                    if (blackPawn1.row == 1 || blackPawn2.row == 1 || blackPawn3.row == 1 || blackPawn4.row == 1 ||
                            blackPawn5.row == 1 || blackPawn6.row == 1 || blackPawn7.row == 1 || blackPawn8.row == 1
                    ) {
                        oos.writeObject(rowBoardToDestroy);
                        oos.flush();
                        oos.writeObject(columnBoardToDestroy);
                        oos.flush();
                        oos.writeObject(rowBoard);
                        oos.flush();
                        oos.writeObject(columnBoard);
                        oos.flush();
                        if (blackPawn1.row == 1) {
                            while (blackPawn1.transformation == 0) {
                                sleep(500);
                            }
                            oos.writeObject(blackPawn1.transformation);
                            oos.flush();
                        } else if (blackPawn2.row == 1) {
                            while (blackPawn2.transformation == 0) {
                                sleep(500);
                            }
                            oos.writeObject(blackPawn2.transformation);
                            oos.flush();
                        } else if (blackPawn3.row == 1) {
                            while (blackPawn3.transformation == 0) {
                                sleep(500);
                            }
                            oos.writeObject(blackPawn3.transformation);
                            oos.flush();
                        } else if (blackPawn4.row == 1) {
                            while (blackPawn4.transformation == 0) {
                                sleep(500);
                            }
                            oos.writeObject(blackPawn4.transformation);
                            oos.flush();
                        } else if (blackPawn5.row == 1) {
                            while (blackPawn5.transformation == 0) {
                                sleep(500);
                            }
                            oos.writeObject(blackPawn5.transformation);
                            oos.flush();
                        } else if (blackPawn6.row == 1) {
                            while (blackPawn6.transformation == 0) {
                                sleep(500);
                            }
                            oos.writeObject(blackPawn6.transformation);
                            oos.flush();
                        } else if (blackPawn7.row == 1) {
                            while (blackPawn7.transformation == 0) {
                                sleep(500);
                            }
                            oos.writeObject(blackPawn7.transformation);
                            oos.flush();
                        } else if (blackPawn8.row == 1) {
                            while (blackPawn8.transformation == 0) {
                                sleep(500);
                            }
                            oos.writeObject(blackPawn8.transformation);
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
                blackMove = false;
                whiteMove = true;
                moveBlackDone = false;

            }
            //System.out.println(gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE+"   "+gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack+"  Identyfikator = "+gameBoard[rowBoard-1][columnBoard-1].identityOfSquare);
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

                if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 9 && rowBoardOdebrany == 8) {
                    whitePawn1.transformation = (int) ois.readObject();
                    if (whitePawn1.transformation == 1) {
                        whitePawn1.image = whiteRookImage;
                    } else if (whitePawn1.transformation == 2) {
                        whitePawn1.image = whiteQueenImage;
                    } else if (whitePawn1.transformation == 3) {
                        whitePawn1.image = whiteBishopImage;
                    } else if (whitePawn1.transformation == 4) {
                        whitePawn1.image = whiteKnightImage;
                    }
                } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 10 && rowBoardOdebrany == 8) {
                    whitePawn2.transformation = (int) ois.readObject();
                    if (whitePawn2.transformation == 1) {
                        whitePawn2.image = whiteRookImage;
                    } else if (whitePawn2.transformation == 2) {
                        whitePawn2.image = whiteQueenImage;
                    } else if (whitePawn2.transformation == 3) {
                        whitePawn2.image = whiteBishopImage;
                    } else if (whitePawn2.transformation == 4) {
                        whitePawn2.image = whiteKnightImage;
                    }
                } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 11 && rowBoardOdebrany == 8) {
                    whitePawn3.transformation = (int) ois.readObject();
                    if (whitePawn3.transformation == 1) {
                        whitePawn3.image = whiteRookImage;
                    } else if (whitePawn3.transformation == 2) {
                        whitePawn3.image = whiteQueenImage;
                    } else if (whitePawn3.transformation == 3) {
                        whitePawn3.image = whiteBishopImage;
                    } else if (whitePawn3.transformation == 4) {
                        whitePawn3.image = whiteKnightImage;
                    }
                } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 12 && rowBoardOdebrany == 8) {
                    whitePawn4.transformation = (int) ois.readObject();
                    if (whitePawn4.transformation == 1) {
                        whitePawn4.image = whiteRookImage;
                    } else if (whitePawn4.transformation == 2) {
                        whitePawn4.image = whiteQueenImage;
                    } else if (whitePawn4.transformation == 3) {
                        whitePawn4.image = whiteBishopImage;
                    } else if (whitePawn4.transformation == 4) {
                        whitePawn4.image = whiteKnightImage;
                    }
                } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 13 && rowBoardOdebrany == 8) {
                    whitePawn5.transformation = (int) ois.readObject();
                    if (whitePawn5.transformation == 1) {
                        whitePawn5.image = whiteRookImage;
                    } else if (whitePawn5.transformation == 2) {
                        whitePawn5.image = whiteQueenImage;
                    } else if (whitePawn5.transformation == 3) {
                        whitePawn5.image = whiteBishopImage;
                    } else if (whitePawn5.transformation == 4) {
                        whitePawn5.image = whiteKnightImage;
                    }
                } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 14 && rowBoardOdebrany == 8) {
                    whitePawn6.transformation = (int) ois.readObject();
                    if (whitePawn6.transformation == 1) {
                        whitePawn6.image = whiteRookImage;
                    } else if (whitePawn6.transformation == 2) {
                        whitePawn6.image = whiteQueenImage;
                    } else if (whitePawn6.transformation == 3) {
                        whitePawn6.image = whiteBishopImage;
                    } else if (whitePawn6.transformation == 4) {
                        whitePawn6.image = whiteKnightImage;
                    }
                } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 15 && rowBoardOdebrany == 8) {
                    whitePawn7.transformation = (int) ois.readObject();
                    if (whitePawn7.transformation == 1) {
                        whitePawn7.image = whiteRookImage;
                    } else if (whitePawn7.transformation == 2) {
                        whitePawn7.image = whiteQueenImage;
                    } else if (whitePawn7.transformation == 3) {
                        whitePawn7.image = whiteBishopImage;
                    } else if (whitePawn7.transformation == 4) {
                        whitePawn7.image = whiteKnightImage;
                    }
                } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 16 && rowBoardOdebrany == 8) {
                    whitePawn8.transformation = (int) ois.readObject();
                    if (whitePawn8.transformation == 1) {
                        whitePawn8.image = whiteRookImage;
                    } else if (whitePawn8.transformation == 2) {
                        whitePawn8.image = whiteQueenImage;
                    } else if (whitePawn8.transformation == 3) {
                        whitePawn8.image = whiteBishopImage;
                    } else if (whitePawn8.transformation == 4) {
                        whitePawn8.image = whiteKnightImage;
                    }
                }

                moveSound("moveSound.wav");


            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 1) {
                whiteRook1.row = rowBoardOdebrany;
                whiteRook1.column = columnBoardOdebrany;
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 2) {
                whiteKnight1.row = rowBoardOdebrany;
                whiteKnight1.column = columnBoardOdebrany;
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 3) {
                whiteBishop1.row = rowBoardOdebrany;
                whiteBishop1.column = columnBoardOdebrany;
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 4) {
                whiteQueen.row = rowBoardOdebrany;
                whiteQueen.column = columnBoardOdebrany;
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 5) {

                if (whiteKing.column == columnBoardOdebrany + 2) {
                    gameBoard[whiteRook1.row - 1][whiteRook1.column - 1].occupiedFieldByWHITE = false;
                    whiteRook1.row = 1;
                    whiteRook1.column = 4;
                    gameBoard[whiteRook1.row - 1][whiteRook1.column - 1].identityOfSquare = 1;

                    gameBoard[0][0].identityOfSquare = 0;

                } else if (whiteKing.column == columnBoardOdebrany - 2) {
                    gameBoard[whiteRook2.row - 1][whiteRook2.column - 1].occupiedFieldByWHITE = false;
                    whiteRook2.row = 1;
                    whiteRook2.column = 6;
                    gameBoard[whiteRook2.row - 1][whiteRook2.column - 1].identityOfSquare = 8;

                    gameBoard[0][7].identityOfSquare = 0;
                }

                whiteKing.row = rowBoardOdebrany;
                whiteKing.column = columnBoardOdebrany;

            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 6) {
                whiteBishop2.row = rowBoardOdebrany;
                whiteBishop2.column = columnBoardOdebrany;
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 7) {
                whiteKnight2.row = rowBoardOdebrany;
                whiteKnight2.column = columnBoardOdebrany;
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 8) {
                whiteRook2.row = rowBoardOdebrany;
                whiteRook2.column = columnBoardOdebrany;
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 9) {
                if (whitePawn1.row + 2 == rowBoardOdebrany && whitePawn1.column == columnBoardOdebrany && gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany].occupiedFieldByBlack) {
                    whitePawn1.beatingInTransit = true;
                }
                if (whitePawn1.row == 5 && !gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 1].occupiedFieldByBlack && whitePawn1.column != columnBoardOdebrany) {
                    if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                    }
                    gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare = 0;
                    gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].occupiedFieldByBlack = false;
                    whitePawn1.row = rowBoardOdebrany;
                    whitePawn1.column = columnBoardOdebrany;
                } else {
                    whitePawn1.row = rowBoardOdebrany;
                    whitePawn1.column = columnBoardOdebrany;
                }
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 10) {

                if (whitePawn2.row + 2 == rowBoardOdebrany && whitePawn2.column == columnBoardOdebrany && (gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany].occupiedFieldByBlack || gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 2].occupiedFieldByBlack)) {
                    whitePawn2.beatingInTransit = true;
                }

                if (whitePawn2.row == 5 && !gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 1].occupiedFieldByBlack && whitePawn2.column != columnBoardOdebrany) {
                    if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                    }
                    gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare = 0;
                    gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].occupiedFieldByBlack = false;
                    whitePawn2.row = rowBoardOdebrany;
                    whitePawn2.column = columnBoardOdebrany;
                } else {
                    whitePawn2.row = rowBoardOdebrany;
                    whitePawn2.column = columnBoardOdebrany;
                }
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 11) {
                if (whitePawn3.row + 2 == rowBoardOdebrany && whitePawn3.column == columnBoardOdebrany && (gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany].occupiedFieldByBlack || gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 2].occupiedFieldByBlack)) {
                    whitePawn3.beatingInTransit = true;
                }
                if (whitePawn3.row == 5 && !gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 1].occupiedFieldByBlack && whitePawn3.column != columnBoardOdebrany) {
                    if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                    }
                    gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare = 0;
                    gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].occupiedFieldByBlack = false;
                    whitePawn3.row = rowBoardOdebrany;
                    whitePawn3.column = columnBoardOdebrany;
                } else {
                    whitePawn3.row = rowBoardOdebrany;
                    whitePawn3.column = columnBoardOdebrany;
                }
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 12) {
                if (whitePawn4.row + 2 == rowBoardOdebrany && whitePawn4.column == columnBoardOdebrany && (gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany].occupiedFieldByBlack || gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 2].occupiedFieldByBlack)) {
                    whitePawn4.beatingInTransit = true;
                }
                if (whitePawn4.row == 5 && !gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 1].occupiedFieldByBlack && whitePawn4.column != columnBoardOdebrany) {
                    if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                    }
                    gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare = 0;
                    gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].occupiedFieldByBlack = false;
                    whitePawn4.row = rowBoardOdebrany;
                    whitePawn4.column = columnBoardOdebrany;
                } else {
                    whitePawn4.row = rowBoardOdebrany;
                    whitePawn4.column = columnBoardOdebrany;
                }
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 13) {
                if (whitePawn5.row + 2 == rowBoardOdebrany && whitePawn5.column == columnBoardOdebrany && (gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany].occupiedFieldByBlack || gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 2].occupiedFieldByBlack)) {
                    whitePawn5.beatingInTransit = true;
                }
                if (whitePawn5.row == 5 && !gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 1].occupiedFieldByBlack && whitePawn5.column != columnBoardOdebrany) {
                    if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                    }
                    gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare = 0;
                    gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].occupiedFieldByBlack = false;
                    whitePawn5.row = rowBoardOdebrany;
                    whitePawn5.column = columnBoardOdebrany;
                } else {
                    whitePawn5.row = rowBoardOdebrany;
                    whitePawn5.column = columnBoardOdebrany;
                }
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 14) {
                if (whitePawn6.row + 2 == rowBoardOdebrany && whitePawn6.column == columnBoardOdebrany && (gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany].occupiedFieldByBlack || gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 2].occupiedFieldByBlack)) {
                    whitePawn6.beatingInTransit = true;
                }
                if (whitePawn6.row == 5 && !gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 1].occupiedFieldByBlack && whitePawn6.column != columnBoardOdebrany) {
                    if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;

                    }
                    gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare = 0;
                    gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].occupiedFieldByBlack = false;
                    whitePawn6.row = rowBoardOdebrany;
                    whitePawn6.column = columnBoardOdebrany;
                } else {
                    whitePawn6.row = rowBoardOdebrany;
                    whitePawn6.column = columnBoardOdebrany;
                }
            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 15) {
                if (whitePawn7.row + 2 == rowBoardOdebrany && whitePawn7.column == columnBoardOdebrany && (gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany].occupiedFieldByBlack || gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 2].occupiedFieldByBlack)) {
                    whitePawn7.beatingInTransit = true;
                }

                if (whitePawn7.row == 5 && !gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 1].occupiedFieldByBlack && whitePawn7.column != columnBoardOdebrany) {
                    if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 49 && blackPawn1.beatingInTransit) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;


                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 50 && blackPawn2.beatingInTransit) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 51 && blackPawn3.beatingInTransit) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;
                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 52 && blackPawn4.beatingInTransit) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;
                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 53 && blackPawn5.beatingInTransit) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;
                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 54 && blackPawn6.beatingInTransit) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;

                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 55 && blackPawn7.beatingInTransit) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;
                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 56 && blackPawn8.beatingInTransit) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;
                    }
                    gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare = 0;
                    gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].occupiedFieldByBlack = false;

                    whitePawn7.row = rowBoardOdebrany;
                    whitePawn7.column = columnBoardOdebrany;
                } else {
                    whitePawn7.row = rowBoardOdebrany;
                    whitePawn7.column = columnBoardOdebrany;
                }

            } else if (gameBoard[rowBoardToDestroyOdebrany - 1][columnBoardToDestroyOdebrany - 1].identityOfSquare == 16) {
                if (whitePawn8.row + 2 == rowBoardOdebrany && whitePawn8.column == columnBoardOdebrany && gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 2].occupiedFieldByBlack) {
                    whitePawn8.beatingInTransit = true;
                }

                if (whitePawn8.row == 5 && !gameBoard[rowBoardOdebrany - 1][columnBoardOdebrany - 1].occupiedFieldByBlack && whitePawn8.column != columnBoardOdebrany) {
                    if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 49) {
                        blackPawn1.row = 0;
                        blackPawn1.column = 0;
                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 50) {
                        blackPawn2.row = 0;
                        blackPawn2.column = 0;
                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 51) {
                        blackPawn3.row = 0;
                        blackPawn3.column = 0;
                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 52) {
                        blackPawn4.row = 0;
                        blackPawn4.column = 0;
                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 53) {
                        blackPawn5.row = 0;
                        blackPawn5.column = 0;
                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 54) {
                        blackPawn6.row = 0;
                        blackPawn6.column = 0;
                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 55) {
                        blackPawn7.row = 0;
                        blackPawn7.column = 0;
                    } else if (gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare == 56) {
                        blackPawn8.row = 0;
                        blackPawn8.column = 0;
                    }
                    gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].identityOfSquare = 0;
                    gameBoard[rowBoardOdebrany - 1 - 1][columnBoardOdebrany - 1].occupiedFieldByBlack = false;
                    whitePawn8.row = rowBoardOdebrany;
                    whitePawn8.column = columnBoardOdebrany;
                } else {
                    whitePawn8.row = rowBoardOdebrany;
                    whitePawn8.column = columnBoardOdebrany;
                }
            }

            //Possible Moves


            checkCollision();
            removeOptionsForAll();
            setAdditionalOptionsForWhite(rowBoardToDestroyOdebrany, columnBoardToDestroyOdebrany, rowBoardOdebrany, columnBoardOdebrany);
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

            blackMove = true;
            whiteMove = false;

            repaint();
        }

    });


}
