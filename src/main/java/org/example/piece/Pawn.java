package org.example.piece;

import io.vavr.collection.List;
import org.example.Color;
import org.example.Position;

public final class Pawn extends ChessPiece {

    private Pawn(Position position, Color color) {
        super(position, color);
    }

    @Override
    public Pawn move(Position to) {
        return new Pawn(to, color);
    }

    @Override
    public List<Position> validPosition() {
        return null;
    }

    public static Pawn of(Position position, Color color) {
        /*
        if (color.equals(Color.BLACK) && position.row() == 1 && position.col() >= 0 && position.col() <= 7) {
            return new Pawn(position, color);
        }
        if (color.equals(Color.WHITE) && position.row() == 6 && position.col() >= 0 && position.col() <= 7) {
            return new Pawn(position, color);
        }

        throw new IllegalArgumentException("Invalid Pawn position");

         */
        return new Pawn(position, color);
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "P" : "p";
    }

    public boolean isMovingOneSquare(Position to) {
        return Math.abs(position.rowDistance(to)) == 1;
    }

    public boolean isMovingTwoSquare(Position to) {
        return Math.abs(position.rowDistance(to)) == 2;
    }

    public boolean isStartingPosition() {
        return color == Color.WHITE && position.row() == 6 || color == Color.BLACK && position.row() == 1;
    }

    public boolean isMovingDiagonally(Position to) {
        return Math.abs(position.colDistance(to) )== 1 && Math.abs(position.rowDistance(to)) == 1;
    }

    public Position oneSquareFront() {
        return color == Color.WHITE ? position.up(1) : position.down(1);
    }
}
