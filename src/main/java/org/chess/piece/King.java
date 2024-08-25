package org.chess.piece;

import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;

public final class King extends ChessPiece {
    private King(Position position, Color color) {
        super(position, color);
    }

    @Override
    public boolean isValidDirection(Direction direction) {
        return direction == Direction.UP ||
                direction == Direction.UP_LEFT ||
                direction == Direction.UP_RIGHT ||
                direction == Direction.LEFT ||
                direction == Direction.DOWN_LEFT ||
                direction == Direction.DOWN ||
                direction == Direction.DOWN_RIGHT ||
                direction == Direction.RIGHT;
    }


    public static King of(Position position, Color color) {
        return new King(position, color);
    }
    @Override
    public ChessPiece move(Position to) {
        return new King(to, color);
    }

    @Override
    public Map<Direction, List<Position>> validPosition() {
        Map<Direction, List<Position>> pos = HashMap.empty();
        return pos
                .put(Direction.UP, List.of(Position.of(position.row() - 1, position.col())).filter(this::isValid))
                .put(Direction.DOWN, List.of(Position.of(position.row() + 1, position.col())).filter(this::isValid))
                .put(Direction.LEFT, List.of(Position.of(position.row(), position.col() -1)).filter(this::isValid))
                .put(Direction.RIGHT, List.of(Position.of(position.row(), position.col() + 1)).filter(this::isValid))
                .put(Direction.UP_LEFT, List.of(Position.of(position.row() - 1, position.col() - 1)).filter(this::isValid))
                .put(Direction.UP_RIGHT, List.of(Position.of(position.row() - 1, position.col() + 1)).filter(this::isValid))
                .put(Direction.DOWN_RIGHT, List.of(Position.of(position.row() + 1, position.col() + 1)).filter(this::isValid))
                .put(Direction.DOWN_LEFT, List.of(Position.of(position.row() + 1, position.col() - 1)).filter(this::isValid));

    }

    private boolean isValid(Position p) {
        return p.row() >= 0 && p.row() <= 7 && p.col() >= 0 && p.col() <= 7;
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "K" : "k";
    }
}
