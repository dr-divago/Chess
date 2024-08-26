### Install lib local repo
```bash
mvn install:install-file -Dfile={project.root}/libs/userinput.jar -DgroupId=com.whitegaming -DartifactId=userinput -Dversion=1.0 -Dpackaging=jar
```
### Build Application
```bash
./mnv clean install
```
### Run Application
Run the application
```bash
java -jar target/chess-fatjar.jar $path-file/{file-with-moves.txt}
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
Missing some tests and more validation for edge cases. 