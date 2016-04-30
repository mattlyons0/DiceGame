package dicegame.UI;

import dicegame.Game;
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
 * Handles displaying the Scoreboard of the current game. Stores its own
 * instance of the distanceLeft in order to simulate individual dice rolls.
 *
 * @author Matt Lyons
 */
class GameScoreboardComponent extends JComponent {

    private Game gameLogic;

    private JTable table;

    private int[] distanceLeft;

    /**
     * Creates a new Game Scoreboard
     * @param gui The parent instance of GUI
     */
    public GameScoreboardComponent(final GUI gui) {
        super();

        this.gameLogic = gui.gameLogic;

        distanceLeft = new int[gameLogic.getNumberOfPlayers()];
        for (int index = 0; index < distanceLeft.length; index++) {
            distanceLeft[index] = gameLogic.getHoleLength();
        }

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
        table.setDefaultRenderer(table.getColumnClass(0), new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, false, hasFocus, row, column);

                label.setForeground(gui.gameplayPanel.animationComp.getPlayerColor(row));
                label.setBackground(gui.gameplayPanel.animationComp.getPlayerColorBackground(row));

                return label;
            }
        });

        add(table, cons);
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
                        return gameLogic.getStrokes(row, gameLogic.getHoleIndex());
                    case 2:
                        return distanceLeft[row];
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
     * @param playerIndex the index of the player who hit the ball
     */
    public void hitBall(int distance, int playerIndex) {
        distanceLeft[playerIndex] -= distance;
        updateTable();
    }

    /**
     * Get the current distance of specified player as displayed on the UI. Note
     * this value can vary from the value in gameLogic as it reflects each
     * individual dice roll instead of all of them at once.
     *
     * @param playerIndex index of the player to get the distance of
     * @return the distance in units the current player is away from the hole
     */
    public int getBallDistanceLeft(int playerIndex) {
        return distanceLeft[playerIndex];
    }

    /**
     * Resets scoreboard values to prepare for a new hole
     */
    public void newHole() {
        for (int index = 0; index < distanceLeft.length; index++) {
            distanceLeft[index] = gameLogic.getDistanceFromHole(gameLogic.getHoleIndex());
        }
        updateTable();
    }

    /**
     * Fire an event to update the table model
     */
    public void updateTable() {
        ((AbstractTableModel) table.getModel()).fireTableDataChanged();
    }
}
