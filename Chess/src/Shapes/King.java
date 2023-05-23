package Shapes;

import Game.GameStart;
import Game.Square;

import javax.swing.*;
import java.io.Serializable;

public class King implements Serializable {

    public String name;

    public int row, column;

    private int shapeDiff;

    public boolean check;
    public boolean checkMate;
    public boolean draw;

    public boolean clicked;
    private boolean shieldIsPossible;

    public boolean castlingLong = false;
    public boolean castlingShort = false;
    public boolean blackKingCastlingAccess = true;
    public boolean blackRook1CastlingAccess = true;
    public boolean blackRook2CastlingAccess = true;

    public boolean whiteKingCastlingAccess = true;
    public boolean whiteRook1CastlingAccess = true;
    public boolean whiteRook2CastlingAccess = true;

    public King(int row, int column, String name, boolean clicked) {
        this.row = row;
        this.column = column;
        this.name = name;
        this.clicked = clicked;
    }

    public void setMoveOptionsForWhite(Square[][] gameBoard) {
        if (row >= 0 && row <= 7 && column >= 0 && column <= 7) {
            if(!gameBoard[row][column].possibleMoveBlack)gameBoard[row][column].possibleMoveWhiteKING = true;
        }
        if (row - 1 >= 0 && row - 1 <= 7 && column >= 0 && column <= 7) {
            if(!gameBoard[row-1][column].possibleMoveBlack)gameBoard[row - 1][column].possibleMoveWhiteKING = true;
        }
        if (row - 2 >= 0 && row - 2 <= 7 && column >= 0 && column <= 7) {
            if(!gameBoard[row-2][column].possibleMoveBlack)gameBoard[row - 2][column].possibleMoveWhiteKING = true;
        }
        if (row - 2 >= 0 && row - 2 <= 7 && column - 1 >= 0 && column - 1 <= 7) {
            if(!gameBoard[row-2][column-1].possibleMoveBlack)gameBoard[row - 2][column - 1].possibleMoveWhiteKING = true;
        }
        if (row - 2 >= 0 && row - 2 <= 7 && column - 2 >= 0 && column - 2 <= 7) {
            if(!gameBoard[row-2][column-2].possibleMoveBlack)gameBoard[row - 2][column - 2].possibleMoveWhiteKING = true;
        }
        if (row - 1 >= 0 && row - 1 <= 7 && column - 2 >= 0 && column - 2 <= 7) {
            if(!gameBoard[row-1][column-2].possibleMoveBlack)gameBoard[row - 1][column - 2].possibleMoveWhiteKING = true;
        }
        if (row >= 0 && row <= 7 && column - 2 >= 0 && column - 2 <= 7) {
            if(!gameBoard[row][column-2].possibleMoveBlack)gameBoard[row][column - 2].possibleMoveWhiteKING = true;
        }
        if (row >= 0 && row <= 7 && column - 1 >= 0 && column - 1 <= 7) {
            if(!gameBoard[row][column-1].possibleMoveBlack)gameBoard[row][column - 1].possibleMoveWhiteKING = true;
        }

    }

    public void setMoveOptionsForBlack(Square[][] gameBoard) {
        if (row >= 0 && row <= 7 && column >= 0 && column <= 7) {
            if(!gameBoard[row][column].possibleMoveWhite) gameBoard[row][column].possibleMoveBlackKING = true;
        }
        if (row - 1 >= 0 && row - 1 <= 7 && column >= 0 && column <= 7) {
            if(!gameBoard[row-1][column].possibleMoveWhite)gameBoard[row - 1][column].possibleMoveBlackKING = true;
        }
        if (row - 2 >= 0 && row - 2 <= 7 && column >= 0 && column <= 7) {
            if(!gameBoard[row-2][column].possibleMoveWhite)gameBoard[row - 2][column].possibleMoveBlackKING = true;
        }
        if (row - 2 >= 0 && row - 2 <= 7 && column - 1 >= 0 && column - 1 <= 7) {
            if(!gameBoard[row-2][column-1].possibleMoveWhite)gameBoard[row - 2][column - 1].possibleMoveBlackKING = true;
        }
        if (row - 2 >= 0 && row - 2 <= 7 && column - 2 >= 0 && column - 2 <= 7) {
            if(!gameBoard[row-2][column-2].possibleMoveWhite)gameBoard[row - 2][column - 2].possibleMoveBlackKING = true;
        }
        if (row - 1 >= 0 && row - 1 <= 7 && column - 2 >= 0 && column - 2 <= 7) {
            if(!gameBoard[row-1][column-2].possibleMoveWhite)gameBoard[row - 1][column - 2].possibleMoveBlackKING = true;
        }
        if (row >= 0 && row <= 7 && column - 2 >= 0 && column - 2 <= 7) {
            if(!gameBoard[row][column-2].possibleMoveWhite)gameBoard[row][column - 2].possibleMoveBlackKING = true;
        }
        if (row >= 0 && row <= 7 && column - 1 >= 0 && column - 1 <= 7) {
            if(!gameBoard[row][column-1].possibleMoveWhite)gameBoard[row][column - 1].possibleMoveBlackKING = true;
        }
    }



    public boolean moveWhite(int rowBoard, int columnBoard, Square[][] gameBoard, King blackKing) {

        boolean moveDone;
        boolean kingIsThere = false;

        if (((rowBoard == row - 1 && columnBoard == column - 1) ||
                (rowBoard == row && columnBoard == column - 1) ||
                (rowBoard == row - 1 && columnBoard == column) ||
                (rowBoard == row - 1 && columnBoard == column + 1) ||
                (rowBoard == row && columnBoard == column + 1) ||
                (rowBoard == row + 1 && columnBoard == column + 1) ||
                (rowBoard == row + 1 && columnBoard == column) ||
                (rowBoard == row + 1 && columnBoard == column - 1)) &&
                (!gameBoard[rowBoard - 1][columnBoard - 1].occupiedFieldByWHITE &&
                        gameBoard[rowBoard - 1][columnBoard - 1].occupiedFieldByBlack ||
                        !gameBoard[rowBoard - 1][columnBoard - 1].occupiedFieldByWHITE &&
                                !gameBoard[rowBoard - 1][columnBoard - 1].occupiedFieldByBlack) &&
                !gameBoard[rowBoard - 1][columnBoard - 1].possibleMoveBlack &&
                !gameBoard[rowBoard - 1][columnBoard - 1].possibleMoveBlackKING

        ) {


            row = rowBoard;
            column = columnBoard;
            clicked = false;
            whiteKingCastlingAccess = false;
            moveDone = true;


        } else if (rowBoard == row && columnBoard == column + 2
                && !gameBoard[row - 1][column].occupiedFieldByWHITE
                && !gameBoard[row - 1][column].occupiedFieldByBlack
                && !gameBoard[row - 1][column + 1].occupiedFieldByWHITE
                && !gameBoard[row - 1][column + 1].occupiedFieldByBlack
                && whiteKingCastlingAccess
                && whiteRook2CastlingAccess && !gameBoard[rowBoard - 1][columnBoard - 1].possibleMoveBlack &&
                !gameBoard[rowBoard - 1][columnBoard - 1].possibleMoveBlackKING &&
                !gameBoard[rowBoard - 1][columnBoard-2].possibleMoveBlack && !gameBoard[rowBoard - 1][columnBoard-2].possibleMoveBlackKING) {
            System.out.println("ezge1");
            row = rowBoard;
            column = columnBoard;
            //whiteRook2.row = 1;
            //whiteRook2.column = 6;
            castlingShort = true;
            castlingLong = false;
            gameBoard[0][7].identityOfSquare = 8;
            gameBoard[0][7].occupiedFieldByWHITE = false;
            gameBoard[0][7].occupiedFieldByBlack = false;
            gameBoard[0][5].occupiedFieldByWHITE = true;
            gameBoard[0][5].occupiedFieldByBlack = false;
            clicked = false;
            whiteKingCastlingAccess = false;
            whiteRook1CastlingAccess = false;
            whiteRook2CastlingAccess = false;
            moveDone = true;

        } else if (rowBoard == row && columnBoard == column - 2
                && !gameBoard[row - 1][column - 2].occupiedFieldByWHITE
                && !gameBoard[row - 1][column - 2].occupiedFieldByBlack
                && !gameBoard[row - 1][column - 3].occupiedFieldByWHITE
                && !gameBoard[row - 1][column - 3].occupiedFieldByBlack
                && !gameBoard[row - 1][column - 4].occupiedFieldByWHITE
                && !gameBoard[row - 1][column - 4].occupiedFieldByBlack
                && whiteKingCastlingAccess
                && whiteRook1CastlingAccess && !gameBoard[rowBoard - 1][columnBoard - 1].possibleMoveBlack &&
                !gameBoard[rowBoard - 1][columnBoard - 1].possibleMoveBlackKING &&
                !gameBoard[rowBoard - 1][columnBoard].possibleMoveBlack && !gameBoard[rowBoard - 1][columnBoard].possibleMoveBlackKING
        ) {
            System.out.println("ezge2");
            row = rowBoard;
            column = columnBoard;
            //whiteRook1.row = 1;
            //whiteRook1.column = 4;
            castlingLong = true;
            castlingShort = false;
            gameBoard[0][0].identityOfSquare = 1;
            gameBoard[0][0].occupiedFieldByWHITE = false;
            gameBoard[0][0].occupiedFieldByBlack = false;
            gameBoard[0][3].occupiedFieldByWHITE = true;
            gameBoard[0][3].occupiedFieldByBlack = false;
            clicked = false;
            whiteKingCastlingAccess = false;
            whiteRook1CastlingAccess = false;
            whiteRook2CastlingAccess = false;
            moveDone = true;

        } else {
            moveDone = false;
        }


        return moveDone;
    }

