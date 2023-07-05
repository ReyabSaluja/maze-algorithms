import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Map for Mall Navigator
 * @author ICS4UE
 * @version Oct 2022
 */

public class Map {
    private int numRows = 11;
    private int numCols = 13;
    private char[][] map = new char[numRows][numCols];
    private int shortestLength=Integer.MAX_VALUE;
	private int length = 0;
	private boolean hasPath = false;

    private int pathCount = 0;
    private String fileName;
//------------------------------------------------------------------------------------
    public Map(String fileName) {
        this.fileName = fileName;
    }
//------------------------------------------------------------------------------------
    // Getters and Setters
    public int getNumRows() {
        return this.numRows;
    }
    public int getNumCols() {
        return this.numCols;
    }
    public char getMap(int row, int col) {
        return this.map[row][col];
    }
    public void setMap(int row, int col, char symbol) {
        this.map[row][col] = symbol;
    }
    public int getPathCount() {
        return this.pathCount;
    }
    public void setPathCount(int newPathCount) {
        this.pathCount = newPathCount;
    }
    public int getLength() {
        return this.length;
    }
    public void setLength(int newLength) {
        this.length = newLength;
    }
    public int getShortestLength() {
        return this.shortestLength;
    }
    public void setShortestLength(int newShortestLength) {
        this.shortestLength = newShortestLength;
    }
    public boolean hasPath() {
        return hasPath;
    }
    public void setHasPath(boolean newHasPath) {
        this.hasPath = newHasPath;
    }
    
    public void printMap() {
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < (map[0].length); c++) {
                System.out.print(map[r][c]);
            }
            System.out.println();
        }
    }
    public int getStartingRow() { // Getter method to locate the row number of the starting position in the maze
        for (int row = 0; row < this.numRows; row++) {
            for (int col = 0; col < this.numCols; col++) {
                if (this.map[row][col] == Const.START) {
                    return row;
                }
            }
        }
        return 0;
    }
    public int getStartingCol() { // Getter method to locate the column number of the starting position in the maze
        for (int row = 0; row < this.numRows; row++) {
            for (int col = 0; col < this.numCols; col++) {
                if (this.map[row][col] == Const.START) {
                    return col;
                }
            }
        }
        return 0;
    }
    public int getEndingRow() { // Getter method to locate the row number of the starting position in the maze
        for (int row = 0; row < this.numRows; row++) {
            for (int col = 0; col < this.numCols; col++) {
                if (this.map[row][col] == Const.END) {
                    return row;
                }
            }
        }
        return 0;
    }
    public int getEndingCol() { // Getter method to locate the column number of the starting position in the maze
        for (int row = 0; row < this.numRows; row++) {
            for (int col = 0; col < this.numCols; col++) {
                if (this.map[row][col] == Const.END) {
                    return col;
                }
            }
        }
        return 0;
    }
//------------------------------------------------------------------------------------
    public void readMapFromFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        int lines = 0, columns = 0;
        String str;
        List<String> lineList = new ArrayList<>();
        while ((str = br.readLine()) != null && str.length() != 0) {
            lines++;
            columns = Math.max(columns, str.length()); // as it's not fixed
            lineList.add(str);
        }
        System.out.println("Row : " + lines);
        System.out.println("Columns : " + columns);

        for (int i = 0; i < lines; i++) {
            String currentLine = lineList.get(i);
            int idx = 0;
            for (int j = 0; j < currentLine.length(); j++) {
                map[i][j] = currentLine.charAt(idx++);
            }
        }
    }
}