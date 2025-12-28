package Pieces;

public class Bishop extends Piece {

    public Bishop(int col, int row, boolean isWhite) {
        super(col, row, isWhite, "Bishop");

        this.image = getImage("Assets/" + (isWhite ? "White" : "Black") + "/bishop.png");
    }

}
