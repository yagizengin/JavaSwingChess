package Pieces;

import java.util.List;

import Game.Position;

public class Knight extends Piece {

    public Knight(Position position, PieceColor color) {
        super(position, color, "Knight");
    }

    public boolean isLegalMove(Position position) {
        int targetCol = position.getCol();
        int targetRow = position.getRow();
        int col = this.getCol();
        int row = this.getRow();

        return Math.abs(targetCol - col) == 2 && Math.abs(targetRow - row) == 1
                || Math.abs(targetCol - col) == 1 && Math.abs(targetRow - row) == 2;
    }

    public List<int[]> getLegalMoves() {
        return null;
    }

}