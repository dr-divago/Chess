package org.example.piece;

import io.vavr.collection.List;
import org.example.Color;
import org.example.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @Test
    void knight_valid_positions() {
        Knight knight = Knight.of(Position.of(0, 1), Color.BLACK);
        List<Position> valid = knight.validPosition();
        Assertions.assertEquals(3, valid.size());
        Assertions.assertTrue(valid.contains(Position.of(2,0)));
        Assertions.assertTrue(valid.contains(Position.of(2, 2)));
        Assertions.assertTrue(valid.contains(Position.of(1, 3)));
    }

    @Test
    void validPosition() {
    }

    @Test
    void testToString() {
    }
}