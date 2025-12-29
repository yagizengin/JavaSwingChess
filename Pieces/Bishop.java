package Pieces;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(int col, int row, PieceColor color) {
        super(col, row, color, "Bishop");
    }

    public boolean isLegalMove(int col, int row) {
        return Math.abs(col - this.col) == Math.abs(row - this.row);
    }

    public List<int[]> getLegalMoves() {
        return null;
    }
}