    public boolean moveBlack(int rowBoard, int columnBoard, Square[][] gameBoard, King whiteKing) {

        boolean moveDone;
        boolean kingIsThere = false;

        if (((rowBoard == row - 1 && columnBoard == column - 1) ||
                (rowBoard == row && columnBoard == column - 1) ||
                (rowBoard == row - 1 && columnBoard == column) ||
                (rowBoard == row - 1 && columnBoard == column + 1) ||
                (rowBoard == row && columnBoard == column + 1) ||
                (rowBoard == row + 1 && columnBoard == column + 1) ||
                (rowBoard == row + 1 && columnBoard == column) ||
                (rowBoard == row + 1 && columnBoard == column - 1)) && (gameBoard[rowBoard - 1][columnBoard - 1].occupiedFieldByWHITE &&
                !gameBoard[rowBoard - 1][columnBoard - 1].occupiedFieldByBlack ||
                !gameBoard[rowBoard - 1][columnBoard - 1].occupiedFieldByWHITE &&
                        !gameBoard[rowBoard - 1][columnBoard - 1].occupiedFieldByBlack) &&
                !gameBoard[rowBoard - 1][columnBoard - 1].possibleMoveWhite &&
                !gameBoard[rowBoard - 1][columnBoard - 1].possibleMoveWhiteKING

        ) {
            row = rowBoard;
            column = columnBoard;
            clicked = false;
            moveDone = true;
            blackKingCastlingAccess = false;

        } else if (rowBoard == row && columnBoard == column + 2
                && !gameBoard[row - 1][column].occupiedFieldByWHITE
                && !gameBoard[row - 1][column].occupiedFieldByBlack
                && !gameBoard[row - 1][column + 1].occupiedFieldByWHITE
                && !gameBoard[row - 1][column + 1].occupiedFieldByBlack
                && blackKingCastlingAccess
                && blackRook2CastlingAccess && !gameBoard[rowBoard - 1][columnBoard - 1].possibleMoveWhite &&
                !gameBoard[rowBoard - 1][columnBoard - 1].possibleMoveWhiteKING &&
                !gameBoard[rowBoard - 1][columnBoard-2].possibleMoveWhite && !gameBoard[rowBoard - 1][columnBoard-2].possibleMoveWhiteKING) {
            row = rowBoard;
            column = columnBoard;
            //blackRook2.row = 8;
            //blackRook2.column = 6;
            castlingShort = true;
            castlingLong = false;
            gameBoard[7][7].identityOfSquare = 64;
            gameBoard[7][7].occupiedFieldByWHITE = false;
            gameBoard[7][7].occupiedFieldByBlack = false;
            gameBoard[7][5].occupiedFieldByWHITE = false;
            gameBoard[7][5].occupiedFieldByBlack = true;
            clicked = false;
            moveDone = true;
            blackKingCastlingAccess = false;
            blackRook1CastlingAccess = false;
            blackRook2CastlingAccess = false;

        } else if (rowBoard == row && columnBoard == column - 2
                && !gameBoard[row - 1][column - 2].occupiedFieldByWHITE
                && !gameBoard[row - 1][column - 2].occupiedFieldByBlack
                && !gameBoard[row - 1][column - 3].occupiedFieldByWHITE
                && !gameBoard[row - 1][column - 3].occupiedFieldByBlack
                && !gameBoard[row - 1][column - 4].occupiedFieldByWHITE
                && !gameBoard[row - 1][column - 4].occupiedFieldByBlack
                && blackKingCastlingAccess
                && blackRook1CastlingAccess && !gameBoard[rowBoard - 1][columnBoard - 1].possibleMoveWhite &&
                !gameBoard[rowBoard - 1][columnBoard - 1].possibleMoveWhiteKING &&
                !gameBoard[rowBoard - 1][columnBoard].possibleMoveWhite && !gameBoard[rowBoard - 1][columnBoard].possibleMoveWhiteKING

        ) {
            row = rowBoard;
            column = columnBoard;
            //blackRook1.row = 8;
            //blackRook1.column = 4;
            castlingShort = false;
            castlingLong = true;
            gameBoard[7][0].identityOfSquare = 57;
            gameBoard[7][0].occupiedFieldByWHITE = false;
            gameBoard[7][0].occupiedFieldByBlack = false;
            gameBoard[7][2].occupiedFieldByWHITE = false;
            gameBoard[7][2].occupiedFieldByBlack = true;
            clicked = false;
            moveDone = true;
            blackKingCastlingAccess = false;
            blackRook1CastlingAccess = false;
            blackRook2CastlingAccess = false;

        } else {
            moveDone = false;
        }

        return moveDone;

    }

    public void checkDrawWHITE(Square[][] gameBoard){

        draw = true;

        for(int i=0; i<=7; i++){
            for(int j=0; j<=7; j++){
                if(gameBoard[i][j].possibleMoveWhite || gameBoard[i][j].possibleMoveWhiteKING){
                    draw = false;
                }
            }
        }
    }

    public void checkDrawBLACK(Square[][] gameBoard){

        draw = true;

        for(int i=0; i<=7; i++){
            for(int j=0; j<=7; j++){
                if(gameBoard[i][j].possibleMoveBlack || gameBoard[i][j].possibleMoveBlackKING){
                    draw = false;
                }
            }
        }
    }

