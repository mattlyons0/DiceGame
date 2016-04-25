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
 * StatsPanel class: Interface accessed from StartMenu to look at players statistics and rankings. 
 * 
 * @author David Lukacs
 */
public class StatsPanel extends JPanel implements ActionListener{
	
	//Variables
	private GUI gui; 
	private JLabel playerLabel, winLabel, lossLabel, scoreLabel, holeLabel, padding;
	private JLabel noPlayers;
	private JButton backButton; 
    
	Players p = new Players();
	
	//Calls JPanel constructor
	public StatsPanel(GUI gui){
		super();
		
		this.gui = gui; 
		gui.maximize();
		
		//setting GridBagConstraints
		setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		cons.weightx = 100;
		cons.weighty = 1;
		//cons.gridwidth = 10;
		//cons.
		cons.insets = new Insets(5, 5, 5, 5); // Add some padding to components
		
		//Adding JButton backButton with ActionListener
		//used for returning back to StartMenu screen 
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        add(backButton, cons);
        
        //Adding JLabels for top row indication of statistics: Wins, Loss, Score 
        cons.gridy++; 
        cons.gridx = 1;
        winLabel = new JLabel("Wins");
        add(winLabel, cons);
        
        cons.gridx = 2;
        lossLabel = new JLabel("Losses");
        add(lossLabel, cons);
        
        cons.gridx  = 3; 
        scoreLabel = new JLabel("Score");
        add(scoreLabel, cons);
        
        //Adding JLabels for creation of statistics table
        //Would be placeholder for player 1 row: wins, losses & score 
        cons.gridy++;
        cons.gridx = 0;
        playerLabel = new JLabel(gui.gameLogic.getPlayer() + " : ");
        add(playerLabel, cons);
        
        cons.gridx++;
        winLabel = new JLabel("NA");
        add(winLabel,cons);
        
        cons.gridx++;
        lossLabel = new JLabel("NA");
        add(lossLabel,cons);
        
        cons.gridx++;
        scoreLabel = new JLabel(p.getScore() + "");
        add(scoreLabel,cons);
        
        //Would be placeholder for player 2 row: wins, losses & score
        cons.gridy++;
        cons.gridx = 0;
        playerLabel = new JLabel("Player 2: ");
        add(playerLabel, cons);
        
        cons.gridx++;
        winLabel = new JLabel("NA");
        add(winLabel,cons);
        
        cons.gridx++;
        lossLabel = new JLabel("NA");
        add(lossLabel,cons);
        
        cons.gridx++;
        scoreLabel = new JLabel("NA");
        add(scoreLabel,cons);
        
        //Would be placeholder for player 3 row: wins, losses & score 
        cons.gridy++;
        cons.gridx = 0;
        playerLabel = new JLabel("Player 3: ");
        add(playerLabel, cons);
		
        cons.gridx++;
        winLabel = new JLabel("NA");
        add(winLabel,cons);
        
        cons.gridx++;
        lossLabel = new JLabel("NA");
        add(lossLabel,cons);
        
        cons.gridx++;
        scoreLabel = new JLabel("NA");
        add(scoreLabel,cons);
        
        //Would be placeholder for player 4 row: wins, losses & score 
        cons.gridy++;
        cons.gridx = 0;
        playerLabel = new JLabel("Player 4: ");
        add(playerLabel, cons);
      
        cons.gridx++;
        winLabel = new JLabel("NA");
        add(winLabel,cons);
        
        cons.gridx++;
        lossLabel = new JLabel("NA");
        add(lossLabel,cons);
        
        cons.gridx++;
        scoreLabel = new JLabel("NA");
        add(scoreLabel,cons);
        
        //padding added for bottom
        cons.gridy++;
        padding = new JLabel("");
        add(padding,cons);
        
        
        //ScoreCard portion:
        int holeNum = gui.gameLogic.getNumberOfHoles();
        int playersNum = gui.gameLogic.getNumberOfPlayers();
        //Testing with rows and columns here. 
        //playersNum = 8;
        //holeNum = 3; 
        
        cons.gridy++;
        cons.gridx = 0; 
        holeLabel = new JLabel("Holes: ");
        add(holeLabel, cons);
        
        //Creating labels for number of holes
        for(int index = 1; index <= holeNum; index++){
        	cons.gridx++;
        	holeLabel = new JLabel("|" +index + "|");
        	add(holeLabel,cons);
        }
        
        //Creating labels for number of Players
        for(int index = 1; index <= playersNum; index++){
        	cons.gridy++;
        	cons.gridx = 0;
        	//Placeholder for players entered name
        	playerLabel = new JLabel("Player " + index);
        	add(playerLabel, cons);
        	//Setting holes for individual players
        	for(int index2 = 1; index2 <= holeNum; index2++){
            	cons.gridx++;
            	//Placeholder for gui.gameLogic.getstroke
            	// confused on how to actually get each holes individual stroke here
            	holeLabel = new JLabel("" + gui.gameLogic.getStrokes());
            	add(holeLabel,cons);
            }
        	
        }
        		
        		
        //TODO: Would be conditional for dynamic player adding
        // Currently the default getNumberofPlayers() == 1; 
        if(gui.gameLogic.getNumberOfPlayers() == 0){
        	removeAll();
        	cons.anchor = GridBagConstraints.CENTER;
        	noPlayers = new JLabel("No Players have been added");
        	add(noPlayers,cons);
        }
        
        else{
        	
        }
      
	}
	
	/**
	 * ActionPerformed for JButton to return back to StartMenu
	 */
    @Override
	public void actionPerformed(ActionEvent event){
		if (event.getSource() == backButton)
			gui.showStartMenu();
	}
}

