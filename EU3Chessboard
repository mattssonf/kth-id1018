/**
* Finish the incomplete program
* with Chessborad and Chesspieces
*/
public class Chessboard {

    public static class Field{

        private char    row;
        private byte    column;
        private Chesspiece  piece = null;
        private boolean marked = false;

        // Defines the object Field
        public Field (char row, byte column){
            this.row = row;
            this.column = column;
            this.piece = piece;
        }
        //Puts a piece on the chessboard
        public void put (Chesspiece piece){
            this.piece = piece;
        }

        //Takes a piece off the chessboard, referred to by another variable
        public Chesspiece take (){
            Chesspiece takenPiece = piece;
            piece = null;
            return takenPiece;
        }
        // Marks field as occupied
        public void mark (){
            marked = true;
        }

        // Unmarks, field is not occupied
        public void unmark (){
            marked = false;
        }

        public String toString (){
            String s = (marked)? "xx" : "--";
            return (piece == null)? s : piece.toString();
        }
    }

    public static final int NUMBER_OF_ROWS = 8;
    public static final int NUMBER_OF_COLUMNS = 8;

    public static final int FIRST_ROW = 'a';
    public static final int FIRST_COLUMN = 1;

    private Field[][] fields;

    public Chessboard(){
        fields = new Field[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
        char    row = 0;
        byte    column = 0;
        for(int r = 0; r < NUMBER_OF_ROWS; r++){
            row = (char) (FIRST_ROW + r);
            column = FIRST_COLUMN;
            for(int c = 0; c < NUMBER_OF_COLUMNS; c++){
                fields[r][c] = new Field (row, column);
                column++;
            }
        }
    }
    //OBS double check
    public String toString (){
        String s = "";
        for(int r = 0; r < NUMBER_OF_ROWS; r++){
            for(int c = 0; c < NUMBER_OF_COLUMNS; c++){
                s += fields[r][c].toString() + " ";
            }
            s += "\n";
        }
        return s;
    }

    public boolean isValidField (char row, byte column){

        return row >= 'a'&& row <= 'h' && column > 0 && column <= NUMBER_OF_COLUMNS;
    }

    public abstract class Chesspiece{

        private char color;
        //w - white, b - black

        private char name;
        //K - King, Q - Queen, R - Rook, B - Bishop, N - Knight,
        //P - Pawn

        protected char  row = 0;
        protected byte  column = -1;

        protected Chesspiece (char color, char name){
            this.color = color;
            this.name = name;
        }
        public String toString(){
            return "" + color + name;
        }
        public boolean isOnBoard(){
            return Chessboard.this.isValidField(row, column);
        }

        public void moveTo(char row, byte column)
            throws NotValidFieldException{
            if(!Chessboard.this.isValidField(row, column))
                throw new NotValidFieldException("bad field: " + row + column);

            this.row = row;
            this.column = column;

            int r = row - FIRST_ROW;
            int c = column - FIRST_COLUMN;
            Chessboard.this.fields[r][c].put(this);
        }
        //put it outside of the board or not?
        public void moveOut(){
            if(this.isOnBoard()){

                int r = row - FIRST_ROW;
                int c = column - FIRST_COLUMN;
                Chessboard.this.fields[r][c].take();
            }
        }

        public abstract void markReachableFields();

        public abstract void unmarkReachableFields();
    }
    public class Pawn extends Chesspiece{
        public Pawn(char color, char name){
            super(color, name);
        }
        public void markReachableFields(){
            byte col = (byte) (column + 1);
            if(Chessboard.this.isValidField(row, col)){
                int r = row - FIRST_ROW;
                int c = col - FIRST_COLUMN;
                Chessboard.this.fields[r][c].mark();
            }
        }
        public void unmarkReachableFields(){
            byte col = (byte) (column + 1);
            if(Chessboard.this.isValidField(row, col)){
                int r = row - FIRST_ROW;
                int c = col - FIRST_COLUMN;
                Chessboard.this.fields[r][c].unmark();
            }
        }
    }
    public class Rook extends Chesspiece {
        public Rook(char color, char name){
            super(color, name);
        }

        public void markReachableFields() {
            if (Chessboard.this.isValidField(this.row, this.column)) {
                for (int r = 0; r < NUMBER_OF_ROWS; r++) {
                    Chessboard.this.fields[r][this.column - FIRST_COLUMN].mark();
                }
                for (int c = 0; c < NUMBER_OF_COLUMNS; c++)
                    Chessboard.this.fields[this.row - FIRST_ROW][c].mark();
            }
        }
        public void unmarkReachableFields(){
            if (Chessboard.this.isValidField(this.row, this.column)){
                for(int r = 0; r < NUMBER_OF_ROWS; r++)
                    Chessboard.this.fields[r][this.column - FIRST_COLUMN].unmark();
                for(int c = 0; c < NUMBER_OF_COLUMNS; c++)
                    Chessboard.this.fields[this.row - FIRST_ROW][c].unmark();
            }
        }
    }

    public class Knight extends Chesspiece {
        public Knight(char color, char name){
            super(color, name);
        }
        int[] change = {-2, -1, 1, 2};
        public void markReachableFields(){
            for(int colChange:change){
                for(int rowChange:change){
                    char rw = (char) (this.row + rowChange);
                    byte col = (byte) (this.column + colChange);
                    if(Chessboard.this.isValidField(rw, col) && (Math.abs(colChange) + Math.abs(rowChange) == 3))
                        Chessboard.this.fields[rw - FIRST_ROW][col - FIRST_COLUMN].mark();
                }
            }
        }
        public void unmarkReachableFields(){
            for(int colChange:change){
                for(int rowChange:change){
                    char rw = (char) (this.row + rowChange);
                    byte col = (byte) (this.column + colChange);
                    if(Chessboard.this.isValidField(rw, col) && (Math.abs(colChange) + Math.abs(rowChange)==3))
                        Chessboard.this.fields[rw - FIRST_ROW][col - FIRST_COLUMN].unmark();
                }
            }
        }
    }

    public class Bishop extends Chesspiece {
        public Bishop(char color, char name){
            super(color, name);
        }
        public void markReachableFields(){
            int[] change = {-1, 1};
            for(int colChange:change) {
                for(int rowChange:change) {
                    char rw = (char) (this.row + rowChange);
                    byte col = (byte) (this.column + colChange);

                    while(Chessboard.this.isValidField(rw, col)) {
                        Chessboard.this.fields[rw - FIRST_ROW][col - FIRST_COLUMN].mark();
                        rw += rowChange;
                        col += colChange;
                    }
                }
            }
        }
        public void unmarkReachableFields(){
            int[] change = {-1, 1};
            for(int colChange:change) {
                for(int rowChange:change) {
                    char rw = (char) (this.row + rowChange);
                    byte col = (byte) (this.column + colChange);

                    while(Chessboard.this.isValidField(rw, col)) {
                        Chessboard.this.fields[rw - FIRST_ROW][col - FIRST_COLUMN].unmark();
                        rw += rowChange;
                        col += colChange;
                    }
                }
            }
        }
    }

    public class Queen extends Chesspiece {
        public Queen(char color, char name){
            super(color, name);
        }
        char rw = (char) (row);
        byte col = (byte) (column);
        public void markReachableFields(){
            if (Chessboard.this.isValidField(this.row, this.column)) {
                for (int r = 0; r < NUMBER_OF_ROWS; r++) {
                    Chessboard.this.fields[r][this.column - FIRST_COLUMN].mark();
                }
                for (int c = 0; c < NUMBER_OF_COLUMNS; c++)
                    Chessboard.this.fields[this.row - FIRST_ROW][c].mark();
            }
            int[] change = {-1, 1};
            for(int colChange:change) {
                for(int rowChange:change) {
                    char rw = (char) (this.row + rowChange);
                    byte col = (byte) (this.column + colChange);

                    while(Chessboard.this.isValidField(rw, col)) {
                        Chessboard.this.fields[rw - FIRST_ROW][col - FIRST_COLUMN].mark();
                        rw += rowChange;
                        col += colChange;
                    }
                }
            }
            }
        public void unmarkReachableFields(){
            if (Chessboard.this.isValidField(this.row, this.column)) {
                for (int r = 0; r < NUMBER_OF_ROWS; r++) {
                    Chessboard.this.fields[r][this.column - FIRST_COLUMN].unmark();
                }
                for (int c = 0; c < NUMBER_OF_COLUMNS; c++)
                    Chessboard.this.fields[this.row - FIRST_ROW][c].unmark();
            }
            int[] change = {-1, 1};
            for(int colChange:change) {
                for(int rowChange:change) {
                    char rw = (char) (this.row + rowChange);
                    byte col = (byte) (this.column + colChange);

                    while(Chessboard.this.isValidField(rw, col)) {
                        Chessboard.this.fields[rw - FIRST_ROW][col - FIRST_COLUMN].unmark();
                        rw += rowChange;
                        col += colChange;
                    }
                }
            }
        }
    }

    public class King extends Chesspiece {
        public King(char color, char name){
            super(color, name);
        }
        int[] change = {-1, 0, 1};
        byte col = (byte) (this.column);
        char rw = (char) (this.row);
        public void markReachableFields(){
            for(int rowChange:change) {
                for (int colChange : change) {
                    col = (byte) (this.column + colChange);
                    rw = (char) (this.row + rowChange);
                    if (Chessboard.this.isValidField(rw, col))
                        Chessboard.this.fields[rw - FIRST_ROW][col - FIRST_COLUMN].mark();
                }
            }
        }
        public void unmarkReachableFields(){
            for(int rowChange:change) {
                for (int colChange : change) {
                    col = (byte) (this.column + colChange);
                    rw = (char) (this.row + rowChange);
                    if (Chessboard.this.isValidField(rw, col))
                        Chessboard.this.fields[rw - FIRST_ROW][col - FIRST_COLUMN].unmark();
                }
            }
        }
    }
}

//Create a custom exception
public class NotValidFieldException extends RuntimeException{

