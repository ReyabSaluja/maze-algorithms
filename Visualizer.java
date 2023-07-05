/**
 * Visualizer for Mall Navigator
 * @author ICS4UE
 * @version Oct 2022
 */

import javax.swing.*;
import java.awt.*;

public class Visualizer extends JFrame {
    Map map;
    MapPanel panel;
    
    final int MAX_X = (int) getToolkit().getScreenSize().getWidth();
    final int MAX_Y = (int) getToolkit().getScreenSize().getHeight();    
    final int GRIDSIZE = MAX_Y / 60;
//------------------------------------------------------------------------------------
    Visualizer(Map map) {
        this.map = map;
        this.panel = new MapPanel();
        this.panel.setBackground(Color.LIGHT_GRAY);
        this.getContentPane().add(BorderLayout.CENTER, panel);
        this.panel.setBackground(Const.BACKGROUND_COLOR);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(map.getNumRows() * GRIDSIZE + Const.UI_HORIZONTAL_DISPLACEMENT, map.getNumCols() * GRIDSIZE + Const.UI_VERTICAL_DISPLACEMENT);
        this.setVisible(true);
    }
//------------------------------------------------------------------------------------
    private class MapPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int row = 0; row < map.getNumRows(); row++) {
                for (int col = 0; col < map.getNumCols(); col++) {
                    if (map.getMap(row, col) == Const.START) {
                        g.setColor(Const.START_COLOR);
                    } else if (map.getMap(row,col) == Const.END) {
                        g.setColor(Const.END_COLOR);
                    } else if (map.getMap(row,col) == Const.VISIT) {
                        g.setColor(Const.VISIT_COLOR);
                    } else if (map.getMap(row,col) == Const.WALL) {
                        g.setColor(Const.WALL_COLOR);
                    } else if (map.getMap(row,col) == Const.ALLEY) {
                        g.setColor(Const.ALLEY_COLOR);
                    } else if (map.getMap(row,col) == Const.NO_PATH) {
                        g.setColor(Color.DARK_GRAY);
                    }
                    g.fillRect(col * GRIDSIZE, row * GRIDSIZE, GRIDSIZE, GRIDSIZE);
                }
            }
            this.repaint();
        }
    }
//------------------------------------------------------------------------------------
}