package Shapes;

import Game.BoardBLACK;
import Game.BoardWHITE;
import Game.Square;

import java.io.Serializable;

public class Knight implements Serializable {

    public String name;

    public int row,column;



    public boolean clicked;

    public boolean kingIsCheckedByThisFigure = false;


    public Knight(int row, int column, String name, boolean clicked){
        this.row = row;
        this.column = column;
        this.name = name;
        this.clicked = clicked;
    }

    public void setMoveOptionsForWhite(Square[][] gameBoard, King blackKing){

        if(row != 0 && column != 0){
            //lewo gora
            if(row-1-1 >= 0 && row-1-1 <= 7 && column-1-2 >= 0 && column-1-2 <= 7){
                gameBoard[row-1-1][column-1-2].possibleMoveWhite = true;
                if(gameBoard[row-1-1][column-1-2] == gameBoard[blackKing.row-1][blackKing.column-1]){
                    kingIsCheckedByThisFigure = true;
                }
            }
            if(row-1-2 >= 0 && row-1-2 <= 7 && column-1-1 >= 0 && column-1-1 <= 7){
                gameBoard[row-1-2][column-1-1].possibleMoveWhite = true;
                if(gameBoard[row-1-2][column-1-1] == gameBoard[blackKing.row-1][blackKing.column-1]){
                    kingIsCheckedByThisFigure = true;
                }
            }
            //prawo gora
            if(row-1-1 >= 0 && row-1-1 <= 7 && column-1+2 >= 0 && column-1+2 <= 7){
                gameBoard[row-1-1][column-1+2].possibleMoveWhite = true;
                if(gameBoard[row-1-1][column-1+2] == gameBoard[blackKing.row-1][blackKing.column-1]){
                    kingIsCheckedByThisFigure = true;
                }
            }
            if(row-1-2 >= 0 && row-1-2 <= 7 && column-1+1 >= 0 && column-1+1 <= 7){
                gameBoard[row-1-2][column-1+1].possibleMoveWhite = true;
                if(gameBoard[row-1-2][column-1+1] == gameBoard[blackKing.row-1][blackKing.column-1]){
                    kingIsCheckedByThisFigure = true;
                }
            }
            //prawo dół
            if(row-1+1 >= 0 && row-1+1 <= 7 && column-1+2 >= 0 && column-1+2 <= 7){
                gameBoard[row-1+1][column-1+2].possibleMoveWhite = true;
                if(gameBoard[row-1+1][column-1+2] == gameBoard[blackKing.row-1][blackKing.column-1]){
                    kingIsCheckedByThisFigure = true;
                }
            }
            if(row-1+2 >= 0 && row-1+2 <= 7 && column-1+1 >= 0 && column-1+1 <= 7){
                gameBoard[row-1+2][column-1+1].possibleMoveWhite = true;
                if(gameBoard[row-1+2][column-1+1] == gameBoard[blackKing.row-1][blackKing.column-1]){
                    kingIsCheckedByThisFigure = true;
                }
            }
            //lewo dół
            if(row-1+1 >= 0 && row-1+1 <= 7 && column-1-2 >= 0 && column-1-2 <= 7){
                gameBoard[row-1+1][column-1-2].possibleMoveWhite = true;
                if(gameBoard[row-1+1][column-1-2] == gameBoard[blackKing.row-1][blackKing.column-1]){
                    kingIsCheckedByThisFigure = true;
                }
            }
            if(row-1+2 >= 0 && row-1+2 <= 7 && column-1-1 >= 0 && column-1-1 <= 7){
                gameBoard[row-1+2][column-1-1].possibleMoveWhite = true;
                if(gameBoard[row-1+2][column-1-1] == gameBoard[blackKing.row-1][blackKing.column-1]){
                    kingIsCheckedByThisFigure = true;
                }
            }
        }



    }



