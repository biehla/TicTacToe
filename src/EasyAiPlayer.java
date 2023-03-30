
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


        while (game.getBoard()[game.toIndex(Game.boardScanMode.horizontal, x, y)] !=Game.Marker.none) {// if spot is occupied
            // if taken take another one
            x = random.nextInt(limit);
            y = random.nextInt(limit);
        }

       this.makeMove(game, x, y);
    }
}
