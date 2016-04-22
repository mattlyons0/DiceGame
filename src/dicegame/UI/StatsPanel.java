package dicegame.UI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * StatsPanel class: Interface accessed from StartMenu to look at players statistics and rankings. 
 * 
 * @author David Lukacs
 */
public class StatsPanel extends JPanel implements ActionListener{
	
	//Variables
	private GUI gui; 
	
	private String player; 
	private JLabel topDescription;
	private JButton backButton; 
	
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
		cons.gridwidth = 2;
		
		//Adding JLabel topDescription
		//To inform player about statistics
		topDescription = new JLabel("Statistics viewed below here");
		add(topDescription, cons);
		
		//Adding JButton backButton with ActionListener
		//used for returning back to StartMenu screen 
        cons.gridy++;
		backButton = new JButton("Back");
        backButton.addActionListener(this);
        add(backButton, cons);
		
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

