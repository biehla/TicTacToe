public class Player {
    private final Game.Marker marker;
    private final String name;

    public Player(Game.Marker marker, String name) {
        this.marker = marker;
        this.name = name;
    }

    public void makeMove(Game game, int x, int y) {
        game.placeMarker(marker, x, y);
    }

    public Game.Marker getMarker() {
        return marker;
    }

    public String getName() {
        return name;
    }
}
