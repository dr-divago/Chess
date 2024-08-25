package org.chess.display;

import org.chess.game.Board;
import org.chess.piece.Position;

public class ChessBoardDisplay {
    public static String print(Board board) {
        StringBuilder sb = new StringBuilder();

        sb.append("  +");
        for (int i = 0; i < 8; i++) {
            sb.append("-".repeat(9)).append("+");
        }
        sb.append("\n  |");
        for (int i = 0; i < 8; i++) {
            sb
                    .append(" ")
                    .append(padLeft(String.valueOf(i), 4))
                    .append(padRight(" ", 3))
                    .append(" |");
        }
        sb.append("\n");

        for (int i = 0; i < 8; i++) {
            sb.append("  +");
            for (int j = 0; j < 8; j++) {
                sb.append("-".repeat(9)).append("+");
            }
            sb.append("\n");

            sb.append(i).append(" |");
            for (int j = 0; j < 8; j++) {
                Position p = Position.of(i, j);
                sb
                        .append(" ")
                        .append(padLeft(board.get(p), 4))
                        .append(padRight(" ", 3))
                        .append(" |");
            }
            sb.append("\n");
        }

        sb.append("  +");
        for (int i = 0; i < 8; i++) {
            sb.append("-".repeat(9)).append("+");
        }
        sb.append("\n");
        return sb.toString();
    }

    private static String printHorizontalBorder(int cols, int width) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= cols; i++) {
            sb.append("   ").append("+").append("-".repeat(width + 2));
        }
        sb.append("+");
        return sb.toString();
    }

    private static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    private static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }
}
