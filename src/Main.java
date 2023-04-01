import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Choose your tic tac toe marker x or o: ");

        Game.Marker marker = Game.Marker.valueOf(scanner.nextLine());

        System.out.println("Choose who your opponent(1 for easy AI, 2 for intelligent AI, 3 for Human V Human): ");
        int userChoose = scanner.nextInt();
        scanner.nextLine();


        Game game = new Game(3);
        Player player = new Player(marker, name);
        Player opponent = null;
        if (userChoose == 1) {
            if (marker == Game.Marker.x) {
                opponent = new EasyAiPlayer(Game.Marker.o);
            } else {
                opponent = new EasyAiPlayer(Game.Marker.x);
            }
        } else if (userChoose == 2) {
            if (marker == Game.Marker.x) {
                opponent = new HardAiPlayer(Game.Marker.o);
            } else {
                opponent = new HardAiPlayer(Game.Marker.x);
            }
        } else if (userChoose == 3) {
            System.out.println("Enter the name of the Player 2: ");
            String opponentName = scanner.nextLine();
            System.out.println("Choose the symbol for the other player (x or o): ");
            Game.Marker opponentMarker = Game.Marker.valueOf(scanner.nextLine());
            opponent = new Player(opponentMarker, opponentName);
        } else {
            System.out.println("error");
        }

        System.out.println("Who goes first (1 for " + name + ", 2 for " + opponent.getName() + "): ");
        int turnChoose = scanner.nextInt();
        scanner.nextLine();
        Player turn = turnChoose == 1 ? player : opponent;
        System.out.println();

        while (game.getWinner()== null && !game.isBoardFull()) {
            System.out.println(game);
            if (turn == player) {
                System.out.println("Your turn, " + name);
                System.out.println("Enter empty horizantal row (0-2): ");
                int row = scanner.nextInt();
                System.out.println("Enter vertical column (0-2): ");
                int column = scanner.nextInt();
                if (game.placeMarker(marker, row, column)) {
                    if (game.checkBoard()) {
                        break;
                    }
                    turn = opponent;
                } else {
                    System.out.println("Invalid move, try again.");
                }
            } else if (turn instanceof EasyAiPlayer) {
                System.out.println("Random Ai  turn");
                ((EasyAiPlayer) turn).makeMove(game);
                if (game.checkBoard()) {
                    break;
                }
                turn = player;
            } else if (turn instanceof HardAiPlayer) {
                System.out.println("Intelligent AI's turn");
//                ((HardAiPlayer) currTurn).makeMove(game);
                if (game.checkBoard()) {
                    break;
                }
                turn = player;
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
                    turn = player;
                } else {
                    System.out.println("Move invalid");
                }
            }
        }
        if (game.getWinner() == null) {
            System.out.println("Game tied");
        } else {
            System.out.println((game.getWinner() == marker?name : opponent.getName()) + " wins :D");
            System.out.println(game);
        }
    }
}
