package org.example.piece;

import io.vavr.collection.List;
import io.vavr.collection.Stream;
import org.example.Color;
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
    public List<Position> validPosition() {
        List<Position> validPositions = List.empty();
        List<Position> pos1 = Stream.iterate(position, p -> p.to(1, 1))
                .filter(p -> p.row() != position.row() && p.col() != position.col())
                .takeWhile(p -> p.validPosition(7))
                .toList();

        List<Position> pos2 = Stream.iterate(position, p -> p.to(1, -1))
                .filter(p -> p.row() != position.row() && p.col() != position.col())
                .takeWhile(p -> p.validPosition(7))
                .toList();

        List<Position> pos3 = Stream.iterate(position, p -> p.to(-1, 1))
                .filter(p -> p.row() != position.row() && p.col() != position.col())
                .takeWhile(p -> p.validPosition(7))
                .toList();

        List<Position> pos4 = Stream.iterate(position, p -> p.to(-1, -1))
                .filter(p -> p.row() != position.row() && p.col() != position.col())
                .takeWhile(p -> p.validPosition(7))
                .toList();
        return validPositions
                .pushAll(pos1)
                .pushAll(pos2)
                .pushAll(pos3)
                .pushAll(pos4);
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "B" : "b";
    }
}
