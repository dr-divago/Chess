package org.example.piece;

import io.vavr.collection.List;
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
        return PieceValidPosition.validPositionDiagonally(position, 1, 1)
                .pushAll(PieceValidPosition.validPositionDiagonally(position, 1, -1))
                .pushAll(PieceValidPosition.validPositionDiagonally(position, -1, 1))
                .pushAll(PieceValidPosition.validPositionDiagonally(position, -1, -1));
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "B" : "b";
    }
}
