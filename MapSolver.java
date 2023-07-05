import java.util.ArrayList;

/**
 * MapSolver for Mall Navigator
 * @author ICS4UE
 * @version Oct 2022
 */

public class MapSolver {
    private Map map;
//------------------------------------------------------------------------------------
    public MapSolver(Map map) {
        this.map = map;
    }
//------------------------------------------------------------------------------------
    /**
     * Method that randomly generates an integer between a set interval
     * 
     * @param min number in the interval
     * @param max number in the interval
     * @return the random number
     */
    public int generateRandomNum(int min, int max) {
        int randomNum = (int)(Math.random() * (max - min + 1) + min);
        return randomNum;
    }

    /**
     * Method that randomly generates a valid starting position using criteria
       that matches where the starting position should be
     * 
     * @return array that consists of the randomly generated starting row and column
     */
    public int[] selectRandomStartingPosition() {
        ArrayList<Integer> rows = new ArrayList<Integer>(); // Creating ArrayList of rows that match criteria to be connecting to the rest of the map
        ArrayList<Integer> cols = new ArrayList<Integer>(); // Creating ArrayList of columns that match criteria to be connecting to the rest of the map
        for (int row = 0; row < map.getNumRows(); row++) { 
            for (int col = 0; col < map.getNumCols(); col++) {
                if (map.getMap(row, col) == Const.ALLEY) {
                    rows.add(row); // Adding all rows that match criteria into array of rows
                    cols.add(col); // Adding all columns that match criteria into array of columns
                }
            }
        }
        int randomRowAndColIndex = generateRandomNum(0, rows.size() - 1); // Calling upon generateRandomNum() method to pick a random starting row and col #
        int randomStartingRow = rows.get(randomRowAndColIndex); // Randomly selecting a row number to use
        int randomStartingCol = cols.get(randomRowAndColIndex); // Selecting the matching column with the row
        int[] randomRowAndCol = {randomStartingRow, randomStartingCol}; // Creating an array of the row, column
        return randomRowAndCol;
    }

    /**
     * Method that randomly generates a valid ending position using criteria
       that matches where the ending position should be
     * 
     * @return array that consists of the randomly generated ending row and column
     */
    public int[] selectRandomEndingPosition() {
        ArrayList<Integer> rows = new ArrayList<Integer>(); // Creating ArrayList of rows that match criteria to be connecting to the rest of the map
        ArrayList<Integer> cols = new ArrayList<Integer>(); // Creating ArrayList of columns that match criteria to be connecting to the rest of the map
        for (int row = 0; row < map.getNumRows(); row++) { 
            for (int col = 0; col < map.getNumCols(); col++) {
                if (map.getMap(row, col) == Const.ALLEY) {
                    rows.add(row); // Adding all rows that match criteria into array of rows
                    cols.add(col); // Adding all columns that match criteria into array of columns
                }
            }
        }
        int randomRowAndColIndex = generateRandomNum(0, rows.size() - 1); // Calling upon generateRandomNum() method to pick a random ending row and col #
        int randomEndingRow = rows.get(randomRowAndColIndex); // Randomly selecting a row number to use
        int randomEndingCol = cols.get(randomRowAndColIndex); // Selecting the matching column with the row
        int[] randomRowAndCol = {randomEndingRow, randomEndingCol}; // Creating an array of the row, column
        return randomRowAndCol;
    }

    /**
     * Method that places the randomly generated starting position
     * 
     * @param map that is being adjusted
     */
    public void placeRandomStartingPosition(Map map) {
        int[] randomRowAndCol = selectRandomStartingPosition();
        map.setMap(randomRowAndCol[0], randomRowAndCol[1], Const.START);
    }

    /**
     * Method that places the randomly generated ending position
     * 
     * @param map that is being adjusted
     */
    public void placeRandomEndingPosition(Map map) {
        int[] randomRowAndCol = selectRandomEndingPosition();
        map.setMap(randomRowAndCol[0], randomRowAndCol[1], Const.END);
    }

    /**
      * Recursive method that locates viable points in the maze
        and moves towards them until it reaches the end

      * @param map that is being solved
      * @param row row to start solving from
      * @param col column to start solving from
      * @return whether or not the next square, in any direction, is able to be moved to
      */
    public boolean solve(Map map, int row, int col) { 
        if (row < 0 || col < 0 || row > map.getNumRows() - 1|| col > map.getNumCols() - 1) { 
            /*
             * Check for bounds, if row or column number is lower/higher than the
             * bounds of the map, the map is not possible to solve
             */
            return false;
        } 
        if (map.getMap(row , col) == Const.END) {
            /*
             * Check if at the finishing position, if so, 
             * return true that the maze is solved
             */
            return true;
        } 
        if (map.getMap(row, col) != Const.ALLEY && map.getMap(row, col) != Const.START && map.getMap(row, col) != Const.END) { 
            /*
             * Check if at a valid position, if position is not at the start or an alley, 
             * it must be in the wall which is not a viable position
             */
            return false; // Hit a wall, not a valid solution
        } 
        /*
         * If passes all these conditions (not out of bounds, is not the end position,
         * and is not a wall), then set the box to visited to mark that we have been at this position
         */
        if (map.getMap(row, col) != Const.START && map.getMap(row, col) != Const.END) {
            map.setMap(row, col, Const.VISIT);
            map.setPathCount(map.getPathCount() + 1); // Adding to the path count to show how many squares it took to find the end
        }
        if (solve(map, row - 1, col) || solve(map, row, col - 1) || // Check above location first, below location second,
            solve(map, row + 1, col) || solve(map, row, col + 1)) { // left location third, and finally right location fourth
            return true;                                            // To see if any of the locations are viable options to move to
        } else { 
            /*
             * If the none of the locations are valid, we are at a dead end
             * and we should reset the area we initally moved to, to alley 
             * because we did not take that route, so it is not visited
             */
			if (map.getMap(row, col) != Const.START && map.getMap(row, col) != Const.END) {
				map.setMap(row, col, Const.ALLEY);
			}
            /*
             * Remove to the path count to show how many squares
             * were lost from the solution when we found out the path was a dead end
             */
            map.setPathCount(map.getPathCount() - 1);
            return false;
        }
    }
}