    public void setMoveOptionsForBlack(Square[][] gameBoard, King whiteKing) {

        if(row != 0 && column != 0){

            //lewo gora
            if (row - 1 - 1 >= 0 && row - 1 - 1 <= 7 && column - 1 - 2 >= 0 && column - 1 - 2 <= 7) {
                gameBoard[row - 1 - 1][column - 1 - 2].possibleMoveBlack = true;
                if(gameBoard[row-1-1][column-1-2] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                    kingIsCheckedByThisFigure = true;
                }
            }
            if (row - 1 - 2 >= 0 && row - 1 - 2 <= 7 && column - 1 - 1 >= 0 && column - 1 - 1 <= 7) {
                gameBoard[row - 1 - 2][column - 1 - 1].possibleMoveBlack = true;
                if(gameBoard[row-1-2][column-1-1] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                    kingIsCheckedByThisFigure = true;
                }
            }
            //prawo gora
            if (row - 1 - 1 >= 0 && row - 1 - 1 <= 7 && column - 1 + 2 >= 0 && column - 1 + 2 <= 7) {
                gameBoard[row - 1 - 1][column - 1 + 2].possibleMoveBlack = true;
                if(gameBoard[row-1-1][column-1+2] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                    kingIsCheckedByThisFigure = true;
                }
            }
            if (row - 1 - 2 >= 0 && row - 1 - 2 <= 7 && column - 1 + 1 >= 0 && column - 1 + 1 <= 7) {
                gameBoard[row - 1 - 2][column - 1 + 1].possibleMoveBlack = true;
                if(gameBoard[row-1-2][column-1+1] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                    kingIsCheckedByThisFigure = true;
                }
            }
            //prawo dół
            if (row - 1 + 1 >= 0 && row - 1 + 1 <= 7 && column - 1 + 2 >= 0 && column - 1 + 2 <= 7) {
                gameBoard[row - 1 + 1][column - 1 + 2].possibleMoveBlack = true;
                if(gameBoard[row-1+1][column-1+2] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                    kingIsCheckedByThisFigure = true;
                }
            }
            if (row - 1 + 2 >= 0 && row - 1 + 2 <= 7 && column - 1 + 1 >= 0 && column - 1 + 1 <= 7) {
                gameBoard[row - 1 + 2][column - 1 + 1].possibleMoveBlack = true;
                if(gameBoard[row-1+2][column-1+1] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                    kingIsCheckedByThisFigure = true;
                }
            }
            //lewo dół
            if (row - 1 + 1 >= 0 && row - 1 + 1 <= 7 && column - 1 - 2 >= 0 && column - 1 - 2 <= 7) {
                gameBoard[row - 1 + 1][column - 1 - 2].possibleMoveBlack = true;
                if(gameBoard[row-1+1][column-1-2] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                    kingIsCheckedByThisFigure = true;
                }
            }
            if (row - 1 + 2 >= 0 && row - 1 + 2 <= 7 && column - 1 - 1 >= 0 && column - 1 - 1 <= 7) {
                gameBoard[row - 1 + 2][column - 1 - 1].possibleMoveBlack = true;
                if(gameBoard[row-1+2][column-1-1] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                    kingIsCheckedByThisFigure = true;
                }
            }
        }

    }


