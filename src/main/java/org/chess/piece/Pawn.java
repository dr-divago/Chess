package org.chess.piece;

import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;

public final class Pawn extends ChessPiece {

    private Pawn(Position position, Color color) {
        super(position, color);
    }

    @Override
    public boolean isValidDirection(Direction direction) {
        if (color == Color.WHITE) {
            return direction == Direction.UP ||
                    direction == Direction.UP_RIGHT ||
                    direction == Direction.UP_LEFT;
        }
        else
            return direction == Direction.DOWN ||
                    direction == Direction.DOWN_RIGHT ||
                    direction == Direction.DOWN_LEFT;
    }

    @Override
    public Map<Direction, List<Position>> validPosition() {
        return HashMap.empty();
    }

    public static Pawn of(Position position, Color color) {
        return new Pawn(position, color);
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "P" : "p";
    }

    public boolean isMovingOneSquare(Position to) {
        return Math.abs(position.rowDistance(to)) == 1;
    }

    public boolean isMovingTwoSquare(Position to) {
        return Math.abs(position.rowDistance(to)) == 2;
    }

    public boolean isStartingPosition() {
        return color == Color.WHITE && position.row() == 6 || color == Color.BLACK && position.row() == 1;
    }

    public boolean isMovingDiagonally(Position to) {
        return
                Math.abs(position.colDistance(to)) == 1 &&
                Math.abs(position.rowDistance(to)) == 1;
    }

    public Position oneSquareFront() {
        return color == Color.WHITE
                ? position.up(1)
                : position.down(1);
    }

    public boolean isMovingVertically(Position to) {
        int colDist = Math.abs(to.col() - position.col());
        return colDist == 0;
    }
}
