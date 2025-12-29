package Pieces;

import java.util.List;

public class Pawn extends Piece {

    public Pawn(int col, int row, PieceColor color) {
        super(col, row, color, "Pawn");
    }

    public boolean isLegalMove(int col, int row) {
        if (color == PieceColor.WHITE) {
            return col == this.col && row == this.row + 1;
        } else {
            return col == this.col && row == this.row - 1;
        }
    }

    public List<int[]> getLegalMoves() {
        return null;
    }
}