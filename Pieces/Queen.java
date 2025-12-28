package Pieces;

public class Queen extends Piece {

    public Queen(int col, int row, boolean isWhite) {
        super(col, row, isWhite, "Queen");

        this.image = getImage("Assets/" + (isWhite ? "White" : "Black") + "/queen.png");
    }

}
