package org.chess.piece;

import io.vavr.collection.List;
import io.vavr.collection.Map;

public sealed interface PieceLogic permits ChessPiece, Bishop, King, Knight, Pawn, Queen, Rook {
    default boolean isValidDirection(Direction direction) {
        return switch (this) {
                case Pawn p -> p.isValidDirection(direction);
                case Rook r -> r.isValidDirection(direction);
                case Knight k -> k.isValidDirection(direction);
                case Bishop b -> b.isValidDirection(direction);
                case King k -> k.isValidDirection(direction);
                case Queen q -> q.isValidDirection(direction);
            };
    }

    /*
    If the direction is not in the set of possible direction of the piece return false
    otherwise find the list of valid position according to that direction and check if position to is
    contained in that list

    for example Q move in all direction up,down,left,right,up-left, up-right, down-left, down-right,
    the possible direction between Q and the Position should be in the direction calculation.
    Q(4,4) -> P(0, 4) have direction UP and validPosition (3, 4), (2,4), (1,4), (0,4).
     */
    default boolean canMoveTo(Direction direction, Position to) {
        if (!isValidDirection(direction))
            return false;

        Map<Direction, List<Position>> directionListMap = validPosition();
        List<Position> validPositions = directionListMap.get(direction).get();
        return validPositions.contains(to);
    }

    default ChessPiece move(Position to) {
        return switch (this) {
            case Pawn p -> Pawn.of(to, p.color);
            case Rook r -> Rook.of(to, r.color);
            case Knight k -> Knight.of(to, k.color);
            case Bishop b -> Bishop.of(to, b.color);
            case King k -> King.of(to, k.color);
            case Queen q -> Queen.of(to, q.color);
        };
    }
    default Map<Direction, List<Position>> validPosition() {
        return switch (this) {
            case Pawn p -> p.validPosition();
            case Rook r -> r.validPosition();
            case Knight k -> k.validPosition();
            case Bishop b -> b.validPosition();
            case King k -> k.validPosition();
            case Queen q -> q.validPosition();
        };
    }
}
