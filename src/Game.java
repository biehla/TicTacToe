import java.util.Arrays;

public class Game {
    public enum Marker {
        none,
        x,
        o
    }

    private enum boardScanMode {
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
        if (board[index] != Marker.none) return false;
        board[index] = player;
        return true;
    }

    public boolean placeMarker(Marker player, int index) {
        if (board[index] != Marker.none) return false;
        board[index] = player;
        return true;
    }


    public Marker getMarkerAtCoords(int x, int y) {
        return board[toIndex(boardScanMode.horizontal, x, y)];
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
        // Row loop lets us check one 'row' at a time
        mainLoop: for (int x = 0; x < boardSize; x++) {
            Marker lastMarker = board[toIndex(horizOrVertical, x, 0)];
            if (lastMarker == Marker.none) {  // we know that we can immediately disregard a row if a blank tile exists
                continue;
            }
            for (int y = 1; y < boardSize; y++) {
                Marker curMarker = board[toIndex(horizOrVertical, x, y)];
                if (curMarker != lastMarker) {
                    continue mainLoop;
                }
                lastMarker = curMarker;
            }
            // Todo: internal winner variable
            return true;
        }
        return false;
    }


    private boolean checkDiagLines() {
        Marker lastMarker = board[0];
        boolean isWinner = true;

        for (int i = 0; i < boardSize; i += boardSize + 1) {
            Marker curMarker = board[i];
            if (curMarker != lastMarker) {
                isWinner = false;
                break;
            }
            lastMarker = curMarker;
        }
        if (isWinner) {
            winner = lastMarker;
            return true;
        }

        isWinner = true;
        lastMarker = board[boardSize-1];

        for (int i = boardSize - 1; i < boardSize; i += boardSize + 1) {
            Marker curMarker = board[i];
            if (curMarker != lastMarker) {
                isWinner = false;
                break;
            }
            lastMarker = curMarker;
        }
        if (isWinner) {
            winner = lastMarker;
            return true;
        }

        return false;
    }
    public boolean isBoardFull() {
        for (Marker marker : board) {
            if (marker == Marker.none) {
                return false;
            }
        }
        return true;
    }

    public int[] positionsAvailable() {
        int[] freeLocations = new int[board.length];
        int indexCounter = 0;

        for (int i = 0; i < board.length; i++) {
            if (board[i] == Marker.none) {
                freeLocations[indexCounter] = i;
                indexCounter++;
            }
        }
        freeLocations[indexCounter] = -1; // -1 used to indicate the end of the array

        return freeLocations;
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        var divider = dividerGenerator();

        for (int i = 0; i < board.length; i++) {
            if (i % boardSize == 0) {
                boardString.append("\n").append(divider).append("\n| ");
            }

            char boardChar = switch (board[i]) {
                case x -> 'X';
                case o -> 'O';
                default -> ' ';
            };

            boardString.append(boardChar).append(" | ");

//            if (i != 0 && i % boardSize - 1 == 0) {
//                boardString.append(" |");
//            }
        }
        boardString.append("\n").append(divider);

        return boardString.toString();
    }

    private String dividerGenerator() {
        String divider = "+---";
        StringBuilder boardString = new StringBuilder();

        for (int i = 0; i < boardSize; i++) {
            boardString.append(divider);
        }

        return boardString.append("+").toString();
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