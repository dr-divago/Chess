package org.example.piece;

import org.example.Color;
import org.example.Position;

import io.vavr.collection.List;

public final class Rook extends ChessPiece {
    private Rook(Position position, Color color) {
        super(position, color);
    }


    public static Rook of(Position position, Color color) {
        if ((position.row() != 0 && position.col() != 0 && position.col() != 7))
            throw new IllegalArgumentException("Rock initial position is not valid");
        return new Rook(position, color);
    }

    public List<Position> validPosition() {
        List<Position> valid = List.empty();
        Position p = position.up(1);
        while (p.row() >= 0) {
            valid = valid.append(p);
            p = p.up(1);
        }
        p = position.right(1);
        while (p.col() <= 7) {
            valid = valid.append(p);
            p = p.right(1);
        }
        p = position.left(1);
        while (p.col() >= 0) {
            valid = valid.append(p);
            p = p.left(1);
        }
        p = position.down(1);
        while (p.row() <= 7) {
            valid = valid.append(p);
            p = p.down(1);
        }
        return valid;
    }
    @Override
    public ChessPiece move(Position to) {
        return new Rook(to, color);
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "R" : "r";
    }
}
