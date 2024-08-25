package org.chess.game;


import io.vavr.collection.List;
import org.chess.piece.Color;
import org.chess.piece.Direction;
import org.chess.piece.Position;
import org.chess.piece.*;

import io.vavr.Tuple2;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Option;

public class Board {
    private final Map<Position, ChessPiece> board;

    public Board(Map<Position, ChessPiece> board) {
        this.board = board;
    }

    public static Board init() {
        Map<Position, ChessPiece> board = HashMap.empty();
        board = board.put(Position.of(0, 0), Rook.of(Position.of(0, 0), Color.BLACK))
                     .put(Position.of(0, 1), Knight.of(Position.of(0, 1), Color.BLACK))
                     .put(Position.of(0, 2), Bishop.of(Position.of(0, 2), Color.BLACK))
                     .put(Position.of(0, 3), Queen.of(Position.of(0, 3), Color.BLACK))
                     .put(Position.of(0, 4), King.of(Position.of(0, 4), Color.BLACK))
                     .put(Position.of(0, 5), Bishop.of(Position.of(0, 5), Color.BLACK))
                     .put(Position.of(0, 6), Knight.of(Position.of(0, 6), Color.BLACK))
                     .put(Position.of(0, 7), Rook.of(Position.of(0, 7), Color.BLACK))
                     .put(Position.of(1, 0), Pawn.of(Position.of(1, 0), Color.BLACK))
                     .put(Position.of(1, 1), Pawn.of(Position.of(1, 1), Color.BLACK))
                     .put(Position.of(1, 2), Pawn.of(Position.of(1, 2), Color.BLACK))
                     .put(Position.of(1, 3), Pawn.of(Position.of(1, 3), Color.BLACK))
                     .put(Position.of(1, 4), Pawn.of(Position.of(1, 4), Color.BLACK))
                     .put(Position.of(1, 5), Pawn.of(Position.of(1, 5), Color.BLACK))
                     .put(Position.of(1, 6), Pawn.of(Position.of(1, 6), Color.BLACK))
                     .put(Position.of(1, 7), Pawn.of(Position.of(1, 7), Color.BLACK))
                     .put(Position.of(6, 0), Pawn.of(Position.of(6, 0), Color.WHITE))
                     .put(Position.of(6, 1), Pawn.of(Position.of(6, 1), Color.WHITE))
                     .put(Position.of(6, 2), Pawn.of(Position.of(6, 2), Color.WHITE))
                     .put(Position.of(6, 3), Pawn.of(Position.of(6, 3), Color.WHITE))
                     .put(Position.of(6, 4), Pawn.of(Position.of(6, 4), Color.WHITE))
                     .put(Position.of(6, 5), Pawn.of(Position.of(6, 5), Color.WHITE))
                     .put(Position.of(6, 6), Pawn.of(Position.of(6, 6), Color.WHITE))
                     .put(Position.of(6, 7), Pawn.of(Position.of(6, 7), Color.WHITE))
                     .put(Position.of(7, 0), Rook.of(Position.of(7, 0), Color.WHITE))
                     .put(Position.of(7, 1), Knight.of(Position.of(7, 1), Color.WHITE))
                     .put(Position.of(7, 2), Bishop.of(Position.of(7, 2), Color.WHITE))
                     .put(Position.of(7, 3), Queen.of(Position.of(7, 3), Color.WHITE))
                     .put(Position.of(7, 4), King.of(Position.of(7, 4), Color.WHITE))
                     .put(Position.of(7, 5), Bishop.of(Position.of(7, 5), Color.WHITE))
                     .put(Position.of(7, 6), Knight.of(Position.of(7, 6), Color.WHITE))
                     .put(Position.of(7, 7), Rook.of(Position.of(7, 7), Color.WHITE));

        return new Board(board);
    }

    public boolean isValidMove(Position from, Position to) {
            return board.get(from)
                    .map(chessPiece -> canMoveTo(chessPiece, to))
                    .getOrElse(false);
    }

    private Boolean canMoveTo(ChessPiece chessPiece, Position to) {
        return switch (chessPiece) {
            case Pawn p  -> isValidPawn(p, to);
            case Knight k -> isValidKnight(k, to);
            case Bishop b -> validChessPiece(b, to);
            case Queen q -> validChessPiece(q, to);
            case King k -> validChessPiece(k, to);
            case Rook r -> validChessPiece(r, to);
        };
    }

    private boolean validChessPiece(ChessPiece cp, Position to) {
        if (!cp.canMoveTo(to))
            return false;

        return noPieceBetween(cp, to);
    }

    public boolean noPieceBetween(ChessPiece cp, Position to){
        Direction direction = cp.position().direction(to);
        Position moveOneSquareTo = cp.position().moveToDirection(direction);
        while (!moveOneSquareTo.equals(to)) {
            if (!board.get(moveOneSquareTo).isEmpty())
                return false;
            moveOneSquareTo = moveOneSquareTo.moveToDirection(direction);
        }

        return true;
    }

    private boolean isValidKnight(Knight k, Position to) {
        Map<Direction, List<Position>> directionListMap = k.validPosition();
        List<Position> validPositions = directionListMap
                .flatMap(x -> x._2)
                .toList();
        if (!validPositions.contains(to))
            return false;

        return true;
    }

    private boolean isValidPawn(Pawn pawn, Position to) {
        if (pawn.isMovingVertically(to)) {
            if (pawn.isMovingOneSquare(to) && board.get(to).isEmpty()) {
                return true;
            }
            return pawn.isMovingTwoSquare(to)
                    && pawn.isStartingPosition()
                    && board.get(to).isEmpty()
                    && board.get(pawn.oneSquareFront()).isEmpty();

        }
        else if (pawn.isMovingDiagonally(to)) {
            return board.get(to)
                        .map(piece -> piece.color() != pawn.color())
                        .getOrElse(false);
        }
        return false;
    }

    public Board movePiece(Position from, Position to) {
        ChessPiece piece = board.get(from).get();
        Map<Position, ChessPiece> newBoard = board.remove(from).put(to, piece.move(to));
        return new Board(newBoard);
    }

    public Color getColorInPosition(Position position) {
        Option<ChessPiece> pieces = board.get(position);
        if (pieces.isDefined()) {
            return pieces.get().color();
        }
        return Color.EMPTY;
    }

    public String get(Position position) {
        return board.get(position).isDefined() ? board.get(position).get().toString() : " ";
    }

    public boolean isCheck(Color color) {
        Position kingPosition = findKing(color);
        return getAllPieceByColor(color.opposite())
                .exists(position -> isValidMove(position, kingPosition));
    }

    public List<Position> getAllPieceByColor(Color color) {
        return board.filter(entry -> entry._2.color() == color).map(Tuple2::_1).toList();
    }

    private Position findKing(Color color) {
        return board.find(entry -> entry._2 instanceof King && entry._2.color() == color)
                    .map(Tuple2::_1)
                    .getOrElseThrow(() -> new IllegalStateException("King not found"));
    }
}
