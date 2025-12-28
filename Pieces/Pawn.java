package Pieces;

public class Pawn extends Piece {

    public Pawn(int col, int row, boolean isWhite) {
        super(col, row, isWhite, "Pawn");

        this.image = getImage("Assets/" + (isWhite ? "White" : "Black") + "/pawn.png");
    }
}