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
	private int playerScore1, playerScore2, playerScore3, playerScore4;
	private int winCounter1 = 0, winCounter2 = 0, winCounter3 = 0, winCounter4 = 0;
	private JButton backButton;

	// Calls JPanel constructor
	public StatsPanel(GUI gui) {
		super();

		this.gui = gui;
		gui.maximize();

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
		String[] playerName = gui.gameLogic.getPlayer();

		// Testing with rows and columns here.
		// playersNum = 8;
		// holeNum = 3;

		// Creating PlayerLabels for each player with wins, losses and scores
		for (int index = 1; index < playersNum; index++) {
			cons.gridy++;
			cons.gridx = 0;

			// Placeholder for players name: gui.gamelogic.getPlayer()
			playerLabel = new JLabel(playerName[index-1]);
			add(playerLabel, cons);

			// Placeholder value for wins
			cons.gridx++;
			
			winLabel = new JLabel("NA");
			add(winLabel, cons);

			// Placeholder value for losses
			cons.gridx++;
			lossLabel = new JLabel("NA");
			add(lossLabel, cons);

			// Placeholder value for score (would be total of accumulated
			// strokes)
			cons.gridx++;
			if(index == 1)
				scoreLabel = new JLabel("" + playerScore1);
			if(index == 2)
				scoreLabel = new JLabel("" + playerScore2);
			if(index == 3)
				scoreLabel = new JLabel("" + playerScore3);
			if(index == 4)
				scoreLabel = new JLabel("" + playerScore4);
			add(scoreLabel, cons);
		}

		// Added padding label
		cons.gridy++;
		padding = new JLabel("");
		add(padding, cons);

		// ScoreCard portion: States holes followed by columns for each hole
		// number
		// Each player will have a row with stroke count for each hole.
		cons.gridy++;
		cons.gridx = 0;
		holeLabel = new JLabel("Holes: ");
		add(holeLabel, cons);

		// Creating labels for number of holes
		for (int index = 1; index <= holeNum; index++) {
			cons.gridx++;
			holeLabel = new JLabel("|" + index + "|");
			add(holeLabel, cons);
		}

		// Creating labels for number of Players
		for (int index = 1; index < playersNum; index++) {
			cons.gridy++;
			cons.gridx = 0;
			// Placeholder for players entered name
			// gui.gameLogic.getPlayer();
			playerLabel = new JLabel(playerName[index-1]);;
			add(playerLabel, cons);
			// Setting holes for individual players
			for (int index2 = 1; index2 <= holeNum; index2++) {
				cons.gridx++;
				// Placeholder for gui.gameLogic.getstroke
				// confused on how to actually get each holes individual stroke
				// here
				holeLabel = new JLabel("" + gui.gameLogic.getStrokes());
				add(holeLabel, cons);
			}

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


/*WINS Label: For calculating wins with addStroke method adding to these. 
 *  			if(playerScore1 <= playerScore2 && playerScore1 <= playerScore3 
					&& playerScore1 <= playerScore4){	
				if(index == 1){
					winCounter1++;
					winLabel = new JLabel("" + winCounter1);
				}
			}
			if(playerScore2 <= playerScore1 && playerScore2 <= playerScore3 
					&& playerScore2 <= playerScore4){	
				if(index == 2){
					winCounter2++;
					winLabel = new JLabel("" + winCounter2);
				}
			}
			if(playerScore3 <= playerScore1 && playerScore3 <= playerScore2 
					&& playerScore3 <= playerScore4){	
				if(index == 3){
					winCounter3++;
					winLabel = new JLabel("" + winCounter3);
				}
			}
			if(playerScore4 <= playerScore1 && playerScore4 <= playerScore2 
					&& playerScore4 <= playerScore3){	
				if(index == 4){
					winCounter4++;
					winLabel = new JLabel("" + winCounter4);
				}
			}

lossLabel:
			if(index == 1){
				if(winCounter1 < 1)
					lossLabel = new JLabel("1");
			}
			if(index == 2){
				if(winCounter2 < 1)
					lossLabel = new JLabel("1");
			}
			if(index == 3){
				if(winCounter3 < 1)
					lossLabel = new JLabel("1");
			}
			if(index == 4){
				if(winCounter4 < 1)
					lossLabel = new JLabel("1");
			}
*/
