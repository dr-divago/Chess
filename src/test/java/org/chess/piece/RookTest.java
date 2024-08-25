package org.chess.piece;

import io.vavr.collection.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.vavr.collection.List;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    @Test
    void when_rock_is_0_0_validPosition() {
        Rook rook = Rook.of(Position.of(0, 0), Color.BLACK);
        Map<Direction, List<Position>> directionListMap = rook.validPosition();
        assertEquals(4, directionListMap.size());
        List<Position> downDirection = directionListMap.get(Direction.DOWN).get();
        List<Position> expectedPosition = List.empty();
        for (int i = 1; i <= 7; i++) {
            expectedPosition = expectedPosition.append(Position.of(i, 0));
        }
        Assertions.assertEquals(expectedPosition, downDirection);

        List<Position> rightDirection = directionListMap.get(Direction.RIGHT).get();
        List<Position> expectedRightPosition =  List.empty();
        for (int i = 1; i <= 7; i++) {
            expectedRightPosition = expectedRightPosition.append(Position.of(0, i));
        }
        Assertions.assertEquals(expectedRightPosition, rightDirection);
    }

    @Test
    void when_rock_is_0_7_validPosition() {
        Rook rook = Rook.of(Position.of(0, 7), Color.BLACK);
        Map<Direction, List<Position>> directionListMap = rook.validPosition();
        assertEquals(4, directionListMap.size());

        List<Position> downDirection = directionListMap.get(Direction.DOWN).get();
        List<Position> expectedPosition = List.empty();
        for (int i = 1; i <= 7; i++) {
            expectedPosition = expectedPosition.append(Position.of(i, 7));
        }
        Assertions.assertEquals(expectedPosition, downDirection);

        List<Position> leftDirection = directionListMap.get(Direction.LEFT).get();
        List<Position> expectedLeftPosition =  List.empty();
        for (int i = 6; i >= 0; i--) {
            expectedLeftPosition = expectedLeftPosition.append(Position.of(0, i));
        }
        Assertions.assertEquals(expectedLeftPosition, leftDirection);
    }

    @Test
    void when_rock_is_7_0_validPosition() {
        Rook rook = Rook.of(Position.of(7, 0), Color.WHITE);
        Map<Direction, List<Position>> directionListMap = rook.validPosition();
        assertEquals(4, directionListMap.size());
    }

    @Test
    void when_rock_is_7_7_validPosition() {
        Rook rook = Rook.of(Position.of(7, 7), Color.WHITE);
        Map<Direction, List<Position>> directionListMap = rook.validPosition();
        assertEquals(4, directionListMap.size());
    }
}