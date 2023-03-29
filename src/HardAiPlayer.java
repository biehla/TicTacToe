public class HardAiPlayer extends Player {
    public HardAiPlayer(Game.Marker marker) {
        super(marker, "The AI");
    }

    private int getNextOptimalMove(Game game) {
        boolean aiTurn = true;
        return 1;
    }

    private int genMove(Game game, int maxDepth, int curDepth, boolean aiTurn) {
        if (curDepth == maxDepth) {
//            return
        }
        maxDepth += 1;
        if (aiTurn) {

        }
        return 1;
    }
}