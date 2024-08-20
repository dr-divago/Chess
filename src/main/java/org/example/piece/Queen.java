package org.example.piece;

import io.vavr.collection.List;
import org.example.Color;
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
    public List<Position> validPosition() {
        return PieceValidPosition.validPositionOrizontallyLeft(position)
                .pushAll(PieceValidPosition.validPositionOrizontallyRight(position))
                .pushAll(PieceValidPosition.validPositionVerticalUp(position))
                .pushAll(PieceValidPosition.validPositionVerticallyDown(position))
                .pushAll(PieceValidPosition.validPositionDiagonally(position, 1, 1))
                .pushAll(PieceValidPosition.validPositionDiagonally(position, 1, -1))
                .pushAll(PieceValidPosition.validPositionDiagonally(position, -1, 1))
                .pushAll(PieceValidPosition.validPositionDiagonally(position, -1, -1));

    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "Q" : "q";
    }
}
