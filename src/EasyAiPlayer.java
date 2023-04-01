import java.util.Random;

public class EasyAiPlayer extends Player {
    public EasyAiPlayer(Game.Marker marker) {
        super(marker, "The AI");
    }

    public void makeMove(Game game) {
        int x, y;
        int limit = game.getBoardSize();

        Random random = new Random();

        do {
            x = random.nextInt(limit);
            y = random.nextInt(limit);
        } while (super.makeMove(game, x, y));
        // if spot is occupied
        // if taken, then take another one
    }
}
