package Pieces;

import Game.Position;

public class Queen extends Piece {

    public Queen(Position position, PieceColor color) {
        super(position, color, "Queen");
    }

    public boolean isLegalMove(Position position) {
        int targetCol = position.getCol();
        int targetRow = position.getRow();
        int col = this.getCol();
        int row = this.getRow();
        return Math.abs(targetCol - col) == Math.abs(targetRow - row) || targetCol == col || targetRow == row;
    }

}