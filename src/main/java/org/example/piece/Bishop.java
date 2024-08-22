package org.example.piece;

import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import org.example.Color;
import org.example.Direction;
import org.example.Position;

public final class Bishop extends ChessPiece {
    private Bishop(Position position, Color color) {
        super(position, color);
    }

    public static Bishop of(Position position, Color color) {
        return new Bishop(position, color);
    }

    @Override
    public ChessPiece move(Position to) {
        return new Bishop(to, color);
    }

    @Override
    public Map<Direction, List<Position>> validPosition() {
        Map<Direction, List<Position>> pos = HashMap.empty();
        return pos
                .put(Direction.DOWN_RIGHT, PieceValidPosition.validPositionDiagonally(position, 1, 1))
                .put(Direction.DOWN_LEFT, PieceValidPosition.validPositionDiagonally(position, 1, -1))
                .put(Direction.UP_RIGHT, PieceValidPosition.validPositionDiagonally(position, -1, 1))
                .put(Direction.UP_LEFT, PieceValidPosition.validPositionDiagonally(position, -1, -1));
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "B" : "b";
    }
}
