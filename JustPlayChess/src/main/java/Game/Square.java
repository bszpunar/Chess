package Game;

public class Square {
    private String name;
    private int row, column;

    public String kolorPola;
    public boolean occupiedFieldByWHITE;
    public boolean occupiedFieldByBlack;

    public boolean białePole, czarnePole;
    public int identityOfSquare;

    public boolean possibleMoveWhite = false;
    public boolean possibleMoveBlack = false;
    public boolean possibleMoveWhiteKING = false;
    public boolean possibleMoveBlackKING = false;

    public Square(int row,int column,String name, boolean occupiedFieldByWHITE, boolean occupiedFieldByBlack, int identityOfSquare){
        this.row = row;
        this.column = column;
        this.name = name;
        this.occupiedFieldByWHITE = occupiedFieldByWHITE;
        this.occupiedFieldByBlack = occupiedFieldByBlack;
        this.identityOfSquare = identityOfSquare;

    }
    public void pokazNazwyPol()
    {
        System.out.print(name);
        System.out.print(kolorPola);
    }
}
