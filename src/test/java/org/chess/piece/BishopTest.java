package org.chess.piece;

import io.vavr.collection.List;
import io.vavr.collection.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Test
    void validPosition() {
        Bishop bishop = Bishop.of(Position.of(2, 3), Color.WHITE);
        Map<Direction, List<Position>> directionListMap = bishop.validPosition();
        Assertions.assertEquals(4, directionListMap.size());
        Assertions.assertEquals(2, directionListMap.get(Direction.UP_LEFT).get().size());
        Assertions.assertTrue(directionListMap.get(Direction.UP_LEFT).get().contains(Position.of(1, 2)));
        Assertions.assertTrue(directionListMap.get(Direction.UP_LEFT).get().contains(Position.of(0, 1)));
        Assertions.assertTrue(directionListMap.get(Direction.UP_RIGHT).get().contains(Position.of(1, 4)));
        Assertions.assertTrue(directionListMap.get(Direction.UP_RIGHT).get().contains(Position.of(0, 5)));
        Assertions.assertTrue(directionListMap.get(Direction.DOWN_LEFT).get().contains(Position.of(3, 2)));
        Assertions.assertTrue(directionListMap.get(Direction.DOWN_LEFT).get().contains(Position.of(4, 1)));
        Assertions.assertTrue(directionListMap.get(Direction.DOWN_LEFT).get().contains(Position.of(5, 0)));
        Assertions.assertTrue(directionListMap.get(Direction.DOWN_RIGHT).get().contains(Position.of(3, 4)));
        Assertions.assertTrue(directionListMap.get(Direction.DOWN_RIGHT).get().contains(Position.of(4, 5)));
        Assertions.assertTrue(directionListMap.get(Direction.DOWN_RIGHT).get().contains(Position.of(5, 6)));
        Assertions.assertTrue(directionListMap.get(Direction.DOWN_RIGHT).get().contains(Position.of(6, 7)));
    }
}