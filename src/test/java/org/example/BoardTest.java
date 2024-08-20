package org.example;

import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import org.example.piece.ChessPiece;
import org.example.piece.Pawn;
import org.example.piece.Queen;
import org.example.piece.Rook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void when_board_is_one_rook_position_is_valid() {
        Map<Position, ChessPiece> m = HashMap.empty();
        m = m.put(Position.of(4, 4), Rook.of(Position.of(4, 4), Color.WHITE));

        Board board = new Board(m);
        Assertions.assertTrue(board.isValidMove(Position.of(4,4), Position.of(0, 4)));
    }


    @Test
    void when_board_is_one_rock_and_one_pawn_up_position_not_valid() {
        Map<Position, ChessPiece> m = HashMap.empty();
        m = m.put(Position.of(4, 4), Rook.of(Position.of(4, 4), Color.WHITE))
                .put(Position.of(2, 4), Pawn.of(Position.of(2, 4), Color.BLACK));

        Board board = new Board(m);
        Assertions.assertFalse(board.isValidMove(Position.of(4,4), Position.of(0, 4)));
    }

    @Test
    void when_board_is_one_rock_and_one_pawn_down_position_not_valid() {
        Map<Position, ChessPiece> m = HashMap.empty();
        m = m.put(Position.of(4, 4), Rook.of(Position.of(4, 4), Color.WHITE))
                .put(Position.of(6, 4), Pawn.of(Position.of(6, 4), Color.BLACK));

        Board board = new Board(m);
        Assertions.assertFalse(board.isValidMove(Position.of(4,4), Position.of(7, 4)));
    }

    @Test
    void when_board_is_one_rock_and_one_pawn_left_position_not_valid() {
        Map<Position, ChessPiece> m = HashMap.empty();
        m = m.put(Position.of(4, 4), Rook.of(Position.of(4, 4), Color.WHITE))
                .put(Position.of(4, 2), Pawn.of(Position.of(4, 2), Color.BLACK));

        Board board = new Board(m);
        Assertions.assertFalse(board.isValidMove(Position.of(4,4), Position.of(4, 0)));
    }

    @Test
    void when_board_is_one_rock_and_one_pawn_right_position_not_valid() {
        Map<Position, ChessPiece> m = HashMap.empty();
        m = m.put(Position.of(4, 4), Rook.of(Position.of(4, 4), Color.WHITE))
                .put(Position.of(4, 5), Pawn.of(Position.of(4, 5), Color.BLACK));

        Board board = new Board(m);
        Assertions.assertFalse(board.isValidMove(Position.of(4,4), Position.of(4, 7)));
    }

    @Test
    void when_board_is_one_queen_and_one_pawn_up_position_not_valid() {
        Map<Position, ChessPiece> m = HashMap.empty();
        m = m.put(Position.of(4, 4), Queen.of(Position.of(4, 4), Color.WHITE))
                .put(Position.of(2, 4), Pawn.of(Position.of(2, 4), Color.BLACK));

        Board board = new Board(m);
        Assertions.assertFalse(board.isValidMove(Position.of(4,4), Position.of(0, 4)));
    }



}