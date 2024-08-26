package org.chess.piece;

import io.vavr.collection.List;
import io.vavr.collection.Map;

public final class Queen extends ChessPiece implements PieceLogic {
    private Queen(Position position, Color color) {
        super(position, color);
    }

    public boolean isValidDirection(Direction direction) {
        return direction != Direction.INVALID;
    }

    public static Queen of(Position position, Color color) {
        return new Queen(position, color);
    }

    public Map<Direction, List<Position>> validPosition() {
        return PieceValidPosition.validPosition(position);

    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "Q" : "q";
    }
}
