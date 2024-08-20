package org.example.piece;

import org.example.Color;
import org.example.Position;

import io.vavr.collection.List;

public final class Rook extends ChessPiece {
    private Rook(Position position, Color color) {
        super(position, color);
    }


    public static Rook of(Position position, Color color) {
        //if ((position.row() != 0 && position.col() != 0 && position.col() != 7))
        //    throw new IllegalArgumentException("Rock initial position is not valid");
        return new Rook(position, color);
    }

    public List<Position> validPosition() {
        return PieceValidPosition.validPositionVerticalUp(position)
                .pushAll(PieceValidPosition.validPositionOrizontallyRight(position))
                .pushAll(PieceValidPosition.validPositionOrizontallyLeft(position))
                .pushAll(PieceValidPosition.validPositionVerticallyDown(position));
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
