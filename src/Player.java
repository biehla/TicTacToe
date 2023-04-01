public class Player {
    private final Game.Marker marker;
    private final String name;

    public Player(Game.Marker marker, String name) {
        this.marker = marker;
        this.name = name;
    }

    public boolean makeMove(Game game, int x, int y) {
        return game.placeMarker(marker, x, y);
    }

    public Game.Marker getMarker() {
        return marker;
    }

    public String getName() {
        return name;
    }
}
