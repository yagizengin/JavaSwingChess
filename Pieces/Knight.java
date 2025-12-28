package Pieces;

public class Knight extends Piece {

    public Knight(int col, int row, boolean isWhite) {
        super(col, row, isWhite, "Knight");

        this.image = getImage("Assets/" + (isWhite ? "White" : "Black") + "/knight.png");
    }

}
