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

    public Direction direction(Position to) {
        int dx = to.row - row;
        int dy = to.col - col;


        if (dy == 0) {
            if (dx > 0) {
                return Direction.DOWN;

            }
            if (dx < 0) {
                return Direction.UP;
            }
        }
        if (dx == 0) {
            if (dy > 0) {
                return Direction.RIGHT;
            }
            if (dy < 0) {
                return Direction.LEFT;
            }
        }
        if (Math.abs(dx) == Math.abs(dy)) {
            if (dx > 0 && dy > 0) {
                return Direction.DOWN_LEFT;
            }
            if (dx > 0) {
                return Direction.DOWN_RIGHT;

            }
            if (dx < 0 && dy < 0) {
                return Direction.UP_LEFT;
            }
            if (dx < 0) {
                return Direction.UP_RIGHT;
            }
        }

        throw new IllegalArgumentException("Error calculating direction");
    }

    public Position moveToDirection(Direction direction) {
        return switch (direction) {
            case UP -> up(1);
            case DOWN -> down(1);
            case LEFT -> left(1);
            case RIGHT -> right(1);
            case UP_LEFT -> to(-1, -1);
            case UP_RIGHT -> to(-1, 1);
            case DOWN_LEFT -> to(1, -1);
            case DOWN_RIGHT -> to(1, 1);
        };
    }
}