package dicegame.UI;

import dicegame.GUI;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Contains all the UI in the Start Menu and methods to modify the data displayed
 * @author Matt Lyons
 */
public class StartMenuPanel extends JPanel implements ActionListener{
    
    private GUI gui;
    
    private JLabel playersLabel;
    private JButton startButton;
    private JButton createPlayerButton;
    private JButton quitButton; 
    
    public StartMenuPanel(GUI gui){
        super(); //Calls JPanel constructor
        
        this.gui=gui;
        
        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        
        startButton = new JButton("Start Game");
        startButton.addActionListener(this);
        cons.gridx=0; cons.gridy=0; //Use Grid cell 0,0
        cons.weightx = 100; cons.weighty = 1;
        cons.insets = new Insets(5,5,5,5); //Add 5px all around of padding
        add(startButton,cons);
        
        createPlayerButton = new JButton("Create New Player");
        createPlayerButton.addActionListener(this);
        cons.gridy++;
        add(createPlayerButton,cons);
        
        cons.gridy++;
        quitButton = new JButton("Quit Game"); 
        quitButton.addActionListener(this);
        add(quitButton,cons);
        
        playersLabel = new JLabel("No Players Created");
        cons.gridy++; cons.anchor = GridBagConstraints.SOUTH;
        cons.weighty = 100;
        add(playersLabel,cons);
    }
    
    public void addPlayer(String playerName){
        if(playersLabel.getText().equals("No Players Created")){
            playersLabel.setText("Players: "+playerName);
        } else {
            playersLabel.setText(playersLabel.getText()+", "+playerName);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == startButton){
            //Start Game
        } else if(event.getSource() == createPlayerButton){
            gui.createPlayer();
        } else if(event.getSource() == quitButton){
            System.exit(0);
        }
    }
}
