package org.example.piece;

import io.vavr.collection.List;
import org.example.Color;
import org.example.Position;

public final class Bishop extends ChessPiece {
    public Bishop(Position position, Color color) {
        super(position, color);
    }

    @Override
    public ChessPiece move(Position to) {
        return new Bishop(to, color);
    }

    @Override
    public List<Position> validPosition() {
        return List.empty();
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "B" : "b";
    }
}
