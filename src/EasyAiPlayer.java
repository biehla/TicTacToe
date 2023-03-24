import java.util.Random;

public class EasyAiPlayer extends Player {
    public EasyAiPlayer(Game.Marker marker) {
        super(marker, "The AI");
    }

    public void makeMove(Game game) {
        int x, y;
        int limit = game.getBoardSize();

        Random random = new Random();
        x = random.nextInt(limit);
        y = random.nextInt(limit);

        super.makeMove(game, x, y);
    }
}
