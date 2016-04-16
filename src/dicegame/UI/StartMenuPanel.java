package dicegame.UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Contains all the UI in the Start Menu and methods to modify the data
 * displayed
 *
 * @author Matt Lyons
 */
public class StartMenuPanel extends JPanel implements ActionListener {

    private GUI gui;

    private JLabel playersLabel;
    private JButton startButton;
    private JButton createPlayerButton;
    private JButton statisticsButton;
    private JButton quitButton;

    /**
     * Create a StartMenuPanel to be used in the GUI
     *
     * @param gui GUI object which is used to display this panel
     */
    public StartMenuPanel(GUI gui) {
        super(); //Calls JPanel constructor

        this.gui = gui;

        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        startButton = new JButton("Start Game");
        startButton.addActionListener(this);

        cons.gridx = 0;
        cons.gridy = 0; //Use Grid cell 0,0
        cons.weightx = 100;
        cons.weighty = 1;
        cons.insets = new Insets(5, 5, 5, 5); //Add 5px all around of padding
        add(startButton, cons);

        createPlayerButton = new JButton("Create New Player");
        createPlayerButton.addActionListener(this);
        cons.gridy++;
        add(createPlayerButton, cons);

        cons.gridy++;
        statisticsButton = new JButton("Statistics");
        statisticsButton.setEnabled(false);
        statisticsButton.setToolTipText("Coming next sprint");
        statisticsButton.addActionListener(this);
        add(statisticsButton, cons);

        cons.gridy++;
        quitButton = new JButton("Quit Game");
        quitButton.addActionListener(this);
        add(quitButton, cons);

        playersLabel = new JLabel("No Players Created");
        cons.gridy++;
        cons.anchor = GridBagConstraints.SOUTH;
        cons.weighty = 100;
        add(playersLabel, cons);

    }

    /**
     * Add a player to the UI in the startMenu
     *
     * @param playerName String of players name
     */
    public void addPlayer(String playerName) {
        if (playersLabel.getText().equals("No Players Created")) {
            playersLabel.setText("Players: " + playerName);
            gui.gameLogic.createPlayer(playerName);
        } else {
            playersLabel.setText(playersLabel.getText() + ", " + playerName);
            gui.gameLogic.createPlayer(playerName);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == startButton) {
            gui.startGame();
        } else if (event.getSource() == createPlayerButton) {
            gui.createPlayer();
        } else if (event.getSource() == statisticsButton) {
            gui.showStatistics();
        } else if (event.getSource() == quitButton) {
            System.exit(0);
        }
    }
}
