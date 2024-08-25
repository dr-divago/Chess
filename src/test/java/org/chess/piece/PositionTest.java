package org.chess.piece;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class PositionTest {

    @ParameterizedTest
    @CsvSource({
            "0, 0", "0, 1", "0, 2", "0, 3", "0, 4", "0, 5", "0, 6", "0, 7",
            "1, 0", "1, 1", "1, 2", "1, 3", "1, 4", "1, 5", "1, 6", "1, 7",
            "2, 0", "2, 1", "2, 2", "2, 3", "2, 4", "2, 5", "2, 6", "2, 7",
            "3, 0", "3, 1", "3, 2", "3, 3", "3, 4", "3, 5", "3, 6", "3, 7",
            "4, 0", "4, 1", "4, 2", "4, 3", "4, 4", "4, 5", "4, 6", "4, 7",
            "5, 0", "5, 1", "5, 2", "5, 3", "5, 4", "5, 5", "5, 6", "5, 7",
            "6, 0", "6, 1", "6, 2", "6, 3", "6, 4", "6, 5", "6, 6", "6, 7",
            "7, 0", "7, 1", "7, 2", "7, 3", "7, 4", "7, 5", "7, 6", "7, 7"})
    void valid_position(int row, int col) {
        Assertions.assertDoesNotThrow(() -> {
            Position position = Position.of(row, col);}
        );
    }

    @Test
    void position_to() {
        Position p = Position.of(2, 3);
        Position to = p.to(4, 3);
        Assertions.assertEquals(6, to.row());
        Assertions.assertEquals(6, to.col());
    }

    @Test
    void position_right() {
        Position p = Position.of(2, 3);
        Position to = p.right(1);
        Assertions.assertEquals(2, to.row());
        Assertions.assertEquals(4, to.col());
    }

    @Test
    void position_left() {
        Position p = Position.of(2, 3);
        Position to = p.left(1);
        Assertions.assertEquals(2, to.row());
        Assertions.assertEquals(2, to.col());
    }

    @Test
    void position_down() {
        Position p = Position.of(2, 3);
        Position to = p.down(1);
        Assertions.assertEquals(3, to.row());
        Assertions.assertEquals(3, to.col());
    }

    @Test
    void position_positive_colDistance() {
        Position p = Position.of(2, 3);
        int colDistance = p.colDistance(Position.of(2, 4));
        Assertions.assertEquals(1, colDistance);
    }

    @Test
    void position_negative_colDistance() {
        Position p = Position.of(2, 3);
        int colDistance = p.colDistance(Position.of(2, 2));
        Assertions.assertEquals(-1, colDistance);
    }

    @Test
    void position_positive_rowDistance() {
        Position p = Position.of(2, 3);
        int colDistance = p.rowDistance(Position.of(3, 4));
        Assertions.assertEquals(1, colDistance);
    }

    @Test
    void position_negative_rowDistance() {
        Position p = Position.of(2, 3);
        int colDistance = p.colDistance(Position.of(1, 2));
        Assertions.assertEquals(-1, colDistance);
    }

    @Test
    void when_position_direction_down() {
        Position position = Position.of(4, 4);
        Position to = Position.of(7, 4);

        Direction direction = position.direction(to);
        Assertions.assertEquals(Direction.DOWN, direction);
    }

    @Test
    void when_position_direction_up() {
        Position position = Position.of(4, 4);
        Position to = Position.of(0, 4);

        Direction direction = position.direction(to);
        Assertions.assertEquals(Direction.UP, direction);
    }

    @Test
    void when_position_direction_right() {
        Position position = Position.of(4, 4);
        Position to = Position.of(4, 7);

        Direction direction = position.direction(to);
        Assertions.assertEquals(Direction.RIGHT, direction);
    }

    @Test
    void when_position_direction_left() {
        Position position = Position.of(4, 4);
        Position to = Position.of(4, 0);

        Direction direction = position.direction(to);
        Assertions.assertEquals(Direction.LEFT, direction);
    }

    @Test
    void when_position_direction_down_left() {
        Position position = Position.of(4, 4);
        Position to = Position.of(5, 3);

        Direction direction = position.direction(to);
        Assertions.assertEquals(Direction.DOWN_LEFT, direction);
    }

    @Test
    void when_position_direction_down_right() {
        Position position = Position.of(4, 4);
        Position to = Position.of(5, 5);

        Direction direction = position.direction(to);
        Assertions.assertEquals(Direction.DOWN_RIGHT, direction);
    }

    @Test
    void when_position_direction_up_left() {
        Position position = Position.of(4, 4);
        Position to = Position.of(3, 3);

        Direction direction = position.direction(to);
        Assertions.assertEquals(Direction.UP_LEFT, direction);
    }

    @Test
    void when_position_direction_up_right() {
        Position position = Position.of(4, 4);
        Position to = Position.of(3, 5);

        Direction direction = position.direction(to);
        Assertions.assertEquals(Direction.UP_RIGHT, direction);
    }

    @Test
    void moveUpToDirection() {
        Position position = Position.of(4, 4);
        Position upPosition = position.moveToDirection(Direction.UP);
        Assertions.assertEquals(Position.of(3, 4), upPosition);
    }

    @Test
    void moveDownToDirection() {
        Position position = Position.of(4, 4);
        Position downPosition = position.moveToDirection(Direction.DOWN);
        Assertions.assertEquals(Position.of(5, 4), downPosition);
    }

    @Test
    void moveLeftToDirection() {
        Position position = Position.of(4, 4);
        Position leftPosition = position.moveToDirection(Direction.LEFT);
        Assertions.assertEquals(Position.of(4, 3), leftPosition);
    }

    @Test
    void moveRightToDirection() {
        Position position = Position.of(4, 4);
        Position rightPosition = position.moveToDirection(Direction.RIGHT);
        Assertions.assertEquals(Position.of(4, 5), rightPosition);
    }

    @Test
    void moveUpRightToDirection() {
        Position position = Position.of(4, 4);
        Position upRightPosition = position.moveToDirection(Direction.UP_RIGHT);
        Assertions.assertEquals(Position.of(3, 5), upRightPosition);
    }

    @Test
    void moveUpLeftToDirection() {
        Position position = Position.of(4, 4);
        Position upLeftPosition = position.moveToDirection(Direction.UP_LEFT);
        Assertions.assertEquals(Position.of(3, 3), upLeftPosition);
    }

    @Test
    void moveDownLeftToDirection() {
        Position position = Position.of(4, 4);
        Position downLeftPosition = position.moveToDirection(Direction.DOWN_LEFT);
        Assertions.assertEquals(Position.of(5, 3), downLeftPosition);
    }

    @Test
    void moveDownRightToDirection() {
        Position position = Position.of(4, 4);
        Position downRightPosition = position.moveToDirection(Direction.DOWN_RIGHT);
        Assertions.assertEquals(Position.of(5, 5), downRightPosition);
    }

    @Test
    void moveInvalidDirection() {
        Position position = Position.of(4, 4);
        Position samePosition = position.moveToDirection(Direction.INVALID);
        Assertions.assertEquals(Position.of(4, 4), samePosition);
    }
}