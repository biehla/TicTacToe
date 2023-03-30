import java.util.Arrays;

public class Game {
    public enum Marker {
        none,
        x,
        o
    }

    public enum boardScanMode {
        horizontal,
        vertical
    }

    private final Marker[] board;
    private final int boardSize;
    private Marker winner;


    public Game(int boardSize) {
        this.boardSize = boardSize;
        board = new Marker[boardSize * boardSize];
        Arrays.fill(board, Marker.none);
    }


public boolean placeMarker(Marker player, int x, int y) {
    int index = toIndex(boardScanMode.horizontal, x, y);
    if (board[index] != Marker.none) {
        return false; // Spot is already taken, return false
    }
    board[index] = player;
    return true;
}



    public int toIndex(boardScanMode horizOrVertical, int x, int y) throws ArrayIndexOutOfBoundsException {
        int index = horizOrVertical == boardScanMode.horizontal ? 3 * x + y : x + 3 * y;
        if (index >= board.length) {
            String errorString = String.format("This index (%d) does not exist on the board", index);
            throw new ArrayIndexOutOfBoundsException(errorString);
        }
        return index;
    }


    public boolean checkBoard() {
        return checkDiagLines() ||
                checkLines(boardScanMode.vertical) ||
                checkLines(boardScanMode.horizontal);
    }

    public Marker getWinner() {
        return winner;
    }


    public Marker[] getBoard() {
        return board;
    }

    public int getBoardSize() {
        return boardSize;
    }

    private boolean checkLines(boardScanMode horizOrVertical) {
        mainLoop: for (int x = 0; x < boardSize; x++) {
            Marker lastMarker = board[toIndex(horizOrVertical, x, 0)];
            if (lastMarker == Marker.none) {
                continue;
            }
            for (int y = 1; y < boardSize; y++) {
                Marker curMarker = board[toIndex(horizOrVertical, x, y)];
                if (curMarker != lastMarker) {
                    continue mainLoop;
                }
                lastMarker = curMarker;
            }

            winner = lastMarker;
            return true;
        }
        return false;
    }


    private boolean checkDiagLines() {
        Marker leftToRight = board[toIndex(boardScanMode.horizontal, 0, 0)];
        boolean isLeftToRightWinner = true;
        for (int i = boardSize + 1; i < board.length; i += boardSize + 1) {
            Marker marker = board[i];
            if (marker == Marker.none || marker != leftToRight) {
                isLeftToRightWinner = false;
                break;
            }
        }
        if (isLeftToRightWinner) {
            winner = leftToRight;
            return true;
        }

        Marker rightToLeft = board[toIndex(boardScanMode.horizontal, 0, boardSize - 1)];
        boolean isRightToLeftWinner = true;
        for (int i = boardSize - 1; i < board.length - boardSize; i += boardSize - 1) {
            Marker marker = board[i];
            if (marker == Marker.none || marker != rightToLeft) {
                isRightToLeftWinner = false;
                break;
            }
        }
        if (isRightToLeftWinner) {
            winner = rightToLeft;
            return true;
        }

        return false;
    }

    public boolean isBoardFull() {
        int noneCount = 0;
        for (int i = 0; i<board.length; i++) {
            if (board[i] == Marker.none) {
                noneCount++;
            }
        }
        return noneCount == 0;
    }
    //making visual board
    public String toString() {
        String box = "----------------------\n";
        StringBuilder sb = new StringBuilder();
        sb.append(box);
        for (int i = 0; i < boardSize; i++) {
            sb.append("| ");
            for (int x = 0; x < boardSize; x++) {
                sb.append(String.format("%s | ", board[toIndex(boardScanMode.horizontal,i,x)] == Marker.none
                        ? " " : board[toIndex(boardScanMode.horizontal,i,x)].toString()));
            }
            sb.append("\n" + box);
        }
        return sb.toString();
    }





//    public static void main(String[] args) {
//        Game game = new Game(3);
//        game.placeMarker(Marker.x, 0, 0);
//        game.placeMarker(Marker.x, 1, 0);
//        game.placeMarker(Marker.x, 2, 0);
//        boolean gameResult = game.checkBoard();
//        System.out.println(gameResult);
//        if (gameResult) {
//            System.out.println(game.getWinner());
//        }
//    }
}