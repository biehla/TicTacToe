import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Choose your marker(x or o): ");

        Game.Marker marker = Game.Marker.valueOf(scanner.nextLine());

        System.out.println("Choose who your opponent(1 for easy AI, 2 for intelligent AI, 3 for Human V Human): ");
        int userChoose = scanner.nextInt();
        scanner.nextLine();


        Game game = new Game(3);
        Player player = new Player(marker, name);
        Player opponent = null;
        if (userChoose == 1) {
            opponent = new EasyAiPlayer(marker== Game.Marker.x ? Game.Marker.o : Game.Marker.x);
        } else if (userChoose == 2) {
            opponent = new HardAiPlayer(marker == Game.Marker.x ? Game.Marker.o : Game.Marker.x);
        } else if (userChoose == 3) {
            System.out.println("Enter the name of the Player 2: ");
            String secondPlayerName = scanner.nextLine();
            System.out.println("Choose the symbol for the other player (x or o): ");
            Game.Marker secondPlayerMarker = Game.Marker.valueOf(scanner.nextLine());
            opponent = new Player(secondPlayerMarker, secondPlayerName);
        } else {
            System.out.println("Invalid");
        }
        System.out.println("Who goes first (1 for " + name + ", 2 for " + opponent.getName() + "): ");
        int turnChoose = scanner.nextInt();
        scanner.nextLine();

<<<<<<< HEAD
        Game game = new Game(3);
        Player player = new Player(playerMarker, playerName);
        Player Ai;

        if (difficulty == 1) {
            if (playerMarker == Game.Marker.x) {
                Ai = new EasyAiPlayer(Game.Marker.o);
            }
            else {
                Ai = new EasyAiPlayer(Game.Marker.x);
            }
        }
        else {
            if (playerMarker == Game.Marker.x) {
                Ai = new HardAiPlayer(Game.Marker.o);
            }
            else {
                Ai = new HardAiPlayer(Game.Marker.x);
            }
        }

        Player currTurn = turnChoose == 1 ? player : Ai;
        System.out.println();

        while (game.getWinner() == null && !game.isBoardFull()) {
            System.out.println(game);
=======
        Player currTurn = turnChoose == 1 ? player : opponent;
        System.out.println();

        while (game.getWinner()== null && !game.isBoardFull()) {
>>>>>>> banu
            if (currTurn == player) {
                // player's turn
                System.out.println("Your turn, " + name);
                System.out.print("Enter empty horizantal row (0-2): ");
                int row = scanner.nextInt();
                System.out.println("Enter vertical column (0-2): ");
                int column = scanner.nextInt();
                if (game.placeMarker(marker, row, column)) {
                    if (game.checkBoard()) {
                        break;
                    }
                    currTurn = opponent;
                } else {
                    System.out.println("Invalid move, try again.");
                }
                // easy AI turn
            } else if (currTurn instanceof EasyAiPlayer) {

                System.out.println("Random Ai  turn");
                ((EasyAiPlayer) currTurn).makeMove(game);
                if (game.checkBoard()) {
                    break;
                }
                currTurn = player;
                // intelligent AI's turn
            } else if (currTurn instanceof HardAiPlayer) {
                System.out.println("Intelligent AI's turn");
//                ((HardAiPlayer) currTurn).makeMove(game);
                if (game.checkBoard()) {
                    break;
                }
                currTurn = player;
            } else {
                // other human player's turn
                System.out.println("Your turn, " + opponent.getName());
                System.out.println("Enter empty horizantal row (0-2): ");
                int row = scanner.nextInt();
                System.out.println("Enter vertical column (0-2): ");
                int column = scanner.nextInt();
                if (game.placeMarker(opponent.getMarker(), row, column)) {
                    if (game.checkBoard()) {
                        break;
                    }
                    currTurn = player;
                } else {
                    System.out.println("Move invalid");
                }
            }
            System.out.println(game);
        }
        if (game.getWinner() == null) {
            System.out.println("Game tied");
        } else {
            String wName = game.getWinner()==marker?name:opponent.getName();

            System.out.println(wName + " wins XD");
            System.out.println();
            System.out.println(game);
        }

    }
}