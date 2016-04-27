package dicegame.UI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Class PlayerCreationPanel: Interface accessed from StartMenu to create
 * players for DiceGolf.
 *
 * @author David Lukacs
 */
public class PlayerCreationPanel extends JPanel implements ActionListener {

    // Variables
    private GUI gui;
    private JLabel playersName, displayName;
    private JTextField enterName;
    private JButton addPlayer, backButton;
    private String name;
    private int counter;

    /**
     * Create a PlayerCreationPanel to be used in the GUI
     *
     * @param gui GUI object which is used to display this panel *
     */
    public PlayerCreationPanel(GUI gui) {
        // Calls JPanel Constructor.
        super();

        this.gui = gui;

        // Setting GridBagConstraints.
        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 0;
        cons.weightx = 100;
        cons.weighty = 1;
        cons.gridwidth = 2;
        cons.insets = new Insets(5, 5, 5, 5); // Add some padding to components

        // Adding JButton with ActionListener.
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        cons.anchor = GridBagConstraints.NORTHWEST;
        add(backButton, cons);

        // Add JLabel to prompt user to enter player name.
        cons.gridwidth = 2;
        cons.weightx = 90;
        cons.anchor = GridBagConstraints.CENTER;
        playersName = new JLabel("Enter players name. 4 player limit");
        add(playersName, cons);

        // Added JTextField for user to enter player name.
        cons.gridy++;
        cons.gridwidth = 1;
        cons.weightx = 90;
        enterName = new JTextField(20);
        enterName.addActionListener(this);
        enterName.setMinimumSize(
                new Dimension(enterName.getPreferredSize().width - 1, enterName.getPreferredSize().height));
        add(enterName, cons);

        // Adding JButton with ActionListener to confirm addition of player
        cons.gridx++;
        cons.weightx = 1;
        addPlayer = new JButton("Add");
        addPlayer.addActionListener(this);
        add(addPlayer, cons);

        // Adding JLabel to display user on how to enter players name.
        cons.gridy++;
        cons.gridx--;
        cons.gridwidth = 2;
        cons.weightx = 100;
        cons.anchor = GridBagConstraints.NORTH;
        displayName = new JLabel("Please enter name and press add");
        add(displayName, cons);

    }

    /**
     * ActionPerfomed: Connecting with JTextfield & JButtons to enter player's
     * name and return to StartMenu.
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        // Storing player name and exiting back to start menu
        if (event.getSource() == addPlayer || event.getSource() == enterName) {

            // storing name to string variable
            name = enterName.getText();

            // conditional for non-text being entered.
            if (name.trim().equals("")) {
                displayName.setText("A name must be entered.");
            } 
            // Conditional for reaching max amount of players of 4. 
            // User is directed to go back to StartMenu.
            if(gui.gameLogic.getNumberOfPlayers() == 5){
            	displayName.setText("Player limit of 4 reached, please return to Start");
            }
            else {
                // adding players to game.
                gui.startMenuPanel.addPlayer(name);
                // removing panel
                gui.showStartMenu();
            }

        }

        // For backButton to return to Start Menu
        if (event.getSource() == backButton) {
            gui.showStartMenu();
        }

    }
}
