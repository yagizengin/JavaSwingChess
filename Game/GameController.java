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
        pieces.add(new Pawn(new Position(0, 1), PieceColor.WHITE));
        pieces.add(new Pawn(new Position(1, 1), PieceColor.WHITE));
        pieces.add(new Pawn(new Position(2, 1), PieceColor.WHITE));
        pieces.add(new Pawn(new Position(3, 1), PieceColor.WHITE));
        pieces.add(new Pawn(new Position(4, 1), PieceColor.WHITE));
        pieces.add(new Pawn(new Position(5, 1), PieceColor.WHITE));
        pieces.add(new Pawn(new Position(6, 1), PieceColor.WHITE));
        pieces.add(new Pawn(new Position(7, 1), PieceColor.WHITE));

        pieces.add(new Pawn(new Position(0, 6), PieceColor.BLACK));
        pieces.add(new Pawn(new Position(1, 6), PieceColor.BLACK));
        pieces.add(new Pawn(new Position(2, 6), PieceColor.BLACK));
        pieces.add(new Pawn(new Position(3, 6), PieceColor.BLACK));
        pieces.add(new Pawn(new Position(4, 6), PieceColor.BLACK));
        pieces.add(new Pawn(new Position(5, 6), PieceColor.BLACK));
        pieces.add(new Pawn(new Position(6, 6), PieceColor.BLACK));
        pieces.add(new Pawn(new Position(7, 6), PieceColor.BLACK));

        pieces.add(new Rook(new Position(0, 0), PieceColor.WHITE));
        pieces.add(new Rook(new Position(7, 0), PieceColor.WHITE));
        pieces.add(new Rook(new Position(0, 7), PieceColor.BLACK));
        pieces.add(new Rook(new Position(7, 7), PieceColor.BLACK));

        pieces.add(new Knight(new Position(1, 0), PieceColor.WHITE));
        pieces.add(new Knight(new Position(6, 0), PieceColor.WHITE));
        pieces.add(new Knight(new Position(1, 7), PieceColor.BLACK));
        pieces.add(new Knight(new Position(6, 7), PieceColor.BLACK));

        pieces.add(new Bishop(new Position(2, 0), PieceColor.WHITE));
        pieces.add(new Bishop(new Position(5, 0), PieceColor.WHITE));
        pieces.add(new Bishop(new Position(2, 7), PieceColor.BLACK));
        pieces.add(new Bishop(new Position(5, 7), PieceColor.BLACK));

        pieces.add(new Queen(new Position(3, 0), PieceColor.WHITE));
        pieces.add(new Queen(new Position(3, 7), PieceColor.BLACK));
        pieces.add(new King(new Position(4, 0), PieceColor.WHITE));
        pieces.add(new King(new Position(4, 7), PieceColor.BLACK));
    }

    public boolean isSelectedPiece() {
        return selectedPiece != null;
    }

    public void selectPiece(Position position) {
        selectedPiece = pieces.stream().filter(
                p -> p.getPosition().equals(position) && p.getColor() == currentColor)
                .findFirst().orElse(null);
        if (selectedPiece != null)
            logger.info("selected piece: " + selectedPiece.getName());
    }

    public boolean movePiece(Position position) {
        if (selectedPiece != null) {
            if (!moveValidator.isLegalMove(selectedPiece, position)) {
                logger.info("illegal move");
                return false;
            }

            Piece targetPiece = getPiece(position);
            if (targetPiece != null) {
                logger.info("captured piece" + targetPiece.getName());
                removePiece(targetPiece);
            }

            selectedPiece.setPosition(position);
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

    public Piece getPiece(Position position) {
        return pieces.stream().filter(
                p -> p.getPosition().equals(position))
                .findFirst().orElse(null);
    }

    public MoveValidator getMoveValidator() {
        return moveValidator;
    }

}
