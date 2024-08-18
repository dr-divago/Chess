package org.example;


import org.example.piece.Bishop;
import org.example.piece.ChessPiece;
import org.example.piece.King;
import org.example.piece.Knight;
import org.example.piece.Pawn;
import org.example.piece.Queen;
import org.example.piece.Rook;

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
                     .put(Position.of(0, 1), new Knight(Position.of(0, 1), Color.BLACK))
                     .put(Position.of(0, 2), new Bishop(Position.of(0, 2), Color.BLACK))
                     .put(Position.of(0, 3), new Queen(Position.of(0, 3), Color.BLACK))
                     .put(Position.of(0, 4), new King(Position.of(0, 4), Color.BLACK))
                     .put(Position.of(0, 5), new Bishop(Position.of(0, 5), Color.BLACK))
                     .put(Position.of(0, 6), new Knight(Position.of(0, 6), Color.BLACK))
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
                     .put(Position.of(7, 1), new Knight(Position.of(7, 1), Color.WHITE))
                     .put(Position.of(7, 2), new Bishop(Position.of(7, 2), Color.WHITE))
                     .put(Position.of(7, 3), new Queen(Position.of(7, 3), Color.WHITE))
                     .put(Position.of(7, 4), new King(Position.of(7, 4), Color.WHITE))
                     .put(Position.of(7, 5), new Bishop(Position.of(7, 5), Color.WHITE))
                     .put(Position.of(7, 6), new Knight(Position.of(7, 6), Color.WHITE))
                     .put(Position.of(7, 7), Rook.of(Position.of(7, 7), Color.WHITE));

        return new Board(board);
    }

    public boolean isValidMove(Position from, Position to) {
            return getPiece(from)
                        .map(chessPiece -> validatePiece(chessPiece, to))
                        .getOrElse(false);
    }

    private Boolean validatePiece(ChessPiece chessPiece, Position to) {
        return switch (chessPiece) {
            case Pawn p -> isValidPawn(p, to);
            case Knight k -> isValidKnight(k);
            case Bishop b -> isValidBishop(b);
            case Queen q -> isValidQueen(q);
            case King k -> isValidKing(k);
            case Rook r -> isValidRock(r, to);
        };
    }

    private boolean isValidRock(Rook r, Position to) {
        if (r.position().col() != to.col() && r.position().row() != to.row()) return false;

        int rowStep = Integer.compare(r.position().col(), to.col());
        int colStep = Integer.compare(r.position().row(), to.row());

        return checkLinePath(r.position(), to, rowStep, colStep);
    }

    private boolean isValidKing(King k) {
        return true;
    }

    private boolean isValidQueen(Queen q) {
        return true;
    }

    private boolean isValidBishop(Bishop b) {
        return true;
    }

    private boolean isValidKnight(Knight k) {
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

    public Option<ChessPiece> getPiece(Position position) {
        return board.get(position);
    }

    public Board movePiece(Position from, Position to) {
        ChessPiece piece = board.get(from).get();
        Map<Position, ChessPiece> newBoard = board.remove(from).put(to, piece.move(to));
        return new Board(newBoard);
    }


    private boolean checkLinePath(Position from, Position to, int rowStep, int colStep) {
        Position current = Position.of(from.row() + rowStep, from.col() + colStep);
        while (!current.equals(to)) {
            if (board.get(current).isDefined()) return false;
            current = Position.of(current.row() + rowStep, current.col() + colStep);
        }
        return true;
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
        return board.values().exists(piece -> piece.isValidMove(kingPosition));
    }

    private Position findKing(Color color) {
        return board.find(entry -> entry._2 instanceof King && entry._2.color() == color)
                    .map(Tuple2::_1)
                    .getOrElseThrow(() -> new IllegalStateException("King not found"));
    }


}
