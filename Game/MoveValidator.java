package Game;

import Pieces.Piece;

public class MoveValidator {
    private GameController gameController;

    public MoveValidator(GameController gameController) {
        this.gameController = gameController;
    }

    public boolean isPathClear(Piece piece, Position target) {
        int col = piece.getCol(), row = piece.getRow();
        int targetCol = target.getCol(), targetRow = target.getRow();
        if (col == targetCol && row == targetRow)
            return false;

        Piece targetPiece = gameController.getPiece(targetCol, targetRow);
        if (targetPiece != null && targetPiece.getColor() == piece.getColor())
            return false;

        if (col == targetCol)
            return isClearVertical(col, row, targetRow);
        if (row == targetRow)
            return isClearHorizontal(row, col, targetCol);
        return isClearDiagonal(col, row, targetCol, targetRow);
    }

    public boolean isClearVertical(int col, int fromRow, int toRow) {
        int direction = toRow > fromRow ? 1 : -1;
        for (int r = fromRow + direction; r != toRow; r += direction) {
            if (gameController.getPiece(col, r) != null)
                return false;
        }
        return true;
    }

    public boolean isClearHorizontal(int row, int fromCol, int toCol) {
        int direction = toCol > fromCol ? 1 : -1;
        for (int c = fromCol + direction; c != toCol; c += direction) {
            if (gameController.getPiece(c, row) != null)
                return false;
        }
        return true;
    }

    public boolean isClearDiagonal(int fromCol, int fromRow, int toCol, int toRow) {
        int colDir = toCol > fromCol ? 1 : -1;
        int rowDir = toRow > fromRow ? 1 : -1;

        for (int c = fromCol + colDir, r = fromRow + rowDir; c != toCol && r != toRow; c += colDir, r += rowDir) {
            if (gameController.getPiece(c, r) != null)
                return false;
        }
        return true;
    }
}
