package org.example;

public record Position(int row, int col) {

    public static Position of(final int row, final int col) {
        if (row < 0 || row > 7 || col < 0 || col > 7)
            throw new IllegalArgumentException("Position is not valid");

        return new Position(row, col);
    }

    public int rowDistance(Position to) {
        return row - to.row;
    }

    public int colDistance(Position to) {
        return col - to.col;
    }

    public Position up() {
        return new Position(row - 1, col);
    }

    public Position down() {
        return new Position(row + 1, col);
    }

    public Position left() {
        return new Position(row, col - 1);
    }

    public Position right() {
        return new Position(row, col + 1);
    }
}