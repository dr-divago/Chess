package org.chess.piece;

import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Stream;

public class PieceValidPosition {

    private static final int MAX_ROW = 7;
    private static final int MAX_COL = 7;

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
        while (p.col() <= MAX_COL) {
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
        while (p.row() <= MAX_ROW) {
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
        return validPosition
                .put(Direction.UP, validPositionVerticalUp(position))
                .put(Direction.DOWN, validPositionVerticallyDown(position))
                .put(Direction.LEFT, validPositionOrizontallyLeft(position))
                .put(Direction.RIGHT, validPositionOrizontallyRight(position))
                .put(Direction.UP_LEFT, validPositionDiagonally(position, -1, -1))
                .put(Direction.UP_RIGHT, validPositionDiagonally(position, -1, 1))
                .put(Direction.DOWN_LEFT, validPositionDiagonally(position, 1, -1))
                .put(Direction.DOWN_RIGHT, validPositionDiagonally(position, 1, 1));
    }
}
