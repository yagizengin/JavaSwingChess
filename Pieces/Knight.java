package Pieces;

import java.util.List;

public class Knight extends Piece {

    public Knight(int col, int row, PieceColor color) {
        super(col, row, color, "Knight");
    }

    public boolean isLegalMove(int col, int row) {
        return Math.abs(col - this.col) == 2 && Math.abs(row - this.row) == 1
                || Math.abs(col - this.col) == 1 && Math.abs(row - this.row) == 2;
    }

    public List<int[]> getLegalMoves() {
        return null;
    }

}