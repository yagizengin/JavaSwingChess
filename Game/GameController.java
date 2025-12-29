package Game;

import java.util.ArrayList;
import java.util.logging.Logger;

import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Piece.PieceColor;
import Pieces.Queen;
import Pieces.Rook;

public class GameController {
    Logger logger = Logger.getLogger(GameController.class.getName());
    private ArrayList<Piece> pieces;
    private Piece selectedPiece;
    private PieceColor currentColor;
    private MoveValidator moveValidator;

    public GameController() {
        pieces = new ArrayList<>();
        selectedPiece = null;
        currentColor = PieceColor.WHITE;
        moveValidator = new MoveValidator(this);
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public void removePiece(Piece piece) {
        pieces.remove(piece);
    }

    public void initPieces() {
        pieces.add(new Pawn(0, 1, PieceColor.WHITE));
        pieces.add(new Pawn(1, 1, PieceColor.WHITE));
        pieces.add(new Pawn(2, 1, PieceColor.WHITE));
        pieces.add(new Pawn(3, 1, PieceColor.WHITE));
        pieces.add(new Pawn(4, 1, PieceColor.WHITE));
        pieces.add(new Pawn(5, 1, PieceColor.WHITE));
        pieces.add(new Pawn(6, 1, PieceColor.WHITE));
        pieces.add(new Pawn(7, 1, PieceColor.WHITE));

        pieces.add(new Pawn(0, 6, PieceColor.BLACK));
        pieces.add(new Pawn(1, 6, PieceColor.BLACK));
        pieces.add(new Pawn(2, 6, PieceColor.BLACK));
        pieces.add(new Pawn(3, 6, PieceColor.BLACK));
        pieces.add(new Pawn(4, 6, PieceColor.BLACK));
        pieces.add(new Pawn(5, 6, PieceColor.BLACK));
        pieces.add(new Pawn(6, 6, PieceColor.BLACK));
        pieces.add(new Pawn(7, 6, PieceColor.BLACK));

        pieces.add(new Rook(0, 0, PieceColor.WHITE));
        pieces.add(new Rook(7, 0, PieceColor.WHITE));
        pieces.add(new Rook(0, 7, PieceColor.BLACK));
        pieces.add(new Rook(7, 7, PieceColor.BLACK));

        pieces.add(new Knight(1, 0, PieceColor.WHITE));
        pieces.add(new Knight(6, 0, PieceColor.WHITE));
        pieces.add(new Knight(1, 7, PieceColor.BLACK));
        pieces.add(new Knight(6, 7, PieceColor.BLACK));

        pieces.add(new Bishop(2, 0, PieceColor.WHITE));
        pieces.add(new Bishop(5, 0, PieceColor.WHITE));
        pieces.add(new Bishop(2, 7, PieceColor.BLACK));
        pieces.add(new Bishop(5, 7, PieceColor.BLACK));

        pieces.add(new Queen(3, 0, PieceColor.WHITE));
        pieces.add(new Queen(3, 7, PieceColor.BLACK));

        pieces.add(new King(4, 0, PieceColor.WHITE));
        pieces.add(new King(4, 7, PieceColor.BLACK));
    }

    public boolean isSelectedPiece() {
        return selectedPiece != null;
    }

    public void selectPiece(int col, int row) {
        selectedPiece = pieces.stream().filter(
                p -> p.getCol() == col &&
                        p.getRow() == row && p.getColor() == currentColor)
                .findFirst().orElse(null);
        if (selectedPiece != null)
            logger.info("selected piece: " + selectedPiece.getName());
    }

    public boolean movePiece(int col, int row) {
        if (selectedPiece != null) {
            if (!moveValidator.isLegalMove(selectedPiece, new Position(col, row))) {
                logger.info("illegal move");
                return false;
            }

            Piece targetPiece = getPiece(col, row);
            if (targetPiece != null) {
                logger.info("captured piece" + targetPiece.getName());
                removePiece(targetPiece);
            }

            selectedPiece.setCol(col);
            selectedPiece.setRow(row);
            selectedPiece.setMoved(true);
            selectedPiece = null;
            currentColor = currentColor == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;
            logger.info("moved piece");
            return true;
        }
        logger.info("no selected piece");
        return false;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public Piece getPiece(int col, int row) {
        return pieces.stream().filter(
                p -> p.getCol() == col &&
                        p.getRow() == row)
                .findFirst().orElse(null);
    }

    public MoveValidator getMoveValidator() {
        return moveValidator;
    }

}
