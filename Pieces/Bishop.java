package Pieces;

import Game.Position;

public class Bishop extends Piece {

    public Bishop(Position position, PieceColor color) {
        super(position, color, "Bishop");
    }

    public boolean isLegalMove(Position position) {
        int targetCol = position.getCol();
        int targetRow = position.getRow();
        int col = this.getCol();
        int row = this.getRow();

        return Math.abs(targetCol - col) == Math.abs(targetRow - row);
    }

}