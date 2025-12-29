package Pieces;

import java.util.List;

import Game.Position;

public class King extends Piece {

    public King(Position position, PieceColor color) {
        super(position, color, "King");
    }

    public boolean isLegalMove(Position position) {
        int targetCol = position.getCol();
        int targetRow = position.getRow();
        int col = this.getCol();
        int row = this.getRow();

        return Math.abs(targetCol - col) <= 1 && Math.abs(targetRow - row) <= 1;
    }

    public List<int[]> getLegalMoves() {
        return null;
    }

}