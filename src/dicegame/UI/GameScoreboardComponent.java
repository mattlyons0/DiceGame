package dicegame.UI;

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

    private JTable table;

    /**
     * Creates a new Game Scoreboard
     */
    public GameScoreboardComponent() {
        super();

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
        final String[] columns = {"Name", "Turns", "Distance from Hole"};

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
                        return 0;
                    case 2:
                        return 0;
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
}
