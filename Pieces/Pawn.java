package Pieces;

import java.util.List;

public class Pawn extends Piece {

    public Pawn(int col, int row, PieceColor color) {
        super(col, row, color, "Pawn");
    }

    public boolean isLegalMove(int col, int row) {
        if (color == PieceColor.WHITE) {
            if (col == this.col)
                return row == this.row + 1 || (row == this.row + (moved ? 1 : 2));
            return Math.abs(col - this.col) <= 1 && row == this.row + 1;
        } else {
            if (col == this.col)
                return row == this.row - 1 || (row == this.row - (moved ? 1 : 2));
            return Math.abs(col - this.col) <= 1 && row == this.row - 1;
        }
    }

    public List<int[]> getLegalMoves() {
        return null;
    }
}