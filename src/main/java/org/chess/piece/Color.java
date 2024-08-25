package org.chess.piece;

public enum Color {
    WHITE, BLACK, EMPTY;

    public Color opposite() {
        return switch(this) {
            case WHITE -> BLACK;
            case BLACK -> WHITE;
            case EMPTY ->  EMPTY;
        };
    }
}
