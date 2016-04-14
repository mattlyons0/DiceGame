package dicegame.UI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*; 

/**
 *
 * @author David Lukacs
 */
public class PlayerCreationPanel extends JPanel implements ActionListener{
    
    private GUI gui;
    private JLabel playersName; 
    private JLabel displayName;
    private JTextField enterName; 
    private JButton addPlayer; 
    private String name; 
    
    public PlayerCreationPanel(GUI gui){
        super();
        
        this.gui = gui;
        
        
        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();        
        cons.gridx = 0;
        cons.gridy = 0;
        cons.weightx = 100;
        cons.weighty = 1;
        cons.gridwidth = 2;
        cons.insets = new Insets(5, 5, 5, 5); //Add some padding to components

        
        //Add JLabel
        playersName = new JLabel ("Enter Players Name");
        add(playersName,cons);
        
    
        
        //Add JTextField (use getText() to get entered text)
        cons.gridy++;
        cons.gridwidth = 1;
        cons.weightx=90;
        enterName = new JTextField(20);
        enterName.setMinimumSize(new Dimension(enterName.getPreferredSize().width - 1, enterName.getPreferredSize().height));
        add(enterName,cons);
        
        //You can update the players label by using setText("new text") on the label object
        //Add Create Button (remember to add action listener)
        cons.gridx++;
        cons.weightx = 1;
        addPlayer = new JButton("add");
        addPlayer.addActionListener(this);
        add(addPlayer, cons);
        
        //Adding JTextField to display name for entered players
        cons.gridy++;
        cons.gridx = 0;
        cons.gridwidth = 2;
        cons.weightx = 100;
        cons.anchor = GridBagConstraints.NORTH;
        displayName = new JLabel("Please enter name and press add");
        add(displayName, cons);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        
        //Storing player name and exiting back to start menu
        if(event.getSource() == addPlayer){
        	
        	//storing name
        	name = enterName.getText();
        	
        	//for none text enters: name.trim().equals("");
        	
        	//adding players to game.
        	gui.startMenuPanel.addPlayer(name);
        	
        	//removing panel
        	gui.switchStart();
        }
        
    }
    
}
