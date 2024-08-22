package org.example.piece;

import io.vavr.collection.Map;
import org.example.Color;
import org.example.Direction;
import org.example.Position;
import org.junit.jupiter.api.Test;

import io.vavr.collection.List;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    @Test
    void when_rock_is_0_0_validPosition() {
        Rook rook = Rook.of(Position.of(0, 0), Color.BLACK);
        Map<Direction, List<Position>> directionListMap = rook.validPosition();
        assertEquals(14, directionListMap.size());
    }

    @Test
    void when_rock_is_0_7_validPosition() {
        Rook rook = Rook.of(Position.of(0, 7), Color.BLACK);
        Map<Direction, List<Position>> directionListMap = rook.validPosition();
        assertEquals(14, directionListMap.size());
    }

    @Test
    void when_rock_is_7_0_validPosition() {
        Rook rook = Rook.of(Position.of(7, 0), Color.WHITE);
        Map<Direction, List<Position>> directionListMap = rook.validPosition();
        assertEquals(14, directionListMap.size());
    }

    @Test
    void when_rock_is_7_7_validPosition() {
        Rook rook = Rook.of(Position.of(7, 7), Color.WHITE);
        Map<Direction, List<Position>> directionListMap = rook.validPosition();
        assertEquals(14, directionListMap.size());
    }
}