package Pieces;

import java.util.List;

public class Rook extends Piece {

    public Rook(int col, int row, PieceColor color) {
        super(col, row, color, "Rook");
    }

    public boolean isLegalMove(int col, int row) {
        return col == this.col || row == this.row;
    }

    public List<int[]> getLegalMoves() {
        return null;
    }
}