package org.chess.game;

import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import org.chess.piece.*;
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

    @Test
    void when_board_is_one_bishop_and_one_pawn_position_not_valid() {
        Map<Position, ChessPiece> m = HashMap.empty();
        m = m.put(Position.of(7, 2), Bishop.of(Position.of(7, 2), Color.WHITE))
                .put(Position.of(4, 5), Pawn.of(Position.of(4, 5), Color.BLACK));

        Board board = new Board(m);
        Assertions.assertTrue(board.isValidMove(Position.of(7,2), Position.of(4, 5)));
    }

    @Test
    void when_board_is_one_pawn_and_one_pawn_vertically_not_valid_one_diagonally_valid() {
        Map<Position, ChessPiece> m = HashMap.empty();
        m = m.put(Position.of(6, 0), Pawn.of(Position.of(6, 0), Color.WHITE))
                .put(Position.of(5, 0), Pawn.of(Position.of(5, 0), Color.BLACK))
                .put(Position.of(5, 1), Pawn.of(Position.of(5, 1), Color.BLACK));

        Board board = new Board(m);
        Assertions.assertFalse(board.isValidMove(Position.of(6,0), Position.of(5, 0)));
        Assertions.assertTrue(board.isValidMove(Position.of(6, 0), Position.of(5, 1)));
    }

    @Test
    void when_board_is_one_knight_and_one_between_pawn_then_valid() {
        Map<Position, ChessPiece> m = HashMap.empty();
        m = m.put(Position.of(4, 4), Knight.of(Position.of(4, 4), Color.WHITE))
                .put(Position.of(5, 4), Pawn.of(Position.of(5, 4),Color.WHITE))
                .put(Position.of(6, 5), Pawn.of(Position.of(6, 5), Color.BLACK));

        Board board = new Board(m);
        Assertions.assertTrue(board.isValidMove(Position.of(4,4), Position.of(6, 5)));
    }

    @Test
    void when_board_is_king_in_check() {
        Map<Position, ChessPiece> m = HashMap.empty();
        m = m.put(Position.of(4, 5), King.of(Position.of(4, 5), Color.WHITE))
                .put(Position.of(6, 4), Pawn.of(Position.of(6,4), Color.BLACK));

        Board board = new Board(m);
        Assertions.assertTrue(board.isValidMove(Position.of(6, 4), Position.of(5, 4)));
        board = board.movePiece(Position.of(6, 4), Position.of(5, 4));
        Assertions.assertTrue(board.isCheck(Color.WHITE));
    }
}