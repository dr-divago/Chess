package org.chess.piece;

import io.vavr.collection.List;
import io.vavr.collection.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class QueenTest {

    @Test
    void validPosition() {
        Queen queen = Queen.of(Position.of(4, 4), Color.WHITE);
        Map<Direction, List<Position>> directionListMap = queen.validPosition();
        Assertions.assertEquals(8, directionListMap.size());
    }

    @Test
    void queen_up_valid_position() {
        Queen queen = Queen.of(Position.of(4,4), Color.WHITE);
        Map<Direction, List<Position>> directionListMap = queen.validPosition();
        List<Position> upPosition = directionListMap.get(Direction.UP).get();
        List<Position> upListPosition = List.of(
                Position.of(3, 4),
                Position.of(2, 4),
                Position.of(1, 4),
                Position.of(0, 4));
        Assertions.assertEquals(upListPosition, upPosition);
    }

    @Test
    void queen_down_valid_position() {
        Queen queen = Queen.of(Position.of(4,4), Color.WHITE);
        Map<Direction, List<Position>> directionListMap = queen.validPosition();
        List<Position> downPosition = directionListMap.get(Direction.DOWN).get();
        List<Position> downListPosition = List.of(
                Position.of(5, 4),
                Position.of(6, 4),
                Position.of(7, 4));
        Assertions.assertEquals(downListPosition, downPosition);
    }

    @Test
    void queen_left_valid_position() {
        Queen queen = Queen.of(Position.of(4,4), Color.WHITE);
        Map<Direction, List<Position>> directionListMap = queen.validPosition();
        List<Position> leftPosition = directionListMap.get(Direction.LEFT).get();
        List<Position> leftListPosition = List.of(
                Position.of(4, 3),
                Position.of(4, 2),
                Position.of(4, 1),
                Position.of(4, 0));
        Assertions.assertEquals(leftListPosition, leftPosition);
    }

    @Test
    void queen_right_valid_position() {
        Queen queen = Queen.of(Position.of(4,4), Color.WHITE);
        Map<Direction, List<Position>> directionListMap = queen.validPosition();
        List<Position> rightPosition = directionListMap.get(Direction.RIGHT).get();
        List<Position> rightListPosition = List.of(
                Position.of(4, 5),
                Position.of(4, 6),
                Position.of(4, 7));
        Assertions.assertEquals(rightListPosition, rightPosition);
    }
}