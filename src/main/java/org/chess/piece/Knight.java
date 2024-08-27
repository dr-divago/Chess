package org.chess.piece;

import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;

public final class Knight extends ChessPiece implements PieceLogic {
    private Knight(Position position, Color color) {
        super(position, color);
    }

    public boolean isValidDirection(Direction direction) {
        return true;
    }

    public static Knight of(Position position, Color color) {
        return new Knight(position, color);
    }

    public Map<Direction, List<Position>> validPosition() {
        Map<Direction, List<Position>> pos = HashMap.empty();
        return pos
                .put(Direction.UP, List.of(position.to(-2, 1)).filter((this::filterPosition)))
                .put(Direction.UP_RIGHT, List.of(position.to(-1, 2)).filter(this::filterPosition))
                .put(Direction.RIGHT, List.of(position.to(1, 2)).filter(this::filterPosition))
                .put(Direction.DOWN_RIGHT, List.of(position.to(2, 1)).filter(this::filterPosition))
                .put(Direction.DOWN, List.of(position.to(2, -1)).filter(this::filterPosition))
                .put(Direction.DOWN_LEFT, List.of(position.to(1, -2)).filter(this::filterPosition))
                .put(Direction.LEFT, List.of(position.to(-1, -2)).filter(this::filterPosition))
                .put(Direction.UP_LEFT, List.of(position.to(-2, -1)).filter(this::filterPosition));
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "N" : "n";
    }

    private boolean filterPosition(Position p) {
        return p.row() >= 0 && p.row() <= 7 && p.col() >= 0 && p.col() <= 7;
    }
}
