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
        if (board[index] != Marker.none) {
            return false; // Spot is already taken, return false
        }
        board[index] = player;
        return true;
    }


    public boolean placeMarker(Marker player, int index) {
        if (board[index] != Marker.none) return false;
        board[index] = player;
        return true;
    }

    public void removeMarker(Marker player, int index) {
        if (index < 0 || index >= board.length) {
            System.out.println("BRUH SOMETHING WENT WRONG. GO FIX YOUR CODE");
        }
        if (board[index] != player) {
            System.out.println("Uh... Wrong player");
        }

        board[index] = Marker.none;
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

}
