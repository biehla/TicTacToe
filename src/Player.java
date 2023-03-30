public class Player {
    public final Game.Marker marker;
    private final String name;

    public Player(Game.Marker marker, String name) {
        this.marker = marker;
        this.name = name;
    }

    public void makeMove(Game game, int x, int y) {
    if (game.getBoard()[game.toIndex(Game.boardScanMode.horizontal, x, y)] != Game.Marker.none) // Spot is already taken, marker can't go in spot
    {
        return;
    }
    game.placeMarker(marker, x, y);
}
    public Game.Marker getMarker() {
        return marker;
    }

    public String getName() {
        return name;
    }


}
