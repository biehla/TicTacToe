import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        System.out.print("Choose your symbol (x or o): ");
        Game.Marker playerMarker = Game.Marker.valueOf(scanner.nextLine());

        System.out.print("Choose difficulty (1 for the Easy Ai, 2 for for the Intelligent): ");
        int difficulty = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Choose who goes first (1 for Player, 2 for Ai: ");
        int turnChoose = scanner.nextInt();
        scanner.nextLine();

        Game game = new Game(3);
        Player player = new Player(playerMarker, playerName);
        Player Ai = difficulty == 1 ? new EasyAiPlayer(playerMarker == Game.Marker.x ? Game.Marker.o : Game.Marker.x)
                : new HardAiPlayer(playerMarker == Game.Marker.x ? Game.Marker.o : Game.Marker.x);

        Player currTurn = turnChoose == 1 ? player : Ai;
        System.out.println();

        while (game.getWinner() == null && !game.isBoardFull()) {
            if (currTurn == player) {
                System.out.println("Your turn, " + playerName + "!");
                System.out.print("Enter row: ");
                int row = scanner.nextInt();
                System.out.print("Enter column: ");
                int col = scanner.nextInt();
                if (game.placeMarker(playerMarker, row, col)) {
                    currTurn = Ai;
                } else {
                    System.out.println("Invalid move, try again.");
                }
            } else {
                System.out.println("Ai Turne");
//                Ai.makeMove(game); error
                currTurn = player;
            }
            System.out.println(game);
        }

        if (game.getWinner() == null) {
            System.out.println("Game ends in tie");
        } else {
            String winName = game.getWinner() == playerMarker ? playerName : currTurn.getName();
            System.out.println(winName + " wins");
        }

        System.out.println();
        System.out.println(game);
    }
}
