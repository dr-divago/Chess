package org.example.piece;

import io.vavr.collection.List;
import io.vavr.collection.Map;
import org.example.Color;
import org.example.Direction;
import org.example.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @Test
    void validPosition() {
        Queen queen = Queen.of(Position.of(4, 4), Color.WHITE);
        Map<Direction, List<Position>> directionListMap = queen.validPosition();
        Assertions.assertEquals(27, directionListMap.size());
    }
}