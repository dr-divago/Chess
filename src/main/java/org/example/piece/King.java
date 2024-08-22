package org.example.piece;

import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import org.example.Color;
import org.example.Direction;
import org.example.Position;

public final class King extends ChessPiece {
    private King(Position position, Color color) {
        super(position, color);
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
                .put(Direction.UP, List.of(Position.of(position.row() - 1, position.col())))
                .put(Direction.DOWN, List.of(Position.of(position.row() + 1, position.col())))
                .put(Direction.LEFT, List.of(Position.of(position.row(), position.col() -1)))
                .put(Direction.RIGHT, List.of(Position.of(position.row(), position.col() + 1)))
                .put(Direction.UP_LEFT, List.of(Position.of(position.row() - 1, position.col() - 1)))
                .put(Direction.UP_RIGHT, List.of(Position.of(position.row() - 1, position.col() + 1)))
                .put(Direction.DOWN_RIGHT, List.of(Position.of(position.row() + 1, position.col() + 1)))
                .put(Direction.DOWN_LEFT, List.of(Position.of(position.row() + 1, position.col() - 1)));

    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "K" : "k";
    }
}
