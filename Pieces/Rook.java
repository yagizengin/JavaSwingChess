package Pieces;

public class Rook extends Piece {

    public Rook(int col, int row, boolean isWhite) {
        super(col, row, isWhite, "Rook");

        this.image = getImage("Assets/" + (isWhite ? "White" : "Black") + "/rook.png");
    }

}
