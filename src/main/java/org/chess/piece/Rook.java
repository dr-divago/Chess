package org.chess.piece;

import io.vavr.collection.HashMap;
import io.vavr.collection.Map;

import io.vavr.collection.List;

public final class Rook extends ChessPiece {
    private Rook(Position position, Color color) {
        super(position, color);
    }

    @Override
    public boolean isValidDirection(Direction direction) {
        return direction == Direction.UP ||
                direction == Direction.LEFT ||
                direction == Direction.RIGHT ||
                direction == Direction.DOWN;
    }


    public static Rook of(Position position, Color color) {
        //if ((position.row() != 0 && position.col() != 0 && position.col() != 7))
        //    throw new IllegalArgumentException("Rock initial position is not valid");
        return new Rook(position, color);
    }

    public Map<Direction, List<Position>> validPosition() {
        Map<Direction, List<Position>> pos = HashMap.empty();
        return pos
                .put(Direction.UP, PieceValidPosition.validPositionVerticalUp(position))
                .put(Direction.RIGHT, PieceValidPosition.validPositionOrizontallyRight(position))
                .put(Direction.LEFT, PieceValidPosition.validPositionOrizontallyLeft(position))
                .put(Direction.DOWN, PieceValidPosition.validPositionVerticallyDown(position));
    }
    @Override
    public ChessPiece move(Position to) {
        return new Rook(to, color);
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "R" : "r";
    }
}
