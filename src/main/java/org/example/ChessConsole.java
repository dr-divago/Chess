package org.example;

import com.whitehatgaming.UserInput;
import com.whitehatgaming.UserInputFile;
import io.vavr.control.Try;

import java.io.IOException;
import java.util.Objects;

public class ChessConsole {

    private ChessGame game;
    private String fileMoves;

    public ChessConsole(String fileMoves) {
        this.game = new ChessGame();
        this.fileMoves = fileMoves;
    }
    public static void main(String[] args) throws IOException {
        ChessConsole chessConsole = new ChessConsole("sample-moves-invalid.txt");
        chessConsole.play();
    }

    public void play() throws IOException {
        System.out.println("Playing chess");
        System.out.println("Initial board");
        String filePath = Objects.requireNonNull(ChessConsole.class.getClassLoader().getResource(fileMoves)).getPath();
        UserInput userInput = new UserInputFile(filePath);
        int[] nextMove = userInput.nextMove();
        while (nextMove != null) {
            System.out.println("Next move: " + nextMove[0] + nextMove[1] + nextMove[2] + nextMove[3]);
            Position from = Position.of(nextMove[1], nextMove[0]);
            Position to = Position.of(nextMove[3], nextMove[2]);

            System.out.println(game);
            if (game.isCheck(game.getCurrentTurn())) {
                System.out.println(game.getCurrentTurn() + " is in check!");
            }

            Try<ChessGame> result = game.move(from, to);
            if (result.isSuccess()) {
                game = result.get();
            } else {
                System.out.println("Invalid move: " + result.getCause().getMessage());
            }
            System.out.println(game);
            nextMove = userInput.nextMove();
        }
    }
}
