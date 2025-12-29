package Game;

public class Position{
    private int col;
    private int row;

    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public boolean equals(Position obj) {
        if (this == obj) return true;
        return this.col == obj.col && this.row == obj.row;
    }
}
