public class VisualizerDemo{
    public static void main(String[] args) throws Exception {
        Map map = new Map(Const.MAP);
        MapSolver mapSolver = new MapSolver(map);

        map.readMapFromFile(); // Initally reading map from file and storing it in char[][] array

        mapSolver.placeRandomStartingPosition(map); // Randomly place starting position in map
        mapSolver.placeRandomEndingPosition(map); // Randomly place finishing position in map

        Visualizer v = new Visualizer(map);

        // Once the visualizer is instantiated it automatically updates as per the map content
        for (int count = 0; count < 10; count++) {
            map.setMap(0,0,'x');
            Thread.sleep(100);
            map.setMap(0,0,' ');
            Thread.sleep(100);
        }

        // Solving maze
        if (mapSolver.solve(map, map.getStartingRow(), map.getStartingCol())) {
            System.out.println("SOLVED || TOOK " + map.getPathCount() + " SQUARES TO FINISH");
        } else {
            System.out.println("NOT SOLVED");
        }
        
        map.printMap();
    }
}