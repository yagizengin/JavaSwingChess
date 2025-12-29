package Interfaces;

import java.util.List;

public interface Movable {
    boolean isLegalMove(int col, int row);

    List<int[]> getLegalMoves();
}
