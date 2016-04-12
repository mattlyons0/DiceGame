package dicegame.UI;

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
    private JTextField enterName;
    private JTextField displayName; 
    private JButton addPlayer; 
    private String name; 
    
    public PlayerCreationPanel(GUI gui){
        super();
        
        this.gui = gui;
        
        
        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();        
        cons.gridx = 0;
        cons.gridy = 0;
        cons.weightx = 1;
        cons.weighty = 30;
        cons.fill = GridBagConstraints.BOTH; //Stretch components to fill space
        cons.insets = new Insets(5, 5, 5, 5); //Add some padding to components

        
        //Add JLabel
        playersName = new JLabel ("Enter Players Name");
        add(playersName,cons);
        
    
        
        //Add JTextField (use getText() to get entered text)
        cons.gridy++;
        enterName = new JTextField();
        enterName.getText();
        add(enterName,cons);
        
        //You can update the players label by using setText("new text") on the label object
        //Add Create Button (remember to add action listener)
        cons.gridy++;
        addPlayer = new JButton("add");
        addPlayer.addActionListener(this);
        add(addPlayer, cons);
        
        //Adding JTextField to display name for entered players
        cons.gridy++;
        displayName = new JTextField();
        displayName.setText(name);
        add(displayName, cons);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        
        //Storing player name and exiting back to start menu
        if(event.getSource() == addPlayer){
        	
        	//storing name
        	name = enterName.getText();
        	displayName.setText(name);
        	name = name + " , "; 
        	
        	//removing panel
        	gui.switchStart();
        }
        
    }
    
}
