package org.example;

import org.example.piece.Pawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    @ParameterizedTest
    @CsvSource({"0", "1", "2", "3", "4", "5", "6", "7"})
    void create_white_and_black_pawn(int col) {
        Pawn.of(Position.of(6, col), Color.WHITE);
        Pawn.of(Position.of(1, col), Color.BLACK);
    }

    @Test
    void when_pawn_is_create_with_wrong_postion_throw_exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Pawn.of(Position.of(0, 0), Color.WHITE));
    }

    @Test
    void when_created_white_pawn_is_in_starting_position() {
        Pawn pawn = Pawn.of(Position.of(6, 0), Color.WHITE);
        Assertions.assertTrue(pawn.isStartingPosition());
    }

    @Test
    void when_created_black_pawn_is_in_starting_position() {
        Pawn pawn = Pawn.of(Position.of(1, 0), Color.BLACK);
        Assertions.assertTrue(pawn.isStartingPosition());
    }

    @Test
    void when_white_pawn_is_moving_one_square() {
        Pawn pawn = Pawn.of(Position.of(6, 0), Color.WHITE);
        Assertions.assertTrue(pawn.isMovingOneSquare(Position.of(5, 0)));
    }

    @Test
    void when_black_pawn_is_moving_one_square() {
        Pawn pawn = Pawn.of(Position.of(1, 0), Color.BLACK);
        Assertions.assertTrue(pawn.isMovingOneSquare(Position.of(2, 0)));
    }

    @Test
    void when_white_pawn_is_moving_two_square() {
        Pawn pawn = Pawn.of(Position.of(6, 0), Color.WHITE);
        Assertions.assertTrue(pawn.isMovingTwoSquare(Position.of(4, 0)));
    }

    @Test
    void when_black_pawn_is_moving_two_square() {
        Pawn pawn = Pawn.of(Position.of(1, 0), Color.BLACK);
        Assertions.assertTrue(pawn.isMovingTwoSquare(Position.of(3, 0)));
    }

    @Test
    void when_white_pawn_is_moving_diagonally() {
        Pawn pawn = Pawn.of(Position.of(6, 0), Color.WHITE);
        Assertions.assertTrue(pawn.isMovingDiagonally(Position.of(5, 1)));
    }

    @Test
    void when_black_pawn_is_moving_diagonally() {
        Pawn pawn = Pawn.of(Position.of(1, 0), Color.BLACK);
        Assertions.assertTrue(pawn.isMovingDiagonally(Position.of(2, 1)));
    }

}