package org.example.piece;

import org.example.Color;
import org.example.Position;

public final class Knight extends ChessPiece {
    public Knight(Position position, Color color) {
        super(position, color);
    }

    @Override
    public ChessPiece move(Position to) {
        return new Knight(to, color);
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "N" : "n";
    }
}
