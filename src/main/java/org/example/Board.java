package org.example;


import io.vavr.collection.List;
import org.example.piece.*;

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
                        .map(chessPiece -> validatePiece(chessPiece, to))
                        .getOrElse(false);
    }

    private Boolean validatePiece(ChessPiece chessPiece, Position to) {
        return switch (chessPiece) {
            case Pawn p -> isValidPawn(p, to);
            case Knight k -> isValidKnight(k, to);
            case Bishop b -> isValidBishop(b, to);
            case Queen q -> isValidQueen(q, to);
            case King k -> isValidKing(k, to);
            case Rook r -> isValidRock(r, to);
        };
    }

    private boolean isValidRock(Rook r, Position to) {
        Map<Direction, List<Position>> directionListMap = r.validPosition();

        //find direction from rook r -> position to
        Direction direction = r.position().direction(to);
        if (!r.isValidDirection(direction)) {
            return false;
        }
        List<Position> validPositions = directionListMap.get(direction).get();
        if (!validPositions.contains(to))
            return false;


        Position rook = r.position().moveToDirection(direction);
        while (!rook.equals(to)) {
            if (!board.get(rook).isEmpty())
                return false;
            rook = rook.moveToDirection(direction);
        }

        return true;
    }

    private boolean isValidKing(King k, Position to) {
        Map<Direction, List<Position>> directionListMap = k.validPosition();

        //find direction from king k -> position to
        Direction direction = k.position().direction(to);
        List<Position> validPositions = directionListMap.get(direction).get();
        if (!validPositions.contains(to))
            return false;

        return true;
    }

    private boolean isValidQueen(Queen q, Position to) {
        Map<Direction, List<Position>> directionListMap = q.validPosition();
        Direction direction = q.position().direction(to);

        if (!q.isValidDirection(direction)) {
            return false;
        }

        List<Position> validPositions = directionListMap.get(direction).get();
        if (!validPositions.contains(to))
            return false;

        Position queenPos = q.position().moveToDirection(direction);
        while (!queenPos.equals(to)) {
            if (!board.get(queenPos).isEmpty())
                return false;
            queenPos = queenPos.moveToDirection(direction);
        }

        return true;
    }

    private boolean isValidBishop(Bishop b, Position to) {
        Map<Direction, List<Position>> directionListMap = b.validPosition();
        Direction direction = b.position().direction(to);

        if (!b.isValidDirection(direction))
            return false;

        List<Position> validPositions = directionListMap.get(direction).get();
        if (!validPositions.contains(to))
            return false;

        Position bishopPos = b.position().moveToDirection(direction);
        while (!bishopPos.equals(to)) {
            if (!board.get(bishopPos).isEmpty())
                return false;
            bishopPos = bishopPos.moveToDirection(direction);
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Position position = Position.of(row, col);
                Option<ChessPiece> piece = board.get(position);
                if (piece.isDefined()) {
                    sb.append(piece.get().toString());
                } else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
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
