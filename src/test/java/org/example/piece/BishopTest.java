package org.example.piece;

import io.vavr.collection.List;
import org.example.Color;
import org.example.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @Test
    void validPosition() {
        Bishop bishop = Bishop.of(Position.of(2, 3), Color.WHITE);
        List<Position> validPositions = bishop.validPosition();
        Assertions.assertEquals(11, validPositions.size());
        Assertions.assertTrue(validPositions.contains(Position.of(1, 2)));
        Assertions.assertTrue(validPositions.contains(Position.of(0, 1)));
        Assertions.assertTrue(validPositions.contains(Position.of(3, 4)));
        Assertions.assertTrue(validPositions.contains(Position.of(4, 5)));
        Assertions.assertTrue(validPositions.contains(Position.of(5, 6)));
        Assertions.assertTrue(validPositions.contains(Position.of(6, 7)));
        Assertions.assertTrue(validPositions.contains(Position.of(1, 4)));
        Assertions.assertTrue(validPositions.contains(Position.of(0, 5)));
        Assertions.assertTrue(validPositions.contains(Position.of(3, 2)));
        Assertions.assertTrue(validPositions.contains(Position.of(4, 1)));
        Assertions.assertTrue(validPositions.contains(Position.of(5, 0)));
    }
}