package org.chess.exception;

public class IllegalMoveException extends Throwable {
    public IllegalMoveException(String moveLeavesKingInCheck) {
        super(moveLeavesKingInCheck);
    }
}
