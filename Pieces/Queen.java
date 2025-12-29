package Pieces;

import java.util.List;

public class Queen extends Piece {

    public Queen(int col, int row, PieceColor color) {
        super(col, row, color, "Queen");
    }

    public boolean isLegalMove(int col, int row) {
        return Math.abs(col - this.col) == Math.abs(row - this.row) || col == this.col || row == this.row;
    }

    public List<int[]> getLegalMoves() {
        return null;
    }
}