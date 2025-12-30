package Game;

import java.util.ArrayList;
import java.util.List;

import Pieces.King;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Piece.PieceColor;

public class MoveValidator {
    private GameController gameController;

    public MoveValidator(GameController gameController) {
        this.gameController = gameController;
    }

    public List<Position> getLegalMoves(Piece piece) {
        List<Position> legalMoves = new ArrayList<>();
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                Position target = new Position(col, row);
                if (isLegalMove(piece, target)) {
                    legalMoves.add(target);
                }
            }
        }
        return legalMoves;
    }

    public boolean isLegalMove(Piece piece, Position target) {
        if (piece.getName() == "Pawn") {
            return ((Pawn) piece).isLegalMove(target, gameController.getPiece(target))
                    && isPathClear(piece, target) && !wouldLeaveKingInCheck(piece, target);
        }
        if (piece.getName() == "King") {
            if (isSquareAttacked(target, piece.getColor() == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE))
                return false;
        }

        return piece.isLegalMove(target) && isPathClear(piece, target) && !wouldLeaveKingInCheck(piece, target);
    }

    public boolean isCapturingPiece(Piece piece, Position target) {
        Piece targetPiece = gameController.getPiece(target);
        return targetPiece != null && targetPiece.getColor() != piece.getColor();
    }

    public boolean isPathClear(Piece piece, Position target) {
        if (piece.getPosition().equals(target))
            return false;

        Piece targetPiece = gameController.getPiece(target);

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

    public boolean isSquareAttacked(Position target, PieceColor color) {
        for (Piece p : gameController.getPieces()) {
            if (p.getColor().equals(color) && canPieceAttack(p, target)) {
                return true;
            }
        }
        return false;
    }

    public boolean canPieceAttack(Piece piece, Position target) {
        if (piece.getName() == "Pawn") {
            if (piece.getCol() == target.getCol()) {
                return false;
            }
            return ((Pawn) piece).isLegalMove(target)
                    && isPathClear(piece, target);
        }
        return piece.isLegalMove(target) && isPathClear(piece, target);
    }

    public boolean wouldLeaveKingInCheck(Piece piece, Position target) {
        Position originalPos = piece.getPosition();
        Piece capturedPiece = gameController.getPiece(target);

        piece.setPosition(target);
        if (capturedPiece != null) {
            gameController.removePiece(capturedPiece);
        }

        boolean inCheck = isKingInCheck(piece.getColor());

        piece.setPosition(originalPos);
        if (capturedPiece != null) {
            gameController.addPiece(capturedPiece);
        }

        return inCheck;
    }

    public boolean isKingInCheck(PieceColor color) {
        King king = null;
        for (Piece p : gameController.getPieces()) {
            if (p instanceof King && p.getColor() == color) {
                king = (King) p;
                break;
            }
        }
        if (king == null) return false;
        return isSquareAttacked(king.getPosition(), color == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE);
    }

    public boolean isCheckmate(PieceColor color) {
        if(!isKingInCheck(color)) return false;

        List<Piece> pieces = new ArrayList<>(gameController.getPieces());
        for (Piece p : pieces) {
            if(p.getColor() == color) {
                List<Position> moves = getLegalMoves(p);
                if(!moves.isEmpty()) return false;
            }
        }
        return true;
    }
}
