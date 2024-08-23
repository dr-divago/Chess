package org.example;

import io.vavr.control.Try;

public class ChessGame {
    private Board board;
    private Color turn;

    public ChessGame() {
        this.board = Board.init();
        this.turn = Color.WHITE;
    }

    public ChessGame(Board board, Color color) {
        this.board = board;
        this.turn = color;
    }

    public boolean isCheck(Color currentTurn) {
        return board.isCheck(currentTurn);
    }

    public Color getCurrentTurn() {
        return turn;
    }

    public Color changeTurn() {
        return turn == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public Try<ChessGame> move(Position from, Position to) {
        return Try.of(() -> {
            if (!isValid(from, to)) {
                throw new IllegalMoveException("Invalid move");
            }
            Board newBoard = board.movePiece(from, to);
            ChessGame newGame = new ChessGame(newBoard, changeTurn());

            if (newGame.isCheck(newGame.getCurrentTurn())) {
                throw new IllegalMoveException("Move leaves king in check");
            }

            return newGame;
        });
    }

    @Override
    public String toString() {
        return board.toString();
    }

    public boolean isValid(Position from, Position to) {
        return board.isValidMove(from, to);
    }
}
