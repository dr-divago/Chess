package org.example.piece;

import io.vavr.collection.List;
import org.example.Color;
import org.example.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    void validPosition() {
        King king = King.of(Position.of(4, 4), Color.WHITE);
        List<Position> validPosition = king.validPosition();
        Assertions.assertEquals(8, validPosition.size());
        Assertions.assertTrue(validPosition.contains(Position.of(3, 4)));
        Assertions.assertTrue(validPosition.contains(Position.of(3, 5)));
        Assertions.assertTrue(validPosition.contains(Position.of(3, 3)));
        Assertions.assertTrue(validPosition.contains(Position.of(4, 5)));
        Assertions.assertTrue(validPosition.contains(Position.of(4, 3)));
        Assertions.assertTrue(validPosition.contains(Position.of(5, 3)));
        Assertions.assertTrue(validPosition.contains(Position.of(5, 4)));
        Assertions.assertTrue(validPosition.contains(Position.of(5, 5)));
    }
}