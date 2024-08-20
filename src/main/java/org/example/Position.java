package org.example;

public record Position(int row, int col) {

    public static Position of(final int row, final int col) {
        if (row < 0 || row > 7 || col < 0 || col > 7)
            throw new IllegalArgumentException("Position is not valid");

        return new Position(row, col);
    }

    public boolean validPosition(int limit) {
        return row >= 0 && row <= limit && col >= 0 && col <= 7;
    }

    public int rowDistance(Position to) {
        return to.row - row;
    }

    public int colDistance(Position to) {
        return to.col - col;
    }

    public Position up(int val) {
        return new Position(row - val, col);
    }

    public Position down(int val) {
        return new Position(row + val, col);
    }

    public Position left(int val) {
        return new Position(row, col - val);
    }

    public Position right(int val) {
        return new Position(row, col + val);
    }

    public Position to(int x, int y) {
        return new Position(row + x, col + y);
    }
}