    public NotValidFieldException(){}

    public NotValidFieldException(String msg){
        super(msg);
    }
}

// Finish the program that presents the board and pieces
import java.util.Random;
public class ReachableFieldsOnChessboard {
    public static void main(String[] args) {
        Random rand = new Random();
        Chessboard chessBoard = new Chessboard();
        System.out.println(chessBoard + "\n");

        Chessboard.Chesspiece []   pieces = new  Chessboard.Chesspiece [6];
        pieces [0] = chessBoard.new  Pawn ('w', 'P');
        pieces [1] = chessBoard.new  Rook ('b', 'R');
        pieces [2] = chessBoard.new  Queen ('w', 'Q');
        pieces [3] = chessBoard.new  Bishop ('w', 'B');
        pieces [4] = chessBoard.new  King ('b', 'K');
        pieces [5] = chessBoard.new  Knight ('w', 'N');

        for(int i = 0; i < pieces.length; i++) {
            int row = rand.nextInt(8);
            int firstRow = 'a';
            char charRow = (char) (firstRow + row);
            int col = rand.nextInt(8) + 1;
            byte byteCol = (byte) (col);

            pieces[i].moveTo(charRow, byteCol);
            pieces[i].markReachableFields();
            System.out.println("Piece " + (i + 1) + ":\n" + chessBoard);
            pieces[i].unmarkReachableFields();
            pieces[i].moveOut();
            try{
                Thread.sleep(500);
            }
            catch (InterruptedException e){}
        }
    }
}
