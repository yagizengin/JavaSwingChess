package Pieces;

public class King extends Piece {

    public King(int col, int row, boolean isWhite) {
        super(col, row, isWhite, "King");

        this.image = getImage("Assets/" + (isWhite ? "White" : "Black") + "/king.png");
    }

}
