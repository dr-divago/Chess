package org.example.piece;

import org.example.Color;
import org.example.Position;

public final class Queen extends ChessPiece {
    public Queen(Position position, Color color) {
        super(position, color);
    }

    @Override
    public ChessPiece move(Position to) {
        return new Queen(to, color);
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "Q" : "q";
    }
}
