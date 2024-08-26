package org.chess.ui;

import com.whitehatgaming.UserInput;
import com.whitehatgaming.UserInputFile;
import io.vavr.control.Try;
import org.chess.game.ChessGame;
import org.chess.piece.Position;

import java.io.IOException;

public class ChessConsole {

    private ChessGame game;
    private final String fileMoves;

    public ChessConsole(String fileMoves) {
        this.game = new ChessGame();
        this.fileMoves = fileMoves;
    }
    public static void main(String[] args) throws IOException {
        if (args.length != 1)
            throw new IllegalArgumentException("Use java -jar ChessTest-1.0.jar {path-file.txt}");
        ChessConsole chessConsole = new ChessConsole(args[0]);
        chessConsole.play();
    }

    public void play() throws IOException {
        System.out.println("Playing chess");
        System.out.println("Initial board");
        System.out.println(game);
        UserInput userInput = new UserInputFile(fileMoves);
        int[] nextMove = userInput.nextMove();
        while (nextMove != null) {
            Position from = Position.of(nextMove[1], nextMove[0]);
            Position to = Position.of(nextMove[3], nextMove[2]);
            System.out.println("Next move from " + from + " to " + to + " for " + game.getCurrentTurn());

            Try<ChessGame> result = game.move(from, to);
            if (result.isSuccess()) {
                game = result.get();
                System.out.println("*** Valid Move ***");
                if (game.isCheck()) {
                    System.out.println(game.getCurrentTurn() + " King is in check");
                }
                System.out.println(game);
            } else {
                System.out.println("*** Invalid Move ***");
                System.out.println(result.getCause().getMessage());
                System.out.println("\n");
            }


            nextMove = userInput.nextMove();
        }
    }
}
