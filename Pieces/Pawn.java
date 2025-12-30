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
                return targetRow == this.position.getRow() + 1
                        || (targetRow == this.position.getRow() + (moveCount > 0 ? 1 : 2));
            return Math.abs(targetCol - col) <= 1 && targetRow == row + 1;
        } else {
            if (targetCol == col)
                return targetRow == row - 1 || (targetRow == row - (moveCount > 0 ? 1 : 2));
            return Math.abs(targetCol - col) <= 1 && targetRow == row - 1;
        }
    }

    public boolean isLegalMove(Position position, Piece targetPiece) {
        if (this.getCol() == position.getCol()) {
            return targetPiece == null && this.isLegalMove(position);
        }
        return targetPiece != null && this.isLegalMove(position);
    }

    public boolean promotion(Position position) {
        if (color == PieceColor.WHITE) {
            return position.getRow() == 7;
        } else {
            return position.getRow() == 0;
        }
    }

    // TODO
    public boolean enPassant(Position position, Piece targetPiece) {
        return false;
    }

    public List<int[]> getLegalMoves() {
        return null;
    }
}