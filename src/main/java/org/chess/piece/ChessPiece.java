package org.chess.piece;

public abstract sealed class ChessPiece implements PieceLogic permits Pawn, Knight, Bishop, Rook, Queen, King {
    protected final Position  position;
    protected final Color     color;
    protected final Direction direction;

    public ChessPiece(Position position, Color color) {
        this.position = position;
        this.color = color;
        this.direction = color == Color.WHITE ? Direction.UP : Direction.DOWN;
    }

    public Position position() {
        return position;
    }

    public Color color() {
        return color;
    }
}
