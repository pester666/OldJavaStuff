package allsrc.ch15src.src;

import java.io.Serializable;

public class ChessGameState implements Serializable {

private ChessBoard theBoard;
private ChessPlayer white;
private ChessPlayer black;
private int fiftyMovesCounter;

}

class ChessPlayer implements Serializable {
    private String name;
}

class ChessBoard implements Serializable {
    // private ChessPiece[][] squares;
}
