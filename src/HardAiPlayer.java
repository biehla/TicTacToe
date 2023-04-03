public class HardAiPlayer extends Player {
    Game.Marker enemyMarker;

    public HardAiPlayer(Game.Marker marker) {
        super(marker, "The AI");
        enemyMarker = (marker == Game.Marker.x) ? Game.Marker.o : Game.Marker.x;
    }

    public void makeMove(Game game) {
        int result = minimax(game, this.getMarker(), Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
        game.placeMarker(this.getMarker(), result);
    }

    private int minimax(Game game, Game.Marker curPlayer, int maxScore, int minScore, int depth) {
        if (game.getWinner() == this.getMarker()) {
            return 1;
        }
        else if (game.getWinner() == this.enemyMarker) {
            return -1;
        }
        else if (game.isBoardFull()) {
            return 0;
        }

        int[] positions = game.positionsAvailable();
        boolean isAI = curPlayer == this.getMarker();
        int bestRating =  isAI ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int bestPosition = -1;
        for (int i = 0; i < positions.length; i++) {
            if (positions[i] == -1) break;

            Game.Marker nextMarker = isAI ? this.enemyMarker : this.getMarker();
            game.placeMarker(curPlayer, positions[i]);
            int rating = minimax(game, nextMarker, maxScore, minScore, depth + 1);
            game.removeMarker(curPlayer, positions[i]);

            if (isAI) {
                if (rating > bestRating) {
                    bestPosition = positions[i];
                }

                bestRating = Math.max(bestRating, rating);
                maxScore = Math.max(maxScore, bestRating);
            }
            else {
                if (rating < bestRating) {
                    bestPosition = positions[i];
                }
                bestRating = Math.min(bestRating, rating);
                minScore = Math.min(minScore, bestRating);
            }

//            if (minScore <= maxScore) {
//                break;
//            }
        }

        if (depth != 0) {
            return bestRating;
        }
        return bestPosition;
    }
}