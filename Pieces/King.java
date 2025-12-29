package Pieces;

import java.util.List;

public class King extends Piece {

    public King(int col, int row, PieceColor color) {
        super(col, row, color, "King");
    }

    public boolean isLegalMove(int col, int row) {
        return Math.abs(col - this.col) <= 1 && Math.abs(row - this.row) <= 1;
    }

    public List<int[]> getLegalMoves() {
        return null;
    }

}