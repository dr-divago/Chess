package org.chess.piece;

import io.vavr.collection.List;
import io.vavr.collection.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    void validPosition() {
        King king = King.of(Position.of(4, 4), Color.WHITE);
        Map<Direction, List<Position>> directionListMap = king.validPosition();
        Assertions.assertEquals(8, directionListMap.size());
        Assertions.assertTrue(directionListMap.get(Direction.UP).get().contains(Position.of(3, 4)));
        Assertions.assertTrue(directionListMap.get(Direction.UP_RIGHT).get().contains(Position.of(3, 5)));
        Assertions.assertTrue(directionListMap.get(Direction.UP_LEFT).get().contains(Position.of(3, 3)));
        Assertions.assertTrue(directionListMap.get(Direction.RIGHT).get().contains(Position.of(4, 5)));
        Assertions.assertTrue(directionListMap.get(Direction.LEFT).get().contains(Position.of(4, 3)));
        Assertions.assertTrue(directionListMap.get(Direction.DOWN_LEFT).get().contains(Position.of(5, 3)));
        Assertions.assertTrue(directionListMap.get(Direction.DOWN).get().contains(Position.of(5, 4)));
        Assertions.assertTrue(directionListMap.get(Direction.DOWN_RIGHT).get().contains(Position.of(5, 5)));
    }
}