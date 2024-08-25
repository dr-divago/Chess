package org.chess.piece;

import io.vavr.collection.List;
import io.vavr.collection.Map;

public abstract sealed class ChessPiece permits Pawn, Knight, Bishop, Rook, Queen, King {
    protected final Position  position;
    protected final Color     color;
    protected final Direction direction;

    public ChessPiece(Position position, Color color) {
        this.position = position;
        this.color = color;
        this.direction = color == Color.WHITE ? Direction.UP : Direction.DOWN;
    }

    public Position position() {
        return position;
    }

    public abstract boolean isValidDirection(Direction direction);

    public Color color() {
        return color;
    }

    public ChessPiece move(Position to) {
        return switch (this) {
            case Pawn p -> Pawn.of(to, p.color);
            case Rook r -> Rook.of(to, r.color);
            case Knight k -> Knight.of(to, k.color);
            case Bishop b -> Bishop.of(to, b.color);
            case King k -> King.of(to, k.color);
            case Queen q -> Queen.of(to, q.color);
        };
    }
    public abstract Map<Direction, List<Position>> validPosition();

    /*
    Get all valid direction of the piece, calculate the direction from piece and the position 'to'
    if the direction is not in the set of possible direction of the piece return false
    otherwise find the list of valid position according to that direction and check if position to is
    contained in that list

    for example Q move in all direction up,down,left,right,up-left, up-right, down-left, down-right,
    the possible direction between Q and the Position should be in the direction calculation.
    Q(4,4) -> P(0, 4) have direction UP and validPosition (3, 4), (2,4), (1,4), (0,4).
     */
    public boolean canMoveTo(Position to) {
        Map<Direction, List<Position>> directionListMap = validPosition();
        Direction direction = position().direction(to);

        if (!isValidDirection(direction))
            return false;

        List<Position> validPositions = directionListMap.get(direction).get();
        return validPositions.contains(to);
    }
}
