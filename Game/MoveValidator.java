package Game;

import Pieces.Piece;

public class MoveValidator {
    private GameController gameController;

    public MoveValidator(GameController gameController) {
        this.gameController = gameController;
    }

    public boolean isLegalMove(Piece piece, Position target) {
        return piece.isLegalMove(target) && isPathClear(piece, target);
    }

    public boolean isCapturingPiece(Piece piece, Position target) {
        Piece targetPiece = gameController.getPiece(target);
        return targetPiece != null && targetPiece.getColor() != piece.getColor();
    }

    public boolean isPathClear(Piece piece, Position target) {
        if (piece.getPosition().equals(target))
            return false;

        Piece targetPiece = gameController.getPiece(target);

        if (piece.getName().equals("Pawn")) {
            if (piece.getCol() == target.getCol()) {
                if (targetPiece != null)
                    return false;
                return isClearVertical(piece.getCol(), piece.getRow(), target.getRow());
            } else {
                return targetPiece != null && targetPiece.getColor() != piece.getColor();
            }
        }

        if (targetPiece != null && targetPiece.getColor() == piece.getColor())
            return false;

        if (piece.getCol() == target.getCol())
            return isClearVertical(piece.getCol(), piece.getRow(), target.getRow());
        if (piece.getRow() == target.getRow())
            return isClearHorizontal(piece.getRow(), piece.getCol(), target.getCol());
        return isClearDiagonal(piece.getCol(), piece.getRow(), target.getCol(), target.getRow());
    }

    public boolean isClearVertical(int col, int fromRow, int toRow) {
        int direction = toRow > fromRow ? 1 : -1;
        for (int r = fromRow + direction; r != toRow; r += direction) {
            if (gameController.getPiece(new Position(col, r)) != null)
                return false;
        }
        return true;
    }

    public boolean isClearHorizontal(int row, int fromCol, int toCol) {
        int direction = toCol > fromCol ? 1 : -1;
        for (int c = fromCol + direction; c != toCol; c += direction) {
            if (gameController.getPiece(new Position(c, row)) != null)
                return false;
        }
        return true;
    }

    public boolean isClearDiagonal(int fromCol, int fromRow, int toCol, int toRow) {
        int colDir = toCol > fromCol ? 1 : -1;
        int rowDir = toRow > fromRow ? 1 : -1;

        for (int c = fromCol + colDir, r = fromRow + rowDir; c != toCol && r != toRow; c += colDir, r += rowDir) {
            if (gameController.getPiece(new Position(c, r)) != null)
                return false;
        }
        return true;
    }
}