    //funkcja przywracajaca figure jezeli ruch na szachu jest niemozliwy
    public void setOptionsLikeBelowWhiteFunction(int rowBoard, int columnBoard, Square gameBoard[][], Knight blackKnight1, Knight blackKnight2,
                                    Bishop blackBishop1, Bishop blackBishop2,
                                    Rook blackRook1, Rook blackRook2,
                                    Queen blackQueen,
                                    Pawn blackPawn1,Pawn blackPawn2,Pawn blackPawn3,Pawn blackPawn4,
                                    Pawn blackPawn5,Pawn blackPawn6,Pawn blackPawn7,Pawn blackPawn8, King blackKing){

        if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 49){
            blackPawn1.row = rowBoard;
            blackPawn1.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 50){
            blackPawn2.row = rowBoard;
            blackPawn2.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 51){
            blackPawn3.row = rowBoard;
            blackPawn3.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 52){
            blackPawn4.row = rowBoard;
            blackPawn4.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 53){
            blackPawn5.row = rowBoard;
            blackPawn5.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 54){
            blackPawn6.row = rowBoard;
            blackPawn6.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 55){
            blackPawn7.row = rowBoard;
            blackPawn7.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 56){
            blackPawn8.row = rowBoard;
            blackPawn8.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 57){
            blackRook1.row = rowBoard;
            blackRook1.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 58){
            blackKnight1.row = rowBoard;
            blackKnight1.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 59){
            blackBishop1.row = rowBoard;
            blackBishop1.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 60){
            blackQueen.row = rowBoard;
            blackQueen.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 61){
            blackKing.row = rowBoard;
            blackKing.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 62){
            blackBishop2.row = rowBoard;
            blackBishop2.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 63){
            blackKnight2.row = rowBoard;
            blackKnight2.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 64){
            blackRook2.row = rowBoard;
            blackRook2.column = columnBoard;
        }

    }

    //funkcja przywracajaca figure jezeli ruch na szachu jest niemozliwy
    public void setOptionsLikeBelowBlackFunction(int rowBoard, int columnBoard, Square gameBoard[][], Knight whiteKnight1, Knight whiteKnight2,
                                                 Bishop whiteBishop1, Bishop whiteBishop2,
                                                 Rook whiteRook1, Rook whiteRook2,
                                                 Queen whiteQueen,
                                                 Pawn whitePawn1,Pawn whitePawn2,Pawn whitePawn3,Pawn whitePawn4,
                                                 Pawn whitePawn5,Pawn whitePawn6,Pawn whitePawn7,Pawn whitePawn8, King whiteKing){

        if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 9){
            whitePawn1.row = rowBoard;
            whitePawn1.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 10){
            whitePawn2.row = rowBoard;
            whitePawn2.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 11){
            whitePawn3.row = rowBoard;
            whitePawn3.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 12){
            whitePawn4.row = rowBoard;
            whitePawn4.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 13){
            whitePawn5.row = rowBoard;
            whitePawn5.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 14){
            whitePawn6.row = rowBoard;
            whitePawn6.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 15){
            whitePawn7.row = rowBoard;
            whitePawn7.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 16){
            whitePawn8.row = rowBoard;
            whitePawn8.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 1){
            whiteRook1.row = rowBoard;
            whiteRook1.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 2){
            whiteKnight1.row = rowBoard;
            whiteKnight1.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 3){
            whiteBishop1.row = rowBoard;
            whiteBishop1.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 4){
            whiteQueen.row = rowBoard;
            whiteQueen.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 5){
            whiteKing.row = rowBoard;
            whiteKing.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 6){
            whiteBishop2.row = rowBoard;
            whiteBishop2.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 7){
            whiteKnight2.row = rowBoard;
            whiteKnight2.column = columnBoard;
        }else if(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare == 8){
            whiteRook2.row = rowBoard;
            whiteRook2.column = columnBoard;
        }

    }


    public boolean moveWhite(int rowBoard, int columnBoard, Square gameBoard[][], King whiteKing, BoardWHITE boardWHITE, Knight blackKnight1, Knight blackKnight2,
                             Bishop blackBishop1, Bishop blackBishop2,
                             Rook blackRook1, Rook blackRook2,
                             Queen blackQueen,
                             Pawn blackPawn1,Pawn blackPawn2,Pawn blackPawn3,Pawn blackPawn4,
                             Pawn blackPawn5,Pawn blackPawn6,Pawn blackPawn7,Pawn blackPawn8, King blackKing){

        boolean moveDone = false;
        int tempVarA = row;
        int tempVarB = column;
        int varForRepairIdentity = gameBoard[row-1][column-1].identityOfSquare;
        int varForFunctionRemoveOptions = 0;

        if(((rowBoard == row-2 && columnBoard == column-1) ||
                (rowBoard == row-1 && columnBoard == column-2) ||
                (rowBoard == row-2 && columnBoard == column+1) ||
                (rowBoard == row-1 && columnBoard == column+2) ||
                (rowBoard == row+1 && columnBoard == column+2) ||
                (rowBoard == row+2 && columnBoard == column+1) ||
                (rowBoard == row+2 && columnBoard == column-1) ||
                (rowBoard == row+1 && columnBoard == column-2))
                && ((gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == false && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == false) || (gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == false && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true)) && !whiteKing.check

        ) {



            row = rowBoard;
            column = columnBoard;
            clicked = false;



            if(!gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE && !gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack){
                varForFunctionRemoveOptions = 0;

                boardWHITE.checkCollision();

                boardWHITE.removeOptionsForAll();
                boardWHITE.setAdditionalOptionsForWhite(tempVarA,tempVarB,rowBoard,columnBoard);
                boardWHITE.setOptionsForAll();
            }else if (gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack) {
                varForFunctionRemoveOptions = gameBoard[rowBoard-1][columnBoard-1].identityOfSquare;
                boardWHITE.checkCollision();

                boardWHITE.removeOptionsForAll();
                boardWHITE.setAdditionalOptionsForWhite(tempVarA,tempVarB,rowBoard,columnBoard);
                boardWHITE.setOptionsForAll();
            }else if(gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE){
                varForFunctionRemoveOptions = gameBoard[rowBoard-1][columnBoard-1].identityOfSquare;
                boardWHITE.checkCollision();

                boardWHITE.removeOptionsForAll();
                boardWHITE.setAdditionalOptionsForWhite(tempVarA,tempVarB,rowBoard,columnBoard);
                boardWHITE.setOptionsForAll();
            }


            if(gameBoard[whiteKing.row-1][whiteKing.column-1].possibleMoveBlack){
                row = tempVarA;
                column = tempVarB;
                clicked = false;


                setOptionsLikeBelowWhiteFunction(rowBoard,  columnBoard,  gameBoard,  blackKnight1,  blackKnight2,
                        blackBishop1,  blackBishop2,
                        blackRook1,  blackRook2,
                        blackQueen,
                        blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                        blackPawn5, blackPawn6, blackPawn7, blackPawn8,  blackKing);

                boardWHITE.removeAdditionalOptionsForALLinRealase(rowBoard, columnBoard, varForFunctionRemoveOptions);
                boardWHITE.addAdditionalOptionsForALLinRealase(row, column, varForRepairIdentity);



            }else{

                boardWHITE.addAdditionalOptionsForALLinRealase(row, column, varForRepairIdentity);

                row = rowBoard;
                column = columnBoard;
                clicked = false;


                moveDone = true;
                whiteKing.check = false;
            }





        }else if(((rowBoard == row-2 && columnBoard == column-1) ||
                    (rowBoard == row-1 && columnBoard == column-2) ||
                    (rowBoard == row-2 && columnBoard == column+1) ||
                    (rowBoard == row-1 && columnBoard == column+2) ||
                    (rowBoard == row+1 && columnBoard == column+2) ||
                    (rowBoard == row+2 && columnBoard == column+1) ||
                    (rowBoard == row+2 && columnBoard == column-1) ||
                    (rowBoard == row+1 && columnBoard == column-2))
                    && ((gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == false && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == false) || (gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == false && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true)) && whiteKing.check
            ){



            row = rowBoard;
            column = columnBoard;
            clicked = false;


            boardWHITE.checkCollision();
            boardWHITE.removeOptionsForAll();
            boardWHITE.setAdditionalOptionsForWhite(rowBoard,columnBoard,row,column);
            boardWHITE.setOptionsForAll();



            //check conditions
            if(!gameBoard[whiteKing.row-1][whiteKing.column-1].possibleMoveBlack){
                moveDone = true;
                whiteKing.check = false;

            }else{

                row = tempVarA;
                column = tempVarB;
                clicked = false;

                setOptionsLikeBelowWhiteFunction(rowBoard,  columnBoard,  gameBoard,  blackKnight1,  blackKnight2,
                        blackBishop1,  blackBishop2,
                        blackRook1,  blackRook2,
                        blackQueen,
                        blackPawn1, blackPawn2, blackPawn3, blackPawn4,
                        blackPawn5, blackPawn6, blackPawn7, blackPawn8,  blackKing);


                boardWHITE.removeAdditionalOptionsForALL(rowBoard,columnBoard);




            }

        } else {
            moveDone = false;
        }
        return moveDone;
    }

    public boolean moveBlack(int rowBoard, int columnBoard, Square gameBoard[][], King blackKing, BoardBLACK boardBLACK, Knight whiteKnight1, Knight whiteKnight2,
                             Bishop whiteBishop1, Bishop whiteBishop2,
                             Rook whiteRook1, Rook whiteRook2,
                             Queen whiteQueen,
                             Pawn whitePawn1,Pawn whitePawn2,Pawn whitePawn3,Pawn whitePawn4,
                             Pawn whitePawn5,Pawn whitePawn6,Pawn whitePawn7,Pawn whitePawn8, King whiteKing){

        boolean moveDone = false;
        int tempVarA = row;
        int tempVarB = column;
        int varForRepairIdentity = gameBoard[row-1][column-1].identityOfSquare;
        int varForFunctionRemoveOptions = 0;

        if(((rowBoard == row-2 && columnBoard == column-1) ||
                (rowBoard == row-1 && columnBoard == column-2) ||
                (rowBoard == row-2 && columnBoard == column+1) ||
                (rowBoard == row-1 && columnBoard == column+2) ||
                (rowBoard == row+1 && columnBoard == column+2) ||
                (rowBoard == row+2 && columnBoard == column+1) ||
                (rowBoard == row+2 && columnBoard == column-1) ||
                (rowBoard == row+1 && columnBoard == column-2))
                && ((gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == false && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == false) || (gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == false)) && !blackKing.check

        ) {


            row = rowBoard;
            column = columnBoard;
            clicked = false;

            if(!gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE && !gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack){
                varForFunctionRemoveOptions = 0;
                boardBLACK.checkCollision();
                boardBLACK.removeOptionsForAll();
                boardBLACK.setAdditionalOptionsForWhite(tempVarA,tempVarB,rowBoard,columnBoard);
                boardBLACK.setOptionsForAll();
            }else if (gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack) {
                varForFunctionRemoveOptions = gameBoard[rowBoard-1][columnBoard-1].identityOfSquare;
                boardBLACK.checkCollision();
                boardBLACK.removeOptionsForAll();
                boardBLACK.setAdditionalOptionsForWhite(tempVarA,tempVarB,rowBoard,columnBoard);
                boardBLACK.setOptionsForAll();
            }else if(gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE){
                varForFunctionRemoveOptions = gameBoard[rowBoard-1][columnBoard-1].identityOfSquare;
                boardBLACK.checkCollision();
                boardBLACK.removeOptionsForAll();
                boardBLACK.setAdditionalOptionsForWhite(tempVarA,tempVarB,rowBoard,columnBoard);
                boardBLACK.setOptionsForAll();
            }


            if(gameBoard[blackKing.row-1][blackKing.column-1].possibleMoveWhite){
                row = tempVarA;
                column = tempVarB;
                clicked = false;


                setOptionsLikeBelowBlackFunction(rowBoard,  columnBoard,  gameBoard,  whiteKnight1,  whiteKnight2,
                        whiteBishop1,  whiteBishop2,
                        whiteRook1,  whiteRook2,
                        whiteQueen,
                        whitePawn1, whitePawn2, whitePawn3, whitePawn4,
                        whitePawn5, whitePawn6, whitePawn7, whitePawn8,  whiteKing);

                boardBLACK.removeAdditionalOptionsForALLinRealase(rowBoard, columnBoard, varForFunctionRemoveOptions);
                boardBLACK.addAdditionalOptionsForALLinRealase(row, column, varForRepairIdentity);


                System.out.println(gameBoard[rowBoard-1][columnBoard-1].identityOfSquare);

            }else{

                boardBLACK.addAdditionalOptionsForALLinRealase(row, column, varForRepairIdentity);

                row = rowBoard;
                column = columnBoard;
                clicked = false;


                moveDone = true;
                blackKing.check = false;
            }
        }else if(((rowBoard == row-2 && columnBoard == column-1) ||
                (rowBoard == row-1 && columnBoard == column-2) ||
                (rowBoard == row-2 && columnBoard == column+1) ||
                (rowBoard == row-1 && columnBoard == column+2) ||
                (rowBoard == row+1 && columnBoard == column+2) ||
                (rowBoard == row+2 && columnBoard == column+1) ||
                (rowBoard == row+2 && columnBoard == column-1) ||
                (rowBoard == row+1 && columnBoard == column-2))
                && ((gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == false && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == false) || (gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true && gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == false)) && blackKing.check
        ){

            row = rowBoard;
            column = columnBoard;
            clicked = false;

            boardBLACK.checkCollision();

            boardBLACK.removeOptionsForAll();
            boardBLACK.setAdditionalOptionsForBlack(rowBoard,columnBoard,row,column);
            boardBLACK.setOptionsForAll();

            //nie dziala odslanianie
            //check conditions
            if(!gameBoard[blackKing.row-1][blackKing.column-1].possibleMoveWhite){
                moveDone = true;
                blackKing.check = false;
            }else{
                row = tempVarA;
                column = tempVarB;
                clicked = false;



                setOptionsLikeBelowBlackFunction(rowBoard,columnBoard,gameBoard,whiteKnight1,whiteKnight2,
                        whiteBishop1,whiteBishop2,
                        whiteRook1,whiteRook2,
                        whiteQueen,
                        whitePawn1,whitePawn2,whitePawn3,whitePawn4,
                        whitePawn5,whitePawn6,whitePawn7,whitePawn8,whiteKing);

                boardBLACK.removeAdditionalOptionsForALL(rowBoard,columnBoard);
            }

        }else {
            moveDone = false;
        }

        return moveDone;
    }
}
