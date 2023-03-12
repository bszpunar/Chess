package Shapes;

import Game.BoardBLACK;
import Game.BoardWHITE;
import Game.Square;

import java.io.Serializable;

import static java.lang.Thread.sleep;

public class Queen implements Serializable {

    public String name;

    public int row,column;

    public int shapeDiff;

    public boolean clicked;

    public boolean kingIsCheckedByThisFigure = false;


    public Queen(int row, int column, String name, boolean clicked){
        this.row = row;
        this.column = column;
        this.name = name;
        this.clicked = clicked;
    }

    public void setMoveOptionForWhite(Square[][] gameBoard, King blackKing){

        if(row != 0 && column != 0){
            if(row >= 1 && column >= 1 && row <= 8 && column <= 8){
                int diff1 = 8;

                diff1 = diff1 - row;

                //dół
                for (int i = 1; i <= diff1; i++) {
                    if ((gameBoard[row - 1 + i][column - 1].occupiedFieldByWHITE == false || gameBoard[row - 1 + i][column - 1].occupiedFieldByWHITE == true) && (gameBoard[row - 1 + i][column - 1].occupiedFieldByBlack == false || gameBoard[row - 1 + i][column - 1].occupiedFieldByBlack == true)) {
                        gameBoard[row - 1 + i][column - 1].possibleMoveWhite = true;
                        if(gameBoard[row - 1 + i][column - 1] == gameBoard[blackKing.row-1][blackKing.column-1]){
                            kingIsCheckedByThisFigure = true;
                        }
                        if (gameBoard[row - 1 + i][column - 1].occupiedFieldByBlack == true || gameBoard[row - 1 + i][column - 1].occupiedFieldByWHITE == true)
                            break;
                    }

                }

                //góra
                int diff2 = 8;
                diff2 = row-1;

                for (int i = 1; i <= diff2; i++) {
                    if ((gameBoard[row - 1 - i][column - 1].occupiedFieldByWHITE == false || gameBoard[row - 1 - i][column - 1].occupiedFieldByWHITE == true) && (gameBoard[row - 1 - i][column - 1].occupiedFieldByBlack == false || gameBoard[row - 1 - i][column - 1].occupiedFieldByBlack == true)) {
                        gameBoard[row - 1 - i][column - 1].possibleMoveWhite = true;
                        if(gameBoard[row - 1 - i][column - 1] == gameBoard[blackKing.row-1][blackKing.column-1]){
                            kingIsCheckedByThisFigure = true;
                        }
                        if (gameBoard[row - 1 - i][column - 1].occupiedFieldByBlack == true || gameBoard[row - 1 - i][column - 1].occupiedFieldByWHITE == true)
                            break;
                    }

                }

                //prawo

                int diff3 = 8;
                diff3 = diff3 - column;

                for (int i = 1; i <= diff3; i++) {
                    if ((gameBoard[row - 1][column - 1+i].occupiedFieldByWHITE == false || gameBoard[row - 1 ][column - 1+i].occupiedFieldByWHITE == true) && (gameBoard[row - 1][column - 1+i].occupiedFieldByBlack == false || gameBoard[row - 1][column - 1+i].occupiedFieldByBlack == true)) {
                        gameBoard[row - 1][column - 1+i].possibleMoveWhite = true;
                        if(gameBoard[row - 1][column - 1+i] == gameBoard[blackKing.row-1][blackKing.column-1]){
                            kingIsCheckedByThisFigure = true;
                        }
                        if (gameBoard[row - 1][column - 1+i].occupiedFieldByBlack == true || gameBoard[row - 1][column - 1+i].occupiedFieldByWHITE == true)
                            break;
                    }

                }

                //lewo

                int diff4 = 8;
                diff4 = column-1;

                for (int i = 1; i <= diff4; i++) {
                    if ((gameBoard[row - 1][column - 1-i].occupiedFieldByWHITE == false || gameBoard[row - 1 ][column - 1-i].occupiedFieldByWHITE == true) && (gameBoard[row - 1][column - 1-i].occupiedFieldByBlack == false || gameBoard[row - 1][column - 1-i].occupiedFieldByBlack == true)) {
                        gameBoard[row - 1][column - 1-i].possibleMoveWhite = true;
                        if(gameBoard[row - 1][column - 1-i] == gameBoard[blackKing.row-1][blackKing.column-1]){
                            kingIsCheckedByThisFigure = true;
                        }
                        if (gameBoard[row - 1][column - 1-i].occupiedFieldByBlack == true || gameBoard[row - 1][column - 1-i].occupiedFieldByWHITE == true)
                            break;
                    }

                }


                int rowDiff1 = 8;
                int columnDiff1 = 8;

                rowDiff1 = rowDiff1 - row;
                columnDiff1 = columnDiff1 - column;


                //prawo dół
                if(rowDiff1 > columnDiff1){
                    for(int i=1; i<=columnDiff1; i++){
                        if((gameBoard[row-1+i][column-1+i].occupiedFieldByWHITE == false ||  gameBoard[row-1+i][column-1+i].occupiedFieldByWHITE == true) && (gameBoard[row-1+i][column-1+i].occupiedFieldByBlack == false || gameBoard[row-1+i][column-1+i].occupiedFieldByBlack == true)){
                            gameBoard[row-1+i][column-1+i].possibleMoveWhite = true;
                            if(gameBoard[row-1+i][column-1+i] == gameBoard[blackKing.row-1][blackKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1+i][column-1+i].occupiedFieldByBlack == true || gameBoard[row-1+i][column-1+i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }else if(rowDiff1 < columnDiff1){
                    for(int i=1; i<=rowDiff1; i++){
                        if((gameBoard[row-1+i][column-1+i].occupiedFieldByWHITE == false ||  gameBoard[row-1+i][column-1+i].occupiedFieldByWHITE == true) && (gameBoard[row-1+i][column-1+i].occupiedFieldByBlack == false || gameBoard[row-1+i][column-1+i].occupiedFieldByBlack == true)){
                            gameBoard[row-1+i][column-1+i].possibleMoveWhite = true;
                            if(gameBoard[row-1+i][column-1+i] == gameBoard[blackKing.row-1][blackKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1+i][column-1+i].occupiedFieldByBlack == true || gameBoard[row-1+i][column-1+i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }else if(rowDiff1 == columnDiff1){

                    for(int i=1; i<=rowDiff1; i++){
                        if((gameBoard[row-1+i][column-1+i].occupiedFieldByWHITE == false ||  gameBoard[row-1+i][column-1+i].occupiedFieldByWHITE == true) && (gameBoard[row-1+i][column-1+i].occupiedFieldByBlack == false || gameBoard[row-1+i][column-1+i].occupiedFieldByBlack == true)){
                            gameBoard[row-1+i][column-1+i].possibleMoveWhite = true;
                            if(gameBoard[row-1+i][column-1+i] == gameBoard[blackKing.row-1][blackKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1+i][column-1+i].occupiedFieldByBlack == true || gameBoard[row-1+i][column-1+i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }

                //prawo góra

                int rowDiff2 = 8;
                int columnDiff2 = 8;

                rowDiff2 = row - 1;
                columnDiff2 = columnDiff2 - column;

                if(rowDiff2 > columnDiff2){

                    for(int i=1; i<=columnDiff2; i++){

                        if((gameBoard[row-1-i][column-1+i].occupiedFieldByWHITE == false ||  gameBoard[row-1-i][column-1+i].occupiedFieldByWHITE == true) && (gameBoard[row-1-i][column-1+i].occupiedFieldByBlack == false || gameBoard[row-1-i][column-1+i].occupiedFieldByBlack == true)){
                            gameBoard[row-1-i][column-1+i].possibleMoveWhite = true;

                            if(gameBoard[row-1-i][column-1+i] == gameBoard[blackKing.row-1][blackKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1-i][column-1+i].occupiedFieldByBlack == true || gameBoard[row-1-i][column-1+i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }else if(rowDiff2 < columnDiff2){

                    for(int i=1; i<=rowDiff2; i++){
                        if((gameBoard[row-1-i][column-1+i].occupiedFieldByWHITE == false ||  gameBoard[row-1-i][column-1+i].occupiedFieldByWHITE == true) && (gameBoard[row-1-i][column-1+i].occupiedFieldByBlack == false || gameBoard[row-1-i][column-1+i].occupiedFieldByBlack == true)){
                            gameBoard[row-1-i][column-1+i].possibleMoveWhite = true;
                            if(gameBoard[row-1-i][column-1+i] == gameBoard[blackKing.row-1][blackKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1-i][column-1+i].occupiedFieldByBlack == true || gameBoard[row-1-i][column-1+i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }else if(rowDiff2 == columnDiff2){

                    for(int i=1; i<=rowDiff2; i++){
                        if((gameBoard[row-1-i][column-1+i].occupiedFieldByWHITE == false ||  gameBoard[row-1-i][column-1+i].occupiedFieldByWHITE == true) && (gameBoard[row-1-i][column-1+i].occupiedFieldByBlack == false || gameBoard[row-1-i][column-1+i].occupiedFieldByBlack == true)){
                            gameBoard[row-1-i][column-1+i].possibleMoveWhite = true;
                            if(gameBoard[row-1-i][column-1+i] == gameBoard[blackKing.row-1][blackKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1-i][column-1+i].occupiedFieldByBlack == true || gameBoard[row-1-i][column-1+i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }






                //lewo dół

                int rowDiff3 = 8;
                int columnDiff3 = 8;

                rowDiff3 = rowDiff3 - row;
                columnDiff3 = column - 1;

                if(rowDiff3 > columnDiff3){
                    for(int i=1; i<=columnDiff3; i++){
                        if((gameBoard[row-1+i][column-1-i].occupiedFieldByWHITE == false ||  gameBoard[row-1+i][column-1-i].occupiedFieldByWHITE == true) && (gameBoard[row-1+i][column-1-i].occupiedFieldByBlack == false || gameBoard[row-1+i][column-1-i].occupiedFieldByBlack == true)){
                            gameBoard[row-1+i][column-1-i].possibleMoveWhite = true;
                            if(gameBoard[row-1+i][column-1-i] == gameBoard[blackKing.row-1][blackKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1+i][column-1-i].occupiedFieldByBlack == true || gameBoard[row-1+i][column-1-i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }else if(rowDiff3 < columnDiff3){
                    for(int i=1; i<=rowDiff3; i++){
                        if((gameBoard[row-1+i][column-1-i].occupiedFieldByWHITE == false ||  gameBoard[row-1+i][column-1-i].occupiedFieldByWHITE == true) && (gameBoard[row-1+i][column-1-i].occupiedFieldByBlack == false || gameBoard[row-1+i][column-1-i].occupiedFieldByBlack == true)){
                            gameBoard[row-1+i][column-1-i].possibleMoveWhite = true;
                            if(gameBoard[row-1+i][column-1-i] == gameBoard[blackKing.row-1][blackKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1+i][column-1-i].occupiedFieldByBlack == true || gameBoard[row-1+i][column-1-i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }else if(rowDiff3 == columnDiff3){

                    for(int i=1; i<=rowDiff3; i++){
                        if((gameBoard[row-1+i][column-1-i].occupiedFieldByWHITE == false ||  gameBoard[row-1+i][column-1-i].occupiedFieldByWHITE == true) && (gameBoard[row-1+i][column-1-i].occupiedFieldByBlack == false || gameBoard[row-1+i][column-1-i].occupiedFieldByBlack == true)){
                            gameBoard[row-1+i][column-1-i].possibleMoveWhite = true;
                            if(gameBoard[row-1+i][column-1-i] == gameBoard[blackKing.row-1][blackKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1+i][column-1-i].occupiedFieldByBlack == true || gameBoard[row-1+i][column-1-i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }


                //lewo góra

                int rowDiff4 = 8;
                int columnDiff4 = 8;

                rowDiff4 = row - 1;
                columnDiff4 = column - 1;

                if(rowDiff4 > columnDiff4){

                    for(int i=1; i<=columnDiff4; i++){

                        if((gameBoard[row-1-i][column-1-i].occupiedFieldByWHITE == false ||  gameBoard[row-1-i][column-1-i].occupiedFieldByWHITE == true) && (gameBoard[row-1-i][column-1-i].occupiedFieldByBlack == false || gameBoard[row-1-i][column-1-i].occupiedFieldByBlack == true)){
                            gameBoard[row-1-i][column-1-i].possibleMoveWhite = true;
                            if(gameBoard[row-1-i][column-1-i] == gameBoard[blackKing.row-1][blackKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1-i][column-1-i].occupiedFieldByBlack == true || gameBoard[row-1-i][column-1-i].occupiedFieldByWHITE == true) break;
                        }

                    }

                }else if(rowDiff4 < columnDiff4){

                    for(int i=1; i<=rowDiff4; i++){
                        if((gameBoard[row-1-i][column-1-i].occupiedFieldByWHITE == false ||  gameBoard[row-1-i][column-1-i].occupiedFieldByWHITE == true) && (gameBoard[row-1-i][column-1-i].occupiedFieldByBlack == false || gameBoard[row-1-i][column-1-i].occupiedFieldByBlack == true)){
                            gameBoard[row-1-i][column-1-i].possibleMoveWhite = true;
                            if(gameBoard[row-1-i][column-1-i] == gameBoard[blackKing.row-1][blackKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1-i][column-1-i].occupiedFieldByBlack == true || gameBoard[row-1-i][column-1-i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }else if(rowDiff4 == columnDiff4){

                    for(int i=1; i<=rowDiff4; i++){
                        if((gameBoard[row-1-i][column-1-i].occupiedFieldByWHITE == false ||  gameBoard[row-1-i][column-1-i].occupiedFieldByWHITE == true) && (gameBoard[row-1-i][column-1-i].occupiedFieldByBlack == false || gameBoard[row-1-i][column-1-i].occupiedFieldByBlack == true)){
                            gameBoard[row-1-i][column-1-i].possibleMoveWhite = true;
                            if(gameBoard[row-1-i][column-1-i] == gameBoard[blackKing.row-1][blackKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1-i][column-1-i].occupiedFieldByBlack == true || gameBoard[row-1-i][column-1-i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }
            }

        }



    }

    public void setMoveOptionForBlack(Square[][] gameBoard, King whiteKing){


        if(row != 0 && column != 0){
            if(row >= 1 && column >= 1 && row <= 8 && column <= 8){
                int diff1 = 8;

                diff1 = diff1 - row;

                //dół
                for (int i = 1; i <= diff1; i++) {
                    if ((gameBoard[row - 1 + i][column - 1].occupiedFieldByWHITE == false || gameBoard[row - 1 + i][column - 1].occupiedFieldByWHITE == true) && (gameBoard[row - 1 + i][column - 1].occupiedFieldByBlack == false || gameBoard[row - 1 + i][column - 1].occupiedFieldByBlack == true)) {
                        gameBoard[row - 1 + i][column - 1].possibleMoveBlack = true;
                        if(gameBoard[row - 1 + i][column - 1] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                            kingIsCheckedByThisFigure = true;
                        }
                        if (gameBoard[row - 1 + i][column - 1].occupiedFieldByBlack == true || gameBoard[row - 1 + i][column - 1].occupiedFieldByWHITE == true)
                            break;
                    }

                }

                //góra
                int diff2 = 8;
                diff2 = row-1;

                for (int i = 1; i <= diff2; i++) {
                    if ((gameBoard[row - 1 - i][column - 1].occupiedFieldByWHITE == false || gameBoard[row - 1 - i][column - 1].occupiedFieldByWHITE == true) && (gameBoard[row - 1 - i][column - 1].occupiedFieldByBlack == false || gameBoard[row - 1 - i][column - 1].occupiedFieldByBlack == true)) {
                        gameBoard[row - 1 - i][column - 1].possibleMoveBlack = true;
                        if(gameBoard[row - 1 - i][column - 1] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                            kingIsCheckedByThisFigure = true;
                        }
                        if (gameBoard[row - 1 - i][column - 1].occupiedFieldByBlack == true || gameBoard[row - 1 - i][column - 1].occupiedFieldByWHITE == true)
                            break;
                    }

                }

                //prawo

                int diff3 = 8;
                diff3 = diff3 - column;

                for (int i = 1; i <= diff3; i++) {
                    if ((gameBoard[row - 1][column - 1+i].occupiedFieldByWHITE == false || gameBoard[row - 1 ][column - 1+i].occupiedFieldByWHITE == true) && (gameBoard[row - 1][column - 1+i].occupiedFieldByBlack == false || gameBoard[row - 1][column - 1+i].occupiedFieldByBlack == true)) {
                        gameBoard[row - 1][column - 1+i].possibleMoveBlack = true;
                        if(gameBoard[row - 1][column - 1+i] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                            kingIsCheckedByThisFigure = true;
                        }
                        if (gameBoard[row - 1][column - 1+i].occupiedFieldByBlack == true || gameBoard[row - 1][column - 1+i].occupiedFieldByWHITE == true)
                            break;
                    }

                }

                //lewo

                int diff4 = 8;
                diff4 = column-1;

                for (int i = 1; i <= diff4; i++) {
                    if ((gameBoard[row - 1][column - 1-i].occupiedFieldByWHITE == false || gameBoard[row - 1 ][column - 1-i].occupiedFieldByWHITE == true) && (gameBoard[row - 1][column - 1-i].occupiedFieldByBlack == false || gameBoard[row - 1][column - 1-i].occupiedFieldByBlack == true)) {
                        gameBoard[row - 1][column - 1-i].possibleMoveBlack = true;
                        if(gameBoard[row - 1][column - 1-i] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                            kingIsCheckedByThisFigure = true;
                        }
                        if (gameBoard[row - 1][column - 1-i].occupiedFieldByBlack == true || gameBoard[row - 1][column - 1-i].occupiedFieldByWHITE == true)
                            break;
                    }

                }

                int rowDiff1 = 8;
                int columnDiff1 = 8;

                rowDiff1 = rowDiff1 - row;
                columnDiff1 = columnDiff1 - column;


                //prawo dół
                if(rowDiff1 > columnDiff1){
                    for(int i=1; i<=columnDiff1; i++){
                        if((gameBoard[row-1+i][column-1+i].occupiedFieldByWHITE == false ||  gameBoard[row-1+i][column-1+i].occupiedFieldByWHITE == true) && (gameBoard[row-1+i][column-1+i].occupiedFieldByBlack == false || gameBoard[row-1+i][column-1+i].occupiedFieldByBlack == true)){
                            gameBoard[row-1+i][column-1+i].possibleMoveBlack = true;
                            if(gameBoard[row-1+i][column-1+i] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1+i][column-1+i].occupiedFieldByBlack == true || gameBoard[row-1+i][column-1+i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }else if(rowDiff1 < columnDiff1){
                    for(int i=1; i<=rowDiff1; i++){
                        if((gameBoard[row-1+i][column-1+i].occupiedFieldByWHITE == false ||  gameBoard[row-1+i][column-1+i].occupiedFieldByWHITE == true) && (gameBoard[row-1+i][column-1+i].occupiedFieldByBlack == false || gameBoard[row-1+i][column-1+i].occupiedFieldByBlack == true)){
                            gameBoard[row-1+i][column-1+i].possibleMoveBlack = true;
                            if(gameBoard[row-1+i][column-1+i] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1+i][column-1+i].occupiedFieldByBlack == true || gameBoard[row-1+i][column-1+i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }else if(rowDiff1 == columnDiff1){

                    for(int i=1; i<=rowDiff1; i++){
                        if((gameBoard[row-1+i][column-1+i].occupiedFieldByWHITE == false ||  gameBoard[row-1+i][column-1+i].occupiedFieldByWHITE == true) && (gameBoard[row-1+i][column-1+i].occupiedFieldByBlack == false || gameBoard[row-1+i][column-1+i].occupiedFieldByBlack == true)){
                            gameBoard[row-1+i][column-1+i].possibleMoveBlack = true;
                            if(gameBoard[row-1+i][column-1+i] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1+i][column-1+i].occupiedFieldByBlack == true || gameBoard[row-1+i][column-1+i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }

                //prawo góra

                int rowDiff2 = 8;
                int columnDiff2 = 8;

                rowDiff2 = row - 1;
                columnDiff2 = columnDiff2 - column;

                if(rowDiff2 > columnDiff2){

                    for(int i=1; i<=columnDiff2; i++){

                        if((gameBoard[row-1-i][column-1+i].occupiedFieldByWHITE == false ||  gameBoard[row-1-i][column-1+i].occupiedFieldByWHITE == true) && (gameBoard[row-1-i][column-1+i].occupiedFieldByBlack == false || gameBoard[row-1-i][column-1+i].occupiedFieldByBlack == true)){
                            gameBoard[row-1-i][column-1+i].possibleMoveBlack = true;
                            if(gameBoard[row-1-i][column-1+i] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }

                            if(gameBoard[row-1-i][column-1+i].occupiedFieldByBlack == true || gameBoard[row-1-i][column-1+i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }else if(rowDiff2 < columnDiff2){

                    for(int i=1; i<=rowDiff2; i++){
                        if((gameBoard[row-1-i][column-1+i].occupiedFieldByWHITE == false ||  gameBoard[row-1-i][column-1+i].occupiedFieldByWHITE == true) && (gameBoard[row-1-i][column-1+i].occupiedFieldByBlack == false || gameBoard[row-1-i][column-1+i].occupiedFieldByBlack == true)){
                            gameBoard[row-1-i][column-1+i].possibleMoveBlack = true;
                            if(gameBoard[row-1-i][column-1+i] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1-i][column-1+i].occupiedFieldByBlack == true || gameBoard[row-1-i][column-1+i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }else if(rowDiff2 == columnDiff2){

                    for(int i=1; i<=rowDiff2; i++){
                        if((gameBoard[row-1-i][column-1+i].occupiedFieldByWHITE == false ||  gameBoard[row-1-i][column-1+i].occupiedFieldByWHITE == true) && (gameBoard[row-1-i][column-1+i].occupiedFieldByBlack == false || gameBoard[row-1-i][column-1+i].occupiedFieldByBlack == true)){
                            gameBoard[row-1-i][column-1+i].possibleMoveBlack = true;
                            if(gameBoard[row-1-i][column-1+i] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1-i][column-1+i].occupiedFieldByBlack == true || gameBoard[row-1-i][column-1+i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }






                //lewo dół

                int rowDiff3 = 8;
                int columnDiff3 = 8;

                rowDiff3 = rowDiff3 - row;
                columnDiff3 = column - 1;

                if(rowDiff3 > columnDiff3){
                    for(int i=1; i<=columnDiff3; i++){
                        if((gameBoard[row-1+i][column-1-i].occupiedFieldByWHITE == false ||  gameBoard[row-1+i][column-1-i].occupiedFieldByWHITE == true) && (gameBoard[row-1+i][column-1-i].occupiedFieldByBlack == false || gameBoard[row-1+i][column-1-i].occupiedFieldByBlack == true)){
                            gameBoard[row-1+i][column-1-i].possibleMoveBlack = true;
                            if(gameBoard[row-1+i][column-1-i] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1+i][column-1-i].occupiedFieldByBlack == true || gameBoard[row-1+i][column-1-i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }else if(rowDiff3 < columnDiff3){
                    for(int i=1; i<=rowDiff3; i++){
                        if((gameBoard[row-1+i][column-1-i].occupiedFieldByWHITE == false ||  gameBoard[row-1+i][column-1-i].occupiedFieldByWHITE == true) && (gameBoard[row-1+i][column-1-i].occupiedFieldByBlack == false || gameBoard[row-1+i][column-1-i].occupiedFieldByBlack == true)){
                            gameBoard[row-1+i][column-1-i].possibleMoveBlack = true;
                            if(gameBoard[row-1+i][column-1-i] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1+i][column-1-i].occupiedFieldByBlack == true || gameBoard[row-1+i][column-1-i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }else if(rowDiff3 == columnDiff3){

                    for(int i=1; i<=rowDiff3; i++){
                        if((gameBoard[row-1+i][column-1-i].occupiedFieldByWHITE == false ||  gameBoard[row-1+i][column-1-i].occupiedFieldByWHITE == true) && (gameBoard[row-1+i][column-1-i].occupiedFieldByBlack == false || gameBoard[row-1+i][column-1-i].occupiedFieldByBlack == true)){
                            gameBoard[row-1+i][column-1-i].possibleMoveBlack = true;
                            if(gameBoard[row-1+i][column-1-i] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1+i][column-1-i].occupiedFieldByBlack == true || gameBoard[row-1+i][column-1-i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }


                //lewo góra

                int rowDiff4 = 8;
                int columnDiff4 = 8;

                rowDiff4 = row - 1;
                columnDiff4 = column - 1;

                if(rowDiff4 > columnDiff4){

                    for(int i=1; i<=columnDiff4; i++){

                        if((gameBoard[row-1-i][column-1-i].occupiedFieldByWHITE == false ||  gameBoard[row-1-i][column-1-i].occupiedFieldByWHITE == true) && (gameBoard[row-1-i][column-1-i].occupiedFieldByBlack == false || gameBoard[row-1-i][column-1-i].occupiedFieldByBlack == true)){
                            gameBoard[row-1-i][column-1-i].possibleMoveBlack = true;
                            if(gameBoard[row-1-i][column-1-i] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1-i][column-1-i].occupiedFieldByBlack == true || gameBoard[row-1-i][column-1-i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }else if(rowDiff4 < columnDiff4){

                    for(int i=1; i<=rowDiff4; i++){
                        if((gameBoard[row-1-i][column-1-i].occupiedFieldByWHITE == false ||  gameBoard[row-1-i][column-1-i].occupiedFieldByWHITE == true) && (gameBoard[row-1-i][column-1-i].occupiedFieldByBlack == false || gameBoard[row-1-i][column-1-i].occupiedFieldByBlack == true)){
                            gameBoard[row-1-i][column-1-i].possibleMoveBlack = true;
                            if(gameBoard[row-1-i][column-1-i] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if(gameBoard[row-1-i][column-1-i].occupiedFieldByBlack == true || gameBoard[row-1-i][column-1-i].occupiedFieldByWHITE == true) break;
                        }

                    }
                }else if(rowDiff4 == columnDiff4){

                    for(int i=1; i<=rowDiff4; i++){
                        if((gameBoard[row-1-i][column-1-i].occupiedFieldByWHITE == false ||  gameBoard[row-1-i][column-1-i].occupiedFieldByWHITE == true) && (gameBoard[row-1-i][column-1-i].occupiedFieldByBlack == false || gameBoard[row-1-i][column-1-i].occupiedFieldByBlack == true)){
                            gameBoard[row-1-i][column-1-i].possibleMoveBlack = true;
                            if(gameBoard[row-1-i][column-1-i] == gameBoard[whiteKing.row-1][whiteKing.column-1]){
                                kingIsCheckedByThisFigure = true;
                            }
                            if (gameBoard[row - 1 - i][column - 1 - i].occupiedFieldByBlack == true || gameBoard[row - 1 - i][column - 1 - i].occupiedFieldByWHITE == true)
                                break;
                        }

                    }
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
        boolean move = true;
        int tempVarA = row;
        int tempVarB = column;
        int varForRepairIdentity = gameBoard[row-1][column-1].identityOfSquare;
        int varForFunctionRemoveOptions = 0;

        if(rowBoard > row && columnBoard > column) {
            //move down-right bishop
            shapeDiff = rowBoard - row-1;
            for(int i=1; i<=shapeDiff; i++){
                if(gameBoard[row+i-1][column+i-1].occupiedFieldByWHITE == true ||
                        gameBoard[row+i-1][column+i-1].occupiedFieldByBlack == true){
                    move = false;
                }
            }
            if(gameBoard[row][column].occupiedFieldByWHITE == true ||
                    gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) move = false;




            if(rowBoard - row == columnBoard - column && move == true && !whiteKing.check){

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



            }else if(rowBoard - row == columnBoard - column && move == true && whiteKing.check){

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
                            blackPawn5, blackPawn6, blackPawn7, blackPawn8,  blackKing);                }
                boardWHITE.removeAdditionalOptionsForALL(rowBoard,columnBoard);



            }

        }else if(rowBoard < row && columnBoard > column){
            //move up-right bishop

            shapeDiff = -rowBoard + row-1;
            for(int i=1; i<=shapeDiff; i++){
                if(gameBoard[row-i-1][column+i-1].occupiedFieldByWHITE == true || gameBoard[row-i-1][column+i-1].occupiedFieldByBlack == true){
                    move = false;
                }
            }
            if(gameBoard[row-2][column].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) move = false;



            if(-rowBoard + row == columnBoard - column && move == true && !whiteKing.check){

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



            }else if(-rowBoard + row == columnBoard - column && move == true && whiteKing.check){

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
                            blackPawn5, blackPawn6, blackPawn7, blackPawn8,  blackKing);                }

                boardWHITE.removeAdditionalOptionsForALL(rowBoard,columnBoard);


            }


        }else if(rowBoard < row && columnBoard < column){
            //move up-left bishop

            shapeDiff = -rowBoard + row-1;
            for(int i=1; i<=shapeDiff; i++){
                if(gameBoard[row-i-1][column-i-1].occupiedFieldByWHITE == true || gameBoard[row-i-1][column-i-1].occupiedFieldByBlack == true){
                    move = false;
                }
            }
            if(gameBoard[row-2][column-2].occupiedFieldByWHITE || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) move = false;


            if(-rowBoard + row == -columnBoard + column && move == true && !whiteKing.check){

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



            }else if(-rowBoard + row == -columnBoard + column && move == true && whiteKing.check){

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
                            blackPawn5, blackPawn6, blackPawn7, blackPawn8,  blackKing);                }
                boardWHITE.removeAdditionalOptionsForALL(rowBoard,columnBoard);



            }


        }else if(rowBoard > row && columnBoard < column){
            //move down-left bishop

            shapeDiff = rowBoard - row-1;
            for(int i=1; i<=shapeDiff; i++){
                if(gameBoard[row+i-1][column-i-1].occupiedFieldByWHITE == true || gameBoard[row+i-1][column-i-1].occupiedFieldByBlack == true){
                    move = false;
                }
            }
            if(gameBoard[row][column-2].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) move = false;



            if(rowBoard - row == -columnBoard + column && move == true && !whiteKing.check){

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



            }else if(rowBoard - row == -columnBoard + column && move == true && whiteKing.check){

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
                            blackPawn5, blackPawn6, blackPawn7, blackPawn8,  blackKing);                }
                boardWHITE.removeAdditionalOptionsForALL(rowBoard,columnBoard);



            }

        }else if(row < rowBoard && column == columnBoard)
        {
            //move down Rook
            shapeDiff = rowBoard - row-1;
            for(int i=1; i<=shapeDiff; i++){
                if(gameBoard[row+i-1][columnBoard-1].occupiedFieldByWHITE == true || gameBoard[row+i-1][columnBoard-1].occupiedFieldByBlack == true){
                    move = false;
                }
            }
            if(gameBoard[row][column-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) move = false;

            if(move == true && !whiteKing.check){

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



            }else if(move == true && whiteKing.check){

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
                            blackPawn5, blackPawn6, blackPawn7, blackPawn8,  blackKing);                }
                boardWHITE.removeAdditionalOptionsForALL(rowBoard,columnBoard);



            }


        }else if(row == rowBoard && column < columnBoard)
        {
            //move right Rook
            shapeDiff = columnBoard - column-1;
            for(int i=1; i<=shapeDiff; i++){
                if(gameBoard[rowBoard-1][column+i-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][column+i-1].occupiedFieldByBlack == true){
                    move = false;
                }
            }
            if(gameBoard[row-1][column].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) move = false;

            if(move == true && !whiteKing.check){

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



            }else if(move == true && whiteKing.check){

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
                            blackPawn5, blackPawn6, blackPawn7, blackPawn8,  blackKing);                }
                boardWHITE.removeAdditionalOptionsForALL(rowBoard,columnBoard);



            }

        }else if(row > rowBoard && column == columnBoard)
        {
            //move up Rook
            shapeDiff = -rowBoard + row-1;
            for(int i=1; i<=shapeDiff; i++){
                if(gameBoard[row-i-1][columnBoard-1].occupiedFieldByWHITE == true || gameBoard[row-i-1][columnBoard-1].occupiedFieldByBlack == true){
                    move = false;
                }
            }
            if(gameBoard[row-2][column-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) move = false;

            if(move == true && !whiteKing.check){

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



            }else if(move == true && whiteKing.check){

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
                            blackPawn5, blackPawn6, blackPawn7, blackPawn8,  blackKing);                }
                boardWHITE.removeAdditionalOptionsForALL(rowBoard,columnBoard);



            }


        }else if(row == rowBoard && column > columnBoard)
        {
            //move left Rook
            shapeDiff = -columnBoard + column-1;
            for(int i=1; i<=shapeDiff; i++){
                if(gameBoard[rowBoard-1][column-i-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][column-i-1].occupiedFieldByBlack == true){
                    move = false;
                }
            }
            if(gameBoard[row-1][column-2].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByWHITE == true) move = false;

            if(move == true && !whiteKing.check){

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



            }else if(move == true && whiteKing.check){

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
                            blackPawn5, blackPawn6, blackPawn7, blackPawn8,  blackKing);                }
                boardWHITE.removeAdditionalOptionsForALL(rowBoard,columnBoard);



            }


        }else {
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
        boolean move = true;
        int tempVarA = row;
        int tempVarB = column;
        int varForRepairIdentity = gameBoard[row-1][column-1].identityOfSquare;
        int varForFunctionRemoveOptions = 0;

        if(rowBoard > row && columnBoard > column ) {
            //move down-right bishop
            shapeDiff = rowBoard - row-1;
            for(int i=1; i<=shapeDiff; i++){
                if(gameBoard[row+i-1][column+i-1].occupiedFieldByWHITE == true || gameBoard[row+i-1][column+i-1].occupiedFieldByBlack == true){
                    move = false;
                }
            }
            if(gameBoard[row][column].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) move = false;



            if(rowBoard - row == columnBoard - column && move == true && !blackKing.check){


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
            }else if(rowBoard - row == columnBoard - column && move == true && blackKing.check){

                row = rowBoard;
                column = columnBoard;
                clicked = false;

                boardBLACK.checkCollision();
                boardBLACK.removeOptionsForAll();
                boardBLACK.setAdditionalOptionsForBlack(rowBoard,columnBoard,row,column);
                boardBLACK.setOptionsForAll();
                //check conditions
                if(!gameBoard[blackKing.row-1][blackKing.column-1].possibleMoveWhite){
                    moveDone = true;
                    blackKing.check = false;
                }else{
                    row = tempVarA;
                    column = tempVarB;
                    clicked = false;
                    boardBLACK.removeAdditionalOptionsForALL(rowBoard,columnBoard);
                    setOptionsLikeBelowBlackFunction(rowBoard,columnBoard,gameBoard,whiteKnight1,whiteKnight2,
                            whiteBishop1,whiteBishop2,
                            whiteRook1,whiteRook2,
                            whiteQueen,
                            whitePawn1,whitePawn2,whitePawn3,whitePawn4,
                            whitePawn5,whitePawn6,whitePawn7,whitePawn8,whiteKing);                }



            }

        }else if(rowBoard < row && columnBoard > column){
            //move up-right bishop

            shapeDiff = -rowBoard + row-1;
            for(int i=1; i<=shapeDiff; i++){
                if(gameBoard[row-i-1][column+i-1].occupiedFieldByWHITE == true || gameBoard[row-i-1][column+i-1].occupiedFieldByBlack == true){
                    move = false;
                }
            }
            if(gameBoard[row-2][column].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) move = false;



            if(-rowBoard + row == columnBoard - column && move == true && !blackKing.check){


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
            }else if(-rowBoard + row == columnBoard - column && move == true && blackKing.check){

                row = rowBoard;
                column = columnBoard;
                clicked = false;

                boardBLACK.checkCollision();
                boardBLACK.removeOptionsForAll();
                boardBLACK.setAdditionalOptionsForBlack(rowBoard,columnBoard,row,column);
                boardBLACK.setOptionsForAll();
                //check conditions
                if(!gameBoard[blackKing.row-1][blackKing.column-1].possibleMoveWhite){
                    moveDone = true;
                    blackKing.check = false;
                }else{
                    row = tempVarA;
                    column = tempVarB;
                    clicked = false;
                    boardBLACK.removeAdditionalOptionsForALL(rowBoard,columnBoard);
                    setOptionsLikeBelowBlackFunction(rowBoard,columnBoard,gameBoard,whiteKnight1,whiteKnight2,
                            whiteBishop1,whiteBishop2,
                            whiteRook1,whiteRook2,
                            whiteQueen,
                            whitePawn1,whitePawn2,whitePawn3,whitePawn4,
                            whitePawn5,whitePawn6,whitePawn7,whitePawn8,whiteKing);                }



            }


        }else if(rowBoard < row && columnBoard < column){
            //move up-left bishop

            shapeDiff = -rowBoard + row-1;
            for(int i=1; i<=shapeDiff; i++){
                if(gameBoard[row-i-1][column-i-1].occupiedFieldByWHITE == true || gameBoard[row-i-1][column-i-1].occupiedFieldByBlack == true){
                    move = false;
                }
            }
            if(gameBoard[row-2][column-2].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) move = false;



            if(-rowBoard + row == -columnBoard + column && move == true && !blackKing.check){


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
            }else if(-rowBoard + row == -columnBoard + column && move == true && blackKing.check){

                row = rowBoard;
                column = columnBoard;
                clicked = false;

                boardBLACK.checkCollision();
                boardBLACK.removeOptionsForAll();
                boardBLACK.setAdditionalOptionsForBlack(rowBoard,columnBoard,row,column);
                boardBLACK.setOptionsForAll();
                //check conditions
                if(!gameBoard[blackKing.row-1][blackKing.column-1].possibleMoveWhite){
                    moveDone = true;
                    blackKing.check = false;
                }else{
                    row = tempVarA;
                    column = tempVarB;
                    clicked = false;
                    boardBLACK.removeAdditionalOptionsForALL(rowBoard,columnBoard);
                    setOptionsLikeBelowBlackFunction(rowBoard,columnBoard,gameBoard,whiteKnight1,whiteKnight2,
                            whiteBishop1,whiteBishop2,
                            whiteRook1,whiteRook2,
                            whiteQueen,
                            whitePawn1,whitePawn2,whitePawn3,whitePawn4,
                            whitePawn5,whitePawn6,whitePawn7,whitePawn8,whiteKing);                }



            }


        }else if(rowBoard > row && columnBoard < column){
            //move down-left bishop

            shapeDiff = rowBoard - row-1;
            for(int i=1; i<=shapeDiff; i++){
                if(gameBoard[row+i-1][column-i-1].occupiedFieldByWHITE == true || gameBoard[row+i-1][column-i-1].occupiedFieldByBlack == true){
                    move = false;
                }
            }
            if(gameBoard[row][column-2].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) move = false;



            if(rowBoard - row == -columnBoard + column && move == true && !blackKing.check){


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
            }else if(rowBoard - row == -columnBoard + column && move == true && blackKing.check){

                row = rowBoard;
                column = columnBoard;
                clicked = false;

                boardBLACK.checkCollision();
                boardBLACK.removeOptionsForAll();
                boardBLACK.setAdditionalOptionsForBlack(rowBoard,columnBoard,row,column);
                boardBLACK.setOptionsForAll();
                //check conditions
                if(!gameBoard[blackKing.row-1][blackKing.column-1].possibleMoveWhite){
                    moveDone = true;
                    blackKing.check = false;
                }else{
                    row = tempVarA;
                    column = tempVarB;
                    clicked = false;
                    boardBLACK.removeAdditionalOptionsForALL(rowBoard,columnBoard);
                    setOptionsLikeBelowBlackFunction(rowBoard,columnBoard,gameBoard,whiteKnight1,whiteKnight2,
                            whiteBishop1,whiteBishop2,
                            whiteRook1,whiteRook2,
                            whiteQueen,
                            whitePawn1,whitePawn2,whitePawn3,whitePawn4,
                            whitePawn5,whitePawn6,whitePawn7,whitePawn8,whiteKing);                }



            }

        }else if(row < rowBoard && column == columnBoard)
        {
            //move down Rook
            shapeDiff = rowBoard - row-1;
            for(int i=1; i<=shapeDiff; i++){
                if(gameBoard[row+i-1][columnBoard-1].occupiedFieldByWHITE == true || gameBoard[row+i-1][columnBoard-1].occupiedFieldByBlack == true){
                    move = false;
                }
            }
            if(gameBoard[row][column-1].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) move = false;

            if(move == true && !blackKing.check){


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
            }else if(move == true && blackKing.check){

                row = rowBoard;
                column = columnBoard;
                clicked = false;

                boardBLACK.checkCollision();
                boardBLACK.removeOptionsForAll();
                boardBLACK.setAdditionalOptionsForBlack(rowBoard,columnBoard,row,column);
                boardBLACK.setOptionsForAll();
                //check conditions
                if(!gameBoard[blackKing.row-1][blackKing.column-1].possibleMoveWhite){
                    moveDone = true;
                    blackKing.check = false;
                }else{
                    row = tempVarA;
                    column = tempVarB;
                    clicked = false;
                    boardBLACK.removeAdditionalOptionsForALL(rowBoard,columnBoard);
                    setOptionsLikeBelowBlackFunction(rowBoard,columnBoard,gameBoard,whiteKnight1,whiteKnight2,
                            whiteBishop1,whiteBishop2,
                            whiteRook1,whiteRook2,
                            whiteQueen,
                            whitePawn1,whitePawn2,whitePawn3,whitePawn4,
                            whitePawn5,whitePawn6,whitePawn7,whitePawn8,whiteKing);                }



            }



        }else if(row == rowBoard && column < columnBoard)
        {
            //move right Rook
            shapeDiff = columnBoard - column-1;
            for(int i=1; i<=shapeDiff; i++){
                if(gameBoard[rowBoard-1][column+i-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][column+i-1].occupiedFieldByBlack == true){
                    move = false;
                }
            }
            if(gameBoard[row-1][column].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) move = false;

            if(move == true && !blackKing.check){


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
            }else if(move == true && blackKing.check){

                row = rowBoard;
                column = columnBoard;
                clicked = false;

                boardBLACK.checkCollision();
                boardBLACK.removeOptionsForAll();
                boardBLACK.setAdditionalOptionsForBlack(rowBoard,columnBoard,row,column);
                boardBLACK.setOptionsForAll();
                //check conditions
                if(!gameBoard[blackKing.row-1][blackKing.column-1].possibleMoveWhite){
                    moveDone = true;
                    blackKing.check = false;
                }else{
                    row = tempVarA;
                    column = tempVarB;
                    clicked = false;
                    boardBLACK.removeAdditionalOptionsForALL(rowBoard,columnBoard);
                    setOptionsLikeBelowBlackFunction(rowBoard,columnBoard,gameBoard,whiteKnight1,whiteKnight2,
                            whiteBishop1,whiteBishop2,
                            whiteRook1,whiteRook2,
                            whiteQueen,
                            whitePawn1,whitePawn2,whitePawn3,whitePawn4,
                            whitePawn5,whitePawn6,whitePawn7,whitePawn8,whiteKing);                }



            }


        }else if(row > rowBoard && column == columnBoard)
        {
            //move up Rook
            shapeDiff = -rowBoard + row-1;
            for(int i=1; i<=shapeDiff; i++){
                if(gameBoard[row-i-1][columnBoard-1].occupiedFieldByWHITE == true || gameBoard[row-i-1][columnBoard-1].occupiedFieldByBlack == true){
                    move = false;
                }
            }
            if(gameBoard[row-2][column-1].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) move = false;

            if(move == true && !blackKing.check){


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
            }else if(move == true && blackKing.check){

                row = rowBoard;
                column = columnBoard;
                clicked = false;

                boardBLACK.checkCollision();
                boardBLACK.removeOptionsForAll();
                boardBLACK.setAdditionalOptionsForBlack(rowBoard,columnBoard,row,column);
                boardBLACK.setOptionsForAll();
                //check conditions
                if(!gameBoard[blackKing.row-1][blackKing.column-1].possibleMoveWhite){
                    moveDone = true;
                    blackKing.check = false;
                }else{
                    row = tempVarA;
                    column = tempVarB;
                    clicked = false;
                    boardBLACK.removeAdditionalOptionsForALL(rowBoard,columnBoard);
                    setOptionsLikeBelowBlackFunction(rowBoard,columnBoard,gameBoard,whiteKnight1,whiteKnight2,
                            whiteBishop1,whiteBishop2,
                            whiteRook1,whiteRook2,
                            whiteQueen,
                            whitePawn1,whitePawn2,whitePawn3,whitePawn4,
                            whitePawn5,whitePawn6,whitePawn7,whitePawn8,whiteKing);                }



            }


        }else if(row == rowBoard && column > columnBoard)
        {
            //move left Rook
            shapeDiff = -columnBoard + column-1;
            for(int i=1; i<=shapeDiff; i++){
                if(gameBoard[rowBoard-1][column-i-1].occupiedFieldByWHITE == true || gameBoard[rowBoard-1][column-i-1].occupiedFieldByBlack == true){
                    move = false;
                }
            }
            if(gameBoard[row-1][column-2].occupiedFieldByBlack == true || gameBoard[rowBoard-1][columnBoard-1].occupiedFieldByBlack == true) move = false;

            if(move == true && !blackKing.check){


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
            }else if(move == true && blackKing.check){

                row = rowBoard;
                column = columnBoard;
                clicked = false;

                boardBLACK.checkCollision();
                boardBLACK.removeOptionsForAll();
                boardBLACK.setAdditionalOptionsForBlack(rowBoard,columnBoard,row,column);
                boardBLACK.setOptionsForAll();
                //check conditions
                if(!gameBoard[blackKing.row-1][blackKing.column-1].possibleMoveWhite){
                    moveDone = true;
                    blackKing.check = false;
                }else{
                    row = tempVarA;
                    column = tempVarB;
                    clicked = false;
                    boardBLACK.removeAdditionalOptionsForALL(rowBoard,columnBoard);
                    setOptionsLikeBelowBlackFunction(rowBoard,columnBoard,gameBoard,whiteKnight1,whiteKnight2,
                            whiteBishop1,whiteBishop2,
                            whiteRook1,whiteRook2,
                            whiteQueen,
                            whitePawn1,whitePawn2,whitePawn3,whitePawn4,
                            whitePawn5,whitePawn6,whitePawn7,whitePawn8,whiteKing);                }



            }


        }else{
            moveDone = false;
        }

        return moveDone;
    }

}
