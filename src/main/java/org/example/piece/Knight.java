package org.example.piece;

import io.vavr.collection.List;
import org.example.Color;
import org.example.Position;

public final class Knight extends ChessPiece {
    private Knight(Position position, Color color) {
        super(position, color);
    }

    @Override
    public ChessPiece move(Position to) {
        return new Knight(to, color);
    }

    public static Knight of(Position position, Color color) {
        return new Knight(position, color);
    }

    @Override
    public List<Position> validPosition() {
        List<Position> all = List.of(position.to(2, 1), position.to(1, 2), position.to(-2, 1), position.to(2, -1));
        return all.filter(p -> p.row() >= 0 && p.row() <= 7 && p.col() >= 0 && p.col() <= 7);
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "N" : "n";
    }
}
