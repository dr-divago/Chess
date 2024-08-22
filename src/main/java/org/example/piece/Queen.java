package org.example.piece;

import io.vavr.collection.List;
import io.vavr.collection.Map;
import org.example.Color;
import org.example.Direction;
import org.example.Position;

public final class Queen extends ChessPiece {
    private Queen(Position position, Color color) {
        super(position, color);
    }

    public static Queen of(Position position, Color color) {
        return new Queen(position, color);
    }

    @Override
    public ChessPiece move(Position to) {
        return new Queen(to, color);
    }

    @Override
    public Map<Direction, List<Position>> validPosition() {
        return PieceValidPosition.validPosition(position);

    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "Q" : "q";
    }
}
