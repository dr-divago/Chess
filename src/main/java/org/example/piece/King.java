package org.example.piece;

import io.vavr.collection.List;
import org.example.Color;
import org.example.Position;

public final class King extends ChessPiece {
    public King(Position position, Color color) {
        super(position, color);
    }

    @Override
    public ChessPiece move(Position to) {
        return new King(to, color);
    }

    @Override
    public List<Position> validPosition() {
        return null;
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "K" : "k";
    }
}
