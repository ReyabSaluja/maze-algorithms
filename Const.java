import java.awt.Color;

public final class Const{
    // Tile Letters
    public static final char START = 'S';
    public static final char END = 'E';
    public static final char VISIT = 'V';
    public static final char WALL = ' ';
    public static final char ALLEY = 'O';
    public static final char NO_PATH = 'x';
    
    // Colors
    public static final Color START_COLOR = new Color (222,63,78,255);
    public static final Color END_COLOR = new Color (41, 98, 81, 255);
    public static final Color VISIT_COLOR = new Color (239,187,75,255);
    public static final Color WALL_COLOR = new Color (64, 64, 64);
    public static final Color ALLEY_COLOR = new Color (179, 179, 179);
    public static final Color NO_PATH_COLOR = new Color (158,160,163,255);

    public static final Color BACKGROUND_COLOR = new Color(18, 18, 18);

    // Maps
    public static final String MAP = "mapFiles/path.txt";

    // Others
    public static final int UI_HORIZONTAL_DISPLACEMENT = 100;
    public static final int UI_VERTICAL_DISPLACEMENT = 10;

    private Const(){
    }
}