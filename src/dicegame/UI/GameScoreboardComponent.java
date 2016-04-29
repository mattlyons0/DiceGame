package dicegame.UI;

import dicegame.Game;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 * Handles displaying the Scoreboard of the current game
 *
 * @author Matt Lyons
 */
class GameScoreboardComponent extends JComponent {

    private Game gameLogic;

    private JTable table;

    private int distanceLeft;
    private int strokes;

    /**
     * Creates a new Game Scoreboard
     */
    public GameScoreboardComponent(final GUI gui) {
        super();

        this.gameLogic = gui.gameLogic;

        distanceLeft = gameLogic.getHoleLength();
        strokes = 0;

        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 0;
        cons.weightx = 1;
        cons.weighty = 1;
        cons.fill = GridBagConstraints.BOTH;

        table = new JTable(getTableModel());
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setDefaultRenderer(table.getColumnClass(0), new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, false, hasFocus, row, column);
                
                Color playerColor = gui.gameplayPanel.animationComp.getPlayerColor(row);
                label.setForeground(playerColor);
                //Perceived Luminance from the W3C spec: https://www.w3.org/TR/AERT#color-contrast
                double luminance = (0.299*(playerColor.getRed()/255) + 0.587*(playerColor.getGreen()/255) + 0.114*(playerColor.getBlue()/255)); 
                //W3C Color Contrast Reccomendations https://www.w3.org/TR/WCAG20/
                if ((luminance + 0.05) / (0.0 + 0.05) > (1.0 + 0.05) / (luminance + 0.05)){
                    label.setBackground(Color.BLACK);
                } else {
                    label.setBackground(Color.WHITE);
                }
                
                return label;
            }
        });

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, cons);
    }

    private TableModel getTableModel() {
        final String[] columns = {"Name", "Strokes", "Distance from Hole"};

        AbstractTableModel model = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return gameLogic.getNumberOfPlayers();
            }

            @Override
            public int getColumnCount() {
                return columns.length;
            }

            @Override
            public Object getValueAt(int row, int col) {
                switch (col) {
                    case 0:
                        return gameLogic.getPlayer()[row];
                    case 1:
                        return strokes;
                    case 2:
                        return distanceLeft;
                    default:
                        return "";
                }
            }

            @Override
            public String getColumnName(int column) {
                return columns[column];
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        return model;
    }

    /**
     * Update Scoreboard by subtracting distance hit from distance remaining
     *
     * @param distance the distance the ball was just hit
     */
    public void hitBall(int distance) {
        distanceLeft -= distance;
        strokes = gameLogic.getStrokes();
        ((AbstractTableModel) table.getModel()).fireTableDataChanged();
    }
    
    public int getBallDistanceLeft(){
        System.out.println(gameLogic.getDistanceFromHole());
        return distanceLeft;
    }
    public void newHole(){
        strokes = 0;
        distanceLeft = gameLogic.getDistanceFromHole();
        ((AbstractTableModel) table.getModel()).fireTableDataChanged();
    }
}
