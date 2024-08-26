### Build Application
```bash
./mnv clean build
```
### Run Application
Run the application
```bash
java -jar target/ChessTest-1.0.jar {moveFile.txt}
```
### Test Application
```bash
./mvn clean test
```
## Architecture choice

The chess game is modeled with sealed class and switch implemented in JDK 21. Choice was made considering the number of piece 
in the board are fixed and cannot be extended. This allow static dispatch implemented in ChessPiece base class and PieceLogic 
where all the logic of piece movement and validation is centralized and specialized piece logic is dispatched. Project
is implemented using vavr library to use a more functional style programming taking in consideration immutability and
referential transparency of the functions. 
The entry point is class ChessConsole that will execute the moves contains in the file txt
passed as input. 