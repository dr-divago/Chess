package org.example.piece;

import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Stream;
import org.example.Direction;
import org.example.Position;

public class PieceValidPosition {

    public static List<Position> validPositionVerticalUp(Position position) {
        List<Position> validPosition = List.empty();
        Position p = position.up(1);
        while (p.row() >= 0) {
            validPosition = validPosition.append(p);
            p = p.up(1);
        }
        return validPosition;
    }

    public static List<Position> validPositionOrizontallyRight(Position position) {
        List<Position> validPosition = List.empty();
        Position p = position.right(1);
        while (p.col() <= 7) {
            validPosition = validPosition.append(p);
            p = p.right(1);
        }
        return validPosition;
    }

    public static List<Position> validPositionOrizontallyLeft(Position position) {
        List<Position> validPosition = List.empty();
        Position p = position.left(1);
        while (p.col() >= 0) {
            validPosition = validPosition.append(p);
            p = p.left(1);
        }
        return validPosition;
    }

    public static List<Position> validPositionVerticallyDown(Position position) {
        List<Position> validPosition = List.empty();
        Position p = position.down(1);
        while (p.row() <= 7) {
            validPosition = validPosition.append(p);
            p = p.down(1);
        }
        return validPosition;
    }

    public static List<Position> validPositionDiagonally(Position position, int stepX, int stepY) {
        return Stream.iterate(position, p -> p.to(stepX, stepY))
                .filter(p -> p.row() != position.row() && p.col() != position.col())
                .takeWhile(p -> p.validPosition(7))
                .toList();
    }

    public static Map<Direction, List<Position>> validPosition(Position position) {
        Map<Direction, List<Position>> validPosition = HashMap.empty();
        validPosition = validPosition.put(Direction.UP, validPositionVerticalUp(position));
        return validPosition;
    }
}
