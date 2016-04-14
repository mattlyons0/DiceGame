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

	private GUI gui;
	private JLabel playersName;
	private JLabel displayName;
	private JTextField enterName;
	private JButton addPlayer;
	private String name;

	/**
	 * Create a PlayerCreationPanel to be used in the GUI
	 *
	 * @param gui
	 *            GUI object which is used to display this panel *
	 */
	public PlayerCreationPanel(GUI gui) {
		// Calls JPanel Constructor
		super();

		this.gui = gui;

		setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		cons.weightx = 100;
		cons.weighty = 1;
		cons.gridwidth = 2;
		cons.insets = new Insets(5, 5, 5, 5); // Add some padding to components

		// Add JLabel to prompt user to enter name.
		playersName = new JLabel("Enter Players Name");
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

		// Adding JButton with ActionListener to confirm addion of player
		cons.gridx++;
		cons.weightx = 1;
		addPlayer = new JButton("Add");
		addPlayer.addActionListener(this);
		add(addPlayer, cons);

		// Adding JLabel to display user on how to enter players name.
		cons.gridy++;
		cons.gridx = 0;
		cons.gridwidth = 2;
		cons.weightx = 100;
		cons.anchor = GridBagConstraints.NORTH;
		displayName = new JLabel("Please enter name and press add");
		add(displayName, cons);

	}

	/**
	 * ActionPerfomed: Connecting with JTextfield to enter player's name.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {

		// Storing player name and exiting back to start menu
		if (event.getSource() == addPlayer || event.getSource() == enterName) {

			// storing name
			name = enterName.getText();

			// for none text enters: name.trim().equals("");
			if (name.trim().equals("")) {
				displayName.setText("A name must be entered.");
			}

			else {
				// adding players to game.
				gui.startMenuPanel.addPlayer(name);

				// removing panel
				gui.switchStart();
			}

		}

	}
}
