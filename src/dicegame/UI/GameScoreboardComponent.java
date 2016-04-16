package dicegame.UI;

import dicegame.Game;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
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
    public GameScoreboardComponent(GUI gui) {
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

        JScrollPane scroll = new JScrollPane(table);

        add(scroll, cons);
    }

    private TableModel getTableModel() {
        final String[] columns = {"Name", "Strokes", "Distance from Hole"};

        AbstractTableModel model = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return 1;
            }

            @Override
            public int getColumnCount() {
                return columns.length;
            }

            @Override
            public Object getValueAt(int row, int col) {
                switch (col) {
                    case 0:
                        return "Player " + (row + 1);
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
     * @param distance the distance the ball was just hit
     */
    public void hitBall(int distance){
        
        distanceLeft -= distance;
        strokes = gameLogic.getStrokes();
        ((AbstractTableModel)table.getModel()).fireTableDataChanged();
    }
}
