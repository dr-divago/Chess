package org.example;

import com.whitehatgaming.UserInput;
import com.whitehatgaming.UserInputFile;

import java.io.IOException;
import java.util.Objects;

public class ChessConsole {

    public static void main(String[] args) throws IOException {
        ChessConsole chessConsole = new ChessConsole();
        chessConsole.play();
    }

    public void play() throws IOException {
        Board board = Board.init();
        System.out.println("Playing chess");
        System.out.println("Initial board");
        System.out.println(board);
        String filePath = Objects.requireNonNull(Main.class.getClassLoader().getResource("sample-moves.txt")).getPath();
        UserInput userInput = new UserInputFile(filePath);
        int[] nextMove = userInput.nextMove();
        while (nextMove != null) {
            System.out.println("Next move: " + nextMove[0] + nextMove[1] + nextMove[2] + nextMove[3]);
            Position from = Position.of(nextMove[1], nextMove[0]);
            Position to = Position.of(nextMove[3], nextMove[2]);
            board = board.movePiece(from, to);
            System.out.println(board);
            nextMove = userInput.nextMove();
        }
    }
}
