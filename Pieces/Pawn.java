package Pieces;

import java.util.List;

import Game.Position;

public class Pawn extends Piece {

    public Pawn(Position position, PieceColor color) {
        super(position, color, "Pawn");
    }

    public boolean isLegalMove(Position position) {
        int targetCol = position.getCol();
        int targetRow = position.getRow();
        int col = this.getCol();
        int row = this.getRow();

        if (color == PieceColor.WHITE) {
            if (targetCol == col)
                return targetRow == this.position.getRow() + 1 || (targetRow == this.position.getRow() + (moved ? 1 : 2));
            return Math.abs(targetCol - col) <= 1 && targetRow == row + 1;
        } else {
            if (targetCol == col)
                return targetRow == row - 1 || (targetRow == row - (moved ? 1 : 2));
            return Math.abs(targetCol - col) <= 1 && targetRow == row - 1;
        }
    }

    public List<int[]> getLegalMoves() {
        return null;
    }
}