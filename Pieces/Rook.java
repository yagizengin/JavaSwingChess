package Pieces;

import Game.Position;

public class Rook extends Piece {

    public Rook(Position position, PieceColor color) {
        super(position, color, "Rook");
    }

    public boolean isLegalMove(Position position) {
        int targetCol = position.getCol();
        int targetRow = position.getRow();
        int col = this.getCol();
        int row = this.getRow();
        return col == targetCol || row == targetRow;
    }

}