    public void checkMateWHITE(Square[][] gameBoard, Knight blackKnight1, Knight blackKnight2,
                               Bishop blackBishop1, Bishop blackBishop2,
                               Rook blackRook1, Rook blackRook2,
                               Queen blackQueen,
                               Pawn blackPawn1,Pawn blackPawn2,Pawn blackPawn3,Pawn blackPawn4,
                               Pawn blackPawn5,Pawn blackPawn6,Pawn blackPawn7,Pawn blackPawn8) {

        if(gameBoard[row-1][column-1].possibleMoveBlack){
            check = true;

            int checkCounter = 0;
            int busyFields = 0;

            //right down
            if (row >= 0 && row <= 7 && column >= 0 && column <= 7) {
                if (gameBoard[row][column].possibleMoveBlack) {
                    checkCounter++;
                }
                if(gameBoard[row][column].occupiedFieldByWHITE){
                    busyFields++;
                }
            }
            //right
            if (row - 1 >= 0 && row - 1 <= 7 && column >= 0 && column <= 7) {
                if (gameBoard[row - 1][column].possibleMoveBlack) {
                    checkCounter++;
                }
                if(gameBoard[row-1][column].occupiedFieldByWHITE){
                    busyFields++;
                }
            }
            //right up
            if (row - 2 >= 0 && row - 2 <= 7 && column >= 0 && column <= 7) {
                if (gameBoard[row - 2][column].possibleMoveBlack) {
                    checkCounter++;
                }
                if(gameBoard[row-2][column].occupiedFieldByWHITE){
                    busyFields++;
                }
            }

            //up
            if (row - 2 >= 0 && row - 2 <= 7 && column - 1 >= 0 && column - 1 <= 7) {
                if (gameBoard[row - 2][column - 1].possibleMoveBlack) {
                    checkCounter++;
                }
                if(gameBoard[row-2][column-1].occupiedFieldByWHITE){
                    busyFields++;
                }
            }

            //left up
            if (row - 2 >= 0 && row - 2 <= 7 && column - 2 >= 0 && column - 2 <= 7) {
                if (gameBoard[row - 2][column - 2].possibleMoveBlack) {
                    checkCounter++;
                }
                if(gameBoard[row-2][column-2].occupiedFieldByWHITE){
                    busyFields++;
                }
            }

            //left
            if (row - 1 >= 0 && row - 1 <= 7 && column - 2 >= 0 && column - 2 <= 7) {
                if (gameBoard[row - 1][column - 2].possibleMoveBlack) {
                    checkCounter++;
                }
                if(gameBoard[row-1][column-2].occupiedFieldByWHITE){
                    busyFields++;
                }
            }

            //down
            if (row >= 0 && row <= 7 && column - 1 >= 0 && column - 1 <= 7) {
                if (gameBoard[row][column - 1].possibleMoveBlack) {
                    checkCounter++;
                }
                if(gameBoard[row][column-1].occupiedFieldByWHITE){
                    busyFields++;
                }
            }

            //left down
            if (row >= 0 && row <= 7 && column - 2 >= 0 && column - 2 <= 7) {
                if (gameBoard[row][column - 2].possibleMoveBlack) {
                    checkCounter++;
                }
                if(gameBoard[row][column-2].occupiedFieldByWHITE){
                    busyFields++;
                }
            }

            boolean tryCombination1 = ((checkCounter == 3 && busyFields >= 0) ||
                    (checkCounter == 2 && busyFields >= 1) ||
                    (checkCounter == 1 && busyFields >= 2) ||
                    (checkCounter == 0 && busyFields >= 3));

            boolean tryCombination2 = ((checkCounter == 5 && busyFields >= 0) ||
                    (checkCounter == 4 && busyFields >= 1) ||
                    (checkCounter == 3 && busyFields >= 2) ||
                    (checkCounter == 2 && busyFields >= 3) ||
                    (checkCounter == 1 && busyFields >= 4) ||
                    (checkCounter == 0 && busyFields >= 5));

            boolean tryCombination3 = ((checkCounter == 8 && busyFields >= 0) ||
                    (checkCounter == 7 && busyFields >= 1) ||
                    (checkCounter == 6 && busyFields >= 2) ||
                    (checkCounter == 5 && busyFields >= 3) ||
                    (checkCounter == 4 && busyFields >= 4) ||
                    (checkCounter == 3 && busyFields >= 5) ||
                    (checkCounter == 2 && busyFields >= 6) ||
                    (checkCounter == 1 && busyFields >= 7) ||
                    (checkCounter == 0 && busyFields >= 8));



            if (blackKnight1.kingIsCheckedByThisFigure) {

                if(!gameBoard[blackKnight1.row-1][blackKnight1.column-1].possibleMoveWhite && !gameBoard[blackKnight1.row-1][blackKnight1.column-1].possibleMoveWhiteKING){

                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }


            }else if(blackKnight2.kingIsCheckedByThisFigure){

                if(!gameBoard[blackKnight2.row-1][blackKnight2.column-1].possibleMoveWhite && !gameBoard[blackKnight2.row-1][blackKnight2.column-1].possibleMoveWhiteKING){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }

            }else if(blackBishop1.kingIsCheckedByThisFigure){

                shieldIsPossible = false;

                //sprawdzamy mozliwosc zasloniecia krola

                if(blackBishop1.row < row && blackBishop1.column > column){
                    // right up
                    shapeDiff = row - blackBishop1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1-i >= 0 && row-1-i <= 7 && column-1+i >= 0 && column-1+i <= 7){
                            if(gameBoard[row-1-i][column-1+i].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row-1-1 >= 0 && row-1-1 <= 7 && column >= 0 && column <= 7){

                    }
                    if(row-1-1 >= 0 && row-1-1 <= 7 && column >= 0 && column <= 7){
                        if(gameBoard[row-1-1][column] == gameBoard[blackBishop1.row-1][blackBishop1.column-1]) shieldIsPossible = false;
                    }

                }else if(blackBishop1.row > row && blackBishop1.column > column){
                    // right down
                    shapeDiff = -row + blackBishop1.row-1;
                    for(int i=1; i<=shapeDiff; i++){

                        if(row-1+i >= 0 && row-1+i <= 7 && column-1+i >= 0 && column-1+i <= 7){
                            if(gameBoard[row-1+i][column-1+i].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }

                    if(row >= 0 && row <= 7 && column >= 0 && column <= 7){
                        if(gameBoard[row][column] == gameBoard[blackBishop1.row-1][blackBishop1.column-1]) shieldIsPossible = false;
                    }





                }else if(blackBishop1.row > row && blackBishop1.column < column){
                    // left down
                    shapeDiff = -row + blackBishop1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1+i >= 0 && row-1+i <= 7 && column-1-i >= 0 && column-1-i <= 7){
                            if(gameBoard[row-1+i][column-1-i].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }


                    if(row >= 0 && row <= 7 && column-1-1 >= 0 && column-1-1 <= 7){
                        if(gameBoard[row][column-1-1] == gameBoard[blackBishop1.row-1][blackBishop1.column-1]) shieldIsPossible = false;
                    }

                }else if(blackBishop1.row < row && blackBishop1.column < column){
                    // left up
                    shapeDiff = row - blackBishop1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1-i >= 0 && row-1-i <= 7 && column-1-i >= 0 && column-1-i <= 7){
                            if(gameBoard[row-1-i][column-1-i].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }

                    if(row-1-1 >= 0 && row-1-1 <= 7 && column-1-1 >= 0 && column-1-1 <= 7){

                        if(gameBoard[row-1-1][column-1-1] == gameBoard[blackBishop1.row-1][blackBishop1.column-1]) shieldIsPossible = false;
                    }

                }

                if(!gameBoard[blackBishop1.row-1][blackBishop1.column-1].possibleMoveWhite && !gameBoard[blackBishop1.row-1][blackBishop1.column-1].possibleMoveWhiteKING && !shieldIsPossible){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }

            }else if(blackBishop2.kingIsCheckedByThisFigure){

                shieldIsPossible = false;

                //sprawdzamy mozliwosc zasloniecia krola

                if(blackBishop2.row < row && blackBishop2.column > column){
                    // right up
                    shapeDiff = row - blackBishop2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1-i >= 0 && row-1-i <= 7 && column-1+i >= 0 && column-1+i <= 7){
                            if(gameBoard[row-1-i][column-1+i].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row-1-1 >= 0 && row-1-1 <= 7 && column >= 0 && column <= 7){
                        if(gameBoard[row-1-1][column] == gameBoard[blackBishop2.row-1][blackBishop2.column-1]) shieldIsPossible = false;
                    }


                }else if(blackBishop2.row > row && blackBishop2.column > column){
                    // right down
                    shapeDiff = -row + blackBishop2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1+i >= 0 && row-1+i <= 7 && column-1+i >= 0 && column-1+i <= 7){

                            if(gameBoard[row-1+i][column-1+i].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }

                    if(row >= 0 && row <= 7 && column >= 0 && column <= 7){
                        if(gameBoard[row][column] == gameBoard[blackBishop2.row-1][blackBishop2.column-1]) shieldIsPossible = false;
                    }


                }else if(blackBishop2.row > row && blackBishop2.column < column){
                    // left down
                    shapeDiff = -row + blackBishop2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1+i >= 0 && row-1+i <= 7 && column-1-i >= 0 && column-1-i <= 7){
                            if(gameBoard[row-1+i][column-1-i].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }

                    if(row >= 0 && row <= 7 && column-1-1 >= 0 && column-1-1 <= 7){
                        if(gameBoard[row][column-1-1] == gameBoard[blackBishop2.row-1][blackBishop2.column-1]) shieldIsPossible = false;
                    }

                }else if(blackBishop2.row < row && blackBishop2.column < column){
                    // left up
                    shapeDiff = row - blackBishop2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1-i >= 0 && row-1-i <= 7 && column-1-i >= 0 && column-1-i <= 7){
                            if(gameBoard[row-1-i][column-1-i].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }



                    if(row-1-1 >= 0 && row-1-1 <= 7 && column-1-1 >= 0 && column-1-1 <= 7){
                        if(gameBoard[row-1-1][column-1-1] == gameBoard[blackBishop2.row-1][blackBishop2.column-1]) shieldIsPossible = false;
                    }

                }

                if(!gameBoard[blackBishop2.row-1][blackBishop2.column-1].possibleMoveWhite && !gameBoard[blackBishop2.row-1][blackBishop2.column-1].possibleMoveWhiteKING && !shieldIsPossible){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(blackRook1.kingIsCheckedByThisFigure){

                shieldIsPossible = false;

                //sprawdzamy mozliwosc zasloniecia krola

                if(blackRook1.column > column){
                    //right
                    shapeDiff = -column + blackRook1.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1 >= 0 && row-1 <= 7 && column-1+i >= 0 && column-1+i <= 7){
                            if(gameBoard[row-1][column-1+i].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row-1 >= 0 && row-1 <= 7 && column >= 0 && column <= 7){

                        if(gameBoard[row-1][column] == gameBoard[blackRook1.row-1][blackRook1.column-1]) shieldIsPossible = false;
                    }
                }else if(blackRook1.row < row){
                    //down
                    shapeDiff = row - blackRook1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1-i >= 0 && row-1-i <= 7 && column-1 >= 0 && column-1 <= 7){
                            if(gameBoard[row-1-i][column-1].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row-1-1 >= 0 && row-1-1 <= 7 && column-1 >= 0 && column-1 <= 7){
                        if(gameBoard[row-1-1][column-1] == gameBoard[blackRook1.row-1][blackRook1.column-1]) shieldIsPossible = false;
                    }

                }else if(blackRook1.column < column){
                    // left
                    shapeDiff = column - blackRook1.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1 >= 0 && row-1 <= 7 && column-1-i >= 0 && column-1-i <= 7){
                            if(gameBoard[row-1][column-1-i].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row-1 >= 0 && row-1 <= 7 && column-1-1 >= 0 && column-1-1 <= 7){
                        if(gameBoard[row-1][column-1-1] == gameBoard[blackRook1.row-1][blackRook1.column-1]) shieldIsPossible = false;
                    }
                }else if(blackRook1.row > row){
                    //up
                    shapeDiff = -row + blackRook1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1+i >= 0 && row-1+i <= 7 && column-1 >= 0 && column-1 <= 7){
                            if(gameBoard[row-1+i][column-1].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row >= 0 && row <= 7 && column-1 >= 0 && column-1 <= 7){
                        if(gameBoard[row][column-1] == gameBoard[blackRook1.row-1][blackRook1.column-1]) shieldIsPossible = false;
                    }
                }

                if(!gameBoard[blackRook1.row-1][blackRook1.column-1].possibleMoveWhite && !gameBoard[blackRook1.row-1][blackRook1.column-1].possibleMoveWhiteKING && !shieldIsPossible){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(blackRook2.kingIsCheckedByThisFigure){


                shieldIsPossible = false;

                //sprawdzamy mozliwosc zasloniecia krola

                if(blackRook2.column > column){
                    //right
                    shapeDiff = -column + blackRook2.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1 >= 0 && row-1 <= 7 && column-1+i >= 0 && column-1+i <= 7){
                            if(gameBoard[row-1][column-1+i].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }

                    if(row-1 >= 0 && row-1 <= 7 && column >= 0 && column <= 7){

                        if(gameBoard[row-1][column] == gameBoard[blackRook2.row-1][blackRook2.column-1]) shieldIsPossible = false;
                    }
                }else if(blackRook2.row < row){
                    //down
                    shapeDiff = row - blackRook2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1-i >= 0 && row-1-i <= 7 && column-1 >= 0 && column-1 <= 7){
                            if(gameBoard[row-1-i][column-1].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row-1-1 >= 0 && row-1-1 <= 7 && column-1 >= 0 && column-1 <= 7){
                        if(gameBoard[row-1-1][column-1] == gameBoard[blackRook2.row-1][blackRook2.column-1]) shieldIsPossible = false;
                    }

                }else if(blackRook2.column < column){
                    // left
                    shapeDiff = column - blackRook2.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1 >= 0 && row-1 <= 7 && column-1-i >= 0 && column-1-i <= 7){
                            if(gameBoard[row-1][column-1-i].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row-1 >= 0 && row-1 <= 7 && column-1-1 >= 0 && column-1-1 <= 7){
                        if(gameBoard[row-1][column-1-1] == gameBoard[blackRook2.row-1][blackRook2.column-1]) shieldIsPossible = false;
                    }
                }else if(blackRook2.row > row){
                    //up
                    shapeDiff = -row + blackRook2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1+i >= 0 && row-1+i <= 7 && column-1 >= 0 && column-1 <= 7){
                            if(gameBoard[row-1+i][column-1].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row >= 0 && row <= 7 && column-1 >= 0 && column-1 <= 7){
                        if(gameBoard[row][column-1] == gameBoard[blackRook2.row-1][blackRook2.column-1]) shieldIsPossible = false;
                    }
                }

                if(!gameBoard[blackRook2.row-1][blackRook2.column-1].possibleMoveWhite && !gameBoard[blackRook2.row-1][blackRook2.column-1].possibleMoveWhiteKING && !shieldIsPossible){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(blackQueen.kingIsCheckedByThisFigure){

                shieldIsPossible = false;

                //sprawdzamy mozliwosc zasloniecia krola

                if(blackQueen.row < row && blackQueen.column > column){
                    // right up
                    shapeDiff = row - blackQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1-i >= 0 && row-1-i <= 7 && column-1+i >= 0 && column-1+i <= 7){
                            if(gameBoard[row-1-i][column-1+i].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row-1-1 >= 0 && row-1-1 <= 7 && column >= 0 && column <= 7){
                        if(gameBoard[row-1-1][column] == gameBoard[blackQueen.row-1][blackQueen.column-1]) shieldIsPossible = false;

                    }

                }else if(blackQueen.row > row && blackQueen.column > column){
                    // right down
                    shapeDiff = -row + blackQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1+i >= 0 && row-1+i <= 7 && column-1+i >= 0 && column-1+i <= 7){
                            if(gameBoard[row-1+i][column-1+i].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }

                    if(row >= 0 && row <= 7 && column >= 0 && column <= 7){
                        if(gameBoard[row][column] == gameBoard[blackQueen.row-1][blackQueen.column-1]) shieldIsPossible = false;

                    }

                }else if(blackQueen.row > row && blackQueen.column < column){
                    // left down
                    shapeDiff = -row + blackQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1+i >= 0 && row-1+i <= 7 && column-1-i >= 0 && column-1-i <= 7){
                            if(gameBoard[row-1+i][column-1-i].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }

                    if(row >= 0 && row <= 7 && column-1-1 >= 0 && column-1-1 <= 7){
                        if(gameBoard[row][column-1-1] == gameBoard[blackQueen.row-1][blackQueen.column-1]) shieldIsPossible = false;

                    }

                }else if(blackQueen.row < row && blackQueen.column < column){
                    // left up
                    shapeDiff = row - blackQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1-i >= 0 && row-1-i <= 7 && column-1-i >= 0 && column-1-i <= 7){
                            if(gameBoard[row-1-i][column-1-i].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }

                    if(row-1-1 >= 0 && row-1-1 <= 7 && column-1-1 >= 0 && column-1-1 <= 7){
                        if(gameBoard[row-1-1][column-1-1] == gameBoard[blackQueen.row-1][blackQueen.column-1]) shieldIsPossible = false;

                    }

                }


                //sprawdzamy mozliwosc zasloniecia krola

                if(blackQueen.column > column && blackQueen.row == row){
                    //right
                    shapeDiff = -column + blackQueen.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1 >= 0 && row-1 <= 7 && column-1+i >= 0 && column-1+i <= 7){
                            if(gameBoard[row-1][column-1+i].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row-1 >= 0 && row-1 <= 7 && column >= 0 && column <= 7){

                        if(gameBoard[row-1][column] == gameBoard[blackQueen.row-1][blackQueen.column-1]) shieldIsPossible = false;
                    }
                }else if(blackQueen.row < row && blackQueen.column == column){
                    //down
                    shapeDiff = row - blackQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1-i >= 0 && row-1-i <= 7 && column-1 >= 0 && column-1 <= 7){
                            if(gameBoard[row-1-i][column-1].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row-1-1 >= 0 && row-1-1 <= 7 && column-1 >= 0 && column-1 <= 7){
                        if(gameBoard[row-1-1][column-1] == gameBoard[blackQueen.row-1][blackQueen.column-1]) shieldIsPossible = false;
                    }

                }else if(blackQueen.column < column && blackQueen.row == row){
                    // left
                    shapeDiff = column - blackQueen.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1 >= 0 && row-1 <= 7 && column-1-i >= 0 && column-1-i <= 7){
                            if(gameBoard[row-1][column-1-i].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row-1 >= 0 && row-1 <= 7 && column-1-1 >= 0 && column-1-1 <= 7){
                        if(gameBoard[row-1][column-1-1] == gameBoard[blackQueen.row-1][blackQueen.column-1]) shieldIsPossible = false;
                    }
                }else if(blackQueen.row > row && blackQueen.column == column){
                    //up
                    shapeDiff = -row + blackQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1+i >= 0 && row-1+i <= 7 && column-1 >= 0 && column-1 <= 7){
                            if(gameBoard[row-1+i][column-1].possibleMoveWhite){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row >= 0 && row <= 7 && column-1 >= 0 && column-1 <= 7){
                        if(gameBoard[row][column-1] == gameBoard[blackQueen.row-1][blackQueen.column-1]) shieldIsPossible = false;
                    }
                }



                if(!gameBoard[blackQueen.row-1][blackQueen.column-1].possibleMoveWhite && !gameBoard[blackQueen.row-1][blackQueen.column-1].possibleMoveWhiteKING && !shieldIsPossible){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(blackPawn1.kingIsCheckedByThisFigure){
                if(!gameBoard[blackPawn1.row-1][blackPawn1.column-1].possibleMoveWhite && !gameBoard[blackPawn1.row-1][blackPawn1.column-1].possibleMoveWhiteKING){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(blackPawn2.kingIsCheckedByThisFigure){
                if(!gameBoard[blackPawn2.row-1][blackPawn2.column-1].possibleMoveWhite && !gameBoard[blackPawn2.row-1][blackPawn2.column-1].possibleMoveWhiteKING){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(blackPawn3.kingIsCheckedByThisFigure){
                if(!gameBoard[blackPawn3.row-1][blackPawn3.column-1].possibleMoveWhite && !gameBoard[blackPawn3.row-1][blackPawn3.column-1].possibleMoveWhiteKING){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(blackPawn4.kingIsCheckedByThisFigure){
                if(!gameBoard[blackPawn4.row-1][blackPawn4.column-1].possibleMoveWhite && !gameBoard[blackPawn4.row-1][blackPawn4.column-1].possibleMoveWhiteKING){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(blackPawn5.kingIsCheckedByThisFigure){
                if(!gameBoard[blackPawn5.row-1][blackPawn5.column-1].possibleMoveWhite && !gameBoard[blackPawn5.row-1][blackPawn5.column-1].possibleMoveWhiteKING){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(blackPawn6.kingIsCheckedByThisFigure){
                if(!gameBoard[blackPawn6.row-1][blackPawn6.column-1].possibleMoveWhite && !gameBoard[blackPawn6.row-1][blackPawn6.column-1].possibleMoveWhiteKING){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(blackPawn7.kingIsCheckedByThisFigure){
                if(!gameBoard[blackPawn7.row-1][blackPawn7.column-1].possibleMoveWhite && !gameBoard[blackPawn7.row-1][blackPawn7.column-1].possibleMoveWhiteKING){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(blackPawn8.kingIsCheckedByThisFigure){
                if(!gameBoard[blackPawn8.row-1][blackPawn8.column-1].possibleMoveWhite && !gameBoard[blackPawn8.row-1][blackPawn8.column-1].possibleMoveWhiteKING){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else {
                check = false;
            }


        }

    }


    public void checkMateBLACK(Square[][] gameBoard, Knight whiteKnight1, Knight whiteKnight2,
                               Bishop whiteBishop1, Bishop whiteBishop2,
                               Rook whiteRook1, Rook whiteRook2,
                               Queen whiteQueen,
                               Pawn whitePawn1,Pawn whitePawn2,Pawn whitePawn3,Pawn whitePawn4,
                               Pawn whitePawn5,Pawn whitePawn6,Pawn whitePawn7,Pawn whitePawn8) {

        if(gameBoard[row-1][column-1].possibleMoveWhite){
            check = true;

            int checkCounter = 0;
            int busyFields = 0;

            //right down
            if (row >= 0 && row <= 7 && column >= 0 && column <= 7) {
                if (gameBoard[row][column].possibleMoveWhite) {
                    checkCounter++;
                }
                if(gameBoard[row][column].occupiedFieldByBlack){
                    busyFields++;
                }
            }
            //right
            if (row - 1 >= 0 && row - 1 <= 7 && column >= 0 && column <= 7) {
                if (gameBoard[row - 1][column].possibleMoveWhite) {
                    checkCounter++;
                }
                if(gameBoard[row-1][column].occupiedFieldByBlack){
                    busyFields++;
                }
            }
            //right up
            if (row - 2 >= 0 && row - 2 <= 7 && column >= 0 && column <= 7) {
                if (gameBoard[row - 2][column].possibleMoveWhite) {
                    checkCounter++;
                }
                if(gameBoard[row-2][column].occupiedFieldByBlack){
                    busyFields++;
                }
            }

            //up
            if (row - 2 >= 0 && row - 2 <= 7 && column - 1 >= 0 && column - 1 <= 7) {
                if (gameBoard[row - 2][column - 1].possibleMoveWhite) {
                    checkCounter++;
                }
                if(gameBoard[row-2][column-1].occupiedFieldByBlack){
                    busyFields++;
                }
            }

            //left up
            if (row - 2 >= 0 && row - 2 <= 7 && column - 2 >= 0 && column - 2 <= 7) {
                if (gameBoard[row - 2][column - 2].possibleMoveWhite) {
                    checkCounter++;
                }
                if(gameBoard[row-2][column-2].occupiedFieldByBlack){
                    busyFields++;
                }
            }

            //left
            if (row - 1 >= 0 && row - 1 <= 7 && column - 2 >= 0 && column - 2 <= 7) {
                if (gameBoard[row - 1][column - 2].possibleMoveWhite) {
                    checkCounter++;
                }
                if(gameBoard[row-1][column-2].occupiedFieldByBlack){
                    busyFields++;
                }
            }

            //down
            if (row >= 0 && row <= 7 && column - 1 >= 0 && column - 1 <= 7) {
                if (gameBoard[row][column - 1].possibleMoveWhite) {
                    checkCounter++;
                }
                if(gameBoard[row][column-1].occupiedFieldByBlack){
                    busyFields++;
                }
            }

            //left down
            if (row >= 0 && row <= 7 && column - 2 >= 0 && column - 2 <= 7) {
                if (gameBoard[row][column - 2].possibleMoveWhite) {
                    checkCounter++;
                }
                if(gameBoard[row][column-2].occupiedFieldByBlack){
                    busyFields++;
                }
            }

            boolean tryCombination1 = ((checkCounter == 3 && busyFields >= 0) ||
                    (checkCounter == 2 && busyFields >= 1) ||
                    (checkCounter == 1 && busyFields >= 2) ||
                    (checkCounter == 0 && busyFields >= 3));

            boolean tryCombination2 = ((checkCounter == 5 && busyFields >= 0) ||
                    (checkCounter == 4 && busyFields >= 1) ||
                    (checkCounter == 3 && busyFields >= 2) ||
                    (checkCounter == 2 && busyFields >= 3) ||
                    (checkCounter == 1 && busyFields >= 4) ||
                    (checkCounter == 0 && busyFields >= 5));

            boolean tryCombination3 = ((checkCounter == 8 && busyFields >= 0) ||
                    (checkCounter == 7 && busyFields >= 1) ||
                    (checkCounter == 6 && busyFields >= 2) ||
                    (checkCounter == 5 && busyFields >= 3) ||
                    (checkCounter == 4 && busyFields >= 4) ||
                    (checkCounter == 3 && busyFields >= 5) ||
                    (checkCounter == 2 && busyFields >= 6) ||
                    (checkCounter == 1 && busyFields >= 7) ||
                    (checkCounter == 0 && busyFields >= 8));



            if (whiteKnight1.kingIsCheckedByThisFigure) {

                if(!gameBoard[whiteKnight1.row-1][whiteKnight1.column-1].possibleMoveBlack && !gameBoard[whiteKnight1.row-1][whiteKnight1.column-1].possibleMoveBlackKING){

                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }


            }else if(whiteKnight2.kingIsCheckedByThisFigure){

                if(!gameBoard[whiteKnight2.row-1][whiteKnight2.column-1].possibleMoveBlack && !gameBoard[whiteKnight2.row-1][whiteKnight2.column-1].possibleMoveBlackKING){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }

            }else if(whiteBishop1.kingIsCheckedByThisFigure){

                shieldIsPossible = false;

                //sprawdzamy mozliwosc zasloniecia krola

                if(whiteBishop1.row < row && whiteBishop1.column > column){
                    // right up
                    shapeDiff = row - whiteBishop1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1-i >= 0 && row-1-i <= 7 && column-1+i >= 0 && column-1+i <= 7){
                            if(gameBoard[row-1-i][column-1+i].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }
                    }
                    if(row-1-1 >= 0 && row-1-1 <= 7 && column >= 0 && column <= 7){
                        if(gameBoard[row-1-1][column] == gameBoard[whiteBishop1.row-1][whiteBishop1.column-1]) shieldIsPossible = false;

                    }


                }else if(whiteBishop1.row > row && whiteBishop1.column > column){
                    // right down
                    shapeDiff = -row + whiteBishop1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1+i >= 0 && row-1+i <= 7 && column-1+i >= 0 && column-1+i <= 7){
                            if(gameBoard[row-1+i][column-1+i].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }
                    }

                    if(row >= 0 && row <= 7 && column >= 0 && column <= 7){
                        if(gameBoard[row][column] == gameBoard[whiteBishop1.row-1][whiteBishop1.column-1]) shieldIsPossible = false;

                    }

                }else if(whiteBishop1.row > row && whiteBishop1.column < column){
                    // left down
                    shapeDiff = -row + whiteBishop1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1+i >= 0 && row-1+i <= 7 && column-1-i >= 0 && column-1-i <= 7){
                            if(gameBoard[row-1+i][column-1-i].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }
                    }

                    if(row >= 0 && row <= 7 && column-1-1 >= 0 && column-1-1 <= 7){
                        if(gameBoard[row][column-1-1] == gameBoard[whiteBishop1.row-1][whiteBishop1.column-1]) shieldIsPossible = false;

                    }

                }else if(whiteBishop1.row < row && whiteBishop1.column < column){
                    // left up
                    shapeDiff = row - whiteBishop1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1-i >= 0 && row-1-i <= 7 && column-1-i >= 0 && column-1-i <= 7){
                            if(gameBoard[row-1-i][column-1-i].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }
                    }

                    if(row-1-1 >= 0 && row-1-1 <= 7 && column-1-1 >= 0 && column-1-1 <= 7){
                        if(gameBoard[row-1-1][column-1-1] == gameBoard[whiteBishop1.row-1][whiteBishop1.column-1]) shieldIsPossible = false;

                    }
                }

                if(!gameBoard[whiteBishop1.row-1][whiteBishop1.column-1].possibleMoveBlack && !gameBoard[whiteBishop1.row-1][whiteBishop1.column-1].possibleMoveBlackKING && !shieldIsPossible){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }

            }else if(whiteBishop2.kingIsCheckedByThisFigure){

                shieldIsPossible = false;

                //sprawdzamy mozliwosc zasloniecia krola

                if(whiteBishop2.row < row && whiteBishop2.column > column){
                    // right up
                    shapeDiff = row - whiteBishop2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1-i >= 0 && row-1-i <= 7 && column-1+i >= 0 && column-1+i <= 7){
                            if(gameBoard[row-1-i][column-1+i].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row-1-1 >= 0 && row-1-1 <= 7 && column >= 0 && column <= 7){
                        if(gameBoard[row-1-1][column] == gameBoard[whiteBishop2.row-1][whiteBishop2.column-1]) shieldIsPossible = false;

                    }
                }else if(whiteBishop2.row > row && whiteBishop2.column > column){
                    // right down
                    shapeDiff = -row + whiteBishop2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1+i >= 0 && row-1+i <= 7 && column-1+i >= 0 && column-1+i <= 7){
                            if(gameBoard[row-1+i][column-1+i].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row >= 0 && row <= 7 && column >= 0 && column <= 7){
                        if(gameBoard[row][column] == gameBoard[whiteBishop2.row-1][whiteBishop2.column-1]) shieldIsPossible = false;

                    }
                }else if(whiteBishop2.row > row && whiteBishop2.column < column){
                    // left down
                    shapeDiff = -row + whiteBishop2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1+i >= 0 && row-1+i <= 7 && column-1-i >= 0 && column-1-i <= 7){
                            if(gameBoard[row-1+i][column-1-i].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row >= 0 && row <= 7 && column-1-1 >= 0 && column-1-1 <= 7){
                        if(gameBoard[row][column-1-1] == gameBoard[whiteBishop2.row-1][whiteBishop2.column-1]) shieldIsPossible = false;

                    }
                }else if(whiteBishop2.row < row && whiteBishop2.column < column){
                    // left up
                    shapeDiff = row - whiteBishop2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1-i >= 0 && row-1-i <= 7 && column-1-i >= 0 && column-1-i <= 7){
                            if(gameBoard[row-1-i][column-1-i].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row-1-1 >= 0 && row-1-1 <= 7 && column-1-1 >= 0 && column-1-1 <= 7){
                        if(gameBoard[row-1-1][column-1-1] == gameBoard[whiteBishop2.row-1][whiteBishop2.column-1]) shieldIsPossible = false;

                    }
                }




                //sprawdzamy mozliwosc zbicia figury ktora szachuje
                if(!gameBoard[whiteBishop2.row-1][whiteBishop2.column-1].possibleMoveBlack && !gameBoard[whiteBishop2.row-1][whiteBishop2.column-1].possibleMoveBlackKING && !shieldIsPossible){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(whiteRook1.kingIsCheckedByThisFigure){

                shieldIsPossible = false;

                //sprawdzamy mozliwosc zasloniecia krola

                if(whiteRook1.column > column){
                    //right
                    shapeDiff = -column + whiteRook1.column-1;
                    for(int i=1; i<=shapeDiff; i++){

                        if(row-1 >= 0 && row-1 <= 7 && column-1+i >= 0 && column-1+i <= 7){
                            if(gameBoard[row-1][column-1+i].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }
                    }
                    if(row-1 >= 0 && row-1 <= 7 && column >= 0 && column <= 7){

                        if(gameBoard[row-1][column] == gameBoard[whiteRook1.row-1][whiteRook1.column-1]) shieldIsPossible = false;
                    }
                }else if(whiteRook1.row < row){
                    //down
                    shapeDiff = row - whiteRook1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1-i >= 0 && row-1-i <= 7 && column-1 >= 0 && column-1 <= 7){
                            if(gameBoard[row-1-i][column-1].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }
                    }


                    if(row-1-1 >= 0 && row-1-1 <= 7 && column-1 >= 0 && column-1 <= 7){
                        if(gameBoard[row-1-1][column-1] == gameBoard[whiteRook1.row-1][whiteRook1.column-1]) shieldIsPossible = false;
                    }
                }else if(whiteRook1.column < column){
                    // left
                    shapeDiff = column - whiteRook1.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1 >= 0 && row-1 <= 7 && column-1-i >= 0 && column-1-i <= 7){
                            if(gameBoard[row-1][column-1-i].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }
                    }
                    if(row-1 >= 0 && row-1 <= 7 && column-1-1 >= 0 && column-1-1 <= 7){
                        if(gameBoard[row-1][column-1-1] == gameBoard[whiteRook1.row-1][whiteRook1.column-1]) shieldIsPossible = false;
                    }
                }else if(whiteRook1.row > row){
                    //up
                    shapeDiff = -row + whiteRook1.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1+i >= 0 && row-1+i <= 7 && column-1 >= 0 && column-1 <= 7){
                            if(gameBoard[row-1+i][column-1].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }
                    }
                    if(row >= 0 && row <= 7 && column-1 >= 0 && column-1 <= 7){
                        if(gameBoard[row][column-1] == gameBoard[whiteRook1.row-1][whiteRook1.column-1]) shieldIsPossible = false;
                    }

                }


                if(!gameBoard[whiteRook1.row-1][whiteRook1.column-1].possibleMoveBlack && !gameBoard[whiteRook1.row-1][whiteRook1.column-1].possibleMoveBlackKING && !shieldIsPossible){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(whiteRook2.kingIsCheckedByThisFigure){

                shieldIsPossible = false;

                //sprawdzamy mozliwosc zasloniecia krola

                if(whiteRook2.column > column){
                    //right
                    shapeDiff = -column + whiteRook2.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1 >= 0 && row-1 <= 7 && column-1+i >= 0 && column-1+i <= 7){
                            if(gameBoard[row-1][column-1+i].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }

                    }


                    if(row-1 >= 0 && row-1 <= 7 && column >= 0 && column <= 7){

                        if(gameBoard[row-1][column] == gameBoard[whiteRook2.row-1][whiteRook2.column-1]) shieldIsPossible = false;
                    }
                }else if(whiteRook2.row < row){
                    //down
                    shapeDiff = row - whiteRook2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1-i >= 0 && row-1-i <= 7 && column-1 >= 0 && column-1 <= 7){
                            if(gameBoard[row-1-i][column-1].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }

                    }


                    if(row-1-1 >= 0 && row-1-1 <= 7 && column-1 >= 0 && column-1 <= 7){
                        if(gameBoard[row-1-1][column-1] == gameBoard[whiteRook2.row-1][whiteRook2.column-1]) shieldIsPossible = false;
                    }
                }else if(whiteRook2.column < column){
                    // left
                    shapeDiff = column - whiteRook2.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1 >= 0 && row-1 <= 7 && column-1-i >= 0 && column-1-i <= 7){
                            if(gameBoard[row-1][column-1-i].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }

                    }


                    if(row-1 >= 0 && row-1 <= 7 && column-1-1 >= 0 && column-1-1 <= 7){
                        if(gameBoard[row-1][column-1-1] == gameBoard[whiteRook2.row-1][whiteRook2.column-1]) shieldIsPossible = false;
                    }
                }else if(whiteRook2.row > row){
                    //up
                    shapeDiff = -row + whiteRook2.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1+i >= 0 && row-1+i <= 7 && column-1 >= 0 && column-1 <= 7){
                            if(gameBoard[row-1+i][column-1].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }

                    }

                    if(row >= 0 && row <= 7 && column-1 >= 0 && column-1 <= 7){
                        if(gameBoard[row][column-1] == gameBoard[whiteRook2.row-1][whiteRook2.column-1]) shieldIsPossible = false;
                    }

                }

                if(!gameBoard[whiteRook2.row-1][whiteRook2.column-1].possibleMoveBlack && !gameBoard[whiteRook2.row-1][whiteRook2.column-1].possibleMoveBlackKING && !shieldIsPossible){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(whiteQueen.kingIsCheckedByThisFigure){

                shieldIsPossible = false;

                //sprawdzamy mozliwosc zasloniecia krola

                if(whiteQueen.row < row && whiteQueen.column > column){
                    // right up
                    shapeDiff = row - whiteQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1-i >= 0 && row-1-i <= 7 && column-1+i >= 0 && column-1+i <= 7){
                            if(gameBoard[row-1-i][column-1+i].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row-1-1 >= 0 && row-1-1 <= 7 && column >= 0 && column <= 7){
                        if(gameBoard[row-1-1][column] == gameBoard[whiteQueen.row-1][whiteQueen.column-1]) shieldIsPossible = false;

                    }
                }else if(whiteQueen.row > row && whiteQueen.column > column){
                    // right down
                    shapeDiff = -row + whiteQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1+i >= 0 && row-1+i <= 7 && column-1+i >= 0 && column-1+i <= 7){
                            if(gameBoard[row-1+i][column-1+i].possibleMoveBlack){
                                shieldIsPossible = true;

                            }
                        }

                    }

                    if(row >= 0 && row <= 7 && column >= 0 && column <= 7){
                        if(gameBoard[row][column] == gameBoard[whiteQueen.row-1][whiteQueen.column-1]) shieldIsPossible = false;

                    }

                }else if(whiteQueen.row > row && whiteQueen.column < column){
                    // left down
                    shapeDiff = -row + whiteQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1+i >= 0 && row-1+i <= 7 && column-1-i >= 0 && column-1-i <= 7){
                            if(gameBoard[row-1+i][column-1-i].possibleMoveBlack){
                                shieldIsPossible = true;

                            }
                        }

                    }

                    if(row >= 0 && row <= 7 && column-1-1 >= 0 && column-1-1 <= 7){
                        if(gameBoard[row][column-1-1] == gameBoard[whiteQueen.row-1][whiteQueen.column-1]) shieldIsPossible = false;

                    }

                }else if(whiteQueen.row < row && whiteQueen.column < column){
                    // left up
                    shapeDiff = row - whiteQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1-i >= 0 && row-1-i <= 7 && column-1-i >= 0 && column-1-i <= 7){
                            if(gameBoard[row-1-i][column-1-i].possibleMoveBlack){
                                shieldIsPossible = true;

                            }
                        }

                    }


                    if(row-1-1 >= 0 && row-1-1 <= 7 && column-1-1 >= 0 && column-1-1 <= 7){
                        if(gameBoard[row-1-1][column-1-1] == gameBoard[whiteQueen.row-1][whiteQueen.column-1]) shieldIsPossible = false;

                    }
                }

                System.out.println(shieldIsPossible);

                //sprawdzamy mozliwosc zasloniecia krola

                if(whiteQueen.column > column && whiteQueen.row == row){
                    //right
                    shapeDiff = -column + whiteQueen.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1 >= 0 && row-1 <= 7 && column-1+i >= 0 && column-1+i <= 7){
                            if(gameBoard[row-1][column-1+i].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }

                    }

                    if(row-1 >= 0 && row-1 <= 7 && column >= 0 && column <= 7){

                        if(gameBoard[row-1][column] == gameBoard[whiteQueen.row-1][whiteQueen.column-1]) shieldIsPossible = false;
                    }
                }else if(whiteQueen.row < row && whiteQueen.column == column){
                    //down
                    shapeDiff = row - whiteQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1-i >= 0 && row-1-i <= 7 && column-1 >= 0 && column-1 <= 7){
                            if(gameBoard[row-1-i][column-1].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row-1-1 >= 0 && row-1-1 <= 7 && column-1 >= 0 && column-1 <= 7){
                        if(gameBoard[row-1-1][column-1] == gameBoard[whiteQueen.row-1][whiteQueen.column-1]) shieldIsPossible = false;
                    }

                }else if(whiteQueen.column < column && whiteQueen.row == row){
                    // left
                    shapeDiff = column - whiteQueen.column-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1 >= 0 && row-1 <= 7 && column-1-i >= 0 && column-1-i <= 7){
                            if(gameBoard[row-1][column-1-i].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row-1 >= 0 && row-1 <= 7 && column-1-1 >= 0 && column-1-1 <= 7){
                        if(gameBoard[row-1][column-1-1] == gameBoard[whiteQueen.row-1][whiteQueen.column-1]) shieldIsPossible = false;
                    }
                }else if(whiteQueen.row > row && whiteQueen.column == column){
                    //up
                    shapeDiff = -row + whiteQueen.row-1;
                    for(int i=1; i<=shapeDiff; i++){
                        if(row-1+i >= 0 && row-1+i <= 7 && column-1 >= 0 && column-1 <= 7){
                            if(gameBoard[row-1+i][column-1].possibleMoveBlack){
                                shieldIsPossible = true;
                            }
                        }

                    }
                    if(row >= 0 && row <= 7 && column-1 >= 0 && column-1 <= 7){
                        if(gameBoard[row][column-1] == gameBoard[whiteQueen.row-1][whiteQueen.column-1]) shieldIsPossible = false;
                    }
                }

                System.out.println(shieldIsPossible);
                if(!gameBoard[whiteQueen.row-1][whiteQueen.column-1].possibleMoveBlack && !gameBoard[whiteQueen.row-1][whiteQueen.column-1].possibleMoveBlackKING && !shieldIsPossible){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(whitePawn1.kingIsCheckedByThisFigure){
                if(!gameBoard[whitePawn1.row-1][whitePawn1.column-1].possibleMoveBlack && !gameBoard[whitePawn1.row-1][whitePawn1.column-1].possibleMoveBlackKING){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(whitePawn2.kingIsCheckedByThisFigure){
                if(!gameBoard[whitePawn2.row-1][whitePawn2.column-1].possibleMoveBlack && !gameBoard[whitePawn2.row-1][whitePawn2.column-1].possibleMoveBlackKING){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(whitePawn3.kingIsCheckedByThisFigure){
                if(!gameBoard[whitePawn3.row-1][whitePawn3.column-1].possibleMoveBlack && !gameBoard[whitePawn3.row-1][whitePawn3.column-1].possibleMoveBlackKING){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(whitePawn4.kingIsCheckedByThisFigure){
                if(!gameBoard[whitePawn4.row-1][whitePawn4.column-1].possibleMoveBlack && !gameBoard[whitePawn4.row-1][whitePawn4.column-1].possibleMoveBlackKING){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(whitePawn5.kingIsCheckedByThisFigure){
                if(!gameBoard[whitePawn5.row-1][whitePawn5.column-1].possibleMoveBlack && !gameBoard[whitePawn5.row-1][whitePawn5.column-1].possibleMoveBlackKING){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(whitePawn6.kingIsCheckedByThisFigure){
                if(!gameBoard[whitePawn6.row-1][whitePawn6.column-1].possibleMoveBlack && !gameBoard[whitePawn6.row-1][whitePawn6.column-1].possibleMoveBlackKING){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(whitePawn7.kingIsCheckedByThisFigure){
                if(!gameBoard[whitePawn7.row-1][whitePawn7.column-1].possibleMoveBlack && !gameBoard[whitePawn7.row-1][whitePawn7.column-1].possibleMoveBlackKING){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else if(whitePawn8.kingIsCheckedByThisFigure){
                if(!gameBoard[whitePawn8.row-1][whitePawn8.column-1].possibleMoveBlack && !gameBoard[whitePawn8.row-1][whitePawn8.column-1].possibleMoveBlackKING){
                    if (row == 1 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 1 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 8 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if (row == 1 && column == 8 && tryCombination1) {
                        checkMate = true;
                    }else if(row == 1 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(row == 8 && column != 1 && column != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 1 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column == 8 && row != 1 && row != 8 && tryCombination2){
                        checkMate = true;
                    }else if(column != 1 && column != 8 && row != 1 && row != 8 && tryCombination3){
                        checkMate = true;
                    }else {
                        checkMate = false;
                    }

                    System.out.println("checkCounter = "+checkCounter);
                    System.out.println("busyFields = "+busyFields);
                }
            }else {
                check = false;
            }


        }

    }

}
