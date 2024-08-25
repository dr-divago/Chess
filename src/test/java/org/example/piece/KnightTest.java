package org.example.piece;

import io.vavr.collection.List;
import io.vavr.collection.Map;
import org.example.Color;
import org.example.Direction;
import org.example.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @Test
    void knight_valid_positions() {
        Knight knight = Knight.of(Position.of(0, 1), Color.BLACK);
        Map<Direction, List<Position>> valid = knight.validPosition();
        Assertions.assertEquals(8, valid.size());
        Assertions.assertTrue(valid.get(Direction.DOWN).get().contains(Position.of(2,0)));
        Assertions.assertTrue(valid.get(Direction.DOWN_RIGHT).get().contains(Position.of(2, 2)));
        Assertions.assertTrue(valid.get(Direction.RIGHT).get().contains(Position.of(1, 3)));

    }
}