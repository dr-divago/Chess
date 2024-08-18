package org.example.piece;

import io.vavr.collection.List;
import org.example.Color;
import org.example.Direction;
import org.example.Position;

public abstract sealed class ChessPiece permits Pawn, Knight, Bishop, Rook, Queen, King {
    protected final Position  position;
    protected final Color     color;
    protected final Direction direction;


    public ChessPiece(Position position, Color color) {
        this.position = position;
        this.color = color;
        this.direction = color == Color.WHITE ? Direction.UP : Direction.DOWN;
    }

    public Direction direction() {
        return direction;
    }

    public Position position() {
        return position;
    }

    public Color color() {
        return color;
    }

    public abstract ChessPiece move(Position to);
    public abstract List<Position> validPosition();

    public boolean isMovingVertically(Position to) {
        int colDist = Math.abs(to.col() - position.col());
        return colDist == 0;
    }
}
