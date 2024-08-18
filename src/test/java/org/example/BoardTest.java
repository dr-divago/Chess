package org.example;

import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void getPiece() {
        Board board = Board.init();
        System.out.println(board);
        Board movedBoard = board.movePiece(Position.of(1, 0), Position.of(3, 0));
        System.out.println(movedBoard);
    }
}