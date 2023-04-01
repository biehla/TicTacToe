public class HardAiPlayer extends Player {
    Game.Marker enemyMarker;
    public HardAiPlayer(Game.Marker marker) {
        super(marker, "The AI");
        enemyMarker = (marker == Game.Marker.x) ? Game.Marker.o : Game.Marker.x;
    }

    private int getNextOptimalMove(Game game) {
        boolean aiTurn = true;
        return 1;
    }

    private int genMove(Game game) {
        int numOptions = game.getBoard().length - getNumOccupiedSpots(game);

        int nodesNeeded = calcNumNodesNeeded(numOptions, 0);
        int[] nodes = new int[nodesNeeded];

        int numOfLeafNodes = calcNumOfLeafNodes(game.getBoardSize());
        int leafNodeStartPos = nodesNeeded - numOfLeafNodes;

        // create the tree



        for (int i = leafNodeStartPos; i < nodesNeeded; i++) {
            // TODO: start working up the tree
        }

        return 1;
    }

    private int getNumOccupiedSpots(Game game) {
        Game.Marker[] board = game.getBoard();
        int occupiedSpots = 0;

        for (var marker: board) {
            if (marker != Game.Marker.none) {
                occupiedSpots++;
            }
        }

        return occupiedSpots;
    }

    private int calcNumOfLeafNodes(int numNodes) {
        if (numNodes != 1) {
            calcNumOfLeafNodes(numNodes - 1);
        }
        return calcNumOfLeafNodes(numNodes);
    }

    private int calcNumNodesNeeded(int numNodes, int nodeCount) {
        if (numNodes != 0) {
            nodeCount += numNodes;
            for (int i = 0; i < nodeCount; i++) {
                nodeCount += calcNumNodesNeeded(numNodes - 1, nodeCount);
            }
        }
        return nodeCount + 1;
    }
}

//    def calcNumNodesNeeded(numNodes, nodeCount=0):
//        if numNodes == 0:
//        return 0
//
//        nodeCount += numNodes
//        for i in range(numNodes):
//        return nodeCount + calcTree(numNodes - 1, nodeCount)
