package dicegame.UI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dicegame.Players;

/**
 * StatsPanel class: Interface accessed from StartMenu to look at players
 * statistics and rankings.
 * 
 * @author David Lukacs
 */
public class StatsPanel extends JPanel implements ActionListener {

	// Variables
	private GUI gui;
	private JLabel playerLabel, winLabel, lossLabel, scoreLabel, holeLabel, padding;
	private JLabel noPlayers;
	private JButton backButton;

	/**
	 *  Creates a StatsPanel to be used in the GUI to view Statistics
     *
     * @param gui GUI object which is used to display this panel
	 */
	public StatsPanel(GUI gui) {
		super();

		this.gui = gui;
		// gui.maximize();

		// setting GridBagConstraints
		setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		cons.weightx = 100;
		cons.weighty = 1;
		cons.insets = new Insets(5, 5, 5, 5); // Add some padding to components

		// Adding JButton backButton with ActionListener
		// used for returning back to StartMenu screen
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		add(backButton, cons);

		// Adding JLabels for top row indication of statistics: Wins, Loss,
		// Score
		cons.gridy++;
		cons.gridx = 1;
		winLabel = new JLabel("Wins");
		add(winLabel, cons);

		cons.gridx = 2;
		lossLabel = new JLabel("Losses");
		add(lossLabel, cons);

		cons.gridx = 3;
		scoreLabel = new JLabel("Score");
		add(scoreLabel, cons);

		// Creating players label based on amount of players created.
		// for some reason this crashes when defined at top with other
		// variables.
		int playersNum = gui.gameLogic.getNumberOfPlayers();
		int holeNum = gui.gameLogic.getNumberOfHoles();
		gui.gameLogic.createGameStats();
		String[] playerName = gui.gameLogic.getPlayer();
		int totalWins = gui.gameLogic.getPlayerOneWins() + gui.gameLogic.getPlayerTwoWins()
				+ gui.gameLogic.getPlayerThreeWins() + gui.gameLogic.getPlayerFourWins();
		gui.gameLogic.getGameStats();
		//gui.gameLogic.loadGameStats();
		//int gameStats[][] = new int[playersNum][holeNum];
		
		// Testing with rows and columns here.
		// playersNum = 8;
		// holeNum = 3;
		
		// Creating PlayerLabels for each player with wins, losses and scores
		for (int index = 0; index < playersNum; index++) {
			cons.gridy++;
			cons.gridx = 0;

			// Placeholder for players name:
			playerLabel = new JLabel(playerName[index]);
			add(playerLabel, cons);
			cons.gridx++;

			// Adding player 1 winning label and checking for wins.
			if (index == 0) {
				winLabel = new JLabel("" + gui.gameLogic.getPlayerOneWins());
				add(winLabel, cons);
			}

			// Adding player 2 winning label and checking for wins.
			if (index == 1) {
				winLabel = new JLabel("" + gui.gameLogic.getPlayerTwoWins());
				add(winLabel, cons);
			}

			// Adding player 3 winning label and checking for wins.
			if (index == 2) {
				winLabel = new JLabel("" + gui.gameLogic.getPlayerThreeWins());
				add(winLabel, cons);
			}

			// Adding player 4 winning label and checking for wins.
			if (index == 3) {
				winLabel = new JLabel("" + gui.gameLogic.getPlayerFourWins());
				add(winLabel, cons);
			}

			// Placeholder value for losses
			cons.gridx++;

			// Adding player 1 loss label and checking for losses.
			if (index == 0) {
				if (gui.gameLogic.getPlayerOneWins() == 0 && totalWins > 0) {
					lossLabel = new JLabel("" + 1);
					add(lossLabel, cons);
				} else {
					lossLabel = new JLabel("" + 0);
					add(lossLabel, cons);
				}
			}

			// Adding player 2 loss label and checking for losses.
			if (index == 1) {
				if (gui.gameLogic.getPlayerTwoWins() == 0 && totalWins > 0) {
					lossLabel = new JLabel("" + 1);
					add(lossLabel, cons);
				} else {
					lossLabel = new JLabel("" + 0);
					add(lossLabel, cons);
				}
			}

			// Adding player 3 loss label and checking for losses.
			if (index == 2) {
				if (gui.gameLogic.getPlayerThreeWins() == 0 && totalWins > 0) {
					lossLabel = new JLabel("" + 1);
					add(lossLabel, cons);
				} else {
					lossLabel = new JLabel("" + 0);
					add(lossLabel, cons);
				}
			}

			// Adding player 4 winning label and checking for losses.
			if (index == 3) {
				if (gui.gameLogic.getPlayerFourWins() == 0 && totalWins > 0) {
					lossLabel = new JLabel("" + 1);
					add(lossLabel, cons);
				} else {
					lossLabel = new JLabel("" + 0);
					add(lossLabel, cons);
				}
			}

			// Placeholder value for score (would be total of accumulated
			// strokes)
			cons.gridx++;
			if (index == 0)
				scoreLabel = new JLabel("" + gui.gameLogic.strokeSum(index));
			if (index == 1)
				scoreLabel = new JLabel("" + gui.gameLogic.strokeSum(index));
			if (index == 2)
				scoreLabel = new JLabel("" + gui.gameLogic.strokeSum(index));
			if (index == 3)
				scoreLabel = new JLabel("" + gui.gameLogic.strokeSum(index));
			add(scoreLabel, cons);
		}

		// Added padding label
		cons.gridy++;
		cons.gridx = 0;
		padding = new JLabel("Score Card");
		add(padding, cons);

		// ScoreCard portion: States holes followed by columns for each hole
		// number
		// Each player will have a row with stroke count for each hole.
		cons.gridy++;
		cons.gridx = 0;
		holeLabel = new JLabel("Holes: ");
		add(holeLabel, cons);
		// Creating labels for number of holes
		for (int index = 0; index < holeNum; index++) {
			cons.gridx++;
			holeLabel = new JLabel("|" + (index + 1) + "|");
			add(holeLabel, cons);
		}
		// Creating labels for players on ScoreCard
		for (int index = 0; index < playersNum; index++) {
			cons.gridy++;
			cons.gridx = 0;
			// players name is entered based on index
			playerLabel = new JLabel("" + playerName[index]);
			add(playerLabel, cons);
			for (int index2 = 0; index2 < holeNum; index2++) {
				cons.gridx++;
				// gameStat traversal done here for labels of each players hole value.
				// works with getStrokes(player, hole) or gameStats();
				holeLabel = new JLabel("" + gui.gameLogic.getStrokes(index, index2));//getStrokes(playerNum, holeNum);
				add(holeLabel, cons);
			}
		}

		// Conditional for no players added. Panel only displays backButton and
		// label
		// telling user to enter player before viewing StatsPanel.
		if (gui.gameLogic.getNumberOfPlayers() == 0) {
			removeAll();
			noPlayers = new JLabel("A player must be entered before viewing Statistics");
			cons.gridx = 0;
			cons.gridy = 0;
			add(backButton, cons);
			cons.gridy++;
			add(noPlayers, cons);
		}
	}
	
	/**
	 * ActionPerformed for JButton to return back to StartMenu
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton)
			gui.showStartMenu();
	}
}