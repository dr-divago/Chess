package org.example;

import io.vavr.control.Try;

public class ChessGame {
    private final Board board;
    private final Color turn;
    private final CheckState state;

    public ChessGame() {
        this.board = Board.init();
        this.turn = Color.WHITE;
        this.state = CheckState.NO_CHECK;
    }

    public ChessGame(Board board, Color color, CheckState state) {
        this.board = board;
        this.turn = color;
        this.state = state;
    }

    public boolean isCheck() {
        return state.equals(CheckState.CHECK);
    }

    public Color getCurrentTurn() {
        return turn;
    }

    public Color changeTurn() {
        return turn == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public Try<ChessGame> move(Position from, Position to) {
        return Try.of(() -> {
            if (!isValid(from, to, getCurrentTurn())) {
                throw new IllegalMoveException("from " + from + " to " + to + " for " + getCurrentTurn());
            }
            Board newBoard = board.movePiece(from, to);

           if (newBoard.isCheck(changeTurn())) {
               return new ChessGame(newBoard, changeTurn(), CheckState.CHECK);
           }
           else {
                return new ChessGame(newBoard, changeTurn(), CheckState.NO_CHECK);
           }
        });
    }

    @Override
    public String toString() {
        return ChessBoardDisplay.print(board);
    }

    public boolean isValid(Position from, Position to, Color color) {
        return board.isValidMove(from, to) && color.equals(board.getColorInPosition(from));
    }